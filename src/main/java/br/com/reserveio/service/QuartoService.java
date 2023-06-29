package br.com.reserveio.service;

import br.com.reserveio.domain.hotel.Hotel;
import br.com.reserveio.domain.hotel.HotelRepository;
import br.com.reserveio.domain.quarto.DadosCadastroQuarto;
import br.com.reserveio.domain.quarto.DadosDetalhamentoQuartos;
import br.com.reserveio.domain.quarto.Quarto;
import br.com.reserveio.domain.quarto.QuartoRepository;
import br.com.reserveio.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuartoService {
    @Autowired
    private QuartoRepository quartoRepository;

    @Autowired
    private HotelRepository hotelRepository;

    public void cadastrar(DadosCadastroQuarto dados){
        Optional<Hotel> byId = hotelRepository.findById(dados.idHotel());
        if(byId.isPresent()){
            Hotel hotel = byId.get();
            Quarto quarto = new Quarto(dados);
            quarto.setHotel(hotel);
            quartoRepository.save(quarto);
            return;
        }
        throw new ValidacaoException("O Hotel com id: "+dados.idHotel()+", está indisponível");


    }

    public List<DadosDetalhamentoQuartos>listarQuartos(){
        List<Quarto> all = quartoRepository.findAll();
        return all.stream().map(DadosDetalhamentoQuartos::new).toList();

    }

}
