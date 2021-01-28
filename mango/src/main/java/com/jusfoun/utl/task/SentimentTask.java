package com.zee.utl.task;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.zee.bll.extend.unity.da.DaSentimentThemeUntBll;
import com.zee.ent.custom.ResultModel;
import com.zee.set.enumer.StatusEnum;
import com.zee.utl.Executors;

@Component
public class SentimentTask {
	
	@Autowired
    private Executors executors;
	
	@Autowired
	@Qualifier("daSentimentThemeUntBll")
	protected DaSentimentThemeUntBll daSentimentThemeUntBll;
	
	@Value("${mangoTaskSwitch.sentimentTask}")
	private Boolean sentimentTask;//定时任务开关

	private static final Logger log = LoggerFactory.getLogger(SentimentTask.class);
	
	public static void main(String[] args) {
		Calendar cal=Calendar.getInstance();      
		int h=cal.get(Calendar.HOUR_OF_DAY);  
		System.out.println(h);
	}

	/**
	 * 舆情文章定时任务
	 */
	//@Scheduled(cron = "0/30 * * * * ?")
	@Scheduled(cron = "0 0 0/1 * * ?")
	public void articleTask(){
		if(!sentimentTask)
			return;
		//一个主题拿一个线程
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("	SELECT                                                      ");
		selectBuffer.append("		A.id id,                                                ");
		selectBuffer.append("		A.theme_name themeName,                                 ");
		selectBuffer.append("		A.key_name keyName,                                     ");
		selectBuffer.append("		A.exclude_name excludeName,                             ");
		selectBuffer.append("		A.rule_id ruleId,                                       ");
		selectBuffer.append("		A.remark remark,                                        ");
		selectBuffer.append("		A.add_user_id addUserId,                                ");
		selectBuffer.append("		A.add_time addTime,                                     ");
		selectBuffer.append("		A.status_code statusCode,                               ");
		selectBuffer.append("		A.status_text statusText,                               ");
		selectBuffer.append("		B.frequency frequency,                                  ");
		selectBuffer.append("		B.rule_name ruleName,                                   ");
		selectBuffer.append("		B.media_type_code_set mediaTypeCodeSet,                 ");
		selectBuffer.append("		B.start_time startTime,                                 ");
		selectBuffer.append("		B.end_time endTime                                      ");
		selectBuffer.append("	FROM                                                        ");
		selectBuffer.append("		da_sentiment_theme A                                    ");
		selectBuffer.append("	INNER JOIN da_sentiment_rule B ON A.rule_id = B.id          ");
		selectBuffer.append("	WHERE A.status_code = "+StatusEnum.KAIQI.getCode()+"        ");
		
		map.put("Sql", selectBuffer.toString());
		ResultModel resultModel = daSentimentThemeUntBll.getListBySQL(map);
		List<Map<String, Object>> list = (List<Map<String, Object>>) resultModel.getData();
		for (Map<String, Object> map2 : list) {
			executors.dealArticle(map2);
		}
		
//		log.info("定时器articleTask1执行开始：" + DateUtils.getCurrentTimeStr());
//		executors.dealArticle("8grg2g8gd1g5d1g4e1r5g1rtj151g5r1", "华坪县 芒果");
//		
//		log.info("定时器articleTask2执行开始：" + DateUtils.getCurrentTimeStr());
//		executors.dealArticle("fsdf34t81h84158h148141g481g34gg3", "芒果 滞销");
	}
}
