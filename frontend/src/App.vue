<script setup>
import { RouterLink, RouterView } from 'vue-router'
</script>

<template>
  <div id="app">
    <header>
      <div class="container">
        <div class="logo">
          <h1>曲阜师范大学论坛</h1>
        </div>
        <nav>
          <RouterLink to="/">首页</RouterLink>
          <RouterLink to="/forum">论坛</RouterLink>
          <RouterLink to="/courses">课程</RouterLink>
        </nav>
      </div>
    </header>

    <main>
      <RouterView />
    </main>

    <footer>
      <div class="container">
        <p>&copy; 2024 曲阜师范大学论坛. All rights reserved.</p>
      </div>
    </footer>
  </div>
</template>

<style>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

body {
  font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen',
    'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue',
    sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  background-color: #f5f5f5;
}

#app {
  min-height: 100vh;
  display: flex;
  flex-direction: column;
}

.container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 0 20px;
}

header {
  background: white;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  position: sticky;
  top: 0;
  z-index: 100;
}

header .container {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 1rem 20px;
}

.logo h1 {
  color: #667eea;
  font-size: 1.5rem;
}

nav {
  display: flex;
  gap: 30px;
}

nav a {
  color: #333;
  text-decoration: none;
  font-size: 1.1rem;
  transition: color 0.3s;
}

nav a:hover,
nav a.router-link-active {
  color: #667eea;
}

main {
  flex: 1;
  padding: 20px 0;
}

footer {
  background: #333;
  color: white;
  padding: 20px 0;
  margin-top: 40px;
  text-align: center;
}

@media (max-width: 768px) {
  header .container {
    flex-direction: column;
    gap: 15px;
  }

  nav {
    width: 100%;
    justify-content: center;
  }

  .logo h1 {
    font-size: 1.2rem;
  }

  nav a {
    font-size: 0.9rem;
  }
}
</style>
