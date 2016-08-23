package programm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.JSlider;
import javax.swing.JLabel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class Filter implements ChangeListener {
	Controller controller = Controller.getInstance();
	private Collection<Plant> plantsToShow = controller.getPlantsToShow();
	private Collection<Plant> plants = controller.getPlants();
	DrawingPane drawingPane = controller.getDrawingPane();
	public final DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	MainWindow mw = controller.getMw();
	private JSlider monatSlider;
	int monat = 0;
	
	public void filterKaputt(){
		controller.setFiltered(true);
		controller.getMw().getAlleEingegangen().setEnabled(false);
		if (plantsToShow != null){
			plantsToShow.clear();
		}
		for (Iterator<Plant> iterator = plants.iterator(); iterator.hasNext();) {
    		Plant plant = (Plant) iterator.next();
    		if (plant.isKaputt()){
    			plantsToShow.add(plant);
    		}
		}
		controller.getMw().getStatusBar().setMessage("Aktuelle Ansicht: Nur Pflanzen die eingegangen sind, werden angezeigt");
		drawingPane.repaint();
	}
	public void filterGeduengt(Date date){
		controller.setFiltered(true);
		if (plantsToShow != null){
			plantsToShow.clear();
		}
		for (Iterator<Plant> iterator = plants.iterator(); iterator.hasNext();) {
    		Plant plant = (Plant) iterator.next();
    		if (plant.getGeduengt().before(date)){
    			plantsToShow.add(plant);
    		}
		}
		controller.getMw().getStatusBar().setMessage("Aktuelle Ansicht: Nur Pflanzen die nach dem " + (date!=null?df.format(date):df.format(new Date(0))) + " nicht mehr gedüngt wurden werden angezeigt");
		drawingPane.repaint();
	}
	public void filterName(String name){
		controller.setFiltered(true);
		if (plantsToShow != null){
			plantsToShow.clear();
		}
		for (Iterator<Plant> iterator = plants.iterator(); iterator.hasNext();) {
    		Plant plant = (Plant) iterator.next();
    		if (plant.getDeutscherName().equalsIgnoreCase(name)){
    			plantsToShow.add(plant);
    		}
		}
		controller.getMw().getStatusBar().setMessage("Aktuelle Ansicht: Nur Pflanzen mit dem deutschen Namen "+ "\"" + name + "\"" + ", werden angezeigt");
		drawingPane.repaint();
	}
	public void filterPlantsYear(int year) {
		controller.setFiltered(true);
		mw.getCustomDisplay().setSelected(true);
		if (plantsToShow != null){
			plantsToShow.clear();
		}
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		for (Iterator<Plant> iterator = plants.iterator(); iterator.hasNext();) {
    		Plant plant = (Plant) iterator.next();
    		date = plant.getGepflanzt();
			c.setTime(date);
			int gepflanztYear = c.get(Calendar.YEAR);
			if(gepflanztYear == year){
				plantsToShow.add(plant);
			}
		}
		controller.getMw().getStatusBar().setMessage("Aktuelle Ansicht: Nur Pflanzen die im Jahr " + year + " gepflanzt wurden, werden angzeigt");
		drawingPane.repaint();
	}
	public void filterPlantsColor(String color){
		controller.setFiltered(true);
		mw.getCustomDisplay().setSelected(true);
		if (plantsToShow != null){
			plantsToShow.clear();
		}
		//ROT
		if (color == "Rot"){
			for (Iterator<Plant> iterator = plants.iterator(); iterator.hasNext();) {
	    		Plant plant = (Plant) iterator.next();
	    		Color plantColor = plant.getBluetenFarbe();
	    		//wenn grün und blau beide groß sind wird es ein helles rot
	    		if (plantColor.getGreen() >= 75 && plantColor.getBlue() >= 75){
	    			if (plantColor.getRed() >= 75 && plantColor.getGreen() <= 225 && plantColor.getBlue() <= 225){
	    				plantsToShow.add(plant);
	    			}
	    		} else {
	    			if (plantColor.getRed() > 51 && plantColor.getGreen() <= 75 && plantColor.getBlue() <= 75){
	    				plantsToShow.add(plant);
	    			}
	    		}
			}
		}
		if (color == "Grün"){
			for (Iterator<Plant> iterator = plants.iterator(); iterator.hasNext();) {
	    		Plant plant = (Plant) iterator.next();
	    		Color plantColor = plant.getBluetenFarbe();
	    		if (plantColor.getRed() >= 75 && plantColor.getBlue() >= 75){
	    			if (plantColor.getGreen() >= 75 && plantColor.getRed() <= 225 && plantColor.getBlue() <= 225){
	    				plantsToShow.add(plant);
	    			}
	    		} else {
	    			if (plantColor.getGreen() >= 51 && plantColor.getRed() <= 175 && plantColor.getBlue() <= 103){
	    				plantsToShow.add(plant);
	    			}
	    		}
			}
		}
		if (color == "Blau"){
			for (Iterator<Plant> iterator = plants.iterator(); iterator.hasNext();) {
	    		Plant plant = (Plant) iterator.next();
	    		Color plantColor = plant.getBluetenFarbe();
	    		if (plantColor.getRed() >= 75 && plantColor.getGreen() >= 75){
	    			if (plantColor.getBlue() >= 75 && plantColor.getRed() <= 225 && plantColor.getGreen() <= 225){
	    				plantsToShow.add(plant);
	    			}
	    		} else {
	    			if (plantColor.getBlue() >= 40 && plantColor.getRed() <= 85 && plantColor.getGreen() <= 110){
	    				plantsToShow.add(plant);
	    			}
	    		}
			}
		}
		if (color == "Gelb"){
			for (Iterator<Plant> iterator = plants.iterator(); iterator.hasNext();) {
	    		Plant plant = (Plant) iterator.next();
	    		Color plantColor = plant.getBluetenFarbe();
	    		if (plantColor.getRed() >= 240 && plantColor.getGreen() >= 240){
	    			if (plantColor.getBlue() <=225){
	    				plantsToShow.add(plant);
	    			}
	    		}
	    		else if (plantColor.getBlue() <= 125 && plantColor.getRed() >= 225 && plantColor.getGreen() >= 225){
	    			plantsToShow.add(plant);
	    		}
			}
		}
		if (color == "Orange"){
			for (Iterator<Plant> iterator = plants.iterator(); iterator.hasNext();) {
	    		Plant plant = (Plant) iterator.next();
	    		Color plantColor = plant.getBluetenFarbe();
	    		if (plantColor.getRed() >= 250 && plantColor.getBlue() <=5){
	    			if (plantColor.getGreen() >= 40 && plantColor.getGreen() <= 205){
	    				plantsToShow.add(plant);
	    			}
	    		}
	    		else{
	    			if (plantColor.getRed() >= 200 && plantColor.getGreen() >= 110 && plantColor.getGreen() <=225 && plantColor.getBlue() <= 125){
	    				plantsToShow.add(plant);
	    			}
	    		}
			}
		}
		if (color == "Lila"){
			for (Iterator<Plant> iterator = plants.iterator(); iterator.hasNext();) {
	    		Plant plant = (Plant) iterator.next();
	    		Color plantColor = plant.getBluetenFarbe();
	    		if (plantColor.getGreen() <= 50 && plantColor.getRed() >= 100 && plantColor.getRed() <= 200 && plantColor.getBlue() >= 100 && plantColor.getBlue() <= 200){
	    			plantsToShow.add(plant);
	    		}
	    		else if (plantColor.getRed() >= 115 && plantColor.getBlue() >= 115 && plantColor.getGreen() <=165){
	    			plantsToShow.add(plant);
	    		}
			}
		}
		if (color == "Türkis"){
			for (Iterator<Plant> iterator = plants.iterator(); iterator.hasNext();) {
	    		Plant plant = (Plant) iterator.next();
	    		Color plantColor = plant.getBluetenFarbe();
	    		if (plantColor.getGreen() >= 200 &&  plantColor.getRed() <= 200 && plantColor.getBlue() >= 150){
	    			plantsToShow.add(plant);
	    		} else if (plantColor.getRed() <= 5 && plantColor.getBlue() <= 205 && plantColor.getBlue() >= 40 && plantColor.getGreen() <= 205 && plantColor.getGreen() >= 40){
	    			plantsToShow.add(plant);
	    		} else if (plantColor.getRed() <= 5 && plantColor.getBlue() >= 50 && plantColor.getGreen() >= 150){
	    			plantsToShow.add(plant);
	    		}
			}
		}
		if (color == "Hell"){
			for (Iterator<Plant> iterator = plants.iterator(); iterator.hasNext();) {
	    		Plant plant = (Plant) iterator.next();
	    		Color plantColor = plant.getBluetenFarbe();
	    		if (plantColor.getGreen() >= 203 &&  plantColor.getRed() >= 203 && plantColor.getBlue() >= 203){
	    			plantsToShow.add(plant);
	    		}
			}
		}
		controller.getMw().getStatusBar().setMessage("Aktuelle Ansicht: Nur Pflanzen mit der Blütenfarbe "+ "\"" + color + "\"" + ", werden angezeigt");
		drawingPane.repaint();
	}
	public void filterBluetezeit(){
		controller.setFiltered(true);
		controller.getMw().getStatusBar().setMessage("Aktuelle Ansicht: Nur Pflanzen die im Januar blühen werden angezeigt!");
		
		//Create the slider.
        monatSlider = new JSlider(JSlider.VERTICAL,0, 11, 0);
        monatSlider.addChangeListener(this);
        monatSlider.setMajorTickSpacing(1);
        monatSlider.setPaintTicks(true);
         
        //Create the label table.
        Hashtable<Integer, JLabel> labelTable =
            new Hashtable<Integer, JLabel>();
        labelTable.put(new Integer( 0 ),
                new JLabel("Januar") );
        labelTable.put(new Integer( 1 ),
                new JLabel("Februar") );
        labelTable.put(new Integer( 2 ),
                new JLabel("März") );
        labelTable.put(new Integer( 3 ),
                new JLabel("April") );
        labelTable.put(new Integer( 4 ),
                new JLabel("Mai") );
        labelTable.put(new Integer( 5 ),
                new JLabel("Juni") );
        labelTable.put(new Integer( 6 ),
                new JLabel("Juli") );
        labelTable.put(new Integer( 7 ),
                new JLabel("August") );
        labelTable.put(new Integer( 8 ),
                new JLabel("September") );
        labelTable.put(new Integer( 9 ),
                new JLabel("Oktober") );
        labelTable.put(new Integer( 10 ),
                new JLabel("November") );
        labelTable.put(new Integer( 11 ),
                new JLabel("Dezember") );
        monatSlider.setLabelTable(labelTable);
        
        controller.getMw().add(monatSlider, BorderLayout.WEST);
 
        monatSlider.setPaintLabels(true);
        monatSlider.setBorder(BorderFactory.createEmptyBorder(0,0,0,10));

        
        if (plantsToShow != null){
			plantsToShow.clear();
		}
        //damit der Januar direkt richtig angezeigt wird
        monat = 0;
        for (Iterator<Plant> iterator = plants.iterator(); iterator.hasNext();) {
    		Plant plant = (Plant) iterator.next();
    		if (plant.getMonatVon() <= plant.getMonatBis()){
    			if (plant.getMonatVon() <= monat && plant.getMonatBis() >= monat){
    				if (plant.getMonatVon() < 12){
    					plantsToShow.add(plant);
    				}
    			}
    		} else {
    			if (monat >= plant.getMonatVon() || monat <= plant.getMonatBis()){
    				if (plant.getMonatVon() < 12){
    					plantsToShow.add(plant);
    				}
    			}
    		}
		}
		drawingPane.repaint();
	}
	@Override
	public void stateChanged(ChangeEvent arg0) {
		monat = monatSlider.getValue();
		if (plantsToShow != null){
			plantsToShow.clear();
		}
		for (Iterator<Plant> iterator = plants.iterator(); iterator.hasNext();) {
    		Plant plant = (Plant) iterator.next();
    		if (plant.getMonatVon() <= plant.getMonatBis()){
    			if (plant.getMonatVon() <= monat && plant.getMonatBis() >= monat){
    				if (plant.getMonatVon() < 12){
    					plantsToShow.add(plant);
    				}
    			}
    		} else {
    			if (monat >= plant.getMonatVon() || monat <= plant.getMonatBis()){
    				if (plant.getMonatVon() < 12){
    					plantsToShow.add(plant);
    				}
    			}
    		}
		}
		drawingPane.repaint();
		
		if (monat == 0) {
			controller.getMw().getStatusBar().setMessage("Aktuelle Ansicht: Nur Pflanzen die im Januar blühen werden angezeigt!");
		} else if (monat == 1){
			controller.getMw().getStatusBar().setMessage("Aktuelle Ansicht: Nur Pflanzen die im Februar blühen werden angezeigt!");
		} else if (monat == 2){
			controller.getMw().getStatusBar().setMessage("Aktuelle Ansicht: Nur Pflanzen die im März blühen werden angezeigt!");
		} else if (monat == 3){
			controller.getMw().getStatusBar().setMessage("Aktuelle Ansicht: Nur Pflanzen die im April blühen werden angezeigt!");
		} else if (monat == 4){
			controller.getMw().getStatusBar().setMessage("Aktuelle Ansicht: Nur Pflanzen die im Mai blühen werden angezeigt!");
		} else if (monat == 5){
			controller.getMw().getStatusBar().setMessage("Aktuelle Ansicht: Nur Pflanzen die im Juni blühen werden angezeigt!");
		} else if (monat == 6){
			controller.getMw().getStatusBar().setMessage("Aktuelle Ansicht: Nur Pflanzen die im Juli blühen werden angezeigt!");
		} else if (monat == 7){
			controller.getMw().getStatusBar().setMessage("Aktuelle Ansicht: Nur Pflanzen die im August blühen werden angezeigt!");
		} else if (monat == 8){
			controller.getMw().getStatusBar().setMessage("Aktuelle Ansicht: Nur Pflanzen die im September blühen werden angezeigt!");
		} else if (monat == 9){
			controller.getMw().getStatusBar().setMessage("Aktuelle Ansicht: Nur Pflanzen die im Oktober blühen werden angezeigt!");
		} else if (monat == 10){
			controller.getMw().getStatusBar().setMessage("Aktuelle Ansicht: Nur Pflanzen die im November blühen werden angezeigt!");
		} else if (monat == 11){
			controller.getMw().getStatusBar().setMessage("Aktuelle Ansicht: Nur Pflanzen die im Dezember blühen werden angezeigt!");
		}
	}
	public JSlider getMonatSlider() {
		return monatSlider;
	}
}
