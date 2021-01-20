package com.jusfoun.dao.unity.gp;

import java.util.List;
import com.jusfoun.dao.unity.base.IBaseUntDal;
import com.jusfoun.ent.extend.gp.GprConfigDomain;
import com.jusfoun.ent.extend.gp.GpDomain;
import com.jusfoun.ent.extend.gp.GpConfig;




/**
 * @author Zee
 * @createDate 2017/05/22 14:01:29
 * @updateDate 2021/1/20 10:44:20
 * @description 扩展自实体类IBaseUntDal<GprConfigDomain>，可手动更改。应用领域配置信息。
 */

public interface IGprConfigDomainUntDal extends IBaseUntDal<GprConfigDomain> {

 
	public int deleteByDomainId(String  domainId);
    
    public int deleteByDomainIdList(List<String> domainIdList);

	public List<GprConfigDomain> getListByDomainId(String  domainId);
 
	public int deleteByConfigId(String  configId);
    
    public int deleteByConfigIdList(List<String> configIdList);

	public List<GprConfigDomain> getListByConfigId(String  configId);
        
	public int deleteByCompositeIdList(List<GprConfigDomain> gprConfigDomainList);   
   

   
}






