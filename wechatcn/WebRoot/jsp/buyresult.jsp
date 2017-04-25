<%@ page language="java"
	import="java.util.*,java.util.List,com.ruiyi.wechat.model.Positions,com.ruiyi.wechat.string.Language"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.5, user-scalable=no">
<title>征服者</title>
<!--  <script type="text/javascript" src="../jsp/js/head.js"></script> -->

<script src="js/carlocation/locationtor.js" type="text/javascript"></script>
<script src="js/utils/util.js" type="text/javascript"></script>

<script type="text/javascript"
	src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
</head>
<body>

	<script language="javascript">
		//配置weixinjs
		wx.config({
			debug : true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
			appId :
	<%=request.getAttribute("appid")%>
		, // 必填，公众号的唯一标识
			timestamp :
	<%=request.getAttribute("timestamp")%>
		, // 必填，生成签名的时间戳
			nonceStr :
	<%=request.getAttribute("nonceStr")%>
		, // 必填，生成签名的随机串
			signature :
	<%=request.getAttribute("signature")%>
		,// 必填，签名，见附录1
			jsApiList : [ 'chooseWXPay' ]
		// 必填，需要使用的JS接口列表，所有JS接口列表见附录2
		});

		function onBridgeReady() {
			WeixinJSBridge.invoke('getBrandWCPayRequest', {
				"appId" : <%=request.getAttribute("appid")%>,//公众号名称，由商户传入     
				"timeStamp" : " 1395712654", //时间戳，自1970年以来的秒数     
				"nonceStr" : "e61463f8efa94090b1f366cccfbbb444", //随机串     
				"package" : "prepay_id=u802345jgfjsdfgsdg888",
				"signType" : "MD5", //微信签名方式：     
				"paySign" : "70EA570631E4BB79628FBCA90534C63FF7FADD89" //微信签名 
			}, function(res) {
				if (res.err_msg == "get_brand_wcpay_request：ok") {
				} // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。 
			});
		}
		if (typeof WeixinJSBridge == "undefined") {
			if (document.addEventListener) {
				document.addEventListener('WeixinJSBridgeReady', onBridgeReady,
						false);
			} else if (document.attachEvent) {
				document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
				document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
			}
		} else {
			onBridgeReady();
		}

		function init(objs) {
			for ( var car in objs) {
				$("#did").append(
						"<option value='"+objs[car].id+"'>" + objs[car].id
								+ ":<br/>" + objs[car].alias + "</option>")
						.trigger("create");
			}
		}
		
		//统一下单
		function payuni()
		{
			if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp = new XMLHttpRequest();
	} else {// code for IE6, IE5
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

			//showloading();

			var jsons = eval(xmlhttp.responseText);
			initWeixinJs(jsons[0].timestamp, jsons[0].nonceStr,
					jsons[0].signature)

		}
	};
	xmlhttp.open("GET", "../ToolsServlet?action=getJsid&url="
			+ encodeURIComponent(location.href.split('#')[0]+"@time="+(new Date()).getTime()), true);
	xmlhttp.send();
		}
		

		function check() {
			if ($("#did").val() == 0)
				alert("请选择设备");
			else if ($("#cost").val() == 0)
				alert("请选择服务");
			else {
		/* 		wx.chooseWXPay({
					timestamp : 0, // 支付签名时间戳，注意微信jssdk中的所有使用timestamp字段均为小写。但最新版的支付后台生成签名使用的timeStamp字段名需大写其中的S字符
					nonceStr : '', // 支付签名随机串，不长于 32 位
					package : '', // 统一支付接口返回的prepay_id参数值，提交格式如：prepay_id=***）
					signType : '', // 签名方式，默认为'SHA1'，使用新版支付需传入'MD5'
					paySign : '', // 支付签名
					success : function(res) {
						// 支付成功后的回调函数
					}
				}); */
			}
			alert($("#did").val() + ":" + $("#cost").val());
		}

		//采用正则表达式获取地址栏参数
		function GetQueryString(name) {
			var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
			var r = window.location.search.substr(1).match(reg);
			if (r != null)
				return unescape(r[2]);
			return null;
		}

		//请求服务器数据
		$.get("../carLocationServlet?action=getCarListByWeid&weid="
				+ GetQueryString("weid"), function(data, status) {
			objs = eval(data);
			init(objs);
		});
	</script>


	<div data-role="page">

		<div data-role="header">
			<h1>设备服务购买</h1>
		</div>

		<div data-role="content">

			<fieldset data-role="fieldcontain">
				<label for="did">选择设备</label> <select name="did" id="did">
					<option value="0">请选择设备</option>
				</select>
			</fieldset>

			<fieldset data-role="fieldcontain">
				<label for="cost">选择服务</label> <select name="cost" id="cost">

					<option value="0">请选择服务</option>
					<optgroup label="平台服务">
						<option value="120">120元/1年</option>
						<option value="200">200元/2年</option>
						<option value="260">260元/3年</option>
					</optgroup>
					<optgroup label="短信服务">
						<option value="10">10元/100条</option>
						<option value="20">20元/200条</option>
						<option value="50">50元/500条</option>
					</optgroup>
				</select>
			</fieldset>
		</div>
		<input type="submit" value="提交" onclick="check()">


	</div>

	</div>

</body>
</html>


