package com.zee.dao.split.mf;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.mf.MfForreturn;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018-6-13 19:07:53
 * @description 扩展自实体类IBaseUntDal<MfForreturn>，可手动更改。批发价格波动性预测
 */

public interface IMfForreturnSplDal extends IBaseSplDal {

	public int add(MfForreturn mfForreturn);

	public int addList(ArrayList<MfForreturn> mfForreturnList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(MfForreturn mfForreturn);

	public MfForreturn getModel(String id);

	public List<MfForreturn> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





