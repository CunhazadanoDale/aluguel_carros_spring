package domain.controller;

import domain.entities.Cliente;
import domain.entities.ContratoAluguel;
import domain.entities.Veiculo;
import domain.repositories.ClienteRepository;
import domain.repositories.ContratoAluguelRepository;
import domain.repositories.VeiculoRepository;
import domain.services.AluguelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/contratos")
public class ContratoAluguelController {

    @Autowired
    private ContratoAluguelRepository contratoAluguelRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private VeiculoRepository veiculoRepository;
    @Autowired
    private AluguelService aluguelService;

    // Criar contrato de aluguel
    @PostMapping
    public ResponseEntity<ContratoAluguel> criarContrato(@RequestBody ContratoAluguel contratoAluguel) {
        Optional<Cliente> cliente = clienteRepository.findById(contratoAluguel.getCliente().getId());
        Optional<Veiculo> veiculo = veiculoRepository.findById(contratoAluguel.getVeiculo().getId());

        if (cliente.isPresent() && veiculo.isPresent() && veiculo.get().isDisponibilidade()) {
            contratoAluguel.setValor(aluguelService.calcularValor(contratoAluguel.getDataRetirada(), contratoAluguel.getDataDevolucao()));
            veiculo.get().setDisponibilidade(false);  // Marcar o veículo como indisponível
            veiculoRepository.save(veiculo.get());
            ContratoAluguel novoContrato = contratoAluguelRepository.save(contratoAluguel);
            return new ResponseEntity<>(novoContrato, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Listar todos os contratos
    @GetMapping
    public ResponseEntity<?> listarContratos() {
        return new ResponseEntity<>(contratoAluguelRepository.findAll(), HttpStatus.OK);
    }
}
