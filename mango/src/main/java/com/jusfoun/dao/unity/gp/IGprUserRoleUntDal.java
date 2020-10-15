package com.jusfoun.dao.unity.gp;

import java.util.List;
import com.jusfoun.dao.unity.base.IBaseUntDal;
import com.jusfoun.ent.extend.gp.GprUserRole;
import com.jusfoun.ent.extend.gp.GpRole;
import com.jusfoun.ent.extend.gp.GpUser;




/**
 * @author Zee
 * @createDate 2017/05/22 14:01:29
 * @updateDate 2020/10/13 20:02:21
 * @description 扩展自实体类IBaseUntDal<GprUserRole>，可手动更改。用户拥有的角色。
 */

public interface IGprUserRoleUntDal extends IBaseUntDal<GprUserRole> {

 
	public int deleteByRoleId(String  roleId);
    
    public int deleteByRoleIdList(List<String> roleIdList);

	public List<GprUserRole> getListByRoleId(String  roleId);
 
	public int deleteByUserId(String  userId);
    
    public int deleteByUserIdList(List<String> userIdList);

	public List<GprUserRole> getListByUserId(String  userId);
   
   
}






