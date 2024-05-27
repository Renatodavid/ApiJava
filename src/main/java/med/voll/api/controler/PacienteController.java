package med.voll.api.controler;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.paciente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.awt.print.Pageable;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    public ResponseEntity cadastrar(@RequestBody @Valid DadosAtualizacaoPaciente dados, UriComponentsBuilder uriBuilder){
        var paciente = new Paciente();

        repository.save(paciente);
        var uri =  uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoPaciente(paciente));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemPaciente>> listar(@PageableDefault(page = 0, size = 10, sort = { "nome" }) Pageable paginacao) {

        var page = repository.findAllByAtivoTrue((org.springframework.data.domain.Pageable) paginacao).map(DadosListagemPaciente::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoPaciente dados) {

        var paciente = repository.getReferenceById(dados.id());

        paciente.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity remover(@PathVariable Long id) {
        var paciente = repository.getReferenceById(id);
        paciente.inativar();

        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id){
        var paciente = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));


    }

}
