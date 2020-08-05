package br.com.xpto.data;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UsuarioSuporteNotaTicket {
	
	private Long idSuporteNota;
	
	private Long idTicket;
	
	private Long idUsuario;

	private String descricaoNota;

}
