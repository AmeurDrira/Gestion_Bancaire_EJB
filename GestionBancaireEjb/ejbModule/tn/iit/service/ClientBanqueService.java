package tn.iit.service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import tn.iit.dao.ClientBanqueDao;
import tn.iit.model.ClientBanque;
import tn.iit.serviceInterface.ClientBanqueLocal;

@Stateless
public class ClientBanqueService implements ClientBanqueLocal, Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private ClientBanqueDao clientBanqueDao;

	@Override
	public void creerClientBanque(ClientBanque clientBanque) {
		clientBanqueDao.createClientDao(clientBanque);
	}

	@Override
	public void supprimerClientBanque(String cin) {
		clientBanqueDao.deleteClientDao(cin);

	}

	@Override
	public void modifierClientBanque(ClientBanque clientBanque) {
		clientBanqueDao.updateClientDao(clientBanque);

	}

	@Override
	public ClientBanque getByIdClientBanque(String cin) {

		return clientBanqueDao.getByCin(cin);
	}

	@Override
	public List<ClientBanque> detailClients() {
		return clientBanqueDao.findAllDao();
	}

}
