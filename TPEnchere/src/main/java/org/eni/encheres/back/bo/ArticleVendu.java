package org.eni.encheres.back.bo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ArticleVendu {
	private int numArticle;
	private String nom;
	private String description;
	private LocalDate dateDeb;
	private LocalDate dateFin;
	private int prixInit;
	private int prixVente;
	private Utilisateur user;
	private Categorie categ;
	private List<Enchere> LEnchere;
	private Enchere enchereMax;
	private int etatVente;
	
	public int getNumArticle() {
		return numArticle;
	}
	public void setNumArticle(int numArticle) {
		this.numArticle = numArticle;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDate getDateDeb() {
		return dateDeb;
	}
	public void setDateDeb(LocalDate dateDeb) {
		this.dateDeb = dateDeb;
	}
	public LocalDate getDateFin() {
		return dateFin;
	}
	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
	}
	public int getPrixInit() {
		return prixInit;
	}
	public void setPrixInit(int prixInit) {
		this.prixInit = prixInit;
	}
	public int getPrixVente() {
		return prixVente;
	}
	public void setPrixVente(int prixVente) {
		this.prixVente = prixVente;
	}
	public Utilisateur getUser() {
		return user;
	}
	public void setUser(Utilisateur user) {
		this.user = user;
	}
	public Categorie getCateg() {
		return categ;
	}
	public void setCateg(Categorie categ) {
		this.categ = categ;
	}
	
	public List<Enchere> getLEnchere() {
		return LEnchere;
	}
	public void setLEnchere(List<Enchere> lEnchere) {
		LEnchere = lEnchere;
	}
	public int getEtatVente() {
		return etatVente;
	}
	public void setEtatVente(int etatVente) {
		this.etatVente = etatVente;
	}
	
	public ArticleVendu() {
		LEnchere = new ArrayList<Enchere>();
	}
	public ArticleVendu(int numArticle, String nom, String description, LocalDate dateDeb, LocalDate dateFin,
			int prixInit, int prixVente, Utilisateur user, Categorie categ, int etatVente) {
		super();
		this.numArticle = numArticle;
		this.nom = nom;
		this.description = description;
		this.dateDeb = dateDeb;
		this.dateFin = dateFin;
		this.prixInit = prixInit;
		this.prixVente = prixVente;
		this.user = user;
		this.categ = categ;
		this.etatVente = etatVente;
		
		categ.addArt(this);
		user.addArticleVendu(this);
	}
	public ArticleVendu(String nom, String description, LocalDate dateDeb, LocalDate dateFin, int prixInit,
			int prixVente, Utilisateur user, Categorie categ, int etatVente) {
		super();
		this.nom = nom;
		this.description = description;
		this.dateDeb = dateDeb;
		this.dateFin = dateFin;
		this.prixInit = prixInit;
		this.prixVente = prixVente;
		this.user = user;
		this.categ = categ;
		this.etatVente = etatVente;
		
		categ.addArt(this);
		user.addArticleVendu(this);
	}
	
	
	@Override
	public String toString() {
		return "ArticleVendu [numArticle=" + numArticle + ", nom=" + nom + ", description=" + description + ", dateDeb="
				+ dateDeb + ", dateFin=" + dateFin + ", prixInit=" + prixInit + ", prixVente=" + prixVente + ", user="
				+ user + ", categ=" + categ + ", etatVente=" + etatVente + "]";
	}
	public void addEnchere(Enchere e) {
		this.LEnchere.add(e);
	}
	public Enchere getEnchereMax() {
		return enchereMax;
	}
	public void setEnchereMax(Enchere enchereMax) {
		this.enchereMax = enchereMax;
	}
}
