<template>
    <div id="app">
        <h3>회원가입</h3>
        <label>
            <span>이메일</span>&nbsp;
            <input type="email" v-model="email" placeholder="정규식에 맞게 입력하세요">
        </label>
        <br/>
        <label>
            <span>비밀번호</span>&nbsp;
            <input type="password" v-model="password">
        </label>
        <br/>
        <label>
            <span>이름</span>&nbsp;
            <input type="text" v-model="memName">
        </label>
        <div>
            <button v-on:click="addTodo">확인</button>
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

        addTodo: function() {
            axios
                .post('http://localhost:8080/api/signup',{
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
                    console.error(error);
                    console.log("..................")
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


</style>
