package com.jusfoun.dao.unity.gp;

import java.util.List;
import com.jusfoun.dao.unity.base.IBaseUntDal;
import com.jusfoun.ent.extend.gp.GprResource;
import com.jusfoun.ent.extend.gp.GpResource;




/**
 * @author Zee
 * @createDate 2017/05/22 14:01:29
 * @updateDate 2020/10/13 20:02:18
 * @description 扩展自实体类IBaseUntDal<GprResource>，可手动更改。附件关联表。只要存有附件字段的表，都会通过此表于gp_resource表关联。
 */

public interface IGprResourceUntDal extends IBaseUntDal<GprResource> {

 
	public int deleteByResourceId(String  resourceId);
    
    public int deleteByResourceIdList(List<String> resourceIdList);

	public List<GprResource> getListByResourceId(String  resourceId);
   
   
}






