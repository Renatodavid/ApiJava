package med.voll.api.paciente;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.voll.api.endereco.DadosEndereco;


public record DadosCadastroPaciente(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long id,

        @NotBlank
        String nome,

        @NotBlank
        @Email
        String email,
        @NotBlank
        String telefone,
        @NotBlank
        String cpf,
        @NotNull
        @Valid
        DadosEndereco endereco



) {

}
