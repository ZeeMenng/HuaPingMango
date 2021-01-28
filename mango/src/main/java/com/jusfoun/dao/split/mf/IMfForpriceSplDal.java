package com.zee.dao.split.mf;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.mf.MfForprice;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018-6-13 19:07:53
 * @description 扩展自实体类IBaseUntDal<MfForprice>，可手动更改。价格预测
 */

public interface IMfForpriceSplDal extends IBaseSplDal {

	public int add(MfForprice mfForprice);

	public int addList(ArrayList<MfForprice> mfForpriceList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(MfForprice mfForprice);

	public MfForprice getModel(String id);

	public List<MfForprice> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





