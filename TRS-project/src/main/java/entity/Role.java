package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Role entity. @author MyEclipse Persistence Tools
 */

@Entity
@SuppressWarnings("serial")
public class Role implements java.io.Serializable {

	// Fields

	@Id
	@GeneratedValue
	private Integer id;
	private String username;
	private String gender;
	private Integer grade;
	private Integer blood;

	// Constructors

	/** default constructor */
	public Role() {
	}

	public Role(String username, String gender, Integer grade, Integer blood) {

		this.username = username;
		this.gender = gender;
		this.grade = grade;
		this.blood = blood;
	}

	/** minimal constructor */
	public Role(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Role(Integer id, String username, String gender, Integer grade,
			Integer blood) {
		this.id = id;
		this.username = username;
		this.gender = gender;
		this.grade = grade;
		this.blood = blood;
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

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getGrade() {
		return this.grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public Integer getBlood() {
		return this.blood;
	}

	public void setBlood(Integer blood) {
		this.blood = blood;
	}

}