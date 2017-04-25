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

<script src="js/carlocation/locationtor.js" type="text/javascript"></script>
<script src="js/utils/util.js" type="text/javascript"></script>
<script src="js/utils/language.js" type="text/javascript"></script>


<script type="text/javascript"
	src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>




<style>
.divcss5 {
	text-align: center
}
</style>





</head>
<body>
	<!-- 提示窗口 -->
	<div data-role="popup" id="popmsg" class="ui-content" data-theme="a">
	</div>

	<div data-role="page" id="#pageone">

		<!-- 左边面板 -->
		<div data-role="panel" data-position="left"
			class="list-group shortcut_menu dn linear-g" id="panel-left"
			data-display="overlay">

			<select name="did" id="did" onchange="carselchange(1);"
				data-corners="false">
			</select>

			<ul data-role="listview" data-inset="true" id="carstates">
			</ul>

			<p id="round_tv"></p>

			<div id="round_time_tv"></div>

			<div id="round_poi"></div>


			<ul data-role="listview" data-inset="true" id="roundlistview">
			</ul>

			<fieldset data-role="collapsible">


				<legend id='round_set_tv'></legend>
				<div>
					<label id='round_start_tv' for="name"></label> <input type="range"
						name="roundstime" id="roundstime" value="22" min="0" max="24">
					<label for="name" id='round_end_tv'></label> <input type="range"
						name="roundetime" id="roundetime" value="06" min="0" max="24">

					<div data-role="controlgroup" id="round_set"></div>
				</div>



				<label class="divcss5" id='round_size_tv'></label>
				<fieldset data-role="fieldcontain">
					<select id="round_size">
						<option value="0.5">0.5KM</option>
						<option value="1">1KM</option>
						<option value="2">2KM</option>
						<option value="3">3KM</option>
						<option value="4">4KM</option>
						<option value="5">5KM</option>
						<option value="5">10KM</option>
						<option value="50">50KM</option>
						<option value="100">100KM</option>
						<option value="200">200KM</option>
						<option value="300">300KM</option>
					</select>
				</fieldset>
				<label id='round_center_tv'></label> <input type="button"
					id='center_chose_tv' onclick="addroundrail()">
			</fieldset>


		</div>

		<!-- 右边面板 -->
		<div data-role="panel" data-position="right"
			class="user_box text-center dn linear-g" id="panel-right"
			data-icon="search" data-display="overlay">

			<select name="did2" id="did2" onchange="carselchange2();"
				data-corners="false">
			</select>

			<div class="u_info">
				<span class="username" id='data_tv'></span> <input id="start_time"
					type="datetime-local" value="1982-12-12T03:45">

				<fieldset data-role="controlgroup" data-type="horizontal">
					<legend id='range_tv'> </legend>
					<label for="day_sel"> Select A </label> <select name="select-h-2a"
						id="day_sel">


					</select> <label for="hours_sel"> Select C </label> <select
						name="select-h-2c" id="hours_sel">
					</select>
				</fieldset>

				<span class="username" id='play_trr_tv'></span>
				<div data-role="fieldcontain">
					<input type="range" name="speed" id="speed" value="1.5" min="0.5"
						max="2.5">
				</div>

				<span class="username" id='paly_center_tv'></span>
				<div>
					<select name="switch" id="switch" data-role="slider">
						<option value="off">off</option>
						<option value="on" selected="selected">on</option>
					</select>
				</div>

				<input id="playhis_tv" name="playhis_tv" type="button"
					onclick="searchbtn();" data-inline="true" data-icon="search" />
			</div>
		</div>



		<!-- 顶部导航 -->
		<div data-role="header" id="head">
			<a href="#panel-left" data-role="button" data-icon="home"
				id='carlist_tv'> </a>
			<h1></h1>
			<div data-role="fieldcontain" class="ui-btn-right"
				style="position:absolute;  top: -10px;">

				<!-- 	<select name="switch_local" id="switch_lacal" data-role="slider" 
					onchange="local_change();">
					<option value="off">定位关</option>
					<option value="on">定位开</option>
				</select> -->
			</div>
		</div>

		<!-- 地图 -->
		<div data-role="content" id="map" style="height:600px"></div>
		<script type="text/javascript"
			src="http://ditu.google.cn/maps/api/js?sensor=false"></script>

		<!-- 底部导航 -->
		<div data-role="footer" id="foot">
			<div data-role="controlgroup" data-type="horizontal" class="divcss5">

				<a href="#panel-right" data-role="button" id='history_search_tv'>
				</a> 
		
			</div>
		</div>
	</div>


	<!-- 超速点页面 -->
	<div data-role="page" id="page_point_over">
		<div data-role="header">


			<div data-role="navbar">
				<ul>
					<li><a href="#" id="over_btn" data-icon="alert"></a></li>
					<li><a href="#" data-icon="minus" id="stop_btn"></a></li>
					<li><a href="#" data-icon="search" id="mileage_btn"></a></li>
				</ul>
			</div>

		</div>

		<div data-role="content">


			<div id="over_panel">
				<p id='over_panel_tv'></p>
				<select name="over_filter" id="over_filter"
					onchange="over_dis_change();">
				</select>

				<div data-role="collapsible" data-collapsed="false">
					<h4 id='over_point_tv'></h4>
					<ul data-role="listview" id="over_list">
					</ul>
				</div>
			</div>

			<div id="stop_panel">
				<p id='stop_panel_tv'></p>
				<select name="stop_filter" id="stop_filter"
					onchange="stop_dis_change();">
				</select>

				<div data-role="collapsible" data-collapsed="false">
					<h4 id='stop_point_tv'></h4>
					<ul data-role="listview" id="stop_list">
					</ul>
				</div>
			</div>

			<div id="mileage_panel">

				<span class="username" id='start_time_tv'></span> <input
					id="start_time_mileage" type="date" value="1982-12-12" /> <span
					class="username" id='end_time_tv'></span> <input
					id="end_time_mileage" type="date" value="1982-12-12" class="mypw" />
				<input id="lc_playhis_tv" name="playhis" type="button"
					data-corners="false" onclick="licheng_serach();" />

				<div class="ui-grid-a" id="mileagelist"></div>
			</div>

			<input type="button" onClick="location.href='#'" id='back_tv'
				value="返回" />
		</div>
	</div>




</body>
<script type="text/javascript">

	
	lsel=0;
	
	document.getElementById("round_tv").innerText = round_tv[lsel];
	document.getElementById("round_time_tv").innerText = round_time_tv[lsel];
	document.getElementById("round_set_tv").innerText = round_set_tv[lsel];
	document.getElementById("round_start_tv").innerText = round_start_tv[lsel];
	document.getElementById("round_end_tv").innerText = round_end_tv[lsel];
	document.getElementById("round_size_tv").innerText = round_size_tv[lsel];
	document.getElementById("round_center_tv").innerText = round_center_tv[lsel];
	$("#center_chose_tv").val(center_chose_tv[lsel]);
	document.getElementById("data_tv").innerText = data_tv[lsel];

	document.getElementById("range_tv").innerText = range_tv[lsel];
	document.getElementById("play_trr_tv").innerText = play_trr_tv[lsel];
	document.getElementById("paly_center_tv").innerText = paly_center_tv[lsel];

	$("#playhis_tv").val(playhis_tv[lsel]);
	

	document.getElementById("carlist_tv").innerText = carlist_tv[lsel];
	
	document.getElementById("history_search_tv").innerText = history_search_tv[lsel];

	$("#playbtn").val(playbtn_tv[lsel]);
	document.getElementById("over_btn").innerText = over_btn[lsel];

	document.getElementById("stop_btn").innerText = stop_btn[lsel];
	document.getElementById("mileage_btn").innerText = mileage_btn[lsel];

	document.getElementById("over_panel_tv").innerText = over_panel_tv[lsel];
	document.getElementById("over_point_tv").innerText = over_point_tv[lsel];
	var obj_over = document.getElementById('over_filter');
	obj_over.options.add(new Option(over_filter_tv[lsel], "0")); //这个兼容IE与firefox 
	obj_over.options.add(new Option(over_5_tv[lsel], "5")); //这个兼容IE与firefox 
	obj_over.options.add(new Option(over_10_tv[lsel], "10")); //这个兼容IE与firefox 
	obj_over.options.add(new Option(over_20_tv[lsel], "20")); //这个兼容IE与firefox 
	obj_over.options.add(new Option(over_30_tv[lsel], "30")); //这个兼容IE与firefox 
	obj_over.options.add(new Option(over_40_tv[lsel], "40")); //这个兼容IE与firefox 

	document.getElementById("stop_panel_tv").innerText = stop_panel_tv[lsel];
	document.getElementById("stop_point_tv").innerText = stop_point_tv[lsel];
	var obj_stop = document.getElementById('stop_filter');
	obj_stop.options.add(new Option(stop_filter_tv[lsel], "0")); //这个兼容IE与firefox 
	obj_stop.options.add(new Option(stop_5_tv[lsel], "5")); //这个兼容IE与firefox 
	obj_stop.options.add(new Option(stop_10_tv[lsel], "10")); //这个兼容IE与firefox 
	obj_stop.options.add(new Option(stop_30_tv[lsel], "30")); //这个兼容IE与firefox 
	obj_stop.options.add(new Option(stop_60_tv[lsel], "60")); //这个兼容IE与firefox 
	obj_stop.options.add(new Option(stop_360_tv[lsel], "360")); //这个兼容IE与firefox 
	obj_stop.options.add(new Option(stop_720_tv[lsel], "720")); //这个兼容IE与firefox 

	$("#lc_playhis_tv").val(playhis_tv[lsel]);
	document.getElementById("start_time_tv").innerText = start_time_tv[lsel];
	document.getElementById("end_time_tv").innerText = end_time_tv[lsel];
	$("#back_tv").val(back_tv[lsel]);
	
	
	
		//页面初始化
	var h = document.body.clientHeight
			- document.getElementById("head").offsetHeight
			- document.getElementById("foot").offsetHeight - 45 - 104 + 30;
	document.getElementById("map").style.height = h + "px";
	
	//服务器数据
	var objs;

	//轨迹点
	var pointObjs;

	//轨迹线数组
	var flightPath;

	//小车点
	var car_marker = [];

	//围栏圈
	var cityCircle = [];

	//超速点
	var over_marker;
	//停车点
	var stop_marker;

	var infowindow_text = new Array()
	var infowindow = new google.maps.InfoWindow();
	

	//普通车
	var html = "<table style='font-size:14px;white-space:normal;width=50px;overflow:hidden;' >"
			+ "<tr><td colspan=2><b>"+device_tv[lsel]+"ID:</b>{0}</td></tr>"
			+ "<tr><td colspan=2><b>"+device_name_tv[lsel]+":</b>{1}</td></tr>"
			+ "<tr><td>{6}</td></tr>"
			+ "<tr><td colspan=2><hr/></td></tr>"
			+ "<tr><td nowrap><b>"+time_tv[lsel]+"</b>{2}</td></tr>"
			+ "<tr><td nowrap><b>"+speed_tv[lsel]+"</b>{3}</td></tr>"
			+ "<tr><td nowrap><b>"+location_tv[lsel]+"</b>{4}</td></tr>"
			+ "<tr><td colspan=2>{5}</td></tr>" + "";

	//微信定位信息
	var wechat_latitude;
	var wechat_longitude;
	var wechat_speed;
	var wechat_accuracy;

	//画线
	var linkPath;

	var tempLine;

	var linecor = "#00FF00";

	var temp_gps;

	var carpoi;
	var mypoi;


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

	function draw_loc_line(poi_lat, poi_lon) {
		carpoi = {
			lat : Number(poi_lat),
			lng : Number(poi_lon)
		};
		mypoi = {
			lat : Number(wechat_latitude),
			lng : Number(wechat_longitude)
		};
		calculateAndDisplayRoute(directionsService, directionsDisplay);
	}

	function calculateAndDisplayRoute(directionsService, directionsDisplay) {
		var start = mypoi;
		var end = carpoi;
		directionsDisplay.setMap(map);
		directionsService.route({
			origin : start,
			destination : end,
			travelMode : google.maps.TravelMode.DRIVING
		}, function(response, status) {
			if (status === google.maps.DirectionsStatus.OK) {
				directionsDisplay.setDirections(response);
			} else {
				window.alert('Directions request failed due to ' + status);
			}
		});
	}

	//var isfirst_change=0;

	//左侧菜单选择变化监听
	function carselchange(type) {
		if (type == 1) {

			$("#did2").val($("#did").val());
			$("#did2").selectmenu("refresh");
		} else {
			$("#did").val($("#did2").val());
			$("#did").selectmenu("refresh");
		}
		
		
			
			$.get("../ToolsServlet?action=changeSelect&weid="
					+ GetQueryString("weid") + "&did=" + $("#did").val(),
					function(data, status) {
					});
		

		/* 		if(isfirst_change==0)
		 isfirst_change=1;
		 else
		 map.setCenter(car_marker[$("#did ").get(0).selectedIndex].getPosition()); */

		map.setZoom(15);

		//还原全部图片
		for (var i = 0; i < car_marker.length; i++)
			if (car_marker[i] != null)
				car_marker[i].setIcon('images/carundefined.gif');
		//设置选中图片
		if (car_marker[$("#did ").get(0).selectedIndex] != null) {
			car_marker[$("#did ").get(0).selectedIndex]
					.setIcon('images/carundefined_selected.gif');

			infowindow
					.setContent(infowindow_text[car_marker[$("#did ").get(0).selectedIndex].zIndex]);
			infowindow.open(map, car_marker[$("#did ").get(0).selectedIndex]);

			clearInterval(caraction);
			if (flightPath != undefined)
				for (var i = 0; i < flightPath.length; i++)
					flightPath[i].setMap(null);
			//清空线和点
			pointObjs = [];
			flightPath = [];
			if (objs.length > 0) {
				$("#carstates").empty().trigger("create");
				$("#carstates").append(
						"<li>" + objs[$("#did ").get(0).selectedIndex].lasttime+objs[$("#did ").get(0).selectedIndex].status
								+ "</li>").trigger("create");
			/* 	$("#carstates").append(
						"<li>" + 
								+ "</li>").trigger("create"); */
					$("#carstates").append(
						"<li>" +"平台服务结束日期"
								+ "</li>").trigger("create");			
					$("#carstates").append(
						"<li>" + objs[$("#did ").get(0).selectedIndex].service_tinme
								+ "</li>").trigger("create");				
								
								

				$("#carstates").listview("refresh");

			} else
				alert(nolock_tip_tv[lsel]);

			$("#mileagelist").html("");

			if (over_marker != undefined)
				for (var i = 0; i < over_marker.length; i++)
					over_marker[i].setMap(null);
			if (stop_marker != undefined)
				for (var i = 0; i < stop_marker.length; i++)
					stop_marker[i].setMap(null);
			over_marker = [];
			stop_marker = [];

			$("#stop_list").empty().trigger("create");
			$("#over_list").empty().trigger("create");

			$("#stop_list").append("<li>"+null_msg_tv[lsel]+"</li>").trigger("create");
			//$("#stop_list").listview("refresh");

			$("#over_list").append("<li>"+null_msg_tv[lsel]+"</li>").trigger("create");
			//$("#over_list").listview("refresh");

			$("#roundlistview").empty().trigger("create");

			document.getElementById("round_time_tv").innerHTML = "";
			document.getElementById("round_poi").innerHTML = "";

			var strs = new Array(); //定义一数组 
			strs = objs[$("#did ").get(0).selectedIndex].fence.split(","); //字符分割 
			if (strs != "undefined" && strs.length > 0
					&& objs[$("#did ").get(0).selectedIndex].fence != "NULL"
					&& strs != "") {

				strs = objs[$("#did ").get(0).selectedIndex].fence.split(","); //字符分割 

				$("#roundlistview")
						.append(
								"<li><a href='#'><h3>"+my_round_tv[lsel]+"</h3><p>"
										+ strs[0]
										+ ","
										+ strs[1]
										+ "</p><p>"+radius_tv[lsel]
										+ strs[2]
										+ "KM</p></a><a id='raoundrail_id'  onclick='removeroundrail()' data-rel='dialog' data-transition='pop' data-icon='delete'>Download Browser</a></li>")
						.trigger("create");
				$("#roundlistview").listview("refresh");

				document.getElementById("round_time_tv").innerHTML =round_start_tv[lsel]+"<br/>"
						+ strs[3].split("-")[0]+"<br/>"
						+ round_end_tv[lsel]+"<br/>"
						+ strs[3].split("-")[1];

				var latLng = new google.maps.LatLng(Number(strs[0]),
						Number(strs[1]));

				geocoder
						.geocode(
								{
									latLng : latLng
								},
								function(responses) {
									if (responses && responses.length > 0) {
										document.getElementById("round_poi").innerHTML = responses[0].formatted_address;
									} else {
										document.getElementById("round_poi").innerHTML = place_nofind_tv[lsel];
									}
								});

			}
			map.setCenter(car_marker[$("#did ").get(0).selectedIndex]
					.getPosition());
		}
	}

	var geocoder = new google.maps.Geocoder();
	function geocodePosition(pos) {
		geocoder.geocode({
			latLng : pos
		}, function(responses) {
			if (responses && responses.length > 0) {
				//document.getElementById("round_poi").innerHTML = responses[0].formatted_address;
				return responses[0].formatted_address;
			} else {
				//document.getElementById("round_poi").innerHTML ="无法确定地址在这个位置";
				return place_nofind_tv[lsel];
			}
		});
	}

	//右侧菜单选择变化监听
	function carselchange2() {
		carselchange(2);

	}

	//左侧菜单初始化	
	function initLeftPanel(objs) {
		if (objs.length > 0)
			for (var i = 0; i < objs.length; i++) {
				$("#did").append(
						$("<option>").val(objs[i].no).text(objs[i].name));
				$("#did2").append(
						$("<option>").val(objs[i].no).text(objs[i].name));
			}
		else {
			$("#did").append("<option value='0'>"+nolock_tip_tv[lsel]+"</option>").trigger(
					"create");
			$("#did2").append("<option value='0'>"+nolock_tip_tv[lsel]+"</option>").trigger(
					"create");
		}

		//初始化车辆和围栏
		initCarPoint();
		initRoundPanel();

		$("#did option:eq(0)").attr("selected", true); //设置属性selected
		$("#did2 option:eq(0)").attr("selected", true); //设置属性selected

		$("#did").selectmenu("refresh");
		$("#did2").selectmenu("refresh");

		var clock_mileage = new Date().getFullYear() + "-";
		if (eval(new Date().getMonth() + 1) < 10)
			clock_mileage += "0";
		clock_mileage += eval(new Date().getMonth() + 1) + "-";

		if (new Date().getDate() < 10)
			clock_mileage += "0";

		$("#start_time_mileage").val(GetDateStr(-7));
		$("#end_time_mileage").val(clock_mileage + new Date().getDate());

	}

	//右侧轨迹菜单初始化
	function initRightPanel() {
		//初始化起始时间
		var clock = new Date().format("yyyy-MM-ddThh:mm");
		$("#start_time").val(clock);
		//初始化日
		for (var i = 0; i <= 6; i++)
			$("#day_sel").append("<option value='"+i+"'>" + i +day_tv[lsel] +"</option>")
					.trigger("create");
		//初始化时
		for (var i = 0; i <= 23; i++)
			$("#hours_sel").append("<option value='"+i+"'>" + i + hour_tv[lsel]+"</option>")
					.trigger("create");

		$("#day_sel option:eq(1)").attr("selected", true); //设置属性selected
		$("#hours_sel option:eq(0)").attr("selected", true); //设置属性selected
		$("#day_sel").selectmenu("refresh");
		$("#hours_sel").selectmenu("refresh");
	}

	//围栏菜单初始化
	function initRoundPanel() {
		//显示围栏列表
		for (var i = 0; i < objs.length; i++) {
			//切割fence
			var strs = new Array(); //定义一数组 
			strs = objs[i].fence.split(","); //字符分割 
			if (strs != "undefined" && strs.length > 0
					&& objs[i].fence != "NULL" && strs != "") {
				drawroundrail(new google.maps.LatLng(strs[0], strs[1]),
						strs[2], cityCircle.length);
			} else
				cityCircle[cityCircle.length] = "";
		}

	}

	//格式化HTML对象
	String.format = function() {
		if (arguments.length == 0) {
			return null;
		}
		var str = arguments[0];
		for (var i = 1; i < arguments.length; i++) {
			var re = new RegExp('\\{' + (i - 1) + '\\}', 'gm');
			str = str.replace(re, arguments[i]);
		}
		return str;
	}

	function goloc() {
		window.location.href = "./locator.jsp?did=" + $("#did").val();
	}

	function createMarker(i, t) {
		var car_marker_now = new google.maps.Marker({
			position : new google.maps.LatLng(objs[i].gpsPosiLat,
					objs[i].gpsPosiLon),
			icon : 'images/car' + objs[0].dir + '.gif',
			animation : google.maps.Animation.DROP,
			zIndex : i,
			map : map
		});
		car_marker[i] = car_marker_now;
		car_marker[i].setMap(map);

		var bat_str = "";
		var lead_btn = "";
		//判断电压
		if (objs[i].type == 4) {
			bat_str = "<b>"+bat_tv[lsel]+":</b>" + objs[i].bat + "<b>"+elec_tv[lsel]+":</b>" + objs[i].elec;
			/* lead_btn="<select id='loc_time'><option value='360'>30分钟</option><option value='720'>1小时</option><option value='4320'>6小时</option><option value='8640'>12小时</option></select><button style='margin-left: 20px;padding: 10px' id='loc_btn"+i+"' onclick='loc_device("+objs[i].gpsPosiLat+","+objs[i].gpsPosiLon+","+i+")'>开始追踪</button>" */
			lead_btn = "<button style='margin-left: 20px;padding: 10px' id='loc_btn"
					+ i + "' onclick='goloc()'>"+lock_device[lsel]+"</button>"

		}
		var time_temp = new Date(objs[i].time * 1000).format("MM/dd hh:mm");

		/* 	  	infowindow_text[i]= String.format(html, objs[i].no,objs[i].name, time_temp, objs[i].speed,
					objs[i].gpsPosiLat, objs[i].gpsPosiLon,bat_str,lead_btn); */

		var latLng = new google.maps.LatLng(Number(objs[i].gpsPosiLat),
				Number(objs[i].gpsPosiLon));

		geocoder.geocode({
			latLng : latLng
		}, function(responses) {
			if (responses && responses.length > 0) {
				infowindow_text[i] = String.format(html, objs[i].no,
						objs[i].name, time_temp, objs[i].speed,
						responses[0].formatted_address, bat_str, lead_btn);
			} else {
				infowindow_text[i] = String.format(html, objs[i].no,
						objs[i].name, time_temp, objs[i].speed, place_nofind_tv[lsel],
						bat_str, lead_btn);
			}

			google.maps.event.addListener(car_marker_now, 'click', function() {
				infowindow.setContent(infowindow_text[car_marker_now.zIndex]);
				infowindow.open(map, car_marker_now);
			});
			carselchange(0);
		});

	}

	//初始化车辆位置
	function initCarPoint() {
		if (objs.length == 0)
			alert(nolock_tip_tv[lsel]);
		for (var i = 0; i < objs.length; i++) {
			if (objs[i].status == nolocation_tip_tv[lsel])
				popup("" + objs[i].no +nolocation_tip_tv[lsel]);
			else
				createMarker(i);
		}

		//map.setZoom(15);
	}

	//时间格式化工具
	Date.prototype.format = function(format) {
		var o = {
			"M+" : this.getMonth() + 1, //month 
			"d+" : this.getDate(), //day 
			"h+" : this.getHours(), //hour 
			"m+" : this.getMinutes(), //minute 
			"s+" : this.getSeconds(), //second 
			"q+" : Math.floor((this.getMonth() + 3) / 3), //quarter 
			"S" : this.getMilliseconds()
		//millisecond 
		}

		if (/(y+)/.test(format)) {
			format = format.replace(RegExp.$1, (this.getFullYear() + "")
					.substr(4 - RegExp.$1.length));
		}

		for ( var k in o) {
			if (new RegExp("(" + k + ")").test(format)) {
				format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
						: ("00" + o[k]).substr(("" + o[k]).length));
			}
		}
		return format;
	}

	//时间格式化工具
	function fmtDate(dt) {
		return dt.getFullYear() + "/" + eval(dt.getMonth() + 1) + "/"
				+ dt.getDate() + " " + dt.getHours() + ":" + dt.getMinutes()
				+ ":" + dt.getSeconds();
	}

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

	var stop_image = {
		url : 'images/stop_dot.png',
		// This marker is 20 pixels wide by 32 pixels tall.
		size : new google.maps.Size(32, 32),
		// The origin for this image is 0,0.
		origin : new google.maps.Point(0, 0),
		// The anchor for this image is the base of the flagpole at 0,32.
		anchor : new google.maps.Point(0, 32)
	};

	var over_image = {
		url : 'images/over_dot.png',
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

	function location_over(i) {
		location.href = "#";
		map.setCenter(over_marker[i].getPosition());
	}

	function upadtepointslist() {
		add_stop_list(0);
		add_over_list(0);
	}

	function add_stop_list(filter_num) {

		$("#stop_list").empty().trigger("create");
		if (stop_marker.length == 0)
			$("#stop_list").append("<li>"+null_msg_tv[lsel]+"</li>").trigger("create");
		else {
			var stop_str = "";
			for (var i = 0; i < stop_marker.length; i++) {
				if (stop_marker[i].getCursor() > filter_num)
					stop_str += " <li><a href='#' onclick='location_stop(" + i
							+ ")'>" + (i + 1) + "  "
							+ stop_marker[i].getTitle() + "</a ></li>";
			}
			$("#stop_list").append(stop_str).trigger("create");
			$("#stop_list").listview().listview('refresh');

		}

	}

	function location_stop(i) {
		location.href = "#";
		map.setCenter(stop_marker[i].getPosition());
	}

	function add_over_list(filter_num) {

		$("#over_list").empty().trigger("create");

		if (over_list.length == 0)
			$("#over_list").append("<li>"+null_msg_tv[lsel]+"</li>").trigger("create");
		else {
			var over_str = "";
			for (var i = 0; i < over_marker.length; i++) {
				if (over_marker[i].getCursor() > filter_num)
					over_str += " <li><a   href='#pageone'    onclick='location_over("
							+ i
							+ ")'>"
							+ (i + 1)
							+ "  "
							+ over_marker[i].getTitle() + "</a ></li>";

			}
			$("#over_list").append(over_str).trigger("create");
			//	$("#over_list").listview("refresh");
			$("#over_list").listview().listview('refresh');
		}
	}

	//画轨迹
	function drawline() {
		//轨迹点
		//var pointarray = [];
		//轨迹颜色
		var linecor;

		if (over_marker != undefined)
			for (var i = 0; i < over_marker.length; i++)
				over_marker[i].setMap(null);
		if (stop_marker != undefined)
			for (var i = 0; i < stop_marker.length; i++)
				stop_marker[i].setMap(null);
		over_marker = [];
		stop_marker = [];

		//从第二个点开始
		for (var i = 1; i < pointObjs[0].length; i++) {
			var tempLine = [
					new google.maps.LatLng(pointObjs[0][i - 1].lat,
							pointObjs[0][i - 1].lng),
					new google.maps.LatLng(pointObjs[0][i].lat, pointObjs[0][i].lng) ];

			//轨迹颜色
			if (pointObjs[0][i].overSpeed > 0 && pointObjs[0][i - 1].overSpeed > 0) {
				linecor = "#FF0000";
				//超速点
				over_marker[over_marker.length] = new google.maps.Marker({
					position : new google.maps.LatLng(pointObjs[0][i].lat,
							pointObjs[0][i].lng),
					map : map,
					icon : over_image,
					shape : shape,
					title : over_point_tv[lsel]+"<br>"
							+ limit_speed_tv[lsel]
							+ pointObjs[0][i].limitSpeed
							+ "\n"+over_speed_tv[lsel]
							+ pointObjs[0][i].overSpeed
							+ "<br>"
							+ new Date(pointObjs[0][i].time * 1000)
									.format("yyyy/MM/dd/ hh:mm:ss"),
					cursor : "" + pointObjs[0][i].overSpeed,
					zIndex : i
				});
			} else
				linecor = "#0000FF";

			if (pointObjs[0][i].lbs == 1) {
				linecor = "#00FFFF";
			}

			//5分钟内画线
			if (Math.abs(Number(pointObjs[0][i].time)
					- Number(pointObjs[0][i - 1].time)) < 300) {
				flightPath[flightPath.length] = new google.maps.Polyline({
					map : map,
					path : tempLine,
					strokeColor : linecor,
					strokeOpacity : 1.0,
					visible : true,
					strokeWeight : 2
				});
				flightPath[flightPath.length - 1].setMap(map);
			} 
/* 			else {

				var total = Math.abs(pointObjs[i].time - pointObjs[i - 1].time);

				var day = parseInt(total / (24 * 60 * 60));//计算整数天数
				var afterDay = total - day * 24 * 60 * 60;//取得算出天数后剩余的秒数
				var hour = parseInt(afterDay / (60 * 60));//计算整数小时数
				var afterHour = total - day * 24 * 60 * 60 - hour * 60 * 60;//取得算出小时数后剩余的秒数
				var min = parseInt(afterHour / 60);//计算整数分
				var afterMin = total - day * 24 * 60 * 60 - hour * 60 * 60
						- min * 60;//取得算出分后剩余的秒数

				stop_marker[stop_marker.length] = new google.maps.Marker({
					position : new google.maps.LatLng(pointObjs[i].lat,
							pointObjs[i].lng),
					map : map,
					icon : stop_image,
					shape : shape,
					title : stop_point_tv[lsel]+"<br>"
							+ new Date(pointObjs[i - 1].time * 1000)
									.format("yyyy年MM月dd日 hh:mm:ss")
							+ "<br>"+stop_time_tv[lsel]+":" + day + day_tv[lsel] + hour + hour_tv[lsel] + min + min_tv[lsel]
							+ afterMin + second_tv[lsel],
					cursor : "" + total / 60,
					zIndex : i
				});
			} */

		}
		
		for (var i = 0; i < pointObjs[1].length; i++) 
		{
				 var total = Math.abs(pointObjs[1][i].stopTime);
				var day = parseInt(total / (24 * 60 * 60));//计算整数天数
				var afterDay = total - day * 24 * 60 * 60;//取得算出天数后剩余的秒数
				var hour = parseInt(afterDay / (60 * 60));//计算整数小时数
				var afterHour = total - day * 24 * 60 * 60 - hour * 60 * 60;//取得算出小时数后剩余的秒数
				var min = parseInt(afterHour / 60);//计算整数分
				var afterMin = total - day * 24 * 60 * 60 - hour * 60 * 60
						- min * 60;//取得算出分后剩余的秒数 

				stop_marker[stop_marker.length] = new google.maps.Marker({
					position : new google.maps.LatLng(pointObjs[1][i].lat,
							pointObjs[1][i].lng),
					map : map,
					icon : stop_image,
					shape : shape,
					title : stop_point_tv[lsel]+"<br>"
							+ new Date(pointObjs[1][i].time * 1000)
									.format("yyyy年MM月dd日 hh:mm:ss")
							+ "<br>"+stop_time_tv[lsel]+":" + day + day_tv[lsel] + hour + hour_tv[lsel] + min + min_tv[lsel]
							+ afterMin + second_tv[lsel],
					cursor : "" + total / 60,
					zIndex : i
				});
			
		}

		upadtepointslist();

	}

	//轨迹查询
	function searchbtn() {
		//清空残留轨迹
		for (var i = 0; i < flightPath.length; i++)
			flightPath[i].setMap(null);

		$("#panel-right").panel("close");

		if ($("#did").val() == 0)
			popup(nolock_tip_tv[lsel]);
		else
			popup(wait_tv[lsel]);

		//计算开始时间和结束时间
		var edate = ($("#start_time").val()).replace("-", "/");
		edate = edate.replace("-", "/");
		edate = edate.replace("T", " ");

		var date = new Date(edate);
		date.setDate(eval(date.getDate() - parseInt($("#day_sel").val())));
		date
				.setHours((eval(date.getHours()
						- parseInt($("#hours_sel").val()))));

		var sdate = fmtDate(date);

		$.get("../carLocationServlet?action=getHistroy&didhis="
				+ $("#did").val() + "&start_time=" + sdate + "&end_time="
				+ edate, function(data, status) {
			pointObjs = eval(data);
			if (pointObjs == null) {
				popup(service_end_tv[lsel]);
				return;
			} else if (pointObjs[0].length == 0)
			
				popup(no_find_history_tv[lsel]);
			else if (pointObjs[0].length == 1) {
				map.setZoom(15);
				map.setCenter(new google.maps.LatLng(pointObjs[0][0].lat,
						pointObjs[0][0].lng));
				
				popup(only_find_one_tv[lsel]);
			} else {
				
				nowPoi=pointObjs[0].length - 1;
			
				map.setZoom(15);
				map.setCenter(new google.maps.LatLng(pointObjs[0][0].lat,
						pointObjs[0][0].lng));
				drawline();
			}

		});
	}

	var caraction;
	//移动轨迹点
	function resetMkPoint(i) {
		if (i >0) {
			caraction = setTimeout(function() {
				i--;
				car_marker[$("#did ").get(0).selectedIndex]
						.setPosition(new google.maps.LatLng(pointObjs[0][i].lat,
								pointObjs[0][i].lng));
								
		
						car_marker[$("#did ").get(0).selectedIndex].setIcon('images/car' + pointObjs[0][i].dir + '.gif');
						
						
				resetMkPoint(i);
				nowPoi = i;

				if ($("#switch").val() == "on")
					map.setCenter(car_marker[$("#did ").get(0).selectedIndex]
							.getPosition());

			}, $("#xila").val() * 1000);
		} else {
			
			nowPoi = pointObjs[0].length - 1;
			isplay = false;

			clearInterval(caraction);
			$("#playbtn").val(playbtn_tv[lsel]).button("refresh");

		}

	}

	var isplay = false;
	var nowPoi = 0;
	//播放轨迹
	function playbtn() {
		if (pointObjs[0].length > 0) {
			if (isplay == true) {
				isplay = false;
				clearInterval(caraction);
				$("#playbtn").val(playbtn_tv[lsel]).button("refresh");
			} else {
				isplay = true;
				$("#playbtn").val(stopbtn_tv[lsel]).button("refresh");
				resetMkPoint(nowPoi);
			}
		} else
			popup(need_do_history_tv[lsel]);
	}

	//添加围栏
	function addroundrail() {

		for (var i = 0; i < cityCircle.length; i++) {
			if (cityCircle[i] != "")
				cityCircle[i].setMap(null);
		}

		$("#panel-left").panel("close");
		map.addListener('click', function(e) {
			placeMarkerAndPanTo(e.latLng, map);
		});
	}

	//上传围栏点
	function placeMarkerAndPanTo(latLng, map) {

		$.get("../carLocationServlet?action=addroundrail&didhis="
				+ $("#did").val() + "&latLng=" + latLng + "&rail="
				+ $("#round_size ").val() + "&roundtime="
				+ $("#roundstime").val() + "-" + $("#roundetime").val(),
				function(data, status) {
					//刷新页面
					location.reload();
				});
	}

	//显示围栏点
	function drawroundrail(latlng, rail, poi) {
		//添加圈圈
		cityCircle[poi] = new google.maps.Circle({
			strokeColor : '#FF0000',
			strokeOpacity : 0.8,
			strokeWeight : 2,
			fillColor : '#FF0000',
			fillOpacity : 0.35,
			map : map,
			center : latlng,
			radius : rail * 1000
		});
	}

	//删除围栏
	function removeroundrail() {
		$.get("../carLocationServlet?action=removeroundrail&didhis="
				+ $("#did").val(), function(data, status) {
			//刷新页面
			//location.reload();
			cityCircle[$("#did ").get(0).selectedIndex].setMap(null);
			$("#roundlistview").empty().trigger("create");
		});

	}

	function searche_roads() {
		if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} else {// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

				var jsons = eval(xmlhttp.responseText);
				alert("tt");
			}
		};
		xmlhttp.open("GET", "../ToolsServlet?action=getRoads&origin="
				+ "24.648516,118.15641" + "&destination="
				+ "24.648212,118.156096", true);
		xmlhttp.send();
	}

	var licheng_jsonobj = "";
	//查询里程
	function updatelicheng() {

		$("#mileagelist").empty().trigger("create");

		if (licheng_jsonobj == null || licheng_jsonobj.length == 0) {
			popup(no_find_lc_tv[lsel]);
		}
		if (licheng_jsonobj.length > 0) {
			$("#mileagelist")
					.html(
							"<div class='ui-block-a' style='background-color: lightgray;padding: 25px;border: 1px solid black;height: 60px;text-align: center;'><span>"
									+ data_tv[lsel]
									+ "</span></div><div class='ui-block-b' style='background-color: lightgray;padding: 25px;border: 1px solid black;height: 60px;text-align: center;'><span>"
									+ "KM" + "</span></div>");
			var str = "";
			for (var i = 0; i < licheng_jsonobj.length; i++) {

				if (i == licheng_jsonobj.length - 1)
					str += "<div class='ui-block-a' style='padding: 25px;border: 1px solid black;height: 60px;text-align: center;'><span>"
							+ licheng_jsonobj[i].time
							+ "</span></div><div class='ui-block-b' style='padding: 25px;border: 1px solid black;height: 60px;text-align: center;'><span>"
							+ licheng_jsonobj[i].miles + "</span></div>";
				else
					str += "<div class='ui-block-a' style='padding: 25px;border: 1px solid black;height: 60px;text-align: center;'><span>"
							+ licheng_jsonobj[i].time
							+ "</span></div><div class='ui-block-b' style='padding: 25px;border: 1px solid black;height: 60px;text-align: center;'><span>"
							+ licheng_jsonobj[i].miles + "</span></div>";
			}
			$("#mileagelist").append(str).trigger("create");

		}
	}




	function licheng_serach() {
		$("#mileagelist").html("");
		$.get("../carLocationServlet?action=searchMileage&didhis="
				+ $("#did").val() + "&start_time="
				+ $("#start_time_mileage").val() + "&end_time="
				+ $("#end_time_mileage").val(), function(result) {
			licheng_jsonobj = eval(result);
			updatelicheng();
		});
	}

	function over_dis_change() {
		$("#over_list").empty().trigger("create");
		add_over_list(Number($("#over_filter").val()));
		$("#over_list").listview("refresh");
	}

	function stop_dis_change() {
		$("#stop_list").empty().trigger("create");
		add_stop_list(Number($("#stop_filter").val()));
		$("#stop_list").listview("refresh");
	}

	function turn_page() {
		location.href = "#page_point_over"
		$("#over_btn").addClass("ui-btn-active ui-state-persist");
		$("#stop_panel").hide();
		$("#mileage_panel").hide();
		$("#over_panel").show();
	}

	$("#over_btn").click(function() {
		$("#over_panel").show();
		$("#stop_panel").hide();
		$("#mileage_panel").hide();
	});
	$("#stop_btn").click(function() {
		$("#over_panel").hide();
		$("#stop_panel").show();
		$("#mileage_panel").hide();
	});
	$("#mileage_btn").click(function() {
		$("#over_panel").hide();
		$("#stop_panel").hide();
		$("#mileage_panel").show();
	});

	initMap();//创建和初始化地图

	//请求服务器数据
	$.get("../carLocationServlet?action=getCarOnMap&weid="
			+ GetQueryString("weid"), function(data, status) {
		objs = eval(data);
		initLeftPanel(objs);
		initRightPanel();

	});
	
	
	
</script>

</html>
