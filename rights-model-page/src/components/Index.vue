<template>
    <div>
        <el-container>
            <el-header class="header">权限管理系统</el-header>
            <el-container class="wrap">
                <el-aside width="150px">
                    <v-menu></v-menu>
                </el-aside>
                <el-main class="main">
                    <transition name="slide-fade">
                        <router-view ></router-view>
                    </transition>
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>

<script>
    import Menu from "./Menu.vue";
    export default {
        name: "index",
        components: {
            'v-menu': Menu
        },
        data() {
            return {};
        },
        methods: {},
        mounted() {}
    };
</script>
<style>
</style>