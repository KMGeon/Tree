<template>
    <div class="mt-7">
        <h3>로그인</h3>
        <label>
            <el-input type="text" v-model="email" placeholder="이메일을 입력하세요."/>
        </label>
        <br/>
        <label>
            <el-input type="text" v-model="password" placeholder="비밀번호를 입력하세요"/>
        </label>
        <br/>
        <div>
            <el-button class="el-button--primary" v-on:click="addTodo">확인</el-button>
        </div>
    </div>
</template>

<script>
import axios from 'axios';
import router from "../../router";

export default {
    data: function () {
        return {
            email: "",
            password: ""
        }
    },
    methods: {

        addTodo: function () {
            axios
                .post('http://localhost:8080/api/login', {
                    email: this.email,
                    password: this.password
                })
                .then(response => {
                    console.log(response.data);
                    localStorage.setItem("response", JSON.stringify(response.data));
                    this.clearInput();
                    router.push("/post")
                })
                .catch(error => {
                    console.error(error);
                });
        },
        clearInput: function () {
            this.email = '';
            this.password = '';
            this.memName = '';
        },
    }
}
</script>

<style scoped>
.el-input{
    width: 300px;
}

</style>
