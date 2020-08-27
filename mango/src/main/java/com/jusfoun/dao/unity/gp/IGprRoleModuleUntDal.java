package com.jusfoun.dao.unity.gp;

import java.util.List;
import com.jusfoun.dao.unity.base.IBaseUntDal;
import com.jusfoun.ent.extend.gp.GprRoleModule;
import com.jusfoun.ent.extend.gp.GpModule;
import com.jusfoun.ent.extend.gp.GpRole;




/**
 * @author Zee
 * @createDate 2017/05/22 14:01:29
 * @updateDate 2020/8/27 11:19:22
 * @description 扩展自实体类IBaseUntDal<GprRoleModule>，可手动更改。角色拥有的功能模块权限。
 */

public interface IGprRoleModuleUntDal extends IBaseUntDal<GprRoleModule> {

 
	public int deleteByModuleId(String  moduleId);

	public List<GprRoleModule> getListByModuleId(String  moduleId);
 
	public int deleteByRoleId(String  roleId);

	public List<GprRoleModule> getListByRoleId(String  roleId);
   
   
}






