package br.com.webscrap.model;

import java.io.Serializable;
import java.util.List;

public class Casa implements Serializable{

    private String name;
    private String url;
    private String logo;
    private int codigo;
    private List<Evento> eventos;

    public Casa(String name, String url, String logo, int codigo) {
        this.name = name;
        this.url = url;
        this.logo = logo;
        this.codigo = codigo;
    }

    public int getCodigo() { return codigo; }

    public void setCodigo(int codigo) { this.codigo = codigo; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public List<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }
}
