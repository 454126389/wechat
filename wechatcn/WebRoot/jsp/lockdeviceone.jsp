
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

	<title><%=Language.lockonetitle %></title> 
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

	function lockone() {
	
	
		if(document.getElementById("usernameone").value=="")
			{
			popup("用户名不能为空");
			return;
			}
		var xmlhttp;
		if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} else {// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			
			
				if (xmlhttp.responseText == "true") {
						popup("<%=Language.lockonesuc %>");
					document.getElementById("tipone").style.visibility="visible";
				}else
					popup("绑定失败，请检查用户名");
			
				
			}else
				popup("绑定失败，请检查用户名");
			
		};
		
		
		xmlhttp.open("GET", "../userRegisterServlet?action=lockone&username="+document.getElementById("usernameone").value+"&weid=<%=request.getParameter("weid")%>", false);
		xmlhttp.send();
	}
	
		function unlockone() {

	var xmlhttp;
		if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} else {// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				popup("<%=Language.unlockonesuc %>");
				document.getElementById("tiptwo").style.visibility="visible";
			}
			
			
			
		};
		
		xmlhttp.open("GET", "../userRegisterServlet?action=unlockone&username="+document.getElementById("usernametwo").value+"&weid=<%=request.getParameter("weid")%>", false);
		xmlhttp.send();
		
	}
	


	
	
</script>


<body>

	<div data-role="popup" id="noline" class="ui-content" data-theme="a">
		</div>

	<div data-role="page" id="pageone" data-theme="b">
			<div data-role="content">
					<center><strong><%=Language.lockonetip %></strong></center>
					</p>
					<legend><%=Language.lockonename %></legend>
						<input type="text"  id="usernameone" placeholder="username"  >
						
						 <input type="button" value="<%=Language.confirm %>" onclick="lockone();" data-corners="false"> 
						
					<label id="tipone" style="visibility: hidden ;color: red; "><%=Language.lockonesuctip %></label>
					<label id="tiponefail" style="visibility: hidden ;color: red; ">绑定失败，请确认用户名是否正确</label>
					</form>
		</div>
	</div>
	<div data-role="page" id="pagetwo" data-theme="b">
			<div data-role="content">
					<center><strong><%=Language.unlockonetip %></strong></center>
					</p>
					<legend><%=Language.lockonename %></legend>
						<input type="text"  id="usernametwo" placeholder="username"  >
						
						 <input type="button" value="<%=Language.confirm %>" onclick="unlockone();" data-corners="false"> 
				
					<label id="tiptwo" style="visibility: hidden; color: red;"><%=Language.lockonesuctip %></label>
					</form>
		</div>
	</div>
</body>
</html>