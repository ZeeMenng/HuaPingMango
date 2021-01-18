package com.jusfoun.dao.unity.gp;

import java.util.List;
import com.jusfoun.dao.unity.base.IBaseUntDal;
import com.jusfoun.ent.extend.gp.GpToken;
import com.jusfoun.ent.extend.gp.GpOperLogLogin;
import com.jusfoun.ent.extend.gp.GpDomain;
import com.jusfoun.ent.extend.gp.GpLoginLog;
import com.jusfoun.ent.extend.gp.GpUser;




/**
 * @author Zee
 * @createDate 2017/05/22 14:01:29
 * @updateDate 2021/1/4 14:18:38
 * @description 扩展自实体类IBaseUntDal<GpToken>，可手动更改。token信息。
 */

public interface IGpTokenUntDal extends IBaseUntDal<GpToken> {

 
	public int deleteByDomainId(String  domainId);
    
    public int deleteByDomainIdList(List<String> domainIdList);

	public List<GpToken> getListByDomainId(String  domainId);
 
	public int deleteByUserId(String  userId);
    
    public int deleteByUserIdList(List<String> userIdList);

	public List<GpToken> getListByUserId(String  userId);
        
 public int deleteByCompositeIdList(List<GpToken> gpTokenList);   
   

   
}






