/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package listData;

import code.get_data;
import code.testConnectionFtp;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

/**
 *
 * @author Rootkit-Ltp
 */
public class dataServer {
    //variable global
    int[] nomor=new int [1000000];
    String[] besar =new String [1000000];
    String[] nama =new String [1000000];
    String[] ekstensi =new String [1000000];
    String[] tipe =new String [1000000];
    String[] modify =new String [1000000];
    String[] tanggal =new String [1000000];
    static int spc_count=0; //untuk mendapatkan data lokasi file pada folder
    String[] area=new String[1000000]; //memasukkan jalur area pada server
    
    //variable rekam data
    int total=0;
    int rekam=0;
    //int isiFolder=0;
    int lokasiFile=0;
    get_data saveList=new get_data();
    testConnectionFtp kon=new testConnectionFtp();
    
    //deklarasi list
    List<listClient> list=new ArrayList<listClient>();
    
    public List<listClient> prosesServer (String Lokasi,String idMember, String User, String Pass, String Host) throws IOException{
        //System.out.print(User);
        //System.out.print(Pass);
        //area[spc_count]=area[spc_count]+"../";
        spc_count++;
        //lokasiFile++;
        area[spc_count]=Lokasi;
        System.out.println("Alamat yang dibuka === "+Lokasi);
         FTPClient ftpClient = new FTPClient();
            ftpClient.connect("localhost", 21);
            ftpClient.login(User, Pass);
            
            FTPFile[] files = ftpClient.listFiles(Lokasi);
            
        
        
        
        try {
            kon.connection();
            kon.connection1();
        } catch (IOException ex) {
            Logger.getLogger(dataServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Untuk melakukan penggeseran (cuma test saja untuk view di console)
        String spcs="";
        
        //mengganti format tanggal
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss"); //MM/dd/yyyy
        SimpleDateFormat tgl = new SimpleDateFormat("dd-MM-yyyy"); //MM/dd/yyyy
        SimpleDateFormat tgl2 = new SimpleDateFormat("yyyy-MM-dd"); //MM/dd/yyyy
        
        
        for(FTPFile file : files){
                System.out.print(file.getName());
                rekam++;
                //System.out.println(rekam);    
                nomor[rekam]=rekam;
                nama[rekam]=file.getName();
                besar[rekam]=file.getSize()+""; //butuh dibulatkan ke atas.
                modify[rekam]=sdf.format(file.getTimestamp().getTime());
                tanggal[rekam]=tgl.format(file.getTimestamp().getTime());
                
                
                listClient simpan_data=new listClient();
                //ambil_dat simpan_data=new ambil_dat();
                simpan_data.setNo(nomor[rekam]);
                simpan_data.setNama(nama[rekam]);
                simpan_data.setBesar(besar[rekam]);
                simpan_data.setTanggal(tanggal[rekam]);
                simpan_data.setModify(modify[rekam]);
                System.out.println("-----Lokasi--------"+lokasiFile);
                if (file.isFile()) { 
                    tipe[rekam]="File "+(lokasiFile)+"";
                    System.out.println();
                    System.out.println();
                    System.out.println("Tanggal file server "+file.getTimestamp().getTime().getHours());
                    System.out.println();
                    System.out.println();
                    
                            //pencarian ekstensi
                    int sub = (file.getName().length()-3);
                    ekstensi[rekam]=file.getName().substring(sub, file.getName().length());
                    simpan_data.setTipe(tipe[rekam]);
                    simpan_data.setEkstensi(ekstensi[rekam]);
                    list.add(simpan_data);
                   // System.out.println("\t"+"File");
                    try {
                        saveList.insertListServer(idMember,nama[rekam],ekstensi[rekam], tipe[rekam], besar[rekam], tgl2.format(file.getTimestamp().getTime()), modify[rekam]);
                    } catch (Exception ex) {
                        Logger.getLogger(dataClient.class.getName()).log(Level.SEVERE, null, ex);
                        System.out.println("error saat disimpan area file");
                    }
                    
                }
                else if(file.isDirectory()) {
                    lokasiFile++;
                    tipe[rekam]="Folder ("+lokasiFile+")" ;
                    simpan_data.setTipe(tipe[rekam]);
                    list.add(simpan_data);
                    System.out.println("\t"+"Folder");
                    area[spc_count]=area[spc_count]+file.getName()+"/";
                    System.out.println(area[spc_count]);                    
                    
                    try {
                        saveList.insertListServer(idMember,nama[rekam],ekstensi[rekam], tipe[rekam], besar[rekam], tgl2.format(file.getTimestamp().getTime()), modify[rekam]);
                    } catch (Exception ex) {
                        Logger.getLogger(dataClient.class.getName()).log(Level.SEVERE, null, ex);
                        System.out.println("error saat disimpan area file");
                    }
                    
                    FTPFile[] listFile = ftpClient.listFiles(area[spc_count]);                    
                    if(listFile!=null){
                            prosesServer(area[spc_count],idMember,User,Pass,Host);
                            lokasiFile--;
                    }                    
                    area[spc_count]=area[spc_count]+"../";
                    System.out.println("-----Lokasi dikurangi 1--------" + spc_count);
                    
                }
            } 
           
            //FTPFile[] files=kon.connection1().listFiles(Lokasi);
        /*
        
        //perulangan untuk mendapatkan file dan folder
        for (FTPFile file : files){
        spc_count++;
        area[spc_count]=Lokasi;
        spcs += " ";
        total=total+1; // untuk penomoran
        
        //hasil disimpan dalam array global
        nomor[total]=total;
        System.out.println(file.getName());
        nama[total]=file.getName();
        besar[total]=file.getSize()/1024+""; //butuh dibulatkan ke atas.
        modify[total]=sdf.format(file.getTimestamp().getTime());
        tanggal[total]=tgl.format(file.getTimestamp().getTime());
        
        listClient simpan_data=new listClient();
        simpan_data.setNo(nomor[total]);
        simpan_data.setNama(nama[total]);
        
        simpan_data.setBesar(besar[total]);
        simpan_data.setTanggal(tanggal[total]);
        simpan_data.setModify(modify[total]);
        
      if(file.isFile()){

        //pencarian ekstensi
        int sub = (file.getName().length()-3);
        ekstensi[total]=file.getName().substring(sub, file.getName().length());
          
            System.out.print(spcs + "[FILE] " + file.getName());
            System.out.print(spcs + "\t" + file.getSize()/1024+ " Kb");
            System.out.println(spcs + "\t"+sdf.format(file.getTimestamp().getTime()));
            System.out.println(spcs + "\t"+tgl.format(file.getTimestamp().getTime()));
            tipe[total]="File "+isiFolder;
            //Lokasi_Folder=" - "+file.getName();
            simpan_data.setTipe(tipe[total]);
            simpan_data.setEkstensi(ekstensi[total]);
            list.add(simpan_data);
            try {
                saveList.insertListClient(idMember,nama[total],ekstensi[total], tipe[total], besar[total], tgl2.format(file.getTimestamp().getTime()), modify[total]);
            } catch (Exception ex) {
                Logger.getLogger(dataClient.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("error saat disimpan area file");
            }
        }
      else if (file.isDirectory()) {
            isiFolder++;
            
            System.out.print(spcs + "[DIR] " + file.getName());
            System.out.print(spcs + "\t" + file.getSize()/1024+ " Kb");
            System.out.println(spcs + "\t"+ sdf.format(file.getTimestamp().getTime()));
            System.out.println(spcs + "\t"+ tgl.format(file.getTimestamp().getTime()));
            tipe[total]="Folder ("+isiFolder+")";
            area[spc_count]=area[spc_count]+file.getName()+"/";
            simpan_data.setTipe(tipe[total]);
            
            System.out.println("------------- Lokasi = "+area[spc_count]+" dari nomot=r ke "+spc_count);
            list.add(simpan_data);
            
            try {
                saveList.insertListClient(idMember,nama[total],ekstensi[total], tipe[total], besar[total], tgl2.format(file.getTimestamp().getTime()), modify[total]);
            } catch (Exception ex) {
                Logger.getLogger(dataClient.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("error saat disimpan area folder");
            }    
            try{
                FTPFile[] listOfFiles = ftpClient.listFiles(area[spc_count]);            
                if(listOfFiles!=null) {
                    for (int i = 0; i < listOfFiles.length; i++){
                        prosesServer(area[spc_count],idMember,User,Pass,Host);}
                        isiFolder--;
                        area[spc_count]=area[spc_count]+"../"; 
                        spc_count--;
                } else {
                    System.out.println(spcs + " [ACCESS DENIED]");
                }
            }catch(Exception e){
                System.out.println("Terjdi keasalahan di bagian bawah");
            }
            
     }
      
        }*/
        
        return list;
    }  
    
    public static void main (String []args) throws IOException{
        testConnectionFtp kon = new testConnectionFtp();
        kon.getInfoFTP("admin", "admin");
        dataServer server1=new dataServer();
        //.prosesServer("","1");
    }
    
}
