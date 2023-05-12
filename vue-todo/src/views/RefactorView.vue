<template>
    <div id="app">
        <Header></Header>
        <RefactorInput></RefactorInput>
        <RefactorList></RefactorList>
        <Footer></Footer>
    </div>
</template>

<script>
import Header from "@/App.vue";
import Footer from "@/App.vue";
import RefactorInput from "@/components/RefactorInput.vue";
import RefactorList from "@/components/RefactorList.vue";

export default {
    components: {
        RefactorInput,
        RefactorList,
        Footer,
        Header
    },
    'Header': Header,
    'Footer': Footer
}
</script>

<style>

</style>
