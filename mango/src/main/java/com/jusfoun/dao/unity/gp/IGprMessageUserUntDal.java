package com.jusfoun.dao.unity.gp;

import java.util.List;
import com.jusfoun.dao.unity.base.IBaseUntDal;
import com.jusfoun.ent.extend.gp.GprMessageUser;
import com.jusfoun.ent.extend.gp.GpMessage;
import com.jusfoun.ent.extend.gp.GpUser;




/**
 * @author Zee
 * @createDate 2017/05/22 14:01:29
 * @updateDate 2020/9/18 15:38:47
 * @description 扩展自实体类IBaseUntDal<GprMessageUser>，可手动更改。消息队列。
 */

public interface IGprMessageUserUntDal extends IBaseUntDal<GprMessageUser> {

 
	public int deleteByMessageId(String  messageId);

	public List<GprMessageUser> getListByMessageId(String  messageId);
 
	public int deleteByUserId(String  userId);

	public List<GprMessageUser> getListByUserId(String  userId);
   
   
}






