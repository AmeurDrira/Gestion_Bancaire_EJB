package tn.iit.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tn.iit.model.ClientBanque;
import tn.iit.model.CompteBancaire;
import tn.iit.serviceInterface.ClientBanqueLocal;
import tn.iit.serviceInterface.CompteBancaireLocal;

/**
 * Servlet implementation class CompteBancaire
 */
@WebServlet("/CompteBancaireServlet")
public class CompteBancaireServlet extends HttpServlet {

	@EJB
	private ClientBanqueLocal clientBanqueLocal;
	@EJB
	private CompteBancaireLocal compteBancaireLocal;

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CompteBancaireServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		RequestDispatcher rd;

		String action = request.getParameter("action");
		String cin = request.getParameter("cin");
		String rib = request.getParameter("rib");
		String cinSession = (String) session.getAttribute("cin");

		session.setAttribute("listeRib", compteBancaireLocal.detailComptes());
		if (cin != null) {
			session.setAttribute("cin", cin);
			ClientBanque clientBanque = clientBanqueLocal.getByIdClientBanque(cin);
			session.setAttribute("nomClient", clientBanque);
			session.setAttribute("listeCompte", compteBancaireLocal.comptesByClient(cin));
		} else if (rib != null && "delete".equals(action)) {
			compteBancaireLocal.supprimerCompteBancaire(Long.parseLong(rib));
		}
		if (cinSession != null) {
			session.setAttribute("listeCompte", compteBancaireLocal.comptesByClient(cinSession));
		}
		rd = getServletContext().getRequestDispatcher("/compte.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher rd;
		boolean erreurRetirer = true;
		String cin = request.getParameter("cin");
		String action = request.getParameter("action");
		String rib = request.getParameter("rib");
		String soldeAretirer = request.getParameter("soldeAretirer");

		if (cin != null && action == null) {

			ClientBanque clientBanque = clientBanqueLocal.getByIdClientBanque(cin);
			float solde = Float.parseFloat(request.getParameter("solde"));
			CompteBancaire compteBancaire = new CompteBancaire();
			compteBancaire.setClient(clientBanque);
			compteBancaire.setSolde(solde);
			compteBancaireLocal.creerCompteBancaire(compteBancaire);

		} else if (rib != null && "retirer".equals(action)) {

			CompteBancaire compteBancaire = compteBancaireLocal.getByIdCompteBancaire(Long.parseLong(rib));
			float solde = compteBancaire.getSolde();

			if (solde > Float.parseFloat(soldeAretirer)) {
				compteBancaire.setSolde(compteBancaire.getSolde() - Float.parseFloat(soldeAretirer));
				compteBancaireLocal.modifierompteBancaire(compteBancaire);
			} else {
				erreurRetirer = false;
				request.setAttribute("erreurRetirer", "1");
				request.setAttribute("solde", solde);
				rd = getServletContext().getRequestDispatcher("/retirer.jsp");
				rd.forward(request, response);

			}
		} else if (rib != null && "deposer".equals(action)) {

			CompteBancaire compteBancaire = compteBancaireLocal.getByIdCompteBancaire(Long.parseLong(rib));
			compteBancaire.setSolde(compteBancaire.getSolde() + Float.parseFloat(soldeAretirer));
			compteBancaireLocal.modifierompteBancaire(compteBancaire);
		} else if (rib != null && "transfere".equals(action)) {

			String ribToTransfere = request.getParameter("ribToTransfere");
			String soldeAtransferer = request.getParameter("soldeAtransferer");

			CompteBancaire compteBancaireToTransfere = compteBancaireLocal
					.getByIdCompteBancaire(Long.parseLong(ribToTransfere));
			CompteBancaire compteBancaireFromTransfere = compteBancaireLocal.getByIdCompteBancaire(Long.parseLong(rib));

			compteBancaireToTransfere
					.setSolde(compteBancaireToTransfere.getSolde() + Float.parseFloat(soldeAtransferer));
			compteBancaireFromTransfere
					.setSolde(compteBancaireFromTransfere.getSolde() - Float.parseFloat(soldeAtransferer));

			compteBancaireLocal.modifierompteBancaire(compteBancaireToTransfere);
			compteBancaireLocal.modifierompteBancaire(compteBancaireFromTransfere);

		}
		if (erreurRetirer) 
			doGet(request, response);
		
	}

}
