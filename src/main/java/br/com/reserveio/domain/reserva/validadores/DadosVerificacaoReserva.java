package br.com.reserveio.domain.reserva.validadores;

import br.com.reserveio.domain.quarto.Quarto;

import java.time.LocalDateTime;

public record DadosVerificacaoReserva(Quarto quarto, LocalDateTime dataDeEntrada, LocalDateTime dataDeSaida) {
}
