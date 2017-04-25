<!DOCTYPE HTML>
<html>
<body>

<img  id="imageplay" width="100%" height="100%" alt="conqueror" />

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
  document.getElementById("imageplay").src=GetQueryString("url");
  
</script>
</html>
