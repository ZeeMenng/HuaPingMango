package com.zee.dao.split.da;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.da.DaIotDictionary;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018-6-13 19:04:23
 * @description 扩展自实体类IBaseUntDal<DaIotDictionary>，可手动更改。物联网传感器设备字典
 */

public interface IDaIotDictionarySplDal extends IBaseSplDal {

	public int add(DaIotDictionary daIotDictionary);

	public int addList(ArrayList<DaIotDictionary> daIotDictionaryList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(DaIotDictionary daIotDictionary);

	public DaIotDictionary getModel(String id);

	public List<DaIotDictionary> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





