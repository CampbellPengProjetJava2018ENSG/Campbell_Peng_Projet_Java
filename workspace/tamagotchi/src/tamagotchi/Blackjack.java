package tamagotchi;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import tamagotchi.Deck;

public class Blackjack {
	
	public static String vainqueur;
	
	
	/**
	 * cette fonction calcule les 'points' d'une main de blackjack.
	 * 
	 * en clair : si controle == false, ce programme de demande rien a l'utilisateur 
	 * et calcule le score minimum de la main ( as = 1 par defaut). 
	 * si controle == true, on demande à l'utilisateur si l'As en question vaut 1 ou 11.
	 * En fin de tour, cette fonction est appellee mais ne va pas demander la valeur de l'as.
	 * Elle ne prend que as = 1 par defaut pour controler si le score minimum est >21.
	 * 
	 * @param  controle  active le "controle" sur la valeur de l'As.
	 * 
	 * @param  main liste de cartes en main.
	 * @return somme le score de la main entree en parametres.
	 */
	public static int somme(List<Carte> main, boolean controle) {
		int somme = 0;
		for (Carte c :main) {
			if (c.nom == "As" && controle == true) {
				Scanner reader = new Scanner(System.in);
				int choix;
				do{
					System.out.println("Quelle valeur (1 ou 11) donner a cet "+ c+"?");
					choix = reader.nextInt();
				}while(choix != 11 && choix != 1);
			somme += choix;
			}else {
				somme += c.valeur;
			}
		}
		return somme;
	}
	
	
	/**
	 * effectue un 'tour' = une main de blackjack.
	 * le tour se termine par une defaite si trop de points ont ete accumules, 
	 * ou alors si le score du tamagotchi est superieur au joueur. 
	 * le tamagotchi pioche un nombre au hasard, ce jeu est un peu trop complique pour lui..
	 *
	 * 
	 * @param  tama  le tamagotchi avec qui on joue aux cartes.
	 */
	public static void tour(Tamagotchi tama) {
		/* joue un 'tour' = une main de blackjack.
		* le tour se termine par une defaite si trop de points ont ete accumules, 
		* ou alors si le score du tamagotchi est superieur au joueur. 
		* le tamagotchi pioche un nombre au hasard, ce jeu est un peu trop complique pour lui..
		* 
		*/
		
		// DEBUT DU TOUR : initialisation du deck et de quelques variables
		System.out.println("");
		System.out.println("###############################");
		System.out.println("#                             #");
		System.out.println("#  D E B U T   D E   T O U R  #");
		System.out.println("#                             #");		
		System.out.println("###############################");
		System.out.println("");
		
		Deck d = new Deck();
		List<Carte> main = new ArrayList<Carte>();
		main.add(d.piocher()); // on pioche 2 cartes
		main.add(d.piocher());
		System.out.println(main);
		String choix = "0";
		
		// CHOIX SUCCESSIFS DU JOUEUR
		while (choix != "F") {
			do {
				System.out.println("P : Piocher une carte en plus");
				System.out.println("A : Afficher la valeur en point de chaque carte");
				System.out.println("M : Afficher votre main");
				System.out.println("F : Finir cette main");
				System.out.println("Faites votre choix : ");
				Scanner reader = new Scanner(System.in);
				choix = reader.nextLine();
			}while ((!choix.equals("P")) && (!choix.equals("A")) && (!choix.equals("M")) && (!choix.equals("F")));
		
			if (choix.equals("P")) {
				System.out.println("");
				System.out.println("#################");
				System.out.println("#               #");
				System.out.println("#  P I O C H E  #");
				System.out.println("#               #");		
				System.out.println("#################");
				System.out.println("");
				main.add(d.piocher());
				System.out.println(main);
				if (somme(main,false) > 21) // detection de fin de tour
					choix = "F";
				}
			
			if (choix.equals("A")) {
				System.out.println("");
				System.out.println("#################");
				System.out.println("#               #");
				System.out.println("#  P O I N T S  #");
				System.out.println("#               #");		
				System.out.println("#################");
				System.out.println("");
				System.out.println("De 2 a 10 : la valeur en point vaut le nombre sur la carte. (4 = 4pts, etc..)");
				System.out.println("Toutes les tetes valent 10 points (R = D = V = 10pts)");
				System.out.println("Un as vaut 1 ou bien 11, a vous de faire le bon choix!\n");
			}
			
			if (choix.equals("M")) {
				System.out.println("");
				System.out.println("#########################");
				System.out.println("#                       #");
				System.out.println("#  V O T R E   M A I N  #");
				System.out.println("#                       #");		
				System.out.println("#########################");
				System.out.println("");
				System.out.println(main);
			}
			
			if (choix.equals("F")) {
				choix = "F";
			}
		}
		
		System.out.println("");
		System.out.println("###########################");
		System.out.println("#                         #");
		System.out.println("#  F I N   D E   T O U R  #");
		System.out.println("#                         #");		
		System.out.println("###########################");
		System.out.println("");
		
		// FIN DU TOUR
		int score_joueur = somme(main,true);
		System.out.println("Votre main vaut...\n"+score_joueur+" points !");
		if (score_joueur > 21) {
			System.out.println("Dommage.. c'est perdu !!");
			vainqueur = "tama";
			
		}
		if (score_joueur == 21) {
			System.out.println("Blackjack!! C'est gagne !!");
			vainqueur = "joueur";
			
		}
		if (score_joueur < 21) {
			System.out.println(tama.surnom + " a pioche....");
			int score_tama = ThreadLocalRandom.current().nextInt(14,20);
			System.out.println(score_tama+" points !!");
			if (score_tama < score_joueur ) {
				System.out.println("Victoire !!!!");
				vainqueur = "joueur";
				
			}
			else if (score_tama == score_joueur) {
				System.out.println("Match nul !");
				vainqueur = "personne";
				
			}
			else {
				System.out.println(tama.surnom + " est trop fort.e !!!");
				vainqueur = "tama";
				
			}
		}

	}
	
	
	/**
	 * lance une session de blackjack (qui peut comporter plusieurs mains)
	 * cette methode appelle la methode tour().
	 * apres le tour le joueur peut choisir de rejouer.
	 * une fois la session terminee le nombre total de victoires et de defaites est affiche.
	 * 
	 * @param  tama  le tamagotchi avec qui on joue aux cartes.
	 */
	public static void joue(Tamagotchi tama) {
		
		System.out.println("");
		System.out.println("#######################");
		System.out.println("#                     #");
		System.out.println("#  B L A C K J A C K  #");
		System.out.println("#                     #");		
		System.out.println("#######################");
		System.out.println("");
		
		System.out.println("On commence par piocher 2 cartes. ");
		System.out.println("Le but est d'arriver a 21 points sans passer au-dessus !!");
		
		String choix;
		Scanner reader = new Scanner(System.in);
		do {
			System.out.println("On commence ? (Entrez Y)");
			choix = reader.next();
		}while (!choix.equals("Y"));
		
		
		int victoires = 0;
		int defaites = 0;
		do {
			tour(tama);
			if (vainqueur == "joueur") {
				victoires ++;
			}
			else if(vainqueur == "tama") {
				defaites ++;
			}
			System.out.println("On recommence ? (Y pour rejouer, toute autre entree pour quitter)");
			choix = reader.next();
		}while(choix.equals("Y"));
		
		System.out.println("");
		System.out.println("#######################");
		System.out.println("#                     #");
		System.out.println("#  R E S U L T A T S  #");
		System.out.println("#                     #");		
		System.out.println("#######################");
		System.out.println("");
		
		System.out.println("Victoires contre "+ tama.surnom + ": "+ victoires);
		System.out.println("Defaites contre "+ tama.surnom + ": "+defaites);
	}
}
