package one.digitalinnovation.gof.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ClienteDTO {
      @NotNull(message = "O nome não pode ser nulo.")
      @NotBlank(message = "O nome não pode ser vazio.")
      private String nome;
      @NotNull(message = "O CEP não pode ser nulo.")
      @NotBlank(message = "O CEP não pode ser vazio.")
      private String cep;

      public String getNome() {
            return nome;
      }

      public void setNome(String nome) {
            this.nome = nome;
      }

      public String getCep() {
            return cep;
      }

      public void setCep(String cep) {
            this.cep = cep;
      }
}
