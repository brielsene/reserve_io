package br.com.reserveio.domain.quarto;

import br.com.reserveio.domain.hotel.Hotel;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "quarto")
@Data
@AllArgsConstructor
@NoArgsConstructor
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

    public Quarto(DadosCadastroQuarto dados){
        this.numeroDoQuarto = dados.numeroDoQuarto();
        this.descricao = dados.descricao();

    }

}
