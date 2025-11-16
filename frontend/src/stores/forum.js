import { defineStore } from 'pinia'
import { categoryAPI, postAPI, commentAPI, courseAPI } from '@/services/api'

export const useForumStore = defineStore('forum', {
  state: () => ({
    categories: [],
    posts: [],
    currentPost: null,
    comments: [],
    courses: [],
    loading: false,
    error: null
  }),

  actions: {
    async fetchCategories(type = null) {
      this.loading = true
      try {
        const response = type 
          ? await categoryAPI.getByType(type)
          : await categoryAPI.getAll()
        this.categories = response.data
      } catch (error) {
        this.error = error.message
      } finally {
        this.loading = false
      }
    },

    async fetchPosts(categoryId = null) {
      this.loading = true
      try {
        const response = categoryId
          ? await postAPI.getByCategory(categoryId)
          : await postAPI.getAll()
        this.posts = response.data
      } catch (error) {
        this.error = error.message
      } finally {
        this.loading = false
      }
    },

    async fetchPostById(id) {
      this.loading = true
      try {
        const response = await postAPI.getById(id)
        this.currentPost = response.data
      } catch (error) {
        this.error = error.message
      } finally {
        this.loading = false
      }
    },

    async createPost(postData) {
      this.loading = true
      try {
        const response = await postAPI.create(postData)
        this.posts.unshift(response.data)
        return response.data
      } catch (error) {
        this.error = error.message
        throw error
      } finally {
        this.loading = false
      }
    },

    async likePost(id) {
      try {
        const response = await postAPI.like(id)
        const index = this.posts.findIndex(p => p.id === id)
        if (index !== -1) {
          this.posts[index] = response.data
        }
        if (this.currentPost && this.currentPost.id === id) {
          this.currentPost = response.data
        }
      } catch (error) {
        this.error = error.message
      }
    },

    async fetchComments(postId) {
      this.loading = true
      try {
        const response = await commentAPI.getByPostId(postId)
        this.comments = response.data
      } catch (error) {
        this.error = error.message
      } finally {
        this.loading = false
      }
    },

    async createComment(commentData) {
      this.loading = true
      try {
        const response = await commentAPI.create(commentData)
        this.comments.unshift(response.data)
        return response.data
      } catch (error) {
        this.error = error.message
        throw error
      } finally {
        this.loading = false
      }
    },

    async fetchCourses(type = null) {
      this.loading = true
      try {
        const response = type
          ? await courseAPI.getByType(type)
          : await courseAPI.getAll()
        this.courses = response.data
      } catch (error) {
        this.error = error.message
      } finally {
        this.loading = false
      }
    }
  }
})
