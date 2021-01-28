package com.zee.bll.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zee.bll.base.Interface.IBaseBll;
import com.zee.dao.unity.base.IBaseUntDal;
import com.zee.ent.extend.gp.GpOperLog;

@Service
@Transactional
public class BaseBll implements IBaseBll {
	@Value("${isLog.write}")
	protected boolean isLogWrite;

	@Value("${isLog.read}")
	protected boolean isLogRead;

	public static final Logger logger = LoggerFactory.getLogger(BaseBll.class);

	@Autowired
	protected IBaseUntDal<GpOperLog> operationLogDal;
}
