package com.jusfoun.dao.unity.gp;

import java.util.List;
import com.jusfoun.dao.unity.base.IBaseUntDal;
import com.jusfoun.ent.extend.gp.GprConfig;
import com.jusfoun.ent.extend.gp.GpDomain;
import com.jusfoun.ent.extend.gp.GpConfig;
import com.jusfoun.ent.extend.gp.GpPage;
import com.jusfoun.ent.extend.gp.GpControl;




/**
 * @author Zee
 * @createDate 2017/05/22 14:01:29
 * @updateDate 2021/1/18 19:49:33
 * @description 扩展自实体类IBaseUntDal<GprConfig>，可手动更改。应用领域配置信息。
 */

public interface IGprConfigUntDal extends IBaseUntDal<GprConfig> {

 
	public int deleteByBusinessId(String  businessId);
    
    public int deleteByBusinessIdList(List<String> businessIdList);

	public List<GprConfig> getListByBusinessId(String  businessId);
 
	public int deleteByConfigId(String  configId);
    
    public int deleteByConfigIdList(List<String> configIdList);

	public List<GprConfig> getListByConfigId(String  configId);
 
	public int deleteByBusinessId(String  businessId);
    
    public int deleteByBusinessIdList(List<String> businessIdList);

	public List<GprConfig> getListByBusinessId(String  businessId);
 
	public int deleteByBusinessId(String  businessId);
    
    public int deleteByBusinessIdList(List<String> businessIdList);

	public List<GprConfig> getListByBusinessId(String  businessId);
        
	public int deleteByCompositeIdList(List<GprConfig> gprConfigList);   
   

   
}






