package br.com.xpto.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UsuarioFilmeAvaliado {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer idUsuarioFilmeAvaliado;

	private Integer idUsuario;

	private Integer idFilme;

	private Integer nota;

}
