package br.com.xpto.service;

import java.util.Date;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.xpto.data.UsuarioFilmeAssistido;
import br.com.xpto.data.UsuarioFilmeAssistidoRepositorio;
import br.com.xpto.data.UsuarioFilmeAvaliado;
import br.com.xpto.data.UsuarioFilmesAvaliadosRepositorio;
import br.com.xpto.data.UsuarioListaFilme;
import br.com.xpto.data.UsuarioListaFilmeRepositorio;
import br.com.xpto.service.message.MachineAMQPConfig;

@Service
public class FilmeService {

	@Autowired
	private UsuarioFilmesAvaliadosRepositorio repoAvaliacao;
	
	@Autowired
	private UsuarioFilmeAssistidoRepositorio repoPlay;
	
	@Autowired
	private UsuarioListaFilmeRepositorio listaFilmesRepo;

	@Autowired
	private RabbitTemplate rabbitTemplate;

	public void save(UsuarioFilmeAvaliado req) throws Exception {
		repoAvaliacao.save(req);
	    String json = new ObjectMapper().writeValueAsString(req);
        rabbitTemplate.convertAndSend(MachineAMQPConfig.EXCHANGE_FILME, "filmes.avaliados", json);
	}

	public Iterable<UsuarioFilmeAvaliado> buscarAvaliacaoDoUsuario(Integer idFilme, Integer idUsuario) {

		if(idFilme != null && idUsuario != null) {
			return repoAvaliacao.findByIdFilmeAndIdUsuario(idFilme, idUsuario);
		}else if(idFilme != null) {
			return repoAvaliacao.findByIdFilme(idFilme);
		}else if(idUsuario != null) {
			return repoAvaliacao.findByIdUsuario(idUsuario);
		}else {
			return repoAvaliacao.findAll();
		}

	}

	public void playFilm(UsuarioFilmeAssistido filme) throws Exception {
		filme.setDataPlay(new Date());
		repoPlay.save(filme);
		String json = new ObjectMapper().writeValueAsString(filme);
		rabbitTemplate.convertAndSend(MachineAMQPConfig.EXCHANGE_FILME, "filmes.assistidos", json);
	}

	public void adicionarNaListaDeFilmesParaAssistir(UsuarioListaFilme filme) {
		listaFilmesRepo.save(filme);
		
	}

	public Iterable<UsuarioListaFilme> buscarListaDeFilmesDoUsuario(Integer idFilme, Integer idUsuario) {
		if(idFilme != null && idUsuario != null) {
			return listaFilmesRepo.findByIdFilmeAndIdUsuario(idFilme, idUsuario);
		}else if(idFilme != null) {
			return listaFilmesRepo.findByIdFilme(idFilme);
		}else if(idUsuario != null) {
			return listaFilmesRepo.findByIdUsuario(idUsuario);
		}else {
			return listaFilmesRepo.findAll();
		}
	}


}
