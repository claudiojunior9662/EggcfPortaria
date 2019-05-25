/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.CadastroBEAN;
import model.bean.ControleExpedienteBEAN;
import model.dao.cadastroDAO;
import model.dao.controlefDAO;
import model.dao.controleDAO;
import com.itextpdf.text.Font;
import java.util.Date;
import model.bean.ControleForaExpedienteBEAN;
import model.bean.PreencheServicoBEAN;
import model.dao.PreencheServicoDAO;

/**
 *
 * @author claud
 */
public class Adm extends javax.swing.JFrame {

    /**
     * Creates new form Adm
     */
    public Adm() {
        initComponents();
        PreencheServicoDAO psDAO = new PreencheServicoDAO();
        int retorno = psDAO.verificaServicoEmAberto();
        if(retorno == 0){
            iniciarServico.setEnabled(true);
            finalizarServico.setEnabled(false);
        }else{
            iniciarServico.setEnabled(false);
            finalizarServico.setEnabled(true);
        }
        System.out.println(retorno);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        data_rel = new com.toedter.calendar.JCalendar();
        iniciarServico = new javax.swing.JButton();
        finalizarServico = new javax.swing.JButton();
        emitirRelatorios = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Administrador");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Relatórios - Gerar", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        iniciarServico.setText("INICIAR SERVIÇO");
        iniciarServico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iniciarServicoActionPerformed(evt);
            }
        });

        finalizarServico.setText("FINALIZAR SERVIÇO");
        finalizarServico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                finalizarServicoActionPerformed(evt);
            }
        });

        emitirRelatorios.setText("EMITIR RELATÓRIOS");
        emitirRelatorios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emitirRelatoriosActionPerformed(evt);
            }
        });

        jButton1.setText("visitantes");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(data_rel, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(iniciarServico)
                                .addGap(97, 97, 97)
                                .addComponent(finalizarServico))
                            .addComponent(emitirRelatorios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addComponent(jButton1))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(data_rel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(emitirRelatorios)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(iniciarServico)
                            .addComponent(finalizarServico))
                        .addGap(43, 43, 43)
                        .addComponent(jButton1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void iniciarServicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iniciarServicoActionPerformed
        PreencheServico ps = new PreencheServico();
        ps.setTitle("NOVA GUARNIÇAO");
        ps.setLocationRelativeTo(null);
        ps.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        ps.setVisible(true);
    }//GEN-LAST:event_iniciarServicoActionPerformed

    private void finalizarServicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_finalizarServicoActionPerformed
        PreencheServicoDAO psDAO = new PreencheServicoDAO();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat sdh = new SimpleDateFormat("dd/MM/yyyy");
        ConfirmaFinalizarServico cfs = new ConfirmaFinalizarServico();
        cfs.setTitle("FINALIZAR SERCIÇO");
        cfs.setLocationRelativeTo(null);   
        cfs.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        ConfirmaFinalizarServico.hora.setText(sdf.format(new Date()));
        ConfirmaFinalizarServico.dia.setText(sdh.format(new Date()));
        for(PreencheServicoBEAN psBEAN : psDAO.retornaGuarnicao()){
            ConfirmaFinalizarServico.graduado_dia.setText(psBEAN.getGraduado_dia());
            ConfirmaFinalizarServico.cabo_dia.setText(psBEAN.getCabo_dia());
            ConfirmaFinalizarServico.soldado1.setText(psBEAN.getSoldado1());
            ConfirmaFinalizarServico.soldado2.setText(psBEAN.getSoldado2());
            ConfirmaFinalizarServico.soldado3.setText(psBEAN.getSoldado3());
            ConfirmaFinalizarServico.id = psBEAN.getId();
            ConfirmaFinalizarServico.data = psBEAN.getData();
            ConfirmaFinalizarServico.horaInicio = psBEAN.getHora_inicio();
        }
        cfs.setVisible(true);
    }//GEN-LAST:event_finalizarServicoActionPerformed

    private void emitirRelatoriosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emitirRelatoriosActionPerformed
        emiteRelatorioVeiculos();
        emiteRelatorioForaExpediente();
        emiteRelatorioHorarioExpediente();
        emiteRelatorioVisitantes();
    }//GEN-LAST:event_emitirRelatoriosActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        emiteRelatorioVisitantes();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Adm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Adm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Adm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Adm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Adm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static com.toedter.calendar.JCalendar data_rel;
    private javax.swing.JButton emitirRelatorios;
    public static javax.swing.JButton finalizarServico;
    public static javax.swing.JButton iniciarServico;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
    public boolean verifica_dir(String path){
        boolean retorno = false;
        File dir = new File(path);
        if(!dir.exists()){
            retorno = false;
        }else{
            retorno = true;
        }
        return retorno;
    }
    
    public void cria_dir(String path){
        try{
            File dir = new File(path);
            dir.mkdir();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Erro ao criar o diretorio");
        }
    }
    
    public void emiteRelatorioVeiculos(){
        SimpleDateFormat sdh = new SimpleDateFormat("dd/MM/yyyy");
        String dataFormatada = sdh.format(this.data_rel.getDate());
        cadastroDAO dao = new cadastroDAO();
        try{
            boolean retorno;
            retorno = dao.busca_data_nome(dataFormatada);
            if(retorno == false){
                JOptionPane.showMessageDialog(null,"Não existem registros de VEÍCULOS para a data selecionada!");
                return;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Adm.class.getName()).log(Level.SEVERE, null, ex);
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Document document = new Document(PageSize.A4, 30, 20, 20, 30) {};
        String path = "relatorios_portaria/" + sdf.format(this.data_rel.getDate());

        try{
            if(verifica_dir(path)){
                
            }else{
                cria_dir(path);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Erro!  " + e);
        }

        try {
            PdfWriter.getInstance(document, new FileOutputStream(path + "/Relatorio-Veiculos"+sdf.format(this.data_rel.getDate())+".pdf"));
            document.open();

            document.setMargins(20,20,20,20);

            Image imagem = Image.getInstance("rel.bmp");
            imagem.setAlignment(1);
            imagem.scaleToFit(570, 600);
            document.add(imagem);

            PdfPTable table = new PdfPTable(7);
            float[] tams = {0.1f,0.1f,0.1f,0.1f,0.1f,0.1f, 0.3f};
            table.setWidths(tams);
            table.setWidthPercentage(105);

            document.add(new Paragraph("\n"));
            Paragraph p = new Paragraph("Relatório de Entrada/Saída de Veículos do dia "+dataFormatada+":", FontFactory.getFont("arial.ttf",12,Font.BOLD));
            p.setAlignment(1);
            document.add(p);
            document.add(new Paragraph("\n"));

            PdfPCell c1 = new PdfPCell(new Phrase("Marca do Veículo".toUpperCase(),FontFactory.getFont("arial.ttf",9,Font.BOLD,BaseColor.WHITE)));
            c1.setBackgroundColor(BaseColor.BLACK);
            c1.setHorizontalAlignment(1);
            PdfPCell c2 = new PdfPCell(new Phrase("Modelo do Veículo".toUpperCase(),FontFactory.getFont("arial.ttf",9,Font.BOLD,BaseColor.WHITE)));
            c2.setBackgroundColor(BaseColor.BLACK);
            c2.setHorizontalAlignment(1);
            PdfPCell c3 = new PdfPCell(new Phrase("Cor do Veículo".toUpperCase(),FontFactory.getFont("arial.ttf",9,Font.BOLD,BaseColor.WHITE)));
            c3.setBackgroundColor(BaseColor.BLACK);
            c3.setHorizontalAlignment(1);
            PdfPCell c4 = new PdfPCell(new Phrase("Placa do Veículo".toUpperCase(),FontFactory.getFont("arial.ttf",9,Font.BOLD,BaseColor.WHITE)));
            c4.setBackgroundColor(BaseColor.BLACK);
            c4.setHorizontalAlignment(1);
            PdfPCell c5 = new PdfPCell(new Phrase("Hora Entrada".toUpperCase(),FontFactory.getFont("arial.ttf",9,Font.BOLD,BaseColor.WHITE)));
            c5.setBackgroundColor(BaseColor.BLACK);
            c5.setHorizontalAlignment(1);
            PdfPCell c6 = new PdfPCell(new Phrase("Hora Saída".toUpperCase(),FontFactory.getFont("arial.ttf",9,Font.BOLD,BaseColor.WHITE)));
            c6.setBackgroundColor(BaseColor.BLACK);
            c6.setHorizontalAlignment(1);
            PdfPCell c7 = new PdfPCell(new Phrase("Condutor".toUpperCase(),FontFactory.getFont("arial.ttf",9,Font.BOLD,BaseColor.WHITE)));
            c7.setBackgroundColor(BaseColor.BLACK);
            c7.setHorizontalAlignment(1);
            table.addCell(c1);
            table.addCell(c2);
            table.addCell(c3);
            table.addCell(c4);
            table.addCell(c5);
            table.addCell(c6);
            table.addCell(c7);

            for(CadastroBEAN c: dao.read_pdf_veiculos(dataFormatada)){
                PdfPCell c8 = new PdfPCell(new Phrase(c.getMarca_veiculo(),FontFactory.getFont("arial.ttf",10,BaseColor.BLACK)));
                PdfPCell c9 = new PdfPCell(new Phrase(c.getModelo_veiculo(),FontFactory.getFont("arial.ttf",10,BaseColor.BLACK)));
                PdfPCell c10 = new PdfPCell(new Phrase(c.getCor_veiculo(),FontFactory.getFont("arial.ttf",10,BaseColor.BLACK)));
                PdfPCell c11 = new PdfPCell(new Phrase(c.getPlaca_veiculo(),FontFactory.getFont("arial.ttf",10,BaseColor.BLACK)));
                PdfPCell c12 = new PdfPCell(new Phrase(c.getHoraE(),FontFactory.getFont("arial.ttf",10,BaseColor.BLACK)));
                PdfPCell c13 = new PdfPCell(new Phrase(c.getHoraS(),FontFactory.getFont("arial.ttf",10,BaseColor.BLACK)));
                PdfPCell c14 = new PdfPCell(new Phrase(c.getNomeCompleto(),FontFactory.getFont("arial.ttf",10,BaseColor.BLACK)));
                table.addCell(c8);
                table.addCell(c9);
                table.addCell(c10);
                table.addCell(c11);
                table.addCell(c12);
                table.addCell(c13);
                table.addCell(c14);
            }
            document.add(table);
            document.close();
        } catch (FileNotFoundException | DocumentException ex ) {
            JOptionPane.showMessageDialog(null,"Erro ao gerar o arquivo!  " + ex);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,"Imagem não encontrada " + ex);
        }
    }
    
    public void emiteRelatorioForaExpediente(){
        SimpleDateFormat sdh = new SimpleDateFormat("dd/MM/yyyy");
        String dataFormatada = sdh.format(this.data_rel.getDate());
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        controlefDAO cfdao = new controlefDAO();
          
        try{
            boolean retorno2;
            retorno2 = cfdao.busca_data_controlef(dataFormatada);
            if(retorno2 == false){
                JOptionPane.showMessageDialog(null,"Não existem registros FORA DO EXPEDIENTE para a data selecionada!");
                return;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Adm.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String path = "relatorios_portaria/" + sdf.format(this.data_rel.getDate());

        try{
            if(verifica_dir(path)){
                
            }else{
                cria_dir(path);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Erro!  " + e);
        }

        Document document = new Document(PageSize.A4, 30, 20, 20, 30);
        
        try {
            try{
                PdfWriter.getInstance(document, new FileOutputStream(path + "/Relatorio_Entrada_ForaDoExpediente"+sdf.format(this.data_rel.getDate())+".pdf"));
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"Erro ao gerar o arquivo!" + ex);
            }
            document.open();

            document.setMargins(20,20,20,20);

            Image imagem = Image.getInstance("rel.bmp");
            imagem.setAlignment(1);
            imagem.scaleToFit(570, 600);
            document.add(imagem);

            PdfPTable table = new PdfPTable(6);
            float[] tams = {0.06f,0.15f,0.12f,0.08f,0.07f,0.40f};
            table.setWidths(tams);
            table.setWidthPercentage(105);
            table.setHorizontalAlignment(Element.ALIGN_CENTER);

            document.add(new Paragraph("\n"));
            Paragraph p = new Paragraph("Relatório de entrada de militares fora do horário de expediente do dia " + dataFormatada + ":", FontFactory.getFont("arial.ttf",12,Font.BOLD));
            p.setAlignment(1);
            document.add(p);
            document.add(new Paragraph("\n"));

            PdfPCell c1 = new PdfPCell(new Phrase("Posto/Grad".toUpperCase(),FontFactory.getFont("arial.ttf",9,Font.BOLD,BaseColor.WHITE)));
            c1.setBackgroundColor(BaseColor.BLACK);
            c1.setHorizontalAlignment(1);
            PdfPCell c2 = new PdfPCell(new Phrase("Nome de Guerra".toUpperCase(),FontFactory.getFont("arial.ttf",9,Font.BOLD,BaseColor.WHITE)));
            c2.setBackgroundColor(BaseColor.BLACK);
            c2.setHorizontalAlignment(1);
            PdfPCell c3 = new PdfPCell(new Phrase("Seção".toUpperCase(),FontFactory.getFont("arial.ttf",10,Font.BOLD,BaseColor.WHITE)));
            c3.setBackgroundColor(BaseColor.BLACK);
            c3.setHorizontalAlignment(1);
            PdfPCell c4 = new PdfPCell(new Phrase("Hora Entrada".toUpperCase(),FontFactory.getFont("arial.ttf",9,Font.BOLD,BaseColor.WHITE)));
            c4.setBackgroundColor(BaseColor.BLACK);
            c4.setHorizontalAlignment(1);
            PdfPCell c5 = new PdfPCell(new Phrase("Hora Saída".toUpperCase(),FontFactory.getFont("arial.ttf",9,Font.BOLD,BaseColor.WHITE)));
            c5.setBackgroundColor(BaseColor.BLACK);
            c5.setHorizontalAlignment(1);
            PdfPCell c6 = new PdfPCell(new Phrase("Motivo Entrada".toUpperCase(),FontFactory.getFont("arial.ttf",10,Font.BOLD,BaseColor.WHITE)));
            c6.setBackgroundColor(BaseColor.BLACK);
            c6.setHorizontalAlignment(1);
            table.addCell(c1);
            table.addCell(c2);
            table.addCell(c3);
            table.addCell(c4);
            table.addCell(c5);
            table.addCell(c6);

                for(ControleForaExpedienteBEAN cf: cfdao.read_pdf_controlef(dataFormatada)){
                    PdfPCell c8 = new PdfPCell(new Phrase(cf.getGrad_posto(),FontFactory.getFont("arial.ttf",10,BaseColor.BLACK)));
                    PdfPCell c9 = new PdfPCell(new Phrase(cf.getNome_guerra(),FontFactory.getFont("arial.ttf",10,BaseColor.BLACK)));
                    PdfPCell c10 = new PdfPCell(new Phrase(cf.getSessao(),FontFactory.getFont("arial.ttf",10,BaseColor.BLACK)));
                    PdfPCell c11 = new PdfPCell(new Phrase(cf.getHora_entrada(),FontFactory.getFont("arial.ttf",10,BaseColor.BLACK)));

                    table.addCell(c8);
                    table.addCell(c9);
                    table.addCell(c10);
                    table.addCell(c11);
                    if(cf.getHora_saida().equals("")){
                        table.addCell("");
                    }else{
                        PdfPCell c12 = new PdfPCell(new Phrase(cf.getHora_saida(),FontFactory.getFont("arial.ttf",10,BaseColor.BLACK)));
                        table.addCell(c12);
                    }
                    PdfPCell c13 = new PdfPCell(new Phrase(cf.getMotivo_entrada(),FontFactory.getFont("arial.ttf",10,BaseColor.BLACK)));
                    table.addCell(c13);   
                }
                document.add(table);
                document.close();
            } catch (DocumentException ex) {
                Logger.getLogger(Adm.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
            Logger.getLogger(Adm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void emiteRelatorioHorarioExpediente(){
        SimpleDateFormat sdh = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String dataFormatada = sdh.format(this.data_rel.getDate());
        controleDAO cdao = new controleDAO();
          
        try{
            boolean retorno2;
            retorno2 = cdao.busca_data_controle(dataFormatada);
            if(retorno2 == false){
                JOptionPane.showMessageDialog(null,"Não existem registros NO HORÁRIO DE EXPEDIENTE para a data selecionada!");
                return;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Adm.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        String path = "relatorios_portaria/" + sdf.format(this.data_rel.getDate());

        try{
            if(verifica_dir(path)){
                
            }else{
                cria_dir(path);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Erro!  " + e);
        }

        Document document = new Document(PageSize.A4, 30, 20, 20, 30);
        
        try {
            try{
                PdfWriter.getInstance(document, new FileOutputStream(path + "/Relatorio_CB-SD"+sdf.format(this.data_rel.getDate())+".pdf"));
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"Erro ao gerar o arquivo!" + ex);
            }
            document.open();

            document.setMargins(20,20,20,20);

            Image imagem = Image.getInstance("rel.bmp");
            imagem.setAlignment(1);
            imagem.scaleToFit(570, 600);
            document.add(imagem);

            PdfPTable table = new PdfPTable(6);
            float[] tams = {0.06f,0.20f,0.13f,0.08f,0.09f,0.39f};
            table.setWidths(tams);
            table.setWidthPercentage(105);
            table.setHorizontalAlignment(Element.ALIGN_CENTER);

            document.add(new Paragraph("\n"));
            Paragraph p = new Paragraph("Relatório de Saída de CB/SD no horário de expediente do dia "+dataFormatada+":", FontFactory.getFont("arial.ttf",12,Font.BOLD));
            p.setAlignment(1);
            document.add(p);
            document.add(new Paragraph("\n"));

            PdfPCell c1 = new PdfPCell(new Phrase("Posto/Grad".toUpperCase(),FontFactory.getFont("arial.ttf",9,Font.BOLD,BaseColor.WHITE)));
            c1.setBackgroundColor(BaseColor.BLACK);
            c1.setHorizontalAlignment(1);
            PdfPCell c2 = new PdfPCell(new Phrase("Nome de Guerra".toUpperCase(),FontFactory.getFont("arial.ttf",10,Font.BOLD,BaseColor.WHITE)));
            c2.setBackgroundColor(BaseColor.BLACK);
            c2.setHorizontalAlignment(1);
            PdfPCell c3 = new PdfPCell(new Phrase("Seção".toUpperCase(),FontFactory.getFont("arial.ttf",10,Font.BOLD,BaseColor.WHITE)));
            c3.setBackgroundColor(BaseColor.BLACK);
            c3.setHorizontalAlignment(1);
            PdfPCell c4 = new PdfPCell(new Phrase("Hora Saída".toUpperCase(),FontFactory.getFont("arial.ttf",10,Font.BOLD,BaseColor.WHITE)));
            c4.setBackgroundColor(BaseColor.BLACK);
            c4.setHorizontalAlignment(1);
            PdfPCell c5 = new PdfPCell(new Phrase("Hora Entrada".toUpperCase(),FontFactory.getFont("arial.ttf",10,Font.BOLD,BaseColor.WHITE)));
            c5.setBackgroundColor(BaseColor.BLACK);
            c5.setHorizontalAlignment(1);
            PdfPCell c6 = new PdfPCell(new Phrase("Motivo Saída".toUpperCase(),FontFactory.getFont("arial.ttf",10,Font.BOLD,BaseColor.WHITE)));
            c6.setBackgroundColor(BaseColor.BLACK);
            c6.setHorizontalAlignment(1);
            table.addCell(c1);
            table.addCell(c2);
            table.addCell(c3);
            table.addCell(c4);
            table.addCell(c5);
            table.addCell(c6);

                for(ControleExpedienteBEAN c: cdao.read_pdf_controle(dataFormatada)){
                    PdfPCell c8 = new PdfPCell(new Phrase(c.getGrad_posto(),FontFactory.getFont("arial.ttf",10,BaseColor.BLACK)));
                    PdfPCell c9 = new PdfPCell(new Phrase(c.getNome_guerra(),FontFactory.getFont("arial.ttf",10,BaseColor.BLACK)));
                    PdfPCell c10 = new PdfPCell(new Phrase(c.getSessao(),FontFactory.getFont("arial.ttf",10,BaseColor.BLACK)));
                    PdfPCell c11 = new PdfPCell(new Phrase(c.getHora_saida(),FontFactory.getFont("arial.ttf",10,BaseColor.BLACK)));

                    table.addCell(c8);
                    table.addCell(c9);
                    table.addCell(c10);
                    table.addCell(c11);
                    if(c.getHora_entrada().equals("")){
                        table.addCell("");
                    }else{
                        PdfPCell c12 = new PdfPCell(new Phrase(c.getHora_entrada(),FontFactory.getFont("arial.ttf",10,BaseColor.BLACK)));
                        table.addCell(c12);
                    }
                    PdfPCell c13 = new PdfPCell(new Phrase(c.getMotivo_saida(),FontFactory.getFont("arial.ttf",10,BaseColor.BLACK)));
                    table.addCell(c13);   
                }
                document.add(table);
                document.close();
            } catch (DocumentException ex) {
                Logger.getLogger(Adm.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
            Logger.getLogger(Adm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void emiteRelatorioVisitantes(){
        SimpleDateFormat sdh = new SimpleDateFormat("dd/MM/yyyy");
        String dataFormatada = sdh.format(this.data_rel.getDate());
        cadastroDAO dao = new cadastroDAO();
        try{
            boolean retorno;
            retorno = dao.busca_data(dataFormatada);
            if(retorno == false){
                JOptionPane.showMessageDialog(null,"Não existem registros DE VISITANTES para a data selecionada!");
                return;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Adm.class.getName()).log(Level.SEVERE, null, ex);
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Document document = new Document(PageSize.A4, 30, 20, 20, 30) {};

        String path = "relatorios_portaria/" + sdf.format(this.data_rel.getDate());

        try{
            if(verifica_dir(path)){
                
            }else{
                cria_dir(path);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Erro!  " + e);
        }

        try {
            PdfWriter.getInstance(document, new FileOutputStream(path + "/Relatorio-Visitantes"+sdf.format(this.data_rel.getDate())+".pdf"));
            document.open();

            document.setMargins(20,20,20,20);

            Image imagem = Image.getInstance("rel.bmp");
            imagem.setAlignment(1);
            imagem.scaleToFit(570, 600);
            document.add(imagem);

            PdfPTable table = new PdfPTable(6);
            float[] tams = {0.4f,0.15f,0.12f,0.08f,0.08f,0.17f};
            table.setWidths(tams);
            table.setWidthPercentage(105);

            document.add(new Paragraph("\n"));
            Paragraph p = new Paragraph("Relatório de Entrada/Saída de Visitantes do dia "+dataFormatada+":", FontFactory.getFont("arial.ttf",12,Font.BOLD));
            p.setAlignment(1);
            document.add(p);
            document.add(new Paragraph("\n"));

            PdfPCell c1 = new PdfPCell(new Phrase("Nome Completo".toUpperCase(),FontFactory.getFont("arial.ttf",10,Font.BOLD,BaseColor.WHITE)));
            c1.setBackgroundColor(BaseColor.BLACK);
            c1.setHorizontalAlignment(1);
            PdfPCell c2 = new PdfPCell(new Phrase("Tipo Doc".toUpperCase(),FontFactory.getFont("arial.ttf",10,Font.BOLD,BaseColor.WHITE)));
            c2.setBackgroundColor(BaseColor.BLACK);
            c2.setHorizontalAlignment(1);
            PdfPCell c3 = new PdfPCell(new Phrase("Nº".toUpperCase(),FontFactory.getFont("arial.ttf",10,Font.BOLD,BaseColor.WHITE)));
            c3.setBackgroundColor(BaseColor.BLACK);
            c3.setHorizontalAlignment(1);
            PdfPCell c4 = new PdfPCell(new Phrase("Hora Entrada".toUpperCase(),FontFactory.getFont("arial.ttf",8,Font.BOLD,BaseColor.WHITE)));
            c4.setBackgroundColor(BaseColor.BLACK);
            c4.setHorizontalAlignment(1);
            PdfPCell c5 = new PdfPCell(new Phrase("Hora Saída".toUpperCase(),FontFactory.getFont("arial.ttf",9,Font.BOLD,BaseColor.WHITE)));
            c5.setBackgroundColor(BaseColor.BLACK);
            c5.setHorizontalAlignment(1);
            PdfPCell c6 = new PdfPCell(new Phrase("Destino".toUpperCase(),FontFactory.getFont("arial.ttf",10,Font.BOLD,BaseColor.WHITE)));
            c6.setBackgroundColor(BaseColor.BLACK);
            c6.setHorizontalAlignment(1);
            table.addCell(c1);
            table.addCell(c2);
            table.addCell(c3);
            table.addCell(c4);
            table.addCell(c5);
            table.addCell(c6);

            for(CadastroBEAN c: dao.read_pdf(dataFormatada)){
                PdfPCell c7 = new PdfPCell(new Phrase(c.getNomeCompleto(),FontFactory.getFont("arial.ttf",10,BaseColor.BLACK)));
                PdfPCell c8 = new PdfPCell(new Phrase(c.getTipoDocumento(),FontFactory.getFont("arial.ttf",10,BaseColor.BLACK)));
                PdfPCell c9 = new PdfPCell(new Phrase(c.getDocumentoIden(),FontFactory.getFont("arial.ttf",10,BaseColor.BLACK)));
                PdfPCell c10 = new PdfPCell(new Phrase(c.getHoraE(),FontFactory.getFont("arial.ttf",10,BaseColor.BLACK)));
                table.addCell(c7);
                table.addCell(c8);
                table.addCell(c9);
                table.addCell(c10);
                if(c.getHoraS() == null){
                    table.addCell("");
                }else{
                    PdfPCell c11 = new PdfPCell(new Phrase(c.getHoraS(),FontFactory.getFont("arial.ttf",10,BaseColor.BLACK)));
                    table.addCell(c11);
                }
                PdfPCell c12 = new PdfPCell(new Phrase(c.getDestino() + " - " + c.getComquemFalar(),FontFactory.getFont("arial.ttf",10,BaseColor.BLACK)));
                table.addCell(c12);
            }
            document.add(table);
            document.close();
        } catch (FileNotFoundException | DocumentException ex ) {
            JOptionPane.showMessageDialog(null,"Erro ao gerar o arquivo!  " + ex);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,"Imagem não encontrada " + ex);
        }
    }
    
    public void abreDiretorio(String path) throws IOException{
        String os = System.getProperty("os.name");
        os = os.toLowerCase();
        if(os.contains("win")){
            Runtime.getRuntime().exec("explorer relatorios_porta");
        }
    }

}
