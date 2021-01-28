package com.zee.dao.split.da;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.da.DaProcess;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018-6-13 19:04:23
 * @description 扩展自实体类IBaseUntDal<DaProcess>，可手动更改。加工品数据表
 */

public interface IDaProcessSplDal extends IBaseSplDal {

	public int add(DaProcess daProcess);

	public int addList(ArrayList<DaProcess> daProcessList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(DaProcess daProcess);

	public DaProcess getModel(String id);

	public List<DaProcess> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





