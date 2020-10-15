package com.jusfoun.dao.unity.gp;

import java.util.List;
import com.jusfoun.dao.unity.base.IBaseUntDal;
import com.jusfoun.ent.extend.gp.GpPage;
import com.jusfoun.ent.extend.gp.GpControl;
import com.jusfoun.ent.extend.gp.GpDomain;
import com.jusfoun.ent.extend.gp.GpResource;
import com.jusfoun.ent.extend.gp.GprModulePage;
import com.jusfoun.ent.extend.gp.GprRolePage;




/**
 * @author Zee
 * @createDate 2017/05/22 14:01:29
 * @updateDate 2020/10/13 20:02:26
 * @description 扩展自实体类IBaseUntDal<GpPage>，可手动更改。系统页面。
 */

public interface IGpPageUntDal extends IBaseUntDal<GpPage> {

 
	public int deleteByDomainId(String  domainId);
    
    public int deleteByDomainIdList(List<String> domainIdList);

	public List<GpPage> getListByDomainId(String  domainId);
   
   
}






