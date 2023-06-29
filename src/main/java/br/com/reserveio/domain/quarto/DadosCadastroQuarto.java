package br.com.reserveio.domain.quarto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroQuarto(
        @NotBlank
        String numeroDoQuarto,
        @NotBlank
        String descricao,
        @NotNull
        Long idHotel) {
}
