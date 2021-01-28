package com.zee.dao.split.mf;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.mf.MfWeather;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018-6-13 19:07:53
 * @description 扩展自实体类IBaseUntDal<MfWeather>，可手动更改。气象建模,气象阈值表
 */

public interface IMfWeatherSplDal extends IBaseSplDal {

	public int add(MfWeather mfWeather);

	public int addList(ArrayList<MfWeather> mfWeatherList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(MfWeather mfWeather);

	public MfWeather getModel(String id);

	public List<MfWeather> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





