/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package liracs.view.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

/**
 *
 * @author Aluno
 */
public class Capture implements Runnable{

    TargetDataLine line;
    Thread thread;
    
    private AudioInputStream ais;

    private ByteArrayOutputStream out;
    
    private double duration;
    
    private byte[] result;

    public Capture() {
        this.duration=0;
    }
    
    
    public void start() {
        thread = new Thread(this);
        thread.setName("Capt");
        thread.start();
    }
    
    public void stop() {
        thread = null;
    }
    
    public void shutdown(String msg) {
        System.out.println(msg);
        thread = null;
        
    }
    
    public void run() {
        
       //duration=0;
       ais=null;         // AIS = Audio Input Stream
       
       AudioData ad = new AudioData();
       AudioFormat audioFormat = ad.getFormat();
       
       //verifica se a linha é suportada
       
       DataLine.Info info = new DataLine.Info(TargetDataLine.class, audioFormat); 
       
       if(!AudioSystem.isLineSupported(info)) {
           this.shutdown("Dispostivo de entrada não conectado!");
           return;
       }
       
       //tenta abrir a linha para gravação
       try {
           line = (TargetDataLine) AudioSystem.getLine(info);
           line.open(audioFormat, line.getBufferSize());
           
       }catch(SecurityException ex) {
           this.shutdown(ex.toString());
           return;
           
       } catch (LineUnavailableException ex) {
           this.shutdown("Unable to open the line: " + ex);
           return;
       }
            
       //já abriu a linha jovem, agora é começar a gravar né? e.e
       
       out = new ByteArrayOutputStream();
       int frameSizeInBytes = audioFormat.getFrameSize();
       int bufferLengthInFrames = line.getBufferSize() / 8;
       int bufferLengthInBytes = bufferLengthInFrames * frameSizeInBytes;
       byte[] data = new byte[bufferLengthInBytes];
       int numBytesRead;
            
       line.start();
       Boolean antibreak = true;
       
       while (thread != null && antibreak) {
            if((numBytesRead = line.read(data, 0, bufferLengthInBytes)) == -1) {
                antibreak=false;
            }
            out.write(data, 0, numBytesRead);
       }
       
       //fecha a linha de gravaçao
       line.stop();
       line.close();
       
       this.setResult(data);
      
       // stop and close the output stream
       try {
            out.flush();
            out.close();
       } catch (IOException ex) {
            ex.printStackTrace();
       }
       // load bytes into the audio input stream for playback

            byte audioBytes[] = out.toByteArray();
            ByteArrayInputStream bais = new ByteArrayInputStream(audioBytes);
            ais = new AudioInputStream(bais, audioFormat, audioBytes.length / frameSizeInBytes);

            long milliseconds = (long)((ais.getFrameLength() * 1000) / audioFormat.getFrameRate());
            duration = milliseconds / 1000.0;
            this.setDuration(duration);
            try {
                ais.reset();
            } catch (Exception ex) { 
                ex.printStackTrace(); 
                return;
            }

    }
    
    public ByteArrayOutputStream getByteArrayOutputStream() {
        return out;
    }
    
    public byte[] getResult() {
        return result;
    }

    public void setResult(byte[] result) {
        this.result = result;
    }
    
    public AudioInputStream getAis() {
        return ais;
    }
    
    public void zeraAis() {
        this.ais = null;
    }
    
    public Double getDuration() {
        return duration;
    }
    
    public void setDuration(Double d) {
        this.duration = d;
    }
    
}