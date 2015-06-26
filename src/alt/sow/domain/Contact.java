package alt.sow.domain;

// Generated Oct 31, 2013 2:24:02 PM by Hibernate Tools 3.6.0

import java.util.HashSet;
import java.util.Set;

/**
 * Contact generated by hbm2java
 */
public class Contact implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1425361821380912572L;
	private int idContact;
	private Address address;
	private String mobile;
	private String phone;
	private String email;
	private Set<Profile> profiles = new HashSet<Profile>(0);

	public Contact() {
	}

	public Contact(int idContact) {
		this.idContact = idContact;
	}

	public Contact(int idContact, Address address, String mobile, String phone,
			String email, Set<Profile> profiles) {
		this.idContact = idContact;
		this.address = address;
		this.mobile = mobile;
		this.phone = phone;
		this.email = email;
		this.profiles = profiles;
	}

	public Address getAddress() {
		return this.address;
	}

	public String getEmail() {
		return this.email;
	}

	public int getIdContact() {
		return this.idContact;
	}

	public String getMobile() {
		return this.mobile;
	}

	public String getPhone() {
		return this.phone;
	}

	public Set<Profile> getProfiles() {
		return this.profiles;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setIdContact(int idContact) {
		this.idContact = idContact;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setProfiles(Set<Profile> profiles) {
		this.profiles = profiles;
	}

}