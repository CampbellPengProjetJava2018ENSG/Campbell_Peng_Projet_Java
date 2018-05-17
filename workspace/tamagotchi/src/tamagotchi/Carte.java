package tamagotchi;

public class Carte {
	String nom;
	String couleur;
	int valeur;
	
	/**
	 * constructeur qui intialise un deck à 52 cartes.
	 * appelle la methode melanger.
	 * 
	 * @param  nom  le titre de la carte (Roi, Dame, dix, etc..)  
	 */
	public Carte(String nom, String couleur, int valeur) {
		this.nom = nom;
		this.couleur = couleur;
		this.valeur = valeur;
		
	}
	
	/** 
	 * redefinition de la methode toString pour afficher les informations d'une carte.
	 * on pourra ensuite print : " Roi de Coeur " 
	 * pour une carte avec le nom "Roi" et la couleur "Coeur"
	 * 
	 * @return    l'equivalent string de la carte.
	 */
	public String toString() {
		return this.nom +" de "+this.couleur;
	}
}
