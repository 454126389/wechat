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
<script type="text/javascript" src="../jsp/js/head.js"></script>

<script src="js/carlocation/locationtor.js" type="text/javascript"></script>
<script src="js/utils/util.js" type="text/javascript"></script>

<script type="text/javascript"
	src="http://res.wx.qq.com/open/js/jweixin-1.1.0.js"></script>
<script src="js/utils/language.js" type="text/javascript"></script>
</head>
<body>



	<div data-role="page" id="pageone">

		<div data-role="header">
			<h1 id='buy_service_tip_tv'></h1>
		</div>

		<div data-role="content">

			<div id="div_phpne">
				<label for="phone">手机：</label> <input type="tel" id="phone"
					data-clear-btn="true" placeholder="收入手机号码">

				<div align="right">
					<input id="doyz" type="button" data-inline="true" value="查找sim卡"
						onclick="getPhoneIccid()">
				</div>
			</div>

			<div id="div_buy" style="display: none">

				<fieldset data-role="fieldcontain">
					<label for="did" id='device_sel'>选择卡</label> <select name="did"
						id="did">
						<!-- 		<option value="0" id='device_sel_option'>选择卡</option> -->

					</select>
				</fieldset>

				<fieldset data-role="fieldcontain">
					<label for="money" id='service_sel'>购买服务</label> <select
						name="money" id="money">

						<!-- 		<option value="0" id='service_sel_option'>选择服务</option> -->

						<optgroup label="半年套餐">
							<option value="60">60元/每月300M</option>
							<option value="70">70元/每月400M</option>
							<option value="85">85元/每月500M</option>
							<option value="100">100元/每月600M</option>
							<option value="140">140元/每月800M</option>
							<option value="150">150元/每月1G</option>
						</optgroup>

						<optgroup label="包年套餐">
							<option value="60">110元/每月300M</option>
							<option value="70">140元/每月400M</option>
							<option value="85">160元/每月500M</option>
							<option value="100">200元/每月600M</option>
							<option value="140">270元/每月800M</option>
							<option value="150">300元/每月1G</option>
						</optgroup>

					</select>
				</fieldset>
				<input type="submit" id="buybtn" value="提交" onclick="check()"
					disabled="disabled">
			</div>
		</div>



	</div>

	</div>



</body>


<script language="javascript">
	//	document.getElementById("service_sel_option").innerText = servicelist_tv[lsel];

	//初始化JS
	/* 	function initWeixinJs(time, noncestr, signa) {
	 wx.config({
	 debug : false,
	 appId : "wx71460f3d267fcd41",
	 timestamp : time,
	 nonceStr : noncestr,
	 signature : signa,
	 jsApiList : [ 'chooseWXPay' ]
	 });
	 }

	 wx.ready(function() {
	 wx.hideOptionMenu();
	 //按钮可以点击
	 $("#buybtn").removeAttr("disabled");
	 $("#buybtn").button("refresh");
	 });
	 */
	function check() {
		if ($("#did").val() == 0)
			alert(carlist_tv[lsel]);
		else if ($("#money").val() == 0)
			alert(servicelist_tv[lsel]);
		else {

			/* 			if ($("#money").val() == 60) {
			 var htmlobj = $.ajax({
			 url : "../ToolsServlet?action=cheak_sim_buy&did="+ $("#did").val(),
			 async : false
			 });
			 var result = eval(htmlobj.responseText);
			 if (result[0].res == "true") {
			 $("#buybtn").attr("disabled", "true");
			 $("#buybtn").val(wait_tv[lsel]).button("refresh");
			 window.location = "../MainServlet?weid="
			 + GetQueryString("weid") + "&did="
			 + $("#did").val() + "&money=" + $("#money").val();
			 } else {
			 alert(result[0].msc);
			 }
			 } else {
			 $("#buybtn").attr("disabled", "true");
			 $("#buybtn").val(wait_tv[lsel]).button("refresh");
			 window.location = "../MainServlet?weid="
			 + GetQueryString("weid") + "&did=" + $("#did").val()
			 + "&money=" + $("#money").val();
			 } */

			/* 			$
			 .get(
			 "../ToolsServlet?action=getPhoneIccid&phone="
			 + $("#phone").val(),
			 function(data, status) {
			 var objs = eval(data);
			 //alert(objs.length+objs[0].ICCID);
			 if (objs.length > 0) {
			 init(objs);
			 document.getElementById("div_phpne").style.display = "none";
			 document.getElementById("div_buy").style.display = "";
			 }

			 else {
			 alert("该手机未绑定过sim卡");
			 }
			 }); */

		}
	}

	//采用正则表达式获取地址栏参数
	function GetQueryString(name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
		var r = window.location.search.substr(1).match(reg);
		if (r != null)
			return unescape(r[2]);
		return null;
	}

	function getPhoneIccid() {
		$
				.get(
						"../ToolsServlet?action=getPhoneIccid&phone="
								+ $("#phone").val(),
						function(data, status) {
							var objs = eval(data);
							//alert(objs.length+objs[0].ICCID);
							if (objs.length > 0) {
								init(objs);
								document.getElementById("div_phpne").style.display = "none";
								document.getElementById("div_buy").style.display = "";
							}

							else {
								alert("该手机未绑定过sim卡");
							}
						});
	}

	function init(objs) {
		for ( var car in objs) {
			$("#did").append(
					"<option value='"+objs[car].ICCID+"'>" + objs[car].ICCID
							+ ":<br/>" + objs[car].ALIAS + "</option>")
					.trigger("create");
		}
		$("#did").selectmenu("refresh");

	}

	/* 	//请求服务器数据
	 $.get("../carLocationServlet?action=getCarListByWeid&weid="
	 + GetQueryString("weid"), function(data, status) {
	 objs = eval(data);
	 init(objs);
	 });

	 //获取初始化WXJS信息
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
	 + encodeURIComponent(location.href.split('#')[0] + "@time="
	 + (new Date()).getTime()), true);
	 xmlhttp.send(); */
</script>
</html>


