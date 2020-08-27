package com.jusfoun.dao.unity.gp;

import java.util.List;
import com.jusfoun.dao.unity.base.IBaseUntDal;
import com.jusfoun.ent.extend.gp.GpModule;
import com.jusfoun.ent.extend.gp.GpDomain;
import com.jusfoun.ent.extend.gp.GpModule;
import com.jusfoun.ent.extend.gp.GpModule;
import com.jusfoun.ent.extend.gp.GpResource;
import com.jusfoun.ent.extend.gp.GprModulePage;
import com.jusfoun.ent.extend.gp.GprRoleModule;




/**
 * @author Zee
 * @createDate 2017/05/22 14:01:29
 * @updateDate 2020/8/27 11:19:25
 * @description 扩展自实体类IBaseUntDal<GpModule>，可手动更改。功能模块。
 */

public interface IGpModuleUntDal extends IBaseUntDal<GpModule> {

 
	public int deleteByDomainId(String  domainId);

	public List<GpModule> getListByDomainId(String  domainId);
   

 	public int deleteByFartherId(String  fartherId);

	public List<GpModule> getListByFartherId(String  fartherId);
   
}






