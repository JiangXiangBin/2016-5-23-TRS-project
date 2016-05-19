package entity;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.springframework.stereotype.Component;

/**
 * Student entity. @author MyEclipse Persistence Tools
 */

public class Student implements java.io.Serializable {

	// Fields
	private Integer id;
	@NotEmpty(message = "make sure name is not Empty")
	// make sure name is not empty
	@Size(min = 5, max = 10, message = "Your name should be between 5 - 10 characters.")
	private String username;
	@NotNull(message = "Please select a password")
	@Length(min = 5, max = 10, message = "Password should be between 5 - 10 charactes")
	private String pwd;
	private Long telphone;
	private String email;
	private Double money;
	private Set studentsecurities = new HashSet(0);

	// Constructors

	/** default constructor */
	public Student() {
	}

	/** minimal constructor */
	public Student(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Student(Integer id, String username, String pwd, Long telphone,
			String email, Double money, Set studentsecurities) {
		this.id = id;
		this.username = username;
		this.pwd = pwd;
		this.telphone = telphone;
		this.email = email;
		this.money = money;
		this.studentsecurities = studentsecurities;
	}

	/** full constructor */
	public Student(String username, String pwd, Long telphone, String email) {

		this.username = username;
		this.pwd = pwd;
		this.telphone = telphone;
		this.email = email;

	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPwd() {
		return this.pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public Long getTelphone() {
		return this.telphone;
	}

	public void setTelphone(Long telphone) {
		this.telphone = telphone;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Double getMoney() {
		return this.money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Set getStudentsecurities() {
		return this.studentsecurities;
	}

	public void setStudentsecurities(Set studentsecurities) {
		this.studentsecurities = studentsecurities;
	}

	@Override
	public String toString() {
		return "Student [username=" + username + ", pwd=" + pwd + "]";
	}

}