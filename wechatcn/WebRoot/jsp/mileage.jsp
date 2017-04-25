<%@ page language="java"
	import="java.util.*,java.util.List,com.ruiyi.wechat.model.Positions,com.ruiyi.wechat.string.Language"
	import="java.sql.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML>

<% 
	List<Positions> carPosiList=(List<Positions>) request.getAttribute("carPosiList");
%>

<html>

	<head>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">

		<meta name="viewport"
			content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.5, user-scalable=no">

 <script type="text/javascript" src="../jsp/js/head.js"></script>
	
	<style type="text/css"> 

#start_time,#end_time{
	
    padding-right:1px; 
	background:url("images/calendar.png") no-repeat scroll right center transparent; 
   
}
	</style>  

<script src="jsp/js/jquery.js"></script>
<script
	src="jsp/js/jquery.mobile-1.3.2.min.js"></script>



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
		
		
		function init()
	{
        
        var clock =  new Date().getFullYear() + "-";
        if(eval(new Date().getMonth() + 1) < 10)
        clock += "0";
        clock += eval(new Date().getMonth() + 1) + "-";
          
        if(new Date().getDate() < 10)
            clock += "0";
            
        document.getElementById('start_time').value=clock+(new Date().getDate()-1);
		document.getElementById('end_time').value=clock+new Date().getDate();
	}	

	//清空内容		
	function selchange(){
	$("#mileagelist").html("");
	
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

				stoploading();
				parseXML2List(xmlhttp.responseXML, "bundling");
				//格式化li
			}
		};
	 	xmlhttp.open("GET", "/lockServlet?action=getLockList&weid=<%=request.getParameter("weid")%>"+"&random="+ra, true);
		xmlhttp.send();
	}
		
		

		function fmtDate(dt) {
		return dt.getFullYear() + "-" + eval(dt.getMonth()+1) + "-" + eval(dt.getDate()-1);
		}	
		
	
	function serach(){
	
	
		$("#mileagelist").html("");
	
		var didhis = document.getElementById('did').value;
		var start_time=document.getElementById('start_time').value;
		
		
		start_time=fmtDate(new Date(start_time.replace(/-/g,"/")));
		
		
		var end_time=document.getElementById('end_time').value;
	
	
			var xmlhttp;
		if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} else {// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {


				  jsonobj=eval(xmlhttp.responseText); 
				  
				  
				  if(jsonobj==null||jsonobj.length==0)
				  {
				  popup("<%=Language.recodenofind%>");
				  }
				  if(jsonobj.length>0)
				  {
				  
				  $("#mileagelist").html("<div class='ui-block-a' style='background-color: lightgray;padding: 25px;border: 1px solid black;height: 60px;text-align: center;'><span><%=Language.date%></span></div><div class='ui-block-b' style='background-color: lightgray;padding: 25px;border: 1px solid black;height: 60px;text-align: center;'><span><%=Language.mileage%></span></div>");
				  var str="";
				  for(var i = 0; i < jsonobj.length; i++)
				  {
				  		str+="<div class='ui-block-a' style='padding: 25px;border: 1px solid black;height: 60px;text-align: center;'><span>"+jsonobj[i].time+"</span></div><div class='ui-block-b' style='padding: 25px;border: 1px solid black;height: 60px;text-align: center;'><span>"+jsonobj[i].miles+"</span></div>";
				  }	
						$("#mileagelist").append(str).trigger("create"); 
				  }
			}
		};
	 	xmlhttp.open("GET", "lockServlet?action=searchMileage&didhis="+didhis+"&start_time="+start_time+"&end_time="+end_time, true);
		xmlhttp.send();
	}

			
	
</script>
	</head>
	<body onload="init();">
		<div data-role="popup" id="noline" class="ui-content" data-theme="a">
		</div>
		<center>
			<span class="username"><%=Language.mileagelist%></span>
		</center>
		
		<div data-role="page" id="pagechange" data-theme="b">
		
		 <div data-role="content">
			<select name="did" id="did" onchange="selchange();" data-corners="false">
				<%
			for(int i=0;i<carPosiList.size();i++)
			{
			%>
				<option value="<%= carPosiList.get(i).getNo() %>">
					<%=carPosiList.get(i).getName()%>
					<%=carPosiList.get(i).getStatus()%>
				</option>
				<%}%>
			</select>

			<div>
				<span class="username"><%=Language.stime%>:</span>
				
				
				<input id="start_time" type="date"
					value="1982-12-12"   />
				<span class="username"><%=Language.etime%>:</span>
				<input id="end_time" type="date" value="1982-12-12" class="mypw"/>

				<input id="playhis" name="playhis" type="button" value="<%=Language.confirm%>" data-corners="false"
					onclick="serach();" />
			</div>
			<div data-role="content">
				<div class="ui-grid-a" id="mileagelist">
					
				</div>
			</div>
			</div>
			</div>
	</body>
</html>