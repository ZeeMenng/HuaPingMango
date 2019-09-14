package com.jusfoun.app.extend.swagger.pi;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jusfoun.app.generate.swagger.pi.PirInteractionImageGenSwgApp;


/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2018-7-5 11:35:44
 * @description pir_interaction_image 对外接口，扩展自PirInteractionImageGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/pi/pirInteractionImage")
public class  PirInteractionImageSwgApp extends  PirInteractionImageGenSwgApp {

}



