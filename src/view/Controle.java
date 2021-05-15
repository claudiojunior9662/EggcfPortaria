/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.text.SimpleDateFormat;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.border.Border;

/**
 *
 * @author claudio
 */
public class Controle {
    public static Border bordaLinhaVermelha = BorderFactory.createLineBorder(Color.red);
    public static Border bordaLinhaVerde = BorderFactory.createLineBorder(Color.green);
    public static Border bordaLinhaPadrao = BorderFactory.createLineBorder(Color.lightGray);
    public static String tipoDesign = "GTK+";
    public static SimpleDateFormat dataPadrao = new SimpleDateFormat("dd/MM/yyyy");
    public static SimpleDateFormat horaPadrao = new SimpleDateFormat("HH:mm:ss");
    public static SimpleDateFormat dataPadraoSistema = new SimpleDateFormat("dd-MM-yyyy");
    public static SimpleDateFormat horaPadraoSistema = new SimpleDateFormat("HH:mm:ss");
    
    //MENSAGENS PADRÃO----------------------------------------------------------
    /**
     * Exibe as mensagens padrão do sistema
     * @param tipo 1 - INFORMATIVA, 2 - ADVERTÊNCIA, 3 - ERRO
     * @param mensagem CONTEÚDO DA MENSAGEM
     */
    public static void exibePopUp(byte tipo, String mensagem){
        switch(tipo){
            case 1:
                JOptionPane.showMessageDialog(null, mensagem, "Informativo", JOptionPane.INFORMATION_MESSAGE);
                break;
            case 2:
                JOptionPane.showMessageDialog(null, mensagem, "Atenção!", JOptionPane.WARNING_MESSAGE);
                break;
            case 3:
                JOptionPane.showMessageDialog(null, mensagem, "Erro!", JOptionPane.ERROR_MESSAGE);
                break;
            default:
                JOptionPane.showMessageDialog(null, mensagem, "Informativo", JOptionPane.INFORMATION_MESSAGE);
                break;
        }
    }
}
