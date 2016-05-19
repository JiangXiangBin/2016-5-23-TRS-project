package entity;

/**
 * Studentsecurity entity. @author MyEclipse Persistence Tools
 */

public class Studentsecurity implements java.io.Serializable {

	// Fields
	// 主键自增长
	private Integer sid;
	private Student student;// 多对一，通过该变量可以引用到关联的student
	// 证券号码
	private Integer numble;

	// Constructors

	/** default constructor */
	public Studentsecurity() {
	}

	/** minimal constructor */
	public Studentsecurity(Integer sid) {
		this.sid = sid;
	}

	/** full constructor */
	public Studentsecurity(Integer sid, Student student, Integer numble) {
		this.sid = sid;
		this.student = student;
		this.numble = numble;
	}

	// Property accessors

	public Integer getSid() {
		return this.sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Integer getNumble() {
		return this.numble;
	}

	public void setNumble(Integer numble) {
		this.numble = numble;
	}

}