package br.com.reserveio.domain.reserva.validadores;

import br.com.reserveio.domain.reserva.DadosAgendamentoReserva;
import br.com.reserveio.domain.reserva.ReservaRepository;
import br.com.reserveio.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ValidadorDataDisponivel implements ValidadoresReserva {

    @Autowired
    private ReservaRepository reservaRepository;

    public void validar(DadosVerificacaoReserva dados){
        boolean valida = reservaRepository.existsByQuartoAndDataDeSaidaGreaterThanEqualAndDataDeEntradaLessThanEqual(dados.quarto(), dados.dataDeEntrada(), dados.dataDeSaida());
        if(valida){
            throw new ValidacaoException("Data indisponível para reserva neste quarto");
        }



    }
}
