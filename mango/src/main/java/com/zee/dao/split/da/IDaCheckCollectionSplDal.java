package com.zee.dao.split.da;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.da.DaCheckCollection;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018-6-13 19:04:23
 * @description 扩展自实体类IBaseUntDal<DaCheckCollection>，可手动更改。采样数据表
 */

public interface IDaCheckCollectionSplDal extends IBaseSplDal {

	public int add(DaCheckCollection daCheckCollection);

	public int addList(ArrayList<DaCheckCollection> daCheckCollectionList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(DaCheckCollection daCheckCollection);

	public DaCheckCollection getModel(String id);

	public List<DaCheckCollection> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





