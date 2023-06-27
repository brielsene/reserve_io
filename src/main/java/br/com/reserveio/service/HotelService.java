package br.com.reserveio.service;

import br.com.reserveio.domain.hotel.DadosCadastroHotel;
import br.com.reserveio.domain.hotel.Hotel;
import br.com.reserveio.domain.hotel.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelService {
    @Autowired
    private HotelRepository hotelRepository;

    public void cadastrar(DadosCadastroHotel dados){
        Hotel hotel = new Hotel(dados);
        hotelRepository.save(hotel);

    }
}
