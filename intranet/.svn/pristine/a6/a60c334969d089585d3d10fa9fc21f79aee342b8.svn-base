package br.com.sp.intranet.util;

import javax.servlet.http.HttpServletRequest;

import org.primefaces.model.StreamedContent;

public final class GeracaoRelatorio {

	/**
	 * Metodo para gerar o relatorio do ireport exportando para pdf ou xls, conforme escolha do usuario em tela
	 * 
	 * @param request
	 * @param nomeRel
	 * @return
	 * @throws Exception 
	 */
	public static final StreamedContent gerarRelatorio( HttpServletRequest request, String nomeRel ) throws Exception{
		
		String formatoImpressao = request.getParameter( "formatoRelatorio" );
		if( ConstantUtils.FORMATO_IMPRESSAO_XLS.equals( formatoImpressao ) ){
			return XlsExport.exportarRelatorioXls( request, nomeRel + ".xls" );
		}
		else if( ConstantUtils.FORMATO_IMPRESSAO_RTF.equals( formatoImpressao ) ){
			return RtfExport.exportarRelatorioDoc( request, nomeRel + ".rtf" );
		}
		else{
			return PdfExport.exportarRelatorioPdf( request, nomeRel + ".pdf" );
		}
	}
}
