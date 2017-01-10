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
import tn.iit.serviceInterface.ClientBanqueLocal;

/**
 * Servlet implementation class ClientBanqueServlet
 */
@WebServlet("/ClientBanqueServlet")
public class ClientBanqueServlet extends HttpServlet {
	@EJB
	private ClientBanqueLocal clientBanqueLocal;

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ClientBanqueServlet() {
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

		if (action != null) {
			String cin = request.getParameter("id");

			if ("delete".equals(action)) {
				clientBanqueLocal.supprimerClientBanque(cin);
			} else if ("update".equals(action)) {
				ClientBanque clientBanque = clientBanqueLocal.getByIdClientBanque(cin);
				session.setAttribute("obj", clientBanque);
			}
		} else {
			session.setAttribute("listeClient", clientBanqueLocal.detailClients());
			rd = getServletContext().getRequestDispatcher("/client.jsp");
		}
		session.setAttribute("listeClient", clientBanqueLocal.detailClients());
		rd = getServletContext().getRequestDispatcher("/client.jsp");
		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		String id = request.getParameter("id");
		String cin = request.getParameter("cin");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String adresse = request.getParameter("adresse");

		if ("".equals(id) && !"".equals(cin) && !"".equals(nom) && !"".equals(prenom) && !"".equals(adresse)) {
			ClientBanque clientBanque = new ClientBanque();
			clientBanque.setCin(cin);
			clientBanque.setNom(nom);
			clientBanque.setPrenom(prenom);
			clientBanque.setAdresse(adresse);
			if (clientBanqueLocal.getByIdClientBanque(cin) == null)
				clientBanqueLocal.creerClientBanque(clientBanque);
			else
				request.setAttribute("erreurCin", 1);

		} else if (!"".equals(id) && !"".equals(nom) && !"".equals(prenom) && !"".equals(adresse)) {
			ClientBanque clientBanque = new ClientBanque();
			clientBanque.setCin(id);
			clientBanque.setNom(nom);
			clientBanque.setPrenom(prenom);
			clientBanque.setAdresse(adresse);
			clientBanqueLocal.modifierClientBanque(clientBanque);
			session.removeAttribute("obj");
		}

		doGet(request, response);
	}

}
