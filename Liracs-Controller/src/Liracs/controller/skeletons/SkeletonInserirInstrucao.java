/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Liracs.controller.skeletons;

import Liracs.model.service.ManterInstrucaoGravada;
import Liracs.model.service.impl.IManterInstrucaoGravada;
import Liracs.shared.model.domain.InstrucaoGravada;
import Liracs.shared.util.exceptions.NegocioException;
import Liracs.shared.util.exceptions.PersistenciaException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nelore
 */
public class SkeletonInserirInstrucao {

    private Socket socket;
    private ObjectInputStream reader;
    private ObjectOutputStream writer;

    public SkeletonInserirInstrucao(Socket s, ObjectInputStream reader, ObjectOutputStream writer) {
        this.socket = s;
        this.reader = reader;
        this.writer = writer;
    }

    public void inserirInstrucao() {
        try {
            InstrucaoGravada instrucaoGravada = (InstrucaoGravada) reader.readObject();
            IManterInstrucaoGravada manterInstrucaoGravada = new ManterInstrucaoGravada();
            Long instrucaoID = manterInstrucaoGravada.cadastrar(instrucaoGravada);
            writer.writeLong(instrucaoID);
            writer.flush();
            
        } catch (IOException ex) {
            Logger.getLogger(SkeletonInserirInstrucao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SkeletonInserirInstrucao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PersistenciaException ex) {
            Logger.getLogger(SkeletonInserirInstrucao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NegocioException ex) {
            Logger.getLogger(SkeletonInserirInstrucao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
