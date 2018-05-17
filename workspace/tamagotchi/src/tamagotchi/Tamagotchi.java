package tamagotchi;

import java.util.ArrayList;
import java.util.List;

public class Tamagotchi {
	
	public int bonheur = 100;
	public int education = 100;
	public int energie = 100;
	public int sante = 100;
	public int satiete = 100;
	
	public String surnom;
	
	public List<String> mots_appris = new ArrayList<String>();
	public String etat = "neutre";
	
	/**
	 * Constructor qui définit des attributs au Tamagotchi.
	 * <p>
	 * Donne au Tamagotchi les attributs surnom, bonheur, education, energie, sante, satiete, et une liste de mots qu'il connait.
	 *
	 * @param  surnom   surnom du Tamagotchi
	 * @param  bonheur   valeur du bonheur de base
	 * @param  education   valeur de l'education de base
	 * @param  energie   valeur de l'energie de base
	 * @param  sante   valeur de la sante de base
	 * @param  satiete   valeur de la satiete de base
	 */
	public Tamagotchi(String surnom, int bonheur, int education, int energie, int sante, int satiete){
		this.surnom = surnom;
		this.bonheur = bonheur;
		this.education = education;
		this.energie = energie;
		this.sante = sante;
		this.satiete = satiete;
		
		this.mots_appris.add("bonjour");
		this.mots_appris.add("tamagotchi");
		this.mots_appris.add("bonheur");
		this.mots_appris.add(this.surnom);
		this.mots_appris.add("oui");
	}
	
}
