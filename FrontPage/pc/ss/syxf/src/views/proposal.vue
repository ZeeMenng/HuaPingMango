<template>
  <div class="container">
    <div class="complaint-content-inner">
     <h2>填写投诉内容</h2>
    </div>
    <div class="container1">
      <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <span class="form-title">投诉者信息</span>
        <el-form-item label="姓名：" prop="name">
          <el-input v-model="ruleForm.name"></el-input>
        </el-form-item>
        <el-form-item label="联系电话：" prop="number">
          <el-input v-model="ruleForm.number"></el-input>
        </el-form-item>
        <el-form-item label="联系地址：" prop="address">
          <el-input v-model="ruleForm.address"></el-input>
        </el-form-item>
        <span class="form-title">投诉详情</span>
        <el-form-item label="溯源码：" prop="num">
          <el-input v-model="ruleForm.num" @change="getMess"></el-input>
        </el-form-item>
        <el-form-item label="投诉产品：" prop="product">
          <el-input v-model="ruleForm.product" disabled></el-input>
        </el-form-item>
        <el-form-item label="投诉对象：" prop="objectTxt">
          <el-input v-model="ruleForm.objectTxt" disabled></el-input>
        </el-form-item>
        <el-form-item label="投诉内容：" prop="desc">
          <el-input type="textarea" v-model="ruleForm.desc" :autosize="{ minRows: 6, maxRows: 20}"></el-input>
        </el-form-item>
        <el-form-item label="附件：" prop="file">
          <el-upload
            :action="axios.defaults.baseURL+'util/uploadRes/saveUploadFile'"
            :on-preview="handlePreview"
            :on-remove="handleRemove"
            :on-success="handleSuccess"
            list-type="picture-card">
            <el-button size="small" type="primary">点击上传</el-button>
          </el-upload>
        </el-form-item>
        <el-form-item>
          <el-button class="btn" type="primary" @click="submitForm('ruleForm')">提交</el-button>
        </el-form-item>
      </el-form>
    </div>
    <button></button>
  </div>
</template>
<script>
  export default {
    data() {
      let number = (rule, value, callback) => {
        if (!value) {
          return callback(new Error('请输入电话号码'));
        }
        setTimeout(() => {
          if (this.validatePhone(value)) {
            callback();
          }else{
            callback(new Error('请输入正确的联系电话'));
          }
        }, 500);
      };
      return {
        fileList:[],
        ruleForm: {
          name: '',
          number: '',
          address:'',
          num:'',
          product:'',
          productId:'',
          entId:'',
          objectTxt:'',
          desc: ''
        },
        rules: {
          name: [
            { required: true, message: '请输入您的姓名', trigger: 'blur' },
          ],
          number: [
            {  validator: number,required: true,  trigger: 'blur' }
          ],
             address: [
            { required: true, message: '请输入联系地址', trigger: 'blur' }
          ],
             num: [
            { required: true, message: '请输入溯源码', trigger: 'blur' }
          ],
            product: [
            { required: true, message: '请输入投诉产品', trigger: 'blur' }
          ],
           objectTxt: [
            { required: true, message: '请输入投诉对象', trigger: 'blur' }
          ],
          desc: [
            { required: true, message: '请填写投诉内容', trigger: 'blur' }
          ]
        }
      };
    },
    methods: {
      validatePhone(val){
        let isPhone = /^([0-9]{3,4}-)?[0-9]{7,8}$/;//手机号码
        let isMob= /^0?1[3|4|5|8][0-9]\d{8}$/;// 座机格式
        if(isMob.test(val)||isPhone.test(val)){
            return true;
        }
        else{
            return false;
        }
      },
      getMess(){
        axios.get('sy/traceInfoByTraceabilityCode/getCodeSimpleInfo', {
          params:{
            traceabilityCode:this.ruleForm.num
          }
        })
        .then(function (response) {
          return response.data
        })
        .catch(function (error) {
          console.log(error);
        }).then(res=>{
          console.log(res)
          if(res.isSuccess){
            this.ruleForm.product=res.data.productName;
            this.ruleForm.objectTxt=res.data.entName;
            this.ruleForm.entId=res.data.entId;
            this.ruleForm.productId=res.data.productId;
          }else{
            this.$message.error('请确认输入有效溯源码！');
            this.ruleForm.num = '';
          }
        });
      },
      handleSuccess(res,data){
        if(res.isSuccess){
          this.fileList.push(res.data.linkList[0])
          this.$message({
              message: '上传成功！',
              type: 'success'
          });
        }else{
            this.$message.error('上传失败！');
        }
      },
      submitForm(formName) {
        let params = {
          "userName":this.ruleForm.name,
          "telephone":this.ruleForm.number,
          "address":this.ruleForm.address,
          "complaintProducts":this.ruleForm.product,
          "productId":this.ruleForm.productId,
          "tracingSource":this.ruleForm.num,
          "complaintContent":this.ruleForm.desc,
          "complaintObject":this.ruleForm.objectTxt,
          "entId":this.ruleForm.entId,
          "imagePath":this.fileList.join(','),
        }
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.saveForm(params)
          } else {
            this.$message.error('必填项需要填写完整！');
            return false;
          }
        });
      },
      saveForm(params){
        axios.post('/sy/complaintSuggestion/addComplaintSuggestion', params)
        .then(function (response) {
          return response.data
        })
        .catch(function (error) {
          console.log(error);
        }).then(res=>{
          if(res.isSuccess){
            this.$message({
              message: '提交成功！',
              type: 'success'
            });
            //表单重置
            this.$refs['ruleForm'].resetFields();
          }else{
            this.$message.error('提交失败！');
          }
        });
      },
      handleRemove(file, fileList) {
        let urlStr = file.response.data.linkList[0];
        this.fileList = this.fileList.filter((item) => item != urlStr);
        axios.get('util/uploadRes/deleteImage', {
          params:{
            "imageUrl":urlStr
          }
        })
        .then(function (response) {
          return response.data
        })
        .catch(function (error) {
          console.log(error);
        }).then(res=>{
          if(res.isSuccess){
            this.$message({
              message: '删除成功！',
              type: 'success'
            });
          }else{
            this.$message.error('删除失败！');
          }
        });
      },
      handlePreview(file) {
        console.log(file);
      }
    }
  }
</script>
<style lang="scss" scoped>
.container{
  min-width: 1280px;
  min-height:calc(100vh - 180px)
}
.container1{
  width:900px;
  margin: 0 auto;
}
.complaint-content-inner{
  width: 1200px;
  margin: 25px auto;
  border-bottom: 1px solid #b9d8c2;
  h2{
    height: 25px;
    font-weight: normal;
    color: #108ee9;
    padding-left: 25px;
    background: url(../assets/img/titlt.png) no-repeat left center;
    font-size: 18px;
  }
}
.btn{
  margin: 30px 280px;
  width: 149px;
  height: 36px;
  border-radius: 18px;
  line-height: 16px;
  text-align: center;
  border: none;
  background: #49a9ee;
  color: #fff;
  font-size: 14px;
  background: gradient(linear,left bottom,right top,from(#49a9ee),to(#108ee9));
  box-shadow: 0 4px 20px 1px #108ee9;
  outline: none;
  cursor: pointer;
}
.form-title{
  font-size: 18px;
  color: #5e5e5e;
  text-indent: -15px;
  padding-top: 10px;
  margin-bottom: 40px;
  display: inline-block;
}
</style>
