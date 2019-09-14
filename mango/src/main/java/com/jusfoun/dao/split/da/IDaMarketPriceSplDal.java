package com.jusfoun.dao.split.da;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jusfoun.dao.split.base.IBaseSplDal;
import com.jusfoun.ent.extend.da.DaMarketPrice;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018-6-13 19:04:23
 * @description 扩展自实体类IBaseUntDal<DaMarketPrice>，可手动更改。市场价格数据表
 */

public interface IDaMarketPriceSplDal extends IBaseSplDal {

	public int add(DaMarketPrice daMarketPrice);

	public int addList(ArrayList<DaMarketPrice> daMarketPriceList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(DaMarketPrice daMarketPrice);

	public DaMarketPrice getModel(String id);

	public List<DaMarketPrice> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





