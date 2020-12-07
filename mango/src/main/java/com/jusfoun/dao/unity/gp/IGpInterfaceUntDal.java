package com.jusfoun.dao.unity.gp;

import java.util.List;
import com.jusfoun.dao.unity.base.IBaseUntDal;
import com.jusfoun.ent.extend.gp.GpInterface;
import com.jusfoun.ent.extend.gp.GpDomain;
import com.jusfoun.ent.extend.gp.GprRoleInterface;




/**
 * @author Zee
 * @createDate 2017/05/22 14:01:29
 * @updateDate 2020/10/13 20:02:23
 * @description 扩展自实体类IBaseUntDal<GpInterface>，可手动更改。系统接口。
 */

public interface IGpInterfaceUntDal extends IBaseUntDal<GpInterface> {

 
	public int deleteByDomainId(String  domainId);
    
    public int deleteByDomainIdList(List<String> domainIdList);

	public List<GpInterface> getListByDomainId(String  domainId);
   
   
}






