﻿package com.jusfoun.dao.unity.gp;

import java.util.List;
import com.jusfoun.dao.unity.base.IBaseUntDal;
import com.jusfoun.ent.extend.gp.GprRoleDomain;
import com.jusfoun.ent.extend.gp.GpDomain;
import com.jusfoun.ent.extend.gp.GpRole;




/**
 * @author Zee
 * @createDate 2017/05/22 14:01:29
 * @updateDate 2021/1/4 14:18:31
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






