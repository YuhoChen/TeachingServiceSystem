package com.yuhao.TeachingServiceSystem.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;


/**
 * 课程表
 * @author yuhaochen
 * 2018-05-30 17:23:29
 */
@Entity  
@Table(name="course")
public class Course extends BaseModel{

    private static final long serialVersionUID = 1L;




	/**
	* 
	*/
	@Id
    @GeneratedValue
	@Column(name="id")
	private Long id;




	/**
	* 课程号
	*/
	@Column(name="course_number")
	private String courseNumber;




	/**
	* 课程名称
	*/
	@Column(name="course_name")
	private String courseName;




	/**
	* 任课老师
	*/
	@Column(name="teacher")
	private String teacher;




	/**
	* 课程信息
	*/
	@Column(name="course_info")
	private String courseInfo;




	/**
	* 学分
	*/
	@Column(name="course_credit")
	private BigDecimal courseCredit;




	public void setId(Long id){
        this.id=id;
	}
	public Long getId(){
        return this.id;
    }




	public void setCourseNumber(String courseNumber){
        this.courseNumber=courseNumber;
	}
	public String getCourseNumber(){
        return this.courseNumber;
    }




	public void setCourseName(String courseName){
        this.courseName=courseName;
	}
	public String getCourseName(){
        return this.courseName;
    }




	public void setTeacher(String teacher){
        this.teacher=teacher;
	}
	public String getTeacher(){
        return this.teacher;
    }




	public void setCourseInfo(String courseInfo){
        this.courseInfo=courseInfo;
	}
	public String getCourseInfo(){
        return this.courseInfo;
    }




	public void setCourseCredit(BigDecimal courseCredit){
        this.courseCredit=courseCredit;
	}
	public BigDecimal getCourseCredit(){
        return this.courseCredit;
    }



}  
