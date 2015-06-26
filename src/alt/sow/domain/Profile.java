package alt.sow.domain;

// Generated Oct 31, 2013 2:24:02 PM by Hibernate Tools 3.6.0

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Profile generated by hbm2java
 */
public class Profile implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 903631038105362579L;
	private int idProfile;
	private Name nameByNameIdName;
	private Organisation organisation;
	private Name nameByNameIdFather;
	private Contact contact;
	private Date birthDate;
	private byte[] profilePicture;
	private Set<User> users = new HashSet<User>(0);
	private Set<Student> students = new HashSet<Student>(0);
	private Set<Exam> exams = new HashSet<Exam>(0);

	public Profile() {
	}

	public Profile(int idProfile) {
		this.idProfile = idProfile;
	}

	public Profile(int idProfile, Name nameByNameIdName,
			Organisation organisation, Name nameByNameIdFather,
			Contact contact, Date birthDate, byte[] profilePicture,
			Set<User> users, Set<Student> students, Set<Exam> exams) {
		this.idProfile = idProfile;
		this.nameByNameIdName = nameByNameIdName;
		this.organisation = organisation;
		this.nameByNameIdFather = nameByNameIdFather;
		this.contact = contact;
		this.birthDate = birthDate;
		this.profilePicture = profilePicture;
		this.users = users;
		this.students = students;
		this.exams = exams;
	}

	public Date getBirthDate() {
		return this.birthDate;
	}

	public Contact getContact() {
		return this.contact;
	}

	public Set<Exam> getExams() {
		return this.exams;
	}

	public int getIdProfile() {
		return this.idProfile;
	}

	public Name getNameByNameIdFather() {
		return this.nameByNameIdFather;
	}

	public Name getNameByNameIdName() {
		return this.nameByNameIdName;
	}

	public Organisation getOrganisation() {
		return this.organisation;
	}

	public byte[] getProfilePicture() {
		return this.profilePicture;
	}

	public Set<Student> getStudents() {
		return this.students;
	}

	public Set<User> getUsers() {
		return this.users;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public void setExams(Set<Exam> exams) {
		this.exams = exams;
	}

	public void setIdProfile(int idProfile) {
		this.idProfile = idProfile;
	}

	public void setNameByNameIdFather(Name nameByNameIdFather) {
		this.nameByNameIdFather = nameByNameIdFather;
	}

	public void setNameByNameIdName(Name nameByNameIdName) {
		this.nameByNameIdName = nameByNameIdName;
	}

	public void setOrganisation(Organisation organisation) {
		this.organisation = organisation;
	}

	public void setProfilePicture(byte[] profilePicture) {
		this.profilePicture = profilePicture;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

}
