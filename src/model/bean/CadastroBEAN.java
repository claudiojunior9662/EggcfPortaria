/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

/**
 *
 * @author claud
 */
public class CadastroBEAN {
     private int id;
    private String nomeCompleto;
    private String documentoIden;
    private String data;
    private String horaE;
    private String horaS;       
    private String destino;
    private String comquemFalar;
    private String tipoDocumento;
    private String tipoVisitante;
    private String telefone;
    private byte[] foto;
    private int n_cracha;
    private String marca_veiculo;
    private String modelo_veiculo;
    private String cor_veiculo;
    private String placa_veiculo;

    public String getMarca_veiculo() {
        return marca_veiculo;
    }

    public void setMarca_veiculo(String marca_veiculo) {
        this.marca_veiculo = marca_veiculo;
    }

    public String getModelo_veiculo() {
        return modelo_veiculo;
    }

    public void setModelo_veiculo(String modelo_veiculo) {
        this.modelo_veiculo = modelo_veiculo;
    }

    public String getCor_veiculo() {
        return cor_veiculo;
    }

    public void setCor_veiculo(String cor_veiculo) {
        this.cor_veiculo = cor_veiculo;
    }

    public String getPlaca_veiculo() {
        return placa_veiculo;
    }

    public void setPlaca_veiculo(String placa_veiculo) {
        this.placa_veiculo = placa_veiculo;
    }
    

    public int getN_cracha() {
        return n_cracha;
    }

    public void setN_cracha(int n_cracha) {
        this.n_cracha = n_cracha;
    }

   


    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getDocumentoIden() {
        return documentoIden;
    }

    public void setDocumentoIden(String documentoIden) {
        this.documentoIden = documentoIden;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHoraE() {
        return horaE;
    }

    public void setHoraE(String horaE) {
        this.horaE = horaE;
    }

    public String getHoraS() {
        return horaS;
    }

    public void setHoraS(String horaS) {
        this.horaS = horaS;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getComquemFalar() {
        return comquemFalar;
    }

    public void setComquemFalar(String comquemFalar) {
        this.comquemFalar = comquemFalar;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getTipoVisitante() {
        return tipoVisitante;
    }

    public void setTipoVisitante(String tipoVisitante) {
        this.tipoVisitante = tipoVisitante;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void add(CadastroBEAN c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
