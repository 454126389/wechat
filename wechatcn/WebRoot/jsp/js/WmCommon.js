function WmResult() {
	this.code = 0;
	this.message = "";
}
function WM_result(resultString) {
	var result = new WmResult();
	if (resultString == null || resultString == "") {
		result.code = -1000;
		result.message = "未知错误！";
	}else if (resultString[0] == '|') {
		result.code = -100;
		result.message = resultString.substring(1);
	}else if (resultString[0] == '-') {
		if (resultString.length == 1) {
			result.code = -1000;
			result.message = "未知错误！";
		}else {
			result.code = resultString[1];
			result.message = resultString.substring(2);
		}
	}else if (resultString[0] == '0') {
		result.code = 0;
		result.message = resultString.substring(1);
	}else {
		result.code = -1000;
		result.message = "未知错误！";
	}
	return result;
}
var WM_toast=function(msg, delay){
	if (delay == null)
		delay = 1500;
	$("<div class='ui-loader ui-overlay-shadow ui-body-e ui-corner-all'><h3>"+msg+"</h3></div>")
	.css({ display: "block", 
		opacity: 0.90, 
		position: "fixed",
		padding: "7px",
		"text-align": "center",
		width: "270px",
		left: ($(window).width() - 284)/2,
		top: $(window).height()/2 })
	.appendTo( $.mobile.pageContainer ).delay( delay )
	.fadeOut( 400, function(){
		$(this).remove();
	});
}

function WM_Loading_init(t) {
	var contextDiv = document.createElement("div");
	contextDiv.innerHTML = "<div class='Loading' style='height:100%;width:100%;text-align:center'><img src='images/load_large.gif'/><div style='color:#EEEEEE'></div></div>";
	var first=document.body.firstChild;//得到页面的第一个元素
	document.body.insertBefore(contextDiv, first);//在得到的第一个元素之前插入
	$(".Loading img").css("top", $(".Loading").height() / 2 - $(".Loading img").height() / 2 - 10);
    $(".Loading div").css("top", $(".Loading").height() / 2 - $(".Loading img").height() / 2);
	if (t != null && t != "") {
		$(".Loading div").text(t);
	}
}
function WM_Loading_show(t) {
	if (t != null && t != "") {
		$(".Loading div").text(t);
	}else {
		$(".Loading div").text("");
	}
	$(".Loading").show();
}
function WM_Loading_setText(t) {
	$(".Loading div").text(t);
}
function WM_Loading_hide() {
	$(".Loading").hide();
}

//判断访问终端
var wmBrowser={
    versions:function(){
        var u = navigator.userAgent, app = navigator.appVersion;
        return {
            trident: u.indexOf('Trident') > -1, //IE内核
            presto: u.indexOf('Presto') > -1, //opera内核
            webKit: u.indexOf('AppleWebKit') > -1, //苹果、谷歌内核
            gecko: u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1,//火狐内核
            mobile: !!u.match(/AppleWebKit.*Mobile.*/), //是否为移动终端
            ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios终端
            android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1, //android终端或者uc浏览器
            iPhone: u.indexOf('iPhone') > -1 , //是否为iPhone或者QQHD浏览器
            iPad: u.indexOf('iPad') > -1, //是否iPad
            webApp: u.indexOf('Safari') == -1, //是否web应该程序，没有头部与底部
            weixin: u.indexOf('MicroMessenger') > -1, //是否微信 （2015-01-22新增）
            qq: u.match(/\sQQ/i) == " qq" //是否QQ
        };
    }(),
    language:(navigator.browserLanguage || navigator.language).toLowerCase()
}
function WM_isRunOnIE() {
	return (wmBrowser.versions.trident);
}
function WM_isRunOnPhone() {
	//判断是否移动端
	return (wmBrowser.versions.mobile||wmBrowser.versions.android||wmBrowser.versions.ios);
}

function WM_getQueryString(name)
{ 
	var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"); 
	var r = window.location.search.substr(1).match(reg); 
	if (r!=null) 
		return unescape(r[2]); 
	return null; 
} 
// 格式化数字
function WM_pad(num, n) {
    return (new Array(n >(''+num).length ? (n - (''+num).length+1) : 0).join('0') + num);
}
/** 
* approx distance between two points on earth ellipsoid 
* @param {Object} lat1 
* @param {Object} lng1 
* @param {Object} lat2 
* @param {Object} lng2 
*/
var EARTH_RADIUS = 6378137.0;    //单位M  
var PI = Math.PI;
// 角度转孤度
function WM_getRad(d) {
    return d * PI / 180.0;
}
// 获取两点的距离
function WM_getDistance(lon1, lat1, lon2, lat2) {
    if (lat1 == lat2 && lon1 == lon2)
	    return 0;
	lat1 = parseFloat(lat1);
	lon1 = parseFloat(lon1);
	lat2 = parseFloat(lat2);
	lon2 = parseFloat(lon2);
	var f = WM_getRad((parseFloat(lat1) + parseFloat(lat2)) / 2);
	var g = WM_getRad((lat1 - lat2) / 2);
	var l = WM_getRad((lon1 - lon2) / 2);

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

	return Math.round(d * (1 + fl * (h1 * sf * (1 - sg) - h2 * (1 - sf) * sg)));
}
/**格式化数字为一个定长的字符串，前面补０ 
*参数: 
* Source 待格式化的字符串 
* Length 需要得到的字符串的长度 
*/ 
function WM_formatNumber(source, length) { 
    var temp=""; 
    for(i = 1;i <= length - source.length; i++) { 
        temp += "0"; 
    } 
    return temp + source; 
}

/**//***
path 要显示值的对象id
****/
function browseFolder(path) {
	try {
		var Message = "\u8bf7\u9009\u62e9\u6587\u4ef6\u5939"; //选择框提示信息
		var Shell = new ActiveXObject("Shell.Application"); 
		var Folder = Shell.BrowseForFolder(0, Message, 64, 17); //起始目录为：我的电脑
		//var Folder = Shell.BrowseForFolder(0,Message,0); //起始目录为：桌面
		if (Folder != null) {
			Folder = Folder.items(); // 返回 FolderItems 对象
			Folder = Folder.item(); // 返回 Folderitem 对象
			Folder = Folder.Path; // 返回路径
			if (Folder.charAt(Folder.length - 1) != "") {
				Folder = Folder + ""; 
			}
			document.getElementById(path).value = Folder; 
			return Folder; 
		}
	}
	catch (e) 
	{
		alert(e.message); 
	}
}

//得到当前时间字符串，格式为：YYYY-MM-DD HH:MM:SS    
function WM_Time_getString() {   
    var now = new Date();  
    var year = now.getFullYear();       //年  
    var month = now.getMonth() + 1;     //月  
    var day = now.getDate();            //日  
         
    var hh = now.getHours();            //时  
    var mm = now.getMinutes();          //分  
    var ss=now.getSeconds();            //秒  
         
    var clock = year + "-";  
         
    if(month < 10) clock += "0";         
    clock += month + "-";  
         
    if(day < 10) clock += "0";   
    clock += day + " ";  
         
    if(hh < 10) clock += "0";  
    clock += hh + ":";  
  
    if (mm < 10) clock += '0';   
    clock += mm+ ":";  
          
    if (ss < 10) clock += '0';   
    clock += ss;  
  
    return(clock);   
}  

function WM_Table_dir(table, notify) {
	table.find("tr").each(function () {
		$(this).find("td").each(function () {
			if (notify != null)
				notify($(this).text());
			// return false;   // break跳出
			return true;    // continue
		});
	});
}
function WM_Table_get(tab, row) {
	return $("#" + tab + " tr").eq(row);
}
// >= 0 具体第几行
function WM_Table_add(tab, row, trHtml) {
	//获取table最后一行 $("#tab tr:last")
	//获取table第一行 $("#tab tr").eq(0)
	//获取table倒数第二行 $("#tab tr").eq(-2)
	var $tr = $("#" + tab + " tr").eq(row);
	if ($tr.size() == 0) {
		alert("指定的table id或行数不存在！");
		return;
	} else {
		$tr.after(trHtml);
	}
}
function WM_Table_delete(ckb) {
	//获取选中的复选框，然后循环遍历删除
	var ckbs = $("input[name=" + ckb + "]:checked");
	if (ckbs.size() == 0) {
		return;
	}
	ckbs.each(function () {
		$(this).parent().parent().parent().remove();
	});
}

function WM_MediaPlayer_init() {
	var contextDiv = document.createElement("div");
	contextDiv.innerHTML = "<div id='WmMediaPlayer' style='height:0;width:0;text-align:center'></div>";
	var first=document.body.firstChild;//得到页面的第一个元素
	document.body.insertBefore(contextDiv, first);//在得到的第一个元素之前插入
}
function WM_MediaPlayer_play(source) {
	var content = "<video id='WmPlayer' autoplay='autoplay' width='320' height='0' controls>";
	content += "<source src='" + source + "' type='video/mp4' />";
	content += "</video>";
	$("#WmMediaPlayer").html(content);
}
function WM_MediaPlayer_stop() {
	$("#WmMediaPlayer video").remove();
}





