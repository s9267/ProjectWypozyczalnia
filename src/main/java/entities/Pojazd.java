package entities;

public class Pojazd extends EntBase {

    public Pojazd(String marka, String model, Double cena, Integer rocznik, Boolean zajetosc) {
        this.marka = marka;
        this.model = model;
        this.cena = cena;
        this.rocznik = rocznik;
        this.zajetosc = zajetosc;
    }

    public Pojazd() {
    }
    
    private String marka;
    private String model;
    private Double cena;
    private Integer rocznik;
    private Boolean zajetosc;

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Double getCena() {
        return cena;
    }

    public void setCena(Double cena) {
        this.cena = cena;
    }

    public Integer getRocznik() {
        return rocznik;
    }

    public void setRocznik(Integer rocznik) {
        this.rocznik = rocznik;
    }

    public Boolean getZajetosc() {
        return zajetosc;
    }

    public void setZajetosc(Boolean zajetosc) {
        this.zajetosc = zajetosc;
    }
}
