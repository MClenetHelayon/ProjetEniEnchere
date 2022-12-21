package org.eni.encheres.bo;

import java.time.LocalDateTime;

public class Enchere {
	private int numEnchere;
	private LocalDateTime date;
	private int montant;
	private Utilisateur user;
	private ArticleVendu artVendu;
	public int getNumEnchere() {
		return numEnchere;
	}
	public void setNumEnchere(int numEnchere) {
		this.numEnchere = numEnchere;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	public int getMontant() {
		return montant;
	}
	public void setMontant(int montant) {
		this.montant = montant;
	}

	public Utilisateur getUser() {
		return user;
	}
	public void setUser(Utilisateur user) {
		this.user = user;
	}
	public ArticleVendu getArtVendu() {
		return artVendu;
	}
	public void setArtVendu(ArticleVendu artVendu) {
		this.artVendu = artVendu;
	}
	public Enchere() {}
	public Enchere(LocalDateTime date, int montant, Utilisateur user, ArticleVendu artVendu) {
		this();
		this.date = date;
		this.montant = montant;
		this.user = user;
		this.artVendu = artVendu;
	}
	public Enchere(int numEnchere, LocalDateTime date, int montant, Utilisateur user, ArticleVendu artVendu) {
		this();
		this.numEnchere = numEnchere;
		this.date = date;
		this.montant = montant;
		this.user = user;
		this.artVendu = artVendu;
	}
	
}
