package br.com.sp.intranet.controller.bean.colaborador;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;



import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import br.com.sp.intranet.model.administrador.vo.rh.Colaborador;
public class ColaboradorModel extends LazyDataModel<Colaborador> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	

	
	List<Colaborador> list = new ArrayList<Colaborador>();
	private List<Colaborador> datasource;
	private List<Colaborador> data = new ArrayList<Colaborador>();

	public ColaboradorModel() {
	}

	public ColaboradorModel(List<Colaborador> data) {
		this.datasource = data;
	}

	@Override
	public Colaborador getRowData(String key) {
		for(Colaborador colaborador : datasource) {
            if(colaborador.getId().equals(Long.parseLong(key)))
                return colaborador;
        }
        return null;
	}

	@Override
	public Object getRowKey(Colaborador object) {
		return object.getId();
	}
	

	@Override
	public List<Colaborador> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {
		List<Colaborador> data = new ArrayList<>();
		
		//filter
		for (Colaborador colaborador : datasource) {
			//System.out.println("entrou aqui");
            boolean match = true;
 
            if (filters != null) {
                for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
                    try {
                    	String fieldValue = "";
                        String filterProperty = it.next();
                        System.out.println("filterProperty: " + filterProperty);
                        Object filterValue = filters.get(filterProperty);
                        System.out.println("filterValue: " + filterValue);
                        
                        
                        //System.out.println("String.valueOf:" + String.valueOf(colaborador.getClass().getField(filterProperty)));
                        
                        if(filterProperty.equals("filial")) {
                         	 fieldValue = colaborador.getFilial().getId().toString();
                         	 System.out.println("fieldValue: " + fieldValue);
                        }
                        
                        if(filterProperty.equals("matricula")) {
                          	 fieldValue = colaborador.getMatricula().toString();
                          	 System.out.println("fieldValue: " + fieldValue);
                         }
                        
                        if(filterProperty.equals("nome")) {
                         	 fieldValue = colaborador.getNome().toString();
                         	 System.out.println("fieldValue: " + fieldValue);
                         }
                        

                        if(filterProperty.equals("cargo.descricao")) {
                        	 fieldValue = colaborador.getCargo().getDescricao();
                        	 System.out.println("fieldValue: " + fieldValue);
                        }
                        
                        if(filterProperty.equals("departamento.descricao")) {
                       	 fieldValue = colaborador.getDepartamento().getDescricao();
                       	 System.out.println("fieldValue: " + fieldValue);
                        }
                        System.out.println("NOME " + colaborador.getNome().toString());
                        if(filterProperty.equals("status")) {
                        	 fieldValue = colaborador.getStatus().toString();
                        	 System.out.println("fieldValue: " + fieldValue);
                       }

            			//	sbSQL.append(" AND model.demissao IS NULL");

            			//} else if (ativo.equalsIgnoreCase("D")) {
            			//	sbSQL.append(" AND model.demissao IS NOT NULL");
            			//}
                        
       
                        //String fieldValue = ""; //String.valueOf(colaborador.getClass().getField(filterProperty).get(colaborador));

                        if(filterValue.toString().toUpperCase() == null || fieldValue.toString().toUpperCase().startsWith(filterValue.toString().toUpperCase())) {
                            match = true;
                    }
                    else {
                            match = false;
                            break;
                        }
                    } catch(Exception e) {
                        match = false;
                    }
                }
            }
 
            if(match) {
                data.add(colaborador);
            }
        }

		for (Colaborador colaborador : datasource) {
			data.add(colaborador);
		}

		int dataSize = data.size();
		this.setRowCount(dataSize);

		// paginate
		if (dataSize > pageSize) {
			try {
				return data.subList(first, first + pageSize);
			} catch (IndexOutOfBoundsException e) {
				return data.subList(first, first + (dataSize % pageSize));
			}
		} else {
			return data;
		}

	}

	public List<Colaborador> getData() {
		return data;
	}

	public void setData(List<Colaborador> data) {
		this.data = data;
	}

}
