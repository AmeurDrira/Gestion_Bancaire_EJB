package tn.iit.serviceInterface;

import java.util.List;

import tn.iit.model.CompteBancaire;

public interface CompteBancaireLocal {
	void creerCompteBancaire(CompteBancaire compte);

	void supprimerCompteBancaire(long rib);

	void modifierompteBancaire(CompteBancaire compte);

	CompteBancaire getByIdCompteBancaire(long rib);

	List<CompteBancaire> detailComptes();

	public List<CompteBancaire> comptesByClient(String cin);
}
