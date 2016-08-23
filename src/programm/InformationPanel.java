package programm;

import java.awt.Color;
import java.io.IOException;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import java.awt.Font;

public class InformationPanel extends javax.swing.JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = -2799835896219315869L;


	/** Creates new form InformationPanel */
    public InformationPanel() {
        initComponents();
    }

    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        botanNameLabel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        deutschNameLabel = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        sorteLabel = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        colorPanel = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        pflegehinweisLabel = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        gepflanztLabel = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        geduengtLabel = new javax.swing.JLabel();
        imagePanel = new ImagePanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        commentLabel = new javax.swing.JLabel();
        bezugsLabel = new javax.swing.JLabel();
        monatVonLabel = new javax.swing.JLabel();
        monatBisLabel = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel1.setText("Botanischer Name:");

        jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 15));
        jLabel2.setText("Bild:");

        jLabel3.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel3.setText("Deutscher Name:");
        
        jLabel4.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel4.setText("Bezugsquelle:");
        
        jLabel9.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel9.setText("Kommentar:");

        jLabel5.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel5.setText("Sorte:");

        jLabel7.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel7.setText("Blütenfarbe:");

        colorPanel.setBackground(new java.awt.Color(255, 255, 255));
        colorPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout colorPanelLayout = new javax.swing.GroupLayout(colorPanel);
        colorPanel.setLayout(colorPanelLayout);
        colorPanelLayout.setHorizontalGroup(
            colorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 44, Short.MAX_VALUE)
        );
        colorPanelLayout.setVerticalGroup(
            colorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 18, Short.MAX_VALUE)
        );

        jLabel8.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel8.setText("Pflegehinweis:");


        jLabel10.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel10.setText("Gepflanzt am:");


        jLabel12.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel12.setText("Zuletzt gedüngt am:");
        
        lblBltezeit = new JLabel("Blütezeit:");
        lblBltezeit.setFont(new Font("Dialog", Font.BOLD, 15));
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addComponent(jLabel1)
        						.addComponent(botanNameLabel)
        						.addComponent(jLabel3)
        						.addComponent(deutschNameLabel)
        						.addComponent(jLabel5)
        						.addComponent(sorteLabel)
        						.addComponent(pflegehinweisLabel)
        						.addComponent(jLabel8)
        						.addComponent(jLabel4)
        						.addComponent(bezugsLabel))
        					.addGap(20))
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(jLabel9)
        					.addContainerGap(137, Short.MAX_VALUE))
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(commentLabel)
        					.addContainerGap(222, Short.MAX_VALUE))
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(jLabel2)
        					.addContainerGap(190, Short.MAX_VALUE))
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(jLabel7)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(colorPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        					.addContainerGap())
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(monatVonLabel)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(monatBisLabel)
        					.addContainerGap(212, Short.MAX_VALUE))
        				.addGroup(Alignment.TRAILING, layout.createSequentialGroup()
        					.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        						.addComponent(gepflanztLabel, Alignment.LEADING)
        						.addComponent(geduengtLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
        						.addComponent(jLabel10, Alignment.LEADING)
        						.addComponent(jLabel12, Alignment.LEADING)
        						.addComponent(lblBltezeit, Alignment.LEADING)
        						.addComponent(imagePanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE))
        					.addContainerGap())))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(jLabel1)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(botanNameLabel)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(jLabel3)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(deutschNameLabel)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(jLabel5)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(sorteLabel)
        			.addGap(12)
        			.addComponent(jLabel8)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(pflegehinweisLabel)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(jLabel4)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(bezugsLabel)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(jLabel9)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(commentLabel)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(jLabel10)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(gepflanztLabel)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(jLabel12)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(geduengtLabel)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(lblBltezeit)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(monatVonLabel)
        				.addComponent(monatBisLabel))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(jLabel7)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(jLabel2))
        				.addComponent(colorPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(imagePanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addGap(25))
        );
        this.setLayout(layout);
    }


    // Variables declaration - do not modify
    private javax.swing.JLabel botanNameLabel;
    private javax.swing.JLabel deutschNameLabel;
    private javax.swing.JLabel geduengtLabel;
    private javax.swing.JLabel gepflanztLabel;
    private javax.swing.JLabel commentLabel;
    private javax.swing.JLabel bezugsLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel colorPanel;
    private javax.swing.JLabel pflegehinweisLabel;
    private javax.swing.JLabel sorteLabel;
    private ImagePanel imagePanel;
    private javax.swing.JLabel jLabel2;
    private JLabel lblBltezeit;
    private JLabel monatVonLabel;
    private JLabel monatBisLabel;
    private boolean pflanzeBlueht;
    

	public void setBotanName(String botanname) {
		botanNameLabel.setText(botanname);
	}

	public void setDeutschName(String deutschname) {
		deutschNameLabel.setText(deutschname);
	}

	public void setGeduengt(String geduengt) {
		geduengtLabel.setText(geduengt);
	}

	public void setGepflanzt(String gepflanzt) {
		gepflanztLabel.setText(gepflanzt);
	}

	public void setPflegehinweis(String pflegehinweis) {
		pflegehinweisLabel.setText(pflegehinweis);
	}

	public void setComment(String comment) {
		commentLabel.setText(comment);
	}

	public void setBezug(String bezug) {
		bezugsLabel.setText(bezug);
	}

	public void setSorte(String sorte) {
		sorteLabel.setText(sorte);
	}
	
	public void setColor(Color color) {
		colorPanel.setBackground(color);
	}

	public ImagePanel getImagePanel() {
		return imagePanel;
	}

	public void setImagePanel(ImagePanel imagePanel) {
		this.imagePanel = imagePanel;
	}
	public void setImagePanelPicture(String path){
			try {
				imagePanel.showImage(path);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	public void setMonatVon(int monat){
		if (monat == 0) {
			monatVonLabel.setText("Januar   - ");
			pflanzeBlueht = true;
		} else if (monat == 1){
			monatVonLabel.setText("Februar   - ");
			pflanzeBlueht = true;
		} else if (monat == 2){
			monatVonLabel.setText("März   - ");
			pflanzeBlueht = true;
		} else if (monat == 3){
			monatVonLabel.setText("April   - ");
			pflanzeBlueht = true;
		} else if (monat == 4){
			monatVonLabel.setText("Mai   - ");
			pflanzeBlueht = true;
		} else if (monat == 5){
			monatVonLabel.setText("Juni   - ");
			pflanzeBlueht = true;
		} else if (monat == 6){
			monatVonLabel.setText("Juli   - ");
			pflanzeBlueht = true;
		} else if (monat == 7){
			monatVonLabel.setText("August   - ");
			pflanzeBlueht = true;
		} else if (monat == 8){
			monatVonLabel.setText("September   - ");
			pflanzeBlueht = true;
		} else if (monat == 9){
			monatVonLabel.setText("Oktober   - ");
			pflanzeBlueht = true;
		} else if (monat == 10){
			monatVonLabel.setText("November   - ");
			pflanzeBlueht = true;
		} else if (monat == 11){
			monatVonLabel.setText("Dezember   - ");
			pflanzeBlueht = true;
		} else if (monat == 12){
			monatVonLabel.setText("Pflanze blüht nicht");
			pflanzeBlueht = false;
		}
	}
	public void setMonatBis(int monat){
		if (pflanzeBlueht == false){
			monatBisLabel.setText("");
		}else {
			if (monat == 0) {
				monatBisLabel.setText("Januar");
			} else if (monat == 1){
				monatBisLabel.setText("Februar");
			} else if (monat == 2){
				monatBisLabel.setText("März");
			} else if (monat == 3){
				monatBisLabel.setText("April");
			} else if (monat == 4){
				monatBisLabel.setText("Mai");
			} else if (monat == 5){
				monatBisLabel.setText("Juni");
			} else if (monat == 6){
				monatBisLabel.setText("Juli");
			} else if (monat == 7){
				monatBisLabel.setText("August");
			} else if (monat == 8){
				monatBisLabel.setText("September");
			} else if (monat == 9){
				monatBisLabel.setText("Oktober");
			} else if (monat == 10){
				monatBisLabel.setText("November");
			} else if (monat == 11){
				monatBisLabel.setText("Dezember");
			}
		}
	}
}
