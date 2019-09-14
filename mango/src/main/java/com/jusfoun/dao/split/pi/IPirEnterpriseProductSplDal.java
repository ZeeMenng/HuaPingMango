package com.jusfoun.dao.split.pi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jusfoun.dao.split.base.IBaseSplDal;
import com.jusfoun.ent.extend.pi.PirEnterpriseProduct;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018/7/11 17:38:33
 * @description 扩展自实体类IBaseUntDal<PirEnterpriseProduct>，可手动更改。企业和产品中间表
 */

public interface IPirEnterpriseProductSplDal extends IBaseSplDal {

	public int add(PirEnterpriseProduct pirEnterpriseProduct);

	public int addList(ArrayList<PirEnterpriseProduct> pirEnterpriseProductList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(PirEnterpriseProduct pirEnterpriseProduct);

	public PirEnterpriseProduct getModel(String id);

	public List<PirEnterpriseProduct> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





