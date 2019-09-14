package com.jusfoun.dao.split.pi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jusfoun.dao.split.base.IBaseSplDal;
import com.jusfoun.ent.extend.pi.PiUserFavorite;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018-6-15 13:54:46
 * @description 扩展自实体类IBaseUntDal<PiUserFavorite>，可手动更改。pi_user_favorite
 */

public interface IPiUserFavoriteSplDal extends IBaseSplDal {

	public int add(PiUserFavorite piUserFavorite);

	public int addList(ArrayList<PiUserFavorite> piUserFavoriteList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(PiUserFavorite piUserFavorite);

	public PiUserFavorite getModel(String id);

	public List<PiUserFavorite> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





