package com.zee.dao.split.da;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.da.DaCommonField;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018-6-13 19:04:23
 * @description 扩展自实体类IBaseUntDal<DaCommonField>，可手动更改。通用字段表
 */

public interface IDaCommonFieldSplDal extends IBaseSplDal {

	public int add(DaCommonField daCommonField);

	public int addList(ArrayList<DaCommonField> daCommonFieldList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(DaCommonField daCommonField);

	public DaCommonField getModel(String id);

	public List<DaCommonField> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





