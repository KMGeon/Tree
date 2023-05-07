<template>
    <div id="app">
        <Header></Header>
        <PostInput></PostInput>
        <Footer></Footer>
    </div>
</template>

<script>
import Header from "../components/common/Header.vue";
import Footer from "../components/common/Footer.vue";
import PostInput from "../components/post/PostInput.vue";

export default {
    components: {Header, Footer, PostInput},
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
