package com.invillia.amigoSecreto.service;

import com.invillia.amigoSecreto.domain.DoublesFriends;
import com.invillia.amigoSecreto.domain.Friend;
import com.invillia.amigoSecreto.exception.DoubleFriendsNotFoundException;
import com.invillia.amigoSecreto.exception.DoubleFriendsOddListException;
import com.invillia.amigoSecreto.repository.DoubleFriendsRepository;
import com.invillia.amigoSecreto.repository.FriendRepository;
import com.invillia.amigoSecreto.request.DoubleFriendsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class DrawService {

    @Autowired
    private FriendService friendService;

    @Autowired
    private DoubleFriendsRepository doublesFriendsRepository;

    @Autowired
    private FriendRepository friendRepository;

    List<Friend> listaAmigos;
    DoubleFriendsRequest duplasSorteadasResponse = new DoubleFriendsRequest();

    public DoubleFriendsRequest sorteio() throws DoubleFriendsOddListException {
        popularListaAmigos();

        int numeroAleatorio1 = 0;
        int numeroAleatorio2 = 0;

        if(verificaTamanhoParOuImpar()== false){
            throw new DoubleFriendsOddListException("the list cannot be odd");
        }else{
            int numeroDuplas = listaAmigos.size() / 2;

            while(!(this.duplasSorteadasResponse.getListaAmigos().size() == numeroDuplas)){
                do{
                    numeroAleatorio1 = (int)(Math.random() * verificaTamanhoDaLista() ) + 0;
                    numeroAleatorio2 = (int)(Math.random() * verificaTamanhoDaLista() ) + 0;

                }while(numeroAleatorio1 == numeroAleatorio2);

                guardaDuplaSorteada(numeroAleatorio1, numeroAleatorio2);
            }
        }

        return duplasSorteadasResponse;
    }

    public void guardaDuplaSorteada(int numeroAleatorio1,int numeroAleatorio2){
        Friend friend1, friend2;

        friend1 = listaAmigos.get(numeroAleatorio1);
        friend2 = listaAmigos.get(numeroAleatorio2);

        DoublesFriends doublesFriends = new DoublesFriends();

        boolean sorteado = verificarSeAmigoJaFoiSorteado(friend1, friend2);

        if(sorteado == false){
            doublesFriends.setName1(friend1.getName());
            doublesFriends.setName2(friend2.getName());

            save(doublesFriends);

            this.duplasSorteadasResponse.getListaAmigos().add(doublesFriends);
        }
    }

    private boolean verificarSeAmigoJaFoiSorteado(Friend friend1, Friend friend2) {
        boolean sorteado = false;

        for (DoublesFriends friend : this.duplasSorteadasResponse.getListaAmigos()) {
            if (friend.getName1().equals(friend1.getName()) ||
                    friend.getName2().equals(friend1.getName())) {
                sorteado = true;
                break;
            } else if (friend.getName2().equals(friend2.getName()) ||
                    friend.getName2().equals(friend1.getName())) {
                sorteado = true;
            }
        }

        return sorteado;
    }


    @Transactional
    public void save(DoublesFriends doublesFriends){
            doublesFriendsRepository.save(doublesFriends);
    }

    public void popularListaAmigos(){
        this.listaAmigos = friendService.buscar();
    }

    public DoublesFriends findAllId(Long id){
        DoublesFriends doubles = doublesFriendsRepository.findById(id).orElseThrow(() -> new DoubleFriendsNotFoundException("DoubleFreind Not found"));
        return doubles;
    }

    public int verificaTamanhoDaLista(){
        return this.listaAmigos.size();
    }

    public boolean verificaTamanhoParOuImpar(){
        int tamanhoDaLista = verificaTamanhoDaLista();
        if(tamanhoDaLista %2!=0){
            return false;
        }
        return true;
    }
}
