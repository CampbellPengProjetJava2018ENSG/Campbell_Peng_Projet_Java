package tamagotchi;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

public class MyApplication extends JFrame{
	// on fait heriter notre classe de JFrame (fenetre graphique)
	// pour pouvoir personnaliser son comportement (surcharge)
	
	public Partie partie;
	
	/**
	 * On separe le constructeur du code d’initialisation des parametres graphiques
	 * on passe aussi en argument la partie courante pour accéder aux informations du fichier de sauvegarde et du nombre de tours.
	 */	
	public MyApplication(Partie partie){
		this.partie = partie; // on met en argument la partie actuelle, avec les informations du tamagotchi
		this.init();
	}
	
	/**
	 * surcharge de la classe JFrame pour afficher le tamagotchi. On affiche ici les informations du tamagotchi 
	 * et on rajoute le Panel à l'application.
	 */
	private void init(){
		
		MyPanel mainPanel = new MyPanel(partie); // on instancie un nouveal objet MyPanel
		this.setContentPane(mainPanel); // on redefinit le conteneur principal de notre fenetre

		
		this.setLocationRelativeTo(null); // positionnement centre par rapport a l’ecran
		this.setTitle("Tamagotchi"); // titre de la fenetre
		
	    JLabel jlabel = new JLabel("Nom : "+partie.tama.surnom + "  N° partie : " + partie.nom_partie +"  Tour n° : "+partie.nombre_tours);
	    jlabel.setFont(new Font("Dialog",1,18)); // on rajoute des infos sur le tamagotchi en haut de la fenetre
        
	    mainPanel.add(jlabel);
	    mainPanel.setBorder(new LineBorder(Color.BLACK));
	    this.setSize(500, 600);
	     
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setVisible(true);
	    
	}

	
}