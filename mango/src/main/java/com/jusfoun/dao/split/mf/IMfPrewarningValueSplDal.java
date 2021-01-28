package com.zee.dao.split.mf;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.mf.MfPrewarningValue;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018-9-26 15:07:44
 * @description 扩展自实体类IBaseUntDal<MfPrewarningValue>，可手动更改。质量安全预警值设置
 */

public interface IMfPrewarningValueSplDal extends IBaseSplDal {

	public int add(MfPrewarningValue mfPrewarningValue);

	public int addList(ArrayList<MfPrewarningValue> mfPrewarningValueList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(MfPrewarningValue mfPrewarningValue);

	public MfPrewarningValue getModel(String id);

	public List<MfPrewarningValue> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





