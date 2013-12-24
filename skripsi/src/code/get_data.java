/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package code;

import coba.Beranda;
import coba.Login;
import java.net.ConnectException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rootkit
 */
public class get_data {
    
   
    
    private ResultSet result;
    private Statement stat;
    
    testConnection koneksi=new testConnection();
//    Beranda beranda=new Beranda();
    SetInfoClient info=new SetInfoClient();
   // Login login=new Login();
    
    public SetInfoClient get_lastUsed(String id) throws SQLException{
        //koneksi.kone();
        stat=koneksi.kone();
        result=stat.executeQuery("Select file,folder,used from cloud_last_used where id_mem ='"+id+"'"); 
        if(result.next()){
            info.setFile(Integer.parseInt(result.getString("file")));
            info.setFolder(Integer.parseInt(result.getString("folder")));
            info.setSize(result.getString("used"));
        }else{
            info.setFile(0);
            info.setFolder(0);
            info.setSize("-");
        } 
        return info;
    }
    public SetInfoClient get_accountFTP(String id) throws SQLException{
        //koneksi.kone();
        stat=koneksi.kone();
        result=stat.executeQuery("Select user,pass from cloud_mem_ftp where id_mem ='"+id+"'"); 
        if(result.next()){
            info.setUser(result.getString("user"));
            info.setPass(result.getString("pass"));
            info.setHost("localhost");
        }else{
            info.setUser("");
            info.setPass("");
            info.setHost("localhost");
        } 
        return info;
    }
    
    public SetInfoClient get_path(String id) throws SQLException{
        //koneksi.kone();
        stat=koneksi.kone();
        result=stat.executeQuery("Select lokasi from cloud_path where id_mem ='"+id+"'"); 
        if(result.next()){
            info.setPath(result.getString("lokasi"));
        }else{
            info.setPath("-");
        } 
        return info;
    }
    
    public void insertListClient(String id,String nama, String ekstensi,String tipe, String besar, String tanggal, String modify ) {
        stat=koneksi.kone();
        try {
            //stat.executeUpdate("DELETE FROM cloud_file_client WHERE id_mem = '"++"'");
            stat.executeUpdate("INSERT INTO cloud_file_client (id_mem,nama,ekstensi,tipe,besar,tanggal,MODIFY) VALUES "
                   + "('"+id+"','"+nama+"','"+ekstensi+"','"+tipe+"','"+besar+"','"+tanggal+"','"+modify+"') ");
        } catch (SQLException ex) {
            Logger.getLogger(get_data.class.getName()).log(Level.SEVERE, null, ex);
            System.out.print("saat disimpan gagal");
        }
       
    }
    public void insertListServer(String id,String nama, String ekstensi,String tipe, String besar, String tanggal, String modify ) {
        stat=koneksi.kone();
        try {
            //stat.executeUpdate("DELETE FROM cloud_file_client WHERE id_mem = '"++"'");
            stat.executeUpdate("INSERT INTO cloud_file_server (id_mem,nama,ekstensi,tipe,besar,tanggal,MODIFY) VALUES "
                   + "('"+id+"','"+nama+"','"+ekstensi+"','"+tipe+"','"+besar+"','"+tanggal+"','"+modify+"') ");
        } catch (SQLException ex) {
            Logger.getLogger(get_data.class.getName()).log(Level.SEVERE, null, ex);
            System.out.print("saat disimpan gagal");
        }
       
    }
    public void deletedListClient(String id) {
        stat=koneksi.kone();
        try {
            stat.executeUpdate("DELETE FROM cloud_file_client WHERE id_mem = '"+id+"'");
            //stat.executeUpdate("INSERT INTO cloud_file_client (id_mem,nama,tipe,besar,tanggal,MODIFY) VALUES "
                  // + "('1','"+nama+"','"+tipe+"','"+besar+"','"+tanggal+"','"+modify+"') ");
        } catch (SQLException ex) {
            Logger.getLogger(get_data.class.getName()).log(Level.SEVERE, null, ex);
            System.out.print("saat disimpan gagal");
        }
       
    }
    public void deletedListServer(String id) {
        stat=koneksi.kone();
        try {
            stat.executeUpdate("DELETE FROM cloud_file_server WHERE id_mem = '"+id+"'");
            //stat.executeUpdate("INSERT INTO cloud_file_client (id_mem,nama,tipe,besar,tanggal,MODIFY) VALUES "
                  // + "('1','"+nama+"','"+tipe+"','"+besar+"','"+tanggal+"','"+modify+"') ");
        } catch (SQLException ex) {
            Logger.getLogger(get_data.class.getName()).log(Level.SEVERE, null, ex);
            System.out.print("saat disimpan gagal");
        }
       
    }
    
}
