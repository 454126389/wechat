<%@ page contentType="text/html; charset=utf-8" import="com.ruiyi.wechat.model.User,com.ruiyi.wechat.model.Car,com.ruiyi.wechat.model.Car,java.util.List,com.ruiyi.wechat.model.Voiture,com.ruiyi.wechat.string.Language" language="java"%>


	<%User user=(User)request.getAttribute("user");%>
	
	<% 
	List<Car> carList=(List<Car>) request.getAttribute("carlist");
%>
	
<!DOCTYPE html>

	<head>
	<meta charset="UTF-8">
	<title><%=Language.usercentertitle %></title> 
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


function changeUserInfo(){
  $("#my_tel")[0].innerText= "<%=Language.teltitle %>"+$("#tel_c")[0].value;
  $("#my_qq")[0].innerText="QQ:"+ $("#qq_c")[0].value;
  $("#my_email")[0].innerText= "<%=Language.email %>："+$("#email_c")[0].value;
  window.location.href="#pageone";  
  
  
  
  	var xmlhttp;
		if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} else {// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

				if (xmlhttp.responseText == "isFalse") {
					popup("<%=Language.changeUserInfofail %>");
				}
			}
		};
		
		xmlhttp.open("GET", "userRegisterServlet?action=changeUserInfo&tel="
				+ $("#tel_c")[0].value + "&qq=" + $("#qq_c")[0].value+ "&email=" + $("#email_c")[0].value+ "&id=" + $("#my_id")[0].innerText,
				false);
		xmlhttp.send();
}


  function unlogin(){
  	var usr = '<%=user.getUsername() %>'
  	var pwd = '';
  	SetLastUser(usr);
  	var expdate = new Date();
	expdate.setTime(0);
	SetCookie(usr, pwd, expdate);
	location.href='userRegisterServlet?action=getcartypelist&weid=<%=request.getAttribute("weid")%>';
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
		expdate.setTime(0);
		SetCookie(id, usr, expdate);
	}
	
	
	
	function getval(){
	
	var pt=$('input:radio[name="pt"]:checked').val();
 	var ll=$('input:radio[name="ll"]:checked').val();
 	var dx=$('input:radio[name="dx"]:checked').val();
 	
 	
 	var no=$('#did option:selected').val();
 	
	alert(no);
 	
	 if(pt==null)
	 pt="0";
	  if(ll==null)
	 ll="0";
	  if(dx==null)
	 dx="0";
	 
	 
	var url="TotalServlet?action=buyservers&weid=<%=request.getAttribute("weid")%>&pt="+pt+"&ll="+ll+"&dx="+dx+"&no="+no;
	
	alert(url);
	
	 var xmlhttp;
		if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} else {// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		} 
		 		xmlhttp.onreadystatechange = function() {
		 if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			 
			  resultDiv.innerHTML="成功！"
		 }else
		 resultDiv.innerHTML="登錄中.."
		 }; 
	 	xmlhttp.open("GET",url, true);
		xmlhttp.send(); 
	
	
	
	}
	
	

</script>


	</head>

	<body> 
			<div data-role="popup" id="noline" class="ui-content" data-theme="a">
		</div>

<div id="pageone" data-role="page" data-add-back-btn="true" data-theme="b" >
	
	
	
	
	
	<div data-role="content">
	

	<div class="ui-grid-a" id="restau_infos">	
		<div class="ui-block-a">
		<h1> <%=Language.lockonename %></h1>
		<p><strong>  <%=user.getUsername() %></strong></p>
		<p> <%=Language.userinfo %>： </p>
			<ul> 
				<li id="my_tel"><%=Language.teltitle %><%=user.getPhone() %></li>
				<li id="my_qq" > QQ:<%=user.getQQ() %></li>
				<li id="my_email"><%=Language.email %>：<%=user.getEmail() %></li>
					<li id="my_balance" >賬戶餘額：<%=user.getBalance() %></li>
				<li id="my_id" style="visibility: hidden"><%=user.getId() %></li>
			
		
			</ul>			
		</div>		
		<div class="ui-block-b">
			    <a  onclick="unlogin();" ><%=Language.unlogin %></a>
		<p><img src="jsp/images/log_min.jpg" alt="jeannette photo"/></p>

		<p>	<a href="#pagechange" data-role="button"  target="_top"	data-mini="true" ><%=Language.changeinfo %></a></p>
	
	
	
			
			
		<p>	<a href="#buyservers" data-role="button"  target="_top"	data-mini="true" style="display: none;">購買服務</a></p>
				
		</div>
	</div><!-- /grid-a -->
	<hr/>
	
		<div class="ui-grid-a" id="contact_infos">	
		<div class="ui-block-a">
		<h2> <%=Language.devicedetial %>：</h2>
		<p id="totalnum" ><%=Language.devicenum %>：<%=request.getAttribute("totalnum")%><%=Language.tai %></p>
		<p  id="wenum" > <%=Language.wechatnum %>：<%=request.getAttribute("wenum")%><%=Language.tai %>	</p>	
		
			
		</div>		
		<div class="ui-block-b">
		
				<p>	<a href="userRegisterServlet?action=getUserCarListView&username=<%=user.getUsername() %>&weid=<%=request.getAttribute("weid")%>" target="_top" data-role="button" 	data-mini="true"  data-corners="false"><%=Language.lookcarlist%></a></p>
		
		</div>
	</div><!-- /grid-a -->
	<div >	
		<a href="tel:4006392999"  data-role="button" data-corners="false"><%=Language.callus %> </a>	
	</div>	
	<hr/>
	

	</div>


</div><!-- /page -->
<div data-role="page" id="pagechange" data-theme="b" >

  <div data-role="content">
								<a href="#pageone" data-role="button" data-inline="true"
					data-mini="true"  data-corners="false"><%=Language.backbtn %></a>
					
					<center>
						<div>
							<%=Language.changeinfo %>
						</div>
					</center>
					
					
					
				
					<div data-role="fieldcontain" data-inline="true"
						style="display: none;">
						<label for="weid">
							微信号：
						</label>
						<input type="text" name="weid" id="weid"
							value="<%=request.getAttribute("weid")%>" placeholder="weid">
					</div>
					
						
						<label for="tel_c">
							<%=Language.teltitle %>
						</label>
						<input type="text" name="tel" id="tel_c" placeholder="15000000000" value="<%=user.getPhone() %>">
						
						<label for="qq_c">
							QQ：
						</label>
						<input type="text" name="tel" id="qq_c" placeholder="15000000000" value="<%=user.getQQ() %>">
						
					<label for="email_c">
							<%=Language.email %>：
						</label>
						<input type="text" name="tel" id="email_c" placeholder="15000000000" value="<%=user.getEmail() %>" >
						
							<input type="button"  value="<%=Language.confirm %>" data-corners="false"
						onclick="return changeUserInfo();" />
						
  </div>

</div> 


<div data-role="page" id="buyservers" >


  <div data-role="content" >
  
  
      <label >余额:<%=user.getBalance() %></label>
  
  <form>
<div class="ui-field-contain">
    <label for="select-native-1">您的车辆:</label>


				<select name="did" id="did"  data-corners="false" >
				

		<%for(int i=0;i<carList.size();i++){%>
			
					<option value="<%= carList.get(i).getNo() %>">
						<%=carList.get(i).getName()%>
					</option>
			
			<%}%>
				</select>

</div>
</form>
  
  
    <form>
      
        <fieldset data-role="controlgroup" >
      <legend>平台服务当前选的服务：未选择</legend>
        <label for="pt120">120元一年</label>
        <input type="radio" name="pt" id="pt120" value="120">
        <label for="pt200">200元两年</label>
     <input type="radio" name="pt" id="pt200" value="200">
        <label for="pt260">260元三年</label>
        <input type="radio" name="pt" id="pt260" value="260">
      </fieldset>

      <fieldset data-role="controlgroup" >
      <legend>流量服务当前选的服务：未选择</legend>
         <label for="ll15">15元三个月</label>
        <input type="radio" name="ll" id="ll15" value="15">
    <label for="ll60">60元一年</label>
        <input type="radio" name="ll" id="ll60" value="60">
      </fieldset>

      <fieldset data-role="controlgroup" >
      <legend>短信服务当前选的服务：未选择</legend>
          <label for="dx10">10元100条</label>
             <input type="radio" name="dx" id="dx10" value="10">
      </fieldset>
      
      <center>
      <input type="button" data-inline="true" value="提交" onclick="getval()">
           <input type="reset" data-inline="true" value="重置">
           </center>
    </form>
  </div>




</body>
</html>



