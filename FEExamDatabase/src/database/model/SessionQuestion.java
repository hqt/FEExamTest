package database.model;

public class SessionQuestion {

	int sessionQuestionId;
	int sessionId;
	String categoryName;
	int idQuestion;
	int percentCorrect;
	
	public SessionQuestion(int sessionQuestionId, int sessionId, String categoryName, int idQuestion, int percentCorrect) {
		super();
		this.sessionQuestionId = sessionQuestionId;
		this.sessionId = sessionId;
		this.categoryName = categoryName;
		this.idQuestion = idQuestion;
		this.percentCorrect = percentCorrect;
	}
	
	
	public int getSessionQuestionId() {
		return sessionQuestionId;
	}


	public void setSessionQuestionId(int sessionQuestionId) {
		this.sessionQuestionId = sessionQuestionId;
	}


	public int getSessionId() {
		return sessionId;
	}
	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public int getIdQuestion() {
		return idQuestion;
	}
	public void setIdQuestion(int idQuestion) {
		this.idQuestion = idQuestion;
	}
	public int getPercentCorrect() {
		return percentCorrect;
	}
	public void setPercentCorrect(int percentCorrect) {
		this.percentCorrect = percentCorrect;
	}


	@Override
	public String toString() {
		return "SessionQuestion [sessionQuestionId=" + sessionQuestionId + ", sessionId=" + sessionId
				+ ", categoryName=" + categoryName + ", idQuestion=" + idQuestion + ", percentCorrect="
				+ percentCorrect + "]";
	}
	
	
	
	
}
