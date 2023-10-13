package domain;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="reiziger")
public class Reiziger {
    @Id
    private int reiziger_id;
    private String voorletters;
    private String tussenvoegsel;
    private String achternaam;
    private Date geboortedatum;
    @OneToOne(mappedBy = "reiziger",cascade = CascadeType.ALL)
    private Adres adres;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "reiziger")
    private List<OVChipkaart> ovChipkaarten = new ArrayList<>();

    public Reiziger(int id, String voorletters, String tussenvoegsel, String achternaam, Date geboortedatum) {
        this.reiziger_id = id;
        this.voorletters = voorletters;
        this.tussenvoegsel = tussenvoegsel;
        this.achternaam = achternaam;
        this.geboortedatum = geboortedatum;
    }

    public Reiziger() {
    }

    public boolean voegToeOVChipkaart(OVChipkaart ovChipkaart){
        if(!ovChipkaarten.contains(ovChipkaart)){
            ovChipkaarten.add(ovChipkaart);
        }
        return false;
    }

    public boolean verwijderOVChipkaart(OVChipkaart ovChipkaart){
        if(ovChipkaarten.contains(ovChipkaart)){
            ovChipkaarten.remove(ovChipkaart);
        }
        return false;
    }
    public int getId() {
        return reiziger_id;
    }

    public String getVoorletters() {
        return voorletters;
    }

    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public Date getGeboortedatum() {
        return geboortedatum;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    public List<OVChipkaart> getOvChipkaarten() {
        return ovChipkaarten;
    }

    public void setOvChipkaarten(List<OVChipkaart> ovChipkaarten) {
        this.ovChipkaarten = ovChipkaarten;
    }

    @Override
    public String toString() {
        return "Reiziger{" +
                "reiziger_id=" + reiziger_id +
                ", voorletters='" + voorletters + '\'' +
                ", tussenvoegsel='" + tussenvoegsel + '\'' +
                ", achternaam='" + achternaam + '\'' +
                ", geboortedatum=" + geboortedatum +
                ", adres=" + adres +
                ", ovChipkaarten=" + ovChipkaarten +
                '}';
    }
}
