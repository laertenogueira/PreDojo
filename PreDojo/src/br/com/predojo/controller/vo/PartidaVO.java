package br.com.predojo.controller.vo;

import java.util.Date;
import java.util.List;

public class PartidaVO {

	private Date inicioJogo;
	private String idPartida;
	private List<JogadorVO> jogadorList;
	private Date fimJogo;

	public Date getInicioJogo() {
		return inicioJogo;
	}

	public void setInicioJogo(Date inicioJogo) {
		this.inicioJogo = inicioJogo;
	}

	public String getIdPartida() {
		return idPartida;
	}

	public void setIdPartida(String idPartida) {
		this.idPartida = idPartida;
	}

	public Date getFimJogo() {
		return fimJogo;
	}

	public void setFimJogo(Date fimJogo) {
		this.fimJogo = fimJogo;
	}


	public List<JogadorVO> getJogadorList() {
		return jogadorList;
	}

	public void setJogadorList(List<JogadorVO> jogadorList) {
		this.jogadorList = jogadorList;
	}

}