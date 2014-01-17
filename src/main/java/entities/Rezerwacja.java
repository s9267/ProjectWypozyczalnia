package entities;

import java.util.Date;

public class Rezerwacja extends EntBase {
    
    private Date startRezerwacji;
    private Date koniecRezerwacji;
    private Pojazd pojazd;
    private Klient klient;
    private Double koszt;
    private Pracownik pracownik;

    public Rezerwacja() {
    }

    public Rezerwacja(Date startRezerwacji, Date koniecRezerwacji, Pojazd pojazd, Klient klient, Pracownik pracownik, Double koszt) {
        this.startRezerwacji = startRezerwacji;
        this.koniecRezerwacji = koniecRezerwacji;
        this.pojazd = pojazd;
        this.klient = klient;
        this.koszt = koszt;
        this.pracownik = pracownik;
    }
    
    public Pracownik getPracownik() {
        return pracownik;
    }

    public void setPracownik(Pracownik pracownik) {
        this.pracownik = pracownik;
    }

    public Date getStartRezerwacji() {
        return startRezerwacji;
    }

    public void setStartRezerwacji(Date startRezerwacji) {
        this.startRezerwacji = startRezerwacji;
    }

    public Date getKoniecRezerwacji() {
        return koniecRezerwacji;
    }

    public void setKoniecRezerwacji(Date koniecRezerwacji) {
        this.koniecRezerwacji = koniecRezerwacji;
    }

    public Pojazd getPojazd() {
        return pojazd;
    }

    public void setPojazd(Pojazd pojazd) {
        this.pojazd = pojazd;
    }

    public Klient getKlient() {
        return klient;
    }

    public void setKlient(Klient klient) {
        this.klient = klient;
    }

    public Double getKoszt() {
        return koszt;
    }

    public void setKoszt(Double koszt) {
        this.koszt = koszt;
    }
}
