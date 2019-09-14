package com.jusfoun.dao.split.da;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jusfoun.dao.split.base.IBaseSplDal;
import com.jusfoun.ent.extend.da.DaEnterprisePropertyInfo;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018-6-13 19:04:23
 * @description 扩展自实体类IBaseUntDal<DaEnterprisePropertyInfo>，可手动更改。企业财产信息表
 */

public interface IDaEnterprisePropertyInfoSplDal extends IBaseSplDal {

	public int add(DaEnterprisePropertyInfo daEnterprisePropertyInfo);

	public int addList(ArrayList<DaEnterprisePropertyInfo> daEnterprisePropertyInfoList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(DaEnterprisePropertyInfo daEnterprisePropertyInfo);

	public DaEnterprisePropertyInfo getModel(String id);

	public List<DaEnterprisePropertyInfo> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





