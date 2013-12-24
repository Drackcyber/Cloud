/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package listData;

import code.get_data;
import java.io.File;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rootkit-Ltp
 */
public class dataClient {
    
    //variable global
    int[] nomor=new int [1000000];
    String[] besar =new String [1000000];
    String[] nama =new String [1000000];
    String[] ekstensi =new String [1000000];
    String[] tipe =new String [1000000];
    String[] modify =new String [1000000];
    String[] tanggal =new String [1000000];
    static int spc_count=-1; //untuk mendapatkan data lokasi file pada folder
    
    //variable rekam data
    int total=0;
    int isiFolder=0;
    get_data saveList=new get_data();
    
    //deklarasi list
    List<listClient> list=new ArrayList<listClient>();
    
    public List<listClient> prosesClient (File aFile,String idMember){
        spc_count++;
        
        //Untuk melakukan penggeseran (cuma test saja untuk view di console)
        String spcs="";
        
        //mengganti format tanggal
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss"); //MM/dd/yyyy
        SimpleDateFormat tgl = new SimpleDateFormat("dd-MM-yyyy"); //MM/dd/yyyy
        SimpleDateFormat tgl2 = new SimpleDateFormat("yyyy-MM-dd"); //MM/dd/yyyy
        
        //perulangan untuk mendapatkan file dan folder
        for (int i = 0; i < spc_count; i++)
        spcs += " ";
        total=total+1; // untuk penomoran
        
        //hasil disimpan dalam array global
        nomor[total]=total;
        nama[total]=aFile.getName();
        besar[total]=aFile.length()+""; //butuh dibulatkan ke atas.
        modify[total]=sdf.format(aFile.lastModified());
        tanggal[total]=tgl.format(aFile.lastModified());
        
        
        listClient simpan_data=new listClient();
        simpan_data.setNo(nomor[total]);
        simpan_data.setNama(nama[total]);
        simpan_data.setBesar(besar[total]);
        simpan_data.setTanggal(tanggal[total]);
        simpan_data.setModify(modify[total]);
       
        
      if(aFile.isFile()){
        
        //pencarian ekstensi
        int sub = (aFile.getName().length()-3);
        ekstensi[total]=aFile.getName().substring(sub, aFile.getName().length());
                  
            System.out.print(spcs + "[FILE] " + aFile.getName());
            System.out.print(spcs + "\t" + aFile.length()/1024+ " Kb");
            System.out.println(spcs + "\t"+sdf.format(aFile.lastModified()));
            System.out.println(spcs + "\t"+tgl.format(aFile.lastModified()));
            tipe[total]="File "+isiFolder;
            //Lokasi_Folder=" - "+aFile.getName();
            simpan_data.setTipe(tipe[total]);
            simpan_data.setEkstensi(ekstensi[total]);
            list.add(simpan_data);
            try {
                saveList.insertListClient( idMember,nama[total],ekstensi[total], tipe[total], besar[total], tgl2.format(aFile.lastModified()), modify[total]);
            } catch (Exception ex) {
                Logger.getLogger(dataClient.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("error saat disimpan area file");
            }
        }
      else if (aFile.isDirectory()) {
            isiFolder++;
            System.out.print(spcs + "[DIR] " + aFile.getName());
            System.out.print(spcs + "\t" + aFile.length()/1024+ " Kb");
            System.out.println(spcs + "\t"+ sdf.format(aFile.lastModified()));
            System.out.println(spcs + "\t"+ tgl.format(aFile.lastModified()));
            tipe[total]="Folder ("+isiFolder+")";
            simpan_data.setTipe(tipe[total]);
            list.add(simpan_data);
            try {
                saveList.insertListClient(idMember,nama[total],ekstensi[total], tipe[total], besar[total], tgl2.format(aFile.lastModified()), modify[total]);
            } catch (Exception ex) {
                Logger.getLogger(dataClient.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("error saat disimpan area folder");
            }    

            File[] listOfFiles = aFile.listFiles();
                if(listOfFiles!=null) {
                    for (int i = 0; i < listOfFiles.length; i++){
                        prosesClient(listOfFiles[i],idMember);}
                        isiFolder--;
                } else {
                    System.out.println(spcs + " [ACCESS DENIED]");
                }
     }
        spc_count--;
        return list;
    }    
    
    
    public static void main(String[] args) {
    String nam = "C:/Documents and Settings/Rootkit-Ltp/My Documents/Downloads/Compressed";
    File aFile = new File(nam);
    dataClient b=new dataClient();
   // b.prosesClient(aFile);
  }
    
}
