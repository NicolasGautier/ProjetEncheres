package fr.eni.ecole.projet_enchere.ihm;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import fr.eni.ecole.projet_enchere.bll.BLLException;
import fr.eni.ecole.projet_enchere.bll.UtilisateurManager;
import fr.eni.ecole.projet_enchere.bll.UtilisateurManagerSingl;
import fr.eni.ecole.projet_enchere.bo.Utilisateur;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter("/ModifierProfilServlet")
public class LoginFilter implements Filter {

	private UtilisateurManager utilisateurManager = UtilisateurManagerSingl.getInstance();

	/**
	 * Default constructor.
	 */
	public LoginFilter() {
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		Cookie[] tabCookies = ((HttpServletRequest) request).getCookies();

		// TODO Tester les cookies avec deux fenêtres
		String servletName = ((HttpServletRequest) request).getServletPath();
		if (((HttpServletRequest) request).getSession().getAttribute("logModel") == null) {
			Boolean identifiant = false;
			Boolean password = false;
			for(Cookie cookie : tabCookies) {
				if("identifiant".equals(cookie.getName())) identifiant = true;
				if("password".equals(cookie.getName())) password = true;
			}
			if (identifiant && password) {
				LoginModel logModel = new LoginModel(new Utilisateur("", "", "", "", "", "", "", "", "", 0, false,true));
				//On récupère nos identifiant et mot de passe dans les cookies
				for (Cookie cookie : tabCookies) {
					if ("identifiant".equals(cookie.getName())) {
						if(cookie.getValue().matches("[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+")) {
							logModel.getUtilisateur().setEmail(cookie.getValue());
						} else {
							logModel.getUtilisateur().setPseudo(cookie.getValue());
						}		
					}
					if ("password".equals(cookie.getName())) {
						logModel.getUtilisateur().setMotDePasse(cookie.getValue());
					}
				}
				//On test que nos identifiant et mot de passe aient bien été récupéré
				if ((!"".equals(logModel.getUtilisateur().getPseudo())
						|| !"".equals(logModel.getUtilisateur().getEmail()))
						&& !"".equals(logModel.getUtilisateur().getMotDePasse())) {
					try {
						//On se log et on charge le model
						if(utilisateurManager.logAndPassChecked(logModel.getUtilisateur())) {
							logModel.setUtilisateur(utilisateurManager.getUtilisateur(logModel.getUtilisateur()));
							((HttpServletRequest) request).getSession().setAttribute("logModel", logModel);
							chain.doFilter(request, response);
						} else { //Sinon on renvois sur la page login
							((HttpServletRequest) request).getSession().setAttribute("previousPage", servletName);
							request.getRequestDispatcher("/LoginServlet").forward(request, response);
						}
					} catch (BLLException e) {
						e.printStackTrace();
					}
				}
			} else {
				((HttpServletRequest) request).getSession().setAttribute("previousPage", servletName);
				request.getRequestDispatcher("/LoginServlet").forward(request, response);
			}
		} else {
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
