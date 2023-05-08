<template>
    <div id="app">
        <Header></Header>
        <SignupInput></SignupInput>
        <Footer></Footer>
    </div>
</template>

<script>
import SignupInput from "../components/signup/SignupInput.vue";
import Header from "../components/common/Header.vue";
import Footer from "../components/common/Footer.vue";

export default {
    components: {Header, Footer, SignupInput},
}
</script>

<style>
body {
    text-align: center;
    background-color: #f2f2f2;
}

input {
    border-style: groove;
}
</style>
