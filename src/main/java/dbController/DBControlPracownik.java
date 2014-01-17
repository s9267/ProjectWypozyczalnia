package dbController;

import entities.Pracownik;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBControlPracownik {

    Pracownik pracownik = null;

    public void create(Pracownik pracownik) {
        this.pracownik = pracownik;
        saveInDB();
    }

    public void create(String imie, String nazwisko, Double wynagrodzenie, Double premia) {
        pracownik = new Pracownik(imie, nazwisko, wynagrodzenie, premia);
        saveInDB();
    }

    private void saveInDB() {
        try {
            String query = "INSERT INTO `wypozyczalnia`.`pracownik` (`imie`, `nazwisko`, `wynagrodzenie`, `premia`) VALUES ('" + this.pracownik.getImie() + "', '" + this.pracownik.getNazwisko() + "', '" + this.pracownik.getWynagrodzenie() + "', '" + this.pracownik.getPremia() + "');";
            Connection con = DBConnection.getCon();
            Statement st = con.createStatement();
            st.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println("SQLException - błąd z zapisaniem zapytania Pracownik!"); 
        }
    }

    public void updateInDB(Pracownik pracownik) {
        this.pracownik = pracownik;
        try {
            String query = "UPDATE `wypozyczalnia`.`pracownik` SET `imie`='" + this.pracownik.getImie() + "', `nazwisko`='" + this.pracownik.getNazwisko() + "', `wynagrodzenie`='" + this.pracownik.getWynagrodzenie() + "', `premia`='" + this.pracownik.getPremia() + "' WHERE `id`='" + this.pracownik.getId() + "';";
            Connection con = DBConnection.getCon();
            Statement st = con.createStatement();
            st.executeUpdate(query);
        }    catch (SQLException ex) {
            System.out.println("SQLException - błąd z updatem Pracownik!"); 
        }
    }
    public Integer getLastId() {
        Integer id = null;
        try {
            Connection con = DBConnection.getCon();
            Statement st = con.createStatement();
            ResultSet res = st.executeQuery("SELECT MAX(id) FROM pracownik;");
            while (res.next()) {
                id = res.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println("SQLException - błąd z metodą getLastId pracownik!"); 
        }
        return id;
    }
    public static Pracownik getPracownikbyID(Integer id) {
        Pracownik pracownik = new Pracownik();
        try {
            Connection con = DBConnection.getCon();
            Statement st = con.createStatement();
            ResultSet res = st.executeQuery("SELECT * FROM pracownik WHERE id=" + id + ";");
            while (res.next()) {
                pracownik.setId(res.getInt("id"));
                pracownik.setImie(res.getString("imie"));
                pracownik.setNazwisko(res.getString("nazwisko"));
                pracownik.setWynagrodzenie(res.getDouble("wynagrodzenie"));
                pracownik.setPremia(res.getDouble("premia"));
            }
        } catch (SQLException ex) {
            System.out.println("SQLException - błąd z pobraniem getPracownikbyID!"); 
        }
        return pracownik;
    }
}
