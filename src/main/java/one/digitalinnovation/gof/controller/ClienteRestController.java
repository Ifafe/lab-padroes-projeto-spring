package one.digitalinnovation.gof.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import one.digitalinnovation.gof.model.Cliente;
import one.digitalinnovation.gof.model.Endereco;
import one.digitalinnovation.gof.service.ClienteService;
import one.digitalinnovation.gof.dto.ClienteDTO;
import one.digitalinnovation.gof.response.ApiResponse;
import javax.validation.Valid;

/**
 * Esse {@link RestController} representa nossa <b>Facade</b>, pois abstrai toda
 * a complexidade de integrações (Banco de Dados H2 e API do ViaCEP) em uma
 * interface simples e coesa (API REST).
 * 
 * @author falvojr
 * @author Ifafe (melhorias)
 * @since 2025
 */
@RestController
@RequestMapping("clientes")
public class ClienteRestController {

	@Autowired
	private ClienteService clienteService;

	/**
	 * Busca todos os clientes cadastrados.
	 */
	@GetMapping
	public ResponseEntity<ApiResponse<Iterable<Cliente>>> buscarTodos() {
		Iterable<Cliente> clientes = clienteService.buscarTodos();
		return ResponseEntity.ok(new ApiResponse<>(200, "Clientes encontrados", clientes));
	}

	/**
	 * Busca um cliente pelo ID.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<Cliente>> buscarPorId(@PathVariable Long id) {
		Cliente cliente = clienteService.buscarPorId(id);
		return ResponseEntity.ok(new ApiResponse<>(200, "Cliente encontrado", cliente));
	}

	/**
	 * Insere um novo cliente.
	 */
	@PostMapping
	public ResponseEntity<ApiResponse<Cliente>> inserir(@Valid @RequestBody ClienteDTO clienteDTO) {
		Cliente cliente = new Cliente();
		cliente.setNome(clienteDTO.getNome());
		Endereco endereco = new Endereco();
		endereco.setCep(clienteDTO.getCep());
		cliente.setEndereco(endereco);
		clienteService.inserir(cliente);
		return ResponseEntity.ok(new ApiResponse<>(201, "Cliente criado", cliente));
	}

	/**
	 * Atualiza um cliente existente.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<Cliente>> atualizar(@PathVariable Long id,
			@Valid @RequestBody ClienteDTO clienteDTO) {
		Cliente cliente = new Cliente();
		cliente.setNome(clienteDTO.getNome());
		Endereco endereco = new Endereco();
		endereco.setCep(clienteDTO.getCep());
		cliente.setEndereco(endereco);
		clienteService.atualizar(id, cliente);
		return ResponseEntity.ok(new ApiResponse<>(200, "Cliente atualizado", cliente));
	}

	/**
	 * Deleta um cliente pelo ID.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<Void>> deletar(@PathVariable Long id) {
		clienteService.deletar(id);
		return ResponseEntity.ok(new ApiResponse<>(200, "Cliente deletado", null));
	}
}
