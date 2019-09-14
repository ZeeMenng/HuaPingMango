package com.jusfoun.bll.extend.unity.da;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.jusfoun.bll.extend.unity.gp.GpDictionaryUntBll;
import com.jusfoun.bll.extend.unity.gp.GpRegionUntBll;
import com.jusfoun.bll.generate.unity.da.DaCrawlerGenUntBll;
import com.jusfoun.bll.generate.unity.mf.MfSaleEcommerceCrawGenUntBll;
import com.jusfoun.ent.extend.da.DaCommonField;
import com.jusfoun.ent.extend.da.DaCrawler;
import com.jusfoun.ent.extend.mf.MfSaleEcommerceCraw;
import com.jusfoun.ent.parameter.da.DaCrawlerParameter.GetList;
import com.jusfoun.utl.DateUtils;
import com.jusfoun.utl.SymbolicConstant;
import us.codecraft.webmagic.selector.Html;

/**
 * @author Zee
 * @createDate 2017/05/22 14:43:59
 * @updateDate 2018-6-13 19:04:22
 * @description 爬虫 业务逻辑处理类，扩展自DaCrawlerGenUntBll ，可手动更改。
 */
@Service("daCrawlerUntBll")
public class DaCrawlerUntBll extends DaCrawlerGenUntBll {
	private static final Logger log = LoggerFactory.getLogger(DaCrawlerUntBll.class);

	@Autowired
	@Qualifier("daCommonFieldUntBll")
	protected DaCommonFieldUntBll daCommonFieldUntBll;
	@Autowired
	@Qualifier("gpRegionUntBll")
	protected GpRegionUntBll gpRegionUntBll;
	@Autowired
	@Qualifier("gpDictionaryUntBll")
	protected GpDictionaryUntBll gpDictionaryUntBll;
	@Autowired
	protected MfSaleEcommerceCrawGenUntBll mfSaleEcommerceCrawGenUntBll;
	
	/**
	 * 数据清洗，将da_crawler入库da_market_price与mf_sale_ecommerce_craw
	 */
	public void dataCleaning() {
		Map<String, Object> map = new HashMap<String, Object>();
		Date currentTime = DateUtils.getCurrentDate();
		map.put("Sql", "SELECT `code`, `text` FROM gp_dictionary WHERE type_id = '"+SymbolicConstant.DI_CROP_STRAINS+"'");
		List<Map<String, Object>> listmd = (List<Map<String, Object>>)gpDictionaryUntBll.getListBySQL(map).getData();
		GetList getListParam = new GetList();
		getListParam.getEntityRelated().setBeginCreatedTime(DateUtils.string2Date(DateUtils.getCurrentDateStr(), SymbolicConstant.DATE_FORMAT));
		getListParam.getPage().setPageIndex(SymbolicConstant.SQLQUERY_PAGEINDEX_DEFAULTVALUE);
		getListParam.getPage().setPageSize(SymbolicConstant.SQLQUERY_KEYWORDS_PAGESIZE_MAX);
		List<DaCrawler> list = (List<DaCrawler>) this.getList(getListParam).getData();
		for (DaCrawler daCrawler : list) {
			log.info("--------------------------id:"+daCrawler.getId());
			DaCommonField dcf = new DaCommonField();
			MfSaleEcommerceCraw msec = new MfSaleEcommerceCraw();
			if(daCrawler.getSource() == 3)
				three(map, currentTime, listmd, daCrawler, dcf, msec);
			else
				oneAndTwo(map, currentTime, listmd, daCrawler, dcf, msec);
			if(null == msec.getSalePrice() || null == msec.getProductAreaCode() || null == msec.getStrainsCode())
				continue;
			this.addValue(currentTime, daCrawler, dcf, msec);
		}
	}
	
	private void three(Map<String, Object> map, Date currentTime, List<Map<String, Object>> listmd,
			DaCrawler daCrawler, DaCommonField dcf, MfSaleEcommerceCraw msec) {
		String parameter = daCrawler.getParameter();
		parameter = parameter.replaceAll("&nbsp;", "");
		Html html = new Html(parameter);
		Document document = html.getDocument();
		Elements elementsByTag = document.getElementsByTag("li");
		for (Element li : elementsByTag) {
			String text = li.text();
			String attr = li.attr("title");
			if(text.contains("商品毛重")){
				this.setPrice(daCrawler, msec, attr);
			}else if(text.contains("原产地")){
				if(text.contains("中国")){
					if(text.contains("台湾"))
						attr = "台湾";
					else
						attr = attr.substring(attr.indexOf("（")+1, attr.indexOf("）"));
					this.setCity(map, currentTime, dcf, msec, attr);
				}else{
					this.setState(map, currentTime, dcf, msec, attr);
				}
			}else if(text.contains("分类")){
				this.setStrains(listmd, msec, attr);
			}
		}
	}

	private void oneAndTwo(Map<String, Object> map, Date currentTime, List<Map<String, Object>> listmd,
			DaCrawler daCrawler, DaCommonField dcf, MfSaleEcommerceCraw msec) {
		String parameter = daCrawler.getParameter();
		if(parameter.contains("中国大陆")){
			parameter = parameter.replaceAll("&nbsp;", "");
			Html html = new Html(parameter);
			Document document = html.getDocument();
			Elements elementsByTag = document.getElementsByTag("li");
			for (Element li : elementsByTag) {
				String text = li.text();
				String attr = li.attr("title");
				if(text.contains("净含量")){
					this.setPrice(daCrawler, msec, attr);
				}else if(text.contains("城市")){
					this.setCity(map, currentTime, dcf, msec, attr);
				}else if(text.contains("水果种类")){
					this.setStrains(listmd, msec, attr);
				}
//				else if(text.contains("单果重量")){
//					this.setWeightSpecification(msec, text);
//				}
			}
		}
	}

	/**
	 * 种类
	 * @param listmd
	 * @param dmp
	 * @param msec
	 * @param attr
	 */
	private void setStrains(List<Map<String, Object>> listmd, MfSaleEcommerceCraw msec,
			String attr) {
		boolean flag = false;
		if(attr.equals("台芒"))
			attr = "台农芒";
		else if(attr.equals("香芒") || attr.equals("桂七芒"))
			attr = "桂七（桂热82号）";
		else if(attr.equals("象牙芒"))
			attr = "红象牙";
		else if(attr.contains("其它"))
			attr = "其他";
		for (Iterator iterator = listmd.iterator(); iterator.hasNext();) {
			Map<String, Object> map2 = (Map<String, Object>) iterator.next();
			if(attr.contains(map2.get("text").toString())){
				msec.setStrainsCode(Byte.valueOf(map2.get("code").toString()));
				msec.setStrainsText(map2.get("text").toString());
				flag = true;
				break;
			}
		}
		if(!flag)
			log.info("--------------------------Strain:"+attr);
	}

	/**
	 * 单颗重量规格
	 * @param msec
	 * @param text
	 */
	private void setWeightSpecification(MfSaleEcommerceCraw msec, String text) {
		String[] split = text.split("-");
		String top = split[1].replaceAll("\\D", "");
		if(new BigDecimal(top).compareTo(new BigDecimal(3000)) == 1){
			msec.setWeightSpecificationCode((byte) 4);
			msec.setWeightSpecificationText("3kg以上");
		}else if(new BigDecimal(top).compareTo(new BigDecimal(2000)) >= 0){
			msec.setWeightSpecificationCode((byte) 3);
			msec.setWeightSpecificationText("2kg-3kg");
		}else if(new BigDecimal(top).compareTo(new BigDecimal(1000)) >= 0){
			msec.setWeightSpecificationCode((byte) 2);
			msec.setWeightSpecificationText("1kg-2kg");
		}else{
			msec.setWeightSpecificationCode((byte) 1);
			msec.setWeightSpecificationText("1kg以下");
		}
	}

	/**
	 * 持久化
	 * @param currentTime
	 * @param daCrawler
	 * @param dcf
	 * @param dmp
	 * @param msec
	 */
	private void addValue(Date currentTime, DaCrawler daCrawler, DaCommonField dcf, MfSaleEcommerceCraw msec) {
		if(msec.getSalePrice().compareTo(new BigDecimal(50)) <= 0){
			dcf.setDataTimeTypeCode((byte) 5);
			dcf.setDataTimeTypeText("日");
			dcf.setSourceChannelTypeCode((byte) 4);
			dcf.setSourceChannelTypeText("网络采集");
			dcf.setAuditStatusCode((byte) 1);
			dcf.setAuditStatusText("审核通过");
			dcf.setStartTime(currentTime);
			daCommonFieldUntBll.add(dcf,false);
			
			msec.setCommonFieldId(dcf.getId());
			msec.setCropTypeCode((byte) 1);
			msec.setCropTypeText("芒果");
			msec.setEcommerceCode(daCrawler.getSource());
			mfSaleEcommerceCrawGenUntBll.add(msec,false);
		}
	}

	/**
	 * 产地(国内)
	 * @param map
	 * @param currentTime
	 * @param dcf
	 * @param msec
	 * @param attr
	 */
	private void setCity(Map<String, Object> map, Date currentTime, DaCommonField dcf, MfSaleEcommerceCraw msec,
			String attr) {
			map.put("Sql", "select `code`, region_level regionLevel, `name` from gp_region where `name` like '"+attr+"%'");
			List<Map<String, Object>>  listm = (List<Map<String, Object>>)gpRegionUntBll.getListBySQL(map).getData();
			if(!org.thymeleaf.util.ListUtils.isEmpty(listm)){
				String code = (String) listm.get(0).get("code");
				String name = (String) listm.get(0).get("name");
				dcf.setRegionId(code);
				dcf.setRegionName(name);
				Integer regionLevel = (Integer) listm.get(0).get("regionLevel");
				dcf.setAreaLatitudeTypeCode(regionLevel.byteValue());
				switch (regionLevel) {
					case 1:
						dcf.setAreaLatitudeTypeText("国家");
						break;
					case 2:
						dcf.setAreaLatitudeTypeText("省");
						break;
					case 3:
						dcf.setAreaLatitudeTypeText("市");
						break;
					case 4:
						dcf.setAreaLatitudeTypeText("县/区");
						break;
					default:
						dcf.setAreaLatitudeTypeText("乡/镇");
						break;
				}
				
				msec.setProductAreaCode(code);
				msec.setProductAreaText(name);
			}else{
				log.info("--------------------------City:"+attr);
			}
	}
	/**
	 * 产地(国外)
	 * @param map
	 * @param currentTime
	 * @param dcf
	 * @param msec
	 * @param attr
	 */
	private void setState(Map<String, Object> map, Date currentTime, DaCommonField dcf, MfSaleEcommerceCraw msec,
			String attr) {
			map.put("Sql", "select `iso_code` isoCode from gp_region_country where `chinese_name` = '"+attr+"'");
			List<Map<String, Object>>  listm = (List<Map<String, Object>>)gpRegionUntBll.getListBySQL(map).getData();
			if(!org.thymeleaf.util.ListUtils.isEmpty(listm)){
				String code = (String) listm.get(0).get("isoCode");
				dcf.setRegionId(code);
				dcf.setRegionName(attr);
				dcf.setAreaLatitudeTypeCode((byte) 1);
				dcf.setAreaLatitudeTypeText("国家");
				msec.setProductAreaCode(code);
				msec.setProductAreaText(attr);
			}else{
				log.info("--------------------------State:"+attr);
			}
	}

	/**
	 * 单价、价格区间、销售量、销售价
	 * @param daCrawler
	 * @param dmp
	 * @param msec
	 * @param attr
	 */
	private void setPrice(DaCrawler daCrawler, MfSaleEcommerceCraw msec, String attr) {
		String replaceAll = attr.replaceAll("[^0-9.]", "");
		BigDecimal perPrice = new BigDecimal(0);
		if(attr.contains("kg")){
			perPrice = daCrawler.getPromoPrice().divide(new BigDecimal(replaceAll), 2, BigDecimal.ROUND_HALF_UP);
			replaceAll = new BigDecimal(replaceAll).multiply(new BigDecimal(1000)).toString();
		}else if(attr.contains("g")){
			perPrice = daCrawler.getPromoPrice().multiply(new BigDecimal(1000)).divide(new BigDecimal(replaceAll), 2, BigDecimal.ROUND_HALF_UP);
			String replaceAll2 = daCrawler.getSellCounter()==null?null:daCrawler.getSellCounter().replaceAll("\\D", "");
			if(StringUtils.isNotBlank(replaceAll2)){
				BigDecimal multiply = new BigDecimal(replaceAll).multiply(new BigDecimal(replaceAll2)).divide(new BigDecimal(1000), 2, BigDecimal.ROUND_HALF_UP);
				msec.setSaleAmount(multiply);
				msec.setSaleAmountUnit(multiply);
				msec.setSaleAmountCode((byte) 1);
				msec.setSaleAmountText("千克");
				BigDecimal multiply2 = new BigDecimal(daCrawler.getSellCounter()).multiply(daCrawler.getPromoPrice());
				msec.setSaleVolume(multiply2);
				msec.setSaleVolumeUnit(multiply2);
				msec.setSaleVolumeUnitCode((byte) 1);
				msec.setSaleVolumeUnitText("元");
			}
		}
		if(new BigDecimal(replaceAll).compareTo(new BigDecimal(3000)) >= 0){
			msec.setWeightSpecificationCode((byte) 4);
			msec.setWeightSpecificationText("3kg以上");
		}else if(new BigDecimal(replaceAll).compareTo(new BigDecimal(2000)) >= 0){
			msec.setWeightSpecificationCode((byte) 3);
			msec.setWeightSpecificationText("2kg-3kg");
		}else if(new BigDecimal(replaceAll).compareTo(new BigDecimal(1000)) >= 0){
			msec.setWeightSpecificationCode((byte) 2);
			msec.setWeightSpecificationText("1kg-2kg");
		}else{
			msec.setWeightSpecificationCode((byte) 1);
			msec.setWeightSpecificationText("1kg以下");
		}
		msec.setSalePrice(perPrice);
		msec.setSalePriceUnit(perPrice);
		msec.setSalePriceCode((byte) 1);
		msec.setSalePriceText("元/公斤");
		if(perPrice.compareTo(new BigDecimal(20)) == 1){
			msec.setPriceRangeCode((byte) 5);
			msec.setPriceRangeText("20元以上/公斤");
		}else if(perPrice.compareTo(new BigDecimal(15)) >= 0){
			msec.setPriceRangeCode((byte) 4);
			msec.setPriceRangeText("15-20元/公斤");
		}else if(perPrice.compareTo(new BigDecimal(10)) >= 0){
			msec.setPriceRangeCode((byte) 3);
			msec.setPriceRangeText("10-15元/公斤");
		}else if(perPrice.compareTo(new BigDecimal(5)) >= 0){
			msec.setPriceRangeCode((byte) 2);
			msec.setPriceRangeText("5-10元/公斤");
		}else{
			msec.setPriceRangeCode((byte) 1);
			msec.setPriceRangeText("5元以下/公斤");
		}
	}
}