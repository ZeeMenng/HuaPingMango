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
 * @updateDate 2020/10/13 20:02:25
 * @description 扩展自实体类IBaseUntDal<GpOperLogLogin>，可手动更改。登录用户操作日志。
 */

public interface IGpOperLogLoginUntDal extends IBaseUntDal<GpOperLogLogin> {

 
	public int deleteByLoginLogId(String  loginLogId);
    
    public int deleteByLoginLogIdList(List<String> loginLogIdList);

	public List<GpOperLogLogin> getListByLoginLogId(String  loginLogId);
 
	public int deleteByOperLogId(String  operLogId);
    
    public int deleteByOperLogIdList(List<String> operLogIdList);

	public List<GpOperLogLogin> getListByOperLogId(String  operLogId);
 
	public int deleteByTokenId(String  tokenId);
    
    public int deleteByTokenIdList(List<String> tokenIdList);

	public List<GpOperLogLogin> getListByTokenId(String  tokenId);
 
	public int deleteByUserId(String  userId);
    
    public int deleteByUserIdList(List<String> userIdList);

	public List<GpOperLogLogin> getListByUserId(String  userId);
   
   
}






