/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package liracs.view.util;

import Liracs.shared.domain.join.JoinIgIcC;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Nelore
 */
public class FindPoints implements Runnable{
    
    private JoinIgIcC join;
    private long[] hashes;
    
    private int equalPoints;
    private boolean maked;
    
    public FindPoints(long[] hashes, JoinIgIcC join) {
        this.hashes = hashes;
        this.join = join;
        maked = false;
    }
    
    @Override
    public void run() {
        equalPoints = 0;
        ArrayList<Integer> positionI = new ArrayList<>();
        ArrayList<Integer> positionJ = new ArrayList<>();
        // procura os pontos iguais
        for(int i=0; i< hashes.length; i++) {
            for(int j = i+1; j< join.getInst_audio().length; j++) {
                if(hashes[i] == join.getInst_audio()[j]) {
                    equalPoints++;
                    positionI.add(i);
                    positionJ.add(j);
                }
            }
        }
        this.put(true);
    }
    private synchronized void put(boolean maked){
        this.maked = maked;
        if(maked)
            notifyAll();
    }
    
    public synchronized int getEqualPoints() {
        while(!maked){
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(FindPoints.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return equalPoints;
    }
    
}
