package ro.ase.semdam;

import java.io.Serializable;
import java.util.Date;

public class BiletAvion implements Serializable {

    private String destinatie;
    private Date dataZbor;
    private float pret;
    private String companie; //Tarom, Ryanair, WizzAir
    private String clasaBilet; //ECONOMY, BUSINESS

    public BiletAvion(String destinatie, Date dataZbor, float pret, String companie, String clasaBilet) {
        this.destinatie = destinatie;
        this.dataZbor = dataZbor;
        this.pret = pret;
        this.companie = companie;
        this.clasaBilet = clasaBilet;
    }

    public String getDestinatie() {
        return destinatie;
    }

    public void setDestinatie(String destinatie) {
        this.destinatie = destinatie;
    }

    public Date getDataZbor() {
        return dataZbor;
    }

    public void setDataZbor(Date dataZbor) {
        this.dataZbor = dataZbor;
    }

    public float getPret() {
        return pret;
    }

    public void setPret(float pret) {
        this.pret = pret;
    }

    public String getCompanie() {
        return companie;
    }

    public void setCompanie(String companie) {
        this.companie = companie;
    }

    public String getClasaBilet() {
        return clasaBilet;
    }

    public void setClasaBilet(String clasaBilet) {
        this.clasaBilet = clasaBilet;
    }

    @Override
    public String toString() {
        return "BiletAvion{" +
                "destinatie='" + destinatie + '\'' +
                ", dataZbor=" + dataZbor +
                ", pret=" + pret +
                ", companie='" + companie + '\'' +
                ", clasaBilet='" + clasaBilet + '\'' +
                '}';
    }
}
