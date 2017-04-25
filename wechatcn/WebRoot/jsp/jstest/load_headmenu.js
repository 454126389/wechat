function create_notice_jsonstr(jsonstr) {
	jsonstr = jsonstr ? jsonstr.trim() : "[]";
	var datas = eval("(" + jsonstr + ")");
	var sixinnum = 0;
	var noticenum = 0;
	if(!!datas[0].msgnum){
		sixinnum = datas[0].msgnum;
	}
	if(!!datas[0].noticenum){
		noticenum = datas[0].noticenum;
	}
	var strpramstart = "{\"expiration\":1,\"num1\" : " + noticenum + ",\"num2\" : " + sixinnum + ",\"notices\":[";
	var strpramend = "]}";
	for(var i = 1; i < datas.length; i++){
		strpramstart += "{\"type\":" + parseInt(datas[i].type) + ",\"id\":\"" + datas[i].id + "\",\"desc\":\"" + datas[i].desc + "\",\"url\":\"" + datas[i].url + "\",\"pos\":" + parseInt(datas[i].pos) + "},";
	}
	if(strpramstart != "{\"expiration\":1,\"num1\" : " + noticenum + ",\"num2\" : " + sixinnum + ",\"notices\":["){
		if(strpramstart.substring(strpramstart.length - 1, strpramstart.length) == ","){
			strpramstart = strpramstart.substring(0, strpramstart.length - 1);
			strpramstart += strpramend;
		}
	}
	return strpramstart;
}
function loadHeadmenu() {
	var tmpReceiverId = window.youyuan.getEnv("uid");
	var rid = eval("("+ tmpReceiverId + ")");
	var receiverId = rid.uid;
	var time = window.youyuan.getNoticeTime(receiverId + "");

	$.get(
		req_headmenu_url+"?tab=101&hosturl="+hosturl+"&version="+version+"&fid="+fid+"&from=frominnerpage&time="+time,
		
		function(txt) {
			if(window.youyuan && window.youyuan.showNotice){
				var msgs = $.trim(txt);
alert("msgs ="+msgs)
				window.youyuan.showNotice(msgs);
				var headMenuJson = eval('(' + msgs + ')');
				
				window.youyuan.localPut("msgnum",headMenuJson.num2);
				window.youyuan.localPut("noticenum",headMenuJson.num1);
				/*window.youyuan.localPut("receiverId",headMenuJson.receiverId + "");
				if(!!headMenuJson.mails && headMenuJson.mails.length>0) {
					window.youyuan.localPut(headMenuJson.receiverId + "", headMenuJson.time);
				}*/
			}
		}
	);
}

function jump(pos, url) {
	pos = pos ? pos : 0;
	// 只有信箱才跳
	if (pos == 2) {
		window.youyuan.jump2tabWithUrl(pos, url);
	} else {
		window.location.href = url;
	}
}

function setColor(dd) {
	var event = window.event;
	var srcElem = event.srcElement;
	// 如果是点击超链接，则跳出
	if (srcElem && srcElem.tagName === 'A') {
		return;
	}
	var y = event.touches[0].pageY;
	if (y) {
		window.uyuan_msgY = y;
	}
}

function clearColor(dd) {
	var event = window.event;
	var srcElem = event.srcElement;
	// 如果是点击超链接，则跳出
	if (srcElem && srcElem.tagName === 'A') {
		return;
	}
	var ey = event.changedTouches[0].pageY;
	var isRedirect = false;
	var y = window.uyuan_msgY || 0;
	if (!!y && Math.abs(ey - y) < 5) {
		isRedirect = true;
	}

	if (isRedirect) {
		dd.style.backgroundColor = '#fce7ed';
		window.setTimeout(function() {
			var d = dd.attributes['data-url'].value;
			if (dd.parentNode) {
				dd.className = dd.parentNode.className;
			}
			if (!!d) {
				var pos = parseInt(d.substring(0, 1));
				var url = d.substring(1, d.length);
				jump(pos, url);
			}
		}, 50, dd);
	}
}