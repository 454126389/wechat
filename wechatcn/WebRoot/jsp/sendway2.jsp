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



<script charset="utf-8" src="http://map.qq.com/api/js?v=2.exp"></script>
<script type="text/javascript"
	src="http://res.wx.qq.com/open/js/jweixin-1.1.0.js"></script>





<style type="text/css">
[class="top-advert"] {
	display: none !important;
	display: none
}

[class="middle-advert"] {
	display: none !important;
	display: none
}
</style>


</head>
<body>
	<input id="latLng" type="textbox" value="39.98174,116.30631">

	<!--   定义地图显示容器   -->
	<div id="container"></div>


</body>
<script type="text/javascript">
	//页面初始化
	//和document.documentElement.clientWidth来取得窗口的高度和宽度。
	document.getElementById("latLng").style.display = "none";
	document.getElementById("container").style.height = document.documentElement.clientHeight
			- 20 + "px";
	document.getElementById("container").style.width = document.documentElement.clientWidth
			+ "px";
</script>
<script>
	window.onload = init;

	var map;
	var info;
	var text;
	//创建div元素
	var customZoomDiv;

	//获取控件接口设置控件
	var customZoomControl;

	var marker;

	function codeLatLng() {
		document.getElementById("latLng").value.split(",", 2)[0];
		var input = document.getElementById("latLng").value;
		var latlngStr = input.split(",", 2);
		var lat = parseFloat(latlngStr[0]);
		var lng = parseFloat(latlngStr[1]);
		var latLng = new qq.maps.LatLng(lat, lng);
		info = new qq.maps.InfoWindow({
			map : map
		});
		var geocoder = new qq.maps.Geocoder(
				{
					complete : function(result) {
						map.setCenter(result.detail.location);
						setTimeout(
								function() {

									text = result.detail.address;
									document.getElementById("latLng").value=result.detail.location;
									customZoomDiv.innerHTML = result.detail.address;

									marker.setMap(map);
									marker
											.setAnimation(qq.maps.MarkerAnimation.DROP);
									marker.setPosition(result.detail.location);

									info.open();
									info
											.setContent('<button onclick="showWay()" style="color:blue; width:180px;height:100px;font-size:20px; text-align:center">'
													+ "发送我的位置" + '</button>');

									info.setPosition(result.detail.location);

								}, 1000);

					}
				});

		geocoder.getAddress(latLng);

		//绑定单击事件添加参数
		qq.maps.event.addListener(map, 'click', function(event) {
			//marker.setPosition(new qq.maps.LatLng(event.latLng.getLat(),event.latLng.getLng()));
			geocoder.getAddress(new qq.maps.LatLng(event.latLng.getLat(),
					event.latLng.getLng()));

		});
		qq.maps.event.addListener(marker, 'click', function() {
			info.open();
			info.setPosition(marker.getPosition());

		});

	}

	function CustomZoomControl(controlDiv, map, title) {

		controlDiv.style.padding = "5px";
		controlDiv.style.backgroundColor = "#FFFFFF";
		controlDiv.style.border = "2px solid #86ACF2";

		controlDiv.index = 1;//设置在当前布局中的位置
		controlDiv.innerHTML = title;
	}

	function init() {
		var center = new qq.maps.LatLng(39.982163, 116.306070);
		map = new qq.maps.Map(document.getElementById("container"), {
			center : center,
			disableDefaultUI: true,    //禁止所有控件
			zoom : 16
		});

		marker = new qq.maps.Marker();
		
		customZoomDiv = document.createElement("div");
		
		customZoomControl = new CustomZoomControl(customZoomDiv, map,"等待定位");
		//将控件添加到controls栈变量并将其设置在顶部位置
		map.controls[qq.maps.ControlPosition.LEFT_TOP	].push(customZoomDiv);
		//codeLatLng();

		init_weijs();
	}

	function init_weijs() {
		//获取初始化WXJS信息
		if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} else {// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

				var jsons = eval(xmlhttp.responseText);
				wx.config({
					debug : false,
					appId : "wx71460f3d267fcd41",
					timestamp : jsons[0].timestamp,
					nonceStr : jsons[0].nonceStr,
					signature : jsons[0].signature,
					jsApiList : [ 'getLocation' ]
				});

			}
		};
		xmlhttp.open("GET", "../ToolsServlet?action=getJsid&url="
				+ encodeURIComponent(location.href.split('#')[0] + "@time="
						+ (new Date()).getTime()), true);
		xmlhttp.send();
	}

	wx.ready(function() {
		wx.getLocation({
			type : 'gcj02', // 默认为wgs84的gps坐标，如果要返回直接给openLocation用的火星坐标，可传入'gcj02'
			success : function(res) {

				document.getElementById("latLng").value = res.latitude + ","
						+ res.longitude;
				codeLatLng();

			}
		});
	});

	function showWay() {
		info.close();

		if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} else {// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

				alert("已发送")

			}
		};
		xmlhttp.open("GET", "../ToolsServlet?action=showWay&weid="
				+ GetQueryString("weid") + "&did=" + GetQueryString("did")
				+ "&lat="
				+ document.getElementById("latLng").value.split(",", 2)[0]
				+ "&lon="
				+ document.getElementById("latLng").value.split(",", 2)[1]
				+ "&text=" + text, true);
		xmlhttp.send();

	}

	//采用正则表达式获取地址栏参数
	function GetQueryString(name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
		var r = window.location.search.substr(1).match(reg);
		if (r != null)
			return unescape(r[2]);
		return null;
	}
	
	  //异步加载地图库函数文件
function loadScript() {
  //创建script标签
  var script = document.createElement("script");
  //设置标签的type属性
  script.type = "text/javascript";
  //设置标签的链接地址
  script.src = "http://map.qq.com/api/js?v=2.exp&callback=init";
  //添加标签到dom
  document.body.appendChild(script);
}
	
	window.onload = loadScript;    // dom文档加载结束开始加载 此段代码
</script>
</html>
