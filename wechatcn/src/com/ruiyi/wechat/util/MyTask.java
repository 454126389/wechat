package com.ruiyi.wechat.util;

import java.util.TimerTask;

public class MyTask extends TimerTask {

	@Override
	public void run() {
		RedisUtil.set("TOKENCN",  UserSendImageMessageUtil.getAccess_token());
		RedisUtil.set("JSAPI",  UserSendImageMessageUtil.getJsapi_Ticket());
	}
	
	public static void main(String[] args) {
		RedisUtil.set("TOKENCN",  UserSendImageMessageUtil.getAccess_token());
		RedisUtil.set("JSAPI",  UserSendImageMessageUtil.getJsapi_Ticket());
		System.out.println(RedisUtil.get("TOKENCN"));
		System.out.println(RedisUtil.get("JSAPI"));
		
//		rpCM_9NTlFpZYWG6QLzUfkRE9UozPsKEQIxzOWT_1EIl5YWZibt_wgMOVddL1-9YcPF7YB1iqHRMT_lkHn7ve_S7KJEDImRWacMATUMDgAKnianC0rVHDZLvGkqYIStcEYHaAIAHBK
//		sM4AOVdWfPE4DxkXGEs8VC1NFMtrWwbpZ5sc9FKDOYsdAMIUIczg1qKpwnikVvkU9r1CJ6IJrLg9z0JASb8CmQ
		
//		xB_89B3z5dE4iuXbt1RwACSkOK1HGnGtNWqggyFshA9iTZlO43AcfY_OLtMIw6HW88K7U_LmzME_pZ4HbcVd2BHq0Op7OyttBZP6yNanp5ahq8moTVIg5fztmf-nPRdgQIDeAEAYQL
//		sM4AOVdWfPE4DxkXGEs8VC1NFMtrWwbpZ5sc9FKDOYtY73wXTQgCeXsC0fMPnEMwG6dIMTK_mDGCezNL2_KwTQ
		
//		mbF16Q2YG4sSYRsCKr3Pi2H8kVLiDT9jS6rvh03DqFIAXqdYKA0ERp6TXfCrAjxJ2ZApPcLUvfe5TQs5QR8MXEALS6QE9MSMUeHSfacN84B8rIzMZCYpRxdCua2drw7oVFQfAEANXI
//		sM4AOVdWfPE4DxkXGEs8VC1NFMtrWwbpZ5sc9FKDOYtY73wXTQgCeXsC0fMPnEMwG6dIMTK_mDGCezNL2_KwTQ

	}
}
