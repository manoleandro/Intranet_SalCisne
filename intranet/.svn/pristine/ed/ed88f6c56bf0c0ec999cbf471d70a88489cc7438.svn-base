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
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.j2ee.servlets.BaseHttpServlet;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 * @author eduardo
 *
 */
public final class PdfExport {

	/**
	 * Metodo para exportar em pdf um relatorio gerado pelo iReport
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public static final StreamedContent exportarRelatorioPdf( HttpServletRequest request, String nomeRel ) throws Exception {
		
		InputStream relatorio = null;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		List<JasperPrint> jasperPrintList = BaseHttpServlet.getJasperPrintList( request );
		JRPdfExporter exporter = new JRPdfExporter();
		exporter.setParameter( JRExporterParameter.JASPER_PRINT_LIST, jasperPrintList );
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);

		try{
			exporter.exportReport();
			relatorio = new ByteArrayInputStream(out.toByteArray());
		} 
		catch ( JRException e ){
			throw new Exception( e );
		}
		return new DefaultStreamedContent( relatorio, "application/pdf", nomeRel );
	}
	
	/**
	 * Metodo para exportar em pdf um relatorio gerado pelo iReport
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public static final InputStream exportarRelatorioPdf( JasperPrint jasperPrint ) throws Exception {
		
		InputStream relatorio = null;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		JRPdfExporter exporter = new JRPdfExporter();
		exporter.setParameter( JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);

		try{
			exporter.exportReport();
			relatorio = new ByteArrayInputStream(out.toByteArray());
		} 
		catch ( JRException e ){
			throw new Exception( e );
		}
		return relatorio;
	}
}
