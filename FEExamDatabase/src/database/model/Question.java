package database.model;

import java.util.ArrayList;
import java.util.List;

public class Question {
	String question;
	String ansa, ansb, ansc, ansd, anse;
	byte[] img;
	byte[] imga, imgb, imgc, imgd, imge;
	String explanation;
	byte[] imgexp;
	int ans;
	String section;


	// custom field
	public int numOfSelections;
	public String[] selections;
	public List<byte[]> imgSelections = new ArrayList<byte[]>();
	
	public Question(String question, String ansa, String ansb, String ansc,
			String ansd, String anse, byte[] img, byte[] imga, byte[] imgb,
			byte[] imgc, byte[] imgd, byte[] imge, String explanation,
			byte[] imgexp, int ans, String section, int numOfSelections) {
		super();
		List<String> tmp = new ArrayList<String>();
		this.question = question;
		this.ansa = ansa;	tmp.add(ansa);
		this.ansb = ansb;	tmp.add(ansb);
		this.ansc = ansc;	tmp.add(ansc);
		this.ansd = ansd;	tmp.add(ansd);
		this.anse = anse;	tmp.add(anse);
		this.img = img;
		this.imga = imga;	imgSelections.add(imga);
		this.imgb = imgb;	imgSelections.add(imgb);
		this.imgc = imgc;	imgSelections.add(imgc);
		this.imgd = imgd;	imgSelections.add(imgd);
		this.imge = imge;	imgSelections.add(imge);
		this.explanation = explanation;
		this.imgexp = imgexp;
		this.ans = ans;
		this.section = section;
		this.numOfSelections = numOfSelections;
	
		// process data
		selections = new String[numOfSelections];
		for (int i = 0; i < numOfSelections; i++) {
			selections[i] = tmp.get(i);
		}
		
		
	}
	
	public List<byte[]> getAllImages() { return imgSelections; }
	
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
				+ "]" + ", numOfSelections=" + numOfSelections + "]";
	}
	
	
}
