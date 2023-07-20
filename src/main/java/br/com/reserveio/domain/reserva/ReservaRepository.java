package br.com.reserveio.domain.reserva;

import br.com.reserveio.domain.quarto.Quarto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    boolean existsByQuartoAndDataDeSaidaGreaterThanEqualAndDataDeEntradaLessThanEqual(
            Quarto quarto, LocalDateTime dataDeEntrada, LocalDateTime dataDeSaida);
}
