package alt.sow.domain;

// Generated Oct 31, 2013 2:24:02 PM by Hibernate Tools 3.6.0

import java.util.HashSet;
import java.util.Set;

/**
 * Tag generated by hbm2java
 */
public class Tag implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7795806849330563391L;
	private int idTag;
	private String tagName;
	private Set<Question> questions = new HashSet<Question>(0);

	public Tag() {
	}

	public Tag(int idTag) {
		this.idTag = idTag;
	}

	public Tag(int idTag, String tagName, Set<Question> questions) {
		this.idTag = idTag;
		this.tagName = tagName;
		this.questions = questions;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tag other = (Tag) obj;
		if (idTag != other.idTag)
			return false;
		if (tagName == null) {
			if (other.tagName != null)
				return false;
		} else if (!tagName.equals(other.tagName))
			return false;
		return true;
	}

	public int getIdTag() {
		return this.idTag;
	}

	public Set<Question> getQuestions() {
		return this.questions;
	}

	public String getTagName() {
		return this.tagName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idTag;
		result = prime * result + ((tagName == null) ? 0 : tagName.hashCode());
		return result;
	}

	public void setIdTag(int idTag) {
		this.idTag = idTag;
	}

	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

}
