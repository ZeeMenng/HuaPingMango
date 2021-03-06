package com.zee.dao.split.da;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.da.DaEnterpriseTaxInfo;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018-6-13 19:04:23
 * @description 扩展自实体类IBaseUntDal<DaEnterpriseTaxInfo>，可手动更改。企业税务信息表
 */

public interface IDaEnterpriseTaxInfoSplDal extends IBaseSplDal {

	public int add(DaEnterpriseTaxInfo daEnterpriseTaxInfo);

	public int addList(ArrayList<DaEnterpriseTaxInfo> daEnterpriseTaxInfoList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(DaEnterpriseTaxInfo daEnterpriseTaxInfo);

	public DaEnterpriseTaxInfo getModel(String id);

	public List<DaEnterpriseTaxInfo> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





