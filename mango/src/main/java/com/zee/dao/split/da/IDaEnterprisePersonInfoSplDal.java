package com.zee.dao.split.da;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.da.DaEnterprisePersonInfo;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018-6-13 19:04:23
 * @description 扩展自实体类IBaseUntDal<DaEnterprisePersonInfo>，可手动更改。企业人员信息表
 */

public interface IDaEnterprisePersonInfoSplDal extends IBaseSplDal {

	public int add(DaEnterprisePersonInfo daEnterprisePersonInfo);

	public int addList(ArrayList<DaEnterprisePersonInfo> daEnterprisePersonInfoList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(DaEnterprisePersonInfo daEnterprisePersonInfo);

	public DaEnterprisePersonInfo getModel(String id);

	public List<DaEnterprisePersonInfo> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





