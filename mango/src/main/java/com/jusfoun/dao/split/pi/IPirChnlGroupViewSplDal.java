package com.zee.dao.split.pi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.pi.PirChnlGroupView;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018/1/16 14:27:23
 * @description 扩展自实体类IBaseUntDal<PirChnlGroupView>，可手动更改。CMS栏目浏览会员组关联表
 */

public interface IPirChnlGroupViewSplDal extends IBaseSplDal {

	public int add(PirChnlGroupView pirChnlGroupView);

	public int addList(ArrayList<PirChnlGroupView> pirChnlGroupViewList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(PirChnlGroupView pirChnlGroupView);

	public PirChnlGroupView getModel(String id);

	public List<PirChnlGroupView> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





