package br.com.sp.intranet.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.TreeNode;

import br.com.sp.intranet.controller.bean.Hlink;

import org.primefaces.model.DefaultTreeNode;
  
@ManagedBean
@SessionScoped
public class JdeTreeBean implements Serializable{  
      
    private TreeNode root;
    private TreeNode rootIntra;
  
    public JdeTreeBean() {      	
    	Hlink raiz = new Hlink("Root", "");
    	root = new DefaultTreeNode(raiz, null);
    	
    	Hlink zero = new Hlink("Navegando no JDE", "http://basedeconhecimento:8080/jde/NAVEGANDO");
    	TreeNode node0 = new DefaultTreeNode(zero, root);  
    	
        Hlink um = new Hlink("Compras / Recebimento / Almoxarifado", "http://basedeconhecimento:8080/jde/COMPRAS_RECEBIMENTO_ALMOXARIFADO");
        TreeNode node1 = new DefaultTreeNode(um, root);

        Hlink dois = new Hlink("Contas a Pagar", "http://basedeconhecimento:8080/jde/CONTAS_A_PAGAR");
        TreeNode node2 = new DefaultTreeNode(dois, root);
        
        Hlink tres = new Hlink("Contas a Receber", "http://basedeconhecimento:8080/jde/CONTAS_A_RECEBER");
        TreeNode node3 = new DefaultTreeNode(tres, root);
        
        Hlink quatro = new Hlink("Orçamento", "http://basedeconhecimento:8080/jde/ORCAMENTO");
        TreeNode node4 = new DefaultTreeNode(quatro, root);
        
        Hlink cinco = new Hlink("Fiscal", "http://basedeconhecimento:8080/jde/FISCAL");
        TreeNode node5 = new DefaultTreeNode(cinco, root);
        
        Hlink seis = new Hlink("Contabilidade", "http://basedeconhecimento:8080/jde/CONTABILIDADE");
        TreeNode node6 = new DefaultTreeNode(seis, root);
        
        Hlink sete = new Hlink("Ativos", "http://basedeconhecimento:8080/jde/ATIVOS");
        TreeNode node7 = new DefaultTreeNode(sete, root);
        
        Hlink oito = new Hlink("Compliance", "http://basedeconhecimento:8080/jde/COMPLIANCE");
        TreeNode node8 = new DefaultTreeNode(oito, root);
        
        Hlink nove = new Hlink("Atendimento CRM", "http://basedeconhecimento:8080/jde/ATENDIMENTO_CRM");
        TreeNode node9 = new DefaultTreeNode(nove, root);
        
        Hlink dez = new Hlink("Controle de Despesas", "http://basedeconhecimento:8080/jde/CONTROLE_DE_DESPESAS");
        TreeNode node10 = new DefaultTreeNode(dez, root);
        
        Hlink onze = new Hlink("Comercial", "http://basedeconhecimento:8080/jde/COMERCIAL");
        TreeNode node11 = new DefaultTreeNode(onze, root);
        
        Hlink doze = new Hlink("Contratos", "http://basedeconhecimento:8080/jde/CONTRATOS");
        TreeNode node12 = new DefaultTreeNode(doze, root);
        
        Hlink treze = new Hlink("Logística", "", "");
        TreeNode node13 = new DefaultTreeNode(treze, root);

        	Hlink trezeUm = new Hlink("Logística - JDE", "http://basedeconhecimento:8080/jde/LOGISTICA/LOGISTICA_JDE");
        	Hlink trezeDois = new Hlink("Logística - CTMS", "http://basedeconhecimento:8080/jde/LOGISTICA/LOGISTICA_CTMS");
        	TreeNode node131 = new DefaultTreeNode(trezeUm, node13);
        	TreeNode node132 = new DefaultTreeNode(trezeDois, node13);
        	
        
        Hlink qze = new Hlink("Manufatura / Custos", "#", "");
        TreeNode node14 = new DefaultTreeNode(qze, root);
        	Hlink qzeUm = new Hlink("Visão Geral", "http://basedeconhecimento:8080/jde/MANUFATURA_CUSTOS/VISAO_GERAL");
        	Hlink qzeDois = new Hlink("Planejamento", "http://basedeconhecimento:8080/jde/MANUFATURA_CUSTOS/PLANEJAMENTO");
        	Hlink qzeTres = new Hlink("Produção", "http://basedeconhecimento:8080/jde/MANUFATURA_CUSTOS/PRODUCAO");
        	Hlink qzeQuatro = new Hlink("Controle de Qualidade", "http://basedeconhecimento:8080/jde/MANUFATURA_CUSTOS/CQ");
        	Hlink qzeCinco = new Hlink("Custos", "http://basedeconhecimento:8080/jde/MANUFATURA_CUSTOS/CUSTOS");
        	TreeNode node141 = new DefaultTreeNode(qzeUm, node14);
        	TreeNode node142 = new DefaultTreeNode(qzeDois, node14);
        	TreeNode node143 = new DefaultTreeNode(qzeTres, node14);
        	TreeNode node144 = new DefaultTreeNode(qzeQuatro, node14);
        	TreeNode node145 = new DefaultTreeNode(qzeCinco, node14);
        
        Hlink dseis = new Hlink("Estoque", "http://basedeconhecimento:8080/jde/ESTOQUE");
        TreeNode node16 = new DefaultTreeNode(dseis, root);
        	
       Hlink raizIntra = new Hlink("Root", "");
        	rootIntra = new DefaultTreeNode(raizIntra, null);
        	
        	Hlink zeroIntra = new Hlink("Página Inicial e Acesso da Intranet" ,"http://basedeconhecimento:8080/jde/INTRANET/PAGINA_INICIAL");
        	TreeNode node0Intra = new DefaultTreeNode(zeroIntra, rootIntra);  
        	
        	Hlink quatroIntra = new Hlink("Sistema de Arquivos", "http://basedeconhecimento:8080/jde/INTRANET/SISTEMA_ARQUIVOS");
            TreeNode node4Intra = new DefaultTreeNode(quatroIntra, rootIntra);

            Hlink cintoIntra = new Hlink("Controle de Caixa", "http://basedeconhecimento:8080/jde/INTRANET/CAIXA");
            TreeNode node5Intra = new DefaultTreeNode(cintoIntra, rootIntra);
    }
    
    
    
    public TreeNode getRootIntra() {
		return rootIntra;
	}
    
    public void setRootIntra(TreeNode rootIntra) {
		this.rootIntra = rootIntra;
	}

    public TreeNode getRoot() {  
        return root;  
    }  
    
    public String doSth(Hlink node) {  
	  return node.getLink();
	} 
}