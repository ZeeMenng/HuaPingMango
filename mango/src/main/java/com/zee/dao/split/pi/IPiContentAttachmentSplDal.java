package com.zee.dao.split.pi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.pi.PiContentAttachment;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018/1/16 14:27:23
 * @description 扩展自实体类IBaseUntDal<PiContentAttachment>，可手动更改。CMS内容附件表
 */

public interface IPiContentAttachmentSplDal extends IBaseSplDal {

	public int add(PiContentAttachment piContentAttachment);

	public int addList(ArrayList<PiContentAttachment> piContentAttachmentList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(PiContentAttachment piContentAttachment);

	public PiContentAttachment getModel(String id);

	public List<PiContentAttachment> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





