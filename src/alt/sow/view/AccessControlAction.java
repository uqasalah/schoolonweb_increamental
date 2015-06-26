package alt.sow.view;

import java.util.List;

import alt.sow.controller.GenericManager;
import alt.sow.domain.Access;
import alt.sow.domain.Role;
import alt.sow.domain.User;

import com.opensymphony.xwork2.ActionSupport;

public class AccessControlAction extends ActionSupport {
	private static final long serialVersionUID = 1278406359685380278L;
	private GenericManager<User> manager = new GenericManager<User>();
	private List<Role> roleList;
	private List<Access> accessList;
	//
	private int roleId;
	private int accessId;

	//
	@Override
	public String execute() throws Exception {
		getAll();
		return SUCCESS;
	}

	public List<Role> getAll() {
		roleList = (List<Role>) manager.runQuery("from Role");
		accessList = (List<Access>) manager.runQuery("from Access");
		return roleList;
	}

	public String list() {
		getAll();
		return SUCCESS;
	}

	public String addNew() {
		Role r = (Role) manager
				.runQuery("from Role r where r.idRole=" + roleId);
		Access a = (Access) manager.runQuery("from Access r where r.idAccess="
				+ accessId);
		r.getAccesses().add(a);
		manager.saveObject(r);
		return SUCCESS;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public List<Access> getAccessList() {
		return accessList;
	}

	public void setAccessList(List<Access> accessList) {
		this.accessList = accessList;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getAccessId() {
		return accessId;
	}

	public void setAccessId(int accessId) {
		this.accessId = accessId;
	}

}
