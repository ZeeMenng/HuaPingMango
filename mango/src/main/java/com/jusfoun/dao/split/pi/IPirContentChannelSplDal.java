package com.zee.dao.split.pi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.pi.PirContentChannel;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018/1/16 14:27:23
 * @description 扩展自实体类IBaseUntDal<PirContentChannel>，可手动更改。CMS内容栏目关联表
 */

public interface IPirContentChannelSplDal extends IBaseSplDal {

	public int add(PirContentChannel pirContentChannel);

	public int addList(ArrayList<PirContentChannel> pirContentChannelList);

	public int delete(String id);
	
	public int deleteByContentId(String contentId);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(PirContentChannel pirContentChannel);

	public PirContentChannel getModel(String id);

	public List<PirContentChannel> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





