package com.zee.utl;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zee.bll.extend.unity.gp.GpDictionaryUntBll;
import com.zee.ent.custom.ResultModel;
import com.zee.ent.extend.gp.GpDictionary;
import com.zee.ent.parameter.base.BaseParameter;
import com.zee.ent.parameter.base.BaseParameter.BaseParamGetList.Order;
import com.zee.ent.parameter.gp.GpDictionaryParameter;
import com.zee.set.annotation.DictionaryConvertAnnotation;
import com.zee.set.symbolic.CustomSymbolic;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;

/**
 * @author Zee
 * @createDate 2021年2月24日 上午11:03:01
 * @updateDate 2021年2月24日 上午11:03:01
 * @description 手动字典解析工具类
 */
@Component
public class DictionaryConvertUtil {

	protected static GpDictionaryUntBll gpDictionaryUntBll;

	/**
	 * @param gpDictionaryUntBll
	 * 
	 * @Autowired 无法注入static属性，迂回一下
	 */
	@Autowired
	public void setGpDictionaryUntBll(GpDictionaryUntBll gpDictionaryUntBll) {
		DictionaryConvertUtil.gpDictionaryUntBll = gpDictionaryUntBll;
	}

	public static <T> void convertToCode(T data) {
		convertToCode(data, null);
	}

	/**
	 * 男 to 1
	 *
	 * @param data     需要转换的对象
	 * @param consumer 转换后做一些事情
	 */
	public static <T> void convertToCode(T data, Consumer<T> consumer) {
		if (Objects.isNull(data)) {
			return;
		}
		convert(data, true);
		if (consumer != null) {
			consumer.accept(data);
		}
	}

	public static <T> void convertToCodeList(List<T> data) {
		convertToCodeList(data, null);
	}

	/**
	 * 男 to 1
	 *
	 * @param data     需要转换的对象
	 * @param consumer 转换后做一些事情
	 */
	public static <T> void convertToCodeList(List<T> data, Consumer<T> consumer) {
		if (Objects.isNull(data) || CollUtil.isEmpty(data)) {
			return;
		}
		data.parallelStream().forEach(d -> {
			convert(d, true);
			if (consumer != null) {
				consumer.accept(d);
			}
		});
	}

	public static <T> void convertToDictionary(T data) {
		convertToDictionary(data, null);
	}

	/**
	 * 1 to 男
	 *
	 * @param data     需要转换的对象
	 * @param consumer 转换后做一些事情
	 */
	public static <T> void convertToDictionary(T data, Consumer<T> consumer) {
		if (Objects.isNull(data)) {
			return;
		}
		convert(data, false);
		if (consumer != null) {
			consumer.accept(data);
		}
	}

	public static <T> void convertToDictionaryList(List<T> data) {
		convertToDictionaryList(data, null);
	}

	/**
	 * 1 to 男
	 *
	 * @param data     需要转换的对象
	 * @param consumer 转换后做一些事情
	 */
	public static <T> void convertToDictionaryList(List<T> data, Consumer<T> consumer) {
		if (Objects.isNull(data) || CollUtil.isEmpty(data)) {
			return;
		}
		data.parallelStream().forEach(d -> {
			convert(d, false);
			if (consumer != null) {
				consumer.accept(d);
			}
		});
	}

	/**
	 * 转换字典中的值
	 *
	 * @param data     需要转换的对象
	 * @param isToCode {
	 *                      eg:      男-1、女-0
	 *                      true:    则将男转为1
	 *                      false:   则将1转为男
	 *                 }
	 */
	private static <T> void convert(T data, boolean isToCode) {

		// 获取当前类的所有字段
		Field[] fields = data.getClass().getDeclaredFields();

		// 过滤 static、 final、private static final字段
		final List<Field> filteredFields = Stream.of(fields).filter(f -> !(f.getModifiers() == Modifier.FINAL || f.getModifiers() == Modifier.STATIC || f.getModifiers() == (Modifier.PRIVATE | Modifier.STATIC | Modifier.FINAL) || f.getAnnotation(DictionaryConvertAnnotation.class) == null)).collect(Collectors.toList());

		// 处理
		for (Field f : filteredFields) {

			// 获取当前字段注解
			DictionaryConvertAnnotation annotation = f.getAnnotation(DictionaryConvertAnnotation.class);

			// 没有加注解的字段不处理
			if (annotation == null) {
				continue;
			}

			// 反射获取字段值
			Object value;

			// 字典类型
			String dictType = annotation.typeId();

			// 如果是引用其他字段则值从其他字段取
			if (StrUtil.isNotEmpty(annotation.codeField())) {
				value = ReflectUtil.getFieldValue(data, annotation.codeField());
			}

			// 否则获取当前字段值
			else {
				value = ReflectUtil.getFieldValue(data, f);
			}

			// 转换字典时字段值为空 不进行后续处理
			if (value == null) {
				continue;
			}

			// 类型
			final Class<?> classType = value.getClass();

			// 如果不是基本类型
			if (!ClassUtil.isBasicType(classType) && classType != String.class) {
				// 是List 循环则递归调用
				if (value instanceof List) {
					for (Object o : (List) value) {
						convert(o, isToCode);
					}
				}
				// 不是 List 则视为对象反射调用
				else {
					convert(value, isToCode);
				}
			}

			// 自定义的字典
			final String dicts = annotation.dictionaryList();

			// 转换字典时字段字典类型未配置（字典key都不配置转个毛线）
			if (StrUtil.isEmpty(dictType) && StrUtil.isEmpty(dicts)) {
				continue;
			}

			// 获取字典的对应 映射关系 （建议此处做缓存提高转换速度）
			final List<GpDictionary> dictionaryList;

			if (StrUtil.isNotBlank(dicts)) {
				final List<String> dictionaryStringList = StrUtil.splitTrim(dicts, ",");
				dictionaryList = Optional.ofNullable(dictionaryStringList).filter(CollUtil::isNotEmpty).map(s -> s.parallelStream().map(d -> {
					final List<String> dTrim = StrUtil.splitTrim(d, ":");
					if (dTrim.size() == 2) {
						GpDictionary gpDictionary = new GpDictionary();
						gpDictionary.setCode(Byte.valueOf(dTrim.get(0)));
						gpDictionary.setText(dTrim.get(1));
						return gpDictionary;
					}
					return null;
				}).filter(Objects::nonNull).collect(Collectors.toList())).orElse(new ArrayList<>(0));

			} else {
				GpDictionaryParameter.GetList jsonData = new GpDictionaryParameter.GetList();

				GpDictionaryParameter.GetList.EntityRelated entityRelated = new GpDictionaryParameter.GetList.EntityRelated();
				BaseParameter.BaseParamGetList.Order order = new BaseParameter.BaseParamGetList.Order();
				BaseParameter.BaseParamGetList.Page page = new BaseParameter.BaseParamGetList.Page();

				order.setColumnName("type_id");
				order.setIsASC(true);

				page.setPageIndex(CustomSymbolic.SQLQUERY_PAGEINDEX_DEFAULTVALUE);
				page.setPageSize(CustomSymbolic.SQLQUERY_KEYWORDS_PAGESIZE_MAX);

				jsonData.setEntityRelated(entityRelated);
				jsonData.setOrderList(new ArrayList<Order>() {
					{
						add(order);
					}
				});
				jsonData.setPage(page);

				ResultModel result = gpDictionaryUntBll.getList(jsonData);
				dictionaryList = CastObjectUtil.cast(result.getData());
			}

			// 是否匹配到了字典中的值
			boolean isMatchSuccess = false;

			// 获取当前字典值
			final String beanValue = Convert.toStr(value);

			// 支持类似 , 逗号隔开的字典转换, 如果需要支持其他 DictConvert#delimiter() 可在此设置
			// eg : 兴趣爱好 （足球,篮球,奥利给）
			// 转换后则为 （football,basketball,aoligei）
			final String delimiter = annotation.delimiter();

			final List<String> beanValues = StrUtil.splitTrim(beanValue, delimiter);

			// 1 to 男
			if (!isToCode) {
				// 逗号隔开字典转换支持
				if (CollUtil.isNotEmpty(beanValues) && beanValues.size() > 1) {
					final Map<Byte, String> dictMap = dictionaryList.stream().collect(Collectors.toMap(GpDictionary::getCode, GpDictionary::getText));
					final List<String> matchesDict = beanValues.stream().filter(dictMap::containsKey).map(dm -> Objects.nonNull(dictMap.get(dm)) ? dictMap.get(dm) : "").collect(Collectors.toList());
					if (CollUtil.isNotEmpty(matchesDict)) {
						isMatchSuccess = true;
						ReflectUtil.setFieldValue(data, f, CollUtil.join(matchesDict, delimiter));
					}
				} else {
					for (GpDictionary sysDictData : dictionaryList) {
						if (Objects.equals(Convert.toByte(value), sysDictData.getCode())) {
							ReflectUtil.setFieldValue(data, f, Objects.nonNull(sysDictData.getText()) ? sysDictData.getText() : value);
							isMatchSuccess = true;
							break;
						}
					}
				}
			}
			// 男 to 1
			else {
				// 逗号隔开字典转换支持
				if (CollUtil.isNotEmpty(beanValues) && beanValues.size() > 1) {
					final Map<String, Byte> dictionaryMap = dictionaryList.stream().collect(Collectors.toMap(GpDictionary::getText, GpDictionary::getCode));
					final List<String> matchesDict = beanValues.stream().filter(dictionaryMap::containsKey).map(dm -> Objects.nonNull(dictionaryMap.get(dm)) ? dictionaryMap.get(dm).toString() : "").collect(Collectors.toList());
					if (CollUtil.isNotEmpty(matchesDict)) {
						isMatchSuccess = true;
						ReflectUtil.setFieldValue(data, f, CollUtil.join(matchesDict, delimiter));
					}
				} else {
					for (GpDictionary sysDictData : dictionaryList) {
						if (Objects.equals(Convert.toStr(value), sysDictData.getText())) {
							ReflectUtil.setFieldValue(data, f, Objects.nonNull(sysDictData.getCode()) ? sysDictData.getCode() : value);
							isMatchSuccess = true;
							break;
						}
					}
				}
			}

			// 如果匹配不到字典中的值 且 字段中明确表示如果匹配不到就置为NULL
			if (!isMatchSuccess && annotation.ifNotMatchConvertToNull()) {
				ReflectUtil.setFieldValue(data, f, null);
			}

		}
	}

}
