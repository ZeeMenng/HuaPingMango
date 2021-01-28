package com.zee.dao.split.da;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.da.DaSalePeasant;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018-6-13 19:04:23
 * @description 扩展自实体类IBaseUntDal<DaSalePeasant>，可手动更改。农户销售数据表
 */

public interface IDaSalePeasantSplDal extends IBaseSplDal {

	public int add(DaSalePeasant daSalePeasant);

	public int addList(ArrayList<DaSalePeasant> daSalePeasantList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(DaSalePeasant daSalePeasant);

	public DaSalePeasant getModel(String id);

	public List<DaSalePeasant> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





