<template>
    <div class="inputBox">
        <label>
            <div>
                <el-input type="text" v-model="title" v-on:keyup.enter="addTodo" placeholder="일정을 입력하세요."/>
            </div>
        </label>
        <label>
            <div>
                <el-input type="text" v-model="content" v-on:keyup.enter="addTodo" placeholder="일정의 상세를 말하세요."/>
            </div>
        </label>
        <el-button class="el-button--primary" v-on:click="addTodo">일정 추가하기</el-button>
    </div>
</template>

<script>
import axios from "axios";
import router from "@/router";

export default {
    data: function () {
        let response = JSON.parse(localStorage.getItem("response")).accessToken;
        return {
            title: "",
            content: "",
            accessTokens: response
        }
    },
    methods: {
        addTodo: function () {
            axios
                .post('http://localhost:8080/api/post', {
                    title: this.title,
                    content: this.content
                }, {
                    headers: {
                        Authorization: `Bearer ${this.accessTokens}`
                    }
                })
                .then(response => {
                    router.push("/post")
                })
                .catch(error => {
                    console.error(error);
                });
            this.clearInput();
        },
        clearInput: function () {
            this.title = '';
            this.content = '';
        }
    }
}
</script>

<style scoped>
.el-input {
    width: 300px;
}
</style>
