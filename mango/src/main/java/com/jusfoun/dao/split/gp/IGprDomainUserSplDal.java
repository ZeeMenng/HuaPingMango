package com.jusfoun.dao.split.gp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jusfoun.dao.split.base.IBaseSplDal;
import com.jusfoun.ent.extend.gp.GprDomainUser;

/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018/1/16 14:27:23
 * @description 扩展自实体类IBaseUntDal<GprDomainUser>，可手动更改。应用领域拥有的用户。
 */

public interface IGprDomainUserSplDal extends IBaseSplDal {

	public int add(GprDomainUser gprDomainUser);

	public int addList(ArrayList<GprDomainUser> gprDomainUserList);

	public int delete(String id);

	public int deleteByDomainId(String domainId);

	public int deleteByUserId(String userId);
	
	public int deleteByIdList(ArrayList<String> idList);
	
	public int deleteByDomainIdList(ArrayList<String> domainIdList);

	public int deleteByUserIdList(ArrayList<String> userIdList);
	
	public int update(GprDomainUser gprDomainUser);

	public GprDomainUser getModel(String id);

	public int isPermitted(String userId,String clientId);

	public List<GprDomainUser> getList(Map<String, Object> map);

	public List<GprDomainUser> getListByUserId(String userId);

	public List<Map<String, Object>> getListBySQL(String sql);

}
