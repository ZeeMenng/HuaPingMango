
//  初始化验证码
var verifyCode = new GVerify("v_container");

//  初始化地区三级联动
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
        verifyCode.refresh(); //刷新图形验证码
    }
    return false;
  });
  //  自定义邮箱验证
  form.verify({
    emailTrue: function(value, item){ //value：表单的值、item：表单的DOM对象
      var reg = "^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$"
      if(!new RegExp(reg).test(value) && value != ''){
        return '邮箱格式错误！';
      }
    } 
  })
})

/**
 * [ 点击提交表单 ]
 */
function saveFn(data){
  var params = {
    "entityRelated":data
  }
  var regionId = $('.title .select-item:last').data('code');
  params.entityRelated.interactionType = 2; //类型：1：问专家，2：留建言
  params.entityRelated.fileIdStr = ""; //图片id
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




/**
 * [表单重置方法]
 * 
 */
function destroyForm(){
  $('input').val('');
  $('textarea').val('');
  $('.title').hide();
  //  重置地区三级联动选择
  $citypicker.citypicker('reset');
}

$('.reset-btn').on('click',function(){
  destroyForm();
})


