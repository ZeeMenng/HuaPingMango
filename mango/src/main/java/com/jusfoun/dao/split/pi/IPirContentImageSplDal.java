package com.jusfoun.dao.split.pi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jusfoun.dao.split.base.IBaseSplDal;
import com.jusfoun.ent.extend.pi.PirContentImage;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018/5/7 15:00:36
 * @description 扩展自实体类IBaseUntDal<PirContentImage>，可手动更改。CMS文章内容所包含图片信息表
 */

public interface IPirContentImageSplDal extends IBaseSplDal {

	public int add(PirContentImage pirContentImage);

	public int addList(ArrayList<PirContentImage> pirContentImageList);

	public int delete(String id);
	
	public int deleteTitleImageByContentId(String contentId);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(PirContentImage pirContentImage);

	public PirContentImage getModel(String id);

	public List<PirContentImage> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





