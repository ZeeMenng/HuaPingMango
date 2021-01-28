package com.zee.dao.split.da;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.da.DaTemplateData;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018-6-13 19:04:23
 * @description 扩展自实体类IBaseUntDal<DaTemplateData>，可手动更改。模板数据表
 */

public interface IDaTemplateDataSplDal extends IBaseSplDal {

	public int add(DaTemplateData daTemplateData);

	public int addList(ArrayList<DaTemplateData> daTemplateDataList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(DaTemplateData daTemplateData);

	public DaTemplateData getModel(String id);

	public List<DaTemplateData> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





