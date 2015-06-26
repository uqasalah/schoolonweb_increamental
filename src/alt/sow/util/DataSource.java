/*

 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package alt.sow.util;

import alt.sow.domain.Name;
import alt.sow.domain.Organisation;
import alt.sow.domain.Profile;
import alt.sow.domain.Role;
import alt.sow.domain.Tag;
import alt.sow.domain.User;

/**
 * 
 * @author Blade
 */
public class DataSource {

	public static final Boolean DEF_BLOCKED = Boolean.FALSE;
	// SINGLETON
	private static DataSource INSTANCE;

	public static DataSource getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new DataSource();
		}
		return INSTANCE;
	}

	// CONSTANTS
	public Role DEBUG;
	public Role ADMIN;
	public Role SCHOOL;
	public Role TEACHER;
	public Tag MCQ;
	public Tag SHORTANSWER;
	public Tag LONGANSWER;
	public User DEBUGADMIN;
	public static int EASY = 1;
	public static int MED = 2;
	public static int HARD = 3;
	//

	//

	//
	public GenericDAO DAO;

	private DataSource() {
		DAO = new GenericDAO();
		//
		DEBUG = new Role();
		DEBUG.setRoleName("debug");
		DEBUG = getOrCreateRole(DEBUG);
		ADMIN = new Role();
		ADMIN.setRoleName("administrator");
		ADMIN = getOrCreateRole(ADMIN);
		SCHOOL = new Role();
		SCHOOL.setRoleName("school");
		SCHOOL = getOrCreateRole(SCHOOL);
		TEACHER = new Role();
		TEACHER.setRoleName("teacher");
		TEACHER = getOrCreateRole(TEACHER);
		//
		MCQ = new Tag();
		MCQ.setTagName("mcq");
		MCQ = getOrCreateTag(MCQ);
		SHORTANSWER = new Tag();
		SHORTANSWER.setTagName("shortanswer");
		SHORTANSWER = getOrCreateTag(SHORTANSWER);
		LONGANSWER = new Tag();
		LONGANSWER.setTagName("longanswer");
		LONGANSWER = getOrCreateTag(LONGANSWER);
		//
		Organisation org = new Organisation();
		org.setOrganisationName("SoftCodes ltd.");
		org = getOrCreateOrg(org);
		//
		Name name = new Name();
		name.setFirstName("Debug Admin");
		// name = getOrCreateName(name);
		//
		Profile prof = new Profile();
		prof.setOrganisation(org);
		prof.setNameByNameIdName(name);

		//
		DEBUGADMIN = new User();
		DEBUGADMIN.setRole(ADMIN);
		DEBUGADMIN.setProfile(prof);
		DEBUGADMIN.setUsername("suadmin");
		DEBUGADMIN.setPassword("pass");
		DEBUGADMIN.setBlocked(false);
		DEBUGADMIN.setFirstLogin(false);
		DEBUGADMIN = getOrCreateUser(DEBUGADMIN);
	}

	private Object getOrCreate(Object obj, String hql, String param) {
		Object db = DAO.findUnique(hql + "'" + param + "'");
		if (db == null) {
			DAO.save(obj);
			db = DAO.findUnique(hql + "'" + param + "'");
		}
		return db;
	}

	private Name getOrCreateName(Name name) {
		return (Name) getOrCreate(name, "from Name u where u.firstName=",
				name.getFirstName());
	}

	private Organisation getOrCreateOrg(Organisation org) {
		return (Organisation) getOrCreate(org,
				"from Organisation u where u.organisationName=",
				org.getOrganisationName());
	}

	private Role getOrCreateRole(Role d) {
		return (Role) getOrCreate(d, "from Role u where u.roleName=",
				d.getRoleName());
	}

	private Tag getOrCreateTag(Tag d) {
		return (Tag) getOrCreate(d, "from Tag u where u.tagName=",
				d.getTagName());
	}

	private User getOrCreateUser(User d) {
		return (User) getOrCreate(d, "from User u where u.username=",
				d.getUsername());
	}

	public static int getListSize(String qry) {
		int rtn = 0;
		try {
			rtn = DataSource.getInstance().DAO.runQuery(qry).size();
		} catch (Exception e) {
		}
		return rtn;
	}
}
