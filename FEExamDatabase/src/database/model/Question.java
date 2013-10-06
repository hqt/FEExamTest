package database.model;

public class Question {
	String question;
	String ansa, ansb, ansc, ansd, anse;
	byte[] img;
	byte[] imga, imgb, imgc, imgd, imge;
	String explanation;
	byte[] imgexp;
	int ans;
	String section;

   	
	public Question(String question, String ansa, String ansb, String ansc,
			String ansd, String anse, byte[] img, byte[] imga, byte[] imgb,
			byte[] imgc, byte[] imgd, byte[] imge, String explanation,
			byte[] imgexp, int ans, String section) {
		super();
		this.question = question;
		this.ansa = ansa;
		this.ansb = ansb;
		this.ansc = ansc;
		this.ansd = ansd;
		this.anse = anse;
		this.img = img;
		this.imga = imga;
		this.imgb = imgb;
		this.imgc = imgc;
		this.imgd = imgd;
		this.imge = imge;
		this.explanation = explanation;
		this.imgexp = imgexp;
		this.ans = ans;
		this.section = section;
	}
	
	public byte[] getImga() {
		return imga;
	}
	public void setImga(byte[] imga) {
		this.imga = imga;
	}
	public byte[] getImgb() {
		return imgb;
	}
	public void setImgb(byte[] imgb) {
		this.imgb = imgb;
	}
	public byte[] getImgc() {
		return imgc;
	}
	public void setImgc(byte[] imgc) {
		this.imgc = imgc;
	}
	public byte[] getImgd() {
		return imgd;
	}
	public void setImgd(byte[] imgd) {
		this.imgd = imgd;
	}
	public byte[] getImge() {
		return imge;
	}
	public void setImge(byte[] imge) {
		this.imge = imge;
	}
	public byte[] getImgexp() {
		return imgexp;
	}
	public void setImgexp(byte[] imgexp) {
		this.imgexp = imgexp;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnsa() {
		return ansa;
	}
	public void setAnsa(String ansa) {
		this.ansa = ansa;
	}
	public String getAnsb() {
		return ansb;
	}
	public void setAnsb(String ansb) {
		this.ansb = ansb;
	}
	public String getAnsc() {
		return ansc;
	}
	public void setAnsc(String ansc) {
		this.ansc = ansc;
	}
	public String getAnsd() {
		return ansd;
	}
	public void setAnsd(String ansd) {
		this.ansd = ansd;
	}
	public String getAnse() {
		return anse;
	}
	public void setAnse(String anse) {
		this.anse = anse;
	}
	public byte[] getImg() {
		return img;
	}
	public void setImg(byte[] img) {
		this.img = img;
	}
	public String getExplanation() {
		return explanation;
	}
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
	public int getAns() {
		return ans;
	}
	public void setAns(int ans) {
		this.ans = ans;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}

	@Override
	public String toString() {
		return "Question [question=" + question + ", ansa=" + ansa + ", ansb=" + ansb + ", ansc=" + ansc + ", ansd="
				+ ansd + ", anse=" + anse + ", explanation=" + explanation + ", ans=" + ans + ", section=" + section
				+ "]";
	}
	
	
}
