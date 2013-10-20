package database.model;

public class Profile {
	int idProfile;
	String name;
	
	public Profile(int idProfile, String name) {
		super();
		this.idProfile = idProfile;
		this.name = name;
	}
	
	public int getIdProfile() {
		return idProfile;
	}
	public void setIdProfile(int idProfile) {
		this.idProfile = idProfile;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	

}
