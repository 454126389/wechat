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

<script src="js/utils/language.js" type="text/javascript"></script>

<script type="text/javascript"
	src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
</head>
<body>

	<div data-role="page" id="pageone">
		<!-- 顶部导航 -->
		<div data-role="header" id="head">

			<h1></h1>
			<div data-role="fieldcontain" class="ui-btn-right"
				style="position:absolute;  top: -10px;"></div>
		</div>


		<div data-role="content">
			<div id="markerStatus" ></div>
			<div id="info" style="display:none">2</div>

			<div id="address"></div>

			<!-- 地图 -->
			<div id="map" style="height:600px"></div>
			<script type="text/javascript"
				src="http://maps.google.cn/maps/api/js?sensor=false"></script>
		</div>

		<!-- 底部导航 -->
		<div data-role="footer" id="foot">
			<center>
				<input id="showWay" href="#anylink" data-role="button"
					onclick="showWay()"  type="button" />
			</center>
		</div>

	</div>



</body>
<script type="text/javascript">

	document.getElementById("markerStatus").innerText = markerStatus[lsel];
	
$("#showWay").val(showWay_tv[lsel]);
	//微信定位信息
	var wechat_latitude;
	var wechat_longitude;
	var wechat_speed;
	var wechat_accuracy;


	//设备信息
	var carinfo;

	//我的点
	var my_marker;

	//设备点
	var device_marker;

	//画线
	var flightPath;

	var my_image = {
		url : 'http://wechat.conqueror.cn/jsp/images/beachflag.png',
		// This marker is 20 pixels wide by 32 pixels tall.
		size : new google.maps.Size(32, 32),
		// The origin for this image is 0,0.
		origin : new google.maps.Point(0, 0),
		// The anchor for this image is the base of the flagpole at 0,32.
		anchor : new google.maps.Point(0, 32)
	};

	var device_marker = {
		url : 'http://wechat.conqueror.cn/jsp/images/device.gif',
		// This marker is 20 pixels wide by 32 pixels tall.
		size : new google.maps.Size(32, 32),
		// The origin for this image is 0,0.
		origin : new google.maps.Point(0, 0),
		// The anchor for this image is the base of the flagpole at 0,32.
		anchor : new google.maps.Point(0, 32)
	};

	var shape = {
		coords : [ 1, 1, 1, 20, 18, 20, 18, 1 ],
		type : 'poly'
	};

 		//获取初始化WXJS信息
		if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
		 xmlhttp = new XMLHttpRequest();
		 } else {// code for IE6, IE5
		 xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		 }
		 xmlhttp.onreadystatechange = function() {
		 if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

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
			jsApiList : [ 'getLocation' ]
		});
	}

 	wx.ready(function() {
		wx.getLocation({
			type : 'gcj02', // 默认为wgs84的gps坐标，如果要返回直接给openLocation用的火星坐标，可传入'gcj02'
			success : function(res) {
				wechat_latitude = res.latitude; // 纬度，浮点数，范围为90 ~ -90
				wechat_longitude = res.longitude; // 经度，浮点数，范围为180 ~ -180。
				wechat_speed = res.speed; // 速度，以米/每秒计
				wechat_accuracy = res.accuracy; // 位置精度
				getCarInfo();
			}
		});

		wx.error(function(res) {

			if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
				xmlhttp = new XMLHttpRequest();
			} else {// code for IE6, IE5
				xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
			}
			xmlhttp.onreadystatechange = function() {
				if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

					alert(log_fails_tv[lsel]);

				}
			};
			xmlhttp.open("GET", "../ToolsServlet?action=errorlog&log="
					+ res.errMsg, true);
			xmlhttp.send();

		});

	}); 



	//获取设备信息
	function getCarInfo() {
		if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} else {// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				carinfo = eval(xmlhttp.responseText);
				initMap();
			}
		};
		xmlhttp.open("GET", "../carLocationServlet?action=getCarInfo&did="
				+ GetQueryString("did"), true);
		xmlhttp.send();
	}

	//地图初始化
	var map;
	//地图初始化
	var map;
	function initMap() {
		var latLng = new google.maps.LatLng(wechat_latitude, wechat_longitude);
		map = new google.maps.Map(document.getElementById('map'), {
			center : latLng, // Australia.
			zoom : 15
		});

		//我的位置
		my_marker = new google.maps.Marker(
				{
					position : new google.maps.LatLng(wechat_latitude,
							wechat_longitude),
					map : map,
					icon : my_image,
					shape : shape,
					title : location_tv[lsel],
					cursor : "",
					draggable : true,
					zIndex : 1

				});
		//设备位置
		device_marker = new google.maps.Marker({
			position : new google.maps.LatLng(carinfo[0].gpsPosiLat,
					carinfo[0].gpsPosiLon),
			map : map,
			icon : device_marker,
			shape : shape,
			title : location_tv[lsel],
			cursor : "",
			zIndex : 0

		});

		device_marker.setMap(map);
		my_marker.setMap(map);
		map.setCenter(latLng);


		//画线
		var tempLine = [
				new google.maps.LatLng(carinfo[0].gpsPosiLat,
						carinfo[0].gpsPosiLon),
				new google.maps.LatLng(wechat_latitude, wechat_longitude) ];
		var linecor = "#00FF00";
		flightPath = new google.maps.Polyline({
			map : map,
			path : tempLine,
			strokeColor : linecor,
			strokeOpacity : 1.0,
			visible : true,
			strokeWeight : 2

		});
		flightPath.setMap(map);

		document.getElementById("map").style.height = $(document.body).height()
				- $("#markerStatus").height() - $("#info").height()
				- $("#address").height() - $("#head").height() * 2 - 30 - 2 - 8
				+ "px";


		// 更新当前的位置信息  
		updateMarkerPosition(latLng);
		geocodePosition(latLng);

		// 添加拖动事件监听器  
		google.maps.event.addListener(my_marker, 'dragstart', function() {
			updateMarkerAddress(searching_tv[lsel]);
			flightPath.setMap(null);
		});

		google.maps.event.addListener(my_marker, 'drag', function() {
			updateMarkerStatus(searching_tv[lsel]);
			updateMarkerPosition(my_marker.getPosition());

		});

		google.maps.event.addListener(my_marker, 'dragend', function() {
			updateMarkerStatus(search_end_tv[lsel]);
			geocodePosition(my_marker.getPosition());
			flightPath.setPath([
					new google.maps.LatLng(carinfo[0].gpsPosiLat,
							carinfo[0].gpsPosiLon), my_marker.getPosition() ]);
			flightPath.setMap(map);
		});

	//	hideLoader();
	}

	//采用正则表达式获取地址栏参数
	function GetQueryString(name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
		var r = window.location.search.substr(1).match(reg);
		if (r != null)
			return unescape(r[2]);
		return null;
	}

	function updateMarkerPosition(latLng) {
		document.getElementById('info').innerHTML = [ latLng.lat(),
				latLng.lng() ].join(', ');
	}

	var geocoder = new google.maps.Geocoder();

	function geocodePosition(pos) {
		geocoder.geocode({
			latLng : pos
		}, function(responses) {
			if (responses && responses.length > 0) {
				updateMarkerAddress(responses[0].formatted_address);
			} else {
				updateMarkerAddress(place_nofind_tv[lsel]);
			}
		});
	}

	function updateMarkerAddress(str) {
		document.getElementById('address').innerHTML = str;
	}

	var wait = 10;

	function showWay() {

		$("#showWay").attr("disabled", "true");
		$("#showWay").val(wait_tv[lsel]).button("refresh");
		$.get("../ToolsServlet?action=showWay&weid=" + GetQueryString("weid")
				+ "&did=" + GetQueryString("did") + "&lat="
				+ document.getElementById("info").innerHTML.split(",")[0]
				+ "&lon="
				+ document.getElementById("info").innerHTML.split(",")[1],
				function(data, status) {
					alert(code_suc_tip_tv[lsel])
					$("#showWay").removeAttr("disabled");
					$("#showWay").val(showWay[lsel]).button("refresh");
				});
	

	}




	function updateMarkerStatus(str) {
		document.getElementById('markerStatus').innerHTML = str;
	}


</script>

</html>
