﻿package com.zee.dao.unity.gp;

import java.util.List;
import com.zee.dao.unity.base.IBaseUntDal;
import com.zee.ent.extend.gp.GprRoleDomain;
import com.zee.ent.extend.gp.GpDomain;
import com.zee.ent.extend.gp.GpRole;




/**
 * @author Zee
 * @createDate 2017/05/22 14:01:29
 * @updateDate 2021/1/28 16:07:40
 * @description 扩展自实体类IBaseUntDal<GprRoleDomain>，可手动更改。角色拥有的功能模块权限。
 */

public interface IGprRoleDomainUntDal extends IBaseUntDal<GprRoleDomain> {

 
	public int deleteByDomainId(String  domainId);
    
    public int deleteByDomainIdList(List<String> domainIdList);

	public List<GprRoleDomain> getListByDomainId(String  domainId);
 
	public int deleteByRoleId(String  roleId);
    
    public int deleteByRoleIdList(List<String> roleIdList);

	public List<GprRoleDomain> getListByRoleId(String  roleId);
        
	public int deleteByCompositeIdList(List<GprRoleDomain> gprRoleDomainList);   
   

   
}





