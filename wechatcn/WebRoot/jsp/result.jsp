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
<!-- <link rel="stylesheet" href="http://apps.bdimg.com/libs/jquerymobile/1.4.5/jquery.mobile-1.4.5.min.css">
<script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js" type="text/javascript"></script>
<script src="http://apps.bdimg.com/libs/jquerymobile/1.4.5/jquery.mobile-1.4.5.min.js"
	type="text/javascript"></script> -->


<!-- <link rel="stylesheet"
	href="../jsp/jquery.mobile-1.4.5/jquery.mobile-1.4.5.min.css">
<script src="../jsp/js/jquery-1.8.3.min.js"></script>
<script src="../jsp/jquery.mobile-1.4.5/jquery.mobile-1.4.5.min.js"></script> -->

 <script type="text/javascript" src="../jsp/js/head.js"></script>
 
<script src="js/utils/language.js" type="text/javascript"></script>

</head>
<body>

	<div data-role="page" id="pageone">
		<center>
			<img alt="loading" src="../jsp/images/loading.gif">
		</center>

	<!-- 	<center>
			<p>视频或图片无法回传的情况，请更新设备软件版本</p>
		</center> -->

		<select name="did" id="did" "
				data-corners="false"
			onchange="carChoise()">
		</select>

		<center>
			<div data-role="controlgroup" data-type="horizontal" id="type3">

				<input id="getPhoto" href="#anylink" data-role="button"
					onclick="getPhoto()" type="button" /> <input
					id="getVideo" href="#anylink" data-role="button"
					onclick="getVideo()"  type="button" /> <input
					id="pickUp" href="#anylink" data-role="button" onclick="pickUp()"
					 type="button" /> <input id="closeSystem"
					href="#anylink" data-role="button" onclick="closeSystem()"
					" type="button" />

			</div>

			<div data-role="controlgroup" data-type="horizontal" id="type2">
			<input id="getPhoto_loc_shock" href="#anylink" data-role="button"
					onclick="getPhoto_shock()"  type="button" />

				<input id="getPhoto_loc" href="#anylink" data-role="button"
					onclick="getPhoto()"  type="button" /> <input
					id="closeSystem_loc" href="#anylink" data-role="button"
					onclick="closeSystem()"  type="button" />

			</div>

		</center>
	</div>



</body>
<script type="text/javascript">

$("#getPhoto").val(take_pic_tv[lsel]);
$("#getVideo").val(take_video_tv[lsel]);
$("#pickUp").val(take_pick_tv[lsel]);
$("#closeSystem").val(take_shut_tv[lsel]);

$("#getPhoto_loc").val(take_pic_tv[lsel]);
$("#getPhoto_loc_shock").val("报警图片");
$("#closeSystem_loc").val(take_shut_tv[lsel]);

	function carChoise() {
		select($("#did ").get(0).selectedIndex);
			$.get("../ToolsServlet?action=changeSelect&weid="
					+ GetQueryString("weid") + "&did=" + $("#did").val(),
					function(data, status) {
					});
		
	}

	//车机关机
	function closeSystem() {
		if (objs[$("#did ").get(0).selectedIndex].type == 2||objs[$("#did ").get(0).selectedIndex].type ==5) {
			$("#closeSystem_loc").attr("disabled", "true");
			$("#closeSystem_loc").val(wait_tv[lsel]).button("refresh");
			$.get("../ToolsServlet?action=closeSystem_loc&did=" + $("#did").val(),
					function(data, status) {
						alert(code_suc_tip_tv[lsel])
						$("#closeSystem_loc").removeAttr("disabled");
					$("#closeSystem_loc").val("关机").button("refresh");
					});
		} else if (objs[$("#did ").get(0).selectedIndex].type == 3) {
			$("#closeSystem").attr("disabled", "true");
			$("#closeSystem").val(wait_tv[lsel]).button("refresh");
			$.get("../ToolsServlet?action=closeSystem&weid="
					+ GetQueryString("weid") + "&did=" + $("#did").val(),
					function(data, status) {
						alert(code_suc_tip_tv[lsel])
						$("#closeSystem").removeAttr("disabled");
						$("#closeSystem").val("关机").button("refresh");
					});
		}

	}

	var wait_photo = 10;
	var wait_video = 10;
	var wait_close = 10;
	//车机拍照
	function getPhoto() {
		if (objs[$("#did ").get(0).selectedIndex].type == 2||objs[$("#did ").get(0).selectedIndex].type ==5) {
			$("#getPhoto_loc").attr("disabled", "true");
			$("#getPhoto_loc").val(wait_tv[lsel]).button("refresh");
			$.get("../ToolsServlet?action=getPhoto_loc&did=" + $("#did").val(),
					function(data, status) {
						alert(code_suc_tip_tv[lsel])
						$("#getPhoto_loc").removeAttr("disabled");
						$("#getPhoto_loc").val("拍照").button("refresh");
					});
		} else if (objs[$("#did ").get(0).selectedIndex].type == 3) {
			$("#getPhoto").attr("disabled", "true");
			$("#getPhoto").val(wait_tv[lsel]).button("refresh");
			$.get("../ToolsServlet?action=getPhoto&weid="
					+ GetQueryString("weid") + "&did=" + $("#did").val(),
					function(data, status) {
					
					//	var obj=eval(data);
					
						alert(code_suc_tip_tv[lsel])
						$("#getPhoto").removeAttr("disabled");
						$("#getPhoto").val("拍照").button("refresh");
					});
		}

	}
		function getPhoto_shock() {
		if (objs[$("#did ").get(0).selectedIndex].type == 2||objs[$("#did ").get(0).selectedIndex].type ==5) {
			$("#getPhoto_loc_shock").attr("disabled", "true");
			$("#getPhoto_loc_shock").val(wait_tv[lsel]).button("refresh");
			$.get("../ToolsServlet?action=getPhoto_loc_shock&did=" + $("#did").val(),
					function(data, status) {
						alert(code_suc_tip_tv[lsel])
						$("#getPhoto_loc").removeAttr("disabled");
						$("#getPhoto_loc").val("拍照").button("refresh");
					});
		} 

	}
	
	
	
	//车机录像
	function getVideo() {

		$("#getVideo").attr("disabled", "true");
		$("#getVideo").val(wait_tv[lsel]).button("refresh");
		$.get("../ToolsServlet?action=getVideo&weid=" + GetQueryString("weid")
				+ "&did=" + $("#did").val(), function(data, status) {
			alert(code_suc_tip_tv[lsel]);
			$("#getVideo").removeAttr("disabled");
			$("#getVideo").val("录像").button("refresh");
		});

	}

	function pickUp() {

		if ($("#did").val() == 0)
			return;

		location.href = "../jsp/PhonePickup.jsp?weid=" + GetQueryString("weid");

	}

	var objs = new Array(); //定义一数组 

	//请求服务器数据
	$.get("../carLocationServlet?action=getOnlineDevice&weid="
			+ GetQueryString("weid"), function(data, status) {
		objs = eval(data);

		updatelist(objs);

	});

	//采用正则表达式获取地址栏参数
	function GetQueryString(name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
		var r = window.location.search.substr(1).match(reg);
		if (r != null)
			return unescape(r[2]);
		return null;
	}

	//解析显示
	function updatelist(objs) {

		if (objs.length > 0) {
			for (var i = 0; i < objs.length; i++)
				$("#did").append(
						"<option value='"+objs[i].no+"'>" + objs[i].name
								+ "</option>").trigger("create");
			select(0);
		} else {
			$("#did").append("<option value='0'>"+device_nosupport[lsel]+"</option>").trigger(
					"create");
			document.getElementById("type3").style.display = "none";
			document.getElementById("type2").style.display = "none";
		}

		$("#did option:eq(0)").attr("selected", true); //设置属性selected

		$("#did").selectmenu("refresh");

	}

	function select(choise) {
		if (objs[choise].type == 2) {
			document.getElementById("type3").style.display = "none";
			document.getElementById("type2").style.display = "";
		} else if (objs[choise].type == 3) {
			document.getElementById("type3").style.display = "";
			document.getElementById("type2").style.display = "none";
		}else if (objs[choise].type == 5) {
			document.getElementById("type3").style.display = "none";
			document.getElementById("type2").style.display = "";
		}
	}
</script>

</html>
