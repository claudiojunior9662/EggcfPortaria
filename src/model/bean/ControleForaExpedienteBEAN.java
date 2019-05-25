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
public class ControleForaExpedienteBEAN {
    private int id;
    private String grad_posto;
    private String nome_guerra;
    private String sessao;
    private String data;
    private String hora_saida;
    private String hora_entrada;
    private String motivo_entrada;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGrad_posto() {
        return grad_posto;
    }

    public void setGrad_posto(String grad_posto) {
        this.grad_posto = grad_posto;
    }

    public String getNome_guerra() {
        return nome_guerra;
    }

    public void setNome_guerra(String nome_guerra) {
        this.nome_guerra = nome_guerra;
    }

    public String getSessao() {
        return sessao;
    }

    public void setSessao(String sessao) {
        this.sessao = sessao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora_saida() {
        return hora_saida;
    }

    public void setHora_saida(String hora_saida) {
        this.hora_saida = hora_saida;
    }

    public String getHora_entrada() {
        return hora_entrada;
    }

    public void setHora_entrada(String hora_entrada) {
        this.hora_entrada = hora_entrada;
    }

    public String getMotivo_entrada() {
        return motivo_entrada;
    }

    public void setMotivo_entrada(String motivo_entrada) {
        this.motivo_entrada = motivo_entrada;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }
    private byte[] imagem;
}
