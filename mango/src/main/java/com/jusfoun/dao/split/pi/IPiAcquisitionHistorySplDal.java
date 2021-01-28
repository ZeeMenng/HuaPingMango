package com.zee.dao.split.pi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.pi.PiAcquisitionHistory;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018/1/16 14:27:23
 * @description 扩展自实体类IBaseUntDal<PiAcquisitionHistory>，可手动更改。CMS采集的文章历史记录表
 */

public interface IPiAcquisitionHistorySplDal extends IBaseSplDal {

	public int add(PiAcquisitionHistory piAcquisitionHistory);

	public int addList(ArrayList<PiAcquisitionHistory> piAcquisitionHistoryList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(PiAcquisitionHistory piAcquisitionHistory);

	public PiAcquisitionHistory getModel(String id);

	public List<PiAcquisitionHistory> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





