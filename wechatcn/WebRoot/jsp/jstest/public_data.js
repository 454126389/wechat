var user_id;
var user_name;
var user_pass;
var user_session_id;
var user_sex;
var hosturl;
var version;
var product;
var fid;
var pid;
var tab;
var net_type;
var dpr;
var dpi;
var w;
var h;
var wvwidth;
var wvheight;

var local_user_id;
var local_user_name;
var local_user_pass;
var local_user_sex;
var local_user_session_id;
var local_hosturl;
var local_version;
var local_product;
var local_fid;
var local_pid;
var local_tab;
var local_net_type;
var local_dpr;
var local_dpi;
var local_w;
var local_h;
var local_wvwidth;
var local_wvheight;
var param_str;

function getClientData() {
	if (window.youyuan && window.youyuan.localGet) {
		local_user_id = window.youyuan.localGet("client_user_id");
		local_user_name = window.youyuan.localGet("client_user_name");
		local_user_pass = window.youyuan.localGet("client_user_pass");
		local_user_sex = window.youyuan.localGet("client_user_sex");
		local_user_session_id = window.youyuan.localGet("client_user_session_id");
		local_hosturl = window.youyuan.localGet("client_hosturl");
		local_version = window.youyuan.localGet("client_version");
		local_product = window.youyuan.localGet("client_product");
		local_fid = window.youyuan.localGet("client_fid");
		local_pid = window.youyuan.localGet("client_pid");
		local_tab = window.youyuan.localGet("client_tab");
		local_net_type = window.youyuan.localGet("client_net_type");
		local_dpr = window.youyuan.localGet("client_dpr");
		local_dpi = window.youyuan.localGet("client_dpi");
		local_w = window.youyuan.localGet("client_w");
		local_h = window.youyuan.localGet("client_h");
		local_wvwidth = window.youyuan.localGet("client_wvwidth");
		local_wvheight = window.youyuan.localGet("client_wvheight");
	}
	if (window.youyuan && window.youyuan.getClientData) {
		var client_data = window.youyuan.getClientData();
		if (client_data != null && client_data != "") {
			client_data = eval("(" + client_data + ")");
			user_id = client_data.uid;
			user_name = client_data.username;
			user_pass = client_data.passwd;
			user_session_id = client_data.sessionid;
			user_sex = client_data.sex;
			hosturl = client_data.hosturl;
			version = client_data.version;
			product = client_data.product;
			fid = client_data.fid;
			pid = client_data.pid;
			tab = client_data.tab;
			net_type = client_data.net_type;
			dpr = client_data.dpr;
			dpi = client_data.dpi;
			w = client_data.w;
			h = client_data.h;
			wvwidth = client_data.wvwidth;
			wvheight = client_data.wvheight;
			
			if (user_id == null || user_id == "") {
				if (local_user_id != null && local_user_id != "") {
					user_id = local_user_id;
				}
			}
			if (user_name == null || user_name == "") {
				if (local_user_name != null && local_user_name != "") {
					user_name = local_user_name;
				}
			}
			if (user_pass == null || user_pass == "") {
				if (local_user_pass != null && local_user_pass != "") {
					user_pass = local_user_pass;
				}
			}
			if (user_session_id == null || user_session_id == "") {
				if (local_user_session_id != null && local_user_session_id != "") {
					user_session_id = local_user_session_id;
				}
			}
			if (user_sex == null || user_sex == "") {
				if (local_user_sex != null && local_user_sex != "") {
					user_sex = local_user_sex;
				}
			}
			if (hosturl == null || hosturl == "") {
				if (local_hosturl != null && local_hosturl != "") {
					hosturl = local_hosturl;
				}
			}
			if (version == null || version == "") {
				if (local_version != null && local_version != "") {
					version = local_version;
				}
			}
			if (product == null || product == "") {
				if (local_product != null && local_product != "") {
					product = local_product;
				}
			}
			if (fid == null || fid == "") {
				if (local_fid != null && local_fid != "") {
					fid = local_fid;
				}
			}
			if (pid == null || pid == "") {
				if (local_pid != null && local_pid != "") {
					pid = local_pid;
				}
			}
			if (tab == null || tab == "") {
				if (local_tab != null && local_tab != "") {
					tab = local_tab;
				}
			}
			if (net_type == null || net_type == "") {
				if (local_net_type != null && local_net_type != "") {
					net_type = local_net_type;
				}
			}
			if (dpr == null || dpr == "") {
				if (local_dpr != null && local_dpr != "") {
					dpr = local_dpr;
				}
			}
			if (dpi == null || dpi == "") {
				if (local_dpi != null && local_dpi != "") {
					dpi = local_dpi;
				}
			}
			if (w == null || w == "") {
				if (local_w != null && local_w != "") {
					w = local_w;
				}
			}
			if (h == null || h == "") {
				if (local_h != null && local_h != "") {
					h = local_h;
				}
			}
			if (wvwidth == null || wvwidth == "") {
				if (local_wvwidth != null && local_wvwidth != "") {
					wvwidth = local_wvwidth;
				}
			}
			if (wvheight == null || wvheight == "") {
				if (local_wvheight != null && local_wvheight != "") {
					wvheight = local_wvheight;
				}
			}

			if (window.youyuan && window.youyuan.localPut) {
				if (user_id != null && user_id != "") {
					window.youyuan.localPut("client_user_id", user_id);
				}
				if (user_name != null && user_name != "") {
					window.youyuan.localPut("client_user_name", user_name);
				}
				if (user_pass != null && user_pass != "") {
					window.youyuan.localPut("client_user_pass", user_pass);
				}
				if (user_sex != null && user_sex != "") {
					window.youyuan.localPut("client_user_sex", user_sex);
				}
				if (user_session_id != null && user_session_id != "") {
					window.youyuan.localPut("client_user_session_id", user_session_id);
				}
				if (hosturl != null && hosturl != "") {
					window.youyuan.localPut("client_hosturl", hosturl);
				}
				if (version != null && version != "") {
					window.youyuan.localPut("client_version", version);
				}
				if (product != null && product != "") {
					window.youyuan.localPut("client_product", product);
				}
				if (fid != null && fid != "") {
					window.youyuan.localPut("client_fid", fid);
				}
				if (pid != null && pid != "") {
					window.youyuan.localPut("client_pid", pid);
				}
				if (tab != null && tab != "") {
					window.youyuan.localPut("client_tab", tab);
				}
				if (net_type != null && net_type != "") {
					window.youyuan.localPut("client_net_type", net_type);
				}
				if (dpr != null && dpr != "") {
					window.youyuan.localPut("client_dpr", dpr);
				}
				if (dpi != null && dpi != "") {
					window.youyuan.localPut("client_dpi", dpi);
				}
				if (w != null && w != "") {
					window.youyuan.localPut("client_w", w);
				}
				if (h != null && h != "") {
					window.youyuan.localPut("client_h", h);
				}
				if (wvwidth != null && wvwidth != "") {
					window.youyuan.localPut("client_wvwidth", wvwidth);
				}
				if (wvheight != null && wvheight != "") {
					window.youyuan.localPut("client_wvheight", wvheight);
				}
			}
			hosturl += "/(" + user_session_id + ")/";
			param_str = "&version=" + version + "&product=" + product
				      + "&fid=" + fid + "&pid=" + pid + "&tab=" + tab
				      + "&net_type=" + net_type + "&dpr=" + dpr
				      + "&dpi=" + dpi + "&w=" + w + "&h=" + h
				      + "&wvwidth=" + wvwidth + "&wvheight=" + wvheight;
		} else {
			alert("获取参数异常");
		}
	} else {
		alert("客户端异常");
	}
}

