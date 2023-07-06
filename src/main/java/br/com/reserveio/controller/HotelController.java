package br.com.reserveio.controller;

import br.com.reserveio.domain.hotel.DadosAtualizaHotel;
import br.com.reserveio.domain.hotel.DadosCadastroHotel;
import br.com.reserveio.domain.hotel.DadosDeHoteis;
import br.com.reserveio.service.HotelService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/hotel")
@SecurityRequirement(name = "bearer-key")
public class HotelController {
    @Autowired
    private HotelService hotelService;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroHotel dados){
        hotelService.cadastrar(dados);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @GetMapping
    public ResponseEntity<List<DadosDeHoteis>>listarHoteis(){
        return ResponseEntity.ok(hotelService.listarHoteis());
    }

    @PutMapping("/{id}")
    public ResponseEntity excluirHotel(@PathVariable("id")Long id){
        hotelService.deletarHotel(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity atualizarHotel(@RequestBody DadosAtualizaHotel dados){
        hotelService.atualiza(dados);
        return ResponseEntity.ok().build();
    }
}
