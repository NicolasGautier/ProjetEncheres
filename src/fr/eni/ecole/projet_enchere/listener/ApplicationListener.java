package fr.eni.ecole.projet_enchere.listener;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import fr.eni.ecole.projet_enchere.bll.ArticleVenduManager;
import fr.eni.ecole.projet_enchere.bll.BLLException;
import fr.eni.ecole.projet_enchere.bll.BllFactory;
import fr.eni.ecole.projet_enchere.bll.UtilisateurManager;
import fr.eni.ecole.projet_enchere.bo.ArticleVendu;
import fr.eni.ecole.projet_enchere.bo.Enchere;
import fr.eni.ecole.projet_enchere.bo.EtatsVente;

/**
 * Application Lifecycle Listener implementation class ApplicationListener
 *
 */
@WebListener
public class ApplicationListener implements ServletContextListener {

	/**
	 * Default constructor.
	 */
	public ApplicationListener() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent sce) {
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					// EnchereManager enchManager = BllFactory.getUniqueEnchereManager();
					ArticleVenduManager artVendManager = BllFactory.getUniqueArticleVenduManager();
					UtilisateurManager utilManager = BllFactory.getUniqueUtilisateurManager();

					List<ArticleVendu> lstArticlesVendus = null;
					try {
						lstArticlesVendus = artVendManager.getAllArticleVendu();
					} catch (BLLException e1) {
						e1.printStackTrace();
					}

					for (ArticleVendu articleVendu : lstArticlesVendus) {
						if (articleVendu.getEtatVente().equals(EtatsVente.EN_COURS)
								&& articleVendu.getDateFinEncheres().isBefore(LocalDate.now())) {
							articleVendu.setUtilisateurAchete(articleVendu.getEncheres().stream()
									.sorted((e1, e2) -> e2.getMontant_enchere().compareTo(e1.getMontant_enchere()))
									.collect(Collectors.toList()).get(0).getUtilisateurEncherit());
							articleVendu.setEtatVente(EtatsVente.ENCHERES_TERMINEES);
							try {
								artVendManager.setArticleVendu(articleVendu);
							} catch (BLLException e) {
								e.printStackTrace();
							}
							Integer index = articleVendu.getEncheres().size();
							for (Enchere enchere : articleVendu.getEncheres().stream()
									.sorted((e1, e2) -> e1.getMontant_enchere().compareTo(e2.getMontant_enchere()))
									.collect(Collectors.toList())) {
								if (index == 1) {
									break;
								}
								index--;
								try {
									utilManager.rendPointUtilisateur(enchere.getUtilisateurEncherit(),
											enchere.getMontant_enchere());
								} catch (BLLException e) {
									e.printStackTrace(); // TODO Afficher ce genre d'erreur aux administrateur
								}
							}
						}
					}

					try {
						Thread.sleep(1000); // TODO Changer le temps de rafraichissement
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}, "afficheVenteRemporteThread");
		thread.start();
	}

}
