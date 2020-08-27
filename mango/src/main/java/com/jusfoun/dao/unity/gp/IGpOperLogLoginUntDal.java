package com.jusfoun.dao.unity.gp;

import java.util.List;
import com.jusfoun.dao.unity.base.IBaseUntDal;
import com.jusfoun.ent.extend.gp.GpOperLogLogin;
import com.jusfoun.ent.extend.gp.GpLoginLog;
import com.jusfoun.ent.extend.gp.GpOperLog;
import com.jusfoun.ent.extend.gp.GpToken;
import com.jusfoun.ent.extend.gp.GpUser;




/**
 * @author Zee
 * @createDate 2017/05/22 14:01:29
 * @updateDate 2020/8/27 11:19:25
 * @description 扩展自实体类IBaseUntDal<GpOperLogLogin>，可手动更改。登录用户操作日志。
 */

public interface IGpOperLogLoginUntDal extends IBaseUntDal<GpOperLogLogin> {

 
	public int deleteByLoginLogId(String  loginLogId);

	public List<GpOperLogLogin> getListByLoginLogId(String  loginLogId);
 
	public int deleteByOperLogId(String  operLogId);

	public List<GpOperLogLogin> getListByOperLogId(String  operLogId);
 
	public int deleteByTokenId(String  tokenId);

	public List<GpOperLogLogin> getListByTokenId(String  tokenId);
 
	public int deleteByUserId(String  userId);

	public List<GpOperLogLogin> getListByUserId(String  userId);
   
   
}






