package com.invillia.amigoSecreto.request;

import com.invillia.amigoSecreto.domain.DoublesFriends;

import java.util.ArrayList;
import java.util.List;

public class DoubleFriendsRequest {

    private String mensagem;
    private List<DoublesFriends> listaAmigos;

    public DoubleFriendsRequest() {
        this.listaAmigos = new ArrayList<DoublesFriends>();
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public List<DoublesFriends> getListaAmigos() {
        return listaAmigos;
    }

    public void setListaAmigos(List<DoublesFriends> listaAmigos) {
        this.listaAmigos = listaAmigos;
    }

}
