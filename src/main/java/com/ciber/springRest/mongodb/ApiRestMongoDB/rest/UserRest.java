/**
 * 
 */
package com.ciber.springRest.mongodb.ApiRestMongoDB.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ciber.springRest.mongodb.ApiRestMongoDB.beans.Usuario;
import com.ciber.springRest.mongodb.ApiRestMongoDB.daoMongo.DaoUsers;

/**
 * @author ciber
 *
 */
@RestController
public class UserRest {
	
	@Autowired
	DaoUsers daoUsers;
	
	
	@GetMapping("/users")
	public List<Usuario> findAll(){
		return daoUsers.findAll();
	}
	
	@GetMapping("/users/{nombre}")
	public Usuario findById(@PathVariable("nombre") String nombre){
		return daoUsers.findUser(nombre);
	}
	
	@PostMapping("/users")
	public Usuario addUser(@RequestBody Usuario usuario){
		return daoUsers.addUser(usuario);
	}
	
	@DeleteMapping("/users/{nombre}")
	public String deleteUser(@PathVariable("nombre") String nombre){
		return daoUsers.deleteUser(nombre);
	}
	
	@PutMapping("/users/{nombre}")
	public Usuario updateUser(@RequestBody Usuario user, @PathVariable("nombre") String nombre){
		return daoUsers.updateUser(user, nombre);
	}
	

}
