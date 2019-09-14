package com.jusfoun.dao.split.da;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jusfoun.dao.split.base.IBaseSplDal;
import com.jusfoun.ent.extend.da.DarTemplateTarget;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018-6-13 19:04:23
 * @description 扩展自实体类IBaseUntDal<DarTemplateTarget>，可手动更改。模板指标中间表
 */

public interface IDarTemplateTargetSplDal extends IBaseSplDal {

	public int add(DarTemplateTarget darTemplateTarget);

	public int addList(ArrayList<DarTemplateTarget> darTemplateTargetList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(DarTemplateTarget darTemplateTarget);

	public DarTemplateTarget getModel(String id);

	public List<DarTemplateTarget> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





