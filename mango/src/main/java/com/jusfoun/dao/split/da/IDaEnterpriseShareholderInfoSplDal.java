package com.jusfoun.dao.split.da;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jusfoun.dao.split.base.IBaseSplDal;
import com.jusfoun.ent.extend.da.DaEnterpriseShareholderInfo;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018-6-13 19:04:23
 * @description 扩展自实体类IBaseUntDal<DaEnterpriseShareholderInfo>，可手动更改。企业股东信息表
 */

public interface IDaEnterpriseShareholderInfoSplDal extends IBaseSplDal {

	public int add(DaEnterpriseShareholderInfo daEnterpriseShareholderInfo);

	public int addList(ArrayList<DaEnterpriseShareholderInfo> daEnterpriseShareholderInfoList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(DaEnterpriseShareholderInfo daEnterpriseShareholderInfo);

	public DaEnterpriseShareholderInfo getModel(String id);

	public List<DaEnterpriseShareholderInfo> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





