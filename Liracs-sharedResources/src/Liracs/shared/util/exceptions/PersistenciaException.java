/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Liracs.shared.util.exceptions;

/**
 *
 * @author Nelore
 */
public class PersistenciaException extends Exception {

    public PersistenciaException(String msg, Exception exception) {
        super(msg, exception);
    }

    public PersistenciaException(String msg) {
        super(msg);
    }

}
