<script setup lang="ts">
import {ref} from "vue";
import axios from "axios";
import router from "@/router";

const email = ref('')
const password = ref('')
const name = ref('')

const signup = function () {
    axios.post("http://localhost:8080/api/signup", {
        email: email.value,
        password: password.value,
        name: name.value,
    }).then((response) => {
        console.log(response.data);
        router.push("/login")
    })
        .catch((error) => {
            console.error(error);
        });
};
</script>

<template>
    <div>
        <h1>회원가입</h1>
        <div>
            <label for="email">Email:</label>
            <input type="text" id="email" v-model="email"/>
        </div>
        <div>
            <label for="password">Password:</label>
            <input type="password" id="password" v-model="password"/>
        </div>
        <div>
            <label for="name">name:</label>
            <input type="text" id="name" v-model="name"/>
        </div>
        <button class="green" @click="signup()">확인</button>
    </div>
</template>
