package com.jusfoun.dao.split.pi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jusfoun.dao.split.base.IBaseSplDal;
import com.jusfoun.ent.extend.pi.PiAcquisition;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018/1/16 14:27:23
 * @description 扩展自实体类IBaseUntDal<PiAcquisition>，可手动更改。CMS采集任务表
 */

public interface IPiAcquisitionSplDal extends IBaseSplDal {

	public int add(PiAcquisition piAcquisition);

	public int addList(ArrayList<PiAcquisition> piAcquisitionList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(PiAcquisition piAcquisition);

	public PiAcquisition getModel(String id);

	public List<PiAcquisition> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





