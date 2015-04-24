package br.com.predojo.controller.vo;

import java.util.ArrayList;
import java.util.List;

public class JogadorVO {

	private String nome;
	private Integer assassinatos;
	private Integer mortes;
	private List<String> armas;
	private Integer sequenciaAssassinatos;
	private Boolean morreu;
	private Boolean cincoEmUm;

	public JogadorVO() {
		assassinatos = 0;
		mortes = 0;
		nome = "";
		armas = new ArrayList<String>();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getAssassinatos() {
		return assassinatos;
	}

	public void setAssassinatos(Integer assassinatos) {
		this.assassinatos = assassinatos;
	}

	public Integer getMortes() {
		return mortes;
	}

	public void setMortes(Integer mortes) {
		this.mortes = mortes;
	}

	public List<String> getArmas() {
		return armas;
	}

	public void setArmas(List<String> armas) {
		this.armas = armas;
	}

	public void addArmas (String arma) {
		armas.add(arma);
	}
	
	public Integer getSequenciaAssassinatos() {
		return sequenciaAssassinatos;
	}

	public void setSequenciaAssassinatos(Integer sequenciaAssassinatos) {
		this.sequenciaAssassinatos = sequenciaAssassinatos;
	}

	public Boolean getMorreu() {
		return morreu;
	}

	public void setMorreu(Boolean morreu) {
		this.morreu = morreu;
	}

	public Boolean getCincoEmUm() {
		return cincoEmUm;
	}

	public void setCincoEmUm(Boolean cincoEmUm) {
		this.cincoEmUm = cincoEmUm;
	}

}