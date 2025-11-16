# API 测试文档 (API Testing Guide)

本文档提供了测试后端 API 的示例命令和响应。

## 前提条件

确保后端服务正在运行: `http://localhost:8080`

## 1. 分类接口测试 (Categories API)

### 获取所有分类
```bash
curl http://localhost:8080/api/categories
```

**响应示例:**
```json
[
  {
    "id": 1,
    "name": "大学生校园日常",
    "description": "校园周边美食、生活、校园文化",
    "type": "forum",
    "createdAt": "2024-01-01T10:00:00",
    "updatedAt": "2024-01-01T10:00:00"
  }
]
```

### 按类型获取分类
```bash
# 获取论坛分类
curl http://localhost:8080/api/categories/type/forum

# 获取商业分类
curl http://localhost:8080/api/categories/type/commercial
```

### 获取单个分类
```bash
curl http://localhost:8080/api/categories/1
```

### 创建新分类
```bash
curl -X POST http://localhost:8080/api/categories \
  -H "Content-Type: application/json" \
  -d '{
    "name": "新分类",
    "description": "这是一个新的分类",
    "type": "forum"
  }'
```

## 2. 帖子接口测试 (Posts API)

### 获取所有帖子
```bash
curl http://localhost:8080/api/posts
```

### 按分类获取帖子
```bash
curl http://localhost:8080/api/posts/category/1
```

### 获取单个帖子
```bash
curl http://localhost:8080/api/posts/1
```

### 创建新帖子
```bash
curl -X POST http://localhost:8080/api/posts \
  -H "Content-Type: application/json" \
  -d '{
    "title": "校园美食推荐",
    "content": "今天发现学校附近有一家很好吃的面馆，强烈推荐！位置在...",
    "author": "张三",
    "categoryId": 1
  }'
```

**响应示例:**
```json
{
  "id": 1,
  "title": "校园美食推荐",
  "content": "今天发现学校附近有一家很好吃的面馆，强烈推荐！位置在...",
  "author": "张三",
  "categoryId": 1,
  "viewCount": 0,
  "likeCount": 0,
  "createdAt": "2024-01-01T12:00:00",
  "updatedAt": "2024-01-01T12:00:00"
}
```

### 更新帖子
```bash
curl -X PUT http://localhost:8080/api/posts/1 \
  -H "Content-Type: application/json" \
  -d '{
    "title": "校园美食推荐（更新）",
    "content": "更新后的内容...",
    "author": "张三",
    "categoryId": 1
  }'
```

### 点赞帖子
```bash
curl -X POST http://localhost:8080/api/posts/1/like
```

### 删除帖子
```bash
curl -X DELETE http://localhost:8080/api/posts/1
```

## 3. 评论接口测试 (Comments API)

### 获取帖子的评论
```bash
curl http://localhost:8080/api/comments/post/1
```

### 创建评论
```bash
curl -X POST http://localhost:8080/api/comments \
  -H "Content-Type: application/json" \
  -d '{
    "postId": 1,
    "content": "说得很对，我也去过那家面馆，确实不错！",
    "author": "李四"
  }'
```

**响应示例:**
```json
{
  "id": 1,
  "postId": 1,
  "content": "说得很对，我也去过那家面馆，确实不错！",
  "author": "李四",
  "createdAt": "2024-01-01T12:30:00"
}
```

### 删除评论
```bash
curl -X DELETE http://localhost:8080/api/comments/1
```

## 4. 课程接口测试 (Courses API)

### 获取所有课程
```bash
curl http://localhost:8080/api/courses
```

### 按类型获取课程
```bash
# 获取汉语教学课程
curl http://localhost:8080/api/courses/type/teaching

# 获取汉教实习课程
curl http://localhost:8080/api/courses/type/internship
```

### 获取试听课程
```bash
curl http://localhost:8080/api/courses/trial
```

### 获取单个课程
```bash
curl http://localhost:8080/api/courses/1
```

### 创建新课程
```bash
curl -X POST http://localhost:8080/api/courses \
  -H "Content-Type: application/json" \
  -d '{
    "title": "汉语中级课程",
    "description": "适合有一定汉语基础的学员，深入学习中级语法和词汇",
    "instructor": "王老师",
    "instructorIntro": "8年汉语教学经验，擅长中级汉语教学",
    "price": 299.00,
    "isTrial": false,
    "type": "teaching",
    "videoUrl": "https://example.com/video/intermediate"
  }'
```

**响应示例:**
```json
{
  "id": 4,
  "title": "汉语中级课程",
  "description": "适合有一定汉语基础的学员，深入学习中级语法和词汇",
  "instructor": "王老师",
  "instructorIntro": "8年汉语教学经验，擅长中级汉语教学",
  "price": 299.00,
  "isTrial": false,
  "type": "teaching",
  "videoUrl": "https://example.com/video/intermediate",
  "createdAt": "2024-01-01T14:00:00",
  "updatedAt": "2024-01-01T14:00:00"
}
```

### 更新课程
```bash
curl -X PUT http://localhost:8080/api/courses/4 \
  -H "Content-Type: application/json" \
  -d '{
    "title": "汉语中级课程（升级版）",
    "description": "更新后的描述...",
    "instructor": "王老师",
    "instructorIntro": "8年汉语教学经验，擅长中级汉语教学",
    "price": 259.00,
    "isTrial": false,
    "type": "teaching",
    "videoUrl": "https://example.com/video/intermediate-v2"
  }'
```

### 删除课程
```bash
curl -X DELETE http://localhost:8080/api/courses/4
```

## 5. 使用 Postman 测试

### 导入 Postman Collection

创建一个新的 Collection，添加以下请求：

**环境变量:**
```json
{
  "base_url": "http://localhost:8080/api"
}
```

**常用请求示例:**

1. **GET** `{{base_url}}/categories`
2. **GET** `{{base_url}}/posts`
3. **POST** `{{base_url}}/posts`
   - Body (JSON):
   ```json
   {
     "title": "测试标题",
     "content": "测试内容",
     "author": "测试用户",
     "categoryId": 1
   }
   ```

## 6. 常见测试场景

### 场景 1: 发布完整的帖子流程
```bash
# 1. 创建帖子
POST_ID=$(curl -s -X POST http://localhost:8080/api/posts \
  -H "Content-Type: application/json" \
  -d '{"title":"测试帖子","content":"测试内容","author":"张三","categoryId":1}' \
  | grep -o '"id":[0-9]*' | grep -o '[0-9]*')

# 2. 查看帖子
curl http://localhost:8080/api/posts/$POST_ID

# 3. 添加评论
curl -X POST http://localhost:8080/api/comments \
  -H "Content-Type: application/json" \
  -d "{\"postId\":$POST_ID,\"content\":\"很好的帖子\",\"author\":\"李四\"}"

# 4. 点赞
curl -X POST http://localhost:8080/api/posts/$POST_ID/like

# 5. 查看最终结果
curl http://localhost:8080/api/posts/$POST_ID
```

### 场景 2: 课程浏览流程
```bash
# 1. 获取所有课程
curl http://localhost:8080/api/courses

# 2. 筛选试听课程
curl http://localhost:8080/api/courses/trial

# 3. 获取汉语教学课程
curl http://localhost:8080/api/courses/type/teaching

# 4. 查看特定课程详情
curl http://localhost:8080/api/courses/1
```

## 7. 错误响应示例

### 404 - 资源不存在
```bash
curl http://localhost:8080/api/posts/99999
# 返回 404 Not Found
```

### 400 - 请求参数错误
```bash
curl -X POST http://localhost:8080/api/posts \
  -H "Content-Type: application/json" \
  -d '{"title":"","content":"","author":"","categoryId":null}'
# 可能返回 400 Bad Request
```

## 8. 性能测试

### 使用 Apache Bench (ab)
```bash
# 测试获取帖子列表的性能
ab -n 1000 -c 10 http://localhost:8080/api/posts
```

### 使用 wrk
```bash
# 测试 30 秒，10 个并发连接
wrk -t10 -c10 -d30s http://localhost:8080/api/posts
```

## 9. 数据库查询验证

测试后可以直接查询数据库验证：

```bash
mysql -u root -p1026

USE qfnu_forum;

# 查看所有表
SHOW TABLES;

# 查看帖子数据
SELECT * FROM posts;

# 查看评论数据
SELECT * FROM comments;

# 查看课程数据
SELECT * FROM courses;
```

## 10. 自动化测试脚本

创建一个简单的测试脚本 `test_api.sh`:

```bash
#!/bin/bash

BASE_URL="http://localhost:8080/api"

echo "Testing Categories API..."
curl -s $BASE_URL/categories | grep -q "name" && echo "✓ Categories OK" || echo "✗ Categories Failed"

echo "Testing Posts API..."
curl -s $BASE_URL/posts | grep -q "title" && echo "✓ Posts OK" || echo "✗ Posts Failed"

echo "Testing Courses API..."
curl -s $BASE_URL/courses | grep -q "instructor" && echo "✓ Courses OK" || echo "✗ Courses Failed"

echo "All tests completed!"
```

运行测试:
```bash
chmod +x test_api.sh
./test_api.sh
```

## 注意事项

1. 确保后端服务已启动并监听在 8080 端口
2. MySQL 数据库服务必须运行
3. 所有 POST/PUT 请求必须包含 `Content-Type: application/json` 头
4. 删除操作是永久性的，测试时请注意
5. 点赞操作会累加，可以多次点赞

## 参考资源

- [Spring Boot REST API 文档](https://spring.io/guides/gs/rest-service/)
- [curl 命令详解](https://curl.se/docs/manual.html)
- [Postman 使用指南](https://learning.postman.com/)
