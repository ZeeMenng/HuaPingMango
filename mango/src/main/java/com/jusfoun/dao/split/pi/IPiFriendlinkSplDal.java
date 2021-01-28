package com.zee.dao.split.pi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.pi.PiFriendlink;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018/7/12 14:43:26
 * @description 扩展自实体类IBaseUntDal<PiFriendlink>，可手动更改。CMS友情链接
 */

public interface IPiFriendlinkSplDal extends IBaseSplDal {

	public int add(PiFriendlink piFriendlink);

	public int addList(ArrayList<PiFriendlink> piFriendlinkList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(PiFriendlink piFriendlink);

	public PiFriendlink getModel(String id);

	public List<PiFriendlink> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





