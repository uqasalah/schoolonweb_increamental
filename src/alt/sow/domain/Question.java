package alt.sow.domain;

// Generated Nov 11, 2013 7:09:27 PM by Hibernate Tools 3.6.0

import java.util.HashSet;
import java.util.Set;

/**
 * Question generated by hbm2java
 */
public class Question implements java.io.Serializable {

	private int idQuestion;
	private Chapter chapter;
	private User user;
	private Difficulty difficulty;
	private Tag tag;
	private Category category;
	private int dif_int;
	private int cat_int;
	private String question;
	private Set<Exam> exams = new HashSet<Exam>(0);
	private Set<Answer> answers = new HashSet<Answer>(0);

	public Question() {
	}

	public Question(int idQuestion) {
		this.idQuestion = idQuestion;
	}

	public Question(int idQuestion, Chapter chapter, User user,
			Difficulty difficulty, Tag tag, Category category, int dif_int,
			int cat_int, String question, Set<Exam> exams, Set<Answer> answers) {
		super();
		this.idQuestion = idQuestion;
		this.chapter = chapter;
		this.user = user;
		this.difficulty = difficulty;
		this.tag = tag;
		this.category = category;
		this.dif_int = dif_int;
		this.cat_int = cat_int;
		this.question = question;
		this.exams = exams;
		this.answers = answers;
	}

	public Question(int idQuestion, Chapter chapter, User user,
			Difficulty difficulty, Tag tag, String question, Set<Exam> exams,
			Set<Answer> answers) {
		this.idQuestion = idQuestion;
		this.chapter = chapter;
		this.user = user;
		this.difficulty = difficulty;
		this.tag = tag;
		this.question = question;
		this.exams = exams;
		this.answers = answers;
	}

	public Question(int idQuestion, Chapter chapter, User user,
			Difficulty difficulty, Tag tag, Category category, String question,
			Set<Exam> exams, Set<Answer> answers) {
		super();
		this.idQuestion = idQuestion;
		this.chapter = chapter;
		this.user = user;
		this.difficulty = difficulty;
		this.tag = tag;
		this.category = category;
		this.question = question;
		this.exams = exams;
		this.answers = answers;
	}

	public int getIdQuestion() {
		return this.idQuestion;
	}

	public void setIdQuestion(int idQuestion) {
		this.idQuestion = idQuestion;
	}

	public int getDif_int() {
		return dif_int;
	}

	public void setDif_int(int dif_int) {
		this.dif_int = dif_int;
	}

	public int getCat_int() {
		return cat_int;
	}

	public void setCat_int(int cat_int) {
		this.cat_int = cat_int;
	}

	public Chapter getChapter() {
		return this.chapter;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public void setChapter(Chapter chapter) {
		this.chapter = chapter;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Difficulty getDifficulty() {
		return this.difficulty;
	}

	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}

	public Tag getTag() {
		return this.tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	public String getQuestion() {
		return this.question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public Set<Exam> getExams() {
		return this.exams;
	}

	public void setExams(Set<Exam> exams) {
		this.exams = exams;
	}

	public Set<Answer> getAnswers() {
		return this.answers;
	}

	public void setAnswers(Set<Answer> answers) {
		this.answers = answers;
	}

	@Override
	public String toString() {
		return "Question [idQuestion=" + idQuestion + ", chapter=" + chapter
				+ ", user=" + user + ", difficulty=" + difficulty + ", tag="
				+ tag + ", category=" + category + ", dif_int=" + dif_int
				+ ", cat_int=" + cat_int + ", question=" + question
				+ ", exams=" + exams + ", answers=" + answers + "]";
	}

}