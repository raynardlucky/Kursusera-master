package com.exercise.raynard.kursusera;

import android.graphics.drawable.Drawable;

public class CourseData {
    public static final int COURSE_HEADER = 0;
    public static final int COURSE_DETAIL = 1;

    public int type;
    public String courseImage;
    public String courseTitle;
    public String courseDesc;

    public CourseData(int type, String courseImage, String courseTitle, String courseDesc) {
        this.type = type;
        this.courseImage = courseImage;
        this.courseTitle = courseTitle;
        this.courseDesc = courseDesc;
    }

    public int getType() {
        return type;
    }

    public String getCourseImage() {
        return courseImage;
    }

    public void setImage(String courseImage) {
        this.courseImage = courseImage;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getCourseDesc() {
        return courseDesc;
    }

    public void setCourseDesc(String courseDesc) {
        this.courseDesc = courseDesc;
    }
}
