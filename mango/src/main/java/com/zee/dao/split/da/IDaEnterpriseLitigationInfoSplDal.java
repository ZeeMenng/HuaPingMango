package com.zee.dao.split.da;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.da.DaEnterpriseLitigationInfo;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018-6-13 19:04:23
 * @description 扩展自实体类IBaseUntDal<DaEnterpriseLitigationInfo>，可手动更改。企业诉讼表
 */

public interface IDaEnterpriseLitigationInfoSplDal extends IBaseSplDal {

	public int add(DaEnterpriseLitigationInfo daEnterpriseLitigationInfo);

	public int addList(ArrayList<DaEnterpriseLitigationInfo> daEnterpriseLitigationInfoList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(DaEnterpriseLitigationInfo daEnterpriseLitigationInfo);

	public DaEnterpriseLitigationInfo getModel(String id);

	public List<DaEnterpriseLitigationInfo> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





