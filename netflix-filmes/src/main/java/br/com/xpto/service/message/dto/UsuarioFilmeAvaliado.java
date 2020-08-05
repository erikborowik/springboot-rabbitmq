package br.com.xpto.service.message.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioFilmeAvaliado {
	
	private Integer idUsuarioFilmeAvaliado;

	private Integer idUsuario;

	private Integer idFilme;

	private Long nota;

}
