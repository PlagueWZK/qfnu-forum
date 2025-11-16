import axios from 'axios'

const API_BASE_URL = 'http://localhost:8080/api'

const api = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    'Content-Type': 'application/json'
  }
})

export const categoryAPI = {
  getAll: () => api.get('/categories'),
  getByType: (type) => api.get(`/categories/type/${type}`),
  getById: (id) => api.get(`/categories/${id}`),
  create: (data) => api.post('/categories', data),
  update: (id, data) => api.put(`/categories/${id}`, data),
  delete: (id) => api.delete(`/categories/${id}`)
}

export const postAPI = {
  getAll: () => api.get('/posts'),
  getByCategory: (categoryId) => api.get(`/posts/category/${categoryId}`),
  getById: (id) => api.get(`/posts/${id}`),
  create: (data) => api.post('/posts', data),
  update: (id, data) => api.put(`/posts/${id}`, data),
  delete: (id) => api.delete(`/posts/${id}`),
  like: (id) => api.post(`/posts/${id}/like`)
}

export const commentAPI = {
  getByPostId: (postId) => api.get(`/comments/post/${postId}`),
  create: (data) => api.post('/comments', data),
  delete: (id) => api.delete(`/comments/${id}`)
}

export const courseAPI = {
  getAll: () => api.get('/courses'),
  getByType: (type) => api.get(`/courses/type/${type}`),
  getTrial: () => api.get('/courses/trial'),
  getById: (id) => api.get(`/courses/${id}`),
  create: (data) => api.post('/courses', data),
  update: (id, data) => api.put(`/courses/${id}`, data),
  delete: (id) => api.delete(`/courses/${id}`)
}

export default api
