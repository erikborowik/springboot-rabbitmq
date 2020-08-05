package br.com.xpto.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Filme {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer idFilme;
	
	private Integer idGeneroFilme;

	private String name;
	
	private String detalhes;
	
	private Long quantidadeAssistida;
	
	private Long nota;
	
	@Transient
	private Long media;
	
	public Long getMedia() {
		
		if(nota != null && nota > 0 && quantidadeAssistida != null && quantidadeAssistida > 0) {
			return nota/quantidadeAssistida;
		}else {
			return Long.valueOf(0);
		}
	}
	
	public void somaNota(Long nota) {
		if(this.nota == null) {
			this.nota = nota;
		}else {
			this.nota = Long.sum(this.nota, nota);
		}
	}

	public void addQuantidadeAssistida() {
		if(this.quantidadeAssistida != null) {
			this.quantidadeAssistida++;
		}else {
			this.quantidadeAssistida = Long.valueOf(1);
		}
	}

}
