package tamagotchi;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Launcher {
	
	//Afficher graphiquement le menu
	//User a le choix entre creer une partie et charger une partie
	
	
	
	/**
	 * charge une partie du fichier parties_sauvegardees.
	 * 
	 * L'utilisateur entre un chiffre x entre 1 et 9, on charge ensuite le fichier 'x.txt'.
	 * si ce fichier existe, on lit ses informations avec FileReader et on les envoie
	 * dans le constructeur de la classe Partie.
	 * 
	 * 
	 * @return une partie chargée si c'est possible, null sinon.
	 */
	public Partie creer_partie() {
		
		Scanner reader = new Scanner(System.in);  // Reading from System.in
		int numero = -1;
		do {
			System.out.println("Numero (entre 1 et 9) de la nouvelle partie (la partie deja presente sera ecrasee) : ");
			numero = reader.nextInt();
		}while (numero<1 || numero>9);
		
		
		System.out.println("Creez un surnom pour votre tamagotchi : ");
		String surnom = reader.next();

		return new Partie(numero,surnom);
	}

	
	/**
	 * charge une partie du fichier parties_sauvegardees.
	 * 
	 * L'utilisateur entre un chiffre x entre 1 et 9, on charge ensuite le fichier 'x.txt'.
	 * si ce fichier existe, on appelle la fonction de chargement de la classe Partie, 
	 * qui charge le fichier et 'remplit' les arguments
	 * 
	 * 
	 * 
	 * @return une partie chargée si c'est possible, null sinon.
	 */
	public Partie charger_partie() {
		
		Scanner reader = new Scanner(System.in);  // Reading from System.in
		int num = -1;
		do{
			System.out.println("Numero de la partie que vous voulez charger (1-9) : ");//entree du numero de partie
			num = reader.nextInt();
		}while(num<1 || num>9);

		BufferedReader br = null;
		
		try {
			br = new BufferedReader(new FileReader("parties_sauvegardees/"+num+".txt"));
			
		} catch (FileNotFoundException e) {
			System.out.println("Il ne peut pas y avoir plus de 9 sauvegardes !");
			return null;
		}

		try {
			if (br.readLine() == null) {
			    System.out.println("Cette partie n'existe pas encore"); // si la partie n'existe pas
				return null;
			}
			else {
				return Partie.charger(num);
			}
		} catch (IOException e) {
			return null;
		}	
		
	}
	
	
	
	public static void main(String[] args) {
		
		Launcher l = new Launcher();
		
		//Partie test = l.creer_partie();
		//test.enregistrer();// on sauvegarde la nouvelle partie directement
		
		
		Partie partie = null;
		Scanner reader = null;
		int numero = -1;
		
		do{
			do{
				System.out.println("Voulez vous charger une partie (0) ou en creer une (1) ?");
				reader = new Scanner(System.in);  // Reading from System.in
				numero = reader.nextInt();
			}while ((numero != 0) && (numero != 1));
			if (numero == 0){
				partie = l.charger_partie();
			}
			if (numero == 1){
				partie = l.creer_partie();
				partie.enregistrer();
			}
		}while (partie == null);
		
		System.out.println("");
		System.out.println("#########################");
		System.out.println("#                       #");
		System.out.println("#  T A M A G O T C H I  #");
		System.out.println("#                       #");		
		System.out.println("#########################");
		System.out.println("");
		
		do {
			MyApplication application = new MyApplication(partie); // affichage graphique
			partie.entree_temps();
		}while (0 == partie.fin_de_tour());
		
		MyApplication fin = new MyApplication(partie); // ecran de fin
		
		System.out.println("");
		System.out.println("#######################");
		System.out.println("#                     #");
		System.out.println("#  G A M E   O V E R  #");
		System.out.println("#                     #");		
		System.out.println("#######################");
		System.out.println("");
		
	}
	
}
