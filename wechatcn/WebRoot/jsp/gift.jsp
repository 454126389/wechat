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
	src="http://res.wx.qq.com/open/js/jweixin-1.1.0.js"></script>
</head>
<body>

	<script language="javascript">


		function check() {
			if ($("#did").val() == 0)
				alert("请选择设备");
			else if ($("#money").val() == 0)
				alert("请选择服务");
			else {
 				
 			$("#buybtn").attr("disabled", "true");
			  $("#buybtn").val("等待").button("refresh");
 
				var htmlobj=$.ajax({url:"../ToolsServlet?action=gift&did="+$("#did").val(),async:false});
				var result=eval(htmlobj.responseText);
				alert(result[0].msc);

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

		function init(objs) {
			for ( var car in objs) {
				$("#did").append(
						"<option value='"+objs[car].id+"'>" + objs[car].id
								+ ":<br/>" + objs[car].alias + "</option>")
						.trigger("create");
			}
		}

		//请求服务器数据
		$.get("../carLocationServlet?action=getCarListByWeid&weid="
				+ GetQueryString("weid"), function(data, status) {
			objs = eval(data);
			init(objs);
		});

	

	</script>


	<div data-role="page">

		<div data-role="header">
			<h1>平台服务时间体验</h1>
		</div>

		<div data-role="content">
			<center>
			<p>征服者，送您一个月平台时间体验时间，新用户限定。</p>
			</center>
			<fieldset data-role="fieldcontain">
				<label for="did">选择设备</label> <select name="did" id="did">
					<option value="0">请选择设备</option>
				</select>
			</fieldset>

		</div>
		<input type="submit" id="buybtn" value="领取" onclick="check()">
			

	</div>

	</div>

</body>
</html>


