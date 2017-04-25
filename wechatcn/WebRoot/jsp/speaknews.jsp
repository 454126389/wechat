<%@ page language="java" import="java.util.*" import="java.sql.*,com.ruiyi.wechat.model.DeviceType"
	pageEncoding="UTF-8"%>
<%@page import="com.ruiyi.wechat.string.Language"%>
<!DOCTYPE html>
<html>
<head>
<%
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Pragrma", "no-cache");
	response.setDateHeader("Expires", 0);
%>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta charset="UTF-8">

<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.5, user-scalable=no">
<link rel="stylesheet" href="css/jquery.mobile-1.3.2.min.css">

 <script type="text/javascript" src="../jsp/js/head.js"></script>
 <script src="js/utils/language.js" type="text/javascript"></script>

</head>



<body onload="getLockList()">

	<div data-role="popup" id="noline" class="ui-content" data-theme="a">
	</div>

	<div data-role="page" id="pageone">
		<div data-role="content">

			<fieldset data-role="collapsible" data-collapsed="false">
				<legend id='device_list_tv'></legend>
				<p id='devicesel_tv'></p>
				<div id="lock_list"></div>
			</fieldset>
			<label for="fname" id='speaknews_tv'></label> <input
				type="text" name="fname" id="fname"
				placeholder=""> <input id='speek_news_tv'
				type="button"  onclick="speek_news()"
				style="background: blue;"> 
				<!-- <a id="takephoto" type="button"
				href="#pagetwo" onclick="take_photo()">进行拍照</a> -->

		</div>
	</div>

	<div data-role="page" id="pagetwo">
		<div data-role="header">
			<h1 id='carmerasel_tv'></h1>
		</div>

		<div data-role="content">
			<h2 id='camera_list_tv' ></h2>
			<ul id="device_list_2" data-role="listview" data-inset="true">
				<li><a href="#" id='no_find_device_tv'></a></li>
	
			</ul>
			<a id='back_tv' href="#pageone"></a>
		</div>


	</div>

</body>
<script type="text/javascript">


document.getElementById("device_list_tv").innerText = device_list_tv[lsel];
document.getElementById("devicesel_tv").innerText = devicesel_tv[lsel];

$("#speek_news_tv").val(speek_news_tv[lsel]);
$("#back_tv").val(back_tv[lsel]);
document.getElementById("carmerasel_tv").innerText = carmerasel_tv[lsel];
document.getElementById("camera_list_tv").innerText = device_list_tv[lsel];
document.getElementById("no_find_device_tv").innerText = no_find_device_tv[lsel];



		var t;  
		function popup(msg){
		clearTimeout(t);
		document.getElementById("noline").innerHTML=msg;	
			$('#noline').popup();
			$('#noline').popup('open');
		t=setTimeout("timedCount()",2000)  
		}
		
			function timedCount(){
 	  $('#noline').popup("close");
		}  






//查询已经绑定设备
function getLockList() {
	

	//清空
		var devices_list = document.getElementById("lock_list");
	while(devices_list.firstChild!=null)
	{
		devices_list.removeChild(devices_list.firstChild);
	}
	
	var xmlhttp;
	if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp = new XMLHttpRequest();
	} else {// code for IE6, IE5
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
		
			parseXML2List(xmlhttp.responseXML, "bundling");
		}
	};
	
	var ra=Math.random()*10000;
	
 	xmlhttp.open("GET", "../lockServlet?action=getLockList&weid="+'<%=request.getParameter("weid")%>'+"&random="+ra, true);
		xmlhttp.send();
	}

	var items;

	//解析显示
	function parseXML2List(xml, type) {

		 items = xml.getElementsByTagName("item");

		var str = "<fieldset data-role='controlgroup'>";
		
		for ( var i = 0; i < items.length; i++) {
			
		
			if (items[i].childNodes.length > 0) {
				text = items[i].getElementsByTagName("itemno")[0].firstChild.nodeValue;
			}
			
			
		
			if(i==0)
				str += "<input type='radio' name='lock_device'  id='"+i+"' value='"+text+"' checked='checked'/><label name='lock_device_lb' for='"+i+"' >"
					+ text + "</label>";
			else
			str += "<input type='radio' name='lock_device'  id='"+i+"' value='"+text+"'/><label name='lock_device_lb' for='"+i+"' >"
					+ text + "</label>";
					
		
			
		}
		str += "</fieldset>";
		$("#lock_list").append(str).trigger("create");
	
		
		
		
	}


	//新闻播报
	function speek_news() {
		var device = document.getElementsByName("lock_device");
		var device_lb = document.getElementsByName("lock_device_lb");
		var fname = document.getElementById("fname");

		var intvalue = $('input[name="lock_device"]:checked').val();
		var content = "";
	/* 	for ( var i = 0; i < device.length; i++) {
			if (device[i].checked) {
				intvalue += device_lb[i].innerText.trim() + ",";
			}
		} */
		content = fname.value;
		var num=$('input[name="lock_device"]:checked').attr("id");
		
		if (intvalue == "") {
			popup("<%=Language.devicesel%>");
			return;
		} else if (content == "" || content == null) {
			popup("<%=Language.speaknonull%>");
			return;
		} else if(items[num].getElementsByTagName("itemtype")[0].firstChild.nodeValue==3)
		{
		//车机
		
		
		popup("<%=Language.speaksuc%>");
	

	
			$.get("../ToolsServlet?action=speakWords&weid="
					+ GetQueryString("weid") + "&did=" + intvalue+"&words="+content,
					function(data, status) {
			
				
					});
		
		}else
		{
			//電子狗
			var xmlhttp;
			if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
				xmlhttp = new XMLHttpRequest();
			} else {// code for IE6, IE5
				xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
			}

			xmlhttp.open("GET", "../speekServlet?action=speek_news&selectlist="
					+ intvalue + "&message=" + content, true);

			xmlhttp.onreadystatechange = function() {
				if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
					popup("<%=Language.speaksuc%>");
				};
			}
			xmlhttp.send();
		}
		
		
		
		
		
		
		

		

	}
	
	
	
		//拍照
	function take_photo() {
	
	// window.location.href = "#pagetwo";
	
		var device = document.getElementsByName("lock_device");
		var device_lb = document.getElementsByName("lock_device_lb");
		var fname = document.getElementById("fname");

		var intvalue = "";
		var content = "";
		for ( var i = 0; i < device.length; i++) {
			if (device[i].checked) {
				intvalue += device_lb[i].innerText.trim() + ",";
			}
		}
		content = fname.value;

		if (intvalue == "") {
			popup("<%=Language.devicesel%>");

			return;
		} else {
			var xmlhttp;
			if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
				xmlhttp = new XMLHttpRequest();
			} else {// code for IE6, IE5
				xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
			}

			xmlhttp.open("GET", "../speekServlet?action=take_photo&selectlist="
					+ intvalue, true);

			xmlhttp.onreadystatechange = function() {
				if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
					popup("请稍等，设备正在回传图片到微信中...");
				}
				;
			}
			xmlhttp.send();
		}

	}
	
	
		
	function showloading() {

		$.mobile.loadingMessageTextVisible = true;
		$.mobile.showPageLoadingMsg('a', "加载中..");
	}

	function stoploading() {

		$.mobile.hidePageLoadingMsg();
	}

	//采用正则表达式获取地址栏参数
	function GetQueryString(name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
		var r = window.location.search.substr(1).match(reg);
		if (r != null)
			return unescape(r[2]);
		return null;
	}


</script>
</html>
