package br.com.reserveio.domain.quarto;

import br.com.reserveio.domain.hotel.Hotel;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "quarto")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
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
    @Enumerated(EnumType.STRING)
    @Column(name = "status", columnDefinition = "ENUM('DISPONIVEL', 'INDISPONIVEL', 'MANUTENCAO')")
    private Status status;

    private BigDecimal valorPerNoite;
    private BigDecimal valorPorHora;


    public Quarto(DadosCadastroQuarto dados){
        this.numeroDoQuarto = dados.numeroDoQuarto();
        this.descricao = dados.descricao();
        this.status = Status.DISPONIVEL;

    }

//    public Quarto(DadosEdicaoQuarto dados){
//        this.numeroDoQuarto = dados.numeroDoQuarto();
//        this.descricao = dados.descricao();
//
//    }

}
