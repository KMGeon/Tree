<template>
    <div id="app" class="mt-6">
        <h3>회원가입</h3>
        <label>
            <el-input type="email" v-model="email" placeholder="이메일을 입력하세요."/>
        </label>
        <br/>
        <label>
            <el-input type="password" v-model="password" placeholder="비밀번호를 입력하세요."/>
        </label>
        <br/>
        <label>
            <el-input type="text" v-model="memName" placeholder="이름을 입력하세요."/>
        </label>
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
            password: "",
            memName: ""
        }
    },
    methods: {

        addTodo: function () {
            axios
                .post('http://localhost:8080/api/signup', {
                    email: this.email,
                    password: this.password,
                    name: this.memName
                })
                .then(response => {
                    console.log(response.data);
                    this.clearInput();
                    router.push("/login")
                })
                .catch(error => {
                    console.log("error message" + error)
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
