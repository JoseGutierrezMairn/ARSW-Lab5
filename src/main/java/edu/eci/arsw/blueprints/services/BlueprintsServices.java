/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.services;

import edu.eci.arsw.blueprints.filtros.implementacion.Filtro;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author hcadavid
 */
@Service
public class BlueprintsServices {
	
	
    @Autowired
    @Qualifier("InMemoryBlueprintPersistence")
    BlueprintsPersistence bpp=null;
    
    // Para usar el filtro de muestreo cambiar el parámetro de la etiqueta Qualifier a "Muestreo"
    // Para usar el filtro de Redundancia cambiar el parámetro de la etiqueta Quealifier a "Redundancia"
    @Autowired
    @Qualifier("Muestreo")
    Filtro r = null;
    
    //@Autowired
    //@Qualifier("Muestreo")
    //Filtro m = null;
    
    
    public void addNewBlueprint(Blueprint bp) throws BlueprintPersistenceException{
        bpp.saveBlueprint(bp);
    }
    
    public Set<Blueprint> getAllBlueprints(){
    	return bpp.getAllBlueprints();
    }
    
    /**
     * 
     * @param author blueprint's author
     * @param name blueprint's name
     * @return the blueprint of the given name created by the given author
     * @throws BlueprintNotFoundException if there is no such blueprint
     */
    public Blueprint getBlueprint(String author,String name) throws BlueprintNotFoundException{
    	return r.filtrar(bpp.getBlueprint(author, name));
        //throw new UnsupportedOperationException("Not supported yet."); 
    }
    
    /**
     * 
     * @param author blueprint's author
     * @return all the blueprints of the given author
     * @throws BlueprintNotFoundException if the given author doesn't exist
     */
    public Set<Blueprint> getBlueprintsByAuthor(String author) throws BlueprintNotFoundException{
    	Set<Blueprint> conF = bpp.getBlueprintsByAuthor(author);
    	for (Blueprint bp : conF) {
    		bp = r.filtrar(bp);
    	}
        return conF;
        //throw new UnsupportedOperationException("Not supported yet."); 
    }
    
    
   
}
