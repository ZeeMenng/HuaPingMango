package com.jusfoun.dao.unity.gp;

import java.util.List;
import com.jusfoun.dao.unity.base.IBaseUntDal;
import com.jusfoun.ent.extend.gp.GprConfigUser;
import com.jusfoun.ent.extend.gp.GpUser;
import com.jusfoun.ent.extend.gp.GpConfig;




/**
 * @author Zee
 * @createDate 2017/05/22 14:01:29
 * @updateDate 2021/1/18 19:49:33
 * @description 扩展自实体类IBaseUntDal<GprConfigUser>，可手动更改。用户配置信息。
 */

public interface IGprConfigUserUntDal extends IBaseUntDal<GprConfigUser> {

 
	public int deleteByUserId(String  userId);
    
    public int deleteByUserIdList(List<String> userIdList);

	public List<GprConfigUser> getListByUserId(String  userId);
 
	public int deleteByConfigId(String  configId);
    
    public int deleteByConfigIdList(List<String> configIdList);

	public List<GprConfigUser> getListByConfigId(String  configId);
        
	public int deleteByCompositeIdList(List<GprConfigUser> gprConfigUserList);   
   

   
}






