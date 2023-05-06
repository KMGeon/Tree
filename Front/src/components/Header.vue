<template>
    <header class="header">
        <div class="header__logo">
            <img src="src/assets/studyTime.png" alt="Study Time 로고" />
        </div>

        <div class="header_main">
            <strong>당신의 스터디를 관리, 스터디 타임</strong>
        </div>

        <div class="header__menu">
            <nav>
                <router-link class="text-bg-primary" to="/" >회원가입</router-link>
                <router-link class="text-bg-dark" to="/login">로그인</router-link>
            </nav>
        </div>
    </header>
</template>

<style scoped>
.header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    height: 60px;
    background-color: #fff;
    box-shadow: 0 0 8px rgba(0, 0, 0, 0.1);
    padding: 0 20px;
}

.header__logo img {
    height: 40px;
}

.header__menu nav {
    display: flex;
    align-items: center;
}

.header__menu nav a {
    color: #333;
    text-decoration: none;
    padding: 8px;
}

.header__menu nav a:hover {
    background-color: #eee;
    border-radius: 4px;
}
</style>
