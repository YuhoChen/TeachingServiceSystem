package com.yuhao.TeachingServiceSystem.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import javax.persistence.*;


/**
 * 学生表
 * @author yuhaochen
 * 2018-05-30 17:23:29
 */
@Entity  
@Table(name="student")
public class Student extends BaseModel{

    private static final long serialVersionUID = 1L;




	/**
	* 
	*/
	@Id
    @GeneratedValue
	@Column(name="id")
	private Long id;




	/**
	* 学生号
	*/
	@Column(name="student_number")
	private String studentNumber;




	/**
	* 姓名
	*/
	@Column(name="name")
	private String name;




	/**
	* 性别
	*/
	@Column(name="sex")
	private String sex;




	/**
	* 身份证号
	*/
	@Column(name="id_card")
	private String idCard;




	/**
	* 出生日期
	*/
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="birth")
	private Date birth;




	/**
	* 籍贯
	*/
	@Column(name="native_place")
	private String nativePlace;




	/**
	* 邮箱
	*/
	@Column(name="email")
	private String email;




	/**
	* 手机号
	*/
	@Column(name="phone")
	private String phone;




	/**
	* 学院
	*/
	@Column(name="college")
	private String college;




	/**
	* 班级
	*/
	@Column(name="`class`")
	private String myClass;




	/**
	* 密码
	*/
	@Column(name="password")
	private String password;




	public void setId(Long id){
        this.id=id;
	}
	public Long getId(){
        return this.id;
    }




	public void setStudentNumber(String studentNumber){
        this.studentNumber=studentNumber;
	}
	public String getStudentNumber(){
        return this.studentNumber;
    }




	public void setName(String name){
        this.name=name;
	}
	public String getName(){
        return this.name;
    }




	public void setSex(String sex){
        this.sex=sex;
	}
	public String getSex(){
        return this.sex;
    }




	public void setIdCard(String idCard){
        this.idCard=idCard;
	}
	public String getIdCard(){
        return this.idCard;
    }




	public void setBirth(Date birth){
        this.birth=birth;
	}
	public Date getBirth(){
        return this.birth;
    }




	public void setNativePlace(String nativePlace){
        this.nativePlace=nativePlace;
	}
	public String getNativePlace(){
        return this.nativePlace;
    }




	public void setEmail(String email){
        this.email=email;
	}
	public String getEmail(){
        return this.email;
    }




	public void setPhone(String phone){
        this.phone=phone;
	}
	public String getPhone(){
        return this.phone;
    }




	public void setCollege(String college){
        this.college=college;
	}
	public String getCollege(){
        return this.college;
    }


	public String getMyClass() {
		return myClass;
	}

	public void setMyClass(String myClass) {
		this.myClass = myClass;
	}

	public void setPassword(String password){
        this.password=password;
	}
	public String getPassword(){
        return this.password;
    }



}  
