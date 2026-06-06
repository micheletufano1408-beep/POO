package dao;

import model.Release;
import java.util.List;

public interface ReleaseDAO {
    void salvaRelease(Release release);
    List<Release> getTutteLeRelease();
}
