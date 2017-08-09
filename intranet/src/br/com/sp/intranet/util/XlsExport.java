/**
 * 
 */
package br.com.sp.intranet.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.j2ee.servlets.BaseHttpServlet;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 * @author eduardo
 *
 */
public final class XlsExport {

	/**
	 * Metodo para exportar em Excel um relatorio gerado pelo iReport
	 * 
	 * @return
	 * @throws Exception
	 */
	public static final StreamedContent exportarRelatorioXls( HttpServletRequest request, String nomeRel ) throws Exception {
		
		InputStream relatorio = null;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		List<JasperPrint> jasperPrintList = BaseHttpServlet.getJasperPrintList( request );
		//response.setHeader( "Content-disposition","attachment; filename=relatorio.xls" );
		JRXlsExporter exporter = new JRXlsExporter();
		exporter.setParameter( JRXlsExporterParameter.JASPER_PRINT_LIST, jasperPrintList );
		exporter.setParameter( JRXlsExporterParameter.OUTPUT_STREAM, out );
		exporter.setParameter( JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE );   
		exporter.setParameter( JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.TRUE );   

		try{
			exporter.exportReport();
			relatorio = new ByteArrayInputStream(out.toByteArray());
		} 
		catch ( JRException e ){
			throw new Exception( e );
		}
		return new DefaultStreamedContent( relatorio, "application/vnd.ms-excel", nomeRel );
	}
}
