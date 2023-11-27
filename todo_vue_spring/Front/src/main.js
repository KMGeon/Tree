import { createApp } from 'vue'

import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'

import App from './App.vue'
import router from './router'



import "bootstrap/dist/css/bootstrap-utilities.css"

const app = createApp(App)

app.use(router)

app.mount('#app')
