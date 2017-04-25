<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	String appId = request.getParameter("appid");
	String timeStamp = request.getParameter("timeStamp");
	String nonceStr = request.getParameter("nonceStr");
	String packageValue = request.getParameter("package");
	String paySign = request.getParameter("sign");
	
	String money = request.getParameter("money");
	String did = request.getParameter("did");

	String service_id = request.getParameter("service_id");
	String orderNo = request.getParameter("orderNo");
	String weid = request.getParameter("weid");
	String key = request.getParameter("key");
	String dec="";
	if(service_id.equals("3"))
		dec="短信服务";
	else if(service_id.equals("3"))
		dec="平台服务";
	
	
	
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.5, user-scalable=no">
<meta charset="utf-8">
<title>微信支付</title>


 <script type="text/javascript" src="../jsp/js/head.js"></script>

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

    // 对浏览器的UserAgent进行正则匹配，不含有微信独有标识的则为其他浏览器
    var useragent = navigator.userAgent;
    if (useragent.match(/MicroMessenger/i) != 'MicroMessenger') {
        // 这里警告框会阻塞当前页面继续加载
        alert('已禁止本次访问：您必须使用微信内置浏览器访问本页面！');
        // 以下代码是用javascript强行关闭当前页面
        var opened = window.open('about:blank', '_self');
        opened.opener = null;
        opened.close();
    }else
    {


	document.getElementById("pay_suc").style.display = "none";

  	function callpay(){
		 WeixinJSBridge.invoke('getBrandWCPayRequest',{
  		 "appId" : "<%=appId%>","timeStamp" : "<%=timeStamp%>", "nonceStr" : "<%=nonceStr%>", "package" : "<%=packageValue%>","signType" : "MD5", "paySign" : "<%=paySign%>"
		}, function(res) {
			WeixinJSBridge.log(res.err_msg);
			// 				alert(res.err_code + res.err_desc + res.err_msg);
			if (res.err_msg == "get_brand_wcpay_request:ok") {
				//alert("微信支付成功!");
				
				var url="../ToolsServlet?action=pay_suc&weid="
						+ "<%=weid%>" + "&id="+"<%=did%>"+"&time="+"<%=timeStamp%>"+"&service_id="+"<%=service_id%>"+"&money="+"<%=money%>"+"&serial="+"<%=orderNo%>"+"&key="+"<%=key%>";
						
						
					
						
						
		/* if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} else {// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

			document.getElementById("pay_msg").style.display = "none";
					document.getElementById("pay_suc").style.display = "";
					
					alert("微信支付成功!");

			}
		};
		xmlhttp.open("GET", url, true);
		xmlhttp.send(); */
				
			} else if (res.err_msg == "get_brand_wcpay_request:cancel") {
				alert("用户取消支付!");
			} else {
				alert("支付失败,服务器暂时无法提供服务");
			}
		})
	}
	}
	

	
</script>

</html>
