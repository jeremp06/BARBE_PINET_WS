/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.unice.miage.ntdp.bibliotheque.services;

import fr.unice.miage.ntdp.bibliotheque.Personne;
import fr.unice.miage.ntdp.bibliotheque.bean.PersonneFacade;
import java.util.List;
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
@Path("personne")
public class PersonneRessources extends PersonneFacade{

    public PersonneRessources() {
        super();
    }
    
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Personne> getPersonnes(){
        return super.findAll();
    }
    @GET
    @Path("count")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public String countPersonne(){
        return (super.count())+"";
    }
    
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Personne getPersonne(@PathParam("id") Long id){
            return super.find(id);
    }
    
    @GET
    @Path("{nbMin}/{nbMax}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Personne> getPersonnesByRange(@PathParam("nbMin") int nbMin,@PathParam("nbMax") int nbMax){
        int tab[] = {nbMin,nbMax}; 
        return super.findRange(tab);
    }
    
    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public String createPersonne(Personne pers) {
        super.create(pers);
        return "Personne créée";
    }
 
    @PUT 
    @Path("{id}")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public String update(Personne pers) {
        super.edit(pers);
        return "Personne mise à jour";
    }
    
    @DELETE 
    @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public String remove(@PathParam("id") int id) {
        super.remove(find(id));
        return "Personne supprimée";
    }
}
