package org.eni.encheres.back.bo;

import java.util.ArrayList;
import java.util.List;

public class Categorie {
	private int numCat;
	private String libelle;
	private List<ArticleVendu> LArticleVendu;
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
	
	public List<ArticleVendu> getLArticleVendu() {
		return LArticleVendu;
	}
	public void setLArticleVendu(List<ArticleVendu> lArticleVendu) {
		LArticleVendu = lArticleVendu;
	}
	public Categorie() {
		LArticleVendu = new ArrayList<ArticleVendu>();
	}
	public Categorie(int numCat, String libelle) {
		this();
		this.numCat = numCat;
		this.libelle = libelle;
	}
	public Categorie(String libelle) {
		this();
		this.libelle = libelle;
	}
	
	public void addArt(ArticleVendu art) {
		LArticleVendu.add(art);
	}
	
}
