package alt.sow.view;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import alt.sow.controller.GenericManager;
import alt.sow.domain.Organisation;
import alt.sow.domain.Profile;
import alt.sow.domain.User;
import alt.sow.mail.SendMailTLS;
import alt.sow.util.DataSource;

import com.opensymphony.xwork2.ActionSupport;
import com.sn.util.StringUtils;

public class TeacherAction extends ActionSupport implements SessionAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GenericManager<User> manager = new GenericManager<User>();
	private Map<String, Object> session;

	private User user;

	private List<User> teacherList;

	public String action(boolean block) {
		String result;
		User user = (User) DataSource.getInstance().DAO.findByID(User.class,
				getUser().getIdUser());
		if (user == null && user.getIdUser() == 0) {
			result = INPUT;
		} else {
			getAll();
			if (teacherList.contains(user)) {
				user.setBlocked(block);
				if (manager.save(user) != null) {
					result = SUCCESS;
				} else {
					result = ERROR;
				}
			} else {
				addActionError("invalid.id.teacher");
				result = ERROR;
			}
		}
		return result;
	}

	public String add() {
		String result = "";
		User teacher = getUser();
		if (teacher != null) {
			String s = teacher.getUsername();
			List<User> users = (List<User>) manager
					.runQuery("from User u where u.username='" + s + "'");
			if (StringUtils.isBlank(s) || !StringUtils.isValidEmail(s)) {
				addFieldError("user.username", getText("required.email"));
				result = INPUT;
			} else if (users != null && users.size() > 0) {
				addActionError(getText("duplicate.user"));
				result = ERROR;
			} else {
				User cu = (User) session.get("currentUser");
				teacher.setFirstLogin(true);
				teacher.setBlocked(DataSource.getInstance().DEF_BLOCKED);
				//
				teacher.setRole(DataSource.getInstance().TEACHER);
				DataSource.getInstance().TEACHER.getUsers().add(teacher);
				//
				if (teacher.getProfile() == null) {
					teacher.setProfile(new Profile());
				}
				//
				Profile profile = teacher.getProfile();
				Organisation org = cu.getProfile().getOrganisation();
				org.getProfiles().add(profile);
				profile.setOrganisation(org);
				//
				// DataSource.getInstance().DAO.save(profile);
				// DataSource.getInstance().DAO.merge(org);
				//
				profile.getUsers().add(teacher);
				teacher.setProfile(profile);
				//
				teacher.setPassword(SendMailTLS.generatePassword());
				if (manager.add(teacher)) {
					addActionMessage(teacher.getUsername() + " "
							+ getText("success.add.teacher"));
					result = SUCCESS;
				} else {
					addActionError(getText("not.added.teacher.duplicate"));
					result = ERROR;
				}
			}
		} else {
			result = INPUT;
		}
		list();
		if (result.equals(SUCCESS)) {
			new LoginAction().mailer(user.getUsername());
		}
		System.out.println("teacher:" + result);
		return result;
	}

	public String block() {
		return action(true);
	}

	public String delete() {
		String returnString = INPUT;
		User user = (User) DataSource.getInstance().DAO.findByID(User.class,
				getUser().getIdUser());
		getAll();
		if (user != null && teacherList.contains(user)) {

			if (manager.remove(user)) {
				addActionMessage(getText("success.delete.teacher"));
				returnString = SUCCESS;
			} else {
				addActionError(getText("not.deleted.teacher"));
				returnString = ERROR;
			}
		} else {
			addActionError("invalid.id.teacher");
			returnString = ERROR;
		}
		list();
		return returnString;
	}

	public String edit() {
		String returnString = INPUT;
		User user = (User) DataSource.getInstance().DAO.findByID(User.class,
				getUser().getIdUser());
		getAll();
		if (user != null && teacherList.contains(user)) {
			if (manager.edit(user)) {
				addActionMessage(getText("success.edit.teacher"));
				returnString = SUCCESS;
			} else {
				addActionError(getText("not.edited.teacher"));
				returnString = ERROR;
			}
		} else {
			addActionError("invalid.id.teacher");
			returnString = ERROR;
		}
		list();
		return returnString;
	}

	@Override
	public String execute() throws Exception {
		list();
		return SUCCESS;
	}

	public List<User> getAll() {
		try {
			User cu = (User) session.get("currentUser");

			teacherList = (List<User>) manager
					.runQuery("from User u where u.role.idRole="
							+ DataSource.getInstance().TEACHER.getIdRole()
							+ " and u.profile.organisation.idOrganisation="
							+ cu.getProfile().getOrganisation()
									.getIdOrganisation());

			return teacherList;

		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<User> getTeacherList() {
		return teacherList;
	}

	public User getUser() {
		return user;
	}

	public String list() {
		if (getAll() == null) {
			addActionError("current.user.profile.notset");
			return ERROR;
		}
		return SUCCESS;
	}

	public void setSession(Map<String, Object> arg0) {
		session = arg0;
	}

	public void setTeacherList(List<User> teacherList) {
		this.teacherList = teacherList;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String unBlock() {
		return action(false);
	}

}
