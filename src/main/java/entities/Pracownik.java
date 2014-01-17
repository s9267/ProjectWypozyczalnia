package entities;

public class Pracownik extends EntBase {
    
    private String imie;
    private String nazwisko;
    private Double wynagrodzenie;
    private Double premia;

    public Pracownik() {
    }

    public Pracownik(String imie, String nazwisko, Double wynagrodzenie, Double premia) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.wynagrodzenie = wynagrodzenie;
        this.premia = premia;
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

    public Double getWynagrodzenie() {
        return wynagrodzenie;
    }

    public void setWynagrodzenie(Double wynagrodzenie) {
        this.wynagrodzenie = wynagrodzenie;
    }

    public Double getPremia() {
        return premia;
    }

    public void setPremia(Double premia) {
        this.premia = premia;
    }
}
