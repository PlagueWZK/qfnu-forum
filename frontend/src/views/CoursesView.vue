<template>
  <div class="courses-view">
    <h1>商业板块 - 课程中心</h1>

    <div class="course-types">
      <button
        @click="filterType = 'all'"
        :class="{ active: filterType === 'all' }"
        class="filter-btn"
      >
        全部课程
      </button>
      <button
        @click="filterType = 'teaching'"
        :class="{ active: filterType === 'teaching' }"
        class="filter-btn"
      >
        汉语教学课程
      </button>
      <button
        @click="filterType = 'internship'"
        :class="{ active: filterType === 'internship' }"
        class="filter-btn"
      >
        汉教实习课程
      </button>
      <button
        @click="filterType = 'trial'"
        :class="{ active: filterType === 'trial' }"
        class="filter-btn"
      >
        免费试听
      </button>
    </div>

    <div v-if="loading" class="loading">加载中...</div>
    <div v-else class="courses-grid">
      <div
        v-for="course in filteredCourses"
        :key="course.id"
        class="course-card"
      >
        <div class="course-badge" v-if="course.isTrial">
          免费试听
        </div>
        <h3>{{ course.title }}</h3>
        <p class="course-description">{{ course.description }}</p>
        <div class="course-instructor">
          <strong>主讲老师：{{ course.instructor }}</strong>
          <p>{{ course.instructorIntro }}</p>
        </div>
        <div class="course-footer">
          <span class="course-price">
            {{ course.price == 0 ? '免费' : `¥${course.price}` }}
          </span>
          <button @click="viewCourse(course)" class="btn-view">查看详情</button>
        </div>
      </div>
    </div>

    <div v-if="selectedCourse" class="course-modal" @click.self="selectedCourse = null">
      <div class="modal-content">
        <button class="close-btn" @click="selectedCourse = null">×</button>
        <h2>{{ selectedCourse.title }}</h2>
        <div class="modal-section">
          <h3>课程介绍</h3>
          <p>{{ selectedCourse.description }}</p>
        </div>
        <div class="modal-section">
          <h3>主讲老师</h3>
          <p><strong>{{ selectedCourse.instructor }}</strong></p>
          <p>{{ selectedCourse.instructorIntro }}</p>
        </div>
        <div class="modal-section" v-if="selectedCourse.videoUrl">
          <h3>课程视频</h3>
          <p>视频链接: <a :href="selectedCourse.videoUrl" target="_blank">{{ selectedCourse.videoUrl }}</a></p>
        </div>
        <div class="modal-footer">
          <span class="price-large">
            {{ selectedCourse.price == 0 ? '免费' : `¥${selectedCourse.price}` }}
          </span>
          <button class="btn-enroll">
            {{ selectedCourse.isTrial ? '立即试听' : '立即报名' }}
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue'
import { useForumStore } from '@/stores/forum'

const forumStore = useForumStore()

const courses = ref([])
const loading = ref(false)
const filterType = ref('all')
const selectedCourse = ref(null)

onMounted(async () => {
  loading.value = true
  await forumStore.fetchCourses()
  courses.value = forumStore.courses
  loading.value = false
})

const filteredCourses = computed(() => {
  if (filterType.value === 'all') {
    return courses.value
  } else if (filterType.value === 'trial') {
    return courses.value.filter(c => c.isTrial)
  } else {
    return courses.value.filter(c => c.type === filterType.value)
  }
})

const viewCourse = (course) => {
  selectedCourse.value = course
}
</script>

<style scoped>
.courses-view {
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
}

h1 {
  color: #333;
  margin-bottom: 30px;
}

.course-types {
  display: flex;
  gap: 10px;
  margin-bottom: 30px;
  flex-wrap: wrap;
}

.filter-btn {
  padding: 10px 20px;
  border: 2px solid #667eea;
  background: white;
  color: #667eea;
  border-radius: 5px;
  cursor: pointer;
  transition: all 0.3s;
}

.filter-btn:hover,
.filter-btn.active {
  background: #667eea;
  color: white;
}

.loading {
  text-align: center;
  padding: 40px;
  color: #666;
}

.courses-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
  gap: 30px;
}

.course-card {
  background: white;
  padding: 25px;
  border-radius: 10px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  transition: transform 0.3s, box-shadow 0.3s;
  position: relative;
}

.course-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
}

.course-badge {
  position: absolute;
  top: 10px;
  right: 10px;
  background: #4caf50;
  color: white;
  padding: 5px 10px;
  border-radius: 5px;
  font-size: 0.8rem;
}

.course-card h3 {
  color: #333;
  margin-bottom: 15px;
}

.course-description {
  color: #666;
  line-height: 1.6;
  margin-bottom: 15px;
}

.course-instructor {
  background: #f8f9fa;
  padding: 15px;
  border-radius: 5px;
  margin-bottom: 15px;
}

.course-instructor strong {
  color: #667eea;
  display: block;
  margin-bottom: 8px;
}

.course-instructor p {
  color: #666;
  font-size: 0.9rem;
  margin: 0;
}

.course-footer {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-top: 15px;
  padding-top: 15px;
  border-top: 1px solid #eee;
}

.course-price {
  font-size: 1.5rem;
  color: #ff6b6b;
  font-weight: bold;
}

.btn-view {
  background: #667eea;
  color: white;
  border: none;
  padding: 10px 20px;
  border-radius: 5px;
  cursor: pointer;
}

.btn-view:hover {
  background: #5568d3;
}

.course-modal {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: rgba(0, 0, 0, 0.7);
  display: flex;
  align-items: center;
  justify-content: center;
  z-index: 1000;
}

.modal-content {
  background: white;
  padding: 40px;
  border-radius: 10px;
  max-width: 600px;
  max-height: 80vh;
  overflow-y: auto;
  position: relative;
}

.close-btn {
  position: absolute;
  top: 15px;
  right: 15px;
  background: none;
  border: none;
  font-size: 2rem;
  cursor: pointer;
  color: #999;
}

.close-btn:hover {
  color: #333;
}

.modal-content h2 {
  color: #333;
  margin-bottom: 20px;
}

.modal-section {
  margin-bottom: 25px;
}

.modal-section h3 {
  color: #667eea;
  margin-bottom: 10px;
}

.modal-section p {
  color: #666;
  line-height: 1.6;
}

.modal-section a {
  color: #667eea;
  word-break: break-all;
}

.modal-footer {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-top: 30px;
  padding-top: 20px;
  border-top: 2px solid #eee;
}

.price-large {
  font-size: 2rem;
  color: #ff6b6b;
  font-weight: bold;
}

.btn-enroll {
  background: #4caf50;
  color: white;
  border: none;
  padding: 15px 30px;
  border-radius: 5px;
  cursor: pointer;
  font-size: 1.1rem;
}

.btn-enroll:hover {
  background: #45a049;
}
</style>
