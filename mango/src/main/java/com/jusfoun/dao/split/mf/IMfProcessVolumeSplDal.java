package com.jusfoun.dao.split.mf;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jusfoun.dao.split.base.IBaseSplDal;
import com.jusfoun.ent.extend.mf.MfProcessVolume;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018-6-13 19:07:53
 * @description 扩展自实体类IBaseUntDal<MfProcessVolume>，可手动更改。mf_process_volume
 */

public interface IMfProcessVolumeSplDal extends IBaseSplDal {

	public int add(MfProcessVolume mfProcessVolume);

	public int addList(ArrayList<MfProcessVolume> mfProcessVolumeList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(MfProcessVolume mfProcessVolume);

	public MfProcessVolume getModel(String id);

	public List<MfProcessVolume> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





