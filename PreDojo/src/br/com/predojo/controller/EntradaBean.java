package br.com.predojo.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.UploadedFile;

import br.com.predojo.controller.util.ArquivoUtil;
import br.com.predojo.controller.util.ParseArquivo;
import br.com.predojo.controller.vo.PartidaVO;

@ManagedBean
@RequestScoped
public class EntradaBean {

	private UploadedFile arquivoUpload;
	private List<String> arquivoList;
	private List<PartidaVO> partidaList;
	private Boolean contemJogo;
	
	public EntradaBean() {
		partidaList = new ArrayList<PartidaVO>();
		contemJogo = false;
	}

	/**
	 * Metodo responsavel por enviar o arquivo de log e parsear ele para grid.
	 * 
	 * @see ParseArquivo
	 */
	public void enviar() {
		if (arquivoUpload != null && !arquivoUpload.getFileName().trim().isEmpty()) {
			FacesMessage message = new FacesMessage("Enviado com sucesso!", arquivoUpload.getFileName());
			FacesContext.getCurrentInstance().addMessage(null, message);
			lerArquivo();
			ParseArquivo parseArquivo = new ParseArquivo();
			setPartidaList(parseArquivo.criarPartida(getArquivoList()));
			if (getPartidaList().size() > 0) {
				contemJogo = true;
			}
		} else {
			FacesMessage message = new FacesMessage("Arquivo", "Selecione um arquivo!");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	/**
	 * L� o arquivo enviado pelo usuario.
	 * 
	 * @see ArquivoUtil
	 */
	private void lerArquivo() {
		InputStream inputStream;
		try {
			ArquivoUtil.criaDiretorio("LogPreDojo");
			inputStream = new BufferedInputStream(arquivoUpload.getInputstream());
			File file = new File("C://LogPreDojo//" + arquivoUpload.getFileName());
			String caminho = file.getAbsolutePath();
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			while (inputStream.available() != 0) {
				fileOutputStream.write(inputStream.read());
			}
			fileOutputStream.close();
			File arquivo = new File(caminho);
			InputStream is = new FileInputStream(arquivo);
			
			setArquivoList(ArquivoUtil.converterParaString(is));
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public UploadedFile getArquivoUpload() {
		return arquivoUpload;
	}

	public void setArquivoUpload(UploadedFile arquivoUpload) {
		this.arquivoUpload = arquivoUpload;
	}

	public List<String> getArquivoList() {
		return arquivoList;
	}

	public void setArquivoList(List<String> arquivoList) {
		this.arquivoList = arquivoList;
	}

	public List<PartidaVO> getPartidaList() {
		return partidaList;
	}

	public void setPartidaList(List<PartidaVO> partidaList) {
		this.partidaList = partidaList;
	}

	public Boolean getContemJogo() {
		return contemJogo;
	}

	public void setContemJogo(Boolean contemJogo) {
		this.contemJogo = contemJogo;
	}
}