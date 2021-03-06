package com.phraselist.components.dao.user;

import com.phraselist.components.data.hbnt.entities.User;
import com.phraselist.exceptions.login.UserException;
import com.phraselist.model.beans.user.ClientUserBean;

/**
 * 19.04.2016
 * Created by Rodion.
 */
public interface UserDAO {
    /**
     * Creates new user in system.
     *
     * @param user - new user.
     */
    User createUser(ClientUserBean user);

    /**
     * Updates existing in data base user.
     *
     * @param user - existing user with changed data.
     */
    void updateUser(User user);

    /**
     * System removes user from system, but adds it to archive for a while.
     *
     * @param id - id of user, which should be deleted.
     */
    void deleteUser(long id);

    /**
     * System retrieves user from database by its login to operate with it.
     *
     * @param login - user's login.
     * @return - user.
     */
    User getUserByLogin(String login) throws UserException;

    /**
     * System retrieves user from database by its email to operate with it.
     *
     * @param email - user's email.
     * @return - user.
     */
    User getUserByEmail(String email);
}
