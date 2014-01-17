package entities;

public class Klient extends EntBase {
    private String imie;
    private String nazwisko;
    private String miasto;
    private Integer pesel;

    public Klient() {
    }

    public Klient(String imie, String nazwisko, String miasto, Integer pesel) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.miasto = miasto;
        this.pesel = pesel;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getMiasto() {
        return miasto;
    }

    public void setMiasto(String miasto) {
        this.miasto = miasto;
    }

    public Integer getPesel() {
        return pesel;
    }

    public void setPesel(Integer pesel) {
        this.pesel = pesel;
    }
    
}
