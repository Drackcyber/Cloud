/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package code;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

/**
 *
 * @author Rootkit
 */
public class testConnectionFtp {
    
    String user;
    String pass;
    String host;
    //int port;
    
    public void getInfoFTP(String user1,String pass1){
        user=user1;
        pass=pass1;
        host="localhost";
        try {
            connection();
            //this.port="21";
        } catch (IOException ex) {
            Logger.getLogger(testConnectionFtp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public FTPFile[] connection() throws IOException {
         //koneksi server
            FTPClient ftpClient = new FTPClient();
            ftpClient.connect(host, 21);
            ftpClient.login(user, pass);
            
            FTPFile[] files = ftpClient.listFiles("");
            return files; //kirim array file ke program
    }
    public FTPClient connection1() throws IOException{
         //koneksi server
            FTPClient ftpClient = new FTPClient();
            ftpClient.connect(host, 21);
            ftpClient.login(user, pass);
            
            FTPFile[] files = ftpClient.listFiles("");
            return ftpClient; //kirim array file ke program
    }
    
    public static void main (String []args){
       // FTPFile[] connection = connection();
    }
    
    
}
