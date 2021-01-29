package com.zee.dao.split.mf;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.mf.MfProductSale;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018-6-13 19:07:53
 * @description 扩展自实体类IBaseUntDal<MfProductSale>，可手动更改。产销预测表
 */

public interface IMfProductSaleSplDal extends IBaseSplDal {

	public int add(MfProductSale mfProductSale);

	public int addList(ArrayList<MfProductSale> mfProductSaleList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(MfProductSale mfProductSale);

	public MfProductSale getModel(String id);

	public List<MfProductSale> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}




