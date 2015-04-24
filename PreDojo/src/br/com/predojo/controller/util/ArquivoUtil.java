package br.com.predojo.controller.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsavel e fazer mudancas no arquivo enviado pelo usuario.
 * 
 * @author Laerte Nogueira
 */
public class ArquivoUtil {

	/**
	 * Transforma o arquivo em String.
	 * 
	 * @param inputStream
	 * @return
	 * @throws IOException
	 */
	public static List<String> converterParaString(InputStream inputStream) throws IOException {  
		List<String> arquivoList = new ArrayList<String>();
        if (inputStream != null) {  
            String linha;
            try {  
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));  
                while ((linha = bufferedReader.readLine()) != null) {  
                	arquivoList.add(linha.trim());
                }  
            } finally {  
                inputStream.close();  
            }  
            return arquivoList;  
        } else {          
            return arquivoList;  
        }  
    }  

	/**
	 * Criar um novo directorio no servidor caso ele n�o exista.
	 * @param novoDiretorio
	 */
	public static void criaDiretorio(String novoDiretorio){  
	    String nomeDiretorio = null;   
	    String separador = java.io.File.separator;   
	    try {       
	         nomeDiretorio = "C:" + separador + novoDiretorio;   
	         if (!new File(nomeDiretorio).exists()) {   
	             (new File(nomeDiretorio)).mkdir();   
	         }   
	    } catch (Exception e) {   
	         e.getMessage();   
	    }  
	}  
	
}
