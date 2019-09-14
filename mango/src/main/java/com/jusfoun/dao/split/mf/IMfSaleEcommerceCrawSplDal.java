package com.jusfoun.dao.split.mf;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jusfoun.dao.split.base.IBaseSplDal;
import com.jusfoun.ent.extend.mf.MfSaleEcommerceCraw;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018-6-13 19:07:53
 * @description 扩展自实体类IBaseUntDal<MfSaleEcommerceCraw>，可手动更改。电商数据表,采集
 */

public interface IMfSaleEcommerceCrawSplDal extends IBaseSplDal {

	public int add(MfSaleEcommerceCraw mfSaleEcommerceCraw);

	public int addList(ArrayList<MfSaleEcommerceCraw> mfSaleEcommerceCrawList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(MfSaleEcommerceCraw mfSaleEcommerceCraw);

	public MfSaleEcommerceCraw getModel(String id);

	public List<MfSaleEcommerceCraw> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





