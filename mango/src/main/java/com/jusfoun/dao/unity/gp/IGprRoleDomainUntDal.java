package com.jusfoun.dao.unity.gp;

import java.util.List;
import com.jusfoun.dao.unity.base.IBaseUntDal;
import com.jusfoun.ent.extend.gp.GprRoleDomain;
import com.jusfoun.ent.extend.gp.GpDomain;
import com.jusfoun.ent.extend.gp.GpRole;




/**
 * @author Zee
 * @createDate 2017/05/22 14:01:29
 * @updateDate 2020/8/27 11:19:22
 * @description 扩展自实体类IBaseUntDal<GprRoleDomain>，可手动更改。角色拥有的功能模块权限。
 */

public interface IGprRoleDomainUntDal extends IBaseUntDal<GprRoleDomain> {

 
	public int deleteByDomainId(String  domainId);

	public List<GprRoleDomain> getListByDomainId(String  domainId);
 
	public int deleteByRoleId(String  roleId);

	public List<GprRoleDomain> getListByRoleId(String  roleId);
   
   
}






