package tamagotchi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;

public class Partie{
	
	public Tamagotchi tama;
	public int nom_partie; // int(1-9)
	public int nombre_tours = 0;
	
	/**
	 * Methode utilisee lorsque le joueur veut faire apprendre un mot au Tamagotchi
	 * 
	 * Cette methode demande si le joueur veut connaitre la liste des mots deja appris,
	 * puis lui demande le mot qu'il veut lui apprendre.
	 * Dans le cas ou ce mot est deja connu, ou s'il s'agit d'un pluriel/singulier de type +/-s ou al-aux,
	 * la methode affiche que le mot est deja connu.
	 * Dans le cas contraire, le Tamagotchi apprend le mot.
	 *
	 */
	protected void apprendre() {
		
		String choix = "";
		
		do {
			System.out.println("Voulez vous connaitre les mots deja appris ? (Entrez Y ou N)");
			Scanner reader = new Scanner(System.in);
			choix = reader.next();
		}while ((!choix.equals("Y")) && (!choix.equals("N")));
		
		if (choix.equals("Y")) {
			for (String s:tama.mots_appris) {
				System.out.print(s + "; ");
			}
		}
		System.out.println("\nQuel mot voulez-vous apprendre a "+tama.surnom+" ?");
		Scanner reader = new Scanner(System.in);
		String nouveau_mot = reader.next();
		int i = 0;
		for (String s : tama.mots_appris) {
			if (compare_mots(s,nouveau_mot) == true || compare_mots(nouveau_mot,s) == true) {
				System.out.println("Ce mot est deja connu !");
				break;
			}
			i += 1;
		}
		if (i == tama.mots_appris.size()) {
			tama.mots_appris.add(nouveau_mot);
			System.out.println(tama.surnom +" a appris un nouveau mot !!");	
		}
	}
	
	
	/**
	 * Le joueur ecrit un message parmi une liste d'options pour que le Tamagotchi l'execute.
	 *
	 * La methode agit en fonction du message entre par le joueur.
	 * Si le message n'est pas reconnu, le joueur devra ecrire un autre message.
	 * Apres qu'un message soit valide, un tour est fini.
	 *
	 * @param  message   le message du joueur
	 */
	protected void interpreter_message(String message) {
		if (!(tama.etat.equals("sommeil"))) {
			switch (message) {
				case "viande" : 
					tama.satiete += 10;
					tama.etat = "joie";
					break;
				case "legume" :
					tama.satiete +=10;
					tama.sante +=5;
					tama.etat = "neutre";
					break;
				case "bonbon" :
					tama.satiete +=5;
					tama.sante -=5;
					tama.bonheur +=5;
					tama.etat = "joie";
					break;
				case "aspirine" :
					tama.sante += 20;
					tama.bonheur -= 5;
					tama.etat = "neutre";
					break;
				case "dodo" :
					tama.energie += 10;
					tama.etat = "sommeil";
					System.out.println(tama.surnom + " dort !");
					break;
				case "apprendre" :
					apprendre();
					tama.bonheur += 5;
					tama.education += 10;
					tama.etat = "joie";
					break;
				case "jouer" :
					jouer(tama);
					tama.bonheur += 20;
					tama.etat = "joie";
					break;
				case "save" :
					this.enregistrer();
					break;
				case "quit" :
					this.enregistrer();// save and quit?
					System.exit(0); // exit, everything's ok
					break;
				default :
					System.out.println("Message non valide");
					tama.etat = "confus";
					break;
			}
		}
		else {
			if (message.equals("eveil")) {			this.tama.etat = "sommeil";
				System.out.println("Vous avez reveille "+tama.surnom);
				tama.etat = "neutre";
			}
			else{
				System.out.println(tama.surnom + " dort !");
				tama.etat = "sommeil";
			}
		}
	}
	
	
	/**
	 * Methode qui appelle la classe Jeux
	 * <p>
	 * Methode utilisee lorsque le joueur entre comme message "jouer".
	 * La classe Jeux est alors appelee.
	 *
	 * @param  tama   le tamagotchi de la partie
	 */
	public void jouer(Tamagotchi tama) {
		Jeux jeu = new Jeux();
		jeu.jouer(tama);
	}
	
	
	/**
	 * Methode de gestion du "temps réel".
	 * Cette méthode permet d'attendre une entrée d' l'utilisateur pendant un temps donné (ici 1 minute)
	 * et d'effectuer une action à la fin de cette minute si aucune entrée n'a eu lieu.
	 * Ici on déclenche la fin du tour si rien n'est entré après 1 minute.
	 *
	 */
	public void entree_temps(){
		this.nombre_tours+=1;
		System.out.println("\nEntrez un message >");
		float m = 1f; // 1min à attendre
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		long startTime = System.currentTimeMillis();
		try {
			while ((System.currentTimeMillis() - startTime) < m *60*1000 // on attend m minutes
			        && !in.ready()) {
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			if (in.ready()) {
			    try {
			    	// si un message est entre
			    	String message = in.readLine();
					System.out.println("You entered: " + message);
					interpreter_message(message);
					return;
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				// si rien n'est entre
				if (tama.etat.equals("sommeil")){
					tama.etat = "sommeil";
					return;
				}
				else{
					tama.etat = "neutre";
					return;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Methode appelee lorsqu'il y a fin du tour.
	 * <p>
	 * Les attributs du Tamagotchi diminuent en valeur.
	 * La methode verifie si le Tamagotchi dort (auquel cas son energie augmente).
	 * Elle verifie egalement s'il est mort (lorsqu'un attribut descend à 0 ou depasse 200).
	 *
	 * @return  -1,0 ou 1 qui signale au main du Launcher si la partie est terminee
	 */
	protected int fin_de_tour(){
		
		tama.sante -= 2;
		tama.satiete -= 2;
		tama.bonheur -= 2;
		tama.energie -= 2;
		tama.education -= 2;
		
		if (tama.etat.equals("sommeil")) {
			tama.energie += 10;
		}
		
		if (tama.bonheur <= 0 || tama.education <= 0 || tama.energie <= 0 || tama.sante <= 0 || tama.satiete <= 0) {
			System.out.println(tama.surnom + " est mort.e :(");
			this.tama.etat = "mort";
			return 1;
		}
		
		if (tama.bonheur >= 200 || tama.energie >= 200 || tama.sante >= 200 || tama.satiete >= 200) {
			System.out.println(tama.surnom + " ne se sent pas tres bien....");
			System.out.println("BOUM");
			this.tama.etat = "ascend";
			return -1;
		}
		return 0;

		
	}
	
	
	/**
	 * méthode de chargement de fichier. On utilise les méthodes de Path et Files pour
	 * ouvrir un fichier de sauvegarde placé dans un dossier "parties_sauvegardees".
	 * Si le chargement est réussi, on renvoie la partie qui est 'mise à jour', sinon on renvoie null.
	 * 
	 * 
	 * @param  nom_partie  le nom correspondant au fichier 
	 * @return   la partie chargée à partir du fichier passé en argument.
	 */
	public static Partie charger(int nom_partie){
		Path path = Paths.get("parties_sauvegardees/"+nom_partie+".txt");
		try(BufferedReader reader = Files.newBufferedReader(path)){
			String line = reader.readLine();
			if (line != null){ // si le fichier
				String[] data = line.split("/"); // premiere ligne : les attributs
				Partie p = new Partie(nom_partie, data[0]);
				p.tama = new Tamagotchi(data[0],Integer.parseInt(data[1]), // lecture des attributs du tamagotchi
						Integer.parseInt(data[2]),Integer.parseInt(data[3]),
						Integer.parseInt(data[4]),Integer.parseInt(data[5]));
				p.nombre_tours = Integer.parseInt(data[6]); // nombre de tours
				
				line = reader.readLine(); // deuxieme ligne : les mots appris
				p.tama.mots_appris = new ArrayList<String>(); // on reinitialise la liste des mots appris
				String[] words = line.split("/");
				for (String s : words){
					p.tama.mots_appris.add(s); // on charge tous les mots appris
				}
				
				System.out.println("Chargement reussi!");
				return p;
			}else{
				System.out.println("fichier vide!!");
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
		return null; // erreur si on arrive ici
	}
	
	
	/**
	 * méthode d'enregistrement de fichier. On utilise les méthodes de Path et Files pour
	 * noter les données de la partie dans le fichier correpondant.
	 * 
	 * 
	 * @param  nom_partie  le nom correspondant au fichier 
	 * @return   la partie chargée à partir du fichier passé en argument.
	 */
	public void enregistrer() {
		// ouverture ou creation du fichier
		Path logFile = Paths.get("parties_sauvegardees/"+this.nom_partie+".txt");
		
		try {
			Files.deleteIfExists(logFile); //  on supprime d'abord le fichier pour eviter des problemes de superposition
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try { 
			Files.createFile(logFile);// on recree le fichier
		}catch(IOException e ) {
			System.out.println("Ecrasement du fichier...");
			//e.printStackTrace();
		}
		
		try(BufferedWriter writer = Files.newBufferedWriter(logFile,
				StandardCharsets.UTF_8 , StandardOpenOption.WRITE)){
			writer.write(this.tama.surnom + "/" + this.tama.bonheur + "/"+ // premiere ligne : les attributs
					this.tama.education + "/" + this.tama.energie  + "/" +
					this.tama.sante + "/"+ this.tama.satiete+"/"+this.nombre_tours+"\n");
			 // deuxieme ligne : les mots appris (on ajoute un / entre chaque mot)
			for (String mot : this.tama.mots_appris){
				writer.write(mot+"/");
			}
		}catch(IOException e){
			e.printStackTrace();
		}
		System.out.println("Sauvegarde reussie!");
	}
	
	
	/**
	 * Methode utilisee pour savoir si deux mots sont identiques 
	 * ou si le deuxieme mot est le pluriel du premier mot de type +s ou al-aux.
	 * <p>
	 * Lorsque les deux mots sont identiques ou d'un couple singulier/pluriel, la fonction renvoie true.
	 * Dans le cas contraire, il renvoie false.
	 *
	 * @param  prem le premier mot
	 * @param  deux le deuxieme mot qui est eventuellement le pluriel du premier
	 * @return  boolean  si les deux mots sont les memes ou pas
	 */
	protected boolean compare_mots(String prem,String deux) {
		
		if (prem.toLowerCase().equals(deux.toLowerCase())){
			return true;
		}
		if (prem.equals(deux+'s')){
			return true;
		}
		if (prem.length()>=3 && deux.length()>=3 && prem.substring(prem.length()-2,prem.length()).equals("al") && deux.substring(0,deux.length()-3).equals(prem.substring(0,prem.length()-2)) && deux.substring(deux.length()-3,deux.length()).equals("aux")){
			return true;
		}
		else{
			return false;
		}
	}
	
	/**
	 * Constructor qui cree une partie a partir du numero de la partie et du surnom du Tamagotchi.
	 *
	 * @param  numero numero de la partie
	 * @param  surnom surnom du Tamagotchi
	 */
	public Partie(int numero, String surnom){
		this.tama = new Tamagotchi(surnom,100,100,100,100,100); // valeurs par defaut
		this.nom_partie = numero;
		this.nombre_tours = 0;
	}	
}
