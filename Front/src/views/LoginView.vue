<script setup lang="ts">
import {ref} from "vue";
import axios from "axios";
import router from "@/router";

const email = ref('')
const password = ref('')
const name = ref('')

const login = function () {
    axios.post("http://localhost:8080/api/login", {
        email: email.value,
        password: password.value
    }).then((response) => {
        console.log(response.data);
        localStorage.setItem("userData", JSON.stringify(response.data))
        router.push("/post")
    })
        .catch((error) => {
            console.error(error);
        });
};
</script>

<template>
    <div>
        <h1>로그인</h1>
        <div>
            <label for="email">Email:</label>
            <input type="text" id="email" v-model="email"/>
        </div>
        <div>
            <label for="password">Password:</label>
            <input type="password" id="password" v-model="password"/>
        </div>
        <button class="green" @click="login()">확인</button>
    </div>
</template>
