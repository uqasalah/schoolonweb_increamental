package alt.sow.view;

import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import alt.sow.controller.GenericManager;
import alt.sow.domain.Address;
import alt.sow.domain.Contact;
import alt.sow.domain.Name;
import alt.sow.domain.Organisation;
import alt.sow.domain.Profile;
import alt.sow.domain.User;
import alt.sow.mail.SendMailTLS;
import alt.sow.util.DataSource;

import com.opensymphony.xwork2.ActionSupport;
import com.sn.file.FileReader;
import com.sn.util.StringUtils;

public class LoginAction extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = 1L;
	private GenericManager<User> manager = new GenericManager<User>();
	private Map<String, Object> session;

	private String username;
	private String retype;
	private String password;

	private User user;

	public String getPassword() {
		return password;
	}

	public User getUser() {
		return user;
	}

	public String getUsername() {
		return username;
	}

	public String login() {

		DataSource.getInstance();

		String result = "";
		if (StringUtils.isBlank(getUsername())) {
			addFieldError("username", getText("required.username"));
			result = INPUT;
		}
		if (StringUtils.isBlank(getPassword())) {
			addFieldError("password", getText("required.password"));
			result = INPUT;
		}

		if (!result.equals(INPUT)) {
			List rtn = manager.runQuery("from User u where u.username='"
					+ getUsername() + "' and u.password='" + getPassword()
					+ "'");
			if (rtn != null && rtn.size() != 0 && rtn.get(0) instanceof User) {
				User t = (User) rtn.get(0);
				if (t.getBlocked()) {
					addActionError(getText("user.blocked"));
					result = ERROR;
				} else {
					session.put("currentUser", t);
					session.put("isLoggedIn", true);
					System.out.println(t + " : loggedin");
					result = SUCCESS;
				}
			} else {
				addActionError(getText("invalid.credentials"));
				result = ERROR;
			}
		}
		System.out.println(result);
		return result;
	}

	public String logout() {

		if (session.containsKey("currentUser")) {
			User cu = (User) session.get("currentUser");
			cu.setLastLogin(new Date(System.currentTimeMillis()));
			cu.setFirstLogin(false);
			manager.add(cu);
			session.remove("currentUser");
			session.remove("isLoggedIn");
			addActionMessage("success.logout");
			return SUCCESS;
		}

		return ERROR;
	}

	public String forgot() {
		System.out.println("username:" + getUsername());
		return mailer(getUsername());
	}

	public String mailer(String un) {
		String result = INPUT;
		if (!StringUtils.isBlank(un) && un.indexOf("@") > -1) {
			InputStream is = this
					.getClass()
					.getClassLoader()
					.getResourceAsStream(
							"Document_Template//passworddetails.html");

			// System.out.println(is);

			FileReader fReader = new FileReader(is, null);

			SendMailTLS mailer = new SendMailTLS();
			List users = DataSource.getInstance().DAO
					.runQuery("from User u where u.username='" + un + "'");
			if (users != null && !users.isEmpty()) {
				User tmp = (User) users.get(0);
				String content = fReader.toString();
				content = content.replace("$username", tmp.getUsername());
				content = content.replace("$password", tmp.getPassword());
				System.out.println("sending to " + tmp.getUsername() + "\n");
				if (mailer.SendMail(tmp.getUsername(), content)) {
					System.out.println("mail sent");
					addActionMessage("mail.sent");
					result = SUCCESS;
				} else {
					addActionError(getText("mail.notsent"));
					System.out.println("mail not sent");
					result = ERROR;
				}
			} else {
				addActionError(getText("record.notfound"));
				result = ERROR;
			}
		} else {
			addFieldError("username", getText("required.username"));
			result = INPUT;
		}
		System.out.println("mailer result : " + result);
		return result;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setSession(Map<String, Object> arg0) {
		this.session = arg0;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String updateProfile() {
		String result = INPUT;
		User newUser = getUser();
		if (newUser != null) {

			if (StringUtils.isBlank(newUser.getPassword())) {
				addFieldError("user.password", getText("required.password"));
				result = INPUT;
				return result;
			}
			if (StringUtils.isBlank(getRetype())) {
				addFieldError("retype", getText("required.retype"));
				result = INPUT;
				return result;
			}
			if (!newUser.getPassword().equals(getRetype())) {
				addActionError(getText("password.retype.notmatched"));
				result = ERROR;
				return result;
			}
			if (newUser.getProfile().getOrganisation() == null) {
				addFieldError("profile.organisation.organisationName",
						getText("required.organisation"));
				System.out.println("org"
						+ newUser.getProfile().getOrganisation()
								.getOrganisationName());
				result = INPUT;
				return result;
			}
			if (StringUtils.isBlank(newUser.getProfile().getContact()
					.getMobile())) {
				addFieldError("user.profile.contact.mobile",
						getText("required.mobile"));
				System.out.println("mob");
				result = INPUT;
				return result;
			}
			System.out.println("new user : " + newUser + "\n"
					+ "from User u where u.username='" + newUser.getUsername()
					+ "'");
			User cu = (User) session.get("currentUser");
			User dbUser = (User) (manager
					.runQuery("from User u where u.username='"
							+ cu.getUsername() + "'").get(0));
			System.out.println("db++ user : " + dbUser);

			Profile profile = dbUser.getProfile();
			if (profile == null) {
				profile = new Profile();
				dbUser.setProfile(profile);
			}
			Contact contact = dbUser.getProfile().getContact();
			if (contact == null) {
				contact = new Contact();
				dbUser.getProfile().setContact(contact);
			}
			Name name = dbUser.getProfile().getNameByNameIdName();
			if (name == null) {
				name = new Name();
				dbUser.getProfile().setNameByNameIdName(name);
			}
			Address address = dbUser.getProfile().getContact().getAddress();
			if (address == null) {
				address = new Address();
				dbUser.getProfile().getContact().setAddress(address);
			}
			Organisation organisation = dbUser.getProfile().getOrganisation();
			if (organisation == null) {
				organisation = new Organisation();
				dbUser.getProfile().setOrganisation(organisation);
			}
			//
			contact.setAddress(address);
			address.getContacts().add(contact);
			//
			profile.setContact(contact);
			contact.getProfiles().add(profile);
			//
			profile.setNameByNameIdName(name);
			name.getProfilesForNameIdName().add(profile);
			//
			organisation.getProfiles().add(profile);
			profile.setOrganisation(organisation);
			//
			dbUser.setProfile(profile);
			//

			dbUser.setUsername(newUser.getUsername());
			dbUser.setPassword(newUser.getPassword());
			dbUser.getProfile()
					.getNameByNameIdName()
					.setFirstName(
							newUser.getProfile().getNameByNameIdName()
									.getFirstName());
			dbUser.getProfile()
					.getNameByNameIdName()
					.setLastName(
							newUser.getProfile().getNameByNameIdName()
									.getLastName());
			dbUser.getProfile()
					.getOrganisation()
					.setOrganisationName(
							(newUser.getProfile().getOrganisation()
									.getOrganisationName()));

			dbUser.getProfile().getContact()
					.setMobile(newUser.getProfile().getContact().getMobile());
			dbUser.getProfile()
					.getContact()
					.getAddress()
					.setHno(newUser.getProfile().getContact().getAddress()
							.getHno());
			dbUser.getProfile()
					.getContact()
					.getAddress()
					.setStreet1(
							newUser.getProfile().getContact().getAddress()
									.getStreet1());
			dbUser.getProfile()
					.getContact()
					.getAddress()
					.setStreet2(
							newUser.getProfile().getContact().getAddress()
									.getStreet2());
			dbUser.getProfile()
					.getContact()
					.getAddress()
					.setPobox(
							newUser.getProfile().getContact().getAddress()
									.getPobox());
			dbUser.getProfile()
					.getContact()
					.getAddress()
					.setCity(
							newUser.getProfile().getContact().getAddress()
									.getCity());
			dbUser.getProfile()
					.getContact()
					.getAddress()
					.setCountry(
							newUser.getProfile().getContact().getAddress()
									.getCountry());

			dbUser.getProfile().getUsers().add(dbUser);
			dbUser.getProfile().setBirthDate(
					newUser.getProfile().getBirthDate());

			if (manager.save(dbUser) != null) {

				session.remove("currentUser");
				session.put("currentUser", dbUser);
				addActionMessage("user.update.successfull");
				result = SUCCESS;
			} else {
				addActionError(getText("user.update.failed"));
				result = ERROR;
			}
		} else {
			//addActionError(getText("user.required.field.missing"));
			result = INPUT;
		}

		System.out.println("rs:" + result);
		return result;
	}

	public String viewProfile() {
		if (getUser() != null && getUser().getIdUser() != 0) {
			User tmp = manager.findByID(User.class, getUser().getIdUser());
			setUser(tmp);
			System.out.println(tmp);
			return SUCCESS;
		} else {
			addActionError(getText("invalid.id"));
			System.out.println("user not found ,invalid id");
			return INPUT;
		}
	}

	public String firstLogin() {
		String result = INPUT;
		User cu = (User) session.get("currentUser");
		if (cu.getFirstLogin()) {
			result = "updateProfile";
		} else {
			result = "dashboard";
		}
		return result;
	}

	public void setRetype(String retype) {
		this.retype = retype;
	}

	public String getRetype() {
		return retype;
	}
}
