<template>
  <div>
    <div class="source">
      <div class="el-input">
        <!-- <el-select v-model="whoRepo" @change="sub()">
                <el-option label="我的仓库" value="mine">我的仓库</el-option>
                <el-option label="所有仓库" value="all">所有仓库</el-option>
            </el-select> -->
        <el-input v-model="qName" @change="sub()" style="width:200px;float:left;" placeholder="请输入用户名"></el-input>
        <el-button style="float:right;" type="primary" icon="el-icon-edit" v-on:click="addUser()" size="medium">添加</el-button>
      </div>
    </div>
    <el-table :data="userList" style="width: 100%">
      <el-table-column prop="username" label="用户名" width="180">
      </el-table-column>
      <el-table-column prop="loginName" label="登录名" width="180">
      </el-table-column>
      <el-table-column prop="loginPassword" label="登陆密码">
      </el-table-column>
      <el-table-column prop="description" label="简介">
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button @click.native.prevent="setRole(scope.row.userId)" type="primary" size="small" icon="el-icon-arrow-right"
            title="角色分配" circle></el-button>
          <el-button @click.native.prevent="modifyUser(scope.row.userId,scope.row.username,scope.row.loginName,scope.row.loginPassword,scope.row.description)"
            type="primary" size="small" icon="el-icon-edit" title="修改" circle></el-button>
          <el-button @click.native.prevent="deleteUser(scope.row.userId,scope.row.username)" type="primary" size="small"
            icon="el-icon-delete" title="删除" circle></el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- modal框 -->
    <el-dialog title="用户信息" :visible.sync="dialogFormVisible" :close-on-click-modal="false">
      <el-form :model="user">
        <el-form-item label="用户名" :label-width="formLabelWidth">
          <el-input v-model="user.username" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="登陆名" :label-width="formLabelWidth">
          <el-input v-model="user.loginName" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="登陆密码" :label-width="formLabelWidth">
          <el-input v-model="user.loginPassword" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="简介" :label-width="formLabelWidth">
          <el-input type="textarea" v-model="user.description" auto-complete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="addSubmit()">确 定</el-button>
      </div>
    </el-dialog>
    <el-dialog style="text-align:left !important;" title="角色分配" :visible.sync="dialogFormVisible2" :close-on-click-modal="false">
      <div>
      <el-transfer filterable="true" v-model="roleData" :data="roleList" :titles="['角色列表', '当前角色']">
      </el-transfer>
      </div>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible2 = false">取 消</el-button>
        <el-button type="primary" @click="submitRole()">确 定</el-button>
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
      currentUserId: "",
      formLabelWidth: "80px",
      qName: "",
      userList: [],
      roleList: [],
      roleData: [],
      dialogFormVisible: false,
      dialogFormVisible2: false,
      user: {
        username: "",
        loginName: "",
        loginPassword: "",
        description: "",
        isAdmin: ""
      }
    };
  },
  methods: {
    async sub() {
      let data = {
        username: this.qName
      };
      let res = await http.get(api.getUserList, data);
      if (res.data.code == 200) {
        this.userList = res.data.data;
      }
    },
    async submitRole() {
      let data = {
        userId: this.currentUserId,
        roleIds: this.roleData.join(",")
      };
      let res = await http.post(api.setUserRoles, data);
      if (res.data.code == 200) {
        this.$notify({
          title: "提示",
          message: res.data.msg, //提示信息
          type: "success"
        });
      }
      this.dialogFormVisible2 = false;
    },
    async setRole(userId) {
      //先清空
      this.roleList = [];
      this.roleData = [];
      this.currentUserId = userId;
      let data = {
        userId: userId
      };
      let res = await http.get(api.getRoleData, data);
      res.data.data1.forEach(element => {
        this.roleData.push(element.ROLEID);
      });
      res.data.data2.forEach(element => {
        this.roleList.push({
          label: element.roleName,
          key: element.roleId
        });
      });
      this.dialogFormVisible2 = true;
    },
    addUser() {
      this.user.userId = "";
      this.user.username = "";
      this.user.loginName = "";
      this.user.loginPassword = "";
      this.user.description = "";
      this.dialogFormVisible = true;
    },
    async modifyUser(userId, userName, loginName, loginPassword, description) {
      this.user.userId = userId;
      this.user.username = userName;
      this.user.loginName = loginName;
      this.user.loginPassword = loginPassword;
      this.user.description = description;
      this.dialogFormVisible = true;
    },
    async deleteUser(userId, userName) {
      let data = {
        userId: userId
      };
      this.$msgbox({
        title: "删除",
        // 对话框 内容 --html
        message:
          '<div class="el-message-box__status el-icon-warning"></div><div class="el-message-box__message"><p>是否删除' +
          userName +
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
      if (this.user.username == "" || this.user.username == undefined) {
        this.$notify.error({
          title: "异常",
          message: "用户名不能为空！" //提示信息
        });
        return;
      }
      if (this.user.loginName == "" || this.user.loginName == undefined) {
        this.$notify.error({
          title: "异常",
          message: "登陆名不能为空！" //提示信息
        });
        return;
      }
      if (
        this.user.loginPassword == "" ||
        this.user.loginPassword == undefined
      ) {
        this.$notify.error({
          title: "异常",
          message: "登陆密码不能为空！" //提示信息
        });
        return;
      }
      let data = {
        userId: this.user.userId,
        username: this.user.username,
        loginName: this.user.loginName,
        loginPassword: this.user.loginPassword,
        description: this.user.description
      };
      let res = {};
      if (
        data.userId != undefined &&
        data.userId != "" &&
        data.userId != null
      ) {
        res = await http.post(api.updateUser, data);
      } else {
        res = await http.post(api.addUser, data);
      }

      if (res.data.code == 200) {
        this.sub();
        this.dialogFormVisible = false;
        // this.user.username = "";
        // this.user.loginName = "";
        // this.user.loginPassword = "";
        // this.user.description = "";
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