package com.zee.bll.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zee.bll.base.Interface.IBaseBll;
import com.zee.dao.unity.base.IBaseUntDal;
import com.zee.ent.custom.ResultModel;
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

	protected void addOperationLog(ResultModel result) {
		Long incomeCount = 0L;
		if (result.getIncomeCount() != null)
			incomeCount = Long.valueOf(result.getIncomeCount());

		if (result.getIsSuccess())
			if (incomeCount != 0 && incomeCount > result.getTotalCount())
				result.setResultMessage(result.getResultMessage() + " 传入数据" + incomeCount + " 条，实际操作 " + result.getTotalCount() + " 。");

		operationLogDal.add(result);
	}

}
