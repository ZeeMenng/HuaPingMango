package com.jusfoun.dao.unity.gp;

import java.util.List;
import com.jusfoun.dao.unity.base.IBaseUntDal;
import com.jusfoun.ent.extend.gp.GpMessage;
import com.jusfoun.ent.extend.gp.GpUser;
import com.jusfoun.ent.extend.gp.GprDomainMessage;
import com.jusfoun.ent.extend.gp.GprMessageUser;




/**
 * @author Zee
 * @createDate 2017/05/22 14:01:29
 * @updateDate 2020/8/27 11:19:25
 * @description 扩展自实体类IBaseUntDal<GpMessage>，可手动更改。系统消息。
 */

public interface IGpMessageUntDal extends IBaseUntDal<GpMessage> {

 
	public int deleteByUserId(String  userId);

	public List<GpMessage> getListByUserId(String  userId);
   
   
}






