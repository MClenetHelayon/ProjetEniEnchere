package org.eni.encheres.bo;

public class Categorie {
	private int numCat;
	private String libelle;
	public int getNumCat() {
		return numCat;
	}
	public void setNumCat(int numCat) {
		this.numCat = numCat;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public Categorie() {
		super();
	}
	public Categorie(int numCat, String libelle) {
		this();
		this.numCat = numCat;
		this.libelle = libelle;
	}
	
	
}
