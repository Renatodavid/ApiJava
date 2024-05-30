package med.voll.api.controler;


import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.consulta.AgendaDeConsultas;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.consulta.DadosdetalhamentoConsulta;
import med.voll.api.domain.consulta.MotivoCancelamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("consultas")
public class ConsultaController {

    @Autowired
    private AgendaDeConsultas agenda;

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DadosAgendamentoConsulta dados ){
       agenda.agendar(dados);
        return ResponseEntity.ok(new DadosdetalhamentoConsulta(null, null, null, null));
    }

    public ResponseEntity cancelar(@RequestBody @Valid DadosCancelamentoConsulta dados ){
        agenda.cancelar(dados);
        return ResponseEntity.noContent().build();
    }

}
