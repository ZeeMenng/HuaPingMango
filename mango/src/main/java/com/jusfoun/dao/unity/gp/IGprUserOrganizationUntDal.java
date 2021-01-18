package com.jusfoun.dao.unity.gp;

import java.util.List;
import com.jusfoun.dao.unity.base.IBaseUntDal;
import com.jusfoun.ent.extend.gp.GprUserOrganization;
import com.jusfoun.ent.extend.gp.GpOrganization;
import com.jusfoun.ent.extend.gp.GpUser;




/**
 * @author Zee
 * @createDate 2017/05/22 14:01:29
 * @updateDate 2021/1/4 14:18:32
 * @description 扩展自实体类IBaseUntDal<GprUserOrganization>，可手动更改。用户所属组织机构。
 */

public interface IGprUserOrganizationUntDal extends IBaseUntDal<GprUserOrganization> {

 
	public int deleteByOrganizationId(String  organizationId);
    
    public int deleteByOrganizationIdList(List<String> organizationIdList);

	public List<GprUserOrganization> getListByOrganizationId(String  organizationId);
 
	public int deleteByUserId(String  userId);
    
    public int deleteByUserIdList(List<String> userIdList);

	public List<GprUserOrganization> getListByUserId(String  userId);
        
 public int deleteByCompositeIdList(List<GprUserOrganization> gprUserOrganizationList);   
   

   
}






