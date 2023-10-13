package domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="product")
public class Product {
    @Id
    private int product_nummer;
    private String naam;
    private String beschrijving;
    private double prijs;
    @ManyToMany(mappedBy = "producten")
    private List<OVChipkaart> ovchipkaarten = new ArrayList<>();

    public Product(int productnummer, String naam, String beschrijving, double prijs) {
        this.product_nummer = productnummer;
        this.naam = naam;
        this.beschrijving = beschrijving;
        this.prijs = prijs;
    }

    public Product() {

    }

    public boolean addOvchipkaart(OVChipkaart ovChipkaart){
        if(!ovchipkaarten.contains(ovChipkaart)){
            ovchipkaarten.add(ovChipkaart);
        }
        return false;
    }

    public boolean removeOvchipkaart(OVChipkaart ovChipkaart){
        if (ovchipkaarten.contains(ovChipkaart)){
            ovchipkaarten.remove(ovChipkaart);
        }
        return false;
    }

    public int getProductnummer() {
        return product_nummer;
    }

    public String getNaam() {
        return naam;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public double getPrijs() {
        return prijs;
    }

    public List<OVChipkaart> getOvchipkaarten() {
        return ovchipkaarten;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productnummer=" + product_nummer +
                ", naam='" + naam + '\'' +
                ", beschrijving='" + beschrijving + '\'' +
                ", prijs=" + prijs +
                ", ovchipkaarten=" + ovchipkaarten +
                '}';
    }
}
