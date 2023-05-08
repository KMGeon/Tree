<template>
    <el-header class="header">
        <el-menu mode="horizontal" router justify-center>
            <div id="hello" class="el-text el-text--primary mx-1">무건이 일정을 부탁해</div>
            <el-button class="el-text--primary" v-on:click="logout">로그아웃</el-button>
        </el-menu>
    </el-header>
</template>


<script>
import axios from 'axios';
import router from "../../router";

export default {
    data: function () {
        let response = JSON.parse(localStorage.getItem("response")).accessToken;
        return {
            posts: [],
            accessTokens: response,
        };
    },
    methods: {
        logout: function () {
            axios
                .delete("http://localhost:8080/api/logout", {
                    headers: {
                        Authorization: `Bearer ${this.accessTokens}`,
                    },
                })
                .then((response) => {
                    console.log(response);
                    localStorage.removeItem("response");
                    router.push("/")
                })
                .catch((error) => {
                    console.error(error);
                });
        }
    }
}
</script>


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
