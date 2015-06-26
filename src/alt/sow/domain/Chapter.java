package alt.sow.domain;

// Generated Oct 31, 2013 2:24:02 PM by Hibernate Tools 3.6.0

import java.util.HashSet;
import java.util.Set;

/**
 * Chapter generated by hbm2java
 */
public class Chapter implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6861114384460421949L;
	private int idChapter;
	private User user;
	private Course course;
	private String chapterName;
	private Set<Question> questions = new HashSet<Question>(0);

	public Chapter() {
	}

	public Chapter(int idChapter, Course course) {
		this.idChapter = idChapter;
		this.course = course;
	}

	public Chapter(int idChapter, User user, Course course, String chapterName,
			Set<Question> questions) {
		this.idChapter = idChapter;
		this.user = user;
		this.course = course;
		this.chapterName = chapterName;
		this.questions = questions;
	}

	public String getChapterName() {
		return this.chapterName;
	}

	public Course getCourse() {
		return this.course;
	}

	public int getIdChapter() {
		return this.idChapter;
	}

	public Set<Question> getQuestions() {
		return this.questions;
	}

	public User getUser() {
		return this.user;
	}

	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public void setIdChapter(int idChapter) {
		this.idChapter = idChapter;
	}

	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
