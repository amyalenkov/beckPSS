package net.pss.beck.dao;

import net.pss.beck.domain.Referee;

import java.util.List;

/**
 * Created by amyalenkov on 07.01.14.
 */
public interface RefereeDAO {
    public void addReferee(Referee referee);
    public Referee getRefereeByLastNameData(Referee referee);

    public List<Referee> getAllReferee();

    public Referee getRefereeById(Referee referee);
}
