/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package liracs.view.util;

import javax.sound.sampled.AudioFormat;

/**
 *
 * @author Aluno
 */
public class AudioData {
    
    private String encodSTR;
    private int sampleSize;
    private float rate;
    private String signedString;
    private Boolean bigEnd;
    private int channels;
    
    
    
    public AudioFormat getFormat() {
        AudioFormat.Encoding encod = AudioFormat.Encoding.ULAW; //mexe nesse ULAW n, pf <3
        encodSTR = "linear";
        rate = 44100;
        sampleSize=16;
        signedString = "signed";
        bigEnd= true;
        channels = 2;
        encod = AudioFormat.Encoding.PCM_SIGNED;
        
        return new AudioFormat(encod, rate, sampleSize, channels, 
                (sampleSize/8)*channels, rate, bigEnd);
        
    }

    public AudioData() {}

    public String getEncodSTR() {
        return encodSTR;
    }

    public void setEncodSTR(String encodSTR) {
        this.encodSTR = encodSTR;
    }

    public int getSampleSize() {
        return sampleSize;
    }

    public void setSampleSize(int sampleSize) {
        this.sampleSize = sampleSize;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public String getSignedString() {
        return signedString;
    }

    public void setSignedString(String signedString) {
        this.signedString = signedString;
    }

    public Boolean getBigEnd() {
        return bigEnd;
    }

    public void setBigEnd(Boolean bigEnd) {
        this.bigEnd = bigEnd;
    }

    public int getChannels() {
        return channels;
    }

    public void setChannels(int channels) {
        this.channels = channels;
    }
}
    
    
    