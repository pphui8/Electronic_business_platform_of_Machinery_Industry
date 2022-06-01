package com.example.mallwork.Entity;

import java.util.Date;

public class User {
	private Integer id;
	private String account;
	private String password;
	private String email;
	private String phone;
	private String question;
	private String asw;
	private Integer role;
	private Date create_time;
	private Date updata_time;
	private Integer age;
	private Integer sex;
	private String name;
	private Integer del;

	public User() {
		super();
	}

	public User(Integer id, String account, String password, String email, String phone, String question, String asw,
                Integer role, Date create_time, Date updata_time, Integer age, Integer sex, String name, Integer del) {
		super();
		this.id = id;
		this.account = account;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.question = question;
		this.asw = asw;
		this.role = role;
		this.create_time = create_time;
		this.updata_time = updata_time;
		this.age = age;
		this.sex = sex;
		this.name = name;
		this.del = del;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAsw() {
		return asw;
	}
	public void setAsw(String asw) {
		this.asw = asw;
	}
	public Integer getRole() {
		return role;
	}
	public void setRole(Integer role) {
		this.role = role;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Date getUpData_time() {
		return updata_time;
	}
	public void setUpData_time(Date updata_time) {
		this.updata_time = updata_time;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getDel() {
		return del;
	}
	public void setDel(Integer del) {
		this.del = del;
	}
	
}
