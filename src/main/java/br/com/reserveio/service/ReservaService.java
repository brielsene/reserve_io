package br.com.reserveio.service;

import br.com.reserveio.domain.quarto.Quarto;
import br.com.reserveio.domain.quarto.QuartoRepository;
import br.com.reserveio.domain.reserva.DadosAgendamentoReserva;
import br.com.reserveio.domain.reserva.Reserva;
import br.com.reserveio.domain.reserva.ReservaRepository;
import br.com.reserveio.domain.reserva.validadores.DadosVerificacaoReserva;
import br.com.reserveio.domain.reserva.validadores.ValidadoresReserva;
import br.com.reserveio.domain.usuario.Usuario;
import br.com.reserveio.domain.usuario.UsuarioRepository;
import br.com.reserveio.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservaService {
    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private QuartoRepository quartoRepository;


    @Autowired
    private UsuarioRepository usuarioRepository;


    @Autowired
    private List<ValidadoresReserva>validadores;

    public void reserva (DadosAgendamentoReserva dados){
        if(!quartoRepository.existsById(dados.idQuarto())){
            throw new ValidacaoException("ID do quarto inexistente");
        }

        if(!usuarioRepository.existsById(dados.idUsuario())){
            throw new ValidacaoException("ID do usuário inexistente");
        }

        Quarto quarto = quartoRepository.getReferenceById(dados.idQuarto());
        Usuario usuario = usuarioRepository.getReferenceById(dados.idUsuario());

        DadosVerificacaoReserva dadosVerificacaoReserva = new DadosVerificacaoReserva( quarto,dados.dataDeEntrada(), dados.dataDeSaida());
        validadores.forEach(v -> v.validar(dadosVerificacaoReserva));




        Reserva reserva = new Reserva(null, dados.dataDeEntrada(), dados.dataDeSaida(), quarto, usuario, null);
        reserva.setValorTotal(calculaValorPerNoite(quarto, dados));
        reservaRepository.save(reserva);



    }

    private BigDecimal calculaValorPerNoite(Quarto quarto, DadosAgendamentoReserva dadosAgendamentoReserva){
       BigDecimal valorPerNoite = quarto.getValorPerNoite();
       BigDecimal valorPorHorario = quarto.getValorPorHora();
       BigDecimal valorTotal = BigDecimal.ZERO;

        Duration duration = Duration.between(dadosAgendamentoReserva.dataDeEntrada(), dadosAgendamentoReserva.dataDeSaida());
        Long duracao = duration.toDays();
        int horasRestantes = duration.toHoursPart();
        if(duracao > 0){

            valorTotal = valorTotal.add(valorPerNoite.multiply(BigDecimal.valueOf(duracao)));

            if(horasRestantes > 0){
                valorTotal = valorTotal.add(valorPorHorario.multiply(BigDecimal.valueOf(horasRestantes)));
            }


        }
        return valorTotal;


    }
}
