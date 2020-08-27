package com.jusfoun.dao.unity.gp;

import java.util.List;
import com.jusfoun.dao.unity.base.IBaseUntDal;
import com.jusfoun.ent.extend.gp.GprDomainUser;
import com.jusfoun.ent.extend.gp.GpDomain;
import com.jusfoun.ent.extend.gp.GpUser;




/**
 * @author Zee
 * @createDate 2017/05/22 14:01:29
 * @updateDate 2020/8/27 11:19:21
 * @description 扩展自实体类IBaseUntDal<GprDomainUser>，可手动更改。应用领域拥有的用户。
 */

public interface IGprDomainUserUntDal extends IBaseUntDal<GprDomainUser> {

 
	public int deleteByDomainId(String  domainId);

	public List<GprDomainUser> getListByDomainId(String  domainId);
 
	public int deleteByUserId(String  userId);

	public List<GprDomainUser> getListByUserId(String  userId);
   
   
}






