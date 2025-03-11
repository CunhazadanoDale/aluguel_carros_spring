package domain.controller;

import domain.entities.Veiculo;
import domain.repositories.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    @Autowired
    private VeiculoRepository veiculoRepository;

    // Criar veículo
    @PostMapping
    public ResponseEntity<Veiculo> criarVeiculo(@RequestBody Veiculo veiculo) {
        Veiculo novoVeiculo = veiculoRepository.save(veiculo);
        return new ResponseEntity<>(novoVeiculo, HttpStatus.CREATED);
    }

    // Listar todos os veículos
    @GetMapping
    public ResponseEntity<List<Veiculo>> listarVeiculos() {
        List<Veiculo> veiculos = veiculoRepository.findAll();
        return new ResponseEntity<>(veiculos, HttpStatus.OK);
    }

    // Obter um veículo por ID
    @GetMapping("/{id}")
    public ResponseEntity<Veiculo> obterVeiculo(@PathVariable Long id) {
        Veiculo veiculo = veiculoRepository.findById(id).orElse(null);
        if (veiculo != null) {
            return new ResponseEntity<>(veiculo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Atualizar veículo
    @PutMapping("/{id}")
    public ResponseEntity<Veiculo> atualizarVeiculo(@PathVariable Long id, @RequestBody Veiculo veiculo) {
        if (veiculoRepository.existsById(id)) {
            veiculo.setId(id);
            Veiculo veiculoAtualizado = veiculoRepository.save(veiculo);
            return new ResponseEntity<>(veiculoAtualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Deletar veículo
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarVeiculo(@PathVariable Long id) {
        if (veiculoRepository.existsById(id)) {
            veiculoRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
