package tn.iit.dao;

import java.util.List;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.iit.model.CompteBancaire;

@Singleton
public class CompteBancaireDao {
	@PersistenceContext
	private EntityManager em;

	public List<CompteBancaire> findAllDao() {
		Query q = em.createQuery("from CompteBancaire n");
		List<CompteBancaire> list = q.getResultList();
		return list;
	}

	public List<CompteBancaire> findByClient(String cin) {

		Query q = em.createQuery("from CompteBancaire cb where cb.client.cin=:cin");
		q.setParameter("cin", cin);

		List<CompteBancaire> list = q.getResultList();

		return list;
	}

	public void createCompteDao(CompteBancaire compteBancaire) {
		em.persist(compteBancaire);
	}

	public void deleteCompteDao(long rib) {
		em.remove(em.find(CompteBancaire.class, rib));
	}

	public void updateCompteDao(CompteBancaire compteBancaire) {
		em.merge(compteBancaire);
	}

	public CompteBancaire getByRib(long rib) {
		CompteBancaire compteBancaire = em.find(CompteBancaire.class, rib);
		return compteBancaire;

	}

}
