PhonePickup_class = function() {};
Object.extend(PhonePickup_class.prototype, Object.extend(new AjaxPro.AjaxClass(), {
	getSerialNo: function(weixiNo) {
		return this.invoke("getSerialNo", {"weixiNo":weixiNo}, this.getSerialNo.getArguments().slice(1));
	},
	checkMachine: function(serialNo) {
		return this.invoke("checkMachine", {"serialNo":serialNo}, this.checkMachine.getArguments().slice(1));
	},
	getWeixinInfo: function(weixinNo, url) {
		return this.invoke("getWeixinInfo", {"weixinNo":weixinNo, "url":url}, this.getWeixinInfo.getArguments().slice(2));
	},
	shareLocal: function(serialNo) {
		return this.invoke("shareLocal", {"serialNo":serialNo}, this.shareLocal.getArguments().slice(1));
	},
	url: '/ajaxpro/PhonePickup,App_Web_1jkqy1wu.ashx'
}));
PhonePickup = new PhonePickup_class();

