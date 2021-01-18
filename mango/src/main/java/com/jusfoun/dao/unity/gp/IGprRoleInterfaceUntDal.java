package com.jusfoun.dao.unity.gp;

import java.util.List;
import com.jusfoun.dao.unity.base.IBaseUntDal;
import com.jusfoun.ent.extend.gp.GprRoleInterface;
import com.jusfoun.ent.extend.gp.GpInterface;
import com.jusfoun.ent.extend.gp.GpRole;




/**
 * @author Zee
 * @createDate 2017/05/22 14:01:29
 * @updateDate 2021/1/4 14:18:31
 * @description 扩展自实体类IBaseUntDal<GprRoleInterface>，可手动更改。角色拥有的接口权限。
 */

public interface IGprRoleInterfaceUntDal extends IBaseUntDal<GprRoleInterface> {

 
	public int deleteByInterfaceId(String  interfaceId);
    
    public int deleteByInterfaceIdList(List<String> interfaceIdList);

	public List<GprRoleInterface> getListByInterfaceId(String  interfaceId);
 
	public int deleteByRoleId(String  roleId);
    
    public int deleteByRoleIdList(List<String> roleIdList);

	public List<GprRoleInterface> getListByRoleId(String  roleId);
        
 public int deleteByCompositeIdList(List<GprRoleInterface> gprRoleInterfaceList);   
   

   
}






