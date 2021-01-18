package com.jusfoun.dao.unity.gp;

import java.util.List;
import com.jusfoun.dao.unity.base.IBaseUntDal;
import com.jusfoun.ent.extend.gp.GprDomainMessage;
import com.jusfoun.ent.extend.gp.GpDomain;
import com.jusfoun.ent.extend.gp.GpMessage;




/**
 * @author Zee
 * @createDate 2017/05/22 14:01:29
 * @updateDate 2021/1/4 14:18:29
 * @description 扩展自实体类IBaseUntDal<GprDomainMessage>，可手动更改。应用领域的站内信。
 */

public interface IGprDomainMessageUntDal extends IBaseUntDal<GprDomainMessage> {

 
	public int deleteByDomainId(String  domainId);
    
    public int deleteByDomainIdList(List<String> domainIdList);

	public List<GprDomainMessage> getListByDomainId(String  domainId);
 
	public int deleteByMessageId(String  messageId);
    
    public int deleteByMessageIdList(List<String> messageIdList);

	public List<GprDomainMessage> getListByMessageId(String  messageId);
        
 public int deleteByCompositeIdList(List<GprDomainMessage> gprDomainMessageList);   
   

   
}






