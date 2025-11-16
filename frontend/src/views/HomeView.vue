<template>
  <div class="home">
    <div class="hero">
      <h1>曲阜师范大学论坛</h1>
      <p>交流学习、分享经验、传播文化</p>
    </div>

    <div class="sections">
      <div class="section-card" @click="$router.push('/forum')">
        <h2>📚 论坛板块</h2>
        <ul>
          <li>大学生校园日常</li>
          <li>汉语学习指导</li>
          <li>旅游打卡推荐</li>
          <li>留学经验</li>
          <li>中华文化宣传</li>
        </ul>
      </div>

      <div class="section-card" @click="$router.push('/courses')">
        <h2>💼 商业板块</h2>
        <ul>
          <li>汉语教学课程</li>
          <li>汉教实习课程</li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script setup>
</script>

<style scoped>
.home {
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
}

.hero {
  text-align: center;
  padding: 60px 20px;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  color: white;
  border-radius: 10px;
  margin-bottom: 40px;
}

.hero h1 {
  font-size: 3rem;
  margin-bottom: 1rem;
}

.hero p {
  font-size: 1.5rem;
}

.sections {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
  gap: 30px;
}

.section-card {
  background: white;
  padding: 30px;
  border-radius: 10px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  cursor: pointer;
  transition: transform 0.3s, box-shadow 0.3s;
}

.section-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 8px 12px rgba(0, 0, 0, 0.15);
}

.section-card h2 {
  color: #667eea;
  margin-bottom: 20px;
}

.section-card ul {
  list-style: none;
  padding: 0;
}

.section-card li {
  padding: 10px 0;
  border-bottom: 1px solid #eee;
}

.section-card li:last-child {
  border-bottom: none;
}
</style>
