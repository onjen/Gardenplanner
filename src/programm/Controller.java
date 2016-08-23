package programm;

import java.awt.Image;
import java.awt.Rectangle;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JFileChooser;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import java.lang.Math;

public class Controller {

	private Collection<Plant> plants;
	private Collection<Plant> plantsToShow = new ArrayList<Plant>();
	private Collection<Plant> plantsSelected = new ArrayList<Plant>();
    private double zoomFactor = 1.2;
	private int zoomstufe;
	private DrawingPane drawingPane;
    private JScrollPane scrollPane;
	private StatusBar statusBar;
    private Dialog pflanzenDialog;
    public boolean grundrissGeladen;
    private boolean filtered = false;
    private Image bgImage = null;
    private JFileChooser fc;
    public int imageWidth;
    public int imageHeight;
    public int quadratBreite = 15;
    private int mouseX;
    private int mouseY;
    private JPopupMenu popup;
    private MainWindow mw;
	public final DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	private int newImageHeight;
	private int newImageWidth;
	private Plant plantToEdit;
	private Plant plantToCopy;
	private Plant plantToResize;
	private Plant plantToMove;
	private String actualFilterColor;
	private Rectangle selectRect;
	private String filePath;
	private Filter filter;
    
    /**
     * Generate only one instance of class Controller
     */
	private static Controller instance;
	public synchronized static Controller getInstance() {
		if (instance == null) {
			instance = new Controller();
		}
		return instance;
	}
	public double convertToBasis(double coordinate) {
		if (zoomstufe < 0) {
			return (coordinate * Math.pow(zoomFactor , zoomstufe));
		}
		else { return (coordinate * Math.pow(zoomFactor , zoomstufe)); }
	}
	public double convertFromBasis(double coordinate) {
				if (zoomstufe < 0) {
					return (coordinate / Math.pow(zoomFactor , zoomstufe));
				}
				else { return (coordinate / Math.pow(zoomFactor , zoomstufe)); }
	}
	
	// ---------------------------------- GETTER UND SETTER ---------------------------- //
	public Collection<Plant> getPlants() {
		return plants;
	}
	public void setPlants(Collection<Plant> plants) {
		this.plants = plants;
	}
	public int getZoomstufe() {
		return zoomstufe;
	}
	public void setZoomstufe(int zoomstufe) {
		this.zoomstufe = zoomstufe;
	}
	public double getZoomFactor() {
		return zoomFactor;
	}
	public void setZoomFactor(double zoomFactor) {
		this.zoomFactor = zoomFactor;
	}
	public DrawingPane getDrawingPane() {
		return drawingPane;
	}
	public void setDrawingPane(DrawingPane drawingPane) {
		this.drawingPane = drawingPane;
	}
	public JScrollPane getScrollPane() {
		return scrollPane;
	}
	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}
	public StatusBar getStatusBar() {
		return statusBar;
	}
	public void setStatusBar(StatusBar statusBar) {
		this.statusBar = statusBar;
	}
	public Dialog getPflanzenDialog() {
		return pflanzenDialog;
	}
	public void setPflanzenDialog(Dialog pflanzenDialog) {
		this.pflanzenDialog = pflanzenDialog;
	}
	public boolean isGrundrissGeladen() {
		return grundrissGeladen;
	}
	public void setGrundrissGeladen(boolean grundrissGeladen) {
		this.grundrissGeladen = grundrissGeladen;
	}
	public Image getBgImage() {
		return bgImage;
	}
	public void setBgImage(Image bgImage) {
		this.bgImage = bgImage;
	}
	public JFileChooser getFc() {
		return fc;
	}
	public void setFc(JFileChooser fc) {
		this.fc = fc;
	}
	public int getImageWidth() {
		return imageWidth;
	}
	public void setImageWidth(int imageWidth) {
		this.imageWidth = imageWidth;
	}
	public int getImageHeight() {
		return imageHeight;
	}
	public void setImageHeight(int imageHeight) {
		this.imageHeight = imageHeight;
	}
	public int getQuadratBreite() {
		return quadratBreite;
	}
	public void setQuadratBreite(int quadratBreite) {
		this.quadratBreite = quadratBreite;
	}
	public int getMouseX() {
		return mouseX;
	}
	public void setMouseX(int mouseX) {
		this.mouseX = mouseX;
	}
	public int getMouseY() {
		return mouseY;
	}
	public void setMouseY(int mouseY) {
		this.mouseY = mouseY;
	}
	public JPopupMenu getPopup() {
		return popup;
	}
	public void setPopup(JPopupMenu popup) {
		this.popup = popup;
	}
	public MainWindow getMw() {
		return mw;
	}
	public void setMw(MainWindow mw) {
		this.mw = mw;
	}
	public static void setInstance(Controller instance) {
		Controller.instance = instance;
	}
	//Muss extra damit der Text im Popup passend angezeigt wird!
	public Plant getPlantToCopy() {
		return plantToCopy;
	}
	//Muss extra damit der Text im Popup passend angezeigt wird!
	public void setPlantToCopy(Plant plantToCopy) {
		this.plantToCopy = plantToCopy;
	}
	public int getNewImageHeight() {
		return newImageHeight;
	}
	public void setNewImageHeight(int newImageHeight) {
		this.newImageHeight = newImageHeight;
	}
	public int getNewImageWidth() {
		return newImageWidth;
	}
	public void setNewImageWidth(int newImageWidth) {
		this.newImageWidth = newImageWidth;
	}
	public Plant getPlantToEdit() {
		return plantToEdit;
	}
	public void setPlantToEdit(Plant plantToEdit) {
		this.plantToEdit = plantToEdit;
	}
	public Collection<Plant> getPlantsToShow() {
		return plantsToShow;
	}
	public void setFilteredPlants(Collection<Plant> plantsToShow) {
		this.plantsToShow = plantsToShow;
	}
	public boolean isFiltered() {
		return filtered;
	}
	public void setFiltered(boolean filtered) {
		this.filtered = filtered;
	}
	public String getActualFilterColor() {
		return actualFilterColor;
	}
	public void setActualFilterColor(String actualFilterColor) {
		this.actualFilterColor = actualFilterColor;
	}
	public Plant getPlantToResize() {
		return plantToResize;
	}
	public void setPlantToResize(Plant plantToResize) {
		this.plantToResize = plantToResize;
	}
	public Rectangle getSelectRect() {
		return selectRect;
	}
	public void setSelectRect(Rectangle selectRect) {
		this.selectRect = selectRect;
	}
	public Collection<Plant> getPlantsSelected() {
		return plantsSelected;
	}
	public void setPlantsSelected(Collection<Plant> plantsSelected) {
		this.plantsSelected = plantsSelected;
	}
	public Plant getPlantToMove() {
		return plantToMove;
	}
	public void setPlantToMove(Plant plantToMove) {
		this.plantToMove = plantToMove;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public Filter getFilter() {
		return filter;
	}
	public void setFilter(Filter filter) {
		this.filter = filter;
	}	
}
