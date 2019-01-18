<template>
    <div>
        <el-row class="tac">
            <el-col>
                <el-menu :default-active="$route.path" router class="el-menu-vertical-demo">
                    <el-menu-item index="/index/user">
                        <i class="el-icon-menu"></i>
                        <span slot="title">用户管理</span>
                    </el-menu-item>
                    <el-menu-item index="/index/role">
                        <i class="el-icon-menu"></i>
                        <span slot="title">角色管理</span>
                    </el-menu-item>
                    <el-menu-item index="/index/resource">
                        <i class="el-icon-menu"></i>
                        <span slot="title">资源管理</span>
                    </el-menu-item>
                </el-menu>
            </el-col>
        </el-row>
    </div>
</template>

<script>

</script>