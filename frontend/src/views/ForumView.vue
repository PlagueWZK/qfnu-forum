<template>
  <div class="forum-view">
    <h1>论坛板块</h1>
    
    <div class="categories">
      <div
        v-for="category in categories"
        :key="category.id"
        class="category-card"
        @click="goToCategory(category.id)"
      >
        <h3>{{ category.name }}</h3>
        <p>{{ category.description }}</p>
      </div>
    </div>

    <div class="recent-posts">
      <h2>最新帖子</h2>
      <div v-if="loading" class="loading">加载中...</div>
      <div v-else class="posts-list">
        <div
          v-for="post in posts"
          :key="post.id"
          class="post-card"
          @click="goToPost(post.id)"
        >
          <h3>{{ post.title }}</h3>
          <div class="post-meta">
            <span>作者: {{ post.author }}</span>
            <span>👁️ {{ post.viewCount }}</span>
            <span>❤️ {{ post.likeCount }}</span>
            <span>{{ formatDate(post.createdAt) }}</span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { useForumStore } from '@/stores/forum'

const router = useRouter()
const forumStore = useForumStore()

const categories = ref([])
const posts = ref([])
const loading = ref(false)

onMounted(async () => {
  loading.value = true
  await forumStore.fetchCategories('forum')
  categories.value = forumStore.categories
  
  await forumStore.fetchPosts()
  posts.value = forumStore.posts
  loading.value = false
})

const goToCategory = (id) => {
  router.push(`/category/${id}`)
}

const goToPost = (id) => {
  router.push(`/post/${id}`)
}

const formatDate = (dateString) => {
  if (!dateString) return ''
  const date = new Date(dateString)
  return date.toLocaleDateString('zh-CN')
}
</script>

<style scoped>
.forum-view {
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
}

h1 {
  color: #333;
  margin-bottom: 30px;
}

.categories {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
  gap: 20px;
  margin-bottom: 40px;
}

.category-card {
  background: white;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  cursor: pointer;
  transition: transform 0.3s, box-shadow 0.3s;
}

.category-card:hover {
  transform: translateY(-3px);
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.15);
}

.category-card h3 {
  color: #667eea;
  margin-bottom: 10px;
}

.category-card p {
  color: #666;
  font-size: 0.9rem;
}

.recent-posts {
  margin-top: 40px;
}

.recent-posts h2 {
  color: #333;
  margin-bottom: 20px;
}

.loading {
  text-align: center;
  padding: 40px;
  color: #666;
}

.posts-list {
  display: flex;
  flex-direction: column;
  gap: 15px;
}

.post-card {
  background: white;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  cursor: pointer;
  transition: transform 0.3s, box-shadow 0.3s;
}

.post-card:hover {
  transform: translateX(5px);
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.15);
}

.post-card h3 {
  color: #333;
  margin-bottom: 10px;
}

.post-meta {
  display: flex;
  gap: 20px;
  color: #999;
  font-size: 0.9rem;
}
</style>
