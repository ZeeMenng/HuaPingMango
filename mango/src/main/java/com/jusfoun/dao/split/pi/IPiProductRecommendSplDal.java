package com.jusfoun.dao.split.pi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jusfoun.dao.split.base.IBaseSplDal;
import com.jusfoun.ent.extend.pi.PiProductRecommend;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018-8-19 10:15:47
 * @description 扩展自实体类IBaseUntDal<PiProductRecommend>，可手动更改。企业推介产品表
 */

public interface IPiProductRecommendSplDal extends IBaseSplDal {

	public int add(PiProductRecommend piProductRecommend);

	public int addList(ArrayList<PiProductRecommend> piProductRecommendList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(PiProductRecommend piProductRecommend);

	public PiProductRecommend getModel(String id);

	public List<PiProductRecommend> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





