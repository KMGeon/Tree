<template>
  <div id="app">
    <TodoHeader></TodoHeader>
    <TodoInput v-on:addItem="addOneItem"></TodoInput>
    <TodoList v-bind:propsdata="todoItems" v-on:removeItem="removeOneItem" v-on:toggleItem="toggleOneItem"></TodoList>
    <TodoFooter v-on:clearAll="clearAllItems"></TodoFooter>
  </div>
</template>

<script>

import Header from "@/components/common/Header.vue";
import List from "@/components/List.vue";
import Input from "@/components/Input.vue";
import Footer from "@/components/common/Footer.vue";

export default {
  data: function() {
    return {
      todoItems: []
    }
  },
  methods: {
    addOneItem: function(todoItem) {
      var obj = {completed: false, item: todoItem};
      localStorage.setItem(todoItem, JSON.stringify(obj));
      this.todoItems.push(obj);
    },
    removeOneItem: function(todoItem, index) {
      this.todoItems.splice(index, 1);
      localStorage.removeItem(todoItem.item);
    },
    toggleOneItem: function(todoItem, index) {
      todoItem.completed = !todoItem.completed;
      localStorage.removeItem(todoItem.item);
      localStorage.setItem(todoItem.item, JSON.stringify(todoItem));
    },
    clearAllItems: function() {
      this.todoItems = [];
      localStorage.clear();
    }
  },
  components: {
    TodoHeader: Header,
    TodoInput: Input,
    TodoList: List,
    TodoFooter: Footer
  }
}
</script>

<style>
body {
  text-align: center;
  background-color: #F6F6F8;
}
input {
  border-style: groove;
  width: 200px;
}
button {
  border-style: groove;
}
.shadow {
  box-shadow: 5px 10px 10px rgba(0, 0, 0, 0.03)
}
</style>