package alt.sow.view;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import alt.sow.controller.GenericManager;
import alt.sow.domain.Course;
import alt.sow.domain.User;
import alt.sow.util.DataSource;

import com.opensymphony.xwork2.ActionSupport;
import com.sn.util.StringUtils;

public class CourseAction extends ActionSupport implements SessionAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1278406359685380215L;
	private GenericManager<Course> manager = new GenericManager<Course>();
	private Map<String, Object> sesion;

	private Course course;

	private List<Course> courseList;

	public String add() {
		String result = "";
		Course course = getCourse();
		System.out.println("course:" + course);
		if (course != null) {
			String s = course.getCourseName();

			User cu = (User) sesion.get("currentUser");
			course.setUser(cu);
			if (StringUtils.isBlank(s)) {
				addFieldError("course.courseName",
						getText("required.course.name"));
				result = INPUT;
			} else {
				if (manager.add(course)) {
					addActionMessage(course.getCourseName() + " "
							+ getText("success.add.course"));
					result = SUCCESS;
				} else {
					addActionError(getText("not.added.course.duplicate"));
					result = ERROR;
				}
			}
		} else {
			result = INPUT;
		}
		// list();
		return result;
	}

	public String delete() {
		String returnString = INPUT;
		Course course = (Course) manager.runQuery(
				"from Course c where c.idCourse=" + getCourse().getIdCourse())
				.get(0);

		System.out.println("db:" + course + "\nur:" + getCourse());

		getAll();
		if (course != null && courseList.contains(course)) {
			if (manager.remove(course)) {
				addActionMessage(getText("success.delete.course"));
				returnString = SUCCESS;
			} else {
				addActionError(getText("not.deleted.course"));
				returnString = ERROR;
			}
		} else {
			addActionError("invalid.id.course");
			returnString = ERROR;
		}
		list();
		return returnString;
	}

	public String edit() {
		String returnString = INPUT;
		Course course = (Course) DataSource.getInstance().DAO.findByID(
				User.class, getCourse().getIdCourse());
		getAll();
		if (course != null && courseList.contains(course)) {
			if (manager.edit(course)) {
				addActionMessage(course.getCourseName() + " "
						+ getText("success.edit.course"));
				returnString = SUCCESS;
			} else {
				addActionError(getText("not.edited.course"));
				returnString = ERROR;
			}
		} else {
			addActionError("invalid.id.course");
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

	public List<Course> getAll() {
		courseList = manager.listAll(Course.class);
		return courseList;
	}

	public Course getCourse() {
		return course;
	}

	public List<Course> getCourseList() {
		return courseList;
	}

	public String list() {
		getAll();
		return SUCCESS;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public void setCourseList(List<Course> courseList) {
		this.courseList = courseList;
	}

	public void setSession(Map<String, Object> arg0) {
		this.sesion = arg0;
	}

}
