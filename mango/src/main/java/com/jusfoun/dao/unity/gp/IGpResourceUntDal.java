package com.jusfoun.dao.unity.gp;

import java.util.List;
import com.jusfoun.dao.unity.base.IBaseUntDal;
import com.jusfoun.ent.extend.gp.GpResource;
import com.jusfoun.ent.extend.gp.GpDomain;
import com.jusfoun.ent.extend.gp.GpModule;
import com.jusfoun.ent.extend.gp.GpPage;
import com.jusfoun.ent.extend.gp.GprResource;




/**
 * @author Zee
 * @createDate 2017/05/22 14:01:29
 * @updateDate 2020/10/13 20:02:27
 * @description 扩展自实体类IBaseUntDal<GpResource>，可手动更改。文件信息。
 */

public interface IGpResourceUntDal extends IBaseUntDal<GpResource> {

 
	public int deleteByDomainId(String  domainId);
    
    public int deleteByDomainIdList(List<String> domainIdList);

	public List<GpResource> getListByDomainId(String  domainId);
 
	public int deleteByModuleId(String  moduleId);
    
    public int deleteByModuleIdList(List<String> moduleIdList);

	public List<GpResource> getListByModuleId(String  moduleId);
 
	public int deleteByPageId(String  pageId);
    
    public int deleteByPageIdList(List<String> pageIdList);

	public List<GpResource> getListByPageId(String  pageId);
   
   
}






