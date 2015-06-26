package alt.sow.view;

import java.util.List;

import alt.sow.controller.GenericManager;
import alt.sow.domain.User;
import alt.sow.mail.SendMailTLS;
import alt.sow.util.DataSource;

import com.opensymphony.xwork2.ActionSupport;
import com.sn.util.StringUtils;

public class AdminAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1278406359685380215L;
	private GenericManager<User> manager = new GenericManager<User>();

	private User user;

	private List<User> adminList;

	public String action(boolean block) {
		String result;
		User user = (User) DataSource.getInstance().DAO.findByID(User.class,
				getUser().getIdUser());
		if (user == null && user.getIdUser() == 0) {
			result = INPUT;
		} else {
			getAll();
			if (user != null && adminList.contains(user)) {
				user.setBlocked(block);
				if (manager.save(user) != null) {
					result = SUCCESS;
				} else {
					result = ERROR;
				}
			} else {
				addActionError(getText("invalid.id.admin"));
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
			} else if (users != null && users.size() > 0) {
				addActionError(getText("duplicate.user"));
				result = ERROR;
			} else {
				user.setFirstLogin(true);
				user.setBlocked(DataSource.getInstance().DEF_BLOCKED);
				user.setRole(DataSource.getInstance().ADMIN);
				//
				user.setPassword(SendMailTLS.generatePassword());
				if (manager.add(user)) {
					addActionMessage(user.getUsername() + " "
							+ getText("success.add.admin"));
					result = SUCCESS;
				} else {
					addActionError(getText(getText("not.added.admin.duplicate")));
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
		if (user != null && adminList.contains(user)) {
			if (manager.remove(user)) {
				addActionMessage(getText("success.delete.admin"));
				returnString = SUCCESS;
			} else {
				addActionError(getText(getText("not.deleted.admin")));
				returnString = ERROR;
			}
		} else {
			addActionError(getText("invalid.id.admin"));
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
		if (user != null && adminList.contains(user)) {
			if (manager.edit(user)) {
				addActionMessage(getText("success.edit.admin"));
				returnString = SUCCESS;
			} else {
				addActionError(getText(getText("not.edited.admin")));
				returnString = ERROR;
			}
		} else {
			addActionError(getText("invalid.id.admin"));
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

	public List<User> getAdminList() {
		return adminList;
	}

	public List<User> getAll() {
		adminList = (List<User>) manager
				.runQuery("from User u where u.role.idRole="
						+ DataSource.getInstance().ADMIN.getIdRole());
		return adminList;
	}

	public User getUser() {
		return user;
	}

	public String list() {
		getAll();
		return SUCCESS;
	}

	public void setAdminList(List<User> adminList) {
		this.adminList = adminList;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String unBlock() {
		return action(false);
	}

}
