<%@ page contentType="text/html; charset=utf-8" language="java"
	import="java.util.List,com.ruiyi.wechat.model.Voiture" errorPage=""%>
<%@page import="com.ruiyi.wechat.string.Language"%>
<!DOCTYPE html>

<%
	if (request.getProtocol().compareTo("HTTP/1.0") == 0)
		response.setHeader("Pragma", "no-cache");
	if (request.getProtocol().compareTo("HTTP/1.1") == 0)
		response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
%>

<html>
<head>


<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.5, user-scalable=no">

 <script type="text/javascript" src="../jsp/js/head.js"></script>

<%
	if (request.getProtocol().compareTo("HTTP/1.0") == 0)
		response.setHeader("Pragma", "no-cache");
	if (request.getProtocol().compareTo("HTTP/1.1") == 0)
		response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
%>

</head>
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
	
function init(){
	formlogin.username.focus();
	
		
		var id = "49BAC005-7D5B-4231-8CEA-16939BEACD67";//GUID标识符
		var usr = GetCookie(id);
		if (usr != null) {
			document.formlogin.username.value = usr;
		} else {
			document.formlogin.username.value = "";
		}
		GetPwdAndChk();
}

function GetPwdAndChk() {
		var usr = document.formlogin.username.value;
		var pwd = GetCookie(usr);
		if (pwd != null) {
			document.formlogin.psw.value= pwd;
		} else {
			document.formlogin.psw.value= "";
		}
	}

	function getCookieVal(offset) {
		var endstr = document.cookie.indexOf(";", offset);
		if (endstr == -1) endstr = document.cookie.length;
		return unescape(document.cookie.substring(offset, endstr));
	}
	function GetCookie(name) {
		var arg = name + "=";
		var alen = arg.length;
		var clen = document.cookie.length;
		var i = 0;
		while (i < clen) {
			var j = i + alen;
			//alert(j);
			if (document.cookie.substring(i, j) == arg) return getCookieVal(j);
			i = document.cookie.indexOf(" ", i) + 1;
			if (i == 0) break;
		}
		return null;
	}
	


function backbag(arg)
{
	if (arg.value=="") 
		{
		arg.style.backgroundColor="#CCFF99"
		arg.focus();
		}
	else
		arg.style.backgroundColor="";
}
	
//验证由字母、数字组成的字符
function yzusername(){
    var w=document.form1.username.value;
    var test=/^[A-Za-z0-9]*$/;
    if(test.exec(w)){
        return true;
    }else{
    return false;
    }
}
function yzemail(){

    var w=document.form1.email.value;
    var test=/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
    if(test.exec(w)){
        return true;
    }else{
    return false;
    }
}

function yztel(){
     var mobile=document.form1.tel.value;
    var reg = /^(((13[0-9]{1})|15[0-9]|14[0-9]|18[0-9])+\d{8})$/;; 
 
     var my=false;
     if (reg.test(mobile))
     return true;
     else 
     return false;
}

function yzusername2(){
		var isusername=true;
 		var xmlhttp;
		if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} else {// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
		if(xmlhttp.responseText=="isFalse")
		{
		
		isusername=false;
		}
				}
		};
	 	xmlhttp.open("GET", "userRegisterServlet?action=checkusername&username="+document.form1.username.value,false);
		xmlhttp.send();
		return isusername;
}

function yzisuser(){
		var isuser=true;
 		var xmlhttp;
		if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} else {// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
		if(xmlhttp.responseText=="isFalse")
		{
		
		isuser=false;
		}
				}
		};
	 	xmlhttp.open("GET", "userRegisterServlet?action=isuser&username="+document.formlogin.username.value+"&psw="+document.formlogin.psw.value,false);
		xmlhttp.send();
		return isuser;
}


function yzusername3(){
	if(document.form1.username.value.length<6||document.form1.username.value.length>30)
		return false;
	else
		return true	;
}

function yzpsw(){
	if(document.form1.psw.value.length<6||document.form1.psw.value.length>30)
		return false;
	else
		return true	;
}


function yzid(){
		var isid=true;
 		var xmlhttp;
		if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} else {// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
		
		if(xmlhttp.responseText=="isFalse")
		{
		isid=false;
		}
				}
		};
	 	xmlhttp.open("GET", "userRegisterServlet?action=checkid&id="+document.form1.id.value,false);
		xmlhttp.send();
		return isid;
}

function yzidisused(){
		var isid=true;
 		var xmlhttp;
		if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} else {// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
		
		if(xmlhttp.responseText=="isFalse")
		{
		isid=false;
		}
				}
		};
	 	xmlhttp.open("GET", "userRegisterServlet?action=checkidisused&id="+document.form1.id.value,false);
		xmlhttp.send();
		return isid;
}


        function Trim(str,is_global)
        {
            var result;
            result = str.replace(/(^\s+)|(\s+$)/g,"");
            if(is_global.toLowerCase()=="g")
            {
                result = result.replace(/\s/g,"");
             }
            return result;
}

function yzkey(){
	  var iskey=true;
 		var xmlhttp;
		if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} else {// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
		
		if(xmlhttp.responseText=="isFalse")
		{
	iskey=false;
		}
				}
		};
	 	xmlhttp.open("GET", "userRegisterServlet?action=checkkey&id="+document.form1.id.value+"&key="+Trim(document.form1.key.value,"g"),false);
		xmlhttp.send();
		return iskey;
}
  
          function checkInput()
        {
            var txtName=document.form1.carno.value;
            
            //创建正则表达式
            var re=/^[\u4e00-\u9fa5]{1}[A-Z]{1}[A-Z_0-9]{5}$/; //车牌号
            //var re=/^[\u4e00-\u9fa5]{1,10}$/; //只输入汉字的正则
               
            if(txtName.search(re)==-1)
            {
                //lblMsg.innerText = "请输入汉字，字符不能超过十个。";
                return false;
            }
            else
            {
                return true;
            }
        }
        
        
      function SetCookie(name, value, expires) {
		var argv = SetCookie.arguments;
		//本例中length = 3
		var argc = SetCookie.arguments.length;
		var expires = (argc > 2) ? argv[2] : null;
		var path = (argc > 3) ? argv[3] : null;
		var domain = (argc > 4) ? argv[4] : null;
		var secure = (argc > 5) ? argv[5] : false;
		document.cookie = name + "=" + escape(value) + ((expires == null) ? "" : ("; expires=" + expires.toGMTString())) + ((path == null) ? "" : ("; path=" + path)) + ((domain == null) ? "" : ("; domain=" + domain)) + ((secure == true) ? "; secure" : "");
	}
        
  
  
  function SetLastUser(usr) {
		var id = "49BAC005-7D5B-4231-8CEA-16939BEACD67";
		var expdate = new Date();
		//当前时间加上1周的时间
		expdate.setTime(expdate.getTime() + 7 * (24 * 60 * 60 * 1000));
		SetCookie(id, usr, expdate);
	}
  
  function checklogin(){
  if(formlogin.username.value==""){
        popup("<%=Language.usernamenonull%>");
       formlogin.username.focus();
        return false;
    }else if(formlogin.psw.value==""){
        popup("<%=Language.pswnonull%>");
        formlogin.psw.focus();
        return false;
    } else if(!yzisuser()){
        popup("<%=Language.pswerror%>");
        formlogin.psw.focus();
        return false;
    }else{
   		 var usr = document.formlogin.username.value;
		//将最后一个用户信息写入到Cookie
		SetLastUser(usr);
			//取密码值
		var pwd = document.formlogin.psw.value;
			
		var expdate = new Date();
		expdate.setTime(expdate.getTime() + 7 * (24 * 60 * 60 * 1000));
			//将用户名和密码写入到Cookie
			SetCookie(usr, pwd, expdate);
			
		location.href='userRegisterServlet?action=login&username='+formlogin.username.value+'&weid='+formlogin.weid.value;
    
        return;
    }
  }
  
function check2(){

	if(form1.username.value==""){
        popup("<%=Language.usernamenonull%>");
        form1.username.focus();
        return false;
    }else if(!yzusername()){
        popup("<%=Language.usernamelimit%>");
        form1.username.focus();
        return false;
    }else if(yzusername2()){
        popup("<%=Language.usernamealreadyused%>");
        form1.username.focus();
        return false;
    }else if(!yzusername3()){
        popup("<%=Language.usernamesize%>");
        form1.username.focus();
        return false;
    }else if(form1.psw.value==""){
        popup("<%=Language.pswnonull%>");
        form1.psw.focus();
        return false;
    }else if(!yzpsw()){
        popup("<%=Language.pswsize%>");
        form1.psw.focus();
        return false;
    }else if(form1.repsw.value!=form1.psw.value){
        popup("<%=Language.pswnothesame%>");
        form1.repsw.focus();
        return false;
    }else if(form1.email.value==""){
        popup("<%=Language.emailnonull%>");
        form1.email.focus();
        return false;
    }else if(!yzemail()){
        popup("<%=Language.emailerror%>");
        form1.email.focus();
        return false;
    }else if(form1.tel.value==""){
        popup("<%=Language.telnonull%>");
        form1.tel.focus();
        return false;
    }else if(form1.id.value==""){
        popup("<%=Language.idnonull%>");
        form1.id.focus();
        return false;
    }else if(!yzid()){
     popup("<%=Language.idnoexist%>");
        form1.id.focus();
        return false;
    }else if(yzidisused()){
     popup("<%=Language.idalready%>");
        form1.id.focus();
        return false;
    }else if(form1.key.value==""){
        popup("<%=Language.keynonull%>");
        form1.key.focus();
        return false;
    }else if(!yzkey()){
        popup("<%=Language.keynouserable%>");
        form1.key.focus();
        return false;
    }
    else if(form1.carno.value==""){
        popup("<%=Language.carnamenonull%>");
        form1.carno.focus();
        return false;
    }else if(document.getElementById("cartype").value=="11"){
        popup("<%=Language.cartypenosel%>");
        return false;
    }
    else{
        document.form1.action='userRegisterServlet?action=register';
        document.form1.method='post';
      
        return;
    }
}
  
</script>
<body onload="init();">

	<div data-role="popup" id="noline" class="ui-content" data-theme="a">
	</div>
	<div data-role="page" id="pageone" data-theme="b">
		<div data-role="header" data-theme="a">
			<div data-role="navbar">
				<ul>
					<li><a href="#" class="ui-btn-active ui-state-persist"
						target="_parent"><%=Language.userlogin%></a></li>
					<li><a href="#pagetwo" target="_parent"><%=Language.userregister%></a>
					</li>
				</ul>
			</div>
		</div>

		<div data-role="content">
			<form name="formlogin" target="_top"
				class="ui-body ui-body-a ui-corner-all">
				<center>
					<div>
						<%=Language.userlogin%>
					</div>
				</center>
				<div data-role="fieldcontain" data-inline="true">
					<label for="search"> <%=Language.lockonename%>
					</label> <input type="text" name="username" id="username"
						placeholder="username" value="">
				</div>
				<div data-role="fieldcontain" data-inline="true"
					style="display: none;">
					<label for="weid"> 微信号： </label> <input type="text" name="weid"
						id="weid" value="<%=request.getParameter("weid")%>"
						placeholder="weid">
				</div>
				<div data-role="fieldcontain">
					<label for="search"> <%=Language.psw%>：
					</label> <input type="text" name="psw" id="psw" placeholder="******"
						type="password" value="">
				</div>



				<input type="button" data-theme="b" name="submit" id="submit"
					value="<%=Language.confirm%>" onclick="checklogin();"
					data-corners="false" />


			</form>
		</div>
	</div>


	<div data-role="page" id="pagetwo" data-theme="b">
		<div data-role="header" data-theme="a">
			<div data-role="navbar">
				<ul>
					<li><a href="#pageone" target="_parent"><%=Language.userlogin%></a>
					</li>
					<li><a href="#" class="ui-btn-active ui-state-persist"
						target="_parent"><%=Language.userregister%></a></li>
				</ul>
			</div>
		</div>

		<div data-role="content">
			<form name="form1" target="_top">
				<center>
					<div>
						<%=Language.userinfo%>
					</div>
				</center>
				<div data-role="fieldcontain" data-inline="true">
					<label for="search"> <%=Language.lockonename%>
					</label> <input type="text" name="username" id="username"
						placeholder="username">
				</div>
				<div data-role="fieldcontain" data-inline="true"
					style="display: none;">
					<label for="weid"> 微信号： </label> <input type="text" name="weid"
						id="weid" value="<%=request.getParameter("weid")%>"
						placeholder="weid">
				</div>
				<div data-role="fieldcontain">
					<label for="search"> <%=Language.psw%>：
					</label> <input type="text" name="psw" id="psw" placeholder="******"
						type="password">
				</div>
				<div data-role="fieldcontain">
					<label for="search"> <%=Language.repsw%>：
					</label> <input type="text" name="repsw" id="repsw" placeholder="******"
						type="password">
				</div>
				<div data-role="fieldcontain">
					<label for="search"> <%=Language.email%>：
					</label> <input type="text" name="email" id="email"
						placeholder="user@163.com">
				</div>
				<div data-role="fieldcontain">
					<label for="search"> <%=Language.teltitle%>
					</label> <input type="text" name="tel" id="tel" placeholder="15000000000">
				</div>
				<center>
					<div>
						<%=Language.devicedetial%>
					</div>
				</center>
				<div data-role="fieldcontain">
					<label for="search"> ID： </label> <input type="text" name="id"
						id="id" placeholder="10000000000000001">
				</div>
				<div data-role="fieldcontain">
					<label for="search"> KEY： </label> <input type="text" name="key"
						id="key" placeholder="WERTYUIOPL"
						onkeyup="this.value=this.value.toUpperCase()">
				</div>
				<div data-role="fieldcontain">
					<label for="search"> <%=Language.carname%>：
					</label> <input type="text" name="carno" id="carno" placeholder="京A00001">
				</div>
				<div data-role="fieldcontain">
					<label for="cartype"> <%=Language.cartype%>：
					</label> <select name="cartype" id="cartype" data-corners="false">
						<%
							List<Voiture> carPosiList = (List<Voiture>) request
									.getAttribute("cartypelist");
							for (int i = 0; i < carPosiList.size(); i++) {
						%>
						<option
							value="<%=carPosiList.get(carPosiList.size() - 1 - i).getId()%>">
							<%=carPosiList.get(carPosiList.size() - 1 - i).getType()%></option>
						<%
							}
						%>
					</select>
				</div>
				<input type="submit" value="<%=Language.confirm%>"
					data-corners="false" onclick="return check2();">
			</form>
		</div>
	</div>

</body>
</html>


