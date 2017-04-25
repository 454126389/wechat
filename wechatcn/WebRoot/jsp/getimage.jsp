<%@ page language="java"
	import="java.util.*,java.util.List,com.ruiyi.wechat.model.Positions,com.ruiyi.wechat.string.Language"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.5, user-scalable=no">
<title>征服者</title>
 <script type="text/javascript" src="../jsp/js/head.js"></script>

<script src="js/carlocation/locationtor.js" type="text/javascript"></script>
<script src="js/utils/util.js" type="text/javascript"></script>

<script type="text/javascript"
	src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
</head>
<body>
	<div data-role="page" id="pageone">
		<div data-role="content">
		<center><h3 id='device_list_tv'></h3></center>
		<p id="demo"></p>
	<ul data-role="listview" data-inset="true" id="roundlistview">
					</ul>
			
		</div>
	</div>

</body>

	<script language="javascript">
	/* 	if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} else {// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

				alert("请求已发出，请稍等");
			}
		};
		xmlhttp.open("GET", "../ToolsServlet?action=getWarn&weid="+GetQueryString("weid")+"&did="+GetQueryString("did")+"@time="+ (new Date()).getTime(), true);
						
		xmlhttp.send(); */
		
		
//          /mnt/external_sdio/DCIM/PIC/
//			IMG_20130121_165230912.jpg,IMGU_20130121_165231059.jpg,IMG_20130121_173438832.jpg,IMG_20130121_173542065.jpg,IMG_20130121_165337898.jpg,IMG_20130121_165440627.jpg,IMG_20130121_165222554.jpg,IMG_20130121_165306121.jpg,IMG_20130121_165433540.jpg,IMG_20130121_171403072.jpg,IMG_20130121_171453529.jpg,IMG_20130121_171620369.jpg,IMG_20130121_171823650.jpg,IMG_20130121_165259811.jpg,IMG_20130121_171654782.jpg,IMG_20130121_165319889.jpg,IMG_20130121_193631182.jpg,IMG_20130121_193753885.jpg,IMG_20160514_140302202.jpg
//			1380106012591131

		var conut=GetQueryString("conut");
		var path=GetQueryString("path");
		var namelist=GetQueryString("namelist");
		var did=GetQueryString("did");
		
		var imagemap=namelist.split(",")	
		for(var i=0;i<imagemap.length;i++)
		{
		$("#roundlistview")
					.append(
							"<li>"+
							"<a href='#'><h3>"+imagemap[i]+"</h3><p>"+"点击下载"+"</p></a>"+
							"</li>")
					.trigger("create");

		}
					$("#roundlistview").listview("refresh"); 

		//采用正则表达式获取地址栏参数
		function GetQueryString(name) {
			var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
			var r = window.location.search.substr(1).match(reg);
			if (r != null)
				return unescape(r[2]);
			return null;
		}
	</script>
</html>


