/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package teste;

import Connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.bean.CadastroBEAN;

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
            if(verificaDuplicidade(bean.getNomeCompleto()) == false){
                insertCadastro(bean);
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
    
    public static boolean verificaDuplicidade(String nomeCompleto){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        boolean retorno = false;
        
        try{
            stmt = con.prepareStatement("SELECT NomeCompleto FROM cadastroVisitantes WHERE NomeCompleto = ?");
            stmt.setString(1, nomeCompleto);
            rs = stmt.executeQuery();
            while(rs.next()){
                retorno = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(NewMain.class.getName()).log(Level.SEVERE, null, ex);
            retorno = false;
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return retorno;
    }
    
    public static void insertCadastro(CadastroBEAN aux){
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
    public static List<>
    
    
}
