package tn.iit.service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import tn.iit.dao.CompteBancaireDao;
import tn.iit.model.CompteBancaire;
import tn.iit.serviceInterface.CompteBancaireLocal;

@Stateless
public class CompteBancaireService implements CompteBancaireLocal, Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private CompteBancaireDao compteBancaireDao;

	@Override
	public void creerCompteBancaire(CompteBancaire compteBancaire) {
		compteBancaireDao.createCompteDao(compteBancaire);

	}

	@Override
	public List<CompteBancaire> detailComptes() {
		return compteBancaireDao.findAllDao();
	}

	@Override
	public List<CompteBancaire> comptesByClient(String cin) {

		return compteBancaireDao.findByClient(cin);
	}

	@Override
	public void supprimerCompteBancaire(long rib) {
		compteBancaireDao.deleteCompteDao(rib);
	}

	@Override
	public void modifierompteBancaire(CompteBancaire compteBancaire) {

		compteBancaireDao.updateCompteDao(compteBancaire);
	}

	@Override
	public CompteBancaire getByIdCompteBancaire(long rib) {
		return compteBancaireDao.getByRib(rib);
	}

}
