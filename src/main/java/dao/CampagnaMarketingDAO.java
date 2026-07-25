package dao;

import eccezioni.DatabaseException;
import model.CampagnaMarketing;

public interface CampagnaMarketingDAO {
    void salvaCampagnaMarketing(CampagnaMarketing campagna) throws DatabaseException;
}
