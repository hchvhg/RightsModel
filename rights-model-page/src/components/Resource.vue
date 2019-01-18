<template>
  <div>
    <div class="source">
      <div class="el-input">
        <el-input v-model="qName" @change="sub()" style="width:200px;float:left;" placeholder="请输入用户名"></el-input>
        <el-button style="float:right;" type="primary" icon="el-icon-edit" v-on:click="addResource()" size="medium">添加</el-button>
      </div>
    </div>
    <el-table :data="resourceList" style="width: 100%">
      <el-table-column prop="RESOURCENAME" label="资源名" width="180">
      </el-table-column>
      <el-table-column prop="TYPENAME" label="资源类型" width="180">
      </el-table-column>
      <el-table-column prop="RESOURCEPATH" label="资源地址" width="180">
      </el-table-column>
      <el-table-column prop="DESCRIPTION" label="简介">
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button @click.native.prevent="modifyResource(scope.row.RESOURCEID,scope.row.RESOURCENAME,scope.row.DESCRIPTION,scope.row.RESOURCEPATH,scope.row.RESOURCETYPE)"
            type="primary" size="small" icon="el-icon-edit" title="修改" circle></el-button>
          <el-button @click.native.prevent="deleteResource(scope.row.RESOURCEID,scope.row.RESOURCENAME)" type="primary"
            size="small" icon="el-icon-delete" title="删除" circle></el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- modal框 -->
    <el-dialog title="资源信息" :visible.sync="dialogFormVisible">
      <el-form :model="resource">
        <el-form-item label="资源名" :label-width="formLabelWidth">
          <el-input v-model="resource.resourceName" auto-complete="off"></el-input>
        </el-form-item>
        <!-- <el-form-item label="资源类型" :label-width="formLabelWidth">
          <el-input v-model="resource.resourceType" auto-complete="off"></el-input>
        </el-form-item> -->
        <el-form-item label="资源类型" :label-width="formLabelWidth">
          <el-select style="width:100%" v-model="resource.resourceType" placeholder="请选择">
            <el-option  v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="资源地址" :label-width="formLabelWidth">
          <el-input v-model="resource.resourcePath" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="简介" :label-width="formLabelWidth">
          <el-input type="textarea" v-model="resource.description" auto-complete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="addSubmit()">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import http from "../utils/http";
  import api from "../utils/api";
  export default {
    data() {
      return {
        formLabelWidth: "80px",
        qName: "",
        resourceList: [],
        dialogFormVisible: false,
        resource: {
          resourceName: "",
          resourceType: "",
          resourcePath: "",
          description: ""
        },
        options: [{
          value: 1,
          label: '菜单'
        }, {
          value: 2,
          label: '按钮'
        }]
      };
    },
    methods: {
      async sub() {
        let data = {
          resourceName: this.qName
        };
        let res = await http.get(api.getResourceList, data);
        if (res.data.code == 200) {
          this.resourceList = res.data.data;
        }
      },
      async modifyResource(resourceId, resourceName, description, resourcePath, resourceType) {
        this.resource.resourceId = resourceId;
        this.resource.resourceName = resourceName;
        this.resource.description = description;
        this.resource.resourcePath = resourcePath;
        this.resource.resourceType = resourceType;
        this.dialogFormVisible = true;
      },
      addResource() {
        this.resource.resourceId = '';
        this.resource.resourceName = '';
        this.resource.description = '';
        this.resource.resourcePath = '';
        this.resource.resourceType = '';
        this.dialogFormVisible = true;
      },
      async addSubmit() {
        if (
          this.resource.resourceName == "" ||
          this.resource.resourceName == undefined
        ) {
          this.$notify.error({
            title: "异常",
            message: "资源名不能为空！" //提示信息
          });
          return;
        }
        if (
          this.resource.resourcePath == "" ||
          this.resource.resourcePath == undefined
        ) {
          this.$notify.error({
            title: "异常",
            message: "资源路径不能为空！" //提示信息
          });
          return;
        }
        let data = {
          resourceId: this.resource.resourceId,
          resourceName: this.resource.resourceName,
          resourceType: this.resource.resourceType,
          resourcePath: this.resource.resourcePath,
          description: this.resource.description
        };
        let res = {};
        if (
          data.resourceId != undefined &&
          data.resourceId != "" &&
          data.resourceId != null
        ) {
          res = await http.post(api.updateResource, data);
        } else {
          res = await http.post(api.addResource, data);
        }
        if (res.data.code == 200) {
          this.$notify({
            title: "提示",
            message: res.data.msg, //提示信息
            type: "success"
          });
          this.sub();
          this.dialogFormVisible = false;
        } else {
          this.$notify.error({
            title: "异常",
            message: res.data.msg //提示信息
          });
        }
      },
      async deleteResource(resourceId, resourceName) {
        let data = {
          resourceId: resourceId
        };
        this.$msgbox({
          title: "删除",
          // 对话框 内容 --html
          message: '<div class="el-message-box__status el-icon-warning"></div><div class="el-message-box__message"><p>是否删除' +
            resourceName +
            "？确定将执行！</p></div>",
          dangerouslyUseHTMLString: true,
          showCancelButton: true,
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          beforeClose: (action, instance, done) => {
            // 点击 --确定
            if (action === "confirm") {
              // 显示--菊花
              instance.confirmButtonLoading = true;
              // http--请求
              http
                .post(api.deleteResource, data)
                .then(res => {
                  if (res.data.code == 200) {
                    // 请求成功--关闭对话框
                    done();
                    this.sub();
                    //显示--提示成功信息
                    this.$notify({
                      title: "删除成功",
                      message: res.data.msg, //提示信息
                      type: "success"
                    });
                    // 隐藏
                    instance.confirmButtonLoading = false;
                  }
                  //请求失败
                })
                .catch(() => {
                  // 提示-- 后台操作失败msg
                  this.$notify.error({
                    title: "错误",
                    message: res.data.msg //提示信息
                  });
                  done();
                });
            } else {
              done();
            }
          }
        });
      }
    },
    mounted() {
      this.sub();
    }
  };
</script>

<style>
</style>