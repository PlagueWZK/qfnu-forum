# 开发指南 (Development Guide)

## 项目概览

这是一个完整的全栈应用，包含：
- Spring Boot 后端 (Java 17)
- Vue 3 前端
- MySQL 数据库

## 环境准备

### 安装依赖

1. **Java 17+**
   ```bash
   # Ubuntu/Debian
   sudo apt update
   sudo apt install openjdk-17-jdk
   
   # macOS
   brew install openjdk@17
   ```

2. **Maven**
   ```bash
   # Ubuntu/Debian
   sudo apt install maven
   
   # macOS
   brew install maven
   ```

3. **Node.js 和 npm**
   ```bash
   # Ubuntu/Debian
   curl -fsSL https://deb.nodesource.com/setup_18.x | sudo -E bash -
   sudo apt install nodejs
   
   # macOS
   brew install node
   ```

4. **MySQL**
   ```bash
   # Ubuntu/Debian
   sudo apt install mysql-server
   sudo mysql_secure_installation
   
   # macOS
   brew install mysql
   brew services start mysql
   ```

### 数据库配置

1. 登录 MySQL:
   ```bash
   mysql -u root -p
   ```

2. 设置 root 密码为 1026 (如果还没有):
   ```sql
   ALTER USER 'root'@'localhost' IDENTIFIED BY '1026';
   FLUSH PRIVILEGES;
   ```

数据库 `qfnu_forum` 会在应用启动时自动创建。

## 启动应用

### 后端启动

```bash
cd backend
mvn clean install
mvn spring-boot:run
```

后端 API 将在 http://localhost:8080 运行

### 前端启动

在新的终端窗口中:

```bash
cd frontend
npm install
npm run dev
```

前端应用将在 http://localhost:5173 运行

## 测试 API

### 使用 curl 测试

1. **获取所有分类**
   ```bash
   curl http://localhost:8080/api/categories
   ```

2. **创建新帖子**
   ```bash
   curl -X POST http://localhost:8080/api/posts \
     -H "Content-Type: application/json" \
     -d '{
       "title": "测试帖子",
       "content": "这是一个测试帖子的内容",
       "author": "测试用户",
       "categoryId": 1
     }'
   ```

3. **获取所有课程**
   ```bash
   curl http://localhost:8080/api/courses
   ```

## 数据库结构

### Categories (分类表)
- id: 主键
- name: 分类名称
- description: 分类描述
- type: 类型 (forum/commercial)
- created_at, updated_at: 时间戳

### Posts (帖子表)
- id: 主键
- title: 标题
- content: 内容
- author: 作者
- category_id: 分类ID
- view_count: 浏览次数
- like_count: 点赞数
- created_at, updated_at: 时间戳

### Comments (评论表)
- id: 主键
- post_id: 帖子ID
- content: 内容
- author: 作者
- created_at: 时间戳

### Courses (课程表)
- id: 主键
- title: 标题
- description: 描述
- instructor: 讲师
- instructor_intro: 讲师介绍
- price: 价格
- is_trial: 是否试听
- type: 类型 (teaching/internship)
- video_url: 视频链接
- created_at, updated_at: 时间戳

## 项目结构详解

### 后端 (Spring Boot)

```
backend/
├── src/main/java/com/qfnu/forum/
│   ├── ForumApplication.java          # 应用入口
│   ├── entity/                        # 实体类
│   │   ├── Category.java
│   │   ├── Post.java
│   │   ├── Comment.java
│   │   └── Course.java
│   ├── repository/                    # 数据访问层
│   │   ├── CategoryRepository.java
│   │   ├── PostRepository.java
│   │   ├── CommentRepository.java
│   │   └── CourseRepository.java
│   ├── service/                       # 业务逻辑层
│   │   ├── CategoryService.java
│   │   ├── PostService.java
│   │   ├── CommentService.java
│   │   └── CourseService.java
│   ├── controller/                    # 控制器层
│   │   ├── CategoryController.java
│   │   ├── PostController.java
│   │   ├── CommentController.java
│   │   └── CourseController.java
│   ├── dto/                          # 数据传输对象
│   │   ├── PostDTO.java
│   │   └── CommentDTO.java
│   └── config/                       # 配置类
│       ├── CorsConfig.java           # CORS配置
│       └── DataInitializer.java      # 数据初始化
└── src/main/resources/
    └── application.properties         # 应用配置
```

### 前端 (Vue 3)

```
frontend/
├── src/
│   ├── App.vue                       # 根组件
│   ├── main.js                       # 应用入口
│   ├── views/                        # 页面组件
│   │   ├── HomeView.vue             # 首页
│   │   ├── ForumView.vue            # 论坛列表
│   │   ├── CategoryView.vue         # 分类详情
│   │   ├── PostDetailView.vue       # 帖子详情
│   │   └── CoursesView.vue          # 课程列表
│   ├── stores/                       # Pinia 状态管理
│   │   └── forum.js                 # 论坛状态
│   ├── services/                     # API 服务
│   │   └── api.js                   # API 调用封装
│   └── router/                       # 路由配置
│       └── index.js                 # 路由定义
└── index.html                        # HTML 模板
```

## 常见问题

### 1. 数据库连接失败

确保 MySQL 正在运行，并且用户名密码正确：
```bash
# 检查 MySQL 状态
sudo systemctl status mysql  # Linux
brew services list           # macOS

# 测试连接
mysql -u root -p1026
```

### 2. 端口已被占用

修改端口：
- 后端：编辑 `backend/src/main/resources/application.properties` 中的 `server.port`
- 前端：编辑 `frontend/vite.config.ts` 添加 server 配置

### 3. CORS 错误

确保后端的 CORS 配置包含前端地址，已在 `CorsConfig.java` 中配置。

## 扩展功能建议

1. **用户认证系统**
   - Spring Security
   - JWT Token
   - 用户注册/登录

2. **文件上传**
   - 头像上传
   - 帖子图片
   - 课程视频

3. **搜索功能**
   - Elasticsearch
   - 全文搜索

4. **实时通知**
   - WebSocket
   - 消息推送

5. **管理后台**
   - 内容审核
   - 用户管理
   - 数据统计

## 部署

### Docker 部署 (推荐)

创建 `docker-compose.yml`:

```yaml
version: '3.8'
services:
  mysql:
    image: mysql:8
    environment:
      MYSQL_ROOT_PASSWORD: 1026
      MYSQL_DATABASE: qfnu_forum
    ports:
      - "3306:3306"
  
  backend:
    build: ./backend
    ports:
      - "8080:8080"
    depends_on:
      - mysql
  
  frontend:
    build: ./frontend
    ports:
      - "80:80"
    depends_on:
      - backend
```

### 传统部署

1. **后端**
   ```bash
   cd backend
   mvn clean package
   java -jar target/forum-1.0.0.jar
   ```

2. **前端**
   ```bash
   cd frontend
   npm run build
   # 将 dist 目录部署到 Nginx 或其他 Web 服务器
   ```

## 贡献指南

1. Fork 项目
2. 创建功能分支 (`git checkout -b feature/AmazingFeature`)
3. 提交更改 (`git commit -m 'Add some AmazingFeature'`)
4. 推送到分支 (`git push origin feature/AmazingFeature`)
5. 创建 Pull Request

## 许可证

MIT License
