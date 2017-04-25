package com.ruiyi.wechat.mybatis.inter;

import java.util.List;

import org.apache.ibatis.annotations.Select;





import com.ruiyi.wechat.mybatis.model.Parameter;
import com.ruiyi.wechat.mybatis.model.User;
import com.ruiyi.wechat.mybatis.model.User_sim;

public interface IUserOperation {
//	@Select("select * from user where USERNAME=#{id}")
//	public User getUser(String id);
	
	@Select("SELECT * FROM `user` WHERE USERNAME=#{0} AND PASSWORD=#{1}")
	public User login(String username,String password);
	
	@Select("SELECT * FROM parameter WHERE ID in(SELECT PARAMETER_ID FROM `user_parameter` WHERE USER_ID=#{user_id})")
	public List<Parameter> getParameterList(String user_id);
	
	@Select("SELECT * FROM user_sim WHERE PHONE =#{0}")
	public List<User_sim> getIccidList(String phone);
}
