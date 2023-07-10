package br.com.reserveio.controller;

import br.com.reserveio.domain.reserva.DadosAgendamentoReserva;
import br.com.reserveio.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reserva")
public class ReservaController {
    @Autowired
    private ReservaService reservaService;

    @PostMapping
    public ResponseEntity reserva(@RequestBody DadosAgendamentoReserva dados){
        reservaService.reserva(dados);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
