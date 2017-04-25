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


<script type="text/javascript"
	src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script src="js/utils/language.js" type="text/javascript"></script>

</head>
<meta name="viewport" content="user-scalable=no, width=device-width" />
<body>

	<div data-role="page" id="pageone">
		<!-- 顶部导航 -->
		<div data-role="header" id="head">

			<h1 id='loc_device_tv'></h1>
			<div data-role="fieldcontain" class="ui-btn-right"
				style="position:absolute;  top: -10px;"></div>
		</div>


		<div data-role="content">
			<!-- 			<div id="markerStatus">可以拖动图标修正当前位置</div>
			<div id="info" >信息</div>

			<div id="address">等待定位</div> -->

			<!-- 地图 -->
			<div id="map" style="height:600px"></div>
			<script type="text/javascript"
				src="http://maps.google.cn/maps/api/js?sensor=false"></script>
		</div>

		<!-- 底部导航 -->
		<div data-role="footer" id="foot">
			<center>
				<div data-role="controlgroup" data-type="horizontal" class="divcss5">

					<input id="locbtn" href="#anylink" data-role="button"
						onclick="endloc()" type="button" /> 
						
						<select id="loctime" class="ci"></select> 
						<select id="loct_trr_time" class="ci" onchange="trr_change()"></select> 

					
					
					
					<a href="#pagetwo" data-transition="flip" data-role="button"
						id='text_moder_tv'></a>
						
						
				</div>
			</center>
		</div>

	</div>

	<div data-role="page" id="pagetwo">
		<div id="msgmode"></div>
		<!-- 底部导航 -->
		<div data-role="footer" id="foot">
			<center>
				<a href="#pageone" data-transition="flip" data-role="button"
					id='map_moder_tv'></a>
			</center>
		</div>
	</div>

</body>
<script type="text/javascript">
	document.getElementById("loc_device_tv").innerText = loc_device_tv[lsel];

	$("#locbtn").val(end_loc[lsel]);
	document.getElementById("text_moder_tv").innerText = text_moder_tv[lsel];
	document.getElementById("map_moder_tv").innerText = map_moder_tv[lsel];
	
	var loct_trr_time = document.getElementById('loct_trr_time');
	loct_trr_time.options.add(new Option("间隔5秒", "5"));
	loct_trr_time.options.add(new Option("间隔30秒", "30"));

	var loctime = document.getElementById('loctime');
	loctime.options.add(new Option(do_30_min[lsel], "360"));
	loctime.options.add(new Option(do_1_hour[lsel], "720"));
	loctime.options.add(new Option(do_6_hour[lsel], "4320"));
	loctime.options.add(new Option(do_12_hour[lsel], "8640"));

	//页面初始化
	var h = document.body.clientHeight
			- document.getElementById("head").offsetHeight
			- $("#markerStatus").height() - $("#info").height()
			- $("#address").height() - $("#head").height()
			- document.getElementById("foot").offsetHeight - 45 - 104 + 30;
	document.getElementById("map").style.height = h + "px";

	//微信定位信息
	var wechat_latitude;
	var wechat_longitude;
	var wechat_speed;
	var wechat_accuracy;

	var last_wechat_latitude;
	var last_wechat_longitude;
	var last_carinfo_latitude;
	var last_carinfo_longitude;

	//设备信息
	var carinfo;

	//我的点
	var my_marker;

	//设备点
	var device_marker;

	//画线
	var flightPath;

	var my_image = {
		url : 'images/icon_geo.png',
		// This marker is 20 pixels wide by 32 pixels tall.
		size : new google.maps.Size(32, 32),
		// The origin for this image is 0,0.
		origin : new google.maps.Point(0, 0),
		// The anchor for this image is the base of the flagpole at 0,32.
		anchor : new google.maps.Point(0, 32)
	};

	var device_marker = {
		url : 'http://wechat.conqueror.cn/jsp/images/beachflag.png',
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

	var mode = google.maps.DirectionsTravelMode.DRIVING; //谷歌地图路线指引的模式  
	var directionsDisplay = new google.maps.DirectionsRenderer(); //地图路线显示对象  
	var directionsService = new google.maps.DirectionsService(); //地图路线服务对象  
	var directionsVisible = false; //是否显示路线  

	var linecor = "#00FF00";

	var myflag = -1;
	var deviceflag = -1;

	var tempLine;

	var loc_Interval;

	initMap();

	var timeout = 5000;

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
			+ encodeURIComponent(location.href.split('#')[0] + "@time="
					+ (new Date()).getTime()), true);
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

	//我的位置
	my_marker = new google.maps.Marker({
		/* 	position : new google.maps.LatLng(wechat_latitude,wechat_longitude), */
		map : map,
		icon : my_image,
		shape : shape,
		title : location_tv[lsel],
		cursor : "",
		draggable : false,
		zIndex : 1

	});
	//设备位置
	device_marker = new google.maps.Marker({
		/* 			position : new google.maps.LatLng(carinfo[0].lat, carinfo[0].lng), */
		map : map,
		icon : device_marker,
		shape : shape,
		title : location_tv[lsel],
		draggable : false,
		cursor : "",
		zIndex : 0

	});

	flightPath = new google.maps.Polyline({
		map : map,
		//		path : tempLine,
		strokeColor : linecor,
		strokeOpacity : 1.0,
		visible : true,
		strokeWeight : 2

	});

	/* 	upmy();
	 updevice(); */

	// 添加拖动事件监听器  
	/* 	google.maps.event.addListener(device_marker, 'dragstart', function() {
	 updateMarkerAddress('正在搜索...');
	 flightPath.setMap(null);
	 });

	 google.maps.event.addListener(device_marker, 'drag', function() {
	 updateMarkerStatus('正在搜索...');
	 updateMarkerPosition(my_marker.getPosition());

	 });

	 google.maps.event.addListener(device_marker, 'dragend', function() {
	 planroad();
	 }); */

	wx.ready(function() {

		//送出追踪开始请求
		$.ajax(
				{
					url : "../carLocationServlet?action=getLocStart&did="
							+ GetQueryString("did") + "&time="
							+ $("#loctime").val(),
					cache : false
				}).done(function(html) {
			//定位我的位置
			upmy();
			//定位设备位置
			updevice();
			//检测是否都定位完了，然后规划路径
			loc_Interval = setInterval('checkdo()', Number(timeout));
			$("#locbtn").val(end_loc[lsel]).button("refresh");
		});
	});
	//atuofit();

	//轮询是否执行
	function checkdo() {

		if (myflag == 1 && deviceflag == 1) {

			//画线
			flightPath.setPath([ device_marker.getPosition(),
					my_marker.getPosition() ]);
			flightPath.setMap(map);

			if (last_wechat_latitude == null || last_carinfo_latitude == null) {

				drawandregetpoi();

			} else {

				//两地距离
				var my2devicedis = getFlatternDistance(Number(carinfo[0].lat),
						Number(carinfo[0].lng), Number(wechat_latitude),
						Number(wechat_longitude));
				var mydis = getFlatternDistance(Number(last_wechat_latitude),
						Number(last_wechat_longitude), Number(wechat_latitude),
						Number(wechat_longitude));
				var devicedis = getFlatternDistance(
						Number(last_carinfo_latitude),
						Number(last_carinfo_longitude), Number(carinfo[0].lat),
						Number(carinfo[0].lng));

				if (!my2devicedis) {
					my2devicedis = 0;
				}
				if (!mydis) {
					mydis = 0;
				}
				if (!devicedis) {
					devicedis = 0;
				}

				if (my2devicedis > 2000) {

					timeout = 10000;

					//2公里以外移动500米
					if (mydis > 500 || devicedis > 500) {
						drawandregetpoi();
					}
				} else {

					timeout = 5000;
					//50米
					if (mydis > 500 || devicedis > 500) {
						drawandregetpoi();
					}
				}

			}

			myflag = -1;
			deviceflag = -1;
			upmy();
			updevice();
		}
	}

	//规划路径并重新定位
	function drawandregetpoi() { //规划路径
		planroad();
	}

	//规划路径
	function planroad() {
		/* 	updateMarkerStatus('搜索结束'); */
		// 更新当前的位置信息  
		/* updateMarkerPosition(device_marker.getPosition());
		geocodePosition(device_marker.getPosition()); */

		//画轨迹
		calculateAndDisplayRoute();

		//记录上次位置
		last_wechat_latitude = wechat_latitude;
		last_wechat_longitude = wechat_longitude;
		last_carinfo_latitude = carinfo[0].lat;
		last_carinfo_longitude = carinfo[0].lng;
		wechat_latitude = null;
		wechat_longitude = null;
		carinfo[0].lat = null;
		carinfo[0].lng = null;

	}

	function calculateAndDisplayRoute() {
		var start = my_marker.getPosition();
		var end = device_marker.getPosition();
		directionsDisplay.setMap(map);
		directionsDisplay.setPanel(document.getElementById("msgmode")); //将路线导航结果显示到
		directionsService.route({
			origin : start,
			destination : end,
			avoidHighways : false,
			avoidTolls : false,
			travelMode : google.maps.TravelMode.DRIVING
		}, function(response, status) {
			if (status === google.maps.DirectionsStatus.OK) {
				directionsDisplay.setDirections(response);
			} else {
				window.alert('Directions request failed due to ' + status);
			}
		});
	}

	//更新我的位置
	function upmy() {
		wx.getLocation({
			type : 'gcj02', // 默认为wgs84的gps坐标，如果要返回直接给openLocation用的火星坐标，可传入'gcj02'
			success : function(res) {
				wechat_latitude = res.latitude; // 纬度，浮点数，范围为90 ~ -90
				wechat_longitude = res.longitude; // 经度，浮点数，范围为180 ~ -180。
				wechat_speed = res.speed; // 速度，以米/每秒计
				wechat_accuracy = res.accuracy; // 位置精度

				my_marker.setPosition(new google.maps.LatLng(wechat_latitude,
						wechat_longitude));
				my_marker.setMap(map);
				myflag = 1;

			}
		});
		/*
		 		wechat_latitude = 26.648516;
			wechat_longitude = 120.15641;

			my_marker.setPosition(new google.maps.LatLng(wechat_latitude,
					wechat_longitude));
			my_marker.setMap(map);
			myflag = 1; 
			
		 */

	}

	//更新设备位置
	function updevice() {

		if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} else {// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				carinfo = eval("[" + xmlhttp.responseText + "]");
				device_marker.setPosition(new google.maps.LatLng(
						carinfo[0].lat, carinfo[0].lng));
				device_marker.setMap(map);
				deviceflag = 1;

			}
		};
		xmlhttp.open("GET", "../carLocationServlet?action=getLastPoi&did="
				+ GetQueryString("did"), true);
		xmlhttp.send();

	}

	function endloc() {
		//window.history.go(-1);

		if ($("#locbtn").val() == end_loc[lsel]) {

			$.ajax(
					{
						url : "../carLocationServlet?action=getLocStart&did="
								+ GetQueryString("did") + "&time=" + 0,
						cache : false
					}).done(function(html) {

				clearInterval(loc_Interval);
				$("#locbtn").val(start_loc[lsel]).button("refresh");
			});

		} else {

			$.ajax(
					{
						url : "../carLocationServlet?action=getLocStart&did="
								+ GetQueryString("did") + "&time="
								+ $("#loctime").val(),
						cache : false
					}).done(function(html) {
				loc_Interval = setInterval('checkdo()', Number(timeout));
				$("#locbtn").val(end_loc[lsel]).button("refresh");
			});

		}

	}

	//地图初始化
	var map;
	//地图初始化
	var map;
	function initMap() {
		map = new google.maps.Map(document.getElementById('map'), {
			center : {
				lat : 39.5427,
				lng : 116.2317
			},
			zoom : 15
		});

	}

	function atuofit() {
		var locationList = [];
		locationList.push(my_marker.getPosition());
		locationList.push(device_marker.getPosition());
		var bounds = new google.maps.LatLngBounds();
		for (var j = 0; j < locationList.length; j++)
			bounds.extend(locationList[j]);
		map.fitBounds(bounds);
	}

	//采用正则表达式获取地址栏参数
	function GetQueryString(name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
		var r = window.location.search.substr(1).match(reg);
		if (r != null)
			return unescape(r[2]);
		return null;
	}

	/* function updateMarkerPosition(latLng) {
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
				updateMarkerAddress('无法确定地址在这个位置。');
			}
		});
	} */

	/* 	function updateMarkerAddress(str) {
	 document.getElementById('address').innerHTML = str;
	 }

	 function updateMarkerStatus(str) {
	 document.getElementById('markerStatus').innerHTML = str;
	 } */

	//算距离
	var EARTH_RADIUS = 6378137.0; //单位M
	var PI = Math.PI;
	function getRad(d) {
		return d * PI / 180.0;
	}

	function getFlatternDistance(lat1, lng1, lat2, lng2) {
		var f = getRad((lat1 + lat2) / 2);
		var g = getRad((lat1 - lat2) / 2);
		var l = getRad((lng1 - lng2) / 2);

		var sg = Math.sin(g);
		var sl = Math.sin(l);
		var sf = Math.sin(f);

		var s, c, w, r, d, h1, h2;
		var a = EARTH_RADIUS;
		var fl = 1 / 298.257;

		sg = sg * sg;
		sl = sl * sl;
		sf = sf * sf;

		s = sg * (1 - sl) + (1 - sf) * sl;
		c = (1 - sg) * (1 - sl) + sf * sl;

		w = Math.atan(Math.sqrt(s / c));
		r = Math.sqrt(s * c) / w;
		d = 2 * w * a;
		h1 = (3 * r - 1) / 2 / c;
		h2 = (3 * r + 1) / 2 / s;

		return d * (1 + fl * (h1 * sf * (1 - sg) - h2 * (1 - sf) * sg));
	}
	
	function trr_change()
	{
		clearInterval(loc_Interval);
		loc_Interval = setInterval('checkdo()', Number($("#loct_trr_time").val()));
	}
	
</script>

</html>
