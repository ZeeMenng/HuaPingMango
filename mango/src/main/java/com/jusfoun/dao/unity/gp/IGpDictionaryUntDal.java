package com.jusfoun.dao.unity.gp;

import java.util.List;
import com.jusfoun.dao.unity.base.IBaseUntDal;
import com.jusfoun.ent.extend.gp.GpDictionary;
import com.jusfoun.ent.extend.gp.GpDictionaryType;




/**
 * @author Zee
 * @createDate 2017/05/22 14:01:29
 * @updateDate 2020/10/13 20:02:22
 * @description 扩展自实体类IBaseUntDal<GpDictionary>，可手动更改。字典信息。
 */

public interface IGpDictionaryUntDal extends IBaseUntDal<GpDictionary> {

 
	public int deleteByTypeId(String  typeId);
    
    public int deleteByTypeIdList(List<String> typeIdList);

	public List<GpDictionary> getListByTypeId(String  typeId);
   
   
}






