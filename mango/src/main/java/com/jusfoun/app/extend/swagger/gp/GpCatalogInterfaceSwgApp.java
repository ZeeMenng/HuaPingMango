package com.jusfoun.app.extend.swagger.gp;

import java.util.ArrayList;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jusfoun.app.generate.swagger.gp.GpCatalogInterfaceGenSwgApp;
import com.jusfoun.ent.custom.ResultModel;
import com.jusfoun.ent.extend.gp.GpCatalogInterface;
import com.jusfoun.ent.parameter.gp.GpCatalogInterfaceParameter;
import com.jusfoun.utl.ClassFieldNullable;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2020/10/21 21:21:11
 * @description 接口分类字典管理存放接口分类信息，支持树形分级分类，主要但不限于业务上的分类方式，支持同时对接口进行多种分类。 对外接口，扩展自GpCatalogInterfaceGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/gp/gpCatalogInterface")
public class GpCatalogInterfaceSwgApp extends GpCatalogInterfaceGenSwgApp {

	@ApiOperation(value = "新增记录", notes = "新增单条记录")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串", required = true, dataType = "GpCatalogInterface") })
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel add(@RequestBody GpCatalogInterface jsonData) {

		ResultModel result = gpCatalogInterfaceUntBll.add(jsonData);

		return result;
	}

	@ApiOperation(value = "批量修改", notes = "同时修改多条记录、多个属性为不同值")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，对象列表", required = true, dataType = "GpCatalogInterfaceAddList") })
	@RequestMapping(value = "/updateListWithDff", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel updateListWithDff(@RequestBody GpCatalogInterfaceParameter.AddList jsonData) {
		
		 ArrayList<GpCatalogInterface> list=ClassFieldNullable.convertNull(jsonData.getEntityList(), new ArrayList<String>() {
			{
				add("fartherId");
			}
		});
		 jsonData.setEntityList(list);
		ResultModel result = gpCatalogInterfaceUntBll.updateListWithDff(jsonData.getEntityList());

		return result;
	}

}
