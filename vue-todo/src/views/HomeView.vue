<template>
    <div id="app">
        <Header></Header>
        <Input></Input>
        <list></list>
        <Footer></Footer>
    </div>
</template>

<script>
import Header from "@/App.vue";
import Footer from "@/App.vue";
import Input from "@/components/Input.vue";
import List from "@/components/List.vue";

export default {
    components: {List, Input, Footer, Header},
    'Header': Header,
    'Footer': Footer
}
</script>

<style>

</style>
