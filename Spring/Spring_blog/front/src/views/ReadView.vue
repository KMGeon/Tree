<script setup lang="ts">
import axios from "axios";
import {onMounted, ref} from "vue";
import router from "@/router";

const props = defineProps({
  postId:{
    type:[Number,String],
    required:true,
  },
});

const post = ref({
  id:0,
  title:"",
  content:"",
});

onMounted(()=>{
  axios.get(`/api/posts/${props.postId}`).then((response)=>{
    post.value = response.data;
  });
});

const moveToEdit = () =>{
  router.push({name:"edit",params:{ postId : props.postId}})
}
</script>

<template>
  <h2>{{post.title}}</h2>
  <p>{{post.content}}</p>

  <div>
    <el-button @click="moveToEdit()">수정</el-button>
  </div>
</template>