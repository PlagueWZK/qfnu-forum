# qfnu-forum

曲阜师范大学论坛系统 - 一个包含论坛和商业课程模块的全栈应用

## 项目简介

本项目是一个综合性的论坛系统，包含以下功能板块：

### 论坛板块
1. **大学生校园日常** - 校园周边美食、生活、校园文化
2. **汉语学习指导** - 给外国学生学习汉语的指导，相关渠道、困难点分析
3. **旅游打卡推荐** - 旅游景点推荐与打卡分享
4. **留学经验** - 留学经验分享与交流
5. **中华文化宣传** - 中华传统文化宣传与推广

### 商业板块
1. **汉语教学课程** - 针对外国学生的汉语教学课程，包括试听课程、付费课程及内容描述、主讲老师介绍、关于汉语教学的视频等
2. **汉教实习课程** - 针对中国汉教专业学生的实习指导、讲课难点应对

## 技术栈

### 后端
- Java 17
- Spring Boot 3.1.5
- Spring Data JPA
- MySQL 8
- Maven

### 前端
- Vue 3
- Vue Router
- Pinia (状态管理)
- Axios
- Vite

## 项目结构

```
qfnu-forum/
├── backend/                    # Spring Boot 后端
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/qfnu/forum/
│   │   │   │   ├── entity/    # 实体类
│   │   │   │   ├── repository/ # 数据访问层
│   │   │   │   ├── service/   # 业务逻辑层
│   │   │   │   ├── controller/ # 控制器层
│   │   │   │   ├── dto/       # 数据传输对象
│   │   │   │   └── config/    # 配置类
│   │   │   └── resources/
│   │   └── test/
│   └── pom.xml
├── frontend/                   # Vue 前端
│   ├── src/
│   │   ├── views/             # 页面组件
│   │   ├── stores/            # Pinia 状态管理
│   │   ├── services/          # API 服务
│   │   └── router/            # 路由配置
│   └── package.json
└── README.md
```

## 安装与运行

### 前置要求

- Java 17 或更高版本
- Maven 3.6+
- Node.js 16+ 和 npm
- MySQL 8.0+

### 数据库设置

1. 安装并启动 MySQL
2. 数据库将在应用启动时自动创建（qfnu_forum）
3. 默认数据库配置：
   - 用户名: root
   - 密码: 1026
   - 端口: 3306

如需修改数据库配置，请编辑 `backend/src/main/resources/application.properties`

### 后端启动

```bash
cd backend
mvn clean install
mvn spring-boot:run
```

后端将在 `http://localhost:8080` 启动

### 前端启动

```bash
cd frontend
npm install
npm run dev
```

前端将在 `http://localhost:5173` 启动

## API 接口

### 分类接口
- GET `/api/categories` - 获取所有分类
- GET `/api/categories/type/{type}` - 按类型获取分类
- GET `/api/categories/{id}` - 获取单个分类
- POST `/api/categories` - 创建分类
- PUT `/api/categories/{id}` - 更新分类
- DELETE `/api/categories/{id}` - 删除分类

### 帖子接口
- GET `/api/posts` - 获取所有帖子
- GET `/api/posts/category/{categoryId}` - 按分类获取帖子
- GET `/api/posts/{id}` - 获取单个帖子
- POST `/api/posts` - 创建帖子
- PUT `/api/posts/{id}` - 更新帖子
- DELETE `/api/posts/{id}` - 删除帖子
- POST `/api/posts/{id}/like` - 点赞帖子

### 评论接口
- GET `/api/comments/post/{postId}` - 获取帖子的评论
- POST `/api/comments` - 创建评论
- DELETE `/api/comments/{id}` - 删除评论

### 课程接口
- GET `/api/courses` - 获取所有课程
- GET `/api/courses/type/{type}` - 按类型获取课程
- GET `/api/courses/trial` - 获取试听课程
- GET `/api/courses/{id}` - 获取单个课程
- POST `/api/courses` - 创建课程
- PUT `/api/courses/{id}` - 更新课程
- DELETE `/api/courses/{id}` - 删除课程

## 功能特性

- ✅ 论坛分类浏览
- ✅ 发帖、评论功能
- ✅ 帖子点赞
- ✅ 浏览量统计
- ✅ 课程展示与分类
- ✅ 免费试听课程
- ✅ 响应式设计
- ✅ RESTful API
- ✅ CORS 跨域支持

## 开发说明

### 初始化数据

系统启动时会自动初始化以下数据：
- 5个论坛分类
- 3个示例课程（包含免费试听课程）

### 添加新功能

1. 后端：按照 entity -> repository -> service -> controller 的顺序开发
2. 前端：在 services/api.js 中添加 API 调用，在 stores 中管理状态，在 views 中创建页面

## 许可证

MIT License
