package com.qfnu.forum.config;

import com.qfnu.forum.entity.Category;
import com.qfnu.forum.entity.Course;
import com.qfnu.forum.repository.CategoryRepository;
import com.qfnu.forum.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DataInitializer implements CommandLineRunner {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public void run(String... args) {
        if (categoryRepository.count() == 0) {
            Category category1 = new Category();
            category1.setName("大学生校园日常");
            category1.setDescription("校园周边美食、生活、校园文化");
            category1.setType("forum");
            categoryRepository.save(category1);

            Category category2 = new Category();
            category2.setName("汉语学习指导");
            category2.setDescription("给外国学生学习汉语的指导，相关渠道、困难点分析");
            category2.setType("forum");
            categoryRepository.save(category2);

            Category category3 = new Category();
            category3.setName("旅游打卡推荐");
            category3.setDescription("旅游景点推荐与打卡分享");
            category3.setType("forum");
            categoryRepository.save(category3);

            Category category4 = new Category();
            category4.setName("留学经验");
            category4.setDescription("留学经验分享与交流");
            category4.setType("forum");
            categoryRepository.save(category4);

            Category category5 = new Category();
            category5.setName("中华文化宣传");
            category5.setDescription("中华传统文化宣传与推广");
            category5.setType("forum");
            categoryRepository.save(category5);
        }

        if (courseRepository.count() == 0) {
            Course course1 = new Course();
            course1.setTitle("汉语入门课程");
            course1.setDescription("适合零基础学员的汉语入门课程，包括拼音、基础词汇和简单对话");
            course1.setInstructor("李老师");
            course1.setInstructorIntro("10年汉语教学经验，精通英语和汉语教学");
            course1.setPrice(new BigDecimal("199.00"));
            course1.setIsTrial(false);
            course1.setType("teaching");
            course1.setVideoUrl("https://example.com/video1");
            courseRepository.save(course1);

            Course course2 = new Course();
            course2.setTitle("汉语免费试听课");
            course2.setDescription("免费试听课程，体验汉语学习的乐趣");
            course2.setInstructor("王老师");
            course2.setInstructorIntro("5年汉语教学经验，擅长初级汉语教学");
            course2.setPrice(new BigDecimal("0.00"));
            course2.setIsTrial(true);
            course2.setType("teaching");
            course2.setVideoUrl("https://example.com/video2");
            courseRepository.save(course2);

            Course course3 = new Course();
            course3.setTitle("汉教实习指导课程");
            course3.setDescription("针对中国汉教专业学生的实习指导，包括讲课技巧和难点应对");
            course3.setInstructor("张老师");
            course3.setInstructorIntro("15年汉语教学经验，培养了数百名优秀汉教老师");
            course3.setPrice(new BigDecimal("299.00"));
            course3.setIsTrial(false);
            course3.setType("internship");
            course3.setVideoUrl("https://example.com/video3");
            courseRepository.save(course3);
        }
    }
}
