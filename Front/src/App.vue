<template>
    <div id="app">
        <router-view></router-view>
    </div>
</template>

<script>
import Header from "./components/common/Header.vue";
import Footer from "./components/common/Footer.vue";

export default {
    components: {Header, Footer},
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
#footer {
    position: fixed;
    bottom: 0;
    width: 100%;
    background-color: #f2f2f2;
    text-align: center;
    padding: 10px;
}

</style>
