
//	初始化验证码
var verifyCode = new GVerify("v_container");
//console.log(URL)
//	初始化地区三级联动
var $citypicker = $('#city-picker');

var layer,form;
layui.use(['form', 'layer'], function(){
   form = layui.form;
   layer = layui.layer;
	//监听提交
  form.on('submit(demo1)', function(data){
  	// console.log(data)
	var codeVal = $('#code_input').val()
	var codeRes = verifyCode.validate(codeVal);
  	if(codeRes){
  		saveFn(data.field)
  	}else{
        layer.msg("验证码错误");
        verifyCode.refresh();	//刷新图形验证码
  	}
    return false;
  });
  //	自定义邮箱验证
  form.verify({
	  emailTrue: function(value, item){ //value：表单的值、item：表单的DOM对象
	  	var reg = "^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$"
	    if(!new RegExp(reg).test(value) && value != ''){
	      return '邮箱格式错误！';
	    }
	  } 
	})
  
})
//	上传图片id
var picId = [];
$('.fileBtn').on('change',function(){
	//获取当前页面的绝对路径
	var localhostUrl = (location.hostname?location.hostname+':':"")+(location.port?location.port+':':"")+location.pathname
	$("#fileForm").ajaxSubmit({
        //url: URL+"/extend/swagger/gp/gpResource/saveUploadFile",
        url: URL+"mango/extend/swagger/gp/gpResource/saveUploadFile",
        type: "POST", /*设置表单以post方法提交*/
        data: {
        	"pageUrl":localhostUrl	//当前页面的绝对路径
        },
        dataType: 'json',
        headers:
        {
           "Authorization": ""
        },
        // contentType: "application/json",
        // crossDomain: true == !(document.all),
        success: function (res) {
            console.info(res);
            if(res.isSuccess){
            	picId.push(res.data[0].id);
            	var html = '<li><span class="delpic" data-id="'+res.data[0].id+'">x</span><img width="98" height="121" src="'+res.data[0].path+'"/></li>';
				$('.picList').append(html);
            }
            // console.log(picId)
        },
        error: function (error) {
            console.info(error);
        }
    })
	// var file = $(this)[0];
	// if (file.files && file.files[0]) {
	// 	var files = file.files[0]
	// 	//判断图片
	// 	if((files.type).indexOf("image/")==-1){  
	//      	alert("请上传图片!");  
	//     }else{
	// 		var reader = new FileReader();
	// 		reader.onload = function(evt){
	// 			var reg = /^data:base64,/;
	// 			var res = evt.target.result;
	// 			if(reg.test(res)){
	// 				res = res.replace(reg, "data:"+file.files[0].type+";base64,");
	// 			}
	// 			console.log(res)
	// 			var html = '<li><span class="delpic">x</span><img width="98" height="121" src="'+res+'"/></li>'
	// 			$('.picList').append(html)
	// 		};
	// 		console.log(reader.readAsDataURL(files));
	//     }
	// }else {
	// 	var html = '<li><span class="delpic">x</span><img width="98" height="121" src="'+res+'"/></li>'
	// 	$('.picList').append(html)
	// }
})

/**
 * [ 点击提交表单 ]
 */
function saveFn(data){
	var params = {
		"entityRelated":data
	}
	var regionId = $('.title .select-item:last').data('code');
	params.entityRelated.interactionType = 1; //类型：1：问专家，2：留建言
	params.entityRelated.fileIdStr = picId.join(','); //图片id
	params.entityRelated.regionId = regionId; //地址
	delete params.entityRelated.file;
	// console.log(params)
	ajaxPostForm('/extend/swagger/pi/piInteraction/addInteraction', JSON.stringify(params), saveFnSuccess);
}
function saveFnSuccess(res){
	if(res.isSuccess){
		layer.msg('提交成功！',{time:500},function(){
			destroyForm();
			history.back(-1)
		})
	}
}


//	点击X删除图片
$('body').on('click','.delpic',function(){
	var delId = $(this).data('id');
	picId.removeIndex(delId);
	$(this).parent().remove();
})

/**
 * 删除数组指定元素
 */
Array.prototype.indexOf = function(val) { 
	for (var i = 0; i < this.length; i++) { 
		if (this[i] == val) return i; 
	} 
	return -1; 
};
Array.prototype.removeIndex = function(val) { 
	var index = this.indexOf(val); 
	if (index > -1) { 
		this.splice(index, 1); 
	} 
};
/**
 * 删除数组指定元素结束
 */


/**
 * [表单重置方法]
 * 
 */
function destroyForm(){
	$('input').val('');
	$('textarea').val('');
	$('.picList').html('');
	$('.title').hide();
	//	清空上传图片的id数组
	picId = [];
	//	重置地区三级联动选择
	$citypicker.citypicker('reset');
}

$('.reset-btn').on('click',function(){
	destroyForm();
})