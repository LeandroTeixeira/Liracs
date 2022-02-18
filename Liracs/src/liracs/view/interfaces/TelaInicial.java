/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package liracs.view.interfaces;

import Liracs.shared.model.domain.Usuario;
import Liracs.shared.util.exceptions.PersistenciaException;
import java.awt.Image;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import liracs.view.Stub.StubUsuario;

/**
 *
 * @author user
 */
public class TelaInicial extends javax.swing.JInternalFrame {
    private final long userLoggedID;
    /**
     * Creates new form T_Conta
     */
    public TelaInicial(long userLoggedID) {
        this.userLoggedID = userLoggedID;
        initComponents();
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        try{
            dadosUsuario();
        } catch (IOException ex) {
            Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        jPanel1 = new javax.swing.JPanel();
        perfilimagem = new javax.swing.JLabel();
        perfilnome = new javax.swing.JLabel();

        jLabel1.setText("jLabel1");

        setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        setResizable(true);
        getContentPane().setLayout(null);

        jLayeredPane2.setBackground(new java.awt.Color(255, 255, 255));
        jLayeredPane2.setMinimumSize(new java.awt.Dimension(620, 550));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(0, 0));
        jPanel1.setPreferredSize(new java.awt.Dimension(630, 550));

        perfilimagem.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 146, 152)));
        perfilimagem.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        perfilnome.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        perfilnome.setForeground(new java.awt.Color(0, 146, 152));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(101, 101, 101)
                        .addComponent(perfilimagem))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(276, 276, 276)
                        .addComponent(perfilnome)))
                .addContainerGap(354, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(perfilimagem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 377, Short.MAX_VALUE)
                .addComponent(perfilnome)
                .addGap(117, 117, 117))
        );

        jLayeredPane2.add(jPanel1);
        jPanel1.setBounds(0, 0, 630, 550);

        getContentPane().add(jLayeredPane2);
        jLayeredPane2.setBounds(0, 0, 620, 550);

        setSize(new java.awt.Dimension(630, 550));
    }// </editor-fold>//GEN-END:initComponents
    private void dadosUsuario() throws IOException {
       try{
        StubUsuario Suser = new StubUsuario();
       Usuario user = new Usuario();
       user = Suser.consultarPorId(userLoggedID);
       if(user.getEndFoto()!=null){
            //Usuario usuario = new Usuario();
            byte[] b = user.getB();
            File tt = new File("src"+File.separator+"liracs"+File.separator+"view"+File.separator+"icones"+File.separator+"perfil.png");
            FileOutputStream fos = new FileOutputStream(tt);
            fos.write(b);
            fos.close();
            // ImageIcon u = new ImageIcon("src"+File.separator+"liracs"+File.separator+"view"+File.separator+"icones"+File.separator+"perfil.png");
            //System.out.println(b);
            Image caminho = new javax.swing.ImageIcon(tt.getAbsolutePath()).getImage();
            Image img = caminho.getScaledInstance(400, 400, Image.SCALE_SMOOTH);
            
            ImageIcon x = new javax.swing.ImageIcon(img);
            //System.out.println("aquiiiiiiiiiiiii"+perfilimagem.toString());
            perfilimagem.setIcon(x);
            perfilnome.setText(user.getNom_Usuario());
            //perfilimagem.setIcon(new javax.swing.ImageIcon(getClass().getResource("liracs"+File.separator+"view"+File.separator+"icones"+File.separator+"perfil.png")));
       }
       }catch (PersistenciaException ex) {
            Logger.getLogger(TelaCadastrar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel perfilimagem;
    private javax.swing.JLabel perfilnome;
    // End of variables declaration//GEN-END:variables
    
}