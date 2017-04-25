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

<script src="js/utils/language.js" type="text/javascript"></script>
</head>
<body>



	<div data-role="page">

		<div data-role="header">
			<h1 id='buy_service_tip_tv'></h1>
		</div>

		<div data-role="content">

			<div data-role="fieldcontain">
				<label for="did">ID：</label> <input type="text" name="did" id="did">
			</div>

			<fieldset data-role="fieldcontain">
				<label for="money" id='service_sel'></label> <select name="money"
					id="money">

					<option value="0" id='service_sel_option'></option>

					<optgroup label="流量服务">
						<option value="60">60元/1年</option>
					</optgroup>

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
		<input type="submit" id="buybtn" value="提交" onclick="check()">


	</div>

	</div>

</body>


<script language="javascript">
 	document.getElementById("buy_service_tip_tv").innerText = buy_service_tip_tv[lsel];
	document.getElementById("service_sel").innerText = servicelist_tv[lsel];
	document.getElementById("service_sel_option").innerText = servicelist_tv[lsel]; 




 	function checkid(id) {
		var htmlobj = $.ajax({
			url : "../ToolsServlet?action=checkid&did=" + $("#did").val(),
			async : false
		});
		var result = eval(htmlobj.responseText);
		alert(result[0].res);
		if (result[0].res == "true")
			return true;
		else
			return false;
	} 

	function check() {
		if ($("#did").val() == 0)
			alert(carlist_tv[lsel]);
		else if ($("#money").val() == 0)
			alert(servicelist_tv[lsel]);
		else if (!checkid()) {
		alert("ID不存在，或者未注册");
		} else {
			if ($("#money").val() == 60) {
				var htmlobj = $.ajax({
					url : "../ToolsServlet?action=checkbuy&did="
							+ $("#did").val(),
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
						+ "&money=" + $("#money").val()+"&type=test";
			}

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

	function init(objs) {
		for ( var car in objs) {
			$("#did").append(
					"<option value='"+objs[car].id+"'>" + objs[car].id
							+ ":<br/>" + objs[car].alias + "</option>")
					.trigger("create");
		}
	}

	//请求服务器数据
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
		

		}
	};
	xmlhttp.open("GET", "../ToolsServlet?action=getJsid&url="
			+ encodeURIComponent(location.href.split('#')[0] + "@time="
					+ (new Date()).getTime()), true);
	xmlhttp.send();
</script>
</html>


