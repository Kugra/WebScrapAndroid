package br.com.webscrap.model;

import java.io.Serializable;

/**
 * Created by Gustavo on 02/09/2016.
 */

public class Evento implements Serializable{

    private String url;
    private int codigo;
    private String capa;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getCapa() {
        return capa;
    }

    public void setCapa(String capa) {
        this.capa = capa;
    }
}
