
<%@ page language="java"
	import="java.util.*,com.ruiyi.wechat.string.Language"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>


<html>
	<head>


		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">

		<meta name="viewport"
			content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.5, user-scalable=no">
 <script type="text/javascript" src="../jsp/js/head.js"></script>

	</head>



	<script type="text/javascript">
	//"0,weifer_TPLINK,,1,2,0;"
</script>


	<body">

		<div data-role="content">
			<ul data-role="listview" data-divider-theme="" data-inset="true">
				<li data-theme="c" data-icon="false" id="wifi_ssid">
					<label for="textinput6">
						名称
					</label>
					<input name="" id="textinput6" placeholder=""
						value="weifer_TPLINK " type="text">

				</li>
				<li data-theme="c" data-icon="false" id="wifi_password">
						<label for="textinput6">
						密码
					</label>
					<input name="" id="textinput6" placeholder=""
						value="" type="text" >
				</li>

				<li data-theme="c" data-icon="false" id="wifi_privacy">
					<a href="#wifi_type" data-transition="flip"> 保存方式 </a>
					<span class="ui-li-count" id="a_type"> 加密</span>
					</a>
				</li>

				<li data-theme="c" data-icon="false" id="wifi_signa">
					<a href="#wifi_type" data-transition="flip"> 信号强度 </a>
					<span class="ui-li-count" id="a_type">9 </span>
					</a>
				</li>

				<li data-theme="c" data-icon="false" id="wifi_state">
					<a href="#wifi_type" data-transition="flip">连接状态 </a>
					<span class="ui-li-count" id="a_type">已连接 </span>
					</a>
				</li>

			</ul>
		</div>
	</body>
</html>