/**
 * 
 */
package br.com.sp.intranet.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
/**
 * @author eduardo
 *
 */
public final class PrintUtils {
	
	private static PrintUtils printUtils;
	
	public static PrintUtils getInstance(){
		
		if( printUtils == null ){
			printUtils = new PrintUtils();	
		}
		return printUtils;
	}
	
	/**
	 * Metodo para preencher o relatorio com os parametros para geracao
	 *  
	 * @param nomeRelatorio
	 * @param parameters
	 * @return
	 */
	public JasperPrint preencherRelatorio( String nomeRelatorio, Map<String, Object> parameters ){
		
		JasperPrint jasperPrint = null;
		try{
			InputStream is = this.getClass().getResourceAsStream( nomeRelatorio );
			Connection conn = null;
			DataSource source = (DataSource)new InitialContext().lookup( ConstantUtils.MAIN_CONNECTION_NAME );
			conn = source.getConnection();
			
			jasperPrint = JasperFillManager.fillReport( is, parameters, conn );
			conn.close();
		}
		catch( JRException jre ){
			jre.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return jasperPrint;
	}
	
	/**
	 * Metodo para preencher o relatorio com os parametros para geracao
	 *  
	 * @param nomeRelatorio
	 * @param parameters
	 * @return
	 */
	public JasperPrint preencherRelatorioPv( String nomeRelatorio, Map<String, Object> parameters ){
		
		JasperPrint jasperPrint = null;
		try{
			InputStream is = this.getClass().getResourceAsStream( nomeRelatorio );
			Connection conn = null;
			DataSource source = (DataSource)new InitialContext().lookup( "java:/PvDS" );
			conn = source.getConnection();
			
			jasperPrint = JasperFillManager.fillReport( is, parameters, conn );
			conn.close();
		}
		catch( JRException jre ){
			jre.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return jasperPrint;
	}
	/**
	 * Metodo para preencher o relatorio com List de Bean para geracao
	 * @author rafael.nakano
	 * @param nomeRelatorio
	 * @param parameters
	 * @return
	 */
	public JasperPrint preencherRelatorioList( String nomeRelatorio, Map<String, Object> parameters, JRDataSource jrds ){
		
		JasperPrint jasperPrint = null;
		try{
			InputStream is = this.getClass().getResourceAsStream( nomeRelatorio );
			Connection conn = null;
			DataSource source = (DataSource)new InitialContext().lookup( ConstantUtils.MAIN_CONNECTION_NAME );
			conn = source.getConnection();
			
			jasperPrint = JasperFillManager.fillReport( is, parameters, jrds );
			conn.close();
		}
		catch( JRException jre ){
			jre.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return jasperPrint;
	}
}
