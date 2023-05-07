<template>
    <div id="app">
        <label>
            <span>이메일</span>&nbsp;
            <input type="text" v-model="email" placeholder="정규식에 맞게 입력하세요">
        </label>
        <br/>
        <label>
            <span>비밀번호</span>&nbsp;
            <input type="text" v-model="password">
        </label>
        <br/>
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
            password: ""
        }
    },
    methods: {

        addTodo: function() {
            axios
                .post('http://localhost:8080/api/login',{
                    email: this.email,
                    password: this.password
                })
                .then(response => {
                    console.log(response.data);
                    this.clearInput();
                    router.push("/post")
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
