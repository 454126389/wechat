<!DOCTYPE HTML>
<html>
<body>

<video id="videoplay"  width="100%" height="100%" controls="controls" autoplay="autoplay">
Your browser does not support the video tag.
</video>

</body>
<script type="text/javascript">
	//采用正则表达式获取地址栏参数
	function GetQueryString(name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
		var r = window.location.search.substr(1).match(reg);
		if (r != null)
			return unescape(r[2]);
		return null;
	}
  document.getElementById("videoplay").src="http://webservice.conqueror.cn:8181/dbs/1758c9da-1ed5-48f7-a7da-d1e964271c6d.mp4";
</script>
</html>
