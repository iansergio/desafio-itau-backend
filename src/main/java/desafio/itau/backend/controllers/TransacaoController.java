package desafio.itau.backend.controllers;

import desafio.itau.backend.entities.Transacao;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    private List<Transacao> transacoes = new ArrayList<>();
    LocalDateTime agora = LocalDateTime.now();

    @PostMapping
    public ResponseEntity<?> insertTransacao(@RequestBody Transacao transacao) {
        if (transacao.getDataHora().isAfter( OffsetDateTime.now()) || transacao.getValor() < 0) {
            return ResponseEntity
                    .unprocessableEntity()
                    .body("");
        }
        transacoes.add(transacao);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<Transacao>> getAllTransacoes() {
        return new ResponseEntity<>(transacoes, HttpStatus.OK);
    }

}
