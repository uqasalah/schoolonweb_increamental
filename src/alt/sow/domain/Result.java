package alt.sow.domain;

// Generated Oct 31, 2013 2:24:02 PM by Hibernate Tools 3.6.0

import java.util.HashSet;
import java.util.Set;

/**
 * Result generated by hbm2java
 */
public class Result implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8457579544137316709L;
	private int idResult;
	private Student student;
	private User user;
	private Float obtainedMarks;
	private Boolean pass;
	private Set<Certificate> certificates = new HashSet<Certificate>(0);

	public Result() {
	}

	public Result(int idResult) {
		this.idResult = idResult;
	}

	public Result(int idResult, Student student, User user,
			Float obtainedMarks, Boolean pass, Set<Certificate> certificates) {
		this.idResult = idResult;
		this.student = student;
		this.user = user;
		this.obtainedMarks = obtainedMarks;
		this.pass = pass;
		this.certificates = certificates;
	}

	public Set<Certificate> getCertificates() {
		return this.certificates;
	}

	public int getIdResult() {
		return this.idResult;
	}

	public Float getObtainedMarks() {
		return this.obtainedMarks;
	}

	public Boolean getPass() {
		return this.pass;
	}

	public Student getStudent() {
		return this.student;
	}

	public User getUser() {
		return this.user;
	}

	public void setCertificates(Set<Certificate> certificates) {
		this.certificates = certificates;
	}

	public void setIdResult(int idResult) {
		this.idResult = idResult;
	}

	public void setObtainedMarks(Float obtainedMarks) {
		this.obtainedMarks = obtainedMarks;
	}

	public void setPass(Boolean pass) {
		this.pass = pass;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public void setUser(User user) {
		this.user = user;
	}

}