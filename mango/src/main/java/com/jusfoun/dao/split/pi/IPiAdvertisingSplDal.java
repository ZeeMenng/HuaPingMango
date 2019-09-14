package com.jusfoun.dao.split.pi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jusfoun.dao.split.base.IBaseSplDal;
import com.jusfoun.ent.extend.pi.PiAdvertising;

/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018/7/12 14:46:55
 * @description 扩展自实体类IBaseUntDal<PiAdvertising>，可手动更改。CMS广告表
 */

public interface IPiAdvertisingSplDal extends IBaseSplDal {

	public int add(PiAdvertising piAdvertising);

	public int addList(ArrayList<PiAdvertising> piAdvertisingList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(PiAdvertising piAdvertising);

	public PiAdvertising getModel(String id);

	public PiAdvertising getModelBySpace(String domainId, Byte spaceCode);

	public List<PiAdvertising> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}
