package com.jusfoun.dao.split.pi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jusfoun.dao.split.base.IBaseSplDal;
import com.jusfoun.ent.extend.pi.PirChannelUser;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018/1/16 14:27:23
 * @description 扩展自实体类IBaseUntDal<PirChannelUser>，可手动更改。CMS栏目用户关联表
 */

public interface IPirChannelUserSplDal extends IBaseSplDal {

	public int add(PirChannelUser pirChannelUser);

	public int addList(ArrayList<PirChannelUser> pirChannelUserList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(PirChannelUser pirChannelUser);

	public PirChannelUser getModel(String id);

	public List<PirChannelUser> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





