<%@ page language="java"
	import="java.util.*,java.util.List,com.ruiyi.wechat.model.DeviceType,com.ruiyi.wechat.string.Language"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>


<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.5, user-scalable=no">
<title>征服者</title>
 <script type="text/javascript" src="../jsp/js/head.js"></script>.js"></script>

<script type="text/javascript"
	src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
</head>
<body>

	<div data-role="page" id="pageone">
		<center>
			<img alt="loading" src="../jsp/images/loading.gif">
		</center>
		
<%-- 		<%
String pid=request.getParameter("weid"); 
out.println("weid:"+pid);
%> --%>
		
	
	<button onclick="openQrScan()">扫描二维码绑定设备</button>

	</div>



</body>
<script type="text/javascript">
	
	//采用正则表达式获取地址栏参数
	function GetQueryString(name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
		var r = window.location.search.substr(1).match(reg);
		if (r != null)
			return unescape(r[2]);
		return null;
	}
	
	
	//获取初始化WXJS信息
	if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp = new XMLHttpRequest();
	} else {// code for IE6, IE5
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

			//showloading();

			var jsons = eval(xmlhttp.responseText);
			initWeixinJs(jsons[0].timestamp, jsons[0].nonceStr,
					jsons[0].signature)
		}
	};
	xmlhttp.open("GET", "../ToolsServlet?action=getJsid&url="
			+ encodeURIComponent(location.href.split('#')[0]+"@time="+(new Date()).getTime()), true);
	xmlhttp.send();

	//初始化JS
	function initWeixinJs(time, noncestr, signa) {
		wx.config({
			debug : false,
			appId : "wx71460f3d267fcd41",
			timestamp : time,
			nonceStr : noncestr,
			signature : signa,
			jsApiList : [
					 'scanQRCode'
					 ]
		});
	}

	function openQrScan()
	{
	wx.ready(function() {
	wx.scanQRCode({
    needResult: 1, // 默认为0，扫描结果由微信处理，1则直接返回扫描结果，
    scanType: ["qrCode","barCode"], // 可以指定扫二维码还是一维码，默认二者都有
    success: function (res) {
    var result = res.resultStr; // 当needResult 为 1 时，扫码返回的结果
	
	lockDeevice(result);
	}
	});
	});
	}
	
	
	function lockDeevice(result)
	{
				
			var str=result.split("_");
				
				if(str[0]=="conqueror")
				{
				
				
				
		if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} else {// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
					
	 		alert(xmlhttp.responseText);
					}
		};
	 	xmlhttp.open("GET","../lockServlet?action=addPar&weid="
			+ GetQueryString("weid")+"&did="+str[1], true);
		xmlhttp.send();
		}else
		{
			alert("二维码不合法");
		}
	}	




</script>

</html>
