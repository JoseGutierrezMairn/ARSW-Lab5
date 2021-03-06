/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.controllers;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.services.BlueprintsServices;

/**
 *
 * @author hcadavid
 */
@RestController
@RequestMapping(value = "/blueprints")
public class BlueprintAPIController {
    
	@Autowired
	BlueprintsServices service = null;
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> manejadorGetRecursoBlueprints(){
	    try {
	        return new ResponseEntity<>(service.getAllBlueprints(),HttpStatus.ACCEPTED);
	    } catch (Exception ex) {
	        Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
	        return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
	    }        
	}
	
	
	@RequestMapping(method = RequestMethod.GET, path="/{author}")
	public ResponseEntity<?> manejadorGetRecursoAuthor(@PathVariable String author){
	    try {
	        return new ResponseEntity<>(service.getBlueprintsByAuthor(author),HttpStatus.ACCEPTED);
	    } catch (Exception ex) {
	        Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
	        return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
	    }        
	}
	
	@RequestMapping(method = RequestMethod.GET, path="/{author}/{bpname}")
	public ResponseEntity<?> manejadorGetRecursoBpByAuthorAndBlueprintName(@PathVariable String author, @PathVariable String bpname){
	    try {
	        return new ResponseEntity<>(service.getBlueprint(author, bpname),HttpStatus.ACCEPTED);
	    } catch (Exception ex) {
	        Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
	        return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
	    }        
	}
    
	
	@RequestMapping(method = RequestMethod.POST)	
	public ResponseEntity<?> manejadorPostRecursoBlueprint(@RequestBody Blueprint bp){
	    try {
	    	service.addNewBlueprint(bp);
	        //registrar dato
	        return new ResponseEntity<>(HttpStatus.CREATED);
	    } catch (Exception ex) {
	        Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
	        return new ResponseEntity<>(ex.getMessage(),HttpStatus.FORBIDDEN);            
	    }        

	}
    
	@RequestMapping(method = RequestMethod.PUT, path="/{author}/{bpname}")	
	public ResponseEntity<?> putBlueprints(@RequestBody Blueprint bp, @PathVariable String author, @PathVariable String bpname){
	    try {
	    	service.setBlueprint(bp, author, bpname);
	        //registrar dato
	        return new ResponseEntity<>(HttpStatus.CREATED);
	    } catch (Exception ex) {
	        Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
	        return new ResponseEntity<>(ex.getMessage(),HttpStatus.FORBIDDEN);            
	    }        

	}
    
}

