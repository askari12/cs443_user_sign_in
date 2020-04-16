package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class CustomQueriesImpl implements CustomQueries {
	
	@Autowired
	private MongoTemplate template;
	
	@Override
	@Transactional
	public void updateUser(String id, User user) {
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(id));
		Update update = new Update();
		update.set("name", user.getFirstName());

		template.update(User.class).matching(query).apply(update).first();
	}

	public List<User> getAllUsers()
	{
		List<User> users = template.findAll(User.class);
		return users;
	}
}
