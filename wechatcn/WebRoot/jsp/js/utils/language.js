
	var lsel = 0;
	//判断网页语言
	var Terminal = {
		// 辨别移动终端的语言：zh-cn、en-us、ko-kr、ja-jp...
		language : (navigator.browserLanguage || navigator.language)
				.toLowerCase()
	}
	// 还可以通过language，区分开多国语言版  
/*	switch (Terminal.language) {
	case 'zh-cn':
		lagName = "中文";
		lsel = 0;
		break;
	case 'zh-tw':
	    lagName = "繁中";
	    lsel = 1;
	    break;	
	default:
		lagName = "英文";
		lsel = 2;
		break;	
	case 'ru-ру':
		lagName = "俄文";
		lsel = 3;
		break;
	default:
		lagName = "中文";
		lsel = 0;
		break;	

	}*/
	


//'简体中文','繁体中文','english'
var example = [ '简中', '繁中', '英文','俄文' ];

// carlocationlast4.jsp
var round_tv = [ '围栏信息','圍欄信訊', 'GEO-fence information','Информация о "электронном заборе"'];

var round_time_tv = [ '围栏生效时间','圍欄生效時間','GEO-fence time','Время "электронного забора"']; 

var round_set_tv = [ '设置围栏','设置围栏','Set GEO-fence','Настройка "электронного забора"'];

var round_start_tv = [ '围栏开始时间','围栏开始时间','GEO-fence started','"Электронный забор" запущен'];

var round_end_tv = [ '围栏结束时间','围栏结束时间','GEO-fence finished','"Электронный забор" остановлен'];

var round_size_tv = [ '围栏半径','围栏半径','GEO-fence radius','Радиус "электронного забора"'];

var round_center_tv = [ '围栏中心','围栏中心','GEO-fence center','Центр "электронного забора"'];

var center_chose_tv = [ '地图选点','地图选点','Setpoint on map','Выбрать точку на карте'];

var data_tv = [ '日期','日期','Date','Дата'];

var range_tv = [ '范围','範圍','Range','Охват'];

var play_trr_tv = [ '播放间隔(s)','播放間隔時間','Playback interval (s)','Интервал воспроизведения'];

var paly_center_tv = [ '镜头居中','劃面置中','Lens center','Центр объектива'];

var playhis_tv = [ '确认','認確','Confirm','Подтвердить'];

var carlist_tv = [ '选择车辆','選擇車輛','Choose a vehicle','Выбрать автомобиль'];

var lc_tv = [ '里程','里程','Mileage','Пробег'];

var history_search_tv = [ '轨迹','軌跡','Track','Путь'];

var playbtn_tv = [ '播放','播放','Play','Воспроизведение'];

var stopbtn_tv = [ '暂停','暫停','Stop','Стоп'];

var over_btn = [ '超速点','超速地點','Overspeed point','Точка превышения скорости'];

var stop_btn = [ '停车点','停車地點','Stop car point','Точки остановки'];

var mileage_btn = [ '里程查询','里程查詢','Mileage inquiry','Просмотр пути'];

var over_panel_tv = [ '超速选择','超速值選擇','Overspeed selection','Выбор превышения скорости'];

var over_filter_tv = [ '超速过滤选择','超速值過濾選擇','Overspeed filter selection','Выбор параметра превышения скорости'];
var over_5_tv = [ '超速5以上','超速5以上','Overspeed more than 5','Превышение скорости более чем на 5'];
var over_10_tv = [ '超速10以上','超速10以上','Overspeed more than 10','Превышение скорости более чем на 10'];
var over_20_tv = [ '超速20以上','超速20以上','Overspeed more than 20','Превышение скорости более чем на 20'];
var over_30_tv = [ '超速30以上','超速30以上','Overspeed more than 30','Превышение скорости более чем на 30'];
var over_40_tv = [ '超速40以上','超速40以上','Overspeed more than 40','Превышение скорости более чем на 40'];

var over_point_tv = [ '超速点','超速點','Overspeed point','Точка превышения скорости'];

var stop_panel_tv = [ '停车选择','停車時間選擇','Stop car selection','Выбор остановки автомобиля'];

var stop_filter_tv = [ '停车过滤选择','停車過濾選擇','Stop car filter selection','Выбор параметра остановки автомобиля'];
var stop_5_tv = [ '停车5分钟以上','停車5分鐘以上','Stop car more than 5 min','Остановка более чем на 5 мин'];
var stop_10_tv = [ '停车10分钟以上','停車10分鐘以上','Stop car more than 10 min','Остановка более чем на 10 мин'];
var stop_30_tv = [ '停车30分钟以上','停車30分鐘以上','Stop car more than 30 min','Остановка более чем на 30 мин'];
var stop_60_tv = [ '停车1小时以上','停車1小時以上','Stop car more than 1 hour','Остановка более чем на 1 час'];
var stop_360_tv = [ '停车6小时以上','停車6小時以上','Stop car more than 6 hour','Остановка более чем на 6 часов'];
var stop_720_tv = [ '停车12小时以上','停車12小時以上','Stop car more than 12 hours','Остановка более чем на 12 часов'];

var stop_point_tv = [ '停车点','停車地點','Stop car point','Точки остановки'];

var start_time_tv = [ '开始时间','開始時間','Start time','Время начала'];
var end_time_tv = [ '结束时间','結束時間','Finish time','Время окончания'];

var back_tv = [ '返回','返回','Return','Назад'];

// js部分
var device_tv = [ '设备','設備','Device','Устройство'];
var device_name_tv = [ '设备别名','設備別稱','Device name','Имя устройства'];
var time_tv = [ '时间','時間','Time','Время'];
var speed_tv = [ '速度','速度','Speed','Скорость'];
var location_tv = [ '位置','位置','Location','Положение'];

var nolock_tip_tv = [ '未绑定车辆','未綁定車輛','Not bound vehicles','Непривязанные автомобили'];

var null_msg_tv = [ '暂无内容','暫無內容','Temporary no content','Содержание временно отсутствует'];

var my_round_tv = [ '我的围栏','我的圍欄','My GEO-fences','Мои "электронные заборы"'];
var radius_tv = [ '半径','半徑','Radius','Радиус',];
var place_nofind_tv = [ '无法确定地址在这个位置','無法確定地址在這個位置','Not possible to confirm this location','Не удается уточнить данную локацию'];

var bat_tv = [ '电压','電壓','Voltage','Напряжение'];

var elec_tv = [ '电量','電量','Power','Питание'];

var lock_device = [ '开始追踪','開始追蹤','Start tracking','Начать трекинг'];


var nolocation_tip_tv = [ '尚未开启过定位','尚未開啟過定位','Not located','Локация не определена'];

var limit_speed_tv=['限速','限速值','Speed limit','Ограничение скорости'];
var over_speed_tv=['超速','超速','Overspeed','Превышение скорости'];

var stop_time_tv=['停车时间','停車時間','Stop car time','Время остановки автомобиля'];

var day_tv=['日','日','Day','Д'];
var hour_tv=['时','時','Hour','Ч'];
var min_tv=['分','分','Minute','М'];
var second_tv=['秒','秒','Second','С'];

var wait_tv=['请稍等','敬請稍候','Please wait','Пожалуйста, подождите'];
var service_end_tv=['平台服务已经到期，请到网页处充值','平台服務己經到期,請到網頁處充值','The service is overdue, please use website to pay the fee','Закончилось время услуги, пожалуйста, оплатите на сайте'];
var no_find_history_tv=['所选期间未找到轨迹信息，请加大时间段后再尝试','所選期間未找到任何軌跡,請加大時間範圍後在測試.','Track history is not found, please, try longer time interval','Не найдено записей, пожалуйста. попробуйте более длительный интервал времени'];
var only_find_one_tv=['所选期间只找到一个轨迹点，请加大时间段后再尝试。','所選期間只找到一個軌跡,請加大時間範圍後在測試.','Only one track history is found, please, try longer time interval','Найдена только одна запись, пожалуйста. попробуйте более длительный интервал времени'];	

var need_do_history_tv=['请先选择轨迹菜单','請選擇軌跡清單','Please first select the track menu','Выберите меню трека'];

var no_find_lc_tv=['未找到记录','未找到記録','No records found','Не найдено записей'];

var servicelist_tv=['请选择服务','請選擇服務','Please select service','Пожалуйста, выберите сервис'];

//locator.jsp
var loc_device_tv=['设备追踪','設備追踪','Locate device','Следить за устройством'];
var start_loc=['开始追踪','開始追踪','Start tracking','Начало трекинга'];
var end_loc=['结束追踪','結束追踪','Finish tracking','Окончание трекинга'];
var do_30_min=['持续30分钟','持續30分鐘','Continuously 30 min','В течение 30 мин'];
var do_1_hour=['持续1小时','持續1小時','Continuously 1 hour','В течение 1 ч'];
var do_6_hour=['持续6小时','持續6小時','Continuously 6 hour','В течение 6 ч'];
var do_12_hour=['持续12小时','持續12小時','Continuously 12 hour','В течение 12 ч'];
var text_moder_tv=['文字模式','文字模式','Text mode','Режим текста'];
var map_moder_tv=['地图模式','地圖模式','Map mode','Режим карты'];


//buy.jsp
var buy_service_tip_tv=['设备服务购买','設備服務購買','Service purchase','Покупка услуг'];

var service_pt=['平台服务','後台服務','Platform service','Услуга сервера'];
var service_ll=['流量服务','流量服務','Internet traffic service','Услуга трафика'];
var service_dx=['短信服务','簡訊服務','Sms service','Услуга SMS'];



//devicesetting.jsp
var net_un=['未连接','未連線','Not connected','Нет соединение'];
var net_do=['已连接','己連線','Connected','Соединено'];
var net_error=['密码错误','密碼錯誤','Password error','Ошибка пароля'];

var net_encrypt=['加密','加密','Encrypted','Зашифровано'];
var net_unencrypted=['未加密','未加密','Not encrypted','Не зашифровано'];


var needtohighter=['输入不能小于10','輸入不能小於10','Input not less than 10','Не меньше, чем 10'];
var needtolower30=['输入不能大于30','輸入不能大於30','Input not more than 30','Не больше, чем 30'];
var setting_tv=['设置','設定','Settings','Настройки'];
var carsel=['车辆选择','車輛選擇','Vehicle select','Выбор автомобиля'];
var settingtip=['注意：设置数值越小，所耗流量越大','注意: 設定值越小,流量耗損越大','Attention: the less set value, the more current consumption','Внимание: чем меньше значение, тем больше расход эл-ва'];
var drive_trr=['行驶间隔(秒)','行駛傳送間隔(秒)','Driving interval','Интервал пути'];
var stop_trr=['停车间隔(分)','停車傳送間隔(分)','Parking interval','Интервал остановки'];
var luk_trr=['路况发送间隔(分)','路況傳送間隔(分)','Broadcast interval','Интервал трансляции'];
var stoplook=['停车监控','停車監控','Parking monitoring','Режим парковки'];
var close_tv=['关','關','OFF','ВЫКЛ'];
var open_tv=['开','開','ON','ВКЛ'];
var shocklevel=['震动灵敏度','震動敏感度','G sensor sensitivity','Чувствительность сенсора удара'];
var strength_l=['低','低','Low','Низкая'];
var strength_m=['中','中','Middle','Средняя'];
var strength_h=['高','高','High','Высокая'];
var newspendsw=['新闻播报','新聞廣播','News broadcast','Новая трансляция'];
var weathersw_tv=['天气服务','天氣廣播','Weather report','Прогноз погоды'];
var emailsw_tv=['邮件报警','郵件通知報警','Email alarm','Предупреждение по e-mail'];
var settingchangesuc=['修改完成','修改完成','Modifications completed','Закончить изменения'];

var sos_tv=['sos报警','SOS 求救報警','Alarm','Сигнал тревоги'];

var time_zone_tv=['时区选择','時區選擇','Timezone selection','Выбор часового пояса'];
var lsen_tv=['曝光开关','曝光開關','Exposure switch','Экспозиция'];


var upload_trr=['上传间隔','上傳間隔','Upload interval','Загрузить интервал'];
var minute_tv=['分钟','分鐘','Minute','Мин'];
var hours_tv=['小时','小時','Hour','Час'];

var vatime_start_sel_tv=['停车报警开始时间','停車報警開始時間','Parking alarm start time','Время начала предупреждения в режиме парковки'];
var vatime_end_sel_tv=['停车报警关闭时间','停車報警結束時間','Parking alarm end time','Время окончания предупреждения в режиме парковки'];

var cgps_tv=['拐点角度','轉灣角度','Inflection point angle','Угол перегиба'];
var listen_list_tv=['监听号码列表','監聽號碼列表','Monitoring number list','Список номеров'];
var listen_tv=['监听号码','監聽號碼','Monitoring number','Номера'];

var add_btn_tv=['添加','添加','Add','Добавить'];

var fphn_list_tv=['亲情号码列表','親情號碼列表','Family members number list','Список номеров членов семьи'];
var fphn_tv=['亲情号码','親情號碼','Family members number','Номера членов семьи'];
var rest_tv=['重置开关','重啟開關','Reset switch','Перенастройка'];

var save_tv=['保存','儲存','Save','Сохранить'];
//lockdevice.jsp
var device_list_tv=['设备列表','設備號列表','Device list','Список устройств'];
var add_device_tv=['添加设备','添加設備','Add device','Добавить устройство'];
var type_add_by_id=['直接输入设备ID','直接輸入設備號ID','Input device ID','Введите ID устройства'];



//speaknews.jsp

var devicesel_tv=['请勾选要播报的设备','請選取要廣播的設備','Please select the broadcast device','Выберите устройство для трансляции'];
var speaknews_tv=['语音播报','語音廣播','Voice broadcast','Голосовая трансляция'];
var speek_news_tv=['推送信息','推送廣播內容','Information providing','Передача информации'];

var carmerasel_tv=['选择支持拍照的设备','選擇支持拍照的設備','Select camera device','Выберите камеру'];

var no_find_device_tv=['未找到符合的设备','找不到符合的設備','Corresponding device not found','Устройство не найдено'];

//result.jsp


var take_pic_tv=['拍照','拍照','Take photo','Сделать фото'];
var take_video_tv=['录像','録影','Recording','Запись'];
var take_pick_tv=['接人','接人','Pick someone','Встретить'];
var take_shut_tv=['关机','關機','Device switch off','Выключить устройство'];

var code_suc_tip_tv=['指令已发送，稍后会推送，可退出本页面','指令己發送,稍候會推送,可退出本頁面','Instruction has been sent and pushed later,you can exit the page.','Команда отправлена, можете покинуть эту страницу'];
var device_nosupport=['设备不支持','設備不支援','Device is not supported','Устройство не поддерживается'];

//sendway.jsp
var markerStatus=['可以拖动图标修正当前位置','可以施拖動圖標修正當前位置','Icon can be moved to amend current position','Иконка может быть перемещена на текущую позицию'];
var showWay_tv=['发送位置','發送位置','Send the location','Отправить локацию'];
var log_fails_tv=['加载微信服务失败,日志已经上传,后台正在处理,请稍等','加載微信服務失敗,日誌己上傳,後台正在處理中.請稍候','Fail to load the WeChat service','Ошибка загрузки услуги WeChat'];

var searching_tv=['正在搜索','正在搜尋','Searching','Поиск'];
var search_end_tv=['搜索结束','搜尋結束','Search completed','Поиск завершен'];

//PhonePickup.jsp
var pick_tip_tv=['我来接您！请打开把您的位置发送给我','我來接您!請開啟您的位置分享發送給我.','I`ll come to pick you, please send your location to me ','Я встречу тебя, пожалуйста, отправь мне свое местоположение'];
var gps_tip_tv=['请确保您的手机已打开GPS位置信息，这样才能更准确的定位到您的位置','請保持您手機的GPS 己經開啟狀態,這樣才能精準的定位到你的位置.','Please confirm that GPS function of your phone is on, it can more accurately locate your position.','Пожалуйста, убедитесь, что функция GPS на Вашем телефоне активирована'];
