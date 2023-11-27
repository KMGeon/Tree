<template>
    <div id="app">
        <LogoutHeader></LogoutHeader>
        <PostInput></PostInput>
        <PostList></PostList>
    </div>
</template>

<script>
import Header from "../components/common/Header.vue";
import PostInput from "../components/post/PostInput.vue";
import PostList from "@/components/post/PostList.vue";
import LogoutHeader from "@/components/common/LogoutHeader.vue";

export default {
    components: {LogoutHeader, PostList, Header, PostInput},
}
</script>

<style>

</style>
