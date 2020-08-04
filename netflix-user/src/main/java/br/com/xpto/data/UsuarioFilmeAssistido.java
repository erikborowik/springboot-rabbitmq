package br.com.xpto.data;

import java.util.Date;

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
public class UsuarioFilmeAssistido {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer idFilmeAssistido;
	
	private Integer idFilme;
	
	private Date dataPlay;
}
