package com.jusfoun.dao.unity.gp;

import java.util.List;
import com.jusfoun.dao.unity.base.IBaseUntDal;
import com.jusfoun.ent.extend.gp.GprCatalogInterface;
import com.jusfoun.ent.extend.gp.GpCatalogInterface;
import com.jusfoun.ent.extend.gp.GpInterface;




/**
 * @author Zee
 * @createDate 2017/05/22 14:01:29
 * @updateDate 2021/1/4 14:18:29
 * @description 扩展自实体类IBaseUntDal<GprCatalogInterface>，可手动更改。后台接口所属分类。
 */

public interface IGprCatalogInterfaceUntDal extends IBaseUntDal<GprCatalogInterface> {

 
	public int deleteByCatalogId(String  catalogId);
    
    public int deleteByCatalogIdList(List<String> catalogIdList);

	public List<GprCatalogInterface> getListByCatalogId(String  catalogId);
 
	public int deleteByInterfaceId(String  interfaceId);
    
    public int deleteByInterfaceIdList(List<String> interfaceIdList);

	public List<GprCatalogInterface> getListByInterfaceId(String  interfaceId);
        
 public int deleteByCompositeIdList(List<GprCatalogInterface> gprCatalogInterfaceList);   
   

   
}






