$(function(){
    //父级模块
    showSelect();
    $("#selectLevelCode").change(function(){
        showSelect();
    })
    $("#selectModuleFst").change(function(){
        $("#selectModuleSec").html('<option value=""></option>');
        var ids = $(this).children('option:selected').val();
        if(ids == '' || ids == null || ids == undefined){
            return;
        }
        var commmitData = {
            entityRelated: {
                fartherId: ids
            }
        };
        var paramsData = JSON.stringify(commmitData)
        initSelect(paramsData,"selectModuleSec");
        hideVal();
    })
    $("#selectModuleSec").change(function(){
        hideVal();
    })

    function showSelect(){
        var val = $("#selectLevelCode").val();
        console.log('级别'+val)
        if(val == 1){
            $("#divModuleFst").hide();
            $("#divModuleSec").hide();
        }else if(val == 2){
            $("#divModuleFst").show();
            $("#divModuleSec").hide();
        }else if(val == 3){
            $("#divModuleFst").show();
            $("#divModuleSec").show();
        }else{
            $("#divModuleFst").hide();
            $("#divModuleSec").hide();
        }
        hideVal();
    }

    //改变隐藏域的值
    function hideVal(){
        var val = $("#selectLevelCode").children('option:selected').val();
        if(val == 1){
            $("#textLevelText").val("第一级");
            $("#textFartherId").val("");
        }else if(val == 2){
            $("#textLevelText").val("第二级");
            $("#textFartherId").val($("#selectModuleFst").val());
        }else if(val == 3){
            $("#textLevelText").val("第三级");
            $("#textFartherId").val($("#selectModuleSec").val());
        }else{
            $("#textFartherId").val("");
        }
    }
    //初始化一级
    var commmitData = {
        entityRelated: {
            levelCode:1
        }
    };
    var paramsData = JSON.stringify(commmitData)
    initSelect(paramsData,'selectModuleFst');

    function initSelect(paramsData,domId){
        var selectParam = {
            selectId : domId,
            textField : "name",
            valueField : "id"
        };
        var ajaxParam = {
            url : RU_GPMODULE_GETLISTBYJSONDATA+ "?jsonData="+paramsData
        }
        initDropDownList(selectParam, ajaxParam);
    }
})