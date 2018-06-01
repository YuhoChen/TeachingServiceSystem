package com.yuhao.TeachingServiceSystem.model;

import java.util.Date;
import javax.persistence.*;


/**
 * 选课表
 * @author yuhaochen
 * 2018-05-30 17:23:29
 */
@Entity  
@Table(name="student_course")
public class StudentCourse extends BaseModel{

    private static final long serialVersionUID = 1L;



	/**
	* 
	*/
	@Id
    @GeneratedValue
	@Column(name="id")
	private Long id;



	/**
	* 学号
	*/
    @ManyToOne
    @JoinColumn(name = "student_number")
    private Student studentNumber;




	/**
	* 课程号
	*/
    @ManyToOne
    @JoinColumn(name = "course_number")
    private Course courseNumber;




	/**
	* 分数
	*/
	@Column(name="score")
	private Long score;




	public void setId(Long id){
        this.id=id;
	}
	public Long getId(){
        return this.id;
    }





    public void setStudentNumber(Student studentNumber){
        this.studentNumber=studentNumber;
    }
    public Student getStudentNumber(){
        return this.studentNumber;
    }





    public void setCourseNumber(Course courseNumber){
        this.courseNumber=courseNumber;
    }
    public Course getCourseNumber(){
        return this.courseNumber;
    }




	public void setScore(Long score){
        this.score=score;
	}
	public Long getScore(){
        return this.score;
    }



}  
