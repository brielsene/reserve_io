package br.com.reserveio.domain.quarto;

import br.com.reserveio.domain.hotel.Hotel;

public record DadosDetalhamentoQuartos(Long id, String numeroDoQuarto, String descricao, String hotel) {
    public DadosDetalhamentoQuartos(Quarto quarto){
        this(quarto.getId(), quarto.getNumeroDoQuarto(), quarto.getDescricao(), quarto.getHotel().getNome());
    }
}
