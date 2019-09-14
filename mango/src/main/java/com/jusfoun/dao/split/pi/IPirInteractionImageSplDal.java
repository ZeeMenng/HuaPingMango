package com.jusfoun.dao.split.pi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jusfoun.dao.split.base.IBaseSplDal;
import com.jusfoun.ent.extend.pi.PirInteractionImage;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018-7-5 11:35:44
 * @description 扩展自实体类IBaseUntDal<PirInteractionImage>，可手动更改。pir_interaction_image
 */

public interface IPirInteractionImageSplDal extends IBaseSplDal {

	public int add(PirInteractionImage pirInteractionImage);

	public int addList(ArrayList<PirInteractionImage> pirInteractionImageList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(PirInteractionImage pirInteractionImage);

	public PirInteractionImage getModel(String id);

	public List<PirInteractionImage> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





