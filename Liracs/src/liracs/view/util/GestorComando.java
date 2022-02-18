/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package liracs.view.util;

import java.awt.Color;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Nelore
 */
public class GestorComando {

    private long cod_comando;
    //parte didática
    private JFrame frame;
    private JPanel panel;

    public GestorComando() {
        frame = new JFrame("Created by LIRacs");
        panel = new JPanel();
        panel.setOpaque(true);
        frame.setSize(400, 400);
        frame.add(panel);
    }

    public GestorComando(long cod_comando) {
        this.cod_comando = cod_comando;
        //parte didática

    }

    public void setCod_comando(long cod_comando) {
        this.cod_comando = cod_comando;
    }

    public void execute() {
        switch ((int) cod_comando) {
            case 1:
                //Desligar o computador
                try {
                    Process p = Runtime.getRuntime().exec("shutdown -s -t 0");
                } catch (IOException ex) {
                    Logger.getLogger(GestorComando.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case 2:
                //Reiniciar o computador
                try {
                    Process p = Runtime.getRuntime().exec("shutdown -g -t 0");
                } catch (IOException ex) {
                    Logger.getLogger(GestorComando.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case 3:
                //Abrir Google chrome
                try {
                    Process p = Runtime.getRuntime().exec("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe \"www.cefetmg.br\"");
                } catch (IOException ex) {
                    Logger.getLogger(GestorComando.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case 4:
                //Abrir firefox
                try {
                    Process p = Runtime.getRuntime().exec("C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe \"www.cefetmg.br\"");
                } catch (IOException ex) {
                    Logger.getLogger(GestorComando.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case 5:
                //Abrir notepad
                try {
                    Process p = Runtime.getRuntime().exec("notepad.exe");
                } catch (IOException ex) {
                    Logger.getLogger(GestorComando.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case 6:
                //frame visible
                panel.setBackground(Color.white);
                frame.setVisible(true);
                break;
            case 7:
                //frame azul
                panel.setBackground(Color.BLUE);
                break;
            case 8:
                //frame amarela
                panel.setBackground(Color.yellow);
                break;
            case 9:
                //frame vermelha
                panel.setBackground(Color.red);
                break;
        }
    }
}
