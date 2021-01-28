package com.zee.dao.split.mf;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.mf.MfMarketPriceCraw;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018-6-13 19:07:53
 * @description 扩展自实体类IBaseUntDal<MfMarketPriceCraw>，可手动更改。市场价格数据表（采集）
 */

public interface IMfMarketPriceCrawSplDal extends IBaseSplDal {

	public int add(MfMarketPriceCraw mfMarketPriceCraw);

	public int addList(ArrayList<MfMarketPriceCraw> mfMarketPriceCrawList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(MfMarketPriceCraw mfMarketPriceCraw);

	public MfMarketPriceCraw getModel(String id);

	public List<MfMarketPriceCraw> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





