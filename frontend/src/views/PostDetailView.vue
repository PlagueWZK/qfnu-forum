<template>
  <div class="post-detail-view">
    <div v-if="loading" class="loading">加载中...</div>
    <div v-else-if="post" class="post-detail">
      <div class="post-header">
        <h1>{{ post.title }}</h1>
        <div class="post-info">
          <span>作者: {{ post.author }}</span>
          <span>发布时间: {{ formatDate(post.createdAt) }}</span>
          <span>👁️ {{ post.viewCount }}</span>
          <span>❤️ {{ post.likeCount }}</span>
        </div>
      </div>

      <div class="post-content">
        {{ post.content }}
      </div>

      <div class="post-actions">
        <button @click="likePost" class="btn-like">
          ❤️ 点赞 ({{ post.likeCount }})
        </button>
      </div>

      <div class="comments-section">
        <h2>评论 ({{ comments.length }})</h2>

        <div class="comment-form">
          <h3>发表评论</h3>
          <textarea
            v-model="newComment.content"
            placeholder="写下你的评论..."
            class="textarea-field"
            rows="4"
          ></textarea>
          <input
            v-model="newComment.author"
            placeholder="你的名字"
            class="input-field"
          />
          <button @click="createComment" class="btn-primary">发表评论</button>
        </div>

        <div class="comments-list">
          <div v-if="comments.length === 0" class="no-comments">
            暂无评论，快来发表第一条评论吧！
          </div>
          <div
            v-for="comment in comments"
            :key="comment.id"
            class="comment-card"
          >
            <div class="comment-header">
              <strong>{{ comment.author }}</strong>
              <span class="comment-date">{{ formatDate(comment.createdAt) }}</span>
            </div>
            <div class="comment-content">{{ comment.content }}</div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRoute } from 'vue-router'
import { useForumStore } from '@/stores/forum'

const route = useRoute()
const forumStore = useForumStore()

const post = ref(null)
const comments = ref([])
const loading = ref(false)
const newComment = ref({
  postId: null,
  content: '',
  author: ''
})

onMounted(async () => {
  const postId = parseInt(route.params.id)
  newComment.value.postId = postId
  
  loading.value = true
  await Promise.all([
    forumStore.fetchPostById(postId),
    forumStore.fetchComments(postId)
  ])
  
  post.value = forumStore.currentPost
  comments.value = forumStore.comments
  loading.value = false
})

const likePost = async () => {
  await forumStore.likePost(post.value.id)
  post.value = forumStore.currentPost
}

const createComment = async () => {
  if (!newComment.value.content || !newComment.value.author) {
    alert('请填写所有字段')
    return
  }
  
  try {
    await forumStore.createComment(newComment.value)
    comments.value = forumStore.comments
    newComment.value = {
      postId: parseInt(route.params.id),
      content: '',
      author: ''
    }
  } catch (error) {
    alert('发表评论失败')
  }
}

const formatDate = (dateString) => {
  if (!dateString) return ''
  const date = new Date(dateString)
  return date.toLocaleString('zh-CN')
}
</script>

<style scoped>
.post-detail-view {
  max-width: 900px;
  margin: 0 auto;
  padding: 20px;
}

.loading {
  text-align: center;
  padding: 40px;
  color: #666;
}

.post-detail {
  background: white;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.post-header {
  padding: 30px;
  border-bottom: 1px solid #eee;
}

.post-header h1 {
  color: #333;
  margin-bottom: 15px;
}

.post-info {
  display: flex;
  gap: 20px;
  color: #999;
  font-size: 0.9rem;
}

.post-content {
  padding: 30px;
  line-height: 1.8;
  color: #333;
  white-space: pre-wrap;
}

.post-actions {
  padding: 20px 30px;
  border-top: 1px solid #eee;
  border-bottom: 1px solid #eee;
}

.btn-like {
  background: #ff6b6b;
  color: white;
  border: none;
  padding: 10px 20px;
  border-radius: 5px;
  cursor: pointer;
  font-size: 1rem;
}

.btn-like:hover {
  background: #ff5252;
}

.comments-section {
  padding: 30px;
}

.comments-section h2 {
  color: #333;
  margin-bottom: 20px;
}

.comment-form {
  background: #f8f9fa;
  padding: 20px;
  border-radius: 8px;
  margin-bottom: 30px;
}

.comment-form h3 {
  color: #333;
  margin-bottom: 15px;
  font-size: 1.1rem;
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

.comments-list {
  display: flex;
  flex-direction: column;
  gap: 15px;
}

.no-comments {
  text-align: center;
  padding: 40px;
  color: #999;
}

.comment-card {
  background: #f8f9fa;
  padding: 15px;
  border-radius: 8px;
}

.comment-header {
  display: flex;
  justify-content: space-between;
  margin-bottom: 10px;
}

.comment-header strong {
  color: #667eea;
}

.comment-date {
  color: #999;
  font-size: 0.85rem;
}

.comment-content {
  color: #333;
  line-height: 1.6;
}
</style>
