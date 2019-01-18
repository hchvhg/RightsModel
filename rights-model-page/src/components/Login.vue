<template>
    <div>
        <div class="elinput">
            <el-input v-model="User.username" placeholder="请输入用户名" clearable></el-input>
            <el-input v-model="User.password" placeholder="请输入密码" clearable></el-input>
        </div>
        <div>
            <el-button @click="loginYz()" type="primary">登陆</el-button>
        </div>
    </div>
</template>
<script>
import http from "../utils/http";
import api from "../utils/api";
export default {
  data() {
    return {
      User: {
        username: "",
        password: ""
      }
    };
  },
  methods: {
    async loginYz() {
      let data = {
        username: this.User.username,
        password: this.User.password
      };
      console.log(data);
      let res = await http.post(api.login, data);
      if (res.data.return_code == "FAIL") {
        this.$notify.error({
          title: "异常",
          message: res.data.return_msg //提示信息
        });
      } else {
        sessionStorage.setItem("userId", res.data.userId);
        sessionStorage.setItem("userName", res.data.userName);
        this.$store.state.userId = res.data.userId;
        console.log('登陆成功');
        this.$router.push({ path: "/index/user" });
      }
    }
  }
};
</script>

<style>
.elinput {
  width: 200px;
  margin: 0 auto;
}
</style>
