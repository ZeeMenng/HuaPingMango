package com.jusfoun.dao.split.pi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jusfoun.dao.split.base.IBaseSplDal;
import com.jusfoun.ent.extend.pi.PirChnlGroupContri;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018/1/16 14:27:23
 * @description 扩展自实体类IBaseUntDal<PirChnlGroupContri>，可手动更改。CMS栏目投稿会员组关联表
 */

public interface IPirChnlGroupContriSplDal extends IBaseSplDal {

	public int add(PirChnlGroupContri pirChnlGroupContri);

	public int addList(ArrayList<PirChnlGroupContri> pirChnlGroupContriList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(PirChnlGroupContri pirChnlGroupContri);

	public PirChnlGroupContri getModel(String id);

	public List<PirChnlGroupContri> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





