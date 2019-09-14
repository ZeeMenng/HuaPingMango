package com.jusfoun.dao.split.da;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jusfoun.dao.split.base.IBaseSplDal;
import com.jusfoun.ent.extend.da.DaTemplate;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018-6-13 19:04:23
 * @description 扩展自实体类IBaseUntDal<DaTemplate>，可手动更改。指标模板表
 */

public interface IDaTemplateSplDal extends IBaseSplDal {

	public int add(DaTemplate daTemplate);

	public int addList(ArrayList<DaTemplate> daTemplateList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(DaTemplate daTemplate);

	public DaTemplate getModel(String id);

	public List<DaTemplate> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





