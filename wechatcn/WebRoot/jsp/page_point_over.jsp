<!DOCTYPE html>
<html>
<head>
 <script type="text/javascript" src="../jsp/js/head.js"></script>
</head>

<!-- 超速点页面 -->
<div data-role="page" id="page_point_over">
	<div data-role="header">


		<div data-role="navbar">
			<ul>
				<li><a href="#" id="over_btn" data-icon="alert">超速点</a></li>
				<li><a href="#" data-icon="minus" id="stop_btn">停车点</a></li>
				<li><a href="#" data-icon="search" id="mileage_btn">里程查询</a></li>
			</ul>
		</div>

	</div>

	<div data-role="content">


		<div id="over_panel">
			<p>超速选择</p>
			<select name="over_filter" id="over_filter"
				onchange="over_dis_change();">
				<option value="0">超速过滤选择</option>
				<option value="5">超速5以上</option>
				<option value="10">超速10以上</option>
				<option value="20">超速20以上</option>
				<option value="30">超速30以上</option>
				<option value="40">超速40以上</option>
			</select>

			<div data-role="collapsible" data-collapsed="false">
				<h4>超速点</h4>
				<ul data-role="listview" id="over_list">
				</ul>
			</div>
		</div>

		<div id="stop_panel">
			<p>停车选择</p>
			<select name="stop_filter" id="stop_filter"
				onchange="stop_dis_change();">
				<option value="0">停车过滤选择</option>
				<option value="5">停车5分钟以上</option>
				<option value="10">停车10分钟以上</option>
				<option value="30">停车30分钟以上</option>
				<option value="60">停车1小时以上</option>
				<option value="360">停车6小时以上</option>
				<option value="720">停车12小时以上</option>
			</select>

			<div data-role="collapsible" data-collapsed="false">
				<h4>停车点</h4>
				<ul data-role="listview" id="stop_list">
				</ul>
			</div>
		</div>

		<div id="mileage_panel">
			<select name="did" id="did" onchange="selchange();"
				data-corners="false">

				<option value="1010104410161161">闽DFS325 离线</option>

				<option value="1140104021591096">闽DFS325 离线</option>

			</select> <span class="username">开始时间:</span> <input id="start_time_mileage"
				type="date" value="1982-12-12" /> <span class="username">结束时间:</span>
			<input id="end_time_mileage" type="date" value="1982-12-12"
				class="mypw" /> <input id="playhis" name="playhis" type="button"
				value="确认" data-corners="false" onclick="serach();" />

			<div class="ui-grid-a" id="mileagelist"></div>
		</div>
		<a type="button" data-inline="true" href="#pageone">返回地图</a>

	</div>
</div>

</body>
</html>