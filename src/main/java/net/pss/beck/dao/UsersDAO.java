package net.pss.beck.dao;

import net.pss.beck.domain.Users;

/**
 * Created by amyalenkov on 30.01.14.
 */
public interface UsersDAO {
    public Users getUserByName(String name);

}
