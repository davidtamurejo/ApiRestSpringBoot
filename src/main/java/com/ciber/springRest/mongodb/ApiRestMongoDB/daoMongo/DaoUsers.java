/**
 * 
 */
package com.ciber.springRest.mongodb.ApiRestMongoDB.daoMongo;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import com.ciber.springRest.mongodb.ApiRestMongoDB.beans.Usuario;

/**
 * @author ciber
 *
 */
@Repository
public class DaoUsers {

	@Autowired
	MongoTemplate mongo;

	public List<Usuario> findAll() {
		return mongo.findAll(Usuario.class, "usuarios2");
	}

	public Collection<Usuario> searchUsers(String text) {

		return (Collection<Usuario>) mongo.find(Query.query(new Criteria()
				.orOperator(Criteria.where("nombre").regex(text, "i"), Criteria.where("apellidos").regex(text, "i"))),
				Usuario.class, "usuarios2");
	}

	public Usuario addUser(Usuario user) {
		try {
			mongo.insert(user, "usuarios2");
			return user;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}

	}

	public Usuario findUser(Usuario user) {
		return mongo.findOne(queryFindByName(user), Usuario.class, "usuario2");
	}

	public Usuario findUser(String user) {
		return mongo.findOne(queryFindByName(user), Usuario.class, "usuarios2");
	}

	public String deleteUser(String nombre) {
		if (findUser(nombre)==null) {
			return "Usuario no encontrado";
		}
		mongo.findAllAndRemove(queryFindByName(nombre), Usuario.class, "usuarios2");
		return "Usuario borrado";
	}

	public Usuario updateUser(Usuario user, String nombre) {
		deleteUser(nombre);
		addUser(user);
		return user;
	}

	public Query queryFindByName(Usuario user) {
		Query query = new Query();
		Criteria criteria = new Criteria();
		criteria.andOperator(criteria.where("nombre").is(user.getNombre()));
		query.addCriteria(criteria);
		return query;
	}

	public Query queryFindByName(String user) {
		Query query = new Query();
		Criteria criteria = new Criteria();
		criteria.andOperator(criteria.where("nombre").is(user));
		query.addCriteria(criteria);
		return query;
	}

}
