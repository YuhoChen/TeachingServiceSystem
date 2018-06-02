package com.yuhao.TeachingServiceSystem.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import javax.persistence.*;


/**
 * 考试安排表
 * @author yuhaochen
 * 2018-05-30 17:23:29
 */
@Entity  
@Table(name="`exam`")
public class Exam extends BaseModel{

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
    @ManyToOne
    @JoinColumn(name = "`course_number`")
    private Course courseNumber;



	/**
	* 考试时间
	*/
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name="exam_time")
	private Date examTime;



	/**
	* 考试地点
	*/
	@Column(name="exam_place")
	private String examPlace;




	/**
	* 考点容量
	*/
	@Column(name="exam_capacity")
	private Long examCapacity;




	/**
	* 监考人员
	*/
	@Column(name="invigilator")
	private String invigilator;




	/**
	* 备注
	*/
	@Column(name="remark")
	private String remark;




	public void setId(Long id){
        this.id=id;
	}
	public Long getId(){
        return this.id;
    }





    public void setCourseNumber(Course courseNumber){
        this.courseNumber=courseNumber;
    }
    public Course getCourseNumber(){
        return this.courseNumber;
    }




	public void setExamTime(Date examTime){
        this.examTime=examTime;
	}
	public Date getExamTime(){
        return this.examTime;
    }




	public void setExamPlace(String examPlace){
        this.examPlace=examPlace;
	}
	public String getExamPlace(){
        return this.examPlace;
    }




	public void setExamCapacity(Long examCapacity){
        this.examCapacity=examCapacity;
	}
	public Long getExamCapacity(){
        return this.examCapacity;
    }




	public void setInvigilator(String invigilator){
        this.invigilator=invigilator;
	}
	public String getInvigilator(){
        return this.invigilator;
    }




	public void setRemark(String remark){
        this.remark=remark;
	}
	public String getRemark(){
        return this.remark;
    }



}  
