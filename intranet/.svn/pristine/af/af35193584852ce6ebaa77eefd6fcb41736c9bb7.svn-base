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
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.j2ee.servlets.BaseHttpServlet;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 * @author eduardo
 *
 */
public final class RtfExport {

	/**
	 * Metodo para exportar em Word um relatorio gerado pelo iReport
	 * 
	 * @return
	 * @throws Exception
	 */
	public static final StreamedContent exportarRelatorioDoc( HttpServletRequest request, String nomeRel ) throws Exception {
		
		InputStream relatorio = null;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		List<JasperPrint> jasperPrintList = BaseHttpServlet.getJasperPrintList( request );
		//response.setHeader( "Content-disposition","attachment; filename=relatorio.rtf" );
		JRRtfExporter exporter = new JRRtfExporter();
		exporter.setParameter( JRExporterParameter.JASPER_PRINT_LIST, jasperPrintList );
		exporter.setParameter( JRExporterParameter.OUTPUT_STREAM, out );

		try{
			exporter.exportReport();
			relatorio = new ByteArrayInputStream(out.toByteArray());
		} 
		catch ( JRException e ){
			throw new Exception( e );
		}
		return new DefaultStreamedContent( relatorio, "application/rtf", nomeRel );
	}
}
