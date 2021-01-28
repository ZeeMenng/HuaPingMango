package com.zee.dao.split.da;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.da.DaImportExport;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018-6-13 19:04:23
 * @description 扩展自实体类IBaseUntDal<DaImportExport>，可手动更改。进出口数据表，用于存放直报数据的进出口数据
 */

public interface IDaImportExportSplDal extends IBaseSplDal {

	public int add(DaImportExport daImportExport);

	public int addList(ArrayList<DaImportExport> daImportExportList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(DaImportExport daImportExport);

	public DaImportExport getModel(String id);

	public List<DaImportExport> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





