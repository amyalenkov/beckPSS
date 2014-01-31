package net.pss.beck.service;

import net.pss.beck.dao.PushedMarkersDAO;
import net.pss.beck.domain.PushedMarkers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by amyalenkov on 30.01.14.
 */
@Service
public class PushedMarkersServiceImpl implements PushedMarkersService {

    @Autowired
    PushedMarkersDAO pushedMarkersDAO;

    @Override
    @Transactional
    public void addPushedMarkers(PushedMarkers pushedMarker) {
        pushedMarkersDAO.addPushedMarkers(pushedMarker);
    }


}
