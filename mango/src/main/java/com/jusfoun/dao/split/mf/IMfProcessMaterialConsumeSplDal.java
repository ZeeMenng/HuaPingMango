package com.jusfoun.dao.split.mf;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jusfoun.dao.split.base.IBaseSplDal;
import com.jusfoun.ent.extend.mf.MfProcessMaterialConsume;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018-6-13 19:07:53
 * @description 扩展自实体类IBaseUntDal<MfProcessMaterialConsume>，可手动更改。加工品原料消耗情况预测表
 */

public interface IMfProcessMaterialConsumeSplDal extends IBaseSplDal {

	public int add(MfProcessMaterialConsume mfProcessMaterialConsume);

	public int addList(ArrayList<MfProcessMaterialConsume> mfProcessMaterialConsumeList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(MfProcessMaterialConsume mfProcessMaterialConsume);

	public MfProcessMaterialConsume getModel(String id);

	public List<MfProcessMaterialConsume> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





