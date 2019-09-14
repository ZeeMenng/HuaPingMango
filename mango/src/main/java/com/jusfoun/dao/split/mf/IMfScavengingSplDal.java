package com.jusfoun.dao.split.mf;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jusfoun.dao.split.base.IBaseSplDal;
import com.jusfoun.ent.extend.mf.MfScavenging;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018-6-13 19:07:53
 * @description 扩展自实体类IBaseUntDal<MfScavenging>，可手动更改。扫码反馈表
 */

public interface IMfScavengingSplDal extends IBaseSplDal {

	public int add(MfScavenging mfScavenging);

	public int addList(ArrayList<MfScavenging> mfScavengingList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(MfScavenging mfScavenging);

	public MfScavenging getModel(String id);

	public List<MfScavenging> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





