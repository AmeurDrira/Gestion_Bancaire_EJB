package tn.iit.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity

public class CompteBancaire {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long rib;
	private float solde;

	public CompteBancaire() {
		super();
	}

	public CompteBancaire(Long rib, float solde, ClientBanque client) {
		super();
		this.rib = rib;
		this.solde = solde;
		this.client = client;
	}

	@ManyToOne
	@JoinColumn(name = "idClient")
	private ClientBanque client;

	public ClientBanque getClient() {
		return client;
	}

	public void setClient(ClientBanque client) {
		this.client = client;
	}

	public Long getRib() {
		return rib;
	}

	public void setRib(Long rib) {
		this.rib = rib;
	}

	public float getSolde() {
		return solde;
	}

	public void setSolde(float solde) {
		this.solde = solde;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((rib == null) ? 0 : rib.hashCode());
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
		CompteBancaire other = (CompteBancaire) obj;
		if (rib == null) {
			if (other.rib != null) {
				return false;
			}
		} else if (!rib.equals(other.rib)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "CompteBancaire [rib=" + rib + ", solde=" + solde + "]";
	}
}
