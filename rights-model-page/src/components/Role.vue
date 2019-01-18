<template>
    <div>
        <div class="source">
            <div class="el-input">
                <el-input v-model="qName" @change="sub()" style="width:200px;float:left;" placeholder="请输入用户名"></el-input>
                <el-button style="float:right;" type="primary" icon="el-icon-edit" v-on:click="addRole()" size="medium">添加</el-button>
            </div>
        </div>
        <el-table :data="roleList" style="width: 100%">
            <el-table-column prop="roleName" label="角色名" width="180">
            </el-table-column>
            <el-table-column prop="description" label="简介" width="180">
            </el-table-column>
            <el-table-column label="操作">
                <template slot-scope="scope">
                    <el-button @click.native.prevent="setResource(scope.row.roleId)" type="primary" size="small" icon="el-icon-arrow-right"
                        title="角色分配" circle></el-button>
                    <el-button @click.native.prevent="modifyRole(scope.row.roleId,scope.row.roleName,scope.row.description)"
                        type="primary" size="small" icon="el-icon-edit" title="修改" circle></el-button>
                    <el-button @click.native.prevent="deleteRole(scope.row.roleId,scope.row.roleName)" type="primary"
                        size="small" icon="el-icon-delete" title="删除" circle></el-button>
                </template>
            </el-table-column>
        </el-table>
        <!-- modal框 -->
        <el-dialog title="角色信息" :visible.sync="dialogFormVisible" :close-on-click-modal="false">
            <el-form :model="role">
                <el-form-item label="角色名" :label-width="formLabelWidth">
                    <el-input v-model="role.rolename" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="简介" :label-width="formLabelWidth">
                    <el-input type="textarea" v-model="role.description" auto-complete="off"></el-input>
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
      roleList: [],
      dialogFormVisible: false,
      role: {
        roleId: "",
        rolename: "",
        description: "",
        status: ""
      }
    };
  },
  methods: {
    async sub() {
      let data = {
        rolename: this.qName
      };
      let res = await http.get(api.getRoleList, data);
      console.log(res);
      if (res.data.code == 200) {
        this.roleList = res.data.data;
      }
    },
    addRole() {
      this.dialogFormVisible = true;
    },
    async modifyRole(roleId, roleName, description) {
      this.role.roleId = roleId;
      this.role.rolename = roleName;
      this.role.description = description;
      this.dialogFormVisible = true;
    },
    async deleteRole(roleId, roleName) {
      let data = {
        roleId: roleId
      };
      this.$msgbox({
        title: "删除",
        // 对话框 内容 --html
        message:
          '<div class="el-message-box__status el-icon-warning"></div><div class="el-message-box__message"><p>是否删除' +
          roleName +
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
              .post(api.deleteRole, data)
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
    },
    async addSubmit() {
      if (this.role.rolename == "" || this.role.rolename == undefined) {
        this.$notify.error({
          title: "异常",
          message: "角色名不能为空！" //提示信息
        });
        return;
      }
      let data = {
        roleId: this.role.roleId,
        rolename: this.role.rolename,
        description: this.role.description
      };
      let res = {};
      if (
        data.roleId != undefined &&
        data.roleId != "" &&
        data.roleId != null
      ) {
        res = await http.post(api.updateRole, data);
      } else {
        res = await http.post(api.addRole, data);
      }
      console.log(res);
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
    }
  },
  mounted() {
    this.sub();
  }
};
</script>

<style>
</style>