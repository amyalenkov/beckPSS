package net.pss.beck.service;

import net.pss.beck.domain.Users;

/**
 * Created by amyalenkov on 30.01.14.
 */
public interface UsersService {
    public Users getUserByName(String name);
}
