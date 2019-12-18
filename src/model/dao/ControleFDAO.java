/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.bean.ControleForaExpedienteBEAN;

/**
 *
 * @author claud
 */
public class ControleFDAO {
    public Statement st2;
    public ResultSet rs3;
    
    public void create(ControleForaExpedienteBEAN cf){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO controlef_saida(grad_posto,nome_guerra,sessao,data,hora_saida,hora_entrada,motivo_entrada,imagem)VALUES(?,?,?,?,?,?,?,?)");
            stmt.setString(1,cf.getGrad_posto());
            stmt.setString(2,cf.getNome_guerra());
            stmt.setString(3,cf.getSessao());
            stmt.setString(4,cf.getData());
            stmt.setString(5,cf.getHora_saida());
            stmt.setString(6,cf.getHora_entrada());
            stmt.setString(7,cf.getMotivo_entrada());
            stmt.setBytes(8,cf.getImagem());
            
            stmt.executeUpdate();

           JOptionPane.showMessageDialog(null,"Salvo com sucesso!");
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null,"Erro ao salvar:"+ex);
        }finally{
            ConnectionFactory.closeConnection(con,stmt);
        }
        
    }
    
    public boolean busca_nome_controlef (String grad_posto, String nome) throws SQLException{
            boolean retorno = false;
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement stmt = null;
            ResultSet rs = null;
            stmt = con.prepareStatement("SELECT nome_guerra FROM controlef_saida WHERE grad_posto = ? and nome_guerra = ?");
            try{
                stmt.setString(1, grad_posto);
                stmt.setString(2, nome);
                rs = stmt.executeQuery();
                if(rs.next()){
                    retorno = true;
                    }
            }catch(SQLException e){
                retorno = false;
            }finally{
                ConnectionFactory.closeConnection(con,stmt,rs);    
            }
        return retorno;
        }
    
    public List<ControleForaExpedienteBEAN> readControleF(String date) throws SQLException{
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement stmt = null;
            ResultSet rs = null;
            
            List<ControleForaExpedienteBEAN> retorno = new ArrayList<>();
            
            try{
                stmt = con.prepareStatement("SELECT * FROM controlef_saida WHERE data = ?");
                stmt.setString(1, date);
                rs = stmt.executeQuery();
                while(rs.next()){
                    ControleForaExpedienteBEAN controleForaExpedienteBEAN = new ControleForaExpedienteBEAN();
                    controleForaExpedienteBEAN.setId(rs.getInt("id"));
                    controleForaExpedienteBEAN.setGrad_posto(rs.getString("grad_posto"));
                    controleForaExpedienteBEAN.setNome_guerra(rs.getString("nome_guerra"));
                    controleForaExpedienteBEAN.setSessao(rs.getString("sessao"));
                    controleForaExpedienteBEAN.setData(rs.getString("data"));
                    controleForaExpedienteBEAN.setHora_saida(rs.getString("hora_saida"));
                    controleForaExpedienteBEAN.setHora_entrada(rs.getString("hora_entrada"));
                    controleForaExpedienteBEAN.setMotivo_entrada(rs.getString("motivo_entrada"));
                    retorno.add(controleForaExpedienteBEAN);
                }
            }catch(SQLException ex){
                throw new SQLException(ex);
            }finally{
                ConnectionFactory.closeConnection(con,stmt,rs);
            }
            return retorno;
        }
    
    public ControleForaExpedienteBEAN buscaImagemEntrada(Integer id) throws SQLException{
            ControleForaExpedienteBEAN retorno2 = null;
            Connection con = ConnectionFactory.getConnection();
            String sql = "SELECT id,imagem FROM controlef_saida WHERE id = ?";
            PreparedStatement stmt = null;
            stmt = con.prepareStatement(sql);
            ResultSet rs = null;
            
            try{
                stmt.setInt(1, id);
                rs = stmt.executeQuery();
                if(rs.next()){
                    retorno2 = new ControleForaExpedienteBEAN();
                    retorno2.setId(rs.getInt("id"));
                    retorno2.setImagem(rs.getBytes("imagem"));
                }
            }catch(SQLException e){
                    retorno2 = null;
            }finally{
                ConnectionFactory.closeConnection(con,stmt,rs);
            }
            
            return retorno2;
        }
    
    public void altera_controlef(ControleForaExpedienteBEAN cf) throws SQLException {
            Connection con = ConnectionFactory.getConnection();
            String sql = "UPDATE controlef_saida SET hora_saida = ? WHERE id = ?";
            PreparedStatement stmt = null;
            stmt = con.prepareStatement(sql);
            
            stmt.setString(1, cf.getHora_saida());
            stmt.setInt(2, cf.getId());
            stmt.execute();
            stmt.close();
            
            ConnectionFactory.closeConnection(con,stmt);

    }
    
    public void executaSQL(String sql){
        try {
            Connection con = ConnectionFactory.getConnection();
            this.st2 = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            this.rs3 = st2.executeQuery(sql);
        } catch (SQLException ex) {
            System.err.println("Não foi possível executar o comando sql" + "" + ex + ", o comando passado foi" + sql);
        }
    }
    
    public List<ControleForaExpedienteBEAN> preenche_pesquisa_controlef(String grad, String nome){
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement stmt = null;
            ResultSet rs = null;
            
            List<ControleForaExpedienteBEAN> controlev = new ArrayList<>();
            
            try{
                stmt = con.prepareStatement("SELECT grad_posto,nome_guerra,sessao,imagem FROM controlef_saida WHERE grad_posto = ? and nome_guerra = ?");
                stmt.setString(1,grad);
                stmt.setString(2,nome);
                rs = stmt.executeQuery();
                while(rs.next()){
                    ControleForaExpedienteBEAN cf = new ControleForaExpedienteBEAN();
                    cf.setGrad_posto(rs.getString("grad_posto"));
                    cf.setNome_guerra(rs.getString("nome_guerra"));
                    cf.setSessao(rs.getString("sessao"));
                    cf.setImagem(rs.getBytes("imagem"));
                    controlev.add(cf);
                }
            }catch(SQLException ex){
                System.err.println("Erro ao preencher pesquisa!"+ex);
            }finally{
                ConnectionFactory.closeConnection(con,stmt,rs);
            }
            return controlev;
        }
    
    public boolean busca_data_controlef(String date) throws SQLException{
            boolean retorno = false;
            Connection con = ConnectionFactory.getConnection();
            String sql = "SELECT data FROM controlef_saida WHERE data = ?";
            PreparedStatement stmt = null;
            ResultSet rs = null;
            stmt = con.prepareStatement(sql);
            try{
                stmt.setString(1, date);
                rs = stmt.executeQuery();
                if(rs.next()){
                    retorno = true;
                }
            }catch(SQLException e){
                retorno = false;
            }finally{
                ConnectionFactory.closeConnection(con,stmt,rs);
            }
            return retorno;
        }
    
    public List<ControleForaExpedienteBEAN> read_pdf_controlef(String date){
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement stmt = null;
            ResultSet rs = null;
            
            List<ControleForaExpedienteBEAN> controlev = new ArrayList<>();
            
            try{
                stmt = con.prepareStatement("SELECT grad_posto,nome_guerra,sessao,hora_entrada,hora_saida,motivo_entrada FROM controlef_saida WHERE data = ?");
                stmt.setString(1,date);
                rs = stmt.executeQuery();
                while(rs.next()){
                    ControleForaExpedienteBEAN cf = new ControleForaExpedienteBEAN();
                    cf.setGrad_posto(rs.getString("grad_posto"));
                    cf.setNome_guerra(rs.getString("nome_guerra"));
                    cf.setSessao(rs.getString("sessao"));
                    cf.setHora_saida(rs.getString("hora_saida"));
                    cf.setHora_entrada(rs.getString("hora_entrada"));
                    cf.setMotivo_entrada(rs.getString("motivo_entrada"));
                    controlev.add(cf);
                }
            }catch(SQLException ex){
                System.err.println("Erro ao buscar data no banco!"+ex);
            }finally{
                ConnectionFactory.closeConnection(con,stmt,rs);
            }
            return controlev;
        }
}
