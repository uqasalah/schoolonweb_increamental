package alt.sow.domain;

// Generated Oct 31, 2013 2:24:02 PM by Hibernate Tools 3.6.0

import java.util.HashSet;
import java.util.Set;

/**
 * Organisation generated by hbm2java
 */
public class Organisation implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -834828433906225279L;
	private int idOrganisation;
	private String organisationName;
	private String organisationCode;
	private Set<Profile> profiles = new HashSet<Profile>(0);

	public Organisation() {
	}

	public Organisation(int idOrganisation) {
		this.idOrganisation = idOrganisation;
	}

	public Organisation(int idOrganisation, String organisationName,
			String organisationCode, Set<Profile> profiles) {
		this.idOrganisation = idOrganisation;
		this.organisationName = organisationName;
		this.organisationCode = organisationCode;
		this.profiles = profiles;
	}

	public int getIdOrganisation() {
		return this.idOrganisation;
	}

	public String getOrganisationCode() {
		return this.organisationCode;
	}

	public String getOrganisationName() {
		return this.organisationName;
	}

	public Set<Profile> getProfiles() {
		return this.profiles;
	}

	public void setIdOrganisation(int idOrganisation) {
		this.idOrganisation = idOrganisation;
	}

	public void setOrganisationCode(String organisationCode) {
		this.organisationCode = organisationCode;
	}

	public void setOrganisationName(String organisationName) {
		this.organisationName = organisationName;
	}

	public void setProfiles(Set<Profile> profiles) {
		this.profiles = profiles;
	}

}