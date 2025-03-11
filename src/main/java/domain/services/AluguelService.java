package domain.services;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class AluguelService {

    private static final double PRECO_DIARIO = 100.0; // Exemplo de preço diário

    public double calcularValor(LocalDate dataRetirada, LocalDate dataDevolucao) {
        long diasAluguel = ChronoUnit.DAYS.between(dataRetirada, dataDevolucao);
        return diasAluguel * PRECO_DIARIO;
    }
}
