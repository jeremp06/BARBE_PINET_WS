/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netbeans.rest.application.config;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Jeremy
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(fr.unice.miage.ntdp.bibliotheque.Auteur.class);
        resources.add(fr.unice.miage.ntdp.bibliotheque.Categorie.class);
        resources.add(fr.unice.miage.ntdp.bibliotheque.Livre.class);
        resources.add(fr.unice.miage.ntdp.bibliotheque.Personne.class);
        resources.add(fr.unice.miage.ntdp.bibliotheque.Pret.class);
        resources.add(fr.unice.miage.ntdp.bibliotheque.Users.class);
        resources.add(fr.unice.miage.ntdp.bibliotheque.services.AuteurRessources.class);
        resources.add(fr.unice.miage.ntdp.bibliotheque.services.CategorieRessources.class);
        resources.add(fr.unice.miage.ntdp.bibliotheque.services.LivreRessources.class);
        resources.add(fr.unice.miage.ntdp.bibliotheque.services.PersonneRessources.class);
        resources.add(fr.unice.miage.ntdp.bibliotheque.services.PretRessources.class);
        resources.add(fr.unice.miage.ntdp.bibliotheque.services.UsersRessources.class);
    }
    
}
