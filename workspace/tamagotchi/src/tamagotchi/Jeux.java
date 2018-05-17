package tamagotchi;

import java.util.Scanner;

public class Jeux {

	
	/**
	 * Methode qui demande à quel jeu l'utilisateur veut jouer, puis charge le jeu correspondant.
	 * 
	 * 
	 * @param  tama  le tamagotchi qui va jouer
	 */
	public void jouer(Tamagotchi tama) {
		//Input le nom du jeu
		
		Scanner reader = new Scanner(System.in);  // Reading from System.in
		System.out.println("Voulez-vous jouer au blackjack ou morpion ? ");
		String jeu = reader.next();
		
		//Ouvrir le jeu (sous-classe) correspondant
		
		switch(jeu) {
			case "morpion" :
				Morpion.joue(tama);
				break;
			case "blackjack" : 
				Blackjack.joue(tama);
				break;
			default :
				System.out.println("dsl il n'y a que blackjack et morpion");
		}
	}	
}
