package com.zee.dao.split.da;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.da.DaSaleEcommerceOrder;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018-6-13 19:04:23
 * @description 扩展自实体类IBaseUntDal<DaSaleEcommerceOrder>，可手动更改。电商数据表，用于存放直报的电商数据
 */

public interface IDaSaleEcommerceOrderSplDal extends IBaseSplDal {

	public int add(DaSaleEcommerceOrder daSaleEcommerceOrder);

	public int addList(ArrayList<DaSaleEcommerceOrder> daSaleEcommerceOrderList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(DaSaleEcommerceOrder daSaleEcommerceOrder);

	public DaSaleEcommerceOrder getModel(String id);

	public List<DaSaleEcommerceOrder> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





