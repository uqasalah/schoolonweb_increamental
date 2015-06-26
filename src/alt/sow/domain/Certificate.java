package alt.sow.domain;

// Generated Oct 31, 2013 2:24:02 PM by Hibernate Tools 3.6.0

/**
 * Certificate generated by hbm2java
 */
public class Certificate implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 267175767574090613L;
	private int idCertificate;
	private Result result;
	private User userByAddedBy;
	private User userByUserIdTeacher;
	private Boolean issued;
	private String certificateCode;

	public Certificate() {
	}

	public Certificate(int idCertificate) {
		this.idCertificate = idCertificate;
	}

	public Certificate(int idCertificate, Result result, User userByAddedBy,
			User userByUserIdTeacher, Boolean issued, String certificateCode) {
		this.idCertificate = idCertificate;
		this.result = result;
		this.userByAddedBy = userByAddedBy;
		this.userByUserIdTeacher = userByUserIdTeacher;
		this.issued = issued;
		this.certificateCode = certificateCode;
	}

	public String getCertificateCode() {
		return this.certificateCode;
	}

	public int getIdCertificate() {
		return this.idCertificate;
	}

	public Boolean getIssued() {
		return this.issued;
	}

	public Result getResult() {
		return this.result;
	}

	public User getUserByAddedBy() {
		return this.userByAddedBy;
	}

	public User getUserByUserIdTeacher() {
		return this.userByUserIdTeacher;
	}

	public void setCertificateCode(String certificateCode) {
		this.certificateCode = certificateCode;
	}

	public void setIdCertificate(int idCertificate) {
		this.idCertificate = idCertificate;
	}

	public void setIssued(Boolean issued) {
		this.issued = issued;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public void setUserByAddedBy(User userByAddedBy) {
		this.userByAddedBy = userByAddedBy;
	}

	public void setUserByUserIdTeacher(User userByUserIdTeacher) {
		this.userByUserIdTeacher = userByUserIdTeacher;
	}

}