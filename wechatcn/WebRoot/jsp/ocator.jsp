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
</head>
<body>

	<div data-role="page" id="pageone">
		<!-- 顶部导航 -->
		<div data-role="header" id="head">

			<h1>设备追踪</h1>
			<div data-role="fieldcontain" class="ui-btn-right"
				style="position:absolute;  top: -10px;"></div>
		</div>


		<div data-role="content">
			<div id="markerStatus">可以拖动图标修正当前位置</div>
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
				<input  href="#anylink" data-role="button"
					onclick="findmy()" value="我的位置" type="button" /> 
				<input  href="#anylink" data-role="button"
					onclick="finddevice()" value="设备位置" type="button" /> 	
					<input
					id="showWay" href="#anylink" data-role="button" onclick="endloc()"
					value="结束追踪" type="button" />
					 <a href="#pagetwo" data-transition="flip" data-role="button">文字模式</a>
			</center>
		</div>

	</div>

<div data-role="page" id="pagetwo">
  	<div id="msgmode"></div>
  		<!-- 底部导航 -->
		<div data-role="footer" id="foot">
			<center>
			 <a href="#pageone" data-transition="flip" data-role="button">地图模式</a>
			</center>
		</div>
</div> 

</body>
<script type="text/javascript">
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

	var mode = google.maps.DirectionsTravelMode.DRIVING;    //谷歌地图路线指引的模式  
	var directionsDisplay = new google.maps.DirectionsRenderer();   //地图路线显示对象  
	var directionsService = new google.maps.DirectionsService();    //地图路线服务对象  
	var directionsVisible = false;  //是否显示路线  

	var linecor = "#00FF00";
	
	//初始化界面
	document.getElementById("map").style.height = $(document.body).height()
				- $("#markerStatus").height() - $("#info").height()
				- $("#address").height() - $("#head").height() * 2 - 30 - 2 - 8
				+ "px";

	//我的位置
	my_marker = new google.maps.Marker({
		/* 	position : new google.maps.LatLng(wechat_latitude,wechat_longitude), */
			map : map,
			icon : my_image,
			shape : shape,
			title : "我的位置",
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
			title : "设备位置",
			draggable : true,
			cursor : "",
			zIndex : 0

				});

		initMap();
		upmy();
		updevice();
	
		// 添加拖动事件监听器  
		google.maps.event.addListener(device_marker, 'dragstart', function() {
			updateMarkerAddress('正在搜索...');
			flightPath.setMap(null);
		});

		google.maps.event.addListener(device_marker, 'drag', function() {
			updateMarkerStatus('正在搜索...');
			updateMarkerPosition(my_marker.getPosition());

		});


		google.maps.event.addListener(device_marker, 'dragend', function() {
			doloc()
		});
	
	
		atuofit();
		planroad()
	
	
	
	function findmy()
	{
	map.setCenter(my_marker.getPosition());
	}
	function finddevice()
	{
	map.setCenter(device_marker.getPosition());
	}
	
	function endloc()
	{
	window.history.go(-1);
	}

	//地图初始化
	var map;
	//地图初始化
	var map;
	function initMap() {
		//var latLng = new google.maps.LatLng(wechat_latitude, wechat_longitude);
		map = new google.maps.Map(document.getElementById('map'), {
			center : {
				lat : 39.5427,
				lng : 116.2317
			},
			zoom : 15
		});

	









	

 
	}
	
	
	function beforeInitWxjs()
	{
	
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
	
	}
	
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
	
	//更新我的位置
	function upmy()
	{
	
	/* wx.getLocation({
		type : 'gcj02', // 默认为wgs84的gps坐标，如果要返回直接给openLocation用的火星坐标，可传入'gcj02'
		success : function(res) {
			wechat_latitude = res.latitude; // 纬度，浮点数，范围为90 ~ -90
			wechat_longitude = res.longitude; // 经度，浮点数，范围为180 ~ -180。
			wechat_speed = res.speed; // 速度，以米/每秒计
			wechat_accuracy = res.accuracy; // 位置精度
			
			
			my_marker.setPosition(new google.maps.LatLng(wechat_longitude,wechat_longitude));
			my_marker.setMap(map);  
			
		}
	}); */
	
	wechat_longitude = 120.15641;
	wechat_latitude = 26.648516;
	
	my_marker.setPosition(new google.maps.LatLng(wechat_longitude,wechat_longitude));
	my_marker.setMap(map);  
	
	}
	
	//更新设备位置
	function updevice()
	{
	
	if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} else {// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				carinfo = eval("[" + xmlhttp.responseText + "]");
				device_marker.setPosition(new google.maps.LatLng(carinfo[0].lat, carinfo[0].lng));
				device_marker.setMap(map); 
				
						// 更新当前的位置信息  
				updateMarkerPosition(device_marker.getPosition());
		//		geocodePosition(device_marker.getPosition());
				
			}
		};
		xmlhttp.open("GET", "../carLocationServlet?action=getLastPoi&did="
				+ GetQueryString("did"), true);
		xmlhttp.send();
	
	}
	
	
	//追踪
	function doloc()
	{
		updateMarkerStatus('搜索结束');
		geocodePosition(device_marker.getPosition());
			flightPath.setPath([
					device_marker.getPosition(),
					my_marker.getPosition() ]);
			flightPath.setMap(map);
		
			atuofit();
			planroad()
	}
	
	//规划路径
	function planroad()
	{
	
				//画线
		var tempLine = [
				new google.maps.LatLng(carinfo[0].lat, carinfo[0].lng),
				new google.maps.LatLng(wechat_latitude, wechat_longitude) ];
		
		flightPath = new google.maps.Polyline({
			map : map,
			path : tempLine,
			strokeColor : linecor,
			strokeOpacity : 1.0,
			visible : true,
			strokeWeight : 2

		});
		flightPath.setMap(map);
	
		//画轨迹
		directionsDisplay.setMap(map);  
		directionsDisplay.setPanel(document.getElementById("msgmode")); //将路线导航结果显示到
		var request = {origin: my_marker.getPosition(), destination: device_marker.getPosition(), travelMode: mode, optimizeWaypoints: true, avoidHighways: false,avoidTolls: false};  
		directionsService.route   
		(request,  function(response, status)  
    	{  
        	if (status == google.maps.DirectionsStatus.OK)  
        	{  
                directionsDisplay.setDirections(response);  
        	}  
    	}  
		);  
		directionsVisible = true; 
	}
	
	function atuofit()
	{
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
				updateMarkerAddress('无法确定地址在这个位置。');
			}
		});
	}

	function updateMarkerAddress(str) {
		document.getElementById('address').innerHTML = str;
	}



	function updateMarkerStatus(str) {
		document.getElementById('markerStatus').innerHTML = str;
	}
</script>

</html>
