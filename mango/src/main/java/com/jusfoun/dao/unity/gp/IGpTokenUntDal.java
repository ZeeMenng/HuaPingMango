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
 * @updateDate 2020/9/18 15:38:57
 * @description 扩展自实体类IBaseUntDal<GpToken>，可手动更改。token信息。
 */

public interface IGpTokenUntDal extends IBaseUntDal<GpToken> {

 
	public int deleteByDomainId(String  domainId);

	public List<GpToken> getListByDomainId(String  domainId);
 
	public int deleteById(String  id);

	public List<GpToken> getListById(String  id);
 
	public int deleteByUserId(String  userId);

	public List<GpToken> getListByUserId(String  userId);
   
   
}






