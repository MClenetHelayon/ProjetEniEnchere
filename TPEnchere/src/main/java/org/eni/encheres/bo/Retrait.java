package org.eni.encheres.bo;

public class Retrait {
	private ArticleVendu artVendu;
	private String rue;
	private String codePostal;
	private String ville;
	public ArticleVendu getArtVendu() {
		return artVendu;
	}
	public void setArtVendu(ArticleVendu artVendu) {
		this.artVendu = artVendu;
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
	public Retrait() {
		super();
	}
	public Retrait(ArticleVendu artVendu, String rue, String codePostal, String ville) {
		super();
		this.artVendu = artVendu;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
	}
	
	
}
