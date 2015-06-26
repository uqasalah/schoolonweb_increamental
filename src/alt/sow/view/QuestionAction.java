package alt.sow.view;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import alt.sow.controller.GenericManager;
import alt.sow.domain.Answer;
import alt.sow.domain.Chapter;
import alt.sow.domain.Course;
import alt.sow.domain.Question;
import alt.sow.domain.Tag;
import alt.sow.domain.User;
import alt.sow.util.DataSource;

import com.opensymphony.xwork2.ActionSupport;
import com.sn.util.StringUtils;

public class QuestionAction extends ActionSupport implements SessionAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GenericManager<Question> manager = new GenericManager<Question>();
	//

	//
	private List<Course> courses = new ArrayList<Course>();
	private List<Question> questions = new ArrayList<Question>();
	private List<String> answers = new ArrayList<String>();
	private Integer correct;
	private Question question;
	//
	private int courseid;
	private int chapterid;
	//
	private Map<String, Object> session;

	public String add(Tag tag) {
		String result = "";

		Question question = getQuestion();

		System.out.println(question);
		if (question != null) {
			Chapter parentChapter = (Chapter) manager.runQuery(
					"from Chapter c where c.idChapter=" + getChapterid())
					.get(0);
			User cu = (User) session.get("currentUser");
			//
			question.setUser(cu);
			cu.getQuestions().add(question);
			//
			parentChapter.getQuestions().add(question);
			question.setChapter(parentChapter);
			//

			//
			tag.getQuestions().add(question);
			question.setTag(tag);
			// validation
			int i = 0;
			for (Iterator iterator = getAnswers().iterator(); iterator
					.hasNext();) {
				i++;
				String answerString = iterator.next().toString();
				System.out.println(answerString);
				if (StringUtils.isBlank(answerString)) {
					addFieldError("answers[" + (i - 1) + "]",
							getText("required.answer"));
					result = INPUT;
				}
			}
			if (StringUtils.isBlank(question.getQuestion())) {
				addFieldError("question.question", getText("required.question"));
				result = INPUT;
			}
			if (!result.equals(INPUT)) {
				correct = getCorrect();
				i = 0;
				for (Iterator iterator = getAnswers().iterator(); iterator
						.hasNext();) {
					String answerString = iterator.next().toString();
					//
					Answer answer = new Answer();
					answer.setAnwer(answerString);
					answer.setCorrect(i == (correct - 1));
					//
					answer.setUser(cu);
					cu.getAnswers().add(answer);
					//
					answer.setQuestion(question);
					question.getAnswers().add(answer);
					//
					i++;
					System.out.println(answer);
					// DataSource.getInstance().DAO.merge(answer);
					// DataSource.getInstance().DAO.merge(cu);
				}

				if (manager.add(question)) {
					addActionMessage(getText("success.add.question") + " "
							+ safesubstring(question.getQuestion(), 10)
							+ "....");
					result = SUCCESS;
				} else {
					addActionError(getText("not.added.question.duplicate"));
					result = ERROR;
				}
			}
		} else {
			result = INPUT;
		}
		list();
		System.out.println("result" + result);
		return result;
	}

	private String safesubstring(String question2, int i) {
		// TODO Auto-generated method stub
		if (question2 != null) {
			return question2.substring(0, (question2.length() > i ? i
					: question2.length()));
		}
		return null;
	}

	public String addLong() {
		return add(DataSource.getInstance().LONGANSWER);
	}

	public String addMCQ() {
		return add(DataSource.getInstance().MCQ);
	}

	public String addShort() {
		return add(DataSource.getInstance().SHORTANSWER);
	}

	public String delete() {
		String returnString = INPUT;
		Question c = (Question) DataSource.getInstance().DAO.runQuery(
				"from Question c where c.idQuestion="
						+ getQuestion().getIdQuestion()).get(0);
		getAll();
		if (c != null) {
			if (manager.remove(c)) {
				addActionMessage(getText("success.delete.question"));
				returnString = SUCCESS;
			} else {
				addActionError(getText("not.deleted.question"));
				returnString = ERROR;
			}
		} else {
			addActionError("invalid.id.question");
			returnString = ERROR;
		}
		list();
		return returnString;
	}

	public String edit() {
		String returnString = INPUT;
		Question c = (Question) DataSource.getInstance().DAO.findByID(
				Question.class, getQuestion().getIdQuestion());
		getAll();
		if (c != null) {
			if (manager.edit(c)) {
				addActionMessage(getText("success.edit.question"));
				returnString = SUCCESS;
			} else {
				addActionError(getText("not.edited.question"));
				returnString = ERROR;
			}
		} else {
			addActionError("invalid.id.question");
			returnString = ERROR;
		}
		list();
		return returnString;
	}

	@Override
	public String execute() throws Exception {
		return list();
	}

	public List<Question> getAll() {
		questions = DataSource.getInstance().DAO.findAll(Question.class);
		courses = DataSource.getInstance().DAO.findAll(Course.class);
		for (Iterator itc = courses.iterator(); itc.hasNext();) {
			Course crs = (Course) itc.next();
			System.out.println("crs:" + crs);
			for (Iterator ith = crs.getChapters().iterator(); ith.hasNext();) {
				Chapter chp = (Chapter) ith.next();
				System.out.println("chp:" + chp);
			}
		}
		return (List<Question>) manager.runQuery("from Question");
	}

	public String list() {
		getAll();
		return SUCCESS;
	}

	public void setSession(Map<String, Object> arg0) {
		session = arg0;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public List<String> getAnswers() {
		return answers;
	}

	public void setAnswers(List<String> answers) {
		this.answers = answers;
	}

	public Integer getCorrect() {
		return correct;
	}

	public void setCorrect(Integer correct) {
		this.correct = correct;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public int getCourseid() {
		return courseid;
	}

	public void setCourseid(int courseid) {
		this.courseid = courseid;
	}

	public int getChapterid() {
		return chapterid;
	}

	public void setChapterid(int chapterid) {
		this.chapterid = chapterid;
	}

}
