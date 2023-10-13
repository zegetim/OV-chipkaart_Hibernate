package domain;

import javax.persistence.*;

@Entity
@Table(name="adres")
public class Adres {
    @Id
    @Column(name = "adres_id")
    private int adres_id;
    private String postcode;
    private String huisnummer;
    private String straat;
    private String woonplaats;
    @OneToOne
    @JoinColumn(name="reiziger_id")
    private Reiziger reiziger;

    public Adres(int id, String postcode, String huisnummer, String straat, String woonplaats) {
        this.adres_id = id;
        this.postcode = postcode;
        this.huisnummer = huisnummer;
        this.straat = straat;
        this.woonplaats = woonplaats;
    }

    public Adres(int id, String postcode, String huisnummer, String straat, String woonplaats, Reiziger reiziger) {
        this.adres_id = id;
        this.postcode = postcode;
        this.huisnummer = huisnummer;
        this.straat = straat;
        this.woonplaats = woonplaats;
        this.reiziger=reiziger;

    }

    public Adres() {

    }

    public int getId() {
        return adres_id;
    }

    public String getPostcode() {
        return postcode;
    }


    public String getStraat() {
        return straat;
    }

    public String getWoonplaats() {
        return woonplaats;
    }

    public void setReiziger(Reiziger reiziger) {
        this.reiziger = reiziger;
    }

    @Override
    public String toString() {
        return "Adres{" +
                "id=" + adres_id +
                ", postcode='" + postcode + '\'' +
                ", huisnummer=" + huisnummer +
                ", straat='" + straat + '\'' +
                ", woonplaats='" + woonplaats + '\'' +
                '}';
    }
}

