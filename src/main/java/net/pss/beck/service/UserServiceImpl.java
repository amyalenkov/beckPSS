package net.pss.beck.service;

import net.pss.beck.dao.UsersDAO;
import net.pss.beck.domain.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by amyalenkov on 30.01.14.
 */
@Service
public class UserServiceImpl implements UsersService{

    @Autowired
    private UsersDAO usersDAO;

    @Override
    @Transactional
    public Users getUserByName(String name) {
        return usersDAO.getUserByName(name);
    }
}
