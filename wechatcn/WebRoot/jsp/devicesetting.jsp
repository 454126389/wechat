
<%@ page language="java"
	import="java.util.*,com.ruiyi.wechat.string.Language,com.ruiyi.wechat.model.DeviceType"
	pageEncoding="utf-8"%>
<!DOCTYPE html>


<html>
<head>

<meta charset="utf-8">

<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.5, user-scalable=no">
 <script type="text/javascript" src="../jsp/js/head.js"></script>

</head>


<script src="js/utils/language.js" type="text/javascript"></script>



<body onload="getLockList(0);">
	<div data-role="popup" id="noline" class="ui-content" data-theme="a">
	</div>
	<div data-role="page" id="test_page">
		<div data-role="header">
			<h1 id='setting_tv'></h1>
		</div>

		<div data-role="content">

			<form>
				<fieldset data-role="fieldcontain">
					<label for="day" id='carlist_tv'></label> <select name="carlist"
						id="carlist" data-native-menu="false" onchange="tip();"
						data-corners="false">
					</select>
				</fieldset>
			</form>
			<div id="tttt">

				<div id="trr_panel">
					<label id='settingtip'></label>
					<div data-role="fieldcontain">

						<label for="ttinterval" id='drive_trr'></label> <input
							type="range" name="ttinterval" id="ttinterval" min="10" max="30"
							data-popup-enabled="true">

						</p>




						<label for="rtinterval" id='stop_trr'></label> <input type="range"
							name="rtinterval" id="rtinterval" min="10" max="30"
							data-popup-enabled="true">
						</p>
					</div>


					<div id="luk_trr_div">
						<label for="tr_r" id='luk_trr'></label> <input type="range"
							name="tr_r" id="tr_r" min="10" max="30" data-popup-enabled="true">
						</p>
					</div>





					<div id="sosdiv">
						<label for="sospmon" id='sos_tv'></label> <select name="sospmon"
							id="sospmon" data-role="slider">

						</select>
						</p>
					</div>
				</div>

				<div id="stopdiv">
					<label for="pmon" id='stoplook'></label> <select name="pmon"
						id="pmon" data-role="slider">

					</select>
					</p>
				</div>


				<div id="shockdiv">
					<label id='shocklevel'></label>
					<fieldset data-role="controlgroup" data-type="horizontal"
						id="level_box">

						<label for="strength_l" id='strength_l'></label> <input
							type="radio" name="level" id="strength_l" value="32"> <label
							for="strength_m" id='strength_m'></label> <input type="radio"
							name="level" id="strength_m" value="16"> <label
							for="strength_h" id='strength_h'></label> <input type="radio"
							name="level" id="strength_h" value="0">
					</fieldset>
				</div>

				</p>

				<div id="newsdiv">


					<label for="newssw" id='newspendsw'></label> <select name="newssw"
						id="newssw" data-role="slider">

					</select> <label for="weathersw" id='weathersw_tv'> </label> <select
						name="weathersw" id="weathersw" data-role="slider">

					</select>
				</div>


				<label for="emailsw" id='emailsw_tv'></label> <select name="emailsw"
					id="emailsw" data-role="slider">

				</select>


				<div id="locatorPanel">

					<div data-role="fieldcontain">
						<label for="points" id='time_zone_tv'></label> <input type="range"
							name="stzs" id="stzs" value="8" min="-12" max="12">
					</div>

		 			<label for="rest" id='lsen_tv'> 光感开关</label> <select name="lsen"
						id="lsen" data-role="slider">

					</select> <label for="ttinterval_locator" id='upload_trr'></label> <select
						name="ttinterval_locator" id="ttinterval_locator"
						data-corners="false">

					</select> <label for="vatime_start_sel" id='vatime_start_sel_tv'></label> <select
						name="vatime_start_sel" id="vatime_start_sel" data-corners="false">

					</select> <label for="vatime_end_sel" id='vatime_end_sel_tv'></label> <select
						name="vatime_end_sel" id="vatime_end_sel" data-corners="false">

					</select>



					<div data-role="fieldcontain" style="display: none">
						<label for="points" id='cgps_tv'></label> <input type="range"
							name="cgps" id="cgps" value="30" min="0" max="360">
					</div>



					<fieldset data-role="collapsible" data-collapsed="false">
						<legend id='listen_list_tv'></legend>
						<label for="name" id='listen_tv'></label>

						<ul data-role="listview" data-inset="true" id="mphnlistview">
						</ul>

						<input type="text" name="text" id="mphn"> <input
							id="mphn_btn" type="submit" data-inline="true"
							onclick="mphnListAdd()">
					</fieldset>
					<fieldset data-role="collapsible" data-collapsed="false">
						<legend id='fphn_list_tv'></legend>
						<label for="name" id='fphn_tv'></label>

						<ul data-role="listview" data-inset="true" id="fphnlistview">
						</ul>

						<input type="text" name="text" id="fphn"> <input
							id="fphn_btn" type="submit" data-inline="true"
							onclick="fphnListAdd()">
					</fieldset>

					<label for="rest" id='rest_tv'> </label> <select name="rest" id="rest"
						data-role="slider">
			
					</select>


				</div>

				<!-- </form> -->

				<div id="wifi_set" data-role="collapsible" data-theme="b"
					data-collapsed="false" style="display: none">
					<h1>WIFI设置</h1>
					<ul data-role="listview" data-divider-theme="b" data-inset="true"
						id="wifi_listview">

					</ul>
				</div>
				<hr />
				<input type="button" onclick="changeSetting();" value="保存"
					data-corners="false">
			</div>
		</div>

		<div data-role="page" id="wifi_detail">

			<div data-role="content">
				<ul data-role="listview" data-divider-theme="" data-inset="true">

					<li data-theme="c" data-icon="false"><label for="textinput6">
							no </label> <input name="" id="wifi_no" placeholder="" value=" "
						type="text"></li>

					<li data-theme="c" data-icon="false"><label for="textinput6">
							名称 </label> <input name="" id="wifi_ssid" placeholder="" value=" "
						type="text"></li>
					<li data-theme="c" data-icon="false" id=""><label
						for="textinput6"> 密码 </label> <input name="" id="wifi_password"
						placeholder="" value="" type="text"></li>

					<li data-theme="c" data-icon="false"><a href="#wifi_type"
						data-transition="flip"> 保存方式 </a> <span class="ui-li-count"
						id="wifi_privacy"> 加密</span> </a></li>

					<li data-theme="c" data-icon="false"><a href="#wifi_type"
						data-transition="flip"> 信号强度 </a> <span class="ui-li-count"
						id="wifi_signal">9 </span> </a></li>

					<li data-theme="c" data-icon="false"><a href="#wifi_type"
						data-transition="flip">连接状态 </a> <span class="ui-li-count"
						id="wifi_state">已连接 </span> </a></li>




					<a data-role="button" data-transition="slidedown"
						href="javascript:;" onClick="changeWifi()" data-corners="false"
						id='save_tv'
						>
						</a>

				</ul>


			</div>

		</div>

	</div>
</body>

<script type="text/javascript">

document.getElementById("carlist_tv").innerText = carlist_tv[lsel];
document.getElementById("setting_tv").innerText = setting_tv[lsel];
document.getElementById("drive_trr").innerText = drive_trr[lsel];

document.getElementById("luk_trr").innerText = luk_trr[lsel];
document.getElementById("stop_trr").innerText = stop_trr[lsel];
document.getElementById("settingtip").innerText = settingtip[lsel];

document.getElementById("sos_tv").innerText = sos_tv[lsel];


	var sospmon = document.getElementById('sospmon');
	sospmon.options.add(new Option(close_tv[lsel], "off")); //这个兼容IE与firefox 
	sospmon.options.add(new Option(open_tv[lsel], "on")); //这个兼容IE与firefox 
	var pmon = document.getElementById('pmon');
	pmon.options.add(new Option(close_tv[lsel], "off")); //这个兼容IE与firefox 
	pmon.options.add(new Option(open_tv[lsel], "on")); //这个兼容IE与firefox 
	document.getElementById("stoplook").innerText = stoplook[lsel];					
	document.getElementById("shocklevel").innerText = shocklevel[lsel];					

	document.getElementById("strength_l").innerText = strength_l[lsel];					
	document.getElementById("strength_m").innerText = strength_m[lsel];					
	document.getElementById("strength_h").innerText = strength_h[lsel];		
				
	document.getElementById("newspendsw").innerText = newspendsw[lsel];					

	
	var newssw = document.getElementById('newssw');
	newssw.options.add(new Option(close_tv[lsel], "off")); //这个兼容IE与firefox 
	newssw.options.add(new Option(open_tv[lsel], "on")); //这个兼容IE与firefox 
	
		document.getElementById("weathersw_tv").innerText = weathersw_tv[lsel];			
	
	
		var weathersw = document.getElementById('weathersw');
	weathersw.options.add(new Option(close_tv[lsel], "off")); //这个兼容IE与firefox 
	weathersw.options.add(new Option(open_tv[lsel], "on")); //这个兼容IE与firefox 
		document.getElementById("emailsw_tv").innerText = emailsw_tv[lsel];			
	
	
		var emailsw = document.getElementById('emailsw');
	emailsw.options.add(new Option(close_tv[lsel], "off")); //这个兼容IE与firefox 
	emailsw.options.add(new Option(open_tv[lsel], "on")); //这个兼容IE与firefox 

			
			
			document.getElementById("time_zone_tv").innerText = time_zone_tv[lsel];			
			
			 var lsen_tv = document.getElementById('lsen_tv'); 
					var lsen = document.getElementById('lsen');
	lsen.options.add(new Option(close_tv[lsel], "off")); //这个兼容IE与firefox 
	lsen.options.add(new Option(open_tv[lsel], "on")); //这个兼容IE与firefox 
	
	
		document.getElementById("upload_trr").innerText = upload_trr[lsel];		
	
			
	var ttinterval_locator = document.getElementById('ttinterval_locator');
	ttinterval_locator.options.add(new Option("1"+minute_tv[lsel], "12")); //这个兼容IE与firefox 
	ttinterval_locator.options.add(new Option("5"+minute_tv[lsel], "60")); //这个兼容IE与firefox 
	ttinterval_locator.options.add(new Option("10"+minute_tv[lsel], "120")); //这个兼容IE与firefox 
	ttinterval_locator.options.add(new Option("30"+minute_tv[lsel], "360")); //这个兼容IE与firefox 
	ttinterval_locator.options.add(new Option("1"+hours_tv[lsel], "720")); //这个兼容IE与firefox 
	ttinterval_locator.options.add(new Option("3"+hours_tv[lsel], "2160")); //这个兼容IE与firefox 
	ttinterval_locator.options.add(new Option("6"+hours_tv[lsel], "4320")); //这个兼容IE与firefox 
	ttinterval_locator.options.add(new Option("12"+hours_tv[lsel], "8640")); //这个兼容IE与firefox 
	ttinterval_locator.options.add(new Option("18"+hours_tv[lsel], "12960")); //这个兼容IE与firefox 
	ttinterval_locator.options.add(new Option("24"+hours_tv[lsel], "17280")); //这个兼容IE与firefox 
	
	
	document.getElementById("vatime_start_sel_tv").innerText = vatime_start_sel_tv[lsel];	
	
	var vatime_start_sel = document.getElementById('vatime_start_sel');
	vatime_start_sel.options.add(new Option("0"+hours_tv[lsel], "00")); //这个兼容IE与firefox 
	vatime_start_sel.options.add(new Option("1"+hours_tv[lsel], "01")); //这个兼容IE与firefox 
	vatime_start_sel.options.add(new Option("2"+hours_tv[lsel], "02")); //这个兼容IE与firefox 
	vatime_start_sel.options.add(new Option("3"+hours_tv[lsel], "03")); //这个兼容IE与firefox 
	vatime_start_sel.options.add(new Option("4"+hours_tv[lsel], "04")); //这个兼容IE与firefox 
	vatime_start_sel.options.add(new Option("5"+hours_tv[lsel], "05")); //这个兼容IE与firefox 
	vatime_start_sel.options.add(new Option("6"+hours_tv[lsel], "06")); //这个兼容IE与firefox 
	
	vatime_start_sel.options.add(new Option("7"+hours_tv[lsel], "07")); //这个兼容IE与firefox 
	vatime_start_sel.options.add(new Option("8"+hours_tv[lsel], "08")); //这个兼容IE与firefox 
	vatime_start_sel.options.add(new Option("9"+hours_tv[lsel], "09")); //这个兼容IE与firefox 
	vatime_start_sel.options.add(new Option("10"+hours_tv[lsel], "10")); //这个兼容IE与firefox 
	vatime_start_sel.options.add(new Option("11"+hours_tv[lsel], "11")); //这个兼容IE与firefox 
	vatime_start_sel.options.add(new Option("12"+hours_tv[lsel], "12")); //这个兼容IE与firefox 
	
	
	vatime_start_sel.options.add(new Option("13"+hours_tv[lsel], "13")); //这个兼容IE与firefox 
	vatime_start_sel.options.add(new Option("14"+hours_tv[lsel], "14")); //这个兼容IE与firefox 
	vatime_start_sel.options.add(new Option("15"+hours_tv[lsel], "15")); //这个兼容IE与firefox 
	vatime_start_sel.options.add(new Option("16"+hours_tv[lsel], "16")); //这个兼容IE与firefox 
	vatime_start_sel.options.add(new Option("17"+hours_tv[lsel], "17")); //这个兼容IE与firefox 
	vatime_start_sel.options.add(new Option("18"+hours_tv[lsel], "18")); //这个兼容IE与firefox 
		
	vatime_start_sel.options.add(new Option("19"+hours_tv[lsel], "19")); //这个兼容IE与firefox 
	vatime_start_sel.options.add(new Option("20"+hours_tv[lsel], "20")); //这个兼容IE与firefox 
	vatime_start_sel.options.add(new Option("21"+hours_tv[lsel], "21")); //这个兼容IE与firefox 
	vatime_start_sel.options.add(new Option("22"+hours_tv[lsel], "22")); //这个兼容IE与firefox 
	vatime_start_sel.options.add(new Option("23"+hours_tv[lsel], "23")); //这个兼容IE与firefox 
	vatime_start_sel.options.add(new Option("24"+hours_tv[lsel], "24")); //这个兼容IE与firefox 
	
	document.getElementById("vatime_end_sel_tv").innerText = vatime_end_sel_tv[lsel];						
	
	var vatime_end_sel = document.getElementById('vatime_end_sel');
	vatime_end_sel.options.add(new Option("0"+hours_tv[lsel], "00")); //这个兼容IE与firefox 
	vatime_end_sel.options.add(new Option("1"+hours_tv[lsel], "01")); //这个兼容IE与firefox 
	vatime_end_sel.options.add(new Option("2"+hours_tv[lsel], "02")); //这个兼容IE与firefox 
	vatime_end_sel.options.add(new Option("3"+hours_tv[lsel], "03")); //这个兼容IE与firefox 
	vatime_end_sel.options.add(new Option("4"+hours_tv[lsel], "04")); //这个兼容IE与firefox 
	vatime_end_sel.options.add(new Option("5"+hours_tv[lsel], "05")); //这个兼容IE与firefox 
	vatime_end_sel.options.add(new Option("6"+hours_tv[lsel], "06")); //这个兼容IE与firefox 
	
	vatime_end_sel.options.add(new Option("7"+hours_tv[lsel], "07")); //这个兼容IE与firefox 
	vatime_end_sel.options.add(new Option("8"+hours_tv[lsel], "08")); //这个兼容IE与firefox 
	vatime_end_sel.options.add(new Option("9"+hours_tv[lsel], "09")); //这个兼容IE与firefox 
	vatime_end_sel.options.add(new Option("10"+hours_tv[lsel], "10")); //这个兼容IE与firefox 
	vatime_end_sel.options.add(new Option("11"+hours_tv[lsel], "11")); //这个兼容IE与firefox 
	vatime_end_sel.options.add(new Option("12"+hours_tv[lsel], "12")); //这个兼容IE与firefox 
	
	
	vatime_end_sel.options.add(new Option("13"+hours_tv[lsel], "13")); //这个兼容IE与firefox 
	vatime_end_sel.options.add(new Option("14"+hours_tv[lsel], "14")); //这个兼容IE与firefox 
	vatime_end_sel.options.add(new Option("15"+hours_tv[lsel], "15")); //这个兼容IE与firefox 
	vatime_end_sel.options.add(new Option("16"+hours_tv[lsel], "16")); //这个兼容IE与firefox 
	vatime_end_sel.options.add(new Option("17"+hours_tv[lsel], "17")); //这个兼容IE与firefox 
	vatime_end_sel.options.add(new Option("18"+hours_tv[lsel], "18")); //这个兼容IE与firefox 
		
	vatime_end_sel.options.add(new Option("19"+hours_tv[lsel], "19")); //这个兼容IE与firefox 
	vatime_end_sel.options.add(new Option("20"+hours_tv[lsel], "20")); //这个兼容IE与firefox 
	vatime_end_sel.options.add(new Option("21"+hours_tv[lsel], "21")); //这个兼容IE与firefox 
	vatime_end_sel.options.add(new Option("22"+hours_tv[lsel], "22")); //这个兼容IE与firefox 
	vatime_end_sel.options.add(new Option("23"+hours_tv[lsel], "23")); //这个兼容IE与firefox 
	vatime_end_sel.options.add(new Option("24"+hours_tv[lsel], "24")); //这个兼容IE与firefox 
	
	
		document.getElementById("cgps_tv").innerText = cgps_tv[lsel];						
		document.getElementById("listen_list_tv").innerText = listen_list_tv[lsel];						
		document.getElementById("listen_tv").innerText = listen_tv[lsel];	
		
		
		
		$("#mphn_btn").val(add_btn_tv[lsel]);
		$("#fphn_btn").val(add_btn_tv[lsel]);
							
		document.getElementById("fphn_list_tv").innerText = fphn_list_tv[lsel];						
		document.getElementById("fphn_tv").innerText = fphn_tv[lsel];
		
					
		document.getElementById("rest_tv").innerText = rest_tv[lsel];		
				
		var rest = document.getElementById('rest');
	rest.options.add(new Option(close_tv[lsel], "off")); //这个兼容IE与firefox 
	rest.options.add(new Option(open_tv[lsel], "on")); //这个兼容IE与firefox 		
	
			
	document.getElementById("save_tv").innerText = save_tv[lsel];	
	
	/* ----------------------- */
	
	var settingxml="";
	var defswc="";
		var netstatus=[net_un[lsel],net_do[lsel],net_error[lsel]];
		var t;  
		function popup(msg){
		clearTimeout(t);
		document.getElementById("noline").firstChild.nodeValue=msg;	
			$('#noline').popup();
			$('#noline').popup('open');
		t=setTimeout("timedCount()",2000)  
		}
		
			function timedCount(){
 	  $('#noline').popup("close");
		}  
		
		
	$(document).ready(function() {

		$("#ttinterval").focus(function() {
			$("#ttinterval").css("background-color", "#FFFFFF");
		});
		$("#ttinterval").blur(function() {
			if ($("#ttinterval").val() < 10) {
				$("#ttinterval").css("background-color", "#D6D6FF");
				popup(needtohighter[lsel]);
			}
			if ($("#ttinterval").val() > 30) {
				$("#ttinterval").css("background-color", "#D6D6FF");
				popup(needtolower30[lsel]);
			}
		});

		$("#rtinterval").focus(function() {
			$("#rtinterval").css("background-color", "#FFFFFF");
		});
		$("#rtinterval").blur(function() {
			if ($("#rtinterval").val() < 10) {
				$("#rtinterval").css("background-color", "#D6D6FF");
				popup(needtohighter[lsel]);
			}
			if ($("#rtinterval").val() > 30) {
				$("#rtinterval").css("background-color", "#D6D6FF");
				popup(needtolower30[lsel]);
			}
		});

		$("#tr_r").focus(function() {
			$("#tr_r").css("background-color", "#FFFFFF");
		});
		$("#tr_r").blur(function() {
			if ($("#tr_r").val() < 10) {
				$("#tr_r").css("background-color", "#D6D6FF");
					popup(needtohighter[lsel]);
			}
			if ($("#tr_r").val() > 30) {
				$("#tr_r").css("background-color", "#D6D6FF");
				popup(needtolower30[lsel]);
			}
		});

	});




	function  shownum(no,name,psw,privacy,signal,state)
	{
	document.getElementById("wifi_no").value=no;
	document.getElementById("wifi_ssid").value=name;
	document.getElementById("wifi_password").value=psw;
	(privacy == 0)?document.getElementById("wifi_privacy").firstChild.nodeValue=net_unencrypted[lsel]: document.getElementById("wifi_privacy").firstChild.nodeValue=net_encrypt[lsel];
	document.getElementById("wifi_signal").firstChild.nodeValue=signal;
	
	
	document.getElementById("wifi_state").firstChild.nodeValue=netstatus[state];
	window.location="#wifi_detail";

	}
	
	
	


	
	function showSetting(select)
	{
		var items = settingxml.getElementsByTagName("item");
		
		var no=items[select].getElementsByTagName("itemno")[0].firstChild.nodeValue;
		var type=items[select].getElementsByTagName("itemtype")[0].firstChild.nodeValue;
		var wifi=items[select].getElementsByTagName("itemwifi")[0].firstChild.nodeValue;
		
		
		if(type==1||type==0)
		{
			document.getElementById("stopdiv").style.display="none";
		}else
		{	
			document.getElementById("stopdiv").style.display="";
		}
		
		if(type>0&type!=4)
		{
		document.getElementById("newsdiv").style.display="none";
		//document.getElementById("stopdiv").style.display="none";
		document.getElementById("sosdiv").style.display="none";
		
		}else{
		document.getElementById("newsdiv").style.display="";
		//document.getElementById("stopdiv").style.display="";
		document.getElementById("sosdiv").style.display="";		
		}
		
		//存在WIFI设置模块
		if(wifi>0)  
			{
			var msg=items[select].getElementsByTagName("itemmsg")[0].firstChild.nodeValue;
			
			var addstrli="";
			
			if(msg!="null")
			  {
			  
			  document.getElementById("wifi_set").style.display="";
			  var arrayObj = msg.split(":")[1].split(";");
				
				onClick='document.getElementById("wifi_ssid").val()=1'
		for ( var i = 0; i < arrayObj.length; i++) {
					var s="ST_F3D076";
					var s2=1;
			addstrli += "<li data-theme=''  data-icon='false' id='wifi_switch'><a  onClick='shownum("+'"'+no+'"'+","+'"'+arrayObj[i].split(",")[1]+'"'+","+'"'+arrayObj[i].split(",")[2]+'"'+","+'"'+arrayObj[i].split(",")[3]+'"'+","+'"'+arrayObj[i].split(",")[4]+'"'+","+'"'+arrayObj[i].split(",")[5]+'"'+")'  data-transition='fade'>"+arrayObj[i].split(",")[1]+" <span class='ui-li-count' id='a_switch'> "+netstatus[arrayObj[i].split(",")[5]]+" </span> </a></li>";
		}
		
		$("#wifi_listview").append(addstrli).trigger("create");
		$('#wifi_listview').listview('refresh');  
			  }
			
			}
		else
			document.getElementById("wifi_set").style.display="none";
			
			
		//存在时区选择	
		if(items[select].getElementsByTagName("itemstzs")[0]==null)
		{
			document.getElementById("locatorPanel").style.display="none";
			
			document.getElementById("luk_trr_div").style.display="";
			document.getElementById("newsdiv").style.display="";
			document.getElementById("trr_panel").style.display="";
			
			
			}
		else
			{
			document.getElementById("trr_panel").style.display="none";
			document.getElementById("newsdiv").style.display="none";
			document.getElementById("luk_trr_div").style.display="none";
			document.getElementById("locatorPanel").style.display="";
			$("#fphnlistview").empty().trigger("create");
			$("#mphnlistview").empty().trigger("create");
			
				var itemstzs=items[select].getElementsByTagName("itemstzs")[0].firstChild.nodeValue;
				var itemcgps=items[select].getElementsByTagName("itemcgps")[0].firstChild.nodeValue;
				
				var itemmphn=[];
				var itemfphn=[];
				if(items[select].getElementsByTagName("itemmphn")[0].lastChild!=null)
					itemmphn=(items[select].getElementsByTagName("itemmphn")[0].firstChild.nodeValue).split(";");
				if(items[select].getElementsByTagName("itemfphn")[0].lastChild!=null)	
					itemfphn=(items[select].getElementsByTagName("itemfphn")[0].firstChild.nodeValue).split(";");
				var itemrest=items[select].getElementsByTagName("itemrest")[0].firstChild.nodeValue;
				
				var itemlsen=items[select].getElementsByTagName("itemlsen")[0].firstChild.nodeValue;
				
				var itemvatime=items[select].getElementsByTagName("itemvatime")[0].firstChild.nodeValue;
				
				
				//$('#rtinterval').val((sgps.split(",")[2])/60).slider('refresh');
				
				
				$('#stzs').val(itemstzs).slider('refresh');
				$('#cgps').val(itemcgps).slider('refresh');
				
				$("#vatime_start_sel").val(itemvatime.split("-")[0]);
				$("#vatime_start_sel").selectmenu("refresh");
				$("#vatime_end_sel").val(itemvatime.split("-")[1]).selectmenu("refresh");
			
			
			
			
			
			for(var i=0;i<itemmphn.length;i++)
				if(itemmphn[i]!=""&&itemmphn[i]!="null")
						$("#mphnlistview")
					.append(
							"<li id='id"+itemmphn[i]+"'><a  class='mphnListid' href='#'>"+ itemmphn[i]+ "</a><a id='raoundrail_id'  onclick='removeMphnList("+itemmphn[i]+")' data-rel='dialog' data-transition='pop' data-icon='delete'>Download Browser</a></li>").trigger("create");
									
									
					
		
			
			for(var i=0;i<itemfphn.length;i++)
				if(itemfphn[i]!=""&&itemfphn[i]!="null")
						$("#fphnlistview")
					.append(
							"<li id='id"+itemfphn[i]+"'><a  class='fphnListid' href='#'>"+ itemfphn[i]+ "</a><a id='raoundrail_id'  onclick='removeFphnList("+itemfphn[i]+")' data-rel='dialog' data-transition='pop' data-icon='delete'>Download Browser</a></li>").trigger("create");
									
									
			$("#mphnlistview").listview("refresh");	
			$("#fphnlistview").listview("refresh");
			
			
			if(itemrest==0)
				document.getElementById("rest").value ="off"; 
			else 
				document.getElementById("rest").value ="on"; 
				$('#rest').slider("refresh"); //更新UI
				
				
						
			if(itemlsen==0)
				document.getElementById("lsen").value ="off"; 
			else 
				document.getElementById("lsen").value ="on"; 
				$('#lsen').slider("refresh"); //更新UI	
				
			}
			

			
		
		var sgps=items[select].getElementsByTagName("itemsgps")[0].firstChild.nodeValue;
		var fortify=items[select].getElementsByTagName("itemfortiry")[0].firstChild.nodeValue;
		var email_open=items[select].getElementsByTagName("itememail")[0].firstChild.nodeValue;
		var sos_open=items[select].getElementsByTagName("itemsos")[0].firstChild.nodeValue;
		var weather_state=items[select].getElementsByTagName("itemweather")[0].firstChild.nodeValue;
		var news_state=items[select].getElementsByTagName("itemnews")[0].firstChild.nodeValue;
		defswc=sgps.split(",")[0];
		
		
				//存在时区选择	
		if(items[select].getElementsByTagName("itemstzs")[0]==null)
			$('#ttinterval').val(sgps.split(",")[1]).slider('refresh');
		else
			{
				$("#ttinterval_locator").val(sgps.split(",")[1]);
				$("#ttinterval_locator").selectmenu("refresh"); 
			}
		
		$('#rtinterval').val((sgps.split(",")[2])/60).slider('refresh');
		$('#tr_r').val(items[select].getElementsByTagName("itemtr_t")[0].firstChild.nodeValue).slider('refresh');
			
			var swch;
			if(fortify%16==0)
				swch="off";
			else 
				swch="on";
				
			var emailswch;
			if(email_open==0)
				emailswch="off";
			else 
				emailswch="on";
				
			var sosswch;
			if(sos_open==0)
				sosswch="off";
			else 
				sosswch="on";
				
			if(weather_state==0)
				weather_state="off";
			else 
				weather_state="on";	
				
			if(news_state==0)
				news_state="off";
			else 
				news_state="on";	
				
				document.getElementById("newssw").value =news_state; 
			$('#newssw').slider("refresh"); //更新UI
		
				document.getElementById("weathersw").value =weather_state; 
			$('#weathersw').slider("refresh"); //更新UI
			
			document.getElementById("emailsw").value =emailswch; 
			$('#emailsw').slider("refresh"); //更新UI
			
				document.getElementById("pmon").value =swch; 
			$('#pmon').slider("refresh"); //更新UI

				document.getElementById("sospmon").value =sosswch; 
			$('#sospmon').slider("refresh"); //更新UI
			
			
			
			 var level = document.getElementsByName("level");  
			
			/*  alert(level.item(0).checked); */
			 level.item(2-Math.floor(fortify/16)).checked=true;
			 $('#level_box').controlgroup("refresh");
		
	}
		//列表添加
		function mphnListAdd()
		{
				if($("#mphn").val()!="")
				{
			$("#mphnlistview")
					.append(
							"<li id='id"+$("#mphn").val()+"'><a class='mphnListid' href='#'>"+ $("#mphn").val()+ "</a><a id='raoundrail_id'  onclick='removeMphnList("+$("#mphn").val()+")' data-rel='dialog' data-transition='pop' data-icon='delete'>Download Browser</a></li>").trigger("create");
				
							$("#mphnlistview").listview("refresh");
							}			
		}
		
		function fphnListAdd()
		{
			if($("#fphn").val()!="")
		{
			$("#fphnlistview")
					.append(
							"<li id='id"+$("#fphn").val()+"'><a class='fphnListid' href='#'>"+ $("#fphn").val()+ "</a><a id='raoundrail_id'  onclick='removeFphnList("+$("#fphn").val()+")' data-rel='dialog' data-transition='pop' data-icon='delete'>Download Browser</a></li>").trigger("create");
							$("#fphnlistview").listview("refresh");
							}		
		}
		
		
		function removeMphnList(id)
		{
		//id.remove();
		$("#id"+id+"").remove();  
		$("#mphnlistview").listview("refresh");	
		}
		
		function removeFphnList(id)
		{
		$("#id"+id+"").remove();   
		$("#fphnlistview").listview("refresh");	
		}
		
	
	
		//修改设置
		function changeSetting() {

		var fortiry=0;
		if($("#pmon").val()=="off")
			fortiry=0;
		else
			fortiry=1;
			
		var sosval=0;
		if($("#sospmon").val()=="off")
			sosval=0;
		else
			sosval=1;		
			
		var emailswch;	
		if($("#emailsw").val()=="off")
			emailswch=0;
		else
			emailswch=1;	
			
			
		var	weatherch;
		if($("#weathersw").val()=="off")
			weatherch=0;
		else
			weatherch=1;
			
		var	newsch;
		if($("#newssw").val()=="off")
			newsch=0;
		else
			newsch=1;
			
		fortiry=parseInt($('input[name="level"]:checked').val())+parseInt(fortiry);
		
		var t = document.getElementById("carlist");
		var no=t.options[t.selectedIndex].value;
		
		
	 	var sgps;
	 	
	 	var urlpar;
	 	if(items[t.selectedIndex].getElementsByTagName("itemstzs")[0]==null)
	 	{
	 	 sgps="1,"+$("#ttinterval").val()+","+($("#rtinterval").val()*60);
	 	 urlpar="no=" + no+"&fortiry="+fortiry+"&sgps="+sgps+"&tr_r="+$("#tr_r").val()+"&email_open="+emailswch+"&weather_state="+weatherch+"&news_state="+newsch+"&sosval="+sosval;
	 	}
	 	else
	 	{
	 	
	 	 sgps="1,"+$("#ttinterval_locator").val()+","+($("#rtinterval").val()*60);
	 	
		var stzs=$("#stzs").val()
		var cgps=$("#cgps").val()
	 	var mphnlist="";
	 	var fphnlist="";
	 	var restswch;	
	 	var lsenswch;	
	 	
	 	for(var i=0;i<$(".mphnListid").length;i++)
	 	{
	 		mphnlist=mphnlist+$(".mphnListid")[i].firstChild.nodeValue;
	 		if(i<$(".mphnListid").length-1)
	 		mphnlist=mphnlist+";";
	 	}
	 	
	 	for(var i=0;i<$(".fphnListid").length;i++)
	 	{
	 	
	 		fphnlist=fphnlist+$(".fphnListid")[i].firstChild.nodeValue;
	 		if(i<$(".fphnListid").length-1)
	 		fphnlist=fphnlist+";";
	 	}
	 		
	 	if($("#lsen").val()=="off")
			lsenswch=0;
		else
			lsenswch=1;	
	 	
		if($("#rest").val()=="off")
			restswch=0;
		else
			restswch=1;	
		var vatime=$("#vatime_start_sel").val()+"-"+$("#vatime_end_sel").val()
		urlpar="no=" + no+"&fortiry="+fortiry+"&sgps="+sgps+"&tr_r="+$("#tr_r").val()+"&email_open="+emailswch+"&weather_state="+weatherch+"&news_state="+newsch+"&sosval="+sosval+"&stzs="+stzs+"&cgps="+cgps+"&mphnlist="+mphnlist+"&fphnlist="+fphnlist+"&restswch="+restswch+"&vatime="+vatime+"&lsenswch="+lsenswch;
	 		}
	 	
 		var xmlhttp;
		if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} else {// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		} 
		 		xmlhttp.onreadystatechange = function() {
		 if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			 
			 var poi=t.selectedIndex;
			 getLockList(poi);
			 popup("<%=Language.settingchangesuc%>");
		 }
		 }; 
	 	xmlhttp.open("GET", "../settingServlet?"+urlpar, true);
		xmlhttp.send(); 
	}



function changeWifiSuc()
{
location.replace(document.referrer);
 
}

	function changeWifi(){
	
	var wifimsg=document.getElementById("wifi_ssid").value+","+document.getElementById("wifi_password").value+",0";
	//String s2="1:" +"8F-AP2,,0;8F-AP3,,0;";
	
	$.ajax({
  url: "../lockServlet?action=changeWifi&no="+document.getElementById('wifi_no').value+"&wifimsg="+wifimsg+"",
  dataType: "script",
  success: changeWifiSuc()
});
	};

	//查询已经绑定设备
	function getLockList(Index) {
		//清空
 	 	var t = document.getElementById("carlist");
 		while(t.firstChild!=null)
		{
			t.removeChild(t.firstChild);
		} 
		
		
		var xmlhttp;
		if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} else {// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				parseXML2List(xmlhttp.responseXML,Index);
				
				
				//格式化li
				/* 	getUnLockList(); */

			}
		};

		var ra=Math.random()*10000;
		xmlhttp.open("GET","../lockServlet?action=getLockListSetting&weid="+'<%=request.getParameter("weid")%>' + "&random=" + ra, true);
	

		xmlhttp.send();
	}

	var items;

	//解析显示
	function parseXML2List(xml, Index) {

		settingxml = xml;
		items = xml.getElementsByTagName("item");

		var str = "";

		if (items.length > 0) {
			for (var i = 0; i < items.length; i++) {
				if (items[i].getElementsByTagName("itemtype")[0].firstChild.nodeValue != 3)
					str += " <option value="
							+ items[i].getElementsByTagName("itemno")[0].firstChild.nodeValue
							+ ">"
							+ items[i].getElementsByTagName("itemname")[0].firstChild.nodeValue
							+ "</option>";

			}

			var t = document.getElementById('carlist');

			$("#carlist").append(str).trigger("create");
			t.options[Index].selected = true;
			$("#carlist").selectmenu("refresh");

			showSetting(Index);

		} else {
			$("#carlist").append("<option value='0'>已绑设备不支持</option>").trigger(
					"create");
			$("#carlist option:eq(0)").attr("selected", true); //设置属性selected
			$("#carlist").selectmenu("refresh");
			document.getElementById("tttt").style.display = "none";
		}

	}

	//解析显示	
	function tip() {

		/* var t = document.getElementById("carlist");  */
		/* alert(t.options[t.selectedIndex].value); */
		/* alert(t.selectedIndex); */
		/*  alert(("#carlist  option:selected").text());  */
		var t = document.getElementById("carlist");
		showSetting(t.selectedIndex);

	}
</script>


</html>