package com.aviralar.restApiDemo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity //to mark class as persistent java class
@Table(name="notes")
@EntityListeners(AuditingEntityListener.class)
/* 
 * @JsonIgnoreProperties is a jackson annotation. 
 * serializes and deserializes pojo to json 
 * ignore is used so that the client of rest api does not supply these values and even if 
 * they supply  we ignore them 
 * */ 
@JsonIgnoreProperties(value = {"createAt","updateAt"}, allowGetters = true)
public class Note implements Serializable {

	@Id //primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) //primary key generation strategy. auto increment
	private long id;
	
	@NotBlank
	private String title;
	
	@NotBlank
	private String content;
	
	@Column(nullable=false, updatable=false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdAt; // camel case are replaced with underscore created_at
	
	@Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP) // convert date and time values from object type to DB type
    @LastModifiedDate
    private Date updatedAt;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	
}
