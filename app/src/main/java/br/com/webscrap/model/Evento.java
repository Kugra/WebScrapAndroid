package br.com.webscrap.model;

import java.io.Serializable;

/**
 * Created by Gustavo on 02/09/2016.
 */

public class Evento implements Serializable{

    private String url;
    private String conteudo;
    private String capa;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public String getCapa() {
        return capa;
    }

    public void setCapa(String capa) {
        this.capa = capa;
    }
}
