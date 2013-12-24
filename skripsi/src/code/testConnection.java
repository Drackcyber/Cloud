/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package code;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Rootkit
 */
public class testConnection {
    String url="jdbc:mysql://localhost/skripsi";
    String userk="root";
    String passk="";
    
    private ResultSet result;
    private Statement stat;
    private Connection koneksi;
    
    public Statement kone(){
    try{ 
            Class.forName("com.mysql.jdbc.Driver"); 
            koneksi=DriverManager.getConnection(url,userk,passk); 
            //JOptionPane.showMessageDialog(null, "Koneksi Berhasil"); 
            stat=koneksi.createStatement();
            
        }catch(Exception e){ 
            //JOptionPane.showMessageDialog(null, "Maaf anda tidak terhubung dengan internet!!!"); 
        } 
        return stat;
    }
}
