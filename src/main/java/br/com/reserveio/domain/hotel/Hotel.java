package br.com.reserveio.domain.hotel;

import br.com.reserveio.domain.endereco.Endereco;
import br.com.reserveio.domain.quarto.Quarto;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "hotel")
@Data
@AllArgsConstructor
@NoArgsConstructor

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
}
