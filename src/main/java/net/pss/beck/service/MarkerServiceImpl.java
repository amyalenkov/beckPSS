package net.pss.beck.service;

import net.pss.beck.dao.MarkersDAO;
import net.pss.beck.domain.Markers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by amyalenkov on 30.01.14.
 */
@Service
public class MarkerServiceImpl implements MarkerService{

    @Autowired
    MarkersDAO markersDAO;

    @Override
    @Transactional
    public void addMarker(Markers marker) {
        markersDAO.addMarker(marker);
    }
}
