package com.oniriccreations.nonutapp.ui.actividades;

public class Cadena {
    private String externo;
    private String interno;
    private String creencia;

    public Cadena(String externo, String interno, String creencia){
        this.externo = externo;
        this.interno = interno;
        this.creencia = creencia;
    }

    public String getExterno() {
        return externo;
    }

    public void setExterno(String externo) {
        this.externo = externo;
    }

    public String getInterno() {
        return interno;
    }

    public void setInterno(String interno) {
        this.interno = interno;
    }

    public String getCreencia() {
        return creencia;
    }

    public void setCreencia(String creencia) {
        this.creencia = creencia;
    }
}
