/**
 * 
 * @author Lynda Thomas and Chris Loftus
 * @version 1.0 (March 19th 2015)
 *
 */
public class Owner {
	private String name;
	private String phone;

	/**
	 * Constructor to create owner
	 * 
	 * @param n
	 *            = Owne's name
	 * @param p
	 *            =Owner's Phone Number
	 */
	public Owner(String n, String p) {
		name = n;
		phone = p;
	}

	/**
	 * get name of the owner/owners
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * set the name of the owner/owners
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * get owner's/owners' phone number
	 * 
	 * @return phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * set the owner's/owners' phone number
	 * 
	 * @param phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/*
	 * Print all the information about owner (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Owner name: ").append(name).append("  Phone No. : ")
				.append(phone);
		return sb.toString();
	}

}
