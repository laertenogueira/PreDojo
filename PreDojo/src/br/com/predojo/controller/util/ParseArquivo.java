package br.com.predojo.controller.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.predojo.controller.vo.JogadorVO;
import br.com.predojo.controller.vo.PartidaVO;

/**
 * Classe responsavel por interpretar o arquivo de log e salva-los nos seus
 * respectivos objetos.
 * 
 * @author Laerte Nogueira
 */
public class ParseArquivo {

	public ParseArquivo() {
	}

	public List<PartidaVO> criarPartida(List<String> arquivoList) {
		List<PartidaVO> partidaList = new ArrayList<PartidaVO>();
		List<JogadorVO> jogadorList = new ArrayList<JogadorVO>();
		PartidaVO partidaVO = new PartidaVO();
		for (String linhaLog : arquivoList) {

			if (linhaLog.isEmpty()) {
				continue;
			}
			if (linhaLog.contains("started")) {
				partidaVO = new PartidaVO();
				jogadorList = new ArrayList<JogadorVO>();
				/** Corta a linha do inicio do jogo e coloca a data em que o jogo comecou **/
				String[] dataInfoPartida = linhaLog.split(" - ");
				DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
				try {
					Date date = (Date) formatter.parse(dataInfoPartida[0]);
					partidaVO.setInicioJogo(date);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				/** Fim data inicioJogo **/

				String[] infoPartida = dataInfoPartida[1].split(" ");

				partidaVO.setIdPartida(infoPartida[2]);
				continue;
			}

			while (!linhaLog.contains("ended")) {
				Boolean jogadorAssassinoExiste = false;
				Boolean jogadorMortoExiste = false;
				String[] dataInfoJogador = linhaLog.split(" - ");
				String[] infoJogador = dataInfoJogador[1].split(" ");

				JogadorVO jogadorAssassino = new JogadorVO();
				JogadorVO jogadorMorto = new JogadorVO();
				for (JogadorVO verificarJogador : jogadorList) {
					if (verificarJogador.getNome().trim().contains(infoJogador[0].trim())) {
						jogadorAssassino = verificarJogador;
						jogadorAssassinoExiste = true;
					} else if (verificarJogador.getNome().trim().contains(infoJogador[2].trim())) {
						jogadorMorto = verificarJogador;
						jogadorMortoExiste = true;
					}
				}

				if (!linhaLog.contains("<WORLD>")) {
					jogadorAssassino.setNome(infoJogador[0]);
					jogadorMorto.setNome(infoJogador[2]);
					if (infoJogador[0].contains(jogadorAssassino.getNome())) {
						jogadorAssassino.setAssassinatos(jogadorAssassino.getAssassinatos() + 1);
						jogadorAssassino.addArmas(infoJogador[4].trim().toUpperCase());
					}
				}

				if (infoJogador[2].contains(jogadorMorto.getNome())) {
					jogadorMorto.setMortes(jogadorMorto.getMortes() + 1);
				}

				if (!jogadorAssassinoExiste && !jogadorAssassino.getNome().trim().isEmpty()) {
					jogadorList.add(jogadorAssassino);
				}
				if (!jogadorMortoExiste && !jogadorMorto.getNome().trim().isEmpty()) {
					jogadorList.add(jogadorMorto);
				}

				partidaVO.setJogadorList(jogadorList);
				break;
			}

			if (linhaLog.contains("ended")) {
				
				/** Corta a linha do fim do jogo e coloca a data em que o jogo acabou **/
				String[] dataInfoPartida = linhaLog.split(" - ");
				DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
				try {
					Date date = (Date) formatter.parse(dataInfoPartida[0]);
					partidaVO.setFimJogo(date);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				/** Fim data fimJogo **/
				partidaList.add(partidaVO);
			}

		}

		return partidaList;
	}
}
