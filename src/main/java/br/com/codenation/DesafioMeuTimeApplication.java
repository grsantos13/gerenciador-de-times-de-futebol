package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import br.com.codenation.desafio.annotation.Desafio;
import br.com.codenation.desafio.app.MeuTimeInterface;
import br.com.codenation.desafio.exceptions.*;

public class DesafioMeuTimeApplication implements MeuTimeInterface {
	private List<Time> timeLista;

	public DesafioMeuTimeApplication() {
		timeLista = new ArrayList<Time>();
	}

	@Desafio("incluirTime")
	public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
		if (getTimePorId(id) != null) {
			throw new IdentificadorUtilizadoException();
		}

		Time time = new Time();
		time.setId(id);
		time.setNome(nome);
		time.setDataCriacao(dataCriacao);
		time.setCorUniformePrincipal(corUniformePrincipal);
		time.setCorUniformeSecundario(corUniformeSecundario);

		timeLista.add(time);
	}

	@Desafio("incluirJogador")
	public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {
		if (getJogadorPorId(id) != null) {
			throw new IdentificadorUtilizadoException();
		}

		Time time = getTimePorId(idTime);
		if (time == null) {
			throw new TimeNaoEncontradoException();
		}

		Jogador jogador = new Jogador();
		jogador.setId(id);
		jogador.setIdTime(idTime);
		jogador.setNome(nome);
		jogador.setDataNascimento(dataNascimento);
		jogador.setNivelHabilidade(nivelHabilidade);
		jogador.setSalario(salario);

		time.setListaJogador(jogador);
	}

	@Desafio("definirCapitao")
	public void definirCapitao(Long idJogador) {
		Jogador jogador = getJogadorPorId(idJogador);

		if (jogador == null) {
			throw new JogadorNaoEncontradoException();
		}

		Time time = getTimePorId(jogador.getIdTime());

		if (time == null) {
			throw new TimeNaoEncontradoException();
		}

		time.setJogadorCapitao(jogador);
	}

	@Desafio("buscarCapitaoDoTime")
	public Long buscarCapitaoDoTime(Long idTime) {
		Time time = getTimePorId(idTime);

		if (time == null) {
			throw new TimeNaoEncontradoException();
		}

		Jogador capitao = time.getJogadorCapitao();

		if (capitao == null) {
			throw new CapitaoNaoInformadoException();
		}

		return capitao.getId();
	}

	@Desafio("buscarNomeJogador")
	public String buscarNomeJogador(Long idJogador) {
		Jogador jogador = getJogadorPorId(idJogador);

		if (jogador == null) {
			throw new JogadorNaoEncontradoException();
		}

		return jogador.getNome();
	}

	@Desafio("buscarNomeTime")
	public String buscarNomeTime(Long idTime) {
		Time time = getTimePorId(idTime);

		if (time == null) {
			throw new TimeNaoEncontradoException();
		}

		return time.getNome();
	}

	@Desafio("buscarJogadoresDoTime")
	public List<Long> buscarJogadoresDoTime(Long idTime) {
		Time time = getTimePorId(idTime);

		if (time == null) {
			throw new TimeNaoEncontradoException();
		}

		List<Long> jogadoresDoTimeLista = time.getListaJogador()
				.stream()
				.sorted(Comparator.comparing(Jogador::getId))
				.map(jogador -> new Long(jogador.getId()))
				.collect(Collectors.toList());

		if (jogadoresDoTimeLista.size() > 0) {
			return jogadoresDoTimeLista;
		} else {
			return new ArrayList<Long>();
		}
	}

	@Desafio("buscarMelhorJogadorDoTime")
	public Long buscarMelhorJogadorDoTime(Long idTime) {
		Time time = getTimePorId(idTime);

		if (time == null) {
			throw new TimeNaoEncontradoException();
		}

		Jogador jogadorMaisHabilidosoDoTime = null;

		List<Jogador> jogadoresDoTimeLista = time.getListaJogador();

		if (jogadoresDoTimeLista.size() > 0) {
			jogadorMaisHabilidosoDoTime = jogadoresDoTimeLista
					.stream()
					.max(Comparator.comparing(Jogador::getNivelHabilidade))
					.get();
		}

		if (jogadorMaisHabilidosoDoTime == null) {
			throw new JogadorNaoEncontradoException();
		}

		return jogadorMaisHabilidosoDoTime.getId();
	}

	@Desafio("buscarJogadorMaisVelho")
	public Long buscarJogadorMaisVelho(Long idTime) {
		Time time = getTimePorId(idTime);

		if (time == null) {
			throw new TimeNaoEncontradoException();
		}

		Jogador jogadorMaisVelhoDoTime = null;

		List<Jogador> jogadoresDoTimeLista = time.getListaJogador()
				.stream()
				.sorted(Comparator.comparing(Jogador::getDataNascimento))
				.collect(Collectors.toList());

		if (jogadoresDoTimeLista.size() > 0) {
			jogadorMaisVelhoDoTime = jogadoresDoTimeLista
					.stream()
					.findFirst()
					.orElse(null);
		}

		if (jogadorMaisVelhoDoTime == null) {
			throw new JogadorNaoEncontradoException();
		}

		return jogadorMaisVelhoDoTime.getId();
	}

	@Desafio("buscarTimes")
	public List<Long> buscarTimes() {
		List<Long> timeLista = getTimeLista()
				.stream()
				.sorted(Comparator.comparing(Time::getId))
				.map(time -> new Long(time.getId()))
				.collect(Collectors.toList());

		if (timeLista != null) {
			return timeLista;
		} else {
			return new ArrayList<Long>();
		}
	}

	@Desafio("buscarJogadorMaiorSalario")
	public Long buscarJogadorMaiorSalario(Long idTime) {
		Time time = getTimePorId(idTime);

		if (time == null) {
			throw new TimeNaoEncontradoException();
		}

		Jogador jogadorComMaiorSalarioDoTime = null;

		List<Jogador> ordenaSalarioJogadoresDoTimeLista = time.getListaJogador()
				.stream()
				.sorted(Comparator.comparing(Jogador::getSalario).reversed())
				.collect(Collectors.toList());

		if (ordenaSalarioJogadoresDoTimeLista.size() > 0) {
			jogadorComMaiorSalarioDoTime = ordenaSalarioJogadoresDoTimeLista
					.stream()
					.findFirst()
					.orElse(null);
		}

		if (jogadorComMaiorSalarioDoTime == null) {
			throw new JogadorNaoEncontradoException();
		}

		return jogadorComMaiorSalarioDoTime.getId();
	}

	@Desafio("buscarSalarioDoJogador")
	public BigDecimal buscarSalarioDoJogador(Long idJogador) {
		List<Jogador> todosJogadoresLista = getJogadorLista();

		Jogador jogador = null;

		if (todosJogadoresLista.size() > 0) {
			jogador = todosJogadoresLista
					.stream()
					.filter(linha -> Long.compare(linha.getId(), idJogador) == 0)
					.findAny()
					.orElse(null);
		}

		if (jogador == null) {
			throw new JogadorNaoEncontradoException();
		}

		return jogador.getSalario();
	}

	@Desafio("buscarTopJogadores")
	public List<Long> buscarTopJogadores(Integer top) {
		List<Jogador> todosJogadoresLista = getJogadorLista();

		if (todosJogadoresLista.size() == 0) {
			new ArrayList<Long>();
		}

		List<Jogador> ordenaMelhoresJogadoresLista = todosJogadoresLista
				.stream()
				.sorted(Comparator.comparing(Jogador::getNivelHabilidade).reversed())
				.collect(Collectors.toList());

		List<Long> topJogadoresLista = ordenaMelhoresJogadoresLista
				.stream()
				.limit(top)
				.map(s -> new Long(s.getId()))
				.collect(Collectors.toList());

		return topJogadoresLista;
	}

	@Desafio("buscarCorCamisaTimeDeFora")
	public String buscarCorCamisaTimeDeFora(Long timeDaCasa, Long timeDeFora) {
		Time timeCasa = getTimePorId(timeDaCasa);
		if (timeCasa == null) {
			throw new TimeNaoEncontradoException();
		}

		Time timeFora = getTimePorId(timeDeFora);
		if (timeFora == null) {
			throw new TimeNaoEncontradoException();
		}

		if (timeCasa.getCorUniformePrincipal() == timeFora.getCorUniformePrincipal()) {
			return timeFora.getCorUniformeSecundario();
		} else {
			return timeFora.getCorUniformePrincipal();
		}
	}

	private List<Time> getTimeLista() {
		return timeLista;
	}

	private List<Jogador> getJogadorLista() {
		List<Time> timeLista = getTimeLista();

		List<Jogador> jogadorLista = new ArrayList<Jogador>();
		for (Time time : timeLista) {
			if (time.getListaJogador().size() > 0) {
				jogadorLista.addAll(time.getListaJogador());
			}
		}

		return jogadorLista;
	}

	private Time getTimePorId(Long idTime) {
		List<Time> timeLista = getTimeLista();
		Time time = null;

		if (timeLista.size() > 0) {
			time = timeLista
					.stream()
					.filter(linha -> Long.compare(linha.getId(), idTime) == 0)
					.findAny()
					.orElse(null);
		}

		return time;
	}

	private Jogador getJogadorPorId(Long idJogador) {
		List<Jogador> jogadorLista = getJogadorLista();
		Jogador jogador = null;

		if (jogadorLista.size() > 0) {
			jogador = jogadorLista
					.stream()
					.filter(linha -> Long.compare(linha.getId(), idJogador) == 0)
					.findAny()
					.orElse(null);
		}


		return jogador;
	}
}
