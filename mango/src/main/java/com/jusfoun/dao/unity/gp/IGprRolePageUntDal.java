package com.jusfoun.dao.unity.gp;

import java.util.List;
import com.jusfoun.dao.unity.base.IBaseUntDal;
import com.jusfoun.ent.extend.gp.GprRolePage;
import com.jusfoun.ent.extend.gp.GpPage;
import com.jusfoun.ent.extend.gp.GpRole;




/**
 * @author Zee
 * @createDate 2017/05/22 14:01:29
 * @updateDate 2020/10/13 20:02:20
 * @description 扩展自实体类IBaseUntDal<GprRolePage>，可手动更改。角色拥有的页面权限。
 */

public interface IGprRolePageUntDal extends IBaseUntDal<GprRolePage> {

 
	public int deleteByPageId(String  pageId);
    
    public int deleteByPageIdList(List<String> pageIdList);

	public List<GprRolePage> getListByPageId(String  pageId);
 
	public int deleteByRoleId(String  roleId);
    
    public int deleteByRoleIdList(List<String> roleIdList);

	public List<GprRolePage> getListByRoleId(String  roleId);
   
   
}






