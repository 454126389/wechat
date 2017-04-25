   var loc_Interval;
	//追踪的设备位置
	var loc_Num=-1;
	
	var last_wechat_latitude;
	var last_wechat_longitude;
	
	var last_hsj_latitude;
	var last_hsj_latitude;
	
		//追踪设备
	function loc_device(poi_lat,poi_lon,choice)
	{
		 var id="loc_btn"+choice;
		 //追踪模式持续时间
		 var loctime=0;
		 
		 map.setZoom(15);
		 
		 
		 //清空正在执行的定位
		  clearInterval(loc_Interval);
		  
		  if(loc_Num!=-1&&loc_Num!=choice)
		  {
		  	//关闭正在执行的
/*		  		$.get("../carLocationServlet?action=getLocStart&did="
			+ objs[loc_Num].no+"&time="+loctime, function(data, status) {
			}); */
		  		
				$.ajax({
  					url: "../carLocationServlet?action=getLocStart&did="+ objs[loc_Num].no+"&time="+loctime,
  					cache: false
				}).done(function( html ) {
				});
		  		
		  }
		  
		  
		  if(loc_Num==choice)
		  {
		    //停止定位
		  	document.getElementById(id).innerHTML="开始追踪";
		  	loc_Num=-1;
		  	loctime=0;
		  	
		  	location.reload(); 
		  	
		  	}
		  else
		  {
		  //开始播放
		   document.getElementById(id).innerHTML="停止追踪";
		   loc_Num=choice;
		   loctime=$("#loc_time").val();
		   
		   
		   
		    google.maps.event.addListener(my_marker, 'click', function() { 
		       	 infowindow.setContent("您当前位置");
		         infowindow.open(map,my_marker);
			    });
		   
		   		   
		   setTimeout("update_poi('"+choice+"')", 0);
		   //10s更新一次点
 		   loc_Interval=setInterval("update_poi('"+choice+"')", 10*1000);
		
		 //  setTimeout("getLocation('"+choice+"')", 0);
		   //10s更新一次点
		 //  loc_Interval=setInterval("getLocation('"+choice+"')", 5*1000);
		   
		//请求服务器数据
		$.get("../carLocationServlet?action=getLocStart&did="
			+ objs[choice].no+"&time="+loctime, function(data, status) {
		}); 
		   
		   }
		   
	}		
	
	
	
	function getLocation(choice)
	  {
	  if (navigator.geolocation)
	    {
	    navigator.geolocation.watchPosition(showPosition,choice);
	    }
	  else{x.innerHTML="Geolocation is not supported by this browser.";}
	  }
	function showPosition(position,choice)
	  {
//	  x.innerHTML="Latitude: " + position.coords.latitude +
//	  "<br />Longitude: " + position.coords.longitude;
	  
		my_marker.setPosition(new google.maps.LatLng(position.coords.latitude,position.coords.longitude));
		my_marker.setMap(map);
		if(isfirst_flag==true)
		 {
			map.setCenter(my_marker.getPosition());
			isfirst_flag=false;
		 }
		 if(choice!=undefined)	
		  update_device(choice);
	  
	  
	  }

	
	
	
	
	//更新设备位置
	function update_device(choice)
			{
			//请求服务器数据
			$.get("../carLocationServlet?action=getCarGps&did="
					+ objs[choice].no, function(data, status) {
				temp_gps = eval(data);
				
				
				
				if(last_wechat_latitude==null||last_hsj_latitude==null)
					{
						draw_loc_line(temp_gps[0].gpsPosiLat,temp_gps[0].gpsPosiLon);
						setInterval("update_poi('"+choice+"')", 5*1000);
						last_wechat_latitude=wechat_latitude;
						last_wechat_longitude=wechat_longitude;
						last_hsj_latitude=temp_gps[0].gpsPosiLat;
						last_hsj_longitude=temp_gps[0].gpsPosiLon;
						
						
						map.setZoom(15);
						map.setCenter(new google.maps.LatLng(wechat_latitude,
								wechat_longitude));
						
						
						var dis=getFlatternDistance(Number(temp_gps[0].gpsPosiLat),Number(temp_gps[0].gpsPosiLon),Number(wechat_latitude),Number(wechat_longitude));
						
						var latLng = new google.maps.LatLng(Number(temp_gps[0].gpsPosiLat), Number(temp_gps[0].gpsPosiLon));
						geocoder.geocode({
							latLng : latLng
						}, function(responses) {
							if (responses && responses.length > 0) {
							  infowindow.setContent("<button id='loc_btn"+choice+"' onclick='loc_device("+objs[choice].gpsPosiLat+","+objs[choice].gpsPosiLon+","+choice+")'>停止追踪</button>"+"<br>"+"距离："+dis+"<br>"+responses[0].formatted_address);
							} else {
							  infowindow.setContent("距离："+dis+"<br>"+"无法确定地址在这个位置");
							}
						});
						
						infowindow.open(map,car_marker[choice]); 
						        google.maps.event.addListener(car_marker[choice], 'click', function() { 
						            infowindow.open(map,car_marker[choice]);
						   	    });
						
					}
				else
					{
					var weidis=getFlatternDistance(Number(last_wechat_latitude),Number(last_wechat_longitude),Number(wechat_latitude),Number(wechat_longitude));
					//last_wechat_latitude=wechat_longitude;
					//last_wechat_longitude=wechat_longitude;
					var hsjdis=getFlatternDistance(Number(last_hsj_latitude),Number(last_hsj_longitude),Number(temp_gps[0].gpsPosiLat),Number(temp_gps[0].gpsPosiLon));
					if(weidis>50)
						{
							draw_loc_line(temp_gps[0].gpsPosiLat,temp_gps[0].gpsPosiLon);
							last_wechat_latitude=wechat_latitude;
							last_wechat_longitude=wechat_longitude;
							
							map.setCenter(new google.maps.LatLng(wechat_latitude,
									wechat_longitude));
						}
					else if(hsjdis>50)
						{
							draw_loc_line(temp_gps[0].gpsPosiLat,temp_gps[0].gpsPosiLon);
							last_hsj_latitude=temp_gps[0].gpsPosiLat;
							last_hsj_longitude=temp_gps[0].gpsPosiLon;
							
							map.setCenter(new google.maps.LatLng(temp_gps[0].gpsPosiLat,
									temp_gps[0].gpsPosiLon));
							
							var dis=getFlatternDistance(Number(temp_gps[0].gpsPosiLat),Number(temp_gps[0].gpsPosiLon),Number(wechat_latitude),Number(wechat_longitude));
							
							var latLng = new google.maps.LatLng(Number(temp_gps[0].gpsPosiLat), Number(temp_gps[0].gpsPosiLon));
							geocoder.geocode({
								latLng : latLng
							}, function(responses) {
								if (responses && responses.length > 0) {
								  infowindow.setContent("<button id='loc_btn"+choice+"' onclick='loc_device("+objs[choice].gpsPosiLat+","+objs[choice].gpsPosiLon+","+choice+")'>停止追踪</button>"+"<br>"+"距离："+dis+"<br>"+responses[0].formatted_address);
								} else {
								  infowindow.setContent("距离："+dis+"<br>"+"无法确定地址在这个位置");
								}
							});
							
							infowindow.open(map,car_marker[choice]); 
							        google.maps.event.addListener(car_marker[choice], 'click', function() { 
							            infowindow.open(map,car_marker[choice]);
							   	    });
							
							
						}
					}
				
				//进行比较,超过一定范围才开始
			//	var dis=getFlatternDistance(Number(temp_gps[0].gpsPosiLat),Number(temp_gps[0].gpsPosiLon),Number(wechat_latitude),Number(wechat_longitude));
				
			//		 car_marker[choice].setPosition(new google.maps.LatLng(temp_gps[0].gpsPosiLat,temp_gps[0].gpsPosiLon));
			//		 car_marker[choice].setMap(map);
				//	 draw_loc_line(temp_gps[0].gpsPosiLat,temp_gps[0].gpsPosiLon);
				
			/*	var latLng = new google.maps.LatLng(Number(temp_gps[0].gpsPosiLat), Number(temp_gps[0].gpsPosiLon));
				geocoder.geocode({
					latLng : latLng
				}, function(responses) {
					if (responses && responses.length > 0) {
					  infowindow.setContent("<button id='loc_btn"+choice+"' onclick='loc_device("+objs[choice].gpsPosiLat+","+objs[choice].gpsPosiLon+","+choice+")'>停止追踪</button>"+"<br>"+"距离："+dis+"<br>"+responses[0].formatted_address);
					} else {
					  infowindow.setContent("距离："+dis+"<br>"+"无法确定地址在这个位置");
					}
				});*/
				
		       // infowindow.open(map,car_marker[choice]); 
		/*        google.maps.event.addListener(car_marker[choice], 'click', function() { 
		            infowindow.open(map,car_marker[choice]);
		   	    }); */
				
					 
					 
					 
			});
			}
			

	
	
	
	var isfirst_flag=true;
	//更新用户位置
	function update_poi(choice)
	{
	 	//正式
			wx.getLocation({
			type : 'gcj02', // 默认为wgs84的gps坐标，如果要返回直接给openLocation用的火星坐标，可传入'gcj02'
			success : function(res) {
				wechat_latitude = res.latitude; // 纬度，浮点数，范围为90 ~ -90
				wechat_longitude = res.longitude; // 经度，浮点数，范围为180 ~ -180。
				wechat_speed = res.speed; // 速度，以米/每秒计
				wechat_accuracy = res.accuracy; // 位置精度
				//getCarInfo();
				my_marker.setPosition(new google.maps.LatLng(wechat_latitude,wechat_longitude));
				my_marker.setMap(map);
				if(isfirst_flag==true)
				 {
					map.setCenter(my_marker.getPosition());
					isfirst_flag=false;
				 }
				 if(choice!=undefined)	
				  update_device(choice);
					
			}, cancel: function (res) {
		        alert('用户拒绝授权获取地理位置');
		    }
		}); 
		 
		
		
//	    google.maps.event.addListener(my_marker, 'click', function() { 
//       	 infowindow.setContent("您当前位置");
//         infowindow.open(map,my_marker);
//	    });
		
		
		
	   	
		//测试				
  /* 	 	 wechat_longitude="118.15641";
		 wechat_latitude="26.648516"; 
		 my_marker.setPosition(new google.maps.LatLng(wechat_latitude,wechat_longitude));
		 my_marker.setMap(map);
				if(isfirst_flag==true)
				 {
					map.setCenter(my_marker.getPosition());
					isfirst_flag=false;
				 }   
		if(choice!=undefined)		 
		 update_device(choice); */
		
	}
	
	
	//算距离
	var EARTH_RADIUS = 6378137.0;    //单位M
    var PI = Math.PI;
	function getRad(d){
        return d*PI/180.0;
    }
    
    function getFlatternDistance(lat1,lng1,lat2,lng2){
        var f = getRad((lat1 + lat2)/2);
        var g = getRad((lat1 - lat2)/2);
        var l = getRad((lng1 - lng2)/2);
        
        var sg = Math.sin(g);
        var sl = Math.sin(l);
        var sf = Math.sin(f);
        
        var s,c,w,r,d,h1,h2;
        var a = EARTH_RADIUS;
        var fl = 1/298.257;
        
        sg = sg*sg;
        sl = sl*sl;
        sf = sf*sf;
        
        s = sg*(1-sl) + (1-sf)*sl;
        c = (1-sg)*(1-sl) + sf*sl;
        
        w = Math.atan(Math.sqrt(s/c));
        r = Math.sqrt(s*c)/w;
        d = 2*w*a;
        h1 = (3*r -1)/2/c;
        h2 = (3*r +1)/2/s;
        
        return d*(1 + fl*(h1*sf*(1-sg) - h2*(1-sf)*sg));
    }