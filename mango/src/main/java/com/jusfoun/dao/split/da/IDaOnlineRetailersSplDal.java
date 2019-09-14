package com.jusfoun.dao.split.da;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jusfoun.dao.split.base.IBaseSplDal;
import com.jusfoun.ent.extend.da.DaOnlineRetailers;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018-6-25 10:47:17
 * @description 扩展自实体类IBaseUntDal<DaOnlineRetailers>，可手动更改。芒果电商分部表
 */

public interface IDaOnlineRetailersSplDal extends IBaseSplDal {

	public int add(DaOnlineRetailers daOnlineRetailers);

	public int addList(ArrayList<DaOnlineRetailers> daOnlineRetailersList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(DaOnlineRetailers daOnlineRetailers);

	public DaOnlineRetailers getModel(String id);

	public List<DaOnlineRetailers> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





