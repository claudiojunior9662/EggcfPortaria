/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

/**
 *
 * @author spd3
 */
public class PreencheServicoBEAN {
    private String data;
    private String cabo_dia;
    private String soldado1;
    private String soldado2;
    private String soldado3;
    private String hora_inicio;
    private String hora_fim;
    private String graduado_dia;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    

    public String getGraduado_dia() {
        return graduado_dia;
    }

    public void setGraduado_dia(String graduado_dia) {
        this.graduado_dia = graduado_dia;
    }

    
    public String getSoldado3() {
        return soldado3;
    }

    public void setSoldado3(String soldado3) {
        this.soldado3 = soldado3;
    }
    
    

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCabo_dia() {
        return cabo_dia;
    }

    public void setCabo_dia(String cabo_dia) {
        this.cabo_dia = cabo_dia;
    }

    public String getSoldado1() {
        return soldado1;
    }

    public void setSoldado1(String soldado1) {
        this.soldado1 = soldado1;
    }

    public String getSoldado2() {
        return soldado2;
    }

    public void setSoldado2(String soldado2) {
        this.soldado2 = soldado2;
    }

    public String getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(String hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public String getHora_fim() {
        return hora_fim;
    }

    public void setHora_fim(String hora_fim) {
        this.hora_fim = hora_fim;
    }
    
    
}
