package com.zee.dao.split.da;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.da.DaTargetType;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018-6-13 19:04:23
 * @description 扩展自实体类IBaseUntDal<DaTargetType>，可手动更改。指标类型
 */

public interface IDaTargetTypeSplDal extends IBaseSplDal {

	public int add(DaTargetType daTargetType);

	public int addList(ArrayList<DaTargetType> daTargetTypeList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(DaTargetType daTargetType);

	public DaTargetType getModel(String id);

	public List<DaTargetType> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





