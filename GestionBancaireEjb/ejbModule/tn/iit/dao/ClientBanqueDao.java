package tn.iit.dao;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.iit.model.ClientBanque;
import tn.iit.model.CompteBancaire;

@Singleton
public class ClientBanqueDao {
	@PersistenceContext
	private EntityManager em;
	@EJB
	private CompteBancaireDao compteBancaireDao;

	public List<ClientBanque> findAllDao() {
		 TypedQuery<ClientBanque> q = em.createQuery("from ClientBanque n",ClientBanque.class);
		List<ClientBanque> list = q.getResultList();
		return list;
	}

	public void createClientDao(ClientBanque clientBanque) {
		em.persist(clientBanque);
	}

	public void deleteClientDao(String cin) {
		ClientBanque clt = em.find(ClientBanque.class, cin);
		List<CompteBancaire> comptes = compteBancaireDao.findByClient(cin);
		for (CompteBancaire compte : comptes)
			compteBancaireDao.deleteCompteDao(compte.getRib());
		em.remove(clt);
	}

	public void updateClientDao(ClientBanque clientBanque) {
		em.merge(clientBanque);
	}

	public ClientBanque getByCin(String cin) {
		ClientBanque clientBanque = em.find(ClientBanque.class, cin);
		return clientBanque;
	}

}
