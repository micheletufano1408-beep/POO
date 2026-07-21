package implementazionePostgresDAO;

import dao.UtenteDAO;

public class UtenteImplementazioneMockDAO implements UtenteDAO {
    @Override
    public boolean verificaLogin(String username, String password){
        if(username.equals("admin") && password.equals("admin")) {
            System.out.println("Login avvenuto con successo");
            return true;
        } else{
            System.out.println("credenziali errate");
            return false;
        }
    }
}
