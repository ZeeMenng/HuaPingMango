package com.zee.dao.split.pi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.pi.PirContentVideo;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018/5/7 15:00:36
 * @description 扩展自实体类IBaseUntDal<PirContentVideo>，可手动更改。CMS文章内容所包含视频信息表
 */

public interface IPirContentVideoSplDal extends IBaseSplDal {

	public int add(PirContentVideo pirContentVideo);

	public int addList(ArrayList<PirContentVideo> pirContentVideoList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(PirContentVideo pirContentVideo);

	public PirContentVideo getModel(String id);

	public List<PirContentVideo> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





