package br.com.xpto.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long idSuporteNota;
	
	private Long idTicket;
	
	private Long idUsuario;

	private String descricaoNota;

}
