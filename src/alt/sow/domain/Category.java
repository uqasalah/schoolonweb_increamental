package alt.sow.domain;

import java.util.HashSet;
import java.util.Set;

public class Category {
	private static final long serialVersionUID = 16425264246L;
	private int idCategory;
	private String categoryName;
	private Set<Question> questions = new HashSet<Question>(0);

	public int getIdCategory() {
		return idCategory;
	}

	public void setIdCategory(int idCategory) {
		this.idCategory = idCategory;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Set<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}

	public Category() {
		// TODO Auto-generated constructor stub
	}

	public Category(int idCategory, String categoryName, Set<Question> questions) {
		super();
		this.idCategory = idCategory;
		this.categoryName = categoryName;
		this.questions = questions;
	}

}
