<template>
    <div class="mt-5">
        <li v-for="(post, index) in posts" v-bind:key="post.memberId" class="my-3">
            <span class="el-text el-text--primary">
                <strong>({{ index + 1 }}) {{ post.postTitle }}</strong>
            </span>
            <div class="el-text el-text--default mx-1 my-content">
                {{ post.postContent }}
            </div>
        </li>
    </div>
</template>


<script>
import axios from "axios";

export default {
    data: function () {
        let response = JSON.parse(localStorage.getItem("response")).accessToken;
        return {
            posts: [],
            accessTokens: response,
        };
    },
    created: function () {
        this.posts = [];
        axios
            .get("http://localhost:8080/api/post/posts?page=0&size=10", {
                headers: {
                    Authorization: `Bearer ${this.accessTokens}`,
                },
            })
            .then((response) => {
                console.log(response)
                this.posts = response.data.content;
            })
            .catch((error) => {
                console.error(error);
            });
    },
};
</script>

<style scoped>
.my-content {
    padding-left: 20px;
    padding-right: 20px;
}


</style>
