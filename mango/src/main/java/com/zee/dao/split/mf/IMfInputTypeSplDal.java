package com.zee.dao.split.mf;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.mf.MfInputType;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018-6-13 19:07:53
 * @description 扩展自实体类IBaseUntDal<MfInputType>，可手动更改。投入品种类监管
 */

public interface IMfInputTypeSplDal extends IBaseSplDal {

	public int add(MfInputType mfInputType);

	public int addList(ArrayList<MfInputType> mfInputTypeList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(MfInputType mfInputType);

	public MfInputType getModel(String id);

	public List<MfInputType> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





