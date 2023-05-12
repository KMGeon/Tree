<template>
    <div id="todoList" class="mt-5">
        <li v-for="(todoItem, index) in todoItems" v-bind:key="todoItem">
            <span>
                <el-button class="el-button--primary" v-on:click="toggleComplete">완료</el-button>
            </span>
            {{ todoItem }}
            <span class="m-5">
                <el-button class="el-button--small" v-on:click="removeTodo(todoItem,index)">삭제</el-button>
            </span>
        </li>
    </div>
</template>

<script>
export default {
    data: function () {
        return {
            todoItems: []
        }
    },
    created: function () {
        if (localStorage.length > 0) {
            for (var i = 0; i < localStorage.length; i++) {
                this.todoItems.push(localStorage.key(i));
            }
        }
    },
    methods: {
        removeTodo: function (todoItem, index) {
            localStorage.removeItem(todoItem);
            this.todoItems.splice(index, 1);//기존 배열을 변경해서 저장한다.
        },
        toggleComplete: function () {

        },

    }
}

</script>

<style>

#todoList {
    text-align: center;
}
</style>
