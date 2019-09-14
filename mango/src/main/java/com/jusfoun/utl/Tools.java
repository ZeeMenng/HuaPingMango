package com.jusfoun.utl;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.jusfoun.ent.extend.gp.GpModule;
import com.jusfoun.set.enumer.DictionaryModuleLevelEnum;
import com.jusfoun.set.exception.GlobalException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import nl.bitwalker.useragentutils.Browser;
import nl.bitwalker.useragentutils.OperatingSystem;
import nl.bitwalker.useragentutils.UserAgent;

/**
 * @author Zee
 * @createDate 2017年4月13日 下午3:19:02
 * @updateDate 2017年4月13日 下午3:19:02
 * @description 常用工具
 */

public class Tools {

	// 格式化为 单词之间加下划线的Camel命名法
	public static String getCamelUnderline(String str) {
		return str.replaceAll("([A-Z])", "_$1").toLowerCase();
	}

	// 根据生日取年龄
	public static int getAgeByBirth(Date birthday) {
		int age = 0;
		try {
			Calendar now = Calendar.getInstance();
			now.setTime(new Date());// 当前时间

			Calendar birth = Calendar.getInstance();
			birth.setTime(birthday);

			if (birth.after(now)) {// 如果传入的时间，在当前时间的后面，返回0岁
				age = 0;
			} else {
				age = now.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
				if (now.get(Calendar.DAY_OF_YEAR) > birth.get(Calendar.DAY_OF_YEAR)) {
					age += 1;
				}
			}
			return age;
		} catch (Exception e) {// 兼容性更强,异常后返回数据
			return 0;
		}
	}

	/**
	 * 获取上传文件的新名称
	 * 
	 * @return
	 */
	public static String getResouceName() {
		return new SimpleDateFormat("HHmmssSSS").format(new Date()) + String.format("%04d", new Random().nextInt(10000));
	}

	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	/**
	 * 加密签名内容
	 * 
	 * @param tokenInner
	 * @param nonce
	 * @param timestamp
	 * @return 加密后的签名内容
	 */
	public static String getSigNature(String tokenInner, String nonce, String timestamp) {
		Set<String> strSet = new TreeSet<String>();
		strSet.add(tokenInner);
		strSet.add(nonce);
		strSet.add(timestamp);
		String joinString = "";
		Iterator<String> iterator = strSet.iterator();
		while (iterator.hasNext()) {
			joinString += iterator.next();
		}
		return getMd5Hash(joinString);
	}

	/**
	 * 获取MD5加密
	 * 
	 * @param input
	 * @return
	 */
	public static String getMd5Hash(String input) {
		String result = "";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.reset();
			byte[] bys = input.getBytes(Charset.forName("UTF-8"));
			md.update(bys);
			byte[] bytes = md.digest();
			// StringBuffer buf = new StringBuffer("");
			for (int i = 0; i < bytes.length; i++) {
				byte temp = bytes[i];
				if (temp < 0) {
					int a = (256 + temp);
					temp = (byte) a;
				}
				result += Integer.toHexString(temp & 0xFF);
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * @Title: getIpAddr
	 * @Descripton 获取当前操作机器IP地址
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 获取客户端相关信息
	 * @param request
	 * @return
	 */
	public static Map<String, Object> getLoginInfo(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
		Browser browser = userAgent.getBrowser();
		map.put("browser", browser.toString());// 浏览器
		OperatingSystem os = userAgent.getOperatingSystem();
		map.put("os", os.toString());// 操作系统
		try {
			String ip = InetAddress.getLocalHost().getHostAddress(); // ip 地址
			map.put("ip", ip);// ip
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 去除html
	 * @param htmlStr
	 * @return
	 */
	public static String delHTMLTag(String htmlStr) {
		String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式
		String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式
		String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式

		Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
		Matcher m_script = p_script.matcher(htmlStr);
		htmlStr = m_script.replaceAll(""); // 过滤script标签

		Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
		Matcher m_style = p_style.matcher(htmlStr);
		htmlStr = m_style.replaceAll(""); // 过滤style标签

		Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
		Matcher m_html = p_html.matcher(htmlStr);
		htmlStr = m_html.replaceAll(""); // 过滤html标签

		return htmlStr.trim(); // 返回文本字符串
	}

	/**
	 * 将list转成sql格式的string
	 * @param list
	 * @return
	 */
	public static String list2SqlStr(List<String> list) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0, len = list.size(); i < len; i++) {
			sb.append("'" + list.get(i) + "'");
			if (i != len - 1) {
				sb.append(",");
			}
		}
		return sb.toString();
	}

	// 获取前端的浏览器信息
	public static String getBrowser(HttpServletRequest request) {
		String browserDetails = request.getHeader("User-Agent");
		String userAgent = browserDetails;
		String user = userAgent.toLowerCase();
		String browser = "";
		if (user.contains("edge")) {
			browser = (userAgent.substring(userAgent.indexOf("Edge")).split(" ")[0]).replace("/", "-");
		} else if (user.contains("msie")) {
			String substring = userAgent.substring(userAgent.indexOf("MSIE")).split(";")[0];
			browser = substring.split(" ")[0].replace("MSIE", "IE") + "-" + substring.split(" ")[1];
		} else if (user.contains("safari") && user.contains("version")) {
			browser = (userAgent.substring(userAgent.indexOf("Safari")).split(" ")[0]).split("/")[0] + "-" + (userAgent.substring(userAgent.indexOf("Version")).split(" ")[0]).split("/")[1];
		} else if (user.contains("opr") || user.contains("opera")) {
			if (user.contains("opera")) {
				browser = (userAgent.substring(userAgent.indexOf("Opera")).split(" ")[0]).split("/")[0] + "-" + (userAgent.substring(userAgent.indexOf("Version")).split(" ")[0]).split("/")[1];
			} else if (user.contains("opr")) {
				browser = ((userAgent.substring(userAgent.indexOf("OPR")).split(" ")[0]).replace("/", "-")).replace("OPR", "Opera");
			}

		} else if (user.contains("chrome")) {
			browser = (userAgent.substring(userAgent.indexOf("Chrome")).split(" ")[0]).replace("/", "-");
		} else if ((user.indexOf("mozilla/7.0") > -1) || (user.indexOf("netscape6") != -1) || (user.indexOf("mozilla/4.7") != -1) || (user.indexOf("mozilla/4.78") != -1) || (user.indexOf("mozilla/4.08") != -1) || (user.indexOf("mozilla/3") != -1)) {
			browser = "Netscape-?";

		} else if (user.contains("firefox")) {
			browser = (userAgent.substring(userAgent.indexOf("Firefox")).split(" ")[0]).replace("/", "-");
		} else if (user.contains("rv")) {
			String IEVersion = (userAgent.substring(userAgent.indexOf("rv")).split(" ")[0]).replace("rv:", "-");
			browser = "IE" + IEVersion.substring(0, IEVersion.length() - 1);
		} else {
			browser = "UnKnown, More-Info: " + userAgent;
		}
		return browser;
	}

	// 获取前端的操作系统信息
	public static String getOs(HttpServletRequest request) {
		String browserDetails = request.getHeader("User-Agent");
		String userAgent = browserDetails;

		String os = "";

		if (userAgent.toLowerCase().indexOf("windows") >= 0) {
			os = "Windows";
		} else if (userAgent.toLowerCase().indexOf("mac") >= 0) {
			os = "Mac";
		} else if (userAgent.toLowerCase().indexOf("x11") >= 0) {
			os = "Unix";
		} else if (userAgent.toLowerCase().indexOf("android") >= 0) {
			os = "Android";
		} else if (userAgent.toLowerCase().indexOf("iphone") >= 0) {
			os = "IPhone";
		} else {
			os = "UnKnown, More-Info: " + userAgent;
		}

		return os;
	}

	// 根据JSON字符串递归获取GpModule列表
	public static ArrayList<GpModule> getModuleListFromJsonString(String jsonString) {

		JSONArray jsonArray = JSONArray.fromObject(jsonString);
		ArrayList<GpModule> moduleList = convertModuleTreeToList(jsonArray);
		return moduleList;

	}

	private static ArrayList<GpModule> convertModuleTreeToList(JSONArray moduleJSONArray) {

		ArrayList<GpModule> moduleList = new ArrayList<GpModule>();

		for (Object object : moduleJSONArray) {
			GpModule module = new GpModule();
			JSONObject jsonObject = JSONObject.fromObject(object);

			byte level = ((Integer) (jsonObject.getInt("level") + 1)).byteValue();

			module.setId(Tools.getUUID());
			module.setLevelCode(level);
			module.setLevelText(DictionaryModuleLevelEnum.getText(level));

			if (jsonObject.containsKey("id"))
				module.setId(jsonObject.getString("id"));
			if (jsonObject.containsKey("domainId"))
				module.setDomainId(jsonObject.getString("domainId"));
			if (jsonObject.containsKey("fartherId"))
				module.setFartherId(jsonObject.getString("fartherId"));
			if (jsonObject.containsKey("name"))
				module.setName(jsonObject.getString("name"));
			if (jsonObject.containsKey("priority"))
				module.setPriority(jsonObject.getInt("priority"));
			if (jsonObject.containsKey("remark"))
				module.setRemark(jsonObject.getString("remark"));
			if (jsonObject.containsKey("children") && StringUtils.isNotBlank(jsonObject.getString("children")))
				moduleList.addAll(convertModuleTreeToList(JSONArray.fromObject("children")));

			moduleList.add(module);
		}

		return moduleList;
	}

}
