/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package liracs.view.util;

import Liracs.shared.domain.join.JoinIgIcC;
import Liracs.shared.util.exceptions.PersistenciaException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import liracs.view.Stub.StubInstrucaoComando;

/**
 *
 * @author Nelore
 */
public class Gravacao {
    
    private static GestorComando gestorComando;
    private Capture capture;
    private final long userLoggedID;

    public Gravacao(long ID, Capture capt, GestorComando gestorComando) {
        this.userLoggedID = ID;
        this.capture = capt;
        this.gestorComando = gestorComando;
    }

    public void compare() {
        try {
          //Gera os pontos chaves do áudio recem gravado
            DefinePoints getPoints = new DefinePoints();
            long[] hashes = getPoints.getHash(capture);
          //Recebe os pontos chaves dos áudios do Banco de dados
            StubInstrucaoComando stubInstrucaoComando = new StubInstrucaoComando();

            List<JoinIgIcC> audioList = stubInstrucaoComando.listarPorUsuario(userLoggedID);
            FindPoints[] findPoints = new FindPoints[audioList.size()];
            
            ArrayList <JoinANDpoints> results = new ArrayList<>();
            
            for(int i=0; i< audioList.size(); i++) {
                findPoints[i] = new FindPoints(hashes, audioList.get(i));
                Thread t = new Thread(findPoints[i]);
                t.start();
                
                JoinANDpoints joinANDpoints = new JoinANDpoints();
                joinANDpoints.setEqualPoints(findPoints[i].getEqualPoints());
                joinANDpoints.setJoin(audioList.get(i));
                results.add(joinANDpoints);
            }
   
            Gravacao.moreEquals(results);
        } catch (PersistenciaException ex) {
            Logger.getLogger(Gravacao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static void moreEquals(ArrayList<JoinANDpoints> results) {
      //Determinar qual dos áudios possui mais pontos em comum  
        
        int morePoints = -1;
        int savedI = 0;
        for(int i=0; i< results.size(); i++) {
            if(results.get(i).getEqualPoints() > morePoints) {
                morePoints = results.get(i).getEqualPoints();
                savedI = i;
            }
        }
      //audio com maior número de pontos em comum identificado
        long comandoID = results.get(savedI).getJoin().getCom_cod_Comando();
        gestorComando.setCod_comando(comandoID);
        gestorComando.execute();
    }

}
