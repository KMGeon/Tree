<template>
    <div id="input">
        <el-input type="text" v-model="newTodoItem" placeholder="일정을 입력하세요." style="width: 300px"/>
        <el-button class="d-inline-block" v-on:click="addTodo" v-on:keyup.enter="addTodo">입력</el-button>
    </div>
</template>

<script>
export default {
    data: function () {
        return {
            newTodoItem: ""
        }
    }, methods: {
        addTodo: function () {
            if (this.newTodoItem !== '') {
                var obj = {completed: false, item: this.newTodoItem};
                localStorage.setItem(this.newTodoItem, JSON.stringify(obj));
                this.clearInput();
            }
        },
        clearInput: function () {
            this.newTodoItem = '';
        },
    }
}
</script>

<style>
#input {
    text-align: center;
}
</style>
