<template>
  <div id="app">
    <Header></Header>
    <!--    v-on:하위 컴포넌트에서 발생시킨 이벤트 이름 = 현재 컴포넌트의 메서드 명"-->
    <Input v-on:addOneItem="addOneItem"></Input>
    <!--    v-bind:내여보낼 프롭스 속성 이름 = "현재 위치의 컴포넌트 데이터 속성  -->
    <list v-bind:propsdata="todoItems"></list>
    <Footer></Footer>
  </div>
</template>

<script>
import Header from "@/App.vue";
import Footer from "@/App.vue";
import Input from "@/components/Input.vue";
import List from "@/components/List.vue";

export default {

  data: function () {
    return {
      todoItems: []
    }
  },
  methods: {
    addOneItem: function () {
      var obj = {completed: false, item: this.newTodoItem};
      localStorage.setItem(this.newTodoItem, JSON.stringify(obj));
    },
  },
  created: function () {
    if (localStorage.length > 0) {
      for (var i = 0; i < localStorage.length; i++) {
        this.todoItems.push(JSON.parse(localStorage.getItem(localStorage.key(i))));
      }
    }
  },
  components: {List, Input, Footer, Header},
  'Header': Header,
  'Footer': Footer
}
</script>

<style>

</style>
