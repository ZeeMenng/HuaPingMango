package com.zee.dao.split.mf;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.mf.MfFreshVolume;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018-6-13 19:07:53
 * @description 扩展自实体类IBaseUntDal<MfFreshVolume>，可手动更改。mf_fresh_volume
 */

public interface IMfFreshVolumeSplDal extends IBaseSplDal {

	public int add(MfFreshVolume mfFreshVolume);

	public int addList(ArrayList<MfFreshVolume> mfFreshVolumeList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(MfFreshVolume mfFreshVolume);

	public MfFreshVolume getModel(String id);

	public List<MfFreshVolume> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





