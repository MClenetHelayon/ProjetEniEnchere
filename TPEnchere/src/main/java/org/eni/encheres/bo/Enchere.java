package org.eni.encheres.bo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Enchere {
	private int numEnchere;
	private LocalDateTime date;
	private int montant;
	private List<ArticleVendu> LArtVendu;
	private List<Utilisateur> LUser;
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
	public List<ArticleVendu> getLArtVendu() {
		return LArtVendu;
	}
	public void setLArtVendu(List<ArticleVendu> lArtVendu) {
		LArtVendu = lArtVendu;
	}
	public List<Utilisateur> getLUser() {
		return LUser;
	}
	public void setLUser(List<Utilisateur> lUser) {
		LUser = lUser;
	}
	public Enchere(int numEnchere, LocalDateTime date, int montant) {
		this();
		this.numEnchere = numEnchere;
		this.date = date;
		this.montant = montant;
	}
	public Enchere() {
		this.LUser=new ArrayList<Utilisateur>();
		this.LArtVendu=new ArrayList<ArticleVendu>();
	}
	
	public void addLUser(Utilisateur user) {
		this.LUser.add(user);
	}
	public void addLArtVendu(ArticleVendu art) {
		this.LArtVendu.add(art);
	}
	
}
