package com.jusfoun.dao.unity.gp;

import java.util.List;
import com.jusfoun.dao.unity.base.IBaseUntDal;
import com.jusfoun.ent.extend.gp.GpLoginLog;
import com.jusfoun.ent.extend.gp.GpDomain;
import com.jusfoun.ent.extend.gp.GpUser;
import com.jusfoun.ent.extend.gp.GpOperLogLogin;
import com.jusfoun.ent.extend.gp.GpToken;




/**
 * @author Zee
 * @createDate 2017/05/22 14:01:29
 * @updateDate 2021/1/4 14:18:34
 * @description 扩展自实体类IBaseUntDal<GpLoginLog>，可手动更改。登录日志。
 */

public interface IGpLoginLogUntDal extends IBaseUntDal<GpLoginLog> {

 
	public int deleteByDomainId(String  domainId);
    
    public int deleteByDomainIdList(List<String> domainIdList);

	public List<GpLoginLog> getListByDomainId(String  domainId);
 
	public int deleteByUserId(String  userId);
    
    public int deleteByUserIdList(List<String> userIdList);

	public List<GpLoginLog> getListByUserId(String  userId);
        
 public int deleteByCompositeIdList(List<GpLoginLog> gpLoginLogList);   
   

   
}






