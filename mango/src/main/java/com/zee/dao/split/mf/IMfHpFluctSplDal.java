package com.zee.dao.split.mf;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.mf.MfHpFluct;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018-6-13 19:07:53
 * @description 扩展自实体类IBaseUntDal<MfHpFluct>，可手动更改。批发价格周期性波动规律分析
 */

public interface IMfHpFluctSplDal extends IBaseSplDal {

	public int add(MfHpFluct mfHpFluct);

	public int addList(ArrayList<MfHpFluct> mfHpFluctList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(MfHpFluct mfHpFluct);

	public MfHpFluct getModel(String id);

	public List<MfHpFluct> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





