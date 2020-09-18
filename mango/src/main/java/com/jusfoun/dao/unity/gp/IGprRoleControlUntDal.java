﻿package com.jusfoun.dao.unity.gp;

import java.util.List;
import com.jusfoun.dao.unity.base.IBaseUntDal;
import com.jusfoun.ent.extend.gp.GprRoleControl;
import com.jusfoun.ent.extend.gp.GpControl;
import com.jusfoun.ent.extend.gp.GpRole;




/**
 * @author Zee
 * @createDate 2017/05/22 14:01:29
 * @updateDate 2020/9/18 15:38:48
 * @description 扩展自实体类IBaseUntDal<GprRoleControl>，可手动更改。角色拥有的控件权限。
 */

public interface IGprRoleControlUntDal extends IBaseUntDal<GprRoleControl> {

 
	public int deleteByControlId(String  controlId);

	public List<GprRoleControl> getListByControlId(String  controlId);
 
	public int deleteByRoleId(String  roleId);

	public List<GprRoleControl> getListByRoleId(String  roleId);
   
   
}






