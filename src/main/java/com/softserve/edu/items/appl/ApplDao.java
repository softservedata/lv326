package com.softserve.edu.items.appl;

import com.softserve.edu.items.dao.ItemDao;
import com.softserve.edu.items.dao.RoleDao;
import com.softserve.edu.items.dao.UserDao;
import com.softserve.edu.items.entity.Item;
import com.softserve.edu.items.entity.Role;
import com.softserve.edu.items.entity.User;

public class ApplDao {

	public static void main(String[] args) {
		System.out.println("Start ...");
		//
		RoleDao roleDao = new RoleDao();
		UserDao userDao = new UserDao();
		ItemDao itemDao = new ItemDao();
		//
		// Insert
		// Role
		roleDao.insert(new Role(1L, "Administrator"));
		roleDao.insert(new Role(2L, "User"));
		//
		// User
		userDao.insert(new User(1L, "ivan", "qwerty", "adminIvan", 1L));
		userDao.insert(new User(2L, "petro", "qwerty", "userPentro", 2L));
		//
		// Item
		itemDao.insert(new Item(1L, "firstIvan", "ivanDecriptions", 1L));
		itemDao.insert(new Item(2L, "firstPetro", "petroDecriptions", 2L));
		//itemDao.insert(new Item(1L, "deleteIvan", "deleteivanDecriptions", 1L));
		//
		// Select
		//System.out.println(roleDao.getAll());
		//System.out.println(roleDao.getById(1L));
		//System.out.println(roleDao.getByFieldName("name", "User"));
		//
		//System.out.println(userDao.getAll());
		//System.out.println(userDao.getById(1L));
		//System.out.println(userDao.getByFieldName("login", "ivan"));
		//
		//System.out.println(itemDao.getAll());
		//System.out.println(itemDao.getById(1L));
		//System.out.println(itemDao.getByFieldName("name", "firstIvan"));
		//
		// Update
		//roleDao.updateByEntity(new Role(3L, "Administrator1"));
		//roleDao.updateByFieldName("name", "User1", "name", "User");
		//
		//userDao.updateByEntity(new User(4L, "petro", "qwerty1", "userPentro1", 2L));
		//userDao.updateByFieldName("name", "adminIvanSuper", "login", "ivan");
		//
		//itemDao.updateByEntity(new Item(2L, "firstPetro1", "petroDecriptions1", 2L));
		//itemDao.updateByFieldName("description", "petroDescriptionSuper", "name", "firstPetro1");
		//
		// Delete
		//roleDao.delete(new Role(12L, "User1"));
		//roleDao.deleteByFieldName("name", "User1");
		//roleDao.deleteById(3L);
		//
		//userDao.delete(new User(4L, "petro", "qwerty1", "userPentro1", 2L));
		//userDao.deleteByFieldName("name", "adminIvanSuper");
		//userDao.deleteById(5L);
		//
		//itemDao.delete(new Item(4L, "deleteIvan", "deleteivanDecriptions", 1L));
		//itemDao.deleteByFieldName("name", "deleteIvan1");
		//itemDao.deleteById(5L);
		//
		System.out.println("Done");
	}

}
