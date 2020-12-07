package com.jusfoun.dao.unity.gp;

import java.util.List;
import com.jusfoun.dao.unity.base.IBaseUntDal;
import com.jusfoun.ent.extend.gp.GprDomainUser;
import com.jusfoun.ent.extend.gp.GpDomain;
import com.jusfoun.ent.extend.gp.GpUser;




/**
 * @author Zee
 * @createDate 2017/05/22 14:01:29
 * @updateDate 2020/10/13 20:02:17
 * @description 扩展自实体类IBaseUntDal<GprDomainUser>，可手动更改。应用领域拥有的用户。
 */

public interface IGprDomainUserUntDal extends IBaseUntDal<GprDomainUser> {

 
	public int deleteByDomainId(String  domainId);
    
    public int deleteByDomainIdList(List<String> domainIdList);

	public List<GprDomainUser> getListByDomainId(String  domainId);
 
	public int deleteByUserId(String  userId);
    
    public int deleteByUserIdList(List<String> userIdList);

	public List<GprDomainUser> getListByUserId(String  userId);
   
   
}






