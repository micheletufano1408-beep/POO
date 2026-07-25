package dao;

import eccezioni.DatabaseException;
import model.RoyaltyReport;


public interface RoyaltyReportDAO {
    void salvaRoyaltyReport(RoyaltyReport report) throws DatabaseException;
}