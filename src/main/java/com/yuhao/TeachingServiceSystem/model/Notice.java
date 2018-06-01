package com.yuhao.TeachingServiceSystem.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import javax.persistence.*;


/**
 * 通知表
 * @author yuhaochen
 * 2018-05-30 17:23:29
 */
@Entity  
@Table(name="notice")
public class Notice extends BaseModel{

    private static final long serialVersionUID = 1L;




	/**
	* 
	*/
	@Id
    @GeneratedValue
	@Column(name="id")
	private Long id;




	/**
	* 标题
	*/
	@Column(name="title")
	private String title;




	/**
	* 通知内容
	*/
	@Column(name="content")
	private String content;




	/**
	* 通知时间
	*/
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name="notice_time")
	private Date noticeTime;




	/**
	* 通知发起者
	*/
	@Column(name="initiator")
	private String initiator;




	public void setId(Long id){
        this.id=id;
	}
	public Long getId(){
        return this.id;
    }




	public void setTitle(String title){
        this.title=title;
	}
	public String getTitle(){
        return this.title;
    }




	public void setContent(String content){
        this.content=content;
	}
	public String getContent(){
        return this.content;
    }




	public void setNoticeTime(Date noticeTime){
        this.noticeTime=noticeTime;
	}
	public Date getNoticeTime(){
        return this.noticeTime;
    }




	public void setInitiator(String initiator){
        this.initiator=initiator;
	}
	public String getInitiator(){
        return this.initiator;
    }



}  
