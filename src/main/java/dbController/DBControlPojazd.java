package dbController;

import entities.Pojazd;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBControlPojazd {

    Pojazd pojazd = null;

    public void create(Pojazd pojazd) {
        this.pojazd = pojazd;
        saveInDB();
    }

    public void create(String marka, String model, Integer rocznik, Double cena, Boolean zajetosc) {
        pojazd = new Pojazd(marka, model, cena, rocznik, zajetosc);
        saveInDB();
    }

    private void saveInDB() {
        try {
            String query = "INSERT INTO `wypozyczalnia`.`pojazd` (`marka`, `model`, `cena`, `rocznik`, `zajetosc`) VALUES ('" + this.pojazd.getMarka() + "', '" + this.pojazd.getModel() + "', '" + this.pojazd.getCena() + "', '" + this.pojazd.getRocznik() + "', " + this.pojazd.getZajetosc() + ");";
            Connection con = DBConnection.getCon();
            Statement st = con.createStatement();
            st.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println("SQLException - błąd z zapisaniem zapytania Pojazd!"); 
        }
    }

    public void updateInDB(Pojazd pojazd) {
        this.pojazd = pojazd;
        try {
            String query = "UPDATE `wypozyczalnia`.`pojazd` SET `marka`='" + this.pojazd.getMarka() + "', `model`='" + this.pojazd.getModel() + "', `cena`='" + this.pojazd.getCena() + "', `rocznik`='" + this.pojazd.getRocznik() + "', `zajetosc`=" + this.pojazd.getZajetosc() + " WHERE `id`='" + this.pojazd.getId() + "';";
            Connection con = DBConnection.getCon();
            Statement st = con.createStatement();
            st.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println("SQLException - błąd z updatem zapytania Pojazd!"); 
        }
    }

    public static Pojazd getPojazdbyID(Integer id) {
        Pojazd pojazd = new Pojazd();
        try {
            Connection con = DBConnection.getCon();
            Statement st = con.createStatement();
            ResultSet res = st.executeQuery("SELECT * FROM pojazd WHERE id=" + id + ";");
            while (res.next()) {
                pojazd.setId(res.getInt("id"));
                pojazd.setMarka(res.getString("marka"));
                pojazd.setModel(res.getString("model"));
                pojazd.setCena(res.getDouble("cena"));
                pojazd.setRocznik(res.getInt("rocznik"));
                pojazd.setZajetosc(res.getBoolean("zajetosc"));
            }
        } catch (SQLException ex) {
            System.out.println("SQLException - błąd z pobraniem getPojazdbyID!"); 
        }
        return pojazd;
    }
    public Integer getLastId() {
        Integer id = null;
        try {
            Connection con = DBConnection.getCon();
            Statement st = con.createStatement();
            ResultSet res = st.executeQuery("SELECT MAX(id) FROM pojazd;");
            while (res.next()) {
                id = res.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println("SQLException - błąd z metodą getLastId pojazd!"); 
        }
        return id;
    }
    public List<Pojazd> getAllPojazdy() {
        List<Pojazd> pojazdy = new ArrayList<Pojazd>();
        try {
            Connection con = DBConnection.getCon();
            Statement st = con.createStatement();
            ResultSet res = st.executeQuery("SELECT * FROM pojazd WHERE zajetosc=false;");
            while (res.next()) {
                Pojazd pojazd = new Pojazd();
                pojazd.setId(res.getInt("id"));
                pojazd.setMarka(res.getString("marka"));
                pojazd.setModel(res.getString("model"));
                pojazd.setCena(res.getDouble("cena"));
                pojazd.setRocznik(res.getInt("rocznik"));
                pojazd.setZajetosc(res.getBoolean("zajetosc"));
                pojazdy.add(pojazd);
            }
        }  catch (SQLException ex) {
            System.out.println("SQLException - błąd z pobraniem getAllPojazdy!"); 
        }
        return pojazdy;
    }
}