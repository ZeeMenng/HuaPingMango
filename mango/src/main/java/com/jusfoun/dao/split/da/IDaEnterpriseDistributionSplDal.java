package com.zee.dao.split.da;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.da.DaEnterpriseDistribution;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018-6-25 10:47:17
 * @description 扩展自实体类IBaseUntDal<DaEnterpriseDistribution>，可手动更改。芒果相关企业分布
 */

public interface IDaEnterpriseDistributionSplDal extends IBaseSplDal {

	public int add(DaEnterpriseDistribution daEnterpriseDistribution);

	public int addList(ArrayList<DaEnterpriseDistribution> daEnterpriseDistributionList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(DaEnterpriseDistribution daEnterpriseDistribution);

	public DaEnterpriseDistribution getModel(String id);

	public List<DaEnterpriseDistribution> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





