package programm;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.Date;

public class Plant {

	private String botanischerName;
	private String deutscherName;
	private String sorte;
	private Color bluetenFarbe;
	private String pflegehinweis;
	private Date gepflanzt;
	private Date geduengt;
	private Rectangle rectangle;
	private String bezug;
	private String comment;
	private String imagePath;
	private boolean kaputt;
	private int monatVon;
	private int monatBis;
	
	
	public String getBotanischerName() {
		return botanischerName;
	}
	public void setBotanischerName(String botanischerName) {
		this.botanischerName = botanischerName;
	}
	public String getDeutscherName() {
		return deutscherName;
	}
	public void setDeutscherName(String deutscherName) {
		this.deutscherName = deutscherName;
	}
	public String getSorte() {
		return sorte;
	}
	public void setSorte(String sorte) {
		this.sorte = sorte;
	}
	public Color getBluetenFarbe() {
		return bluetenFarbe;
	}
	public void setBluetenFarbe(Color bluetenFarbe) {
		this.bluetenFarbe = bluetenFarbe;
	}
	public String getPflegehinweis() {
		return pflegehinweis;
	}
	public void setPflegehinweis(String pflegehinweis) {
		this.pflegehinweis = pflegehinweis;
	}
	public Date getGepflanzt() {
		return gepflanzt;
	}
	public void setGepflanzt(Date gepflanzt) {
		this.gepflanzt = gepflanzt;
	}
	public Date getGeduengt() {
		return geduengt;
	}
	public void setGeduengt(Date geduengt) {
		this.geduengt = geduengt;
	}
	public Rectangle getRectangle() {
		return rectangle;
	}
	public void setRectangle(Rectangle rectangle) {
		this.rectangle = rectangle;
	}
	public String getBezug() {
		return bezug;
	}
	public void setBezug(String bezug) {
		this.bezug = bezug;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public boolean isKaputt() {
		return kaputt;
	}
	public void setKaputt(boolean kaputt) {
		this.kaputt = kaputt;
	}
	public int getMonatVon() {
		return monatVon;
	}
	public void setMonatVon(int monatVon) {
		this.monatVon = monatVon;
	}
	public int getMonatBis() {
		return monatBis;
	}
	public void setMonatBis(int monatBis) {
		this.monatBis = monatBis;
	}
	@Override
	public String toString() {
		Controller controller = Controller.getInstance();
		return this.botanischerName + ";" + 
			   this.deutscherName + ";" + 
			   this.sorte + ";" + 
			   Integer.toString(this.bluetenFarbe.getRed()) + ";" +
			   Integer.toString(this.bluetenFarbe.getGreen()) + ";" +
			   Integer.toString(this.bluetenFarbe.getBlue()) + ";" + 
			   this.pflegehinweis + ";" + 
			   this.comment + ";" +
				this.bezug + ";" +
			   (this.gepflanzt!=null?controller.df.format(this.gepflanzt):controller.df.format(new Date(0))) + ";" + 
			   (this.geduengt!=null?controller.df.format(this.geduengt):controller.df.format(new Date(0))) + ";" + 
			   this.imagePath + ";" +
			   (int)this.rectangle.getX() + ";" +
			   (int)this.rectangle.getY()+ ";" +
			   rectangle.width + ";" +
			   rectangle.height + ";" +
			   this.isKaputt() + ";" +
			   this.monatVon + ";" +
			   this.monatBis;
	}
	
	/**
	 * Constructor for TestCase
	 */
	public Plant() {
		
		this.botanischerName = "bla";
		   this.deutscherName = "bla"; 
		   this.sorte = "bla";
		   this.bluetenFarbe = Color.black; 
		   this.pflegehinweis = "bla"; 
		   this.gepflanzt = new Date(2011,1,2); 
		   this.geduengt = new Date(2011,1,2); 
		   this.rectangle = new Rectangle();
	}
	
	public Plant(String botanischerName, String deutscherName, String sorte,
			Color bluetenFarbe, String pflegehinweis, String comment, String bezug, Date gepflanzt,
			Date geduengt,String imagePath,Rectangle rectangle, boolean kaputt, int monatVon, int monatBis) {
		super();
		this.botanischerName = botanischerName;
		this.deutscherName = deutscherName;
		this.sorte = sorte;
		this.bluetenFarbe = bluetenFarbe;
		this.pflegehinweis = pflegehinweis;
		this.comment = comment;
		this.bezug = bezug;
		this.gepflanzt = gepflanzt;
		this.geduengt = geduengt;
		this.imagePath = imagePath;
		this.rectangle = rectangle;
		this.kaputt = kaputt;
		this.monatVon = monatVon;
		this.monatBis = monatBis;
	}
	
	
}
