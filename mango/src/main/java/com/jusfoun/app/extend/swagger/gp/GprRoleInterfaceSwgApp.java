package com.jusfoun.app.extend.swagger.gp;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jusfoun.app.generate.swagger.gp.GprRoleInterfaceGenSwgApp;


/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2017/12/15 23:41:48
 * @description 角色拥有的接口权限。 对外接口，扩展自GprRoleInterfaceGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/gp/gprRoleInterface")
public class GprRoleInterfaceSwgApp extends GprRoleInterfaceGenSwgApp {

}



