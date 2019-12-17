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
import java.sql.Time;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.JOptionPane;
import model.bean.ControleExpedienteBEAN;

/**
 *
 * @author claud
 */
public class ControleDAO {
    public Statement st2;
    public ResultSet rs3;
    
    public void create(ControleExpedienteBEAN c){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO controle_saida(grad_posto,nome_guerra,sessao,data,hora_saida,hora_entrada,motivo_saida,imagem)VALUES(?,?,?,?,?,?,?,?)");
            stmt.setString(1,c.getGrad_posto());
            stmt.setString(2,c.getNome_guerra());
            stmt.setString(3,c.getSessao());
            stmt.setString(4,c.getData());
            stmt.setString(5,c.getHora_saida());
            stmt.setString(6,c.getHora_entrada());
            stmt.setString(7,c.getMotivo_saida());
            stmt.setBytes(8,c.getImagem());
            
            stmt.executeUpdate();

           JOptionPane.showMessageDialog(null,"Salvo com sucesso!");  
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null,"Erro ao salvar:"+ex);
        }finally{
            ConnectionFactory.closeConnection(con,stmt);
        }
        
    }
    
    public boolean busca_nome_controle (String grad_posto, String nome) throws SQLException{
            boolean retorno = false;
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement stmt = null;
            ResultSet rs = null;
            stmt = con.prepareStatement("SELECT grad_posto, nome_guerra FROM controle_saida WHERE grad_posto = ? and nome_guerra = ?");
            try{
                stmt.setString(1, grad_posto);
                stmt.setString(2,nome);
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
    
    public List<ControleExpedienteBEAN> read_controle(String date){
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement stmt = null;
            ResultSet rs = null;
            
            List<ControleExpedienteBEAN> controlev = new ArrayList<>();
            
            try{
                stmt = con.prepareStatement("SELECT * FROM controle_saida WHERE data = ?");
                stmt.setString(1, date);
                rs = stmt.executeQuery();
                while(rs.next()){
                    ControleExpedienteBEAN c = new ControleExpedienteBEAN();
                    c.setId(rs.getInt("id"));
                    c.setGrad_posto(rs.getString("grad_posto"));
                    c.setNome_guerra(rs.getString("nome_guerra"));
                    c.setSessao(rs.getString("sessao"));
                    c.setData(rs.getString("data"));
                    c.setHora_saida(rs.getString("hora_saida"));
                    c.setHora_entrada(rs.getString("hora_entrada"));
                    c.setMotivo_saida(rs.getString("motivo_saida"));
                    controlev.add(c);
                    
                }
            }catch(SQLException ex){
                System.err.println("Não foi possível atualizar a tabela! "+ex);
            }finally{
                ConnectionFactory.closeConnection(con,stmt,rs);
            }
            return controlev;
        }
    
    public ControleExpedienteBEAN buscaImg_controle(Integer id) throws SQLException{
            ControleExpedienteBEAN retorno2 = null;
            Connection con = ConnectionFactory.getConnection();
            String sql = "SELECT id,imagem FROM controle_saida WHERE id = ?";
            PreparedStatement stmt = null;
            stmt = con.prepareStatement(sql);
            ResultSet rs = null;
            
            try{
                stmt.setInt(1, id);
                rs = stmt.executeQuery();
                if(rs.next()){
                    retorno2 = new ControleExpedienteBEAN();
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
    
    public void altera_controle(ControleExpedienteBEAN c) throws SQLException {
            Connection con = ConnectionFactory.getConnection();
            String sql = "UPDATE controle_saida SET hora_entrada = ? WHERE id = ?";
            PreparedStatement stmt = null;
            stmt = con.prepareStatement(sql);
            
            stmt.setString(1, c.getHora_entrada());
            stmt.setInt(2, c.getId());
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
    
    public List<ControleExpedienteBEAN> preenche_pesquisa_controle(String grad, String nome){
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement stmt = null;
            ResultSet rs = null;
            
            List<ControleExpedienteBEAN> controlev = new ArrayList<>();
            
            try{
                stmt = con.prepareStatement("SELECT grad_posto,nome_guerra,sessao,imagem FROM controle_saida WHERE grad_posto = ? and nome_guerra = ?");
                stmt.setString(1,grad);
                stmt.setString(2,nome);
                rs = stmt.executeQuery();
                while(rs.next()){
                    ControleExpedienteBEAN c = new ControleExpedienteBEAN();
                    c.setGrad_posto(rs.getString("grad_posto"));
                    c.setNome_guerra(rs.getString("nome_guerra"));
                    c.setSessao(rs.getString("sessao"));
                    c.setImagem(rs.getBytes("imagem"));
                    controlev.add(c);
                }
            }catch(SQLException ex){
                System.err.println("Erro ao preencher pesquisa!"+ex);
            }finally{
                ConnectionFactory.closeConnection(con,stmt,rs);
            }
            return controlev;
        }
    
    public boolean busca_data_controle(String date) throws SQLException{
            boolean retorno = false;
            Connection con = ConnectionFactory.getConnection();
            String sql = "SELECT data FROM controle_saida WHERE data = ?";
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
    
    public List<ControleExpedienteBEAN> read_pdf_controle(String date){
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement stmt = null;
            ResultSet rs = null;
            
            List<ControleExpedienteBEAN> controlev = new ArrayList<>();
            
            try{
                stmt = con.prepareStatement("SELECT grad_posto,nome_guerra,sessao,hora_saida,hora_entrada,motivo_saida FROM controle_saida WHERE data = ?");
                stmt.setString(1,date);
                rs = stmt.executeQuery();
                while(rs.next()){
                    ControleExpedienteBEAN c = new ControleExpedienteBEAN();
                    c.setGrad_posto(rs.getString("grad_posto"));
                    c.setNome_guerra(rs.getString("nome_guerra"));
                    c.setSessao(rs.getString("sessao"));
                    c.setHora_saida(rs.getString("hora_saida"));
                    c.setHora_entrada(rs.getString("hora_entrada"));
                    c.setMotivo_saida(rs.getString("motivo_saida"));
                    controlev.add(c);
                }
            }catch(SQLException ex){
                System.err.println("Erro ao buscar data no banco!"+ex);
            }finally{
                ConnectionFactory.closeConnection(con,stmt,rs);
            }
            return controlev;
        }
    
    public static Boolean horarioExpediente(Time horario, Boolean especial) throws SQLException{
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Boolean retorno = false;
        
        try{
            if(especial){
                stmt = con.prepareStatement("SELECT inicio_expediente_especial, fim_expediente_especial"
                        + " FROM controle");
            }else{
                stmt = con.prepareStatement("SELECT inicio_expediente, fim_expediente"
                        + " FROM controle");
            }
            if((rs = stmt.executeQuery()).next()){
                System.out.println(horario.after(rs.getTime("inicio_expediente")));
                System.out.println(horario.before(rs.getTime("fim_expediente")));
                if(especial){
                    if(horario.after(rs.getTime("inicio_expediente_especial")) && horario.before(rs.getTime("fim_expediente_especial"))){
                        retorno = true;
                    }
                }else{
                    if(horario.after(rs.getTime("inicio_expediente")) && horario.before(rs.getTime("fim_expediente"))){
                        retorno = true;
                    }
                }
            }
        }catch(SQLException ex){
            throw new SQLException(ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return retorno;
    }
    
    public static byte retornaDiaEspecial() throws SQLException{
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        byte retorno = 0;
        
        try{
            stmt = con.prepareStatement("SELECT dia_expediente_especial FROM controle");
            if((rs = stmt.executeQuery()).next()){
                retorno = (byte) rs.getInt("dia_expediente_especial");
            }
        }catch(SQLException ex){
            throw new SQLException(ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return retorno;
    }
    
    
}
