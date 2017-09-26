
/**
 * 
 * @author cuddlyteddy
 *
 */
public class Kennel {

	private String kennelName;
/**
 * Construct new kennel 
 * @param name
 * 			kennel name
 */
	public Kennel(String name){
		kennelName = name;
		
	}
/**
 * Get kennelName
 * @return kennelName
 */
	protected String getKennelname() {
		return kennelName;
	}
	/**
	 * Set kennelName
	 * @param kennelname
	 */
	protected void setKennelname(String kennelname) {
		this.kennelName = kennelname;
	}
	

}
