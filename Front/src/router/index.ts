import {createRouter, createWebHistory} from 'vue-router'

import SignupView from "@/views/SignupView.vue";
import LoginView from "@/views/LoginView.vue";
import PostView from "@/views/PostView.vue";

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/',
            name: 'signup',
            component: SignupView
        },
        {
            path: '/login',
            name: 'login',
            component: LoginView
        },
        {
            path: '/post',
            name: 'post',
            component: PostView
        }
    ]
})

export default router
