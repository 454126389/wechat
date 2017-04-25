<%@ page language="java"
	import="java.util.*,java.util.List,com.ruiyi.wechat.model.DeviceType,com.ruiyi.wechat.string.Language"
	pageEncoding="utf-8"%>
<%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.5, user-scalable=no">
<title>征服者</title>
 <script type="text/javascript" src="../jsp/js/head.js"></script>
<script type="text/javascript"
	src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
	<script src="js/utils/language.js" type="text/javascript"></script>

<body onload="init();">

	<div class="MainDiv">
		<select name="did" id="did" "
				data-corners="false"
			onchange="carChoise()">
		</select>

		<div id="TipShow">
			<img src="../jsp/images/pickup_tip.jpg" style="width:100%;" />
		</div>
	</div>

</body>


<script type="text/javascript">
	//采用正则表达式获取地址栏参数
	function GetQueryString(name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
		var r = window.location.search.substr(1).match(reg);
		if (r != null)
			return unescape(r[2]);
		return null;
	}

	//初始化JS
	function initWeixinJs(time, noncestr, signa) {
		wx.config({
			debug : false,
			appId : "wx71460f3d267fcd41",
			timestamp : time,
			nonceStr : noncestr,
			signature : signa,
			jsApiList : [ 'onMenuShareAppMessage' ]
		});
	}

	function init() {
		//获取初始化WXJS信息
		if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} else {// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

				var jsons = eval(xmlhttp.responseText);
				initWeixinJs(jsons[0].timestamp, jsons[0].nonceStr,
						jsons[0].signature)

			}
		};
		xmlhttp.open("GET", "../ToolsServlet?action=getJsid&url="
				+ encodeURIComponent(location.href.split('#')[0] + "@time="
						+ (new Date()).getTime()), true);
		xmlhttp.send();

	}

	var objs = new Array(); //定义一数组 

	//解析显示
	function updatelist(objs) {

		if (objs.length > 0)
			for (var i = 0; i < objs.length; i++)
				$("#did").append(
						"<option value='"+objs[i].no+"'>" + objs[i].name
								+ "</option>").trigger("create");
		else
			$("#did").append("<option value='0'>"+device_nosupport[lsel]+"</option>").trigger(
					"create");
		$("#did option:eq(0)").attr("selected", true); //设置属性selected

		$("#did").selectmenu("refresh");

	}

	wx.ready(function() {

		//请求服务器数据
		$.get("../carLocationServlet?action=getOnlineDevice&weid="
				+ GetQueryString("weid"), function(data, status) {
			objs = eval(data);
			updatelist(objs);

			wx.onMenuShareAppMessage({
				title : pick_tip_tv[lsel], // 分享标题
				desc : gps_tip_tv[lsel], // 分享描述
				link : "http://wechat.conqueror.cn/jsp/sendway2.jsp?weid="
						+ GetQueryString("weid") + "&did=" + $("#did").val(), // 分享链接
				imgUrl : 'http://wechat.conqueror.cn/jsp/images/log_min.jpg', // 分享图标
				type : 'link', // 分享类型,music、video或link，不填默认为link
				dataUrl : '', // 如果type是music或video，则要提供数据链接，默认为空
				success : function() {
					// 用户确认分享后执行的回调函数
					// 用户确认分享后，删除接人状态
				},
				cancel : function() {
					// 用户取消分享后执行的回调函数
				}
			});

		});

		wx.error(function(res) {

			if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
				xmlhttp = new XMLHttpRequest();
			} else {// code for IE6, IE5
				xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
			}
			xmlhttp.onreadystatechange = function() {
				if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

					alert(log_fails_tv[lsel]);

				}
			};
			xmlhttp.open("GET", "../ToolsServlet?action=errorlog&log="
					+ res.errMsg, true);
			xmlhttp.send();

		});
	});
</script>


</html>
