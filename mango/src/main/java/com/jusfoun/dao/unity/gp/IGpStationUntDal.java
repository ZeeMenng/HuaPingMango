﻿package com.jusfoun.dao.unity.gp;

import java.util.List;
import com.jusfoun.dao.unity.base.IBaseUntDal;
import com.jusfoun.ent.extend.gp.GpStation;
import com.jusfoun.ent.extend.gp.GpOrganization;
import com.jusfoun.ent.extend.gp.GprUserStation;




/**
 * @author Zee
 * @createDate 2017/05/22 14:01:29
 * @updateDate 2021/1/4 14:18:38
 * @description 扩展自实体类IBaseUntDal<GpStation>，可手动更改。岗位。
 */

public interface IGpStationUntDal extends IBaseUntDal<GpStation> {

 
	public int deleteByOrganizationId(String  organizationId);
    
    public int deleteByOrganizationIdList(List<String> organizationIdList);

	public List<GpStation> getListByOrganizationId(String  organizationId);
   

   
}






