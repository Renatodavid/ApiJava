package med.voll.api.domain.consulta;

import java.time.LocalDateTime;

public record DadosdetalhamentoConsulta(Long id, Long idMedico, Long idPaciente, LocalDateTime data) {
}
