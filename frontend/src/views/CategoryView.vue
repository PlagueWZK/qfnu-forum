<template>
  <div class="category-view">
    <div v-if="category" class="category-header">
      <h1>{{ category.name }}</h1>
      <p>{{ category.description }}</p>
    </div>

    <div class="actions">
      <button @click="showCreateForm = !showCreateForm" class="btn-primary">
        {{ showCreateForm ? '取消' : '发表新帖' }}
      </button>
    </div>

    <div v-if="showCreateForm" class="create-post-form">
      <h3>发表新帖</h3>
      <input v-model="newPost.title" placeholder="标题" class="input-field" />
      <textarea v-model="newPost.content" placeholder="内容" class="textarea-field" rows="6"></textarea>
      <input v-model="newPost.author" placeholder="作者" class="input-field" />
      <button @click="createPost" class="btn-primary">发布</button>
    </div>

    <div class="posts-list">
      <div v-if="loading" class="loading">加载中...</div>
      <div v-else-if="posts.length === 0" class="no-posts">暂无帖子</div>
      <div
        v-for="post in posts"
        :key="post.id"
        class="post-card"
        @click="goToPost(post.id)"
      >
        <h3>{{ post.title }}</h3>
        <p class="post-preview">{{ post.content.substring(0, 100) }}...</p>
        <div class="post-meta">
          <span>作者: {{ post.author }}</span>
          <span>👁️ {{ post.viewCount }}</span>
          <span>❤️ {{ post.likeCount }}</span>
          <span>{{ formatDate(post.createdAt) }}</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { useForumStore } from '@/stores/forum'

const route = useRoute()
const router = useRouter()
const forumStore = useForumStore()

const category = ref(null)
const posts = ref([])
const loading = ref(false)
const showCreateForm = ref(false)
const newPost = ref({
  title: '',
  content: '',
  author: '',
  categoryId: null
})

onMounted(async () => {
  const categoryId = parseInt(route.params.id)
  newPost.value.categoryId = categoryId
  
  loading.value = true
  await Promise.all([
    forumStore.fetchCategories(),
    forumStore.fetchPosts(categoryId)
  ])
  
  category.value = forumStore.categories.find(c => c.id === categoryId)
  posts.value = forumStore.posts
  loading.value = false
})

const createPost = async () => {
  if (!newPost.value.title || !newPost.value.content || !newPost.value.author) {
    alert('请填写所有字段')
    return
  }
  
  try {
    await forumStore.createPost(newPost.value)
    posts.value = forumStore.posts
    newPost.value = {
      title: '',
      content: '',
      author: '',
      categoryId: parseInt(route.params.id)
    }
    showCreateForm.value = false
  } catch (error) {
    alert('发布失败')
  }
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
.category-view {
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
}

.category-header {
  background: white;
  padding: 30px;
  border-radius: 8px;
  margin-bottom: 20px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.category-header h1 {
  color: #667eea;
  margin-bottom: 10px;
}

.category-header p {
  color: #666;
}

.actions {
  margin-bottom: 20px;
}

.btn-primary {
  background: #667eea;
  color: white;
  border: none;
  padding: 10px 20px;
  border-radius: 5px;
  cursor: pointer;
  font-size: 1rem;
}

.btn-primary:hover {
  background: #5568d3;
}

.create-post-form {
  background: white;
  padding: 30px;
  border-radius: 8px;
  margin-bottom: 20px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.create-post-form h3 {
  color: #333;
  margin-bottom: 20px;
}

.input-field,
.textarea-field {
  width: 100%;
  padding: 10px;
  margin-bottom: 15px;
  border: 1px solid #ddd;
  border-radius: 5px;
  font-size: 1rem;
}

.textarea-field {
  resize: vertical;
}

.loading,
.no-posts {
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

.post-preview {
  color: #666;
  margin-bottom: 10px;
}

.post-meta {
  display: flex;
  gap: 20px;
  color: #999;
  font-size: 0.9rem;
}
</style>
