﻿package com.jusfoun.dao.unity.gp;

import java.util.List;
import com.jusfoun.dao.unity.base.IBaseUntDal;
import com.jusfoun.ent.extend.gp.GprRoleInterface;
import com.jusfoun.ent.extend.gp.GpInterface;
import com.jusfoun.ent.extend.gp.GpRole;




/**
 * @author Zee
 * @createDate 2017/05/22 14:01:29
 * @updateDate 2020/9/18 15:38:49
 * @description 扩展自实体类IBaseUntDal<GprRoleInterface>，可手动更改。角色拥有的接口权限。
 */

public interface IGprRoleInterfaceUntDal extends IBaseUntDal<GprRoleInterface> {

 
	public int deleteByInterfaceId(String  interfaceId);

	public List<GprRoleInterface> getListByInterfaceId(String  interfaceId);
 
	public int deleteByRoleId(String  roleId);

	public List<GprRoleInterface> getListByRoleId(String  roleId);
   
   
}






