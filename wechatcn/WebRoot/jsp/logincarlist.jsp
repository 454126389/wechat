<%@ page contentType="text/html; charset=utf-8" language="java"
	import="java.util.List,com.ruiyi.wechat.model.Car,com.ruiyi.wechat.model.Voiture,com.ruiyi.wechat.string.Language"
	errorPage=""%>
	
	<%
    	List<Car> carlist=(List<Car>) request.getAttribute("carlist");
    %>	
	
<!DOCTYPE html>

<head>
	<meta charset="UTF-8">
	<title><%=Language.usercentertitle%></title> 
	<meta name="viewport" content="width=device-width, initial-scale=1,user-scalable=no"> 

 <script type="text/javascript" src="../jsp/js/head.js"></script>
<link rel="stylesheet"
	href="jsp/css/custom.css">


<script>


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

function yzid() {
		var isid = true;
		var xmlhttp;
		if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} else {// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

				if (xmlhttp.responseText == "isFalse") {
					isid = false;
				}
			}
		};
		xmlhttp.open("GET", "userRegisterServlet?action=checkid&id="
				+ document.form1.id.value, false);
		xmlhttp.send();
		return isid;
	}

		function yzidisused() {
		var isid = true;
		var xmlhttp;
		if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} else {// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

				if (xmlhttp.responseText == "isFalse") {
					isid = false;
				}
			}
		};
		xmlhttp.open("GET", "userRegisterServlet?action=checkidisused&id="
				+ document.form1.id.value, false);
		xmlhttp.send();
		return isid;
	}
	
		function yzkey() {
		var iskey = true;
		var xmlhttp;
		if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} else {// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

				if (xmlhttp.responseText == "isFalse") {
					iskey = false;
				}
			}
		};
		xmlhttp.open("GET", "userRegisterServlet?action=checkkey&id="
				+ document.form1.id.value + "&key=" + document.form1.key.value,
				false);
		xmlhttp.send();
		return iskey;
	}

function checkadd() {
			
		if (form1.id.value == "") {
			popup("<%=Language.idnonull%>");
			form1.id.focus();
			return false;
		} else if (!yzid()) {
			popup("<%=Language.idnoexist%>");
			form1.id.focus();
			return false;
		} else if (yzidisused()) {
			popup("<%=Language.idalready%>");
			form1.id.focus();
			return false;
		} else if (form1.key.value == "") {
			popup("<%=Language.keynonull%>");
			form1.key.focus();
			return false;
		} else if (!yzkey()) {
			popup("<%=Language.keynouserable%>");
			form1.key.focus();
			return false;
		} else if (form1.carno.value == "") {
			popup("<%=Language.carnamenonull%>");
			form1.carno.focus();
			return false;
		}  else if (document.getElementById("cartype").value == "11") {
			popup("<%=Language.cartypenosel%>");
			return false;
		} else {
			document.form1.action = 'userRegisterServlet?action=add_device';
			document.form1.method = 'post';
			
		
			 
			return;
		}
	}
	
	
	
	
function lock() {

		var xmlhttp;
		if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} else {// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				popup("<%=Language.locksuc%>");
				 window.location.href="userRegisterServlet?action=getUserCarListView&username=<%=request.getParameter("username")%>&weid=<%=request.getParameter("weid")%>";
			}
		};
		
		
		xmlhttp.open("GET", "userRegisterServlet?action=lock&pid="
				+ document.getElementById("p3_id").innerHTML+"&weid=<%=request.getParameter("weid")%>", false);
		xmlhttp.send();
	}
	
	function unlock() {

	
		var xmlhttp;
		if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} else {// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				popup("<%=Language.unlocksuc%>");
				 window.location.href="userRegisterServlet?action=getUserCarListView&username=<%=request.getParameter("username")%>&weid=<%=request.getParameter("weid")%>";
			}
		};
		
		
		xmlhttp.open("GET", "userRegisterServlet?action=unlock&pid="
				+ document.getElementById("p3_id").innerHTML, false);
		xmlhttp.send();
	}
	
 function	cardetail(name,no,type)
 {
  
  window.location.href="#pagethree";
  
  document.getElementById("p3_name").innerHTML=name;
  document.getElementById("p3_id").innerHTML=no;
  document.getElementById("p3_type").innerHTML=type;
    document.getElementById("p3_change").style.display="";  

  document.getElementById("p3_unchange").style.display="";  
 
  
 }
	

	function lockone() {

		var xmlhttp;
		if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} else {// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				popup("<%=Language.lockonesucshort%>");
				 window.location.href="userRegisterServlet?action=getUserCarListView&username=<%=request.getParameter("username")%>&weid=<%=request.getParameter("weid")%>";
			}
		};
		
		
		xmlhttp.open("GET", "userRegisterServlet?action=lockone&username=<%=request.getParameter("username")%>&weid=<%=request.getParameter("weid")%>", false);
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
				popup("<%=Language.unlockonesucshort%>");
				 window.location.href="userRegisterServlet?action=getUserCarListView&username=<%=request.getParameter("username")%>&weid=<%=request.getParameter("weid")%>";
			}
		};
		
		xmlhttp.open("GET", "userRegisterServlet?action=unlockone&username=<%=request.getParameter("username")%>", false);
		xmlhttp.send();
	}
	


</script>


	</head>

	<body> 
		<div data-role="popup" id="noline" class="ui-content" data-theme="a">
		</div>
	
<div data-role="page" id="pageone" >

			<div data-role="content">
			
				<a href="userRegisterServlet?action=login&username=<%=request.getParameter("username")%>&weid=<%=request.getParameter("weid")%>" data-role="button" data-inline="true"
					data-mini="true" data-corners="false" >	<%=Language.backbtn%></a>
			

				<a href="#pagetwo" data-role="button" data-inline="true"
					data-mini="true"  data-corners="false"><%=Language.deviceadd%></a>
					
						<a onclick="lockone();" data-role="button" data-inline="true"
					data-mini="true"  data-corners="false"><%=Language.lockonetip%></a>	
					
								<a onclick="unlockone();" data-role="button" data-inline="true"
					data-mini="true"  data-corners="false"><%=Language.unlockonetip%></a>	
					
					
				<p />
		<ul data-role="listview" data-inset="true" id="parent" >


					<%
					for(int i=0;i<carlist.size();i++)
					{
					%>
						<li name="li" onclick="cardetail('<%=carlist.get(i).getName()%>','<%=carlist.get(i).getNo()%>','<%=carlist.get(i).getCarType()%>');" value="<%=carlist.get(i).getNo()%>"><a  data-transition="slide" > <img src="jsp/images/caricon.jpg"/ onclick="cardetail();"> <h2> <%=carlist.get(i).getName()%>     </h2> <%=carlist.get(i).getNo()%> </p></a></li>
					<%}%>

					</ul>	
			</div>


		</div>

	<div data-role="page" id="pagetwo">
			<div data-role="header">
		
			</div>

			<div data-role="content">
					<a href="#pageone" data-role="button" data-inline="true"
					data-mini="true"  data-corners="false">	<%=Language.backbtn%></a>
				<form name="form1">
					<center>
						<div>
								<%=Language.devicedetial%>
						</div>
					</center>
					<div data-role="fieldcontain" data-inline="true"
						style="display: none;">
						<label for="weid">
							微信号：
						</label>
						<input type="text" name="weid" id="weid"
							value="<%=request.getParameter("weid")%>" placeholder="weid">
					</div>


					<div data-role="fieldcontain" data-inline="true"
						style="display: none;">
						<label for="search">
								<%=Language.teltitle%>
						</label>
						<input type="text" name="tel" id="tel" placeholder="15000000000">
					</div>


					<div data-role="fieldcontain" data-inline="true"
						style="display: none;">
						<label for="search">
							<%=Language.lockonename%>
						</label>
						<input type="text" name="username" id="username"
							placeholder="username"
							value="<%=request.getParameter("username") %>">
					</div>



					<div data-role="fieldcontain">

						<label for="search">
							ID：
						</label>
						<input type="text" name="id" id="id"
							placeholder="10000000000000001">
					</div>
					<div data-role="fieldcontain">
						<label for="search">
							KEY：
						</label>
						<input type="text" name="key" id="key" placeholder="WERTYUIOPL"
							onkeyup=
	this.value = this.value.toUpperCase();
>
					</div>
					<div data-role="fieldcontain">
						<label for="search">
							<%=Language.carname%>
						</label>
						<input type="text" name="carno" id="carno" placeholder="京A00001"
							onkeyup=
	this.value = this.value.toUpperCase();
>
					</div>

					<div data-role="fieldcontain">
						<label for="cartype">
								<%=Language.cartype%>
						</label>
						<select name="cartype" id="cartype" data-corners="false">
							<%
								List<Voiture> carPosiList=(List<Voiture>) request.getAttribute("cartypelist");
			for(int i=0;i<carPosiList.size();i++)
			{
			%>
							<option
								value="<%= carPosiList.get(carPosiList.size()-1-i).getId() %>">
								<%=carPosiList.get(carPosiList.size()-1-i).getType()%></option>
							<%}%>
						</select>
					</div>


					<input type="submit"  value="<%=Language.confirm%>" data-corners="false"
						onclick="return checkadd();"/>


				</form>
			</div>

		</div>

<div id="pagethree" data-role="page"  >
	
	
	
	<center>
	
	<div data-role="content">
	
		<a href="#pageone" data-role="button" data-inline="true"
					data-mini="true" data-corners="false" >	<%=Language.backbtn%></a>
	
		<a href="userRegisterServlet?action=login&username=<%=request.getParameter("username")%>&weid=<%=request.getParameter("weid")%>" data-role="button" data-inline="true" data-mini="true" data-corners="false" ><%=Language.backindexbtn%></a>
					
						<hr/>
	<div  id="restau_infos">	
		<div>
				<p><img src="jsp/images/caricon_2.jpg"  alt="jeannette photo"/></p>
			<hr/>
		<h1> <%=Language.carname%>:<label id="p3_name">name</label></h1>
		<p><strong>ID:<label id="p3_id">id</label> </strong></p>
		<p><strong><%=Language.cartype%>:<label id="p3_type">type</label> </strong></p>
			<a  id="p3_change" onclick="lock();" data-role="button" style="display: none"	data-mini="true" >绑定设备</a>
			<a  id="p3_unchange" onclick="unlock();" data-role="button" style="display: none" 	data-mini="true" >解绑设备</a>
		</div>	
	</div>
	<hr/>

	</div>

</center>
</div>
	
	
	</body>
</html>



