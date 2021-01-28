package com.zee.dao.split.da;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.da.DaWholesaleMarket;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018-6-25 10:47:17
 * @description 扩展自实体类IBaseUntDal<DaWholesaleMarket>，可手动更改。批发市场
 */

public interface IDaWholesaleMarketSplDal extends IBaseSplDal {

	public int add(DaWholesaleMarket daWholesaleMarket);

	public int addList(ArrayList<DaWholesaleMarket> daWholesaleMarketList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(DaWholesaleMarket daWholesaleMarket);

	public DaWholesaleMarket getModel(String id);

	public List<DaWholesaleMarket> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





