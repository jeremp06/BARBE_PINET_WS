/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.unice.miage.ntdp.bibliotheque.services;

import fr.unice.miage.ntdp.bibliotheque.Categorie;
import fr.unice.miage.ntdp.bibliotheque.bean.AbstractFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Jeremy
 */
@Path("categorie")
@Stateless
public class CategorieRessources extends AbstractFacade<Categorie>{

    public CategorieRessources() {
        super(Categorie.class);
    }
    
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Categorie> getCategories(){
        return super.findAll();
    }
    @GET
    @Path("count")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public String countCategorie(){
        return (super.count())+"";
    }
    
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Categorie getCategorie(@PathParam("id") Long id){
            return super.find(id);
    }
    
    @GET
    @Path("{nbMin}/{nbMax}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Categorie> getCategoriesByRange(@PathParam("nbMin") int nbMin,@PathParam("nbMax") int nbMax){
        int tab[] = {nbMin,nbMax}; 
        return super.findRange(tab);
    }
    
    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public String createCat(Categorie cat) {
        super.create(cat);
        return "Categorie créée";
    }
 
    @PUT 
    @Path("{id}")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public String update(Categorie cat) {
        super.edit(cat);
        return "Categorie mise à jour";
    }
    
    @DELETE 
    @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public String remove(@PathParam("id") int id) {
        super.remove(find(id));
        return "Categorie supprimé";
    }
}
