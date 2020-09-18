package com.jusfoun.dao.unity.gp;

import java.util.List;
import com.jusfoun.dao.unity.base.IBaseUntDal;
import com.jusfoun.ent.extend.gp.GpOperLog;
import com.jusfoun.ent.extend.gp.GpDomain;
import com.jusfoun.ent.extend.gp.GpOperLogLogin;




/**
 * @author Zee
 * @createDate 2017/05/22 14:01:29
 * @updateDate 2020/9/18 15:38:54
 * @description 扩展自实体类IBaseUntDal<GpOperLog>，可手动更改。操作日志。
 */

public interface IGpOperLogUntDal extends IBaseUntDal<GpOperLog> {

 
	public int deleteByDomainId(String  domainId);

	public List<GpOperLog> getListByDomainId(String  domainId);
   
   
}






