
<%@ page language="java" import="java.util.*,com.ruiyi.wechat.string.Language" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
     if(request.getProtocol().compareTo("HTTP/1.0")==0)
    	 response.setHeader("Pragma","no-cache");
     if(request.getProtocol().compareTo("HTTP/1.1")==0)
    	 response.setHeader("Cache-Control","no-cache");
     response.setDateHeader("Expires",0);

%>

<html>

<head>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">

<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.5, user-scalable=no">
 <script type="text/javascript" src="../jsp/js/head.js"></script>



</head>



<script type="text/javascript">


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
		

	
	

	
	
	function getList(){
		
		var ra=Math.random()*10000;
		getUnLockList(ra);
		 getLockList(ra); 
	}
	
	//查询未绑定设备
	function getUnLockList(ra) {
		
	
	var devices_list2 = document.getElementById("unlock_list");
	
		while(devices_list2.firstChild!=null)
		{
			devices_list2.removeChild(devices_list2.firstChild);
		} 

		var phone = document.getElementById('search');
		
		var xmlhttp;
		if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} else {// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

				parseXML2List(xmlhttp.responseXML, "unbundling");
			}
		};
		xmlhttp.open("GET", "../lockServlet?action=getUnLockList&phone=<%=request.getParameter("search")%>"+"&random="+ra, true);
		xmlhttp.send(); 
	}

	//查询已经绑定设备
	function getLockList(ra) {
		
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
	 	xmlhttp.open("GET", "../lockServlet?action=getLockList&weid=<%=request.getParameter("weid")%>"+"&random="+ra, true);
		xmlhttp.send();
	}

	
	
	
	//解析显示
	function parseXML2List(xml, type) {

		var items = xml.getElementsByTagName("item");

			 var str="<fieldset data-role='controlgroup'>";
			 
			for ( var i = 0; i < items.length; i++) {
			var text = "";
			if (items[i].childNodes.length > 0) {
			
			
				text = items[i].getElementsByTagName("itemno")[0].firstChild.nodeValue+" ";
				try{
						text+=items[i].getElementsByTagName("itemname")[0].firstChild.nodeValue;
				}catch(err){
						text+="<%=Language.defaultcarname%>";
				}
		
				
			}
			if (type == 'bundling')
				/* changecheckbox(i, text); */
				str+="<input type='checkbox' name='lock_device'  id='"+i+"'/><label name='lock_device_lb' for='"+i+"' >"+text+"</label>";
			else 
				str+="<input type='checkbox' name='unlock_device'  id='"+i+"'/><label name='unlock_device_lb' for='"+i+"' >"+text+"</label>";
		}
			str+="</fieldset>";
			
			if (type == 'bundling')
				{
				$("#lock_list").append(str).trigger("create"); 
				/*  $("#lock_list").checkboxradio("refresh"); */
				}
			else
				{
				$("#unlock_list").append(str).trigger("create"); 
				 /* $("#unlock_list").checkboxradio("refresh"); */
				}
	}
	
	//绑定设备
	function lock_device() {
		
		var ra=Math.random()*10000;
		var device=document.getElementsByName("unlock_device");
		var device_lb=document.getElementsByName("unlock_device_lb");
		
		var intvalue = "";
		for ( var i = 0; i < device.length; i++) {
			 if (device[i].checked) {
				intvalue += device_lb[i].innerText.trim().split(" ")[0] + ",";
			}
		}
		
		if(intvalue=="")
		{
				popup("<%=Language.noselect%>");
			return;
			}
		
		
		var xmlhttp;
		if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} else {// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}

		xmlhttp.open("GET", "../lockServlet?action=setLoctList&selectlist="
				+ intvalue+"&weid="+'<%=request.getParameter("weid")%>'+"&random="+ra, true);

		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				
				popup("<%=Language.locksuc%>");
				getList(ra);
					
			}
		}

		xmlhttp.send();
	}
	
	
	
	//解绑设备
	function relace_device() {
		
		
		var ra=Math.random()*10000;
		
		var device=document.getElementsByName("lock_device");
		var device_lb=document.getElementsByName("lock_device_lb");
		
		var intvalue = "";
		for ( var i = 0; i < device.length; i++) {
			 if (device[i].checked) {
				intvalue += device_lb[i].innerText.trim().split(" ")[0] + ",";
			}
		}
		
		
		if(intvalue=="")
			{
				popup("<%=Language.noselect%>");
			return;
			}
		
		
		var xmlhttp;
		if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} else {// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}

		xmlhttp.open("GET", "../lockServlet?action=setunLockList&selectlist="
				+ intvalue+"&weid="+'<%=request.getParameter("weid")%>'+"&random="+ra,
				true);

		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				
				popup("<%=Language.unlocksuc%>");
				getList(ra);
			}
		}

		xmlhttp.send();
	}
</script>


<body onload="getList()">
	<div data-role="popup" id="noline" class="ui-content" data-theme="a">
		</div>

	<div data-role="page" id="pageone">
		<div data-role="content">
			<p><%=Language.carlisttip1%></p>
			<form>
				<fieldset  data-collapsed="false">
					<p><%=Language.nolockcarlist%></p>
					<div id="unlock_list"></div>
					<input type="button" value="<%=Language.lockbtn%>" onclick="lock_device()" data-inline="true">
				</fieldset>
			</form>
			<form>
				<fieldset  data-collapsed="false" >
					<p><%=Language.lockcarlist%></p>
					<div id="lock_list"></div>
					<input type="button" value="<%=Language.unlockbtn%>" onclick="relace_device()" data-inline="true">
				</fieldset>
			</form>
		</div>
	</div>
	

</body>
</html>