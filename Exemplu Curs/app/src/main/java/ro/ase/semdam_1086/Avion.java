package ro.ase.semdam_1086;

import java.io.Serializable;
import java.util.Date;

public class Avion implements Serializable {

    private String producator;
    private Date dataFabricatiei;
    private int nrLocuri;
    private String model;//A320, A330, 787, 737
    private boolean esteDefect;//DA, NU

    public Avion(String producator, Date dataFabricatiei, int nrLocuri, String model, boolean esteDefect) {
        this.producator = producator;
        this.dataFabricatiei = dataFabricatiei;
        this.nrLocuri = nrLocuri;
        this.model = model;
        this.esteDefect = esteDefect;
    }

    public String getProducator() {
        return producator;
    }

    public void setProducator(String producator) {
        this.producator = producator;
    }

    public Date getDataFabricatiei() {
        return dataFabricatiei;
    }

    public void setDataFabricatiei(Date dataFabricatiei) {
        this.dataFabricatiei = dataFabricatiei;
    }

    public int getNrLocuri() {
        return nrLocuri;
    }

    public void setNrLocuri(int nrLocuri) {
        this.nrLocuri = nrLocuri;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public boolean isEsteDefect() {
        return esteDefect;
    }

    public void setEsteDefect(boolean esteDefect) {
        this.esteDefect = esteDefect;
    }

    @Override
    public String toString() {
        return "Avion{" +
                "producator='" + producator + '\'' +
                ", dataFabricatiei=" + dataFabricatiei +
                ", nrLocuri=" + nrLocuri +
                ", model='" + model + '\'' +
                ", esteDefect=" + esteDefect +
                '}';
    }
}
