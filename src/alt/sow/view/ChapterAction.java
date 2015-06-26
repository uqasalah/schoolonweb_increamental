package alt.sow.view;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import alt.sow.controller.GenericManager;
import alt.sow.domain.Chapter;
import alt.sow.domain.Course;
import alt.sow.domain.User;
import alt.sow.util.DataSource;

import com.opensymphony.xwork2.ActionSupport;
import com.sn.util.StringUtils;

public class ChapterAction extends ActionSupport implements SessionAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GenericManager<Chapter> manager = new GenericManager<Chapter>();

	private List<Chapter> chapterList;
	private List<Course> courseList;
	private Map<String, Object> session;
	private Integer courseId;

	private Chapter chapter;

	public String add() {
		String result = "";
		Chapter chap = getChapter();
		if (chap != null) {
			Course parentCourse = (Course) manager.runQuery(
					"from Course c where c.idCourse="
							+ getChapter().getCourse().getIdCourse()).get(0);
			User cu = (User) session.get("currentUser");
			chap.setUser(cu);
			chap.setCourse(parentCourse);
			parentCourse.getChapters().add(chap);
			String s = chap.getChapterName();
			if (StringUtils.isBlank(s)) {
				addFieldError("chapter.chapterName",
						getText("required.chapter.name"));
				result = INPUT;
			} else {
				if (manager.add(chap)) {
					addActionMessage(getText("success.add.chapter") + " "
							+ chap.getChapterName() + " to "
							+ parentCourse.getCourseName());
					result = SUCCESS;
				} else {
					addActionError(getText("not.added.chapter.duplicate"));
					result = ERROR;
				}
			}
		} else {
			result = INPUT;
		}
		list();
		return result;
	}

	public String delete() {
		String returnString = INPUT;
		Chapter c = (Chapter) DataSource.getInstance().DAO.runQuery(
				"from Chapter c where c.idChapter="
						+ getChapter().getIdChapter()).get(0);

		getAll();

		if (c != null) {
			if (manager.remove(c)) {
				addActionMessage(getText("success.delete.chapter"));
				returnString = SUCCESS;
			} else {
				addActionError(getText("not.deleted.chapter"));
				returnString = ERROR;
			}
		} else {
			addActionError("invalid.id.chapter");
			returnString = ERROR;
		}
		list();
		System.out.println("chapoter : " + returnString);
		return returnString;
	}

	public String edit() {
		String returnString = INPUT;
		Chapter c = (Chapter) DataSource.getInstance().DAO.findByID(User.class,
				getChapter().getIdChapter());
		getAll();
		if (c != null && chapterList.contains(c)) {
			if (manager.edit(c)) {
				addActionMessage(getText("success.edit.chapter"));
				returnString = SUCCESS;
			} else {
				addActionError(getText("not.edited.chapter"));
				returnString = ERROR;
			}
		} else {
			addActionError("invalid.id.chapter");
			returnString = ERROR;
		}
		list();
		return returnString;
	}

	@Override
	public String execute() throws Exception {
		getAll();
		return SUCCESS;
	}

	public List<Chapter> getAll() {
		chapterList = (List<Chapter>) manager.runQuery("from Chapter");
		courseList = (List<Course>) manager.runQuery("from Course");
		return chapterList;
	}

	public Chapter getChapter() {
		return chapter;
	}

	public List<Chapter> getChapterList() {
		return chapterList;
	}

	public Integer getCourseId() {
		return courseId;
	}

	public List<Course> getCourseList() {
		return courseList;
	}

	public String list() {
		getAll();
		return SUCCESS;
	}

	public void setChapter(Chapter chapter) {
		this.chapter = chapter;
	}

	public void setChapterList(List<Chapter> chapterList) {
		this.chapterList = chapterList;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public void setCourseList(List<Course> courseList) {
		this.courseList = courseList;
	}

	public void setSession(Map<String, Object> arg0) {
		session = arg0;
	}

}
