package com.jusfoun.dao.split.da;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jusfoun.dao.split.base.IBaseSplDal;
import com.jusfoun.ent.extend.da.DaEnterpriseCertificateInfo;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018-9-14 13:59:04
 * @description 扩展自实体类IBaseUntDal<DaEnterpriseCertificateInfo>，可手动更改。企业证书
 */

public interface IDaEnterpriseCertificateInfoSplDal extends IBaseSplDal {

	public int add(DaEnterpriseCertificateInfo daEnterpriseCertificateInfo);

	public int addList(ArrayList<DaEnterpriseCertificateInfo> daEnterpriseCertificateInfoList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(DaEnterpriseCertificateInfo daEnterpriseCertificateInfo);

	public DaEnterpriseCertificateInfo getModel(String id);

	public List<DaEnterpriseCertificateInfo> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





