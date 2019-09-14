package com.jusfoun.dao.split.da;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jusfoun.dao.split.base.IBaseSplDal;
import com.jusfoun.ent.extend.da.DaUserContribution;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018-6-19 16:50:20
 * @description 扩展自实体类IBaseUntDal<DaUserContribution>，可手动更改。用户贡献
 */

public interface IDaUserContributionSplDal extends IBaseSplDal {

	public int add(DaUserContribution daUserContribution);

	public int addList(ArrayList<DaUserContribution> daUserContributionList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(DaUserContribution daUserContribution);

	public DaUserContribution getModel(String id);

	public List<DaUserContribution> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





