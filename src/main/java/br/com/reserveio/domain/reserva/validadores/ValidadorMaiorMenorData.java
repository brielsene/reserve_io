package br.com.reserveio.domain.reserva.validadores;

import br.com.reserveio.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMaiorMenorData implements ValidadoresReserva{


    @Override
    public void validar(DadosVerificacaoReserva dadosVerificacaoReserva) {
        if(dadosVerificacaoReserva.dataDeEntrada().getDayOfMonth() > dadosVerificacaoReserva.dataDeSaida().getDayOfMonth()){
            throw new ValidacaoException("A Data de saída está antes da data de entrada");
        }

        if(dadosVerificacaoReserva.dataDeSaida().toLocalDate().isBefore(dadosVerificacaoReserva.dataDeEntrada().toLocalDate()) ||
                dadosVerificacaoReserva.dataDeSaida().toLocalDate().isEqual(dadosVerificacaoReserva.dataDeEntrada().toLocalDate())){
            throw new ValidacaoException("O horário de entrada está igual ou depois do de saída");
        }





    }
}
