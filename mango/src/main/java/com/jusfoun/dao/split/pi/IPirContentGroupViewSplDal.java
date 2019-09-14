package com.jusfoun.dao.split.pi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jusfoun.dao.split.base.IBaseSplDal;
import com.jusfoun.ent.extend.pi.PirContentGroupView;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018/1/16 14:27:23
 * @description 扩展自实体类IBaseUntDal<PirContentGroupView>，可手动更改。CMS内容浏览会员组关联表
 */

public interface IPirContentGroupViewSplDal extends IBaseSplDal {

	public int add(PirContentGroupView pirContentGroupView);

	public int addList(ArrayList<PirContentGroupView> pirContentGroupViewList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(PirContentGroupView pirContentGroupView);

	public PirContentGroupView getModel(String id);

	public List<PirContentGroupView> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





