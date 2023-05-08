<template>
    <el-header class="header">
        <el-menu mode="horizontal" router justify-center>
            <el-menu-item index="/">회원가입</el-menu-item>
            <div id="hello" class="el-text el-text--primary mx-1">무건이 일정을 부탁해</div>
            <el-menu-item index="/login">로그인</el-menu-item>
        </el-menu>
    </el-header>
</template>

<style scoped>
.header {
    padding: 0;
    height: 60px;
}

#hello {
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    font-size: 24px;
}
</style>
