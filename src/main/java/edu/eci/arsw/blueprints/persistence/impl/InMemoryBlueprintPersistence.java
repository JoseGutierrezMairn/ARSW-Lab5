/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;

import java.util.*;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author hcadavid
 */
@Service("InMemoryBlueprintPersistence")
public class InMemoryBlueprintPersistence implements BlueprintsPersistence{

    private final Map<Tuple<String,String>,Blueprint> blueprints=new HashMap<>();

    public InMemoryBlueprintPersistence() {
        //load stub data
        Point[] pts=new Point[]{new Point(140, 140),new Point(115, 115)};
        Blueprint bp=new Blueprint("jose", "plano1",pts);
        blueprints.put(new Tuple<>(bp.getAuthor(),bp.getName()), bp);
        
        pts=new Point[]{new Point(200, 200),new Point(150, 150)};
        bp=new Blueprint("jose", "plano2",pts);
        blueprints.put(new Tuple<>(bp.getAuthor(),bp.getName()), bp);
        
        pts=new Point[]{new Point(100, 100)};
        bp=new Blueprint("daniel", "plano3",pts);
        blueprints.put(new Tuple<>(bp.getAuthor(),bp.getName()), bp);
    }    

    @Override
    public void saveBlueprint(Blueprint bp) throws BlueprintPersistenceException {

        if (blueprints.containsKey(new Tuple<>(bp.getAuthor(),bp.getName()))){
            throw new BlueprintPersistenceException("The given blueprint already exists: "+bp);
        }
        else{
            blueprints.put(new Tuple<>(bp.getAuthor(),bp.getName()), bp);
        }        
    }

    @Override
    public Blueprint getBlueprint(String author, String bprintname) throws BlueprintNotFoundException {
    	Blueprint bp = blueprints.get(new Tuple<>(author, bprintname));
    	if(bp == null) {
    		throw new BlueprintNotFoundException("Blueprint or author have not been found");
    	}
        return bp;
    }
	@Override
	public Set<Blueprint> getBlueprintsByAuthor(String author) throws BlueprintNotFoundException {
		// TODO Auto-generated method stub
		Set<Blueprint> answ = new HashSet<Blueprint>();
		Collection<Blueprint> co = blueprints.values();
		for (Blueprint bp : co) {
			if(bp.getAuthor().equals(author)) {
				answ.add(bp);
			}
		}
		if(answ.size() == 0) {
			throw new BlueprintNotFoundException("The author does not exist");
		}
			
		return answ;
	}

	@Override
	public Set<Blueprint> getAllBlueprints() {
		Set<Blueprint> answ = new HashSet<Blueprint>();
		for(Blueprint bp : blueprints.values()) {
			answ.add(bp);
		}
		
		return answ;
	}


	

    
    
}
