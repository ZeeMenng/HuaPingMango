﻿package com.zee.dao.unity.gp;

import java.util.List;
import com.zee.dao.unity.base.IBaseUntDal;
import com.zee.ent.extend.gp.GpStation;
import com.zee.ent.extend.gp.GpOrganization;
import com.zee.ent.extend.gp.GprUserStation;




/**
 * @author Zee
 * @createDate 2017/05/22 14:01:29
 * @updateDate 2021/1/28 16:07:50
 * @description 扩展自实体类IBaseUntDal<GpStation>，可手动更改。岗位。
 */

public interface IGpStationUntDal extends IBaseUntDal<GpStation> {

 
	public int deleteByOrganizationId(String  organizationId);
    
    public int deleteByOrganizationIdList(List<String> organizationIdList);

	public List<GpStation> getListByOrganizationId(String  organizationId);
   

   
}





