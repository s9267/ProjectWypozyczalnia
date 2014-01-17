package dbController;

import entities.Klient;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBControlKlient {

    Klient klient = null;

    public void create(Klient klient) {
        this.klient = klient;
        saveInDB();
    }

    public void create(String imie, String nazwisko, String miasto, int pesel) {
        klient = new Klient(imie, nazwisko, miasto, pesel);
        saveInDB();
    }

    private void saveInDB() {
        try {
            String query = "INSERT INTO `wypozyczalnia`.`klient` (`imie`, `nazwisko`, `miasto`, `pesel`) VALUES ('" + this.klient.getImie() + "', '" + this.klient.getNazwisko() + "', '" + this.klient.getMiasto() + "', '" + this.klient.getPesel() + "');";
            Connection con = DBConnection.getCon();
            Statement st = con.createStatement();
            st.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println("SQLException - błąd z zapisaniem zapytania Klient!"); 
        }
    }
    public Integer getLastId() {
        Integer id = null;
        try {
            Connection con = DBConnection.getCon();
            Statement st = con.createStatement();
            ResultSet res = st.executeQuery("SELECT MAX(id) FROM klient;");
            while (res.next()) {
                id = res.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println("SQLException - błąd z metodą getLastId klient!"); 
        }
        return id;
    }
    public static Klient getKlientbyID(Integer id) {
        Klient klient = new Klient();
        try {
            Connection con = DBConnection.getCon();
            Statement st = con.createStatement();
            ResultSet res = st.executeQuery("SELECT * FROM klient WHERE id=" + id + ";");
            while (res.next()) {
                klient.setId(res.getInt("id"));
                klient.setImie(res.getString("imie"));
                klient.setMiasto(res.getString("miasto"));
                klient.setNazwisko(res.getString("nazwisko"));
                klient.setPesel(res.getInt("pesel"));
            }
        } catch (SQLException ex) {
            System.out.println("SQLException - błąd z pobraniem KlientabyID!"); 
        }
        return klient;
    }
}