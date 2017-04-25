
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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

<script src="js/utils/language.js" type="text/javascript"></script>

</head>

<body onload="init();">
	<div data-role="page" id="pageone">
		<div data-role="content">
		<center><h3 id='device_list_tv'></h3></center>
		
		<p id="demo"></p>
					
				
					
					
					
			<fieldset data-role="collapsible">


				<legend id='add_device_tv'></legend>
								<legend id='type_add_by_id'></legend>
					<input type="search" name="did" size=100
						id="did" placeholder="ID">
						
					 <input id='playhis_tv' type="Submit"  onclick="addPar();"> 
			</fieldset>
					
					
			<ul data-role="listview" data-autodividers="true" data-inset="true" data-filter="true" id="roundlistview">
					</ul>
					
					
		</div>
	</div>

</body>


<script type="text/javascript">
	
	$("#playhis_tv").val(playhis_tv[lsel]);

document.getElementById("device_list_tv").innerText = device_list_tv[lsel];
document.getElementById("add_device_tv").innerText = add_device_tv[lsel];
document.getElementById("type_add_by_id").innerText = type_add_by_id[lsel];

 		$.get("../carLocationServlet?action=getCarOnMap&weid="
				+ GetQueryString("weid"), function(data, status) {
			objs = eval(data);
			init(objs);
		}); 

	
	
		//采用正则表达式获取地址栏参数
	function GetQueryString(name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
		var r = window.location.search.substr(1).match(reg);
		if (r != null)
			return unescape(r[2]);
		return null;
	}
	
	
		//提示窗口
	function popup(msg) {
		//2秒后关闭
		clearTimeout(t);
		document.getElementById("popmsg").innerHTML = msg;
		$("#popmsg").popup();
		$("#popmsg").popup("open");
		var t = setTimeout("timedCount()", 2000)

	}
	//自动关闭
	function timedCount() {
		$("#popmsg").popup("close");
	}
	
		function addPar()
		{		
	
		if(""==$("#did").val())
			alert("输入为空");
			else
			{
	
		var didhis = document.getElementById('did').value;
		if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} else {// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
					
	 		alert(xmlhttp.responseText);
			 document.getElementById("demo").innerHTML=xmlhttp.responseText;
			 $("#did").val("");
			 location.reload(); 
					}
		};
	 	xmlhttp.open("GET","../lockServlet?action=addPar&weid="
			+ GetQueryString("weid")+"&did="+$("#did").val()+"&time="+(new Date()).getTime(), true);
		xmlhttp.send();
	}
		
		}
		
		function removePar(id)
		{
			var sid="li"+id;
			$("#"+sid).remove();
		 	$.ajax({url:"../ToolsServlet?action=removepar&did="+id,async:false});
		}
		
		function openmap(lat,lon)
		{
		
 /* 		wx.openLocation({
    		latitude: lat, // 纬度，浮点数，范围为90 ~ -90
    	 	longitude: lon, // 经度，浮点数，范围为180 ~ -180。
    		name: '设备', // 位置名
   			address: '', // 地址详情说明
  		    scale: 1, // 地图缩放级别,整形值,范围从1~28。默认为最大
    		infoUrl: '' // 在查看位置界面底部显示的超链接,可点击跳转
			});  */
		}
		
		

		function init(objs) {
			for ( var car in objs) {
				/* $("#did").append(
						"<option value='"+objs[car].id+"'>" + objs[car].id
								+ ":<br/>" + objs[car].alias + "</option>")
						.trigger("create"); */
						
				$("#roundlistview")
					.append(
							"<li id=li"+objs[car].no+" name="+objs[car].name+" onclick='changeName("+objs[car].no+",this)'>"+
							"<a id=op"+objs[car].no+" onclick='openmap("+objs[car].gpsPosiLat+","+objs[car].gpsPosiLon+")' href='#'><h3 id=h3"+objs[car].no+">"+objs[car].name+"</h3><p>"+objs[car].no+"</p></a>"+
							"<a id='raoundrail_id'  onclick='removePar("+objs[car].no+")' data-rel='dialog' data-transition='pop' data-icon='delete'>Download Browser</a>"+
							"</li>")
					.trigger("create");
			$("#roundlistview").listview("refresh"); 				
						
						$(".ui-listview-filter").remove();       
						$("#roundlistview").listview("option", "filter", true);
						$("#roundlistview").trigger("listviewcreate");
						   
						
						
			}
		}
		

	function changeName(id,obj)
	{
	
	
 	newname = prompt("输入新的备注名",""+$("#li"+id).attr("name"));
		if (newname != null){
		var didhis = document.getElementById('did').value;
		if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} else {// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
					document.getElementById("h31490106040901016").innerText=newname;
					}
		};
	 	xmlhttp.open("GET","../lockServlet?action=changename&name="+newname+"&did="+id, true);
		xmlhttp.send(); 
	}else{
//alert("你按了[取消]按钮");
	} 
	
	
	}
	
	
</script>

</html>