<template>
    <div id="app">
        <Header></Header>
        <LoginInput></LoginInput>
        <Footer></Footer>
    </div>
</template>

<script>
import Header from "../components/common/Header.vue";
import Footer from "../components/common/Footer.vue";
import LoginInput from "../components/login/LoginInput.vue";

export default {
    components: {Header, Footer, LoginInput},
}
</script>

<style>
body {
    text-align: center;
    background-color: #f2f2f2;
}

input {
    border-style: groove;
    width: 200px;
}

button {
    border-style: groove;
}
</style>
