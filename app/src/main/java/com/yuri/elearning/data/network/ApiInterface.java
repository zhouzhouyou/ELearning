package com.yuri.elearning.data.network;

import com.yuri.elearning.model.Category;
import com.yuri.elearning.model.CourseBriefInfo;
import com.yuri.elearning.model.CourseDetailInfo;
import com.yuri.elearning.model.Lesson;
import com.yuri.elearning.model.LessonBriefInfo;
import com.yuri.elearning.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {
    /**
     * 登录
     *
     * @param name     用户名
     * @param password 密码
     * @return 用户的ID
     */
    @POST("user/signIn")
    Call<User> signIn(
            @Query("name") String name,
            @Query("password") String password
    );

    /**
     * 用户充值
     *
     * @param cost 充值金额
     * @param id   用户名
     * @return 成功消息
     */
    @POST("user/recharge")
    Call<Double> recharge(
            @Query("cost") Double cost,
            @Query("id") Integer id
    );

    /**
     * 注册用户
     *
     * @param name     用户名
     * @param password 用户密码
     * @return 注册的ID
     */
    @POST("user/signUp")
    Call<Integer> signUp(
            @Query("name") String name,
            @Query("password") String password
    );

    /**
     * 通过课程id获取课程的全部信息和对应的所有lesson的简要内容
     *
     * @param cid course的ID
     * @return 课程的全部信息和所有lesson的简要内容
     */
    @POST("lesson/allLessonByCourse")
    Call<CourseDetailInfo> courseDetail(
            @Query("cid") Integer cid
    );

    /**
     * 通过lesson的ID获取lesson的详细信息
     *
     * @param lid lesson的ID
     * @return lesson的全部信息
     */
    @POST("lesson/lesson")
    Call<Lesson> lessonDetail(
            @Query("lid") Integer lid
    );

    /**
     * 获取某个月份的用户的所有lesson
     *
     * @param uid   用户id
     * @param year  年份
     * @param month 月份
     * @return 这个月份的用户的所有lesson
     */
    @POST("lesson/myAllLessonWithDate")
    Call<List<LessonBriefInfo>> queryCalendar(
            @Query("uid") Integer uid,
            @Query("year") Integer year,
            @Query("month") Integer month
    );

    /**
     * 查询所有的类别
     *
     * @return 所有的类别
     */
    @POST("course/allCategory")
    Call<List<Category>> allCategory();

    /**
     * 获取所有课程的内容
     *
     * @return 所有的课程信息
     */
    @POST("course/allCourse")
    Call<List<CourseBriefInfo>> queryAllCourses();

    @POST("course/allCourseByCategory")
    Call<List<CourseBriefInfo>> queryAllCourseByCategory(
            @Query("cid") Integer cid
    );

    /**
     * 获取用户有哪些课
     *
     * @param uid 用户id
     * @return 用户的
     */
    @POST("course/myAllCourse")
    Call<List<CourseBriefInfo>> queryMyCourses(
            @Query("uid") Integer uid
    );

    /**
     * 查询我买过的课的cid
     *
     * @param uid 用户id
     * @return 课的id
     */
    @POST("purchase/bought")
    Call<List<Integer>> bought(
            @Query("id") Integer uid
    );

    /**
     * 购买课程
     *
     * @param cid 课程id
     * @param uid 用户id
     * @return 购买是否成功
     */
    @POST("purchase/purchase")
    Call<Double> purchase(
            @Query("cid") Integer cid,
            @Query("uid") Integer uid
    );
}
