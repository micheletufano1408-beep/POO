package dao;

import eccezioni.DatabaseException;
import model.CampagnaMarketing;

import java.util.List;

public interface CampagnaMarketingDAO {
    void salvaCampagnaMarketing(CampagnaMarketing campagna) throws DatabaseException;
    List<CampagnaMarketing> getCampagneMarketing() throws DatabaseException;
    void eliminaCampagna(String idCampagna) throws DatabaseException;

}
