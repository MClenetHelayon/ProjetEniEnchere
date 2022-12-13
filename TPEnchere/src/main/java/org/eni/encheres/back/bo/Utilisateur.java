package org.eni.encheres.back.bo;

import java.util.ArrayList;
import java.util.List;

public class Utilisateur {
	private int idUser;
	private String pseudo;
	private String nom;
	private String prenom;
	private String email;
	private String telephone;
	private String rue;
	private String codePostal;
	private String ville;
	private String mdp;
	private int credit;
	private boolean admin;
	private List<Enchere> LEnchere;
	private List<ArticleVendu> LArtVendu;
	private List<ArticleVendu> LArtAchete;
	
	public Utilisateur() {
		this.LEnchere = new ArrayList<Enchere>();
		this.LArtVendu = new ArrayList<ArticleVendu>();
		this.LArtAchete = new ArrayList<ArticleVendu>();
	}

	public Utilisateur(int idUser, String pseudo, String nom, String prenom, String email, String telephone, String rue,
			String codePostal, String ville, String mdp, int credit, boolean admin) {
		this();
		this.idUser = idUser;
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.mdp = mdp;
		this.credit = credit;
		this.admin = admin;
	}

	public Utilisateur(String pseudo, String nom, String prenom, String email, String telephone, String rue,
			String codePostal, String ville, String mdp, int credit, boolean admin) {
		this();
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.mdp = mdp;
		this.credit = credit;
		this.admin = admin;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public List<Enchere> getLEnchere() {
		return LEnchere;
	}

	public void setLEnchere(List<Enchere> lEnchere) {
		this.LEnchere = lEnchere;
	}
	
	public List<ArticleVendu> getLArtVendu() {
		return LArtVendu;
	}

	public void setLArtVendu(List<ArticleVendu> lArtVendu) {
		LArtVendu = lArtVendu;
	}

	public List<ArticleVendu> getLArtAchete() {
		return LArtAchete;
	}

	public void setLArtAchete(List<ArticleVendu> lArtAchete) {
		LArtAchete = lArtAchete;
	}

	public void addArticleVendu(ArticleVendu av) {
		this.LArtVendu.add(av);
	}
	public void addArticleAcheter(ArticleVendu aa) {
		this.LArtAchete.add(aa);
	}
	public void addEnchere(Enchere e) {
		this.LEnchere.add(e);
	}
}
