/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import connection.ConnectionFactory;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import model.bean.CadastroBEAN;
import model.bean.ControleForaExpedienteBEAN;

/**
 *
 * @author claud
 */
public class CadastroDAO {
    public Statement st;
    public ResultSet rs2;
    
    public static void create(CadastroBEAN cadastroBEAN) throws SQLException{
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO cadastro(n_cracha,NomeCompleto,TipoDocumento, DocumentoIden, Destino, ComquemFalar, Telefone, TipoVisitante, Data, HoraE, HoraS, imagem, marca_veiculo, modelo_veiculo, cor_veiculo, placa_veiculo)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            stmt.setInt(1,cadastroBEAN.getN_cracha());
            stmt.setString(2,cadastroBEAN.getNomeCompleto());
            stmt.setString(3,cadastroBEAN.getTipoDocumento());
            stmt.setString(4,cadastroBEAN.getDocumentoIden());
            stmt.setString(5,cadastroBEAN.getDestino());
            stmt.setString(6,cadastroBEAN.getComquemFalar());
            stmt.setString(7,cadastroBEAN.getTelefone());
            stmt.setString(8,cadastroBEAN.getTipoVisitante());
            stmt.setString(9,cadastroBEAN.getData());
            stmt.setString(10,cadastroBEAN.getHoraE());
            stmt.setString(11,cadastroBEAN.getHoraS());
            stmt.setBytes(12, cadastroBEAN.getFoto());
            stmt.setString(13, cadastroBEAN.getMarca_veiculo());
            stmt.setString(14, cadastroBEAN.getModelo_veiculo());
            stmt.setString(15, cadastroBEAN.getCor_veiculo());
            stmt.setString(16, cadastroBEAN.getPlaca_veiculo());
            
            stmt.executeUpdate();
        } catch (SQLException ex) {
             throw new SQLException(ex);
        }finally{
            ConnectionFactory.closeConnection(con,stmt);
        }
        
        }
    
        public static byte[] getImgBytes(BufferedImage image){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "JPEG", baos);
        } catch (IOException ex) {
            Logger.getLogger(CadastroDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        InputStream is = new ByteArrayInputStream(baos.toByteArray());
        return baos.toByteArray();
    }
      
        public List<CadastroBEAN> read(String date){
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement stmt = null;
            ResultSet rs = null;
            
            List<CadastroBEAN> cadastrov = new ArrayList<>();
            
            try{
                stmt = con.prepareStatement("SELECT * FROM cadastro WHERE data = ?");
                stmt.setString(1,date);
                rs = stmt.executeQuery();
                while(rs.next()){
                    CadastroBEAN c = new CadastroBEAN();
                    c.setId(rs.getInt("id"));
                    c.setN_cracha(rs.getInt("n_cracha"));
                    c.setNomeCompleto(rs.getString("NomeCompleto"));
                    c.setTipoDocumento(rs.getString("TipoDocumento"));
                    c.setDocumentoIden(rs.getString("DocumentoIden"));
                    c.setDestino(rs.getString("Destino"));
                    c.setComquemFalar(rs.getString("ComquemFalar"));
                    c.setTelefone(rs.getString("Telefone"));
                    c.setTipoVisitante(rs.getString("TipoVisitante"));
                    c.setData(rs.getString("Data"));
                    c.setHoraE(rs.getString("HoraE"));
                    c.setHoraS(rs.getString("HoraS"));
                    cadastrov.add(c);
                }
                stmt.close();
            }catch(SQLException ex){
                System.err.println("Não foi possível atualizar a tabela! "+ex);
            }finally{
                ConnectionFactory.closeConnection(con,stmt,rs);
            }
            return cadastrov;
        }
        
        public void altera(CadastroBEAN c) throws SQLException {
            Connection con = ConnectionFactory.getConnection();
            String sql = "UPDATE cadastro SET HoraS = ? WHERE id = ?";
            PreparedStatement stmt = null;
            stmt = con.prepareStatement(sql);
            
            stmt.setString(1, c.getHoraS());
            stmt.setInt(2, c.getId());
            stmt.execute();
            stmt.close();

    }
        
        public CadastroBEAN buscaImg(Integer id) throws SQLException{
            CadastroBEAN retorno = null;
            Connection con = ConnectionFactory.getConnection();
            String sql = "SELECT id,imagem FROM cadastro WHERE id = ?";
            PreparedStatement stmt = null;
            stmt = con.prepareStatement(sql);
            ResultSet rs = null;
            
            try{
                stmt.setInt(1, id);
                rs = stmt.executeQuery();
                if(rs.next()){
                    retorno = new CadastroBEAN();
                    retorno.setId(rs.getInt("id"));
                    retorno.setFoto(rs.getBytes("imagem"));
                }
                stmt.close();
            }catch(SQLException e){
                    retorno = null;
            }finally{
                ConnectionFactory.closeConnection(con,stmt,rs);
            }
            
            return retorno;
        }
        
        public boolean busca_data(String date) throws SQLException{
            boolean retorno = false;
            Connection con = ConnectionFactory.getConnection();
            String sql = "SELECT Data FROM cadastro WHERE Data = ?";
            PreparedStatement stmt = null;
            ResultSet rs = null;
            stmt = con.prepareStatement(sql);
            try{
                stmt.setString(1, date);
                rs = stmt.executeQuery();
                if(rs.next()){
                    retorno = true;
                }
                stmt.close();
            }catch(SQLException e){
                retorno = false;
            }finally{
                ConnectionFactory.closeConnection(con,stmt,rs);
            }
            return retorno;
        }
        
        public boolean busca_data_nome(String date) throws SQLException{
            boolean retorno = false;
            Connection con = ConnectionFactory.getConnection();
            String sql = "SELECT Data, placa_veiculo FROM cadastro WHERE Data = ? and placa_veiculo is not null";
            PreparedStatement stmt = null;
            ResultSet rs = null;
            stmt = con.prepareStatement(sql);
            try{
                stmt.setString(1, date);
                rs = stmt.executeQuery();
                if(rs.next()){
                    retorno = true;
                }
                stmt.close();
            }catch(SQLException e){
                retorno = false;
            }finally{
                ConnectionFactory.closeConnection(con,stmt,rs);
            }
            return retorno;
        }
       
        public List<CadastroBEAN> read_pdf(String date){
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement stmt = null;
            ResultSet rs = null;
            
            List<CadastroBEAN> cadastrov = new ArrayList<>();
            
            try{
                stmt = con.prepareStatement("SELECT NomeCompleto, TipoDocumento, DocumentoIden, HoraE, HoraS, Destino, Data, ComquemFalar FROM cadastro WHERE Data = ?");
                stmt.setString(1,date);
                rs = stmt.executeQuery();
                while(rs.next()){
                    CadastroBEAN c = new CadastroBEAN();
                    c.setNomeCompleto(rs.getString("NomeCompleto"));
                    c.setTipoDocumento(rs.getString("TipoDocumento"));
                    c.setDocumentoIden(rs.getString("DocumentoIden"));
                    c.setDestino(rs.getString("Destino"));
                    c.setHoraE(rs.getString("HoraE"));
                    c.setHoraS(rs.getString("HoraS"));
                    c.setComquemFalar(rs.getString("ComquemFalar"));
                    cadastrov.add(c);
                }
                stmt.close();
            }catch(SQLException ex){
                System.err.println("Erro ao buscar data no banco!"+ex);
            }finally{
                ConnectionFactory.closeConnection(con,stmt,rs);
            }
            return cadastrov;
        }
        
        public List<CadastroBEAN> read_pdf_veiculos(String date){
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement stmt = null;
            ResultSet rs = null;
            
            List<CadastroBEAN> cadastrov = new ArrayList<>();
            
            try{
                stmt = con.prepareStatement("SELECT marca_veiculo, modelo_veiculo, cor_veiculo, placa_veiculo, HoraE, HoraS, NomeCompleto, Data FROM cadastro WHERE Data = ? AND placa_veiculo is not null");
                stmt.setString(1,date);
                rs = stmt.executeQuery();
                while(rs.next()){
                    CadastroBEAN c = new CadastroBEAN();
                    c.setMarca_veiculo(rs.getString("marca_veiculo"));
                    c.setModelo_veiculo(rs.getString("modelo_veiculo"));
                    c.setCor_veiculo(rs.getString("cor_veiculo"));
                    c.setPlaca_veiculo(rs.getString("placa_veiculo"));
                    c.setHoraE(rs.getString("HoraE"));
                    c.setHoraS(rs.getString("HoraS"));
                    c.setNomeCompleto(rs.getString("NomeCompleto"));
                    cadastrov.add(c);
                }
                stmt.close();
            }catch(SQLException ex){
                System.err.println("Erro ao buscar data no banco!"+ex);
            }finally{
                ConnectionFactory.closeConnection(con,stmt,rs);
            }
            return cadastrov;
        }
        
        public void executaSQL(String sql){
        try {
            Connection con = ConnectionFactory.getConnection();
            this.st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            this.rs2 = st.executeQuery(sql);
        } catch (SQLException ex) {
            System.err.println("Não foi possível executar o comando sql" + "" + ex + ", o comando passado foi" + sql);
        }
    }
        
        public static List<CadastroBEAN> preenchePesquisa(String nome){
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement stmt = null;
            ResultSet rs = null;
            
            List<CadastroBEAN> cadastrov = new ArrayList<>();
            
            try{
                stmt = con.prepareStatement("SELECT NomeCompleto, TipoDocumento, DocumentoIden,TipoVisitante, Telefone, imagem FROM cadastro WHERE NomeCompleto = ?");
                stmt.setString(1,nome);
                rs = stmt.executeQuery();
                while(rs.next()){
                    CadastroBEAN c = new CadastroBEAN();
                    c.setNomeCompleto(rs.getString("NomeCompleto"));
                    c.setTipoDocumento(rs.getString("TipoDocumento"));
                    c.setDocumentoIden(rs.getString("DocumentoIden"));
                    c.setTipoVisitante(rs.getString("TipoVisitante"));
                    c.setTelefone(rs.getString("Telefone"));
                    c.setFoto(rs.getBytes("imagem"));
                    cadastrov.add(c);
                }
                stmt.close();
            }catch(SQLException ex){
                System.err.println("Erro ao preencher pesquisa!"+ex);
            }finally{
                ConnectionFactory.closeConnection(con,stmt,rs);
            }
            return cadastrov;
        }
        
        public static boolean buscaNome (String nome) throws SQLException{
            boolean retorno = false;
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement stmt = null;
            ResultSet rs = null;
            stmt = con.prepareStatement("SELECT NomeCompleto FROM cadastro WHERE NomeCompleto = ?");
            try{
                stmt.setString(1, nome);
                rs = stmt.executeQuery();
                if(rs.next()){
                    retorno = true;
                }
                stmt.close();
            }catch(SQLException e){
                retorno = false;
            }finally{
                ConnectionFactory.closeConnection(con,stmt,rs);
            }
            return retorno;
        }
        
        public boolean busca_cracha(int n) throws SQLException{
            boolean retorno = false;
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement stmt = null;
            ResultSet rs = null;
            stmt = con.prepareStatement("SELECT n_cracha, HoraS FROM cadastro WHERE n_cracha = ? and HoraS is null");
            try{
                stmt.setInt(1,n);
                rs = stmt.executeQuery();
                if(rs.next()){
                    retorno = true;
                }
                stmt.close();
            }catch(SQLException e){
                retorno = false;
            }finally{
                ConnectionFactory.closeConnection(con,stmt,rs);
            }
            return retorno;
        }
        
        
        
        //----------------------------------------------------------------------
        
        
        
        
        public List<ControleForaExpedienteBEAN> pesquisaBancoTodos(){
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement stmt = null;
            ResultSet rs = null;
            
            List<ControleForaExpedienteBEAN> cfeBEAN = new ArrayList();
            
            try{
                stmt = con.prepareStatement("SELECT * FROM controlef_saida");
                rs = stmt.executeQuery();
                while(rs.next()){
                    ControleForaExpedienteBEAN cfe = new ControleForaExpedienteBEAN();
                    cfe.setId(rs.getInt("id"));
                    cfe.setGrad_posto(rs.getString("grad_posto"));
                    cfe.setNome_guerra(rs.getString("nome_guerra"));
                    cfe.setSessao(rs.getString("sessao"));
                    cfe.setImagem(rs.getBytes("imagem"));
                    cfeBEAN.add(cfe);
                }
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(CadastroDAO.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                ConnectionFactory.closeConnection(con, stmt, rs);
            }
            return cfeBEAN;
        }
        
        public List<CadastroBEAN> pesquisaBancoTodosVisitantes(){
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement stmt = null;
            ResultSet rs = null;
            
            List<CadastroBEAN> cfeBEAN = new ArrayList();
            
            try{
                stmt = con.prepareStatement("SELECT * FROM cadastro");
                rs = stmt.executeQuery();
                while(rs.next()){
                    CadastroBEAN cfe = new CadastroBEAN();
                    cfe.setId(rs.getInt("id"));
                    cfe.setNomeCompleto(rs.getString("NomeCompleto"));
                    cfe.setTipoDocumento(rs.getString("TipoDocumento"));
                    cfe.setDocumentoIden(rs.getString("DocumentoIden"));
                    cfe.setTelefone(rs.getString("Telefone"));
                    cfe.setTipoVisitante(rs.getString("TipoVisitante"));
                    cfe.setFoto(rs.getBytes("imagem"));
                    cfeBEAN.add(cfe);
                }
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(CadastroDAO.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                ConnectionFactory.closeConnection(con, stmt, rs);
            }
            return cfeBEAN;
        }
        
        public int pesquisaBancoUm(String gradPosto, String nomeGuerra){
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement stmt = null;
            ResultSet rs = null;
            int retorno = 0;
            
            try{
                stmt = con.prepareStatement("SELECT * FROM cadastroEfetivo WHERE grad_posto = ? AND nome_guerra = ?");
                stmt.setString(1, gradPosto);
                stmt.setString(2, nomeGuerra);
                rs = stmt.executeQuery();
                while(rs.next()){
                    retorno = 1;
                }
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(CadastroDAO.class.getName()).log(Level.SEVERE, null, ex);
                retorno = 0;
            }finally{
                ConnectionFactory.closeConnection(con, stmt, rs);
            }
            return retorno;
        }
        
        public int pesquisaBancoUmVisitantes(String doc, String nome){
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement stmt = null;
            ResultSet rs = null;
            int retorno = 0;
            
            try{
                stmt = con.prepareStatement("SELECT * FROM cadastroVisitantes WHERE DocumentoIden = ? AND NomeCompleto = ?");
                stmt.setString(1, doc);
                stmt.setString(2, nome);
                rs = stmt.executeQuery();
                while(rs.next()){
                    retorno = 1;
                }
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(CadastroDAO.class.getName()).log(Level.SEVERE, null, ex);
                retorno = 0;
            }finally{
                ConnectionFactory.closeConnection(con, stmt, rs);
            }
            return retorno;
        }
        
        public void insereBanco(ControleForaExpedienteBEAN cfe){
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement stmt = null;
            
            try{
                stmt = con.prepareStatement("INSERT INTO cadastroEfetivo(id, grad_posto, nome_guerra, sessao, imagem) VALUES(?,?,?,?,?)");
                stmt.setInt(1, cfe.getId());
                stmt.setString(2, cfe.getGrad_posto());
                stmt.setString(3, cfe.getNome_guerra());
                stmt.setString(4, cfe.getSessao());
                stmt.setBytes(5, cfe.getImagem());
                stmt.executeUpdate();
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(CadastroDAO.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                ConnectionFactory.closeConnection(con, stmt);
            }
        }
        
        public void insereBancoVisitantes(CadastroBEAN cfe){
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement stmt = null;
            
            try{
                stmt = con.prepareStatement("INSERT INTO cadastroVisitantes(id, NomeCompleto, TipoDocumento, DocumentoIden, Telefone, TipoVisitante, imagem) VALUES(?,?,?,?,?,?,?)");
                stmt.setInt(1, cfe.getId());
                stmt.setString(2, cfe.getNomeCompleto());
                stmt.setString(3, cfe.getTipoDocumento());
                stmt.setString(4, cfe.getDocumentoIden());
                stmt.setString(5, cfe.getTelefone());
                stmt.setString(6, cfe.getTipoVisitante());
                stmt.setBytes(7, cfe.getFoto());
                stmt.executeUpdate();
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(CadastroDAO.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                ConnectionFactory.closeConnection(con, stmt);
            }
        }
        
        public static Boolean verificaDocumento(String tipoDocumento, String numeroDocumento) throws SQLException{
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement stmt = null;
            ResultSet rs = null;
            Boolean retorno = false;
            
            try{
                stmt = con.prepareStatement("SELECT TipoDocumento, DocumentoIden FROM cadastro WHERE TipoDocumento = ? AND DocumentoIden = ?");
                stmt.setString(1, tipoDocumento);
                stmt.setString(2, numeroDocumento);
                if((rs = stmt.executeQuery()).next()){
                    retorno = true;
                }
            }catch(SQLException ex){
                throw new SQLException(ex);
            }finally{
                ConnectionFactory.closeConnection(con, stmt, rs);
            }
            return retorno;
        }
}
