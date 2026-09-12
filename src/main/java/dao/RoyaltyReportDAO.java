package dao;

import eccezioni.DatabaseException;
import model.RoyaltyReport;

import java.util.List;


public interface RoyaltyReportDAO {
    void salvaRoyaltyReport(RoyaltyReport report) throws DatabaseException;
    List<RoyaltyReport> getRoyaltyReport() throws DatabaseException;
    void eliminaRoyaltyReport(String idRoyalty) throws DatabaseException;
}