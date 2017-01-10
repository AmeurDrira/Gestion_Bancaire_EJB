package tn.iit.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ClientBanque {

	@Id
	private String cin;
	private String nom;
	private String prenom;
	private String adresse;

	public ClientBanque() {
		super();
	}

	public ClientBanque(String cin, String nom, String prenom, String adresse) {
		super();
		this.cin = cin;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
	}

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
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

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cin == null) ? 0 : cin.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		ClientBanque other = (ClientBanque) obj;
		if (cin == null) {
			if (other.cin != null) {
				return false;
			}
		} else if (!cin.equals(other.cin)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "ClientBanque [cin=" + cin + ", nom=" + nom + ", prenom=" + prenom + ", adresse=" + adresse + "]";
	}
}