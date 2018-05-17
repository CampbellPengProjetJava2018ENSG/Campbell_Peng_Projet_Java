package tamagotchi;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Paint;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class MyPanel extends JPanel{
	
	public Image image;
	public Partie partie;
	
	/**
	 * On separe le constructeur du code d’initialisation des parametres graphiques
	 * on passe en argument la partie courante pour accéder aux informations du tamagotchi.
	 */	
	public MyPanel(Partie partie){
		this.partie = partie;
	}
	
	
	/**
	 * fonction qui renvoie la couleur correspondant au pourcentage de 'remplissage' d'une valeur. 
	 * une barre remplie est bleue, elle devient verte puis orange puis rouge en se vidant.

	 * @param  max   la valeur maximale pouvant être atteinte par l'attribut
	 * @param  valeur   la valeur de l'attribut
	 * @return la couleur correspondant au pourcentage de remplissage
	 */
	public Paint couleur_barre(int max, int valeur){

		
		if (valeur >= (7*max/8)){
			return Color.BLUE;	
		}
		if (valeur >= (max/2)){
			return Color.GREEN;	
		}
		if (valeur >= max/4){
			return Color.ORANGE;
		}
		return Color.RED;
	}
	
	
	/**
	 * surcharge de la classe JPanel pour dessiner les cadres de l'affichage graphique.
	 * on importe aussi des photos depuis un fichier prérempli, "photobank".
	 * on peut alors ajouter différentes photos du tamagotchi en fonction de l'avancement de la partie,
	 * et mettre a jour les barres des attributs pour représenter l'état du tamagotchi.
	 * 
	 * @param  g objet de java2d permettant de manipuler des graphismes.
	 */
	@Override
	public void paintComponent(Graphics g) { // on redefinit la methode paintComponent, qui precise la facon dont le
		super.paintComponent(g); // Appel de la methode paintComponent de la classe mere
		int w = this.getWidth(); // on recupere la largeur du conteneur
		int h = this.getHeight(); // on recupere sa hauteur
		
		
		// Graphics est un objet fourni par le systeme qui est utilise pour dessiner les composant du conteneur
		Graphics2D g2d = (Graphics2D) g; // on cast en Graphics2D, objet permettant des manipulations plus evoluees
		
		

		// IMAGE DU FOND (OPTIONS)
		ImageIcon options = new ImageIcon("photobank/options.png"); // chemin vers l’image
		this.image = options.getImage(); // on recupere l’Image de l’icone
				
		g2d.drawImage(this.image, 0,0, this.image.getWidth(null), this.image.getHeight(null), null); // on affiche l’image sans redimentionnement, en haut a gauche
		g2d.setPaint(Color.BLACK);
		g.fillRect(10,580,480,10); // barre du bas
			
		
		// AFFICHAGE DES STATS (BARRES DE VIE)
		int[] attributs = {partie.tama.bonheur, partie.tama.education, partie.tama.energie,partie.tama.sante,partie.tama.satiete};
		int y_decale = 50;
		int decalage = 26;
		for (int a :attributs){
			g2d.setPaint(Color.black); // cadre
			g.fillRect(121,y_decale-4 , 308, 8+(decalage/2)); // cadre noir (contour)
			g2d.setPaint(Color.white); 
			g.fillRect(125,y_decale,300,decalage/2); // cadre blanc (interieur)
			g2d.setPaint(couleur_barre(200, a));
			g.fillRect(125, y_decale, 3*a/2 , decalage/2); // barre de vie
			y_decale += decalage;
		}
	
		
		//IMAGE DU FOND (STATS)
		ImageIcon stats= new ImageIcon("photobank/stats.png"); // chemin vers l’image
		this.image = stats.getImage(); // on recupere l’Image de l’icone
		
		g2d.drawImage(this.image, 5, 45,(int)(this.image.getWidth(null) * 0.4),
				(int)(this.image.getHeight(null) * 0.4),null); // on affiche l’image un peu décalée en haut à gauche, en .4 de taille

		

		
		// IMAGE DU TAMAGOTCHI (MOKEY)
		// les differentes images correspondent aux differents etats du tamagotchi
		ImageIcon tama = new ImageIcon("photobank/neutre.png"); // chemin vers l’image
		
		if (partie.tama.etat.equals("mort")){
			tama = new ImageIcon("photobank/mort.png"); // chemin vers l’image
		}
		if (partie.tama.etat.equals("joie")){
			tama = new ImageIcon("photobank/content.png"); // chemin vers l’image
		}
		if (partie.tama.etat.equals("sommeil")){
			tama = new ImageIcon("photobank/endormi.png"); // chemin vers l’image
		}
		if (partie.tama.etat.equals("confus")){
			tama = new ImageIcon("photobank/confus.png"); // chemin vers l’image
		}
		this.image = tama.getImage(); // on recupere l’Image de l’icone
		if (partie.tama.etat.equals("ascend")){
			tama = new ImageIcon("photobank/boum.jpg"); // chemin vers l’image
			this.image = tama.getImage(); // on recupere l’Image de l’icone
			g2d.drawImage(this.image
					,w/2 - (int)(this.image.getWidth(null)*0.2)
					,h/2 - (int)(this.image.getHeight(null)*0.2)
					,(int)(this.image.getWidth(null)*0.4), // on reduit l'image de l'explosion (trop grande)
					(int)(this.image.getHeight(null)*0.4),null);
		}
		else{
			g2d.drawImage(this.image,-5,+15,(int)(this.image.getWidth(null)),
			(int)(this.image.getHeight(null)),null);
		}
		
		// credits de l'image d'explosion : pngtree
	}
}
