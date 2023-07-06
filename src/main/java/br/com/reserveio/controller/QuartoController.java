package br.com.reserveio.controller;

import br.com.reserveio.domain.quarto.DadosCadastroQuarto;
import br.com.reserveio.domain.quarto.DadosDetalhamentoQuartos;
import br.com.reserveio.domain.quarto.DadosEdicaoQuarto;
import br.com.reserveio.service.QuartoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quarto")
@SecurityRequirement(name = "bearer-key")
public class QuartoController {
    @Autowired
    private QuartoService quartoService;

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroQuarto dados){
        quartoService.cadastrar(dados);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    public ResponseEntity<List<DadosDetalhamentoQuartos>>listar(){
        return ResponseEntity.ok(quartoService.listarQuartos());
    }

    @PutMapping
    public ResponseEntity editar(@RequestBody DadosEdicaoQuarto dados){
        quartoService.editarQuarto(dados);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable("id")Long id){
        quartoService.deletarQuarto(id);
        return ResponseEntity.noContent().build();
    }
}
