package wypozyczalnia;

import dbController.DBConnection;
import dbController.DBControlKlient;
import dbController.DBControlPojazd;
import dbController.DBControlPracownik;
import dbController.DBControlRezerwacja;
import entities.Klient;
import entities.Pojazd;
import entities.Pracownik;
import entities.Rezerwacja;

import java.sql.Date;
import java.sql.SQLException;

public class Wypozyczalnia {

    /**
     * @param args the command line arguments
     */
    @SuppressWarnings("deprecation")
	public static void main(String[] args) {
        try {
            // TODO code application logic here
            DBControlPojazd dbPojazdy = new DBControlPojazd();
            Pojazd pojazd = new Pojazd();
            pojazd.setCena(100.0);
            pojazd.setMarka("SAAB");
            pojazd.setModel("9-5");
            pojazd.setRocznik(2003);
            pojazd.setZajetosc(false);
            
            dbPojazdy.create(pojazd);   // utworzenie pojazdu metoda przyjmujaca obiekt
            pojazd = DBControlPojazd.getPojazdbyID(dbPojazdy.getLastId());
            
            dbPojazdy.create("Mercedes", "Klasa S", 2008, 200.0, true); // utworzenie pojazdu metoda przyjmujaca parametry
            Pojazd pojazd1 = DBControlPojazd.getPojazdbyID(dbPojazdy.getLastId());
            
            DBControlKlient dbKlienci = new DBControlKlient();
            Klient klient = new Klient();
            klient.setImie("Eugeniusz");
            klient.setNazwisko("Wangler");
            klient.setMiasto("Gdansk");
            klient.setPesel(914573745);

            dbKlienci.create(klient);
            klient = DBControlKlient.getKlientbyID(dbKlienci.getLastId());
            
            dbKlienci.create("Leszek", "Szreder", "Warszawa", 761436784);
            Klient klient1 = DBControlKlient.getKlientbyID(dbKlienci.getLastId());

            DBControlPracownik dbPracownik = new DBControlPracownik();
            Pracownik pracownik = new Pracownik();
            pracownik.setImie("Ariel");
            pracownik.setNazwisko("Skwara");
            pracownik.setPremia(0.0);
            pracownik.setWynagrodzenie(1300.0);

            dbPracownik.create(pracownik);
            pracownik = DBControlPracownik.getPracownikbyID(dbPracownik.getLastId());

            dbPracownik.create("Mariusz", "Michalowski", 1500.0, 0.0);
            Pracownik pracownik1 = DBControlPracownik.getPracownikbyID(dbPracownik.getLastId());

            DBControlRezerwacja dbRezerwacje = new DBControlRezerwacja();
            
            Date start = new Date(113, 12, 12);
            Date stop = new Date(113, 12, 14);

            dbRezerwacje.create(start, stop, pojazd, klient1, pracownik, 2000.0);  
           
            Rezerwacja rezerwacja = new Rezerwacja(); 
            rezerwacja.setKlient(klient);
            rezerwacja.setKoniecRezerwacji(stop);
            rezerwacja.setKoszt(1000.0);
            rezerwacja.setPojazd(pojazd1);
            rezerwacja.setPracownik(pracownik1);
            rezerwacja.setStartRezerwacji(start);
            
            dbRezerwacje.create(rezerwacja);

            System.out.println("Firma posiada nastepujące wolne pojazdy: ");
            for (Pojazd poj : dbPojazdy.getAllPojazdy()) {
                System.out.println("Pojazd o id: " + poj.getId() + " to: " + poj.getMarka() + " " + poj.getModel() + " z roku: " + poj.getRocznik());
            }

            System.out.println("Ilość rezerwacji w systemie: " + dbRezerwacje.getRezerwacje().size());

            System.out.println("Klient: " + klient1.getImie() + " " + klient1.getNazwisko() + " posiada nastepujące rezerwacje: ");
            for (Rezerwacja rez : dbRezerwacje.getRezerwacjebyKlient(klient1)) {	
                System.out.println("Rezerwacja o id: " + rez.getId());
            }
            System.out.print("Klient: " + klient.getImie() +  " " + klient.getNazwisko() + " unieważnił swoje rezerwacje");
            for (Rezerwacja rez : dbRezerwacje.getRezerwacjebyKlient(klient)) {	
                dbRezerwacje.deleteFromDB(rez);
                pojazd.setZajetosc(false);
                dbPojazdy.updateInDB(pojazd);
            	}
        } catch (SQLException ex) {
            System.out.println("Błąd z wykonaniem kodu w funkcji main.");
        }
        DBConnection.killConnection();
    }
}
