/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.unice.miage.ntdp.bibliotheque.services;

import fr.unice.miage.ntdp.bibliotheque.Pret;
import fr.unice.miage.ntdp.bibliotheque.bean.PretFacade;
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
@Path("pret")
public class PretRessources extends PretFacade{

    public PretRessources() {
        super();
    }
    
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Pret> getPrets(){
        return super.findAll();
    }
    @GET
    @Path("count")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public String countPret(){
        return (super.count())+"";
    }
    
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Pret getPret(@PathParam("id") Long id){
            return super.find(id);
    }
    
    @GET
    @Path("{nbMin}/{nbMax}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Pret> getPretsByRange(@PathParam("nbMin") int nbMin,@PathParam("nbMax") int nbMax){
        int tab[] = {nbMin,nbMax}; 
        return super.findRange(tab);
    }
    
    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public String createPret(Pret pret) {
        super.create(pret);
        return "Pret créé";
    }
 
    @PUT 
    @Path("{id}")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public String update(Pret pret) {
        super.edit(pret);
        return "Pret mis à jour";
    }
    
    @DELETE 
    @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public String remove(@PathParam("id") int id) {
        super.remove(find(id));
        return "Pret supprimé";
    }
}
