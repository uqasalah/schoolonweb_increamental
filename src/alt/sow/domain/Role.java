package alt.sow.domain;

// Generated Oct 31, 2013 2:24:02 PM by Hibernate Tools 3.6.0

import java.util.HashSet;
import java.util.Set;

/**
 * Role generated by hbm2java
 */
public class Role implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6242024816649494983L;
	private int idRole;
	private String roleName;
	private Set<User> users = new HashSet<User>(0);
	private Set<Access> accesses = new HashSet<Access>(0);

	public Role() {
	}

	public Role(int idRole) {
		this.idRole = idRole;
	}

	public Role(int idRole, String roleName, Set<User> users,
			Set<Access> accesses) {
		this.idRole = idRole;
		this.roleName = roleName;
		this.users = users;
		this.accesses = accesses;
	}

	public Set<Access> getAccesses() {
		return this.accesses;
	}

	public int getIdRole() {
		return this.idRole;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public Set<User> getUsers() {
		return this.users;
	}

	public void setAccesses(Set<Access> accesses) {
		this.accesses = accesses;
	}

	public void setIdRole(int idRole) {
		this.idRole = idRole;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idRole;
		result = prime * result
				+ ((roleName == null) ? 0 : roleName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		if (idRole != other.idRole)
			return false;
		if (roleName == null) {
			if (other.roleName != null)
				return false;
		} else if (!roleName.equals(other.roleName))
			return false;
		return true;
	}

}
