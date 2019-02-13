/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
  * @author Andrea Marín y Luis Felipe Landa
 */
public interface Bioinformatics extends Remote{
    public Task executeBioTask(Task t) throws RemoteException;
}
