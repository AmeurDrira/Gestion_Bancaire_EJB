package tn.iit.serviceInterface;

import java.util.List;

import tn.iit.model.ClientBanque;

public interface ClientBanqueLocal {
	void creerClientBanque(ClientBanque clientBanque);

	void supprimerClientBanque(String cin);

	void modifierClientBanque(ClientBanque clientBanque);

	ClientBanque getByIdClientBanque(String cin);

	List<ClientBanque> detailClients();
}
