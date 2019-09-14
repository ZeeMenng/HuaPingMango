package com.jusfoun.dao.split.da;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jusfoun.dao.split.base.IBaseSplDal;
import com.jusfoun.ent.extend.da.DaEnterpriseCognateInfo;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018-6-13 19:04:23
 * @description 扩展自实体类IBaseUntDal<DaEnterpriseCognateInfo>，可手动更改。企业关联企业信息表，企业与企业之间关系。
 */

public interface IDaEnterpriseCognateInfoSplDal extends IBaseSplDal {

	public int add(DaEnterpriseCognateInfo daEnterpriseCognateInfo);

	public int addList(ArrayList<DaEnterpriseCognateInfo> daEnterpriseCognateInfoList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(DaEnterpriseCognateInfo daEnterpriseCognateInfo);

	public DaEnterpriseCognateInfo getModel(String id);

	public List<DaEnterpriseCognateInfo> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





