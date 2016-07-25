package com.phraselist.components.services.user.hbnt.impl;

import com.phraselist.components.data.hbnt.entities.Role;
import com.phraselist.components.data.hbnt.util.HibernateUtil;
import com.phraselist.components.services.user.UserService;
import com.phraselist.components.data.hbnt.entities.User;
import com.phraselist.exceptions.login.UserException;
import com.phraselist.model.beans.user.ClientUserBean;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * 25.07.2016
 * Created by Rodion.
 */
public class UserServiceImpl {
    public void createUser(ClientUserBean user) {
        User convertedUser = convertUser(user);
        SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(convertedUser);
        session.getTransaction().commit();
        sessionFactory.close();
    }

    public void updateUser(User user) {

    }

    public void deleteUser(long id) {

    }

    public User getUserByLogin(String login) throws UserException {
        return null;
    }

    public User getUserByEmail(String email) {
        return null;
    }

    private User convertUser(ClientUserBean user) {
        User convertedUser = new User();
        convertedUser.setEmail(user.getEmail());
        convertedUser.setLogin(user.getLogin());
        convertedUser.setName(user.getName());
        convertedUser.setLastname(user.getLastname());
        convertedUser.setPass(user.getPassword());
        convertedUser.setRole(getRole(2));
        return convertedUser;
    }

    public Role getRole(long id) {
        SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Role role = (Role) session.get(Role.class, id);
        transaction.commit();
        return role;
    }
}
