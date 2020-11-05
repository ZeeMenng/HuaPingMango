﻿package com.jusfoun.dao.unity.gp;

import java.util.List;
import com.jusfoun.dao.unity.base.IBaseUntDal;
import com.jusfoun.ent.extend.gp.GpCatalogInterface;
import com.jusfoun.ent.extend.gp.GpCatalogInterface;
import com.jusfoun.ent.extend.gp.GpCatalogInterface;




/**
 * @author Zee
 * @createDate 2017/05/22 14:01:29
 * @updateDate 2020/11/5 20:16:08
 * @description 扩展自实体类IBaseUntDal<GpCatalogInterface>，可手动更改。接口分类字典。存放接口分类信息，支持树形分级分类，主要但不限于业务上的分类方式，支持同时对接口进行多种分类。
 */

public interface IGpCatalogInterfaceUntDal extends IBaseUntDal<GpCatalogInterface> {

   

 	public int deleteByFartherId(String  fartherId);

	public List<GpCatalogInterface> getListByFartherId(String  fartherId);
   
}






