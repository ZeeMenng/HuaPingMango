package com.zee.bll.extend.unity.da;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.zee.bll.generate.unity.da.DaMarketPriceGenUntBll;
import com.zee.dao.unity.da.IDaMarketPriceUntDal;
import com.zee.ent.extend.da.DaMarketPrice;
import com.zee.utl.DateUtils;


/**
 * @author Zee
 * @createDate 2017/05/22 14:43:59
 * @updateDate 2018-6-13 19:04:23
 * @description 市场价格数据表 业务逻辑处理类，扩展自DaMarketPriceGenUntBll ，可手动更改。
 */
@Service("daMarketPriceUntBll")
public class DaMarketPriceUntBll extends DaMarketPriceGenUntBll {
	@Autowired
	protected IDaMarketPriceUntDal daMarketPriceUntDal;
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 更新平均、高、低价
	 */
	public void updateAvgTopBottomPrice() {
		String format = DateUtils.date2String(DateUtils.getCurrentDate(),"yyyy-MM-dd");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Sql", "SELECT AVG(p.per_price_unit) price_avg, MAX(p.per_price_unit) price_max, MIN(p.per_price_unit) price_min, p.strains_code, p.`name` FROM da_market_price p, da_common_field f WHERE p.common_field_id = f.id AND p.price_type_code = 2 AND p.average_price IS NULL AND f.source_channel_type_code = 4 AND f.data_time_type_code = 5 AND f.audit_status_code = 1 AND f.start_time = str_to_date('"+format+"','%Y-%m-%d') GROUP BY p.`name`, p.strains_code");
		List<Map<String, Object>> listmm = (List<Map<String, Object>>)this.getListBySQL(map).getData();
		for (Iterator iterator = listmm.iterator(); iterator.hasNext();) {
			Map<String, Object> map2 = (Map<String, Object>) iterator.next();
			DaMarketPrice dmp = new DaMarketPrice();
			dmp.setAveragePrice(new BigDecimal(map2.get("price_avg").toString()));
			dmp.setAveragePriceUnit(new BigDecimal(map2.get("price_avg").toString()));
			dmp.setBottomPriceUnitCode((byte) 1);
			dmp.setBottomPriceUnitText("元/公斤");
			dmp.setBottomPrice(new BigDecimal(map2.get("price_min").toString()));
			dmp.setBottomPriceUnit(new BigDecimal(map2.get("price_min").toString()));
			dmp.setTopPriceUnitCode((byte) 1);
			dmp.setTopPriceUnitText("元/公斤");
			dmp.setTopPrice(new BigDecimal(map2.get("price_max").toString()));
			dmp.setTopPriceUnit(new BigDecimal(map2.get("price_max").toString()));
			dmp.setAveragePriceUnitCode((byte) 1);
			dmp.setAveragePriceUnitText("元/公斤");
			dmp.setStrainsCode(Byte.valueOf(map2.get("strains_code").toString()));
			dmp.setPriceTypeCode((byte) 2);
			dmp.setName(map2.get("name").toString());
			this.updateAvgTopBottomPrice(dmp);
		}
	}

	private void updateAvgTopBottomPrice(DaMarketPrice dmp) {
		StringBuffer sb = new StringBuffer();
		sb.append("		UPDATE da_market_price					");
		sb.append("			SET 								");
		sb.append("			`average_price` = ?,				");
		sb.append("			`average_price_unit_code` = ?,		");
		sb.append("			`average_price_unit_text` = ?,		");
		sb.append("			`average_price_unit` = ?,			");
		sb.append("             bottom_price = ?,				");
		sb.append("             bottom_price_unit_code = ?,		");
		sb.append("             bottom_price_unit_text = ?,		");
		sb.append("             bottom_price_unit = ?,			");
		sb.append("             top_price = ?,					");
		sb.append("             top_price_unit_code = ?,		");
		sb.append("             top_price_unit_text = ?,		");
		sb.append("             top_price_unit = ?				");
		sb.append("		WHERE									");
		sb.append("			average_price IS NULL				");
		sb.append("		AND strains_code = ? 					");
		sb.append("		AND `name` = ?							");
		sb.append("		AND `price_type_code` = ? 				");
		jdbcTemplate.update(sb.toString(), new Object[] {dmp.getAveragePrice(), dmp.getAveragePriceUnitCode(), 
				dmp.getAveragePriceUnitText(), dmp.getAveragePriceUnit(), dmp.getBottomPrice(), dmp.getBottomPriceUnitCode(), 
				dmp.getBottomPriceUnitText(), dmp.getBottomPriceUnit(), dmp.getTopPrice(), dmp.getTopPriceUnitCode(), 
				dmp.getTopPriceUnitText(), dmp.getTopPriceUnit(), dmp.getStrainsCode(), dmp.getName(), dmp.getPriceTypeCode()});
		
	}
}