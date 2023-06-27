package br.com.reserveio.domain.quarto;

import br.com.reserveio.domain.hotel.Hotel;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "quarto")
public class Quarto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "numero_do_quarto")
    private String numeroDoQuarto;
    private String descricao;
    @ManyToOne
    @JoinColumn(name = "id_hotel")
    @JsonBackReference
    private Hotel hotel;
}
