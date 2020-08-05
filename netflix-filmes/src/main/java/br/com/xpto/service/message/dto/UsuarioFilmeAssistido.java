package br.com.xpto.service.message.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioFilmeAssistido {
	
	private Integer idFilmeAssistido;
	
	private Integer idFilme;
	
	private Integer idUsuario;
	
	private Date dataPlay;

}
