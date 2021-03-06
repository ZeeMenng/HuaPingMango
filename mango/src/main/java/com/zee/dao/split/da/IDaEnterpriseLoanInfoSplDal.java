package com.zee.dao.split.da;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.da.DaEnterpriseLoanInfo;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018-6-13 19:04:23
 * @description 扩展自实体类IBaseUntDal<DaEnterpriseLoanInfo>，可手动更改。企业贷款记录表
 */

public interface IDaEnterpriseLoanInfoSplDal extends IBaseSplDal {

	public int add(DaEnterpriseLoanInfo daEnterpriseLoanInfo);

	public int addList(ArrayList<DaEnterpriseLoanInfo> daEnterpriseLoanInfoList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(DaEnterpriseLoanInfo daEnterpriseLoanInfo);

	public DaEnterpriseLoanInfo getModel(String id);

	public List<DaEnterpriseLoanInfo> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





