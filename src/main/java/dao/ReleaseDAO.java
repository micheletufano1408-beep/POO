package dao;

import eccezioni.DatabaseException;
import model.Release;
import java.util.List;

public interface ReleaseDAO {
    void salvaRelease(Release release) throws DatabaseException;
    List<Release> getTutteLeRelease() throws DatabaseException;
    List<Release> getReleaseDiArtista(String idArtista) throws DatabaseException;
}
