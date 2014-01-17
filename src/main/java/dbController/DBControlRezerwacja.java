package dbController;

import entities.Klient;
import entities.Pojazd;
import entities.Pracownik;
import entities.Rezerwacja;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DBControlRezerwacja {
    
    Rezerwacja rezerwacja = null;
    
    public void create(Rezerwacja rezerwacja) throws SQLException {
        this.rezerwacja = rezerwacja;
        if (saveInDB()) {
            DBControlPojazd dbPoj = new DBControlPojazd();
            Pojazd pojazd = rezerwacja.getPojazd();
            pojazd.setZajetosc(true);
            dbPoj.updateInDB(pojazd);
            
            DBControlPracownik dbPrac = new DBControlPracownik();
            Pracownik pracownik = rezerwacja.getPracownik();
            pracownik.setPremia(pracownik.getPremia() + rezerwacja.getKoszt() * 0.03);
            dbPrac.updateInDB(pracownik);
        }
    }
    
    public void create(Date startRezerwacji, Date koniecRezerwacji, Pojazd pojazd, Klient klient, Pracownik pracownik, Double koszt) throws SQLException {
        rezerwacja = new Rezerwacja(startRezerwacji, koniecRezerwacji, pojazd, klient, pracownik, koszt);
        if (saveInDB()) {
            DBControlPojazd dbPoj = new DBControlPojazd();
            pojazd.setZajetosc(true);
            dbPoj.updateInDB(pojazd);
            
            DBControlPracownik dbPrac = new DBControlPracownik();
            pracownik.setPremia(pracownik.getPremia() + (koszt * 0.03));
            dbPrac.updateInDB(pracownik);
        }
    }
    
    private boolean saveInDB() throws SQLException {
        try {
            String query = "INSERT INTO `wypozyczalnia`.`rezerwacja` (`startRezerwacji`, `koniecRezerwacji`, `id_poj`, `id_kli`, `id_pra`, `koszt`) VALUES ('" + this.rezerwacja.getStartRezerwacji() + "', '" + this.rezerwacja.getKoniecRezerwacji() + "', '" + this.rezerwacja.getPojazd().getId() + "', '" + this.rezerwacja.getKlient().getId() + "', '" + this.rezerwacja.getPracownik().getId() + "', '" + this.rezerwacja.getKoszt() + "');";
            Connection con = DBConnection.getCon();
            Statement st = con.createStatement();
            st.executeUpdate(query);
            return true;
        } catch (SQLException ex) {
            System.out.println("SQLException - błąd z zapisaniem zapytania Rezerwacja!"); 
        }
        return false;
    }
    
    public void updateInDB(Rezerwacja rezerwacja) {
        this.rezerwacja = rezerwacja;
        try {
            String query = "UPDATE `wypozyczalnia`.`rezerwacja` SET `startRezerwacji`='" + this.rezerwacja.getStartRezerwacji() + "', `koniecRezerwacji`='" + this.rezerwacja.getKoniecRezerwacji() + "', `id_poj`='" + this.rezerwacja.getPojazd().getId() + "', `id_kli`='" + this.rezerwacja.getKlient().getId() + "', `id_pra`='" + this.rezerwacja.getPracownik().getId() + "', `koszt`='" + this.rezerwacja.getKoszt() + "' WHERE `id`='" + this.rezerwacja.getId() + "';";
            Connection con = DBConnection.getCon();
            Statement st = con.createStatement();
            st.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println("SQLException - błąd z updatem Rezerwacja!"); 
        }
    }
    public Integer getLastId() {
        Integer id = null;
        try {
            Connection con = DBConnection.getCon();
            Statement st = con.createStatement();
            ResultSet res = st.executeQuery("SELECT MAX(id) FROM rezerwacja;");
            while (res.next()) {
                id = res.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println("SQLException - błąd z metodą getLastId rezerwacja!"); 
        }
        return id;
    }
    
    public void deleteFromDB(Rezerwacja rezerwacja) {
        this.rezerwacja = rezerwacja;
        try {
            String query = "DELETE FROM `wypozyczalnia`.`rezerwacja` WHERE `id`= " + this.rezerwacja.getId() + ";";
            Connection con = DBConnection.getCon();
            Statement st = con.createStatement();
            st.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println("SQLException - błąd z deletem Rezerwacja!"); 
        }
    }
    
    public List<Rezerwacja> getRezerwacje() {
        List<Rezerwacja> rezerwacje = new ArrayList<Rezerwacja>();
        try {
            Connection con = DBConnection.getCon();
            Statement st = con.createStatement();
            ResultSet res = st.executeQuery("SELECT * FROM rezerwacja;");
            while (res.next()) {
                Rezerwacja rezerwacja = new Rezerwacja();
                rezerwacja.setId(res.getInt("id"));
                rezerwacja.setKlient(DBControlKlient.getKlientbyID(res.getInt("id_kli")));
                rezerwacja.setKoniecRezerwacji(res.getDate("koniecRezerwacji"));
                rezerwacja.setKoszt(res.getDouble("koszt"));
                rezerwacja.setPojazd(DBControlPojazd.getPojazdbyID(res.getInt("id_poj")));
                rezerwacja.setPracownik(DBControlPracownik.getPracownikbyID(res.getInt("id_pra")));
                rezerwacja.setStartRezerwacji(res.getDate("startRezerwacji"));
                rezerwacje.add(rezerwacja);
            }
        } catch (SQLException ex) {
            System.out.println("SQLException - błąd z pobraniem getRezerwacje()!"); 
        }
        return rezerwacje;
    }
    
    public List<Rezerwacja> getRezerwacjebyKlient(Klient klient) {
        List<Rezerwacja> rezerwacje = new ArrayList<Rezerwacja>();
        try {
            Connection con = DBConnection.getCon();
            Statement st = con.createStatement();
            ResultSet res = st.executeQuery("SELECT * FROM rezerwacja WHERE id_kli=" + klient.getId() + ";");
            while (res.next()) {
                Rezerwacja rezerwacja = new Rezerwacja();
                rezerwacja.setId(res.getInt("id"));
                rezerwacja.setKlient(DBControlKlient.getKlientbyID(res.getInt("id_kli")));
                rezerwacja.setKoniecRezerwacji(res.getDate("koniecRezerwacji"));
                rezerwacja.setKoszt(res.getDouble("koszt"));
                rezerwacja.setPojazd(DBControlPojazd.getPojazdbyID(res.getInt("id_poj")));
                rezerwacja.setPracownik(DBControlPracownik.getPracownikbyID(res.getInt("id_pra")));
                rezerwacja.setStartRezerwacji(res.getDate("startRezerwacji"));
                rezerwacje.add(rezerwacja);
            }
        } catch (SQLException ex) {
            System.out.println("SQLException - błąd z pobraniem getRezerwacjebyKlient!"); 
        }
        return rezerwacje;
    }
}