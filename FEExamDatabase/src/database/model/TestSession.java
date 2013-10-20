package database.model;

import java.util.Date;

public class TestSession {

	int sessionId;
	Date doingDate;
	int profileId;
	public TestSession(int sessionId, Date doingDate, int profileId) {
		super();
		this.sessionId = sessionId;
		this.doingDate = doingDate;
		this.profileId = profileId;
	}
	public int getSessionId() {
		return sessionId;
	}
	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}
	public Date getDoingDate() {
		return doingDate;
	}
	public void setDoingDate(Date doingDate) {
		this.doingDate = doingDate;
	}
	public int getProfileId() {
		return profileId;
	}
	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}
	
	
	
	
	
}
