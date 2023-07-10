package br.com.reserveio.domain.reserva;

import java.time.LocalDateTime;

public record DadosAgendamentoReserva(LocalDateTime dataDeEntrada, LocalDateTime dataDeSaida, Long idQuarto, Long idUsuario) {
}
