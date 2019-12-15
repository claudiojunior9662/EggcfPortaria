/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package teste;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.bean.CadastroBEAN;
import model.bean.ControleExpedienteBEAN;

/**
 *
 * @author claudio
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        atualizaVisitantes();
    }
    
    //ATUALIZAR NOVA TABELA VISITANTES------------------------------------------
    public static void atualizaVisitantes(){
        for(CadastroBEAN bean : retornaVisitantes()){
            if(verificaDuplicidade(bean.getNomeCompleto(), true, false) == false){
                insereVisitante(bean);
            }
        }
    }
    
    public static List<CadastroBEAN> retornaVisitantes(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<CadastroBEAN> retorno = new ArrayList();
        
        try{
            stmt = con.prepareStatement("SELECT * FROM cadastro");
            rs = stmt.executeQuery();
            while(rs.next()){
                CadastroBEAN aux = new CadastroBEAN();
                aux.setId(rs.getInt("id"));
                aux.setNomeCompleto(rs.getString("NomeCompleto"));
                aux.setTipoDocumento(rs.getString("TipoDocumento"));
                aux.setDocumentoIden(rs.getString("DocumentoIden"));
                aux.setTelefone(rs.getString("Telefone"));
                aux.setTipoVisitante(rs.getString("TipoVisitante"));
                aux.setFoto(rs.getBytes("imagem"));
                retorno.add(aux);
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(NewMain.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return retorno;
    }    
    
    public static void insereVisitante(CadastroBEAN aux){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            stmt = con.prepareStatement("INSERT INTO cadastroVisitantes VALUES(?,?,?,?,?,?,?)");
            stmt.setInt(1, aux.getId());
            stmt.setString(2, aux.getNomeCompleto());
            stmt.setString(3, aux.getTipoDocumento());
            stmt.setString(4, aux.getDocumentoIden());
            stmt.setString(5, aux.getTelefone());
            stmt.setString(6, aux.getTipoVisitante());
            stmt.setBytes(7, aux.getFoto());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(NewMain.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    //ATUALIZAR NOVA TABELA EFETIVO---------------------------------------------
    public static void atualizaEfetivo(){
        for(ControleExpedienteBEAN bean : retornaEfetivo()){
            if(verificaDuplicidade(bean.getNome_guerra(), false, true) == false){
                insereEfetivo(bean);
            }
        }
    }
    
    public static List<ControleExpedienteBEAN>  retornaEfetivo(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<ControleExpedienteBEAN> retorno = new ArrayList();
        
        try{
            stmt = con.prepareStatement("SELECT * FROM controle_saida");
            rs = stmt.executeQuery();
            while(rs.next()){
                ControleExpedienteBEAN aux = new ControleExpedienteBEAN();
                aux.setId(rs.getInt("id"));
                aux.setGrad_posto(rs.getString("grad_posto"));
                aux.setNome_guerra(rs.getString("nome_guerra"));
                aux.setSessao(rs.getString("sessao"));
                aux.setImagem(rs.getBytes("imagem"));
                retorno.add(aux);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NewMain.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return retorno;
    }
    
    public static void insereEfetivo(ControleExpedienteBEAN aux){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            stmt = con.prepareStatement("INSERT INTO cadastroEfetivo VALUES(?,?,?,?,?)");
            stmt.setInt(1, aux.getId());
            stmt.setString(2, aux.getGrad_posto());
            stmt.setString(3, aux.getNome_guerra());
            stmt.setString(4, aux.getSessao());
            stmt.setBytes(5, aux.getImagem());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(NewMain.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    //FUNÇÕES GENÉRICAS---------------------------------------------------------
    public static boolean verificaDuplicidade(String nome, boolean visitantes, boolean efetivo){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        boolean retorno = false;
        
        try{
            if(visitantes == true){
                stmt = con.prepareStatement("SELECT NomeCompleto FROM cadastroVisitantes WHERE NomeCompleto = ?");
                stmt.setString(1, nome);
            }else if(efetivo == true){
                stmt = con.prepareStatement("SELECT nome_guerra FROM cadastroEfetivo WHERE nome_guerra = ?");
                stmt.setString(1, nome);
            }
            rs = stmt.executeQuery();
            while(rs.next()){
                retorno = true;
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(NewMain.class.getName()).log(Level.SEVERE, null, ex);
            retorno = false;
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return retorno;
    }
    
    
    
    
}
