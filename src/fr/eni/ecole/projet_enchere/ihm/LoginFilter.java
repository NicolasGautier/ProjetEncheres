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
import javax.servlet.http.HttpServletResponse;

import fr.eni.ecole.projet_enchere.bll.BLLException;
import fr.eni.ecole.projet_enchere.bll.BllFactory;
import fr.eni.ecole.projet_enchere.bll.UtilisateurManager;
import fr.eni.ecole.projet_enchere.bo.Utilisateur;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter({"/AccueilServlet","/AfficherProfilServlet","/LogoutServlet","/ModifierProfilServlet","/NewArticleServlet"})
public class LoginFilter implements Filter {

	private UtilisateurManager utilManager = BllFactory.getUniqueUtilisateurManager();

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
			if (tabCookies != null) {
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
						if(utilManager.logAndPassChecked(logModel.getUtilisateur())) {
							logModel.setUtilisateur(utilManager.getUtilisateur(logModel.getUtilisateur()));
							((HttpServletRequest) request).getSession().setAttribute("logModel", logModel);
							chain.doFilter(request, response);
						} else { //Sinon on renvois sur la page login
							if(servletName.equals("/AccueilServlet")) {
								chain.doFilter(request, response);
							} else {
								((HttpServletRequest) request).getSession().setAttribute("previousPage", servletName);
								request.getRequestDispatcher("/LoginServlet").forward(request, response);
							}
						}
					} catch (BLLException e) {
						e.printStackTrace();
					}
				} else {
					if(servletName.equals("/AccueilServlet")) {
						chain.doFilter(request, response);
					} else {
						((HttpServletRequest) request).getSession().setAttribute("previousPage", servletName);
						request.getRequestDispatcher("/LoginServlet").forward(request, response);
					}
				} 
			} else {
				if(servletName.equals("/AccueilServlet")) {
					chain.doFilter(request, response);
				} else {
					((HttpServletRequest) request).getSession().setAttribute("previousPage", servletName);
					request.getRequestDispatcher("/LoginServlet").forward(request, response);
				}
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
