package tamagotchi;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Deck {
	List<Carte> cartes = new ArrayList<Carte>();
	
	
	/**
	 * methode qui simule l'action de piocher une carte.
	 * 
	 * avec nextInt, on retire une carte au hasard du deck de cartes
	 * qu'on retourne pour qu'elle soit ajoutee a la main.
	 * 
	 * @return ret  la carte qu'on a pioche et retire du paquet.
	 */
	public Carte piocher() {
		int randint = ThreadLocalRandom.current().nextInt(0,this.cartes.size());
		Carte ret = this.cartes.get(randint);
		this.cartes.remove(randint);
		return ret;
	}
	
	
	/**
	 * constructeur qui intialise un deck à 52 cartes.
	 * appelle la methode melanger.
	 * 
	 */
	public Deck() {
		this.cartes = melanger();
	}
	
	/**
	 * cette methode remplit une liste de 52 instances de la classe Carte.
	 * On simule ainsi un paquet normal de cartes (sans jokers).
	 * 
	 * @return deck  une liste complete de 52 cartes.
	 */
	public List<Carte> melanger() {
		
		List<Carte> deck = new ArrayList<Carte>();
		
		deck.add(new Carte("As","Pique",1)); deck.add(new Carte("As","Carreau",1));
		deck.add(new Carte("As","Coeur",1)); deck.add(new Carte("As","Trefle",1));
		
		deck.add(new Carte("Deux","Pique",2));deck.add(new Carte("Deux","Carreau",2));
		deck.add(new Carte("Deux","Coeur",2));deck.add(new Carte("Deux","Trefle",2));
		
		deck.add(new Carte("Trois","Pique",3));deck.add(new Carte("Trois","Carreau",3));
		deck.add(new Carte("Trois","Coeur",3));deck.add(new Carte("Trois","Trefle",3));
		
		deck.add(new Carte("Quatre","Pique",4));deck.add(new Carte("Quatre","Carreau",4));
		deck.add(new Carte("Quatre","Coeur",4));deck.add(new Carte("Quatre","Trefle",4));
		
		deck.add(new Carte("Cinq","Pique",5));deck.add(new Carte("Cinq","Carreau",5));
		deck.add(new Carte("Cinq","Coeur",5));deck.add(new Carte("Cinq","Trefle",5));
		
		deck.add(new Carte("Six","Pique",6));deck.add(new Carte("Six","Carreau",6));
		deck.add(new Carte("Six","Coeur",6));deck.add(new Carte("Six","Trefle",6));
		
		deck.add(new Carte("Sept","Pique",7));deck.add(new Carte("Sept","Carreau",7));
		deck.add(new Carte("Sept","Coeur",7));deck.add(new Carte("Sept","Trefle",7));
		
		deck.add(new Carte("Huit","Pique",8));deck.add(new Carte("Huit","Carreau",8));
		deck.add(new Carte("Huit","Coeur",8));deck.add(new Carte("Huit","Trefle",8));
		
		deck.add(new Carte("Neuf","Pique",9));deck.add(new Carte("Neuf","Carreau",9));
		deck.add(new Carte("Neuf","Coeur",9));deck.add(new Carte("Neuf","Trefle",9));
		
		deck.add(new Carte("Dix","Pique",10));deck.add(new Carte("Dix","Carreau",10));
		deck.add(new Carte("Dix","Coeur",10));deck.add(new Carte("Dix","Trefle",10));
		
		deck.add(new Carte("Valet","Pique",10));deck.add(new Carte("Valet","Carreau",10));
		deck.add(new Carte("Valet","Coeur",10));deck.add(new Carte("Valet","Trefle",10));
		
		deck.add(new Carte("Dame","Pique",10));deck.add(new Carte("Dame","Carreau",10));
		deck.add(new Carte("Dame","Coeur",10));deck.add(new Carte("Dame","Trefle",10));
		
		deck.add(new Carte("Roi","Pique",10));deck.add(new Carte("Roi","Carreau",10));
		deck.add(new Carte("Roi","Coeur",10));deck.add(new Carte("Roi","Trefle",10));
		
		return deck;
	}
	

	
}
