package com.jusfoun.dao.unity.gp;

import java.util.List;
import com.jusfoun.dao.unity.base.IBaseUntDal;
import com.jusfoun.ent.extend.gp.GprUserStation;
import com.jusfoun.ent.extend.gp.GpStation;
import com.jusfoun.ent.extend.gp.GpUser;




/**
 * @author Zee
 * @createDate 2017/05/22 14:01:29
 * @updateDate 2020/10/13 20:02:21
 * @description 扩展自实体类IBaseUntDal<GprUserStation>，可手动更改。用户所属岗位。
 */

public interface IGprUserStationUntDal extends IBaseUntDal<GprUserStation> {

 
	public int deleteByStationId(String  stationId);
    
    public int deleteByStationIdList(List<String> stationIdList);

	public List<GprUserStation> getListByStationId(String  stationId);
 
	public int deleteByUserId(String  userId);
    
    public int deleteByUserIdList(List<String> userIdList);

	public List<GprUserStation> getListByUserId(String  userId);
   
   
}






