﻿package com.zee.dao.unity.gp;

import java.util.List;
import com.zee.dao.unity.base.IBaseUntDal;
import com.zee.ent.extend.gp.GpModule;
import com.zee.ent.extend.gp.GpDomain;
import com.zee.ent.extend.gp.GpModule;
import com.zee.ent.extend.gp.GpModule;
import com.zee.ent.extend.gp.GprModulePage;
import com.zee.ent.extend.gp.GprRoleModule;




/**
 * @author Zee
 * @createDate 2017/05/22 14:01:29
 * @updateDate 2021/2/2 10:32:05
 * @description 扩展自实体类IBaseUntDal<GpModule>，可手动更改。功能模块。
 */

public interface IGpModuleUntDal extends IBaseUntDal<GpModule> {

 
	public int deleteByDomainId(String  domainId);
    
    public int deleteByDomainIdList(List<String> domainIdList);

	public List<GpModule> getListByDomainId(String  domainId);
   

   
}






