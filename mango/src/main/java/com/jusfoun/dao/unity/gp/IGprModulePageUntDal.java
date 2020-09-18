package com.jusfoun.dao.unity.gp;

import java.util.List;
import com.jusfoun.dao.unity.base.IBaseUntDal;
import com.jusfoun.ent.extend.gp.GprModulePage;
import com.jusfoun.ent.extend.gp.GpModule;
import com.jusfoun.ent.extend.gp.GpPage;




/**
 * @author Zee
 * @createDate 2017/05/22 14:01:29
 * @updateDate 2020/9/18 15:38:47
 * @description 扩展自实体类IBaseUntDal<GprModulePage>，可手动更改。功能模块所包含的页面。
 */

public interface IGprModulePageUntDal extends IBaseUntDal<GprModulePage> {

 
	public int deleteByModuleId(String  moduleId);

	public List<GprModulePage> getListByModuleId(String  moduleId);
 
	public int deleteByPageId(String  pageId);

	public List<GprModulePage> getListByPageId(String  pageId);
   
   
}






