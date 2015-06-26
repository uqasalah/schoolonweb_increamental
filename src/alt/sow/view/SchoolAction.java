package alt.sow.view;

import java.util.List;

import alt.sow.controller.GenericManager;
import alt.sow.domain.User;
import alt.sow.mail.SendMailTLS;
import alt.sow.util.DataSource;

import com.opensymphony.xwork2.ActionSupport;
import com.sn.util.StringUtils;

public class SchoolAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1278406359685380215L;
	private GenericManager<User> manager = new GenericManager<User>();

	private User user;

	private List<User> schoolList;

	public String action(boolean block) {
		String result;
		User user = (User) DataSource.getInstance().DAO.findByID(User.class,
				getUser().getIdUser());
		if (user == null || user.getIdUser() == 0) {
			result = INPUT;
		} else {
			getAll();
			if (schoolList.contains(user)) {
				user.setBlocked(block);
				if (manager.save(user) != null) {
					result = SUCCESS;
				} else {
					result = ERROR;
				}
			} else {
				addActionError("invalid.id.school");
				result = ERROR;
			}
		}
		return result;
	}

	public String add() {
		String result = "";
		User user = getUser();
		if (user != null) {
			String s = user.getUsername();
			List<User> users = (List<User>) manager
			.runQuery("from User u where u.username='" + s + "'");
			if (StringUtils.isBlank(s) || !StringUtils.isValidEmail(s)) {
				addFieldError("user.username", getText("required.email"));
				result = INPUT;
			}else if (users != null && users.size() > 0) {
				addActionError(getText("duplicate.user"));
				result = ERROR;
			} else {
				user.setFirstLogin(true);
				user.setBlocked(DataSource.getInstance().DEF_BLOCKED);
				user.setRole(DataSource.getInstance().SCHOOL);
				user.setPassword(SendMailTLS.generatePassword());
				if (manager.add(user)) {
					addActionMessage(user.getUsername() + " "
							+getText("success.add.school"));
					result = SUCCESS;
				} else {
					addActionError(getText("not.added.school.duplicate"));
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
		if (user != null && getSchoolList().contains(user)) {
			if (manager.remove(user)) {
				addActionMessage(getText("success.delete.school"));
				returnString = SUCCESS;
			} else {
				addActionError(getText("not.deleted.school"));
				returnString = ERROR;
			}
		} else {
			addActionError("invalid.id.school");
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
		if (user != null && schoolList.contains(user)) {
			if (manager.edit(user)) {
				addActionMessage(getText("success.edit.school"));
				returnString = SUCCESS;
			} else {
				addActionError(getText("not.edited.school"));
				returnString = ERROR;
			}
		} else {
			addActionError("invalid.id.school");
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

	public List<User> getAll() {
		schoolList = (List<User>) manager
				.runQuery("from User u where u.role.idRole="
						+ DataSource.getInstance().SCHOOL.getIdRole());
		return schoolList;
	}

	public List<User> getSchoolList() {
		return schoolList;
	}

	public User getUser() {
		return user;
	}

	public String list() {
		getAll();
		return SUCCESS;
	}

	public void setSchoolList(List<User> schoolList) {
		this.schoolList = schoolList;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String unBlock() {
		return action(false);
	}

}
