package org.eni.encheres.bo;

import java.time.LocalDate;

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
	public ArticleVendu(int numArticle, String nom, String description, LocalDate dateDeb, LocalDate dateFin,
			int prixInit, int prixVente, Utilisateur user, Categorie categ) {
		this();
		this.numArticle = numArticle;
		this.nom = nom;
		this.description = description;
		this.dateDeb = dateDeb;
		this.dateFin = dateFin;
		this.prixInit = prixInit;
		this.prixVente = prixVente;
		this.user = user;
		this.categ = categ;
	}
	public ArticleVendu() {
		super();
	}
	
	
}
