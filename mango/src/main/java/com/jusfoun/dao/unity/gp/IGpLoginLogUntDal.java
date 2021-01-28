﻿package com.zee.dao.unity.gp;

import java.util.List;
import com.zee.dao.unity.base.IBaseUntDal;
import com.zee.ent.extend.gp.GpLoginLog;
import com.zee.ent.extend.gp.GpDomain;
import com.zee.ent.extend.gp.GpUser;
import com.zee.ent.extend.gp.GpOperLogLogin;
import com.zee.ent.extend.gp.GpToken;




/**
 * @author Zee
 * @createDate 2017/05/22 14:01:29
 * @updateDate 2021/1/28 16:07:45
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






