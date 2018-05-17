package tamagotchi;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Morpion {
	
	//Initialisation de la grille
	private static int[][] grille = {{0,0,0},{0,0,0},{0,0,0}};
	private static boolean fin = false;

	private static String vainqueur = "";
	
	
	/**
	 * Methode pour jouer au morpion.
	 * <p>
	 * Lance une partie de morpion.
	 * Le joueur doit rentrer les coordonnees de la case ou il veut mettre son numero (1).
	 * Le tamagotchi va ensuite chercher une case vide aleatoirement et y mettre son numero (2).
	 * Puis iteration des deux etapes precedentes jusqu'a ce que la partie soit finie.
	 *
	 * @param  tama le Tamagotchi de la partie
	 */
	public static void joue(Tamagotchi tama) {
		
		System.out.println("");
		System.out.println("###################");
		System.out.println("#                 #");
		System.out.println("#  M O R P I O N  #");
		System.out.println("#                 #");		
		System.out.println("###################");
		System.out.println("");
		
		
		//INTERFACE GRAPHIQUE
		afficher_grille();
		
		while (fin == false) {
			
			boolean a_joue = false;
			boolean tama_a_joue = false;
			
			while (a_joue == false) {
				
				String choix;
				Scanner reader = new Scanner(System.in);
				do{
					System.out.println("Ou placer la croix sur la grille (3,3) ? La reponse est de la forme x,x");
					choix = reader.next();
				}while (!choix.equals("1,1") && !choix.equals("1,2") && !choix.equals("1,3") && !choix.equals("2,1")
						 && !choix.equals("2,2") && !choix.equals("2,3") && !choix.equals("3,1") && !choix.equals("3,2")
						 && !choix.equals("3,3") && !choix.equals(null));
				
				String[] position = choix.split(",");
				int[] pos = {Integer.parseInt(position[0])-1, Integer.parseInt(position[1])-1};
				
				if (grille[pos[0]][pos[1]] != 0) {
					System.out.println("Cette case est deja remplie !");
				}
				else {
					grille[pos[0]][pos[1]] = 1;
					a_joue = true;
					afficher_grille();
				}
				//AFFICHAGE GRAPHIQUE
			}
			
			if (verif_fin() == true) {
				
				fin = true;
				
				if (vainqueur == "personne") {
					System.out.println("Match nul");
				}
				
				if (vainqueur == "joueur") {
					System.out.println("Vous gagnez !!");
				}
				
				if (vainqueur == "tama") {
					System.out.println(tama.surnom +" gagne !");
				}
				break;
			}
			
			while (tama_a_joue == false) {
				
				System.out.println(tama.surnom +" cherche...");
				
				int randX = ThreadLocalRandom.current().nextInt(0,3); //3 exclu
				int randY = ThreadLocalRandom.current().nextInt(0,3); //3 exclu
				
				if (grille[randX][randY] == 0) {
					System.out.println(tama.surnom+" joue!!");
					//On assigne 2 aux moves de tama
					grille[randX][randY] = 2;
					//affichage graphique
					tama_a_joue = true;
					afficher_grille();
				}
				
			}
			
			if (verif_fin() == true) {
				
				if (vainqueur == "personne") {
					System.out.println("Match nul");
				}
				
				if (vainqueur == "joueur") {
					System.out.println("Vous gagnez !!");
				}
				
				if (vainqueur == "tama") {
					System.out.println(tama.surnom+" gagne !");
				}
				break;
			}

		}

	}

	
	/**
	 * Methode qui verifie si la partie est finie.
	 * <p>
	 * Renvoie true si l'un des deux gagne ou que la grille soit remplie.
	 * Renvoie false dans le cas contraire.
	 *
	 * @return  boolean pour savoir si la partie est finie ou non.
	 */
	private static boolean verif_fin() {
		
		if (grille[0][0] == grille[1][0] && grille[1][0]== grille[2][0] && grille[0][0] != 0) {
			fin = true;
			if (grille[0][0] == 1) {
				vainqueur = "joueur";
			}
			else {
				vainqueur = "tama";
			}
		}
		else if (grille[0][1] == grille[1][1] && grille[1][1]== grille[2][1] && grille[0][1] != 0) {
			fin = true;
			if (grille[0][1] == 1) {
				vainqueur = "joueur";
			}
			else {
				vainqueur = "tama";
			}
		}
		else if (grille[0][2] == grille[1][2] && grille[1][2]== grille[2][2] && grille[0][2] != 0) {
			fin = true;
			if (grille[0][2] == 1) {
				vainqueur = "joueur";
			}
			else {
				vainqueur = "tama";
			}
		}
		else if (grille[0][0] == grille[1][1] && grille[1][1]== grille[2][2] && grille[0][0] != 0) {
			fin = true;
			if (grille[0][0] == 1) {
				vainqueur = "joueur";
			}
			else {
				vainqueur = "tama";
			}
		}
		else if (grille[0][2] == grille[1][1] && grille[1][1]== grille[2][0] && grille[0][2] != 0) {
			fin = true;
			if (grille[0][2] == 1) {
				vainqueur = "joueur";
			}
			else {
				vainqueur = "tama";
			}
		}
		else if (grille[0][0] == grille[0][1] && grille[0][1]== grille[0][2] && grille[0][0] != 0) {
			fin = true;
			if (grille[0][0] == 1) {
				vainqueur = "joueur";
			}
			else {
				vainqueur = "tama";
			}
		}
		else if (grille[1][0] == grille[1][1] && grille[1][1]== grille[1][2] && grille[1][0] != 0) {
			fin = true;
			if (grille[1][0] == 1) {
				vainqueur = "joueur";
			}
			else {
				vainqueur = "tama";
			}
		}
		else if (grille[2][0] == grille[2][1] && grille[2][1]== grille[2][2] && grille[2][0] != 0) {
			fin = true;
			if (grille[2][0] == 1) {
				vainqueur = "joueur";
			}
			else {
				vainqueur = "tama";
			}
		}
		else if (case_vide_null() == true) {
			fin = true;
			vainqueur = "personne";
		}
		else {
		}
		return fin;
	}

	
	/**
	 * Methode qui verifie si la grille est remplie.
	 * <p>
	 * Renvoie true si la grille est remplie.
	 * Renvoie false dans le cas contraire.
	 *
	 * @return  boolean pour savoir si la grille est remplie ou non.
	 */
	private static boolean case_vide_null() {
		int k = 0;
		for (int i=0;i<3;i++) {
			for (int j=0;j<3;j++) {
				if (grille[i][j] != 0) {
					k += 1;
				}
			}
		}
		if (k == 9) {
			return true;
		}
		return false;
	}
	
	
	/**
	 * Methode qui affiche la grille dans la console.
	 *
	 */
	private static void afficher_grille() {
		for (int i=0;i<3;i++) {
			for (int j=0;j<3;j++) {
				System.out.print(grille[i][j]+" ");
			}
			System.out.print("\n");
		}
	}

}
