package domain;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="ov_chipkaart")
public class OVChipkaart {
    @Id
    private int kaart_nummer;
    @Column(name="geldig_tot")
    private Date geldig;
    private int klasse;
    private double saldo;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reiziger_id")
    private Reiziger reiziger;
    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(name="ov_chipkaart_product", joinColumns = @JoinColumn(name = "kaart_nummer"),
            inverseJoinColumns = @JoinColumn(name = "product_nummer"))
    private List<Product> producten = new ArrayList<>();

    public OVChipkaart(int kaartnummer, Date geldig, int klasse, double saldo, Reiziger reiziger) {
        this.kaart_nummer = kaartnummer;
        this.geldig = geldig;
        this.klasse = klasse;
        this.saldo = saldo;
        this.reiziger = reiziger;
    }

    public OVChipkaart(int kaartnummer, Date geldig, int klasse, double saldo) {
        this.kaart_nummer = kaartnummer;
        this.geldig = geldig;
        this.klasse = klasse;
        this.saldo = saldo;
    }

    public OVChipkaart() {

    }

    public boolean addProduct(Product product){
        if(!producten.contains(product)){
            producten.add(product);
        }
        return false;
    }

    public boolean removeProduct(Product product){
        if (producten.contains(product)){
            producten.remove(product);
        }
        return false;
    }

    public int getKaartnummer() {
        return kaart_nummer;
    }

    public Date getGeldig() {
        return geldig;
    }

    public int getKlasse() {
        return klasse;
    }

    public double getSaldo() {
        return saldo;
    }

    public Reiziger getReiziger() {
        return reiziger;
    }

    public List<Product> getProducten() {
        return producten;
    }

    public void setReiziger(Reiziger reiziger) {
        this.reiziger = reiziger;
    }

    @Override
    public String toString() {
        return "OVChipkaart{" +
                "kaart_nummer=" + kaart_nummer +
                ", geldig=" + geldig +
                ", klasse=" + klasse +
                ", saldo=" + saldo +
                '}';
    }
}
