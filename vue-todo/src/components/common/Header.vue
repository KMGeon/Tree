<template>
    <el-header class="header">
        <el-menu mode="horizontal" router justify-center>
            <div id="hello" class="el-text el-text--primary">Todo List</div>
            <el-menu-item index="/">Original TodoList</el-menu-item>
            <el-menu-item index="/refactor">Refactor TodoList</el-menu-item>
        </el-menu>
    </el-header>
</template>

<style scoped>
.header {
    padding: 0;
    height: 60px;
}


</style>
