<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	String appId = "wx71460f3d267fcd41";
	String timeStamp = "1458982396";
	String nonceStr = "1653085512";
	String packageValue = "prepay_id=wx201603261654143004825d0c0258975444";
	String paySign = "3DDB6B0FB50631CB783798A6ACA48502";
	
	String money = "10";
	String did = "1380106000921214";

	String dec = "短信";
	String service_id = "3";
	String orderNo = "wx71460f3d267fcd411458982385";
	String weid = "oISxbtyMDjzmDSX7Mn3VSvQKdAMQ";
%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.5, user-scalable=no">
<meta charset="utf-8">
<title>微信支付</title>


<link rel="stylesheet"
	href="http://libs.useso.com/js/jquery-mobile/1.4.2/jquery.mobile.min.css">
<link
	href='http://fonts.useso.com/css?family=Open+Sans:300,400,600&subset=latin,latin-ext'
	rel='stylesheet'>
<script src="http://libs.useso.com/js/jquery/2.1.1-rc2/jquery.min.js"
	type="text/javascript"></script>
<script
	src="http://libs.useso.com/js/jquery-mobile/1.4.2/jquery.mobile.min.js"
	type="text/javascript"></script>


</head>

<body>
	<body>
<div data-role="page">

  <div data-role="header">
    <h1>支付确认</h1>
  </div>

  <div data-role="content">
  <Center>
  
		<!-- <input type="button"  data-inline="true value="微信支付"
			onclick="callpay()"> -->
			
			<div id="pay_msg">
			
			     <label>设备:<%=did%></label>
			     <label>服务类型:<%=dec%></label>
			     <label>金额:<%=money%></label>
			
			  <a id="paybtn" href="#pagetwo" data-role="button" onclick="callpay()" data-inline="true">去支付</a>
			  </div>
			  
			  <div id="pay_suc">
			  <Center>
			    <label>购买成功!</label>
			  </Center>
			  </div>
			
</Center>			
  </div>


</div>
</body>


</body>
<script type="text/javascript">
	document.getElementById("pay_suc").style.display = "none";
				//alert("微信支付成功!");
				var url="../ToolsServlet?action=pay_suc&weid=aa";
						$.get(url, 
						function(data, status) {
						
			document.getElementById("pay_msg").style.display = "none";
			document.getElementById("pay_suc").style.display = "";
			});	
	
</script>

</html>
