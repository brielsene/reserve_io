package br.com.reserveio.domain.reserva;

import br.com.reserveio.domain.quarto.Quarto;
import br.com.reserveio.domain.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "reserva")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dataDeEntrada;
    private LocalDateTime dataDeSaida;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_quarto")
    @JsonBackReference
    private Quarto quarto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    @JsonBackReference
    private Usuario usuario;

    private BigDecimal valorTotal;






}
