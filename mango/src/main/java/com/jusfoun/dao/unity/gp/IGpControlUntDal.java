package com.jusfoun.dao.unity.gp;

import java.util.List;
import com.jusfoun.dao.unity.base.IBaseUntDal;
import com.jusfoun.ent.extend.gp.GpControl;
import com.jusfoun.ent.extend.gp.GpDomain;
import com.jusfoun.ent.extend.gp.GpPage;
import com.jusfoun.ent.extend.gp.GprRoleControl;




/**
 * @author Zee
 * @createDate 2017/05/22 14:01:29
 * @updateDate 2021/1/4 14:18:33
 * @description 扩展自实体类IBaseUntDal<GpControl>，可手动更改。系统控件。
 */

public interface IGpControlUntDal extends IBaseUntDal<GpControl> {

 
	public int deleteByDomainId(String  domainId);
    
    public int deleteByDomainIdList(List<String> domainIdList);

	public List<GpControl> getListByDomainId(String  domainId);
 
	public int deleteByPageId(String  pageId);
    
    public int deleteByPageIdList(List<String> pageIdList);

	public List<GpControl> getListByPageId(String  pageId);
        
 public int deleteByCompositeIdList(List<GpControl> gpControlList);   
   

   
}






