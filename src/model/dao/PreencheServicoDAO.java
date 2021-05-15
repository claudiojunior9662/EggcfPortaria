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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.PreencheServicoBEAN;

/**
 *
 * @author spd3
 */
public class PreencheServicoDAO {
    
    public void criaGuarnicao(PreencheServicoBEAN psBEAN){
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement stmt = null;
            
            try{
               stmt = con.prepareStatement("INSERT INTO guarnicao(data, graduado_dia, cabo_dia, soldado1, soldado2, soldado3, hora_inicio, hora_fim)"
                                            + " VALUES(?,?,?,?,?,?,?,?)");
               stmt.setString(1, psBEAN.getData());
               stmt.setString(2, psBEAN.getGraduado_dia());
               stmt.setString(3, psBEAN.getCabo_dia());
               stmt.setString(4, psBEAN.getSoldado1());
               stmt.setString(5, psBEAN.getSoldado2());
               stmt.setString(6, psBEAN.getSoldado3());
               stmt.setString(7, psBEAN.getHora_inicio());
               stmt.setString(8, psBEAN.getHora_fim());
               stmt.executeUpdate();
               stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(ControleVisitantesDAO.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                ConnectionFactory.closeConnection(con, stmt);
            }
        }
        
    public int verificaServicoEmAberto(){
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement stmt = null;
            ResultSet rs = null;
            int retorno = 0;
            
            try{
                stmt = con.prepareStatement("SELECT hora_fim FROM guarnicao WHERE hora_fim IS NULL");
                rs = stmt.executeQuery();
                while(rs.next()){
                    retorno = 1;
                }
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(ControleVisitantesDAO.class.getName()).log(Level.SEVERE, null, ex);
                retorno = 0;
            }finally{
                ConnectionFactory.closeConnection(con, stmt, rs);
            }
            return retorno;
        }
    
    public List<PreencheServicoBEAN> retornaGuarnicao(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int retorno = 0;
        
        List<PreencheServicoBEAN> psBEAN = new ArrayList();
        
        try{
            stmt = con.prepareStatement("SELECT * FROM guarnicao WHERE hora_fim IS NULL");
            rs = stmt.executeQuery();
            while(rs.next()){
                PreencheServicoBEAN psAUX = new PreencheServicoBEAN();
                psAUX.setGraduado_dia(rs.getString("graduado_dia"));
                psAUX.setCabo_dia(rs.getString("cabo_dia"));
                psAUX.setSoldado1(rs.getString("soldado1"));
                psAUX.setSoldado2(rs.getString("soldado2"));
                psAUX.setSoldado3(rs.getString("soldado3"));
                psAUX.setData(rs.getString("data"));
                psAUX.setHora_inicio(rs.getString("hora_inicio"));
                psAUX.setId(rs.getInt("Id"));
                psBEAN.add(psAUX);
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(PreencheServicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return psBEAN;
    }
    
    public void excluiGuarnicao(int id){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            stmt = con.prepareStatement("DELETE FROM guarnicao WHERE Id = ?");
            stmt.setInt(1, id);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(PreencheServicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
}
