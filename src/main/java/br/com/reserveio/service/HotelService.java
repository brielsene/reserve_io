package br.com.reserveio.service;

import br.com.reserveio.domain.hotel.*;
import br.com.reserveio.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelService {
    @Autowired
    private HotelRepository hotelRepository;

    public void cadastrar(DadosCadastroHotel dados){
        Hotel hotel = new Hotel(dados);
        hotelRepository.save(hotel);

    }

    public List<DadosDeHoteis>listarHoteis(){
        List<Hotel> hoteis = hotelRepository.findByStatus(Status.DISPONIVEL);
        return hoteis.stream().map(DadosDeHoteis::new).toList();
    }

    public void deletarHotel(Long id){
        Optional<Hotel> byId = hotelRepository.findById(id);
        if(byId.isPresent()){
            Hotel hotel = byId.get();
            hotel.setStatus(Status.INDISPONIVEL);
            System.out.println(hotel.getStatus());
            hotelRepository.save(hotel);
            return;
        }
        throw new ValidacaoException("o  hotel com ID: "+id+", não existe");
    }

    public void atualiza (DadosAtualizaHotel dados){
        Optional<Hotel> byId = hotelRepository.findById(dados.id());
        System.out.println(byId.get().getId());
        if(byId.isPresent()){
            Hotel hotel = byId.get();
            if(dados.nome() != null){
                hotel.setNome(dados.nome());
            }
            if(dados.endereco() != null){
                hotel.setEndereco(dados.endereco());
            }


            hotelRepository.save(hotel);
            return;
        }
        throw new ValidacaoException("Hotel com id: "+ dados.id()+", não existe");
    }
}
