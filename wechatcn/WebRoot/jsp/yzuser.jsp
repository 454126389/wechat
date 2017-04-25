<%@ page language="java"
	import="java.util.*,com.ruiyi.wechat.string.Language,com.ruiyi.wechat.model.CarInfo"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>




<html>
<head>
<script type="text/javascript" src="../jsp/js/head.js"></script>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.5, user-scalable=no">
</head>
<body>



	<div data-role="page" id="pageone">
		<div data-role="header">
			<h1>征卡绑定</h1>
		</div>

		<div data-role="content">




			<label></label>
			<div id="locatorbuy">
				<form method="post" action="demoform.asp">
					<div data-role="fieldcontain">
						<label for="fullname">ICCID：</label> <input type="text" id="iccid"
							data-clear-btn="true" placeholder="最后一位英文字母不填" data-inline="true">



						<label for="phone">手机：</label> <input type="tel" id="phone"
							data-clear-btn="true" placeholder="手机号码"> <label
							for="alias">征卡昵称：</label> <input type="text" id="alias"
							data-clear-btn="true" placeholder="流量卡别名">





						<table border="0" align="center">
							<caption>
								<tr style="text-align:right;">
									<td><input type="text" id="code" placeholder="验证码">
									<td><input id="doyz" type="button" data-inline="true"
										value="点击获取验证码" onclick="getCode()">
								</tr>
						</table>






						<center>
							<input type="button" data-inline="true" value="提交"
								onclick="getval()"> <input type="reset"
								data-inline="true" value="重置">
						</center>
				</form>
			</div>



		</div>

		<center>
			<div id="locatorsuc" style="display: none">绑定成功</div>
		</center>
	</div>



</body>


<script language="javascript">
	function checkPhone() {
		var phone = $("#phone").val();
		if (!(/^1[3|4|5|7|8]\d{9}$/.test(phone))) {
			alert("手机号码有误，请重填");
			return false;
		} else
			return true;
	}

	var countdown = 60;

	function getCode() {
		if (checkPhone()) {
			//请求验证码
			$.get(
					"../ToolsServlet?action=send_code&phone="
							+ $("#phone").val(), function(data, status) {
						var objs = eval(data);
						if (objs.success) {
							alert("验证码请求成功");
						}

					});

			settime();
		}

	}

	function settime() {

		if (countdown == 0) {

			$("#doyz").removeAttr("disabled");
			$("#doyz").val("获取验证码").button("refresh");
			countdown = 60;
			return;
		} else {

			$("#doyz").attr("disabled", "true");
			$("#doyz").val("重新发送(" + countdown + ")").button("refresh");

			countdown--;
		}
		setTimeout(function() {
			settime()
		}, 1000)

	}

	function getval() {

		if ($("#iccid").val() == "") {
			alert("ICCID不能为空")
		} else if ($("#phone").val() == "") {
			alert("号码不能为空")
		} else if (!checkPhone()) {
		} else if ($("#alias").val() == "") {
			alert("设备别名不能为空")
		} else if ($("#code").val() == "") {
			alert("验证码不能为空")
		} else {
			//请求验证码

			$
					.get(
							"../ToolsServlet?action=loc_iccid&phone="
									+ $("#phone").val() + "&code="
									+ $("#code").val() + "&alias="
									+ $("#alias").val() + "&iccid="
									+ $("#iccid").val()+"&weid="+GetQueryString("weid"),
							function(data, status) {
								var objs = eval(data);
								if (objs.success) {

									document.getElementById("locatorbuy").style.display = "none";
									document.getElementById("locatorsuc").style.display = "";
									alert("绑定成功" + objs.success);
								} else {
									switch (objs.errorCore) {
									case "10001":
										alert("验证码不正确");
										break;
									case "10002":
										alert("ICCID不存在");
										break;
									case "10003":
										alert("联通服务错误");
										break;
									}

								}
							});
		}
	}
	
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


