package br.com.reserveio.service;

import br.com.reserveio.domain.quarto.Quarto;
import br.com.reserveio.domain.quarto.QuartoRepository;
import br.com.reserveio.domain.reserva.DadosAgendamentoReserva;
import br.com.reserveio.domain.reserva.Reserva;
import br.com.reserveio.domain.reserva.ReservaRepository;
import br.com.reserveio.domain.usuario.Usuario;
import br.com.reserveio.domain.usuario.UsuarioRepository;
import br.com.reserveio.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservaService {
    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private QuartoRepository quartoRepository;


    @Autowired
    private UsuarioRepository usuarioRepository;

    public void reserva (DadosAgendamentoReserva dados){
        if(!quartoRepository.existsById(dados.idQuarto())){
            throw new ValidacaoException("ID do quarto inexistente");
        }

        if(!usuarioRepository.existsById(dados.idUsuario())){
            throw new ValidacaoException("ID do usuário inexistente");
        }

        Usuario usuario = usuarioRepository.getReferenceById(dados.idUsuario());
        Quarto quarto = quartoRepository.getReferenceById(dados.idQuarto());

        Reserva reserva = new Reserva(null, dados.dataDeEntrada(), dados.dataDeSaida(), quarto, usuario);
        reservaRepository.save(reserva);



    }
}
