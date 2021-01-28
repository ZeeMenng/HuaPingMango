package com.zee.dao.split.mf;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.mf.MfHpSeason;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018-6-13 19:07:53
 * @description 扩展自实体类IBaseUntDal<MfHpSeason>，可手动更改。批发价格季节性波动规律分析
 */

public interface IMfHpSeasonSplDal extends IBaseSplDal {

	public int add(MfHpSeason mfHpSeason);

	public int addList(ArrayList<MfHpSeason> mfHpSeasonList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(MfHpSeason mfHpSeason);

	public MfHpSeason getModel(String id);

	public List<MfHpSeason> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





