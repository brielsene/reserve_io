package br.com.reserveio.domain.hotel;

import br.com.reserveio.domain.endereco.Endereco;
import br.com.reserveio.domain.quarto.Quarto;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "hotel")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Embedded
    private Endereco endereco;
    @OneToMany(mappedBy = "hotel")
    @JsonManagedReference
    private List<Quarto>quartos;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", columnDefinition = "ENUM('DISPONIVEL', 'INDISPONIVEL')")
    private Status status;

    public Hotel(DadosCadastroHotel dados){
        this.nome = dados.nome();
        this.endereco = dados.endereco();
        this.status = Status.DISPONIVEL;
    }
}
