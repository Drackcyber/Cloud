/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package listData;

/**
 *
 * @author Rootkit-Ltp
 */
public class listClient {
    
    //data yang nanti akan diambil oleh table model
    private int no;
    private String besar;
    private String nama;
    private String ekstensi;

    public String getEkstensi() {
        return ekstensi;
    }

    public void setEkstensi(String ekstensi) {
        this.ekstensi = ekstensi;
    }
    private String tipe;
    private String modify;
    private String tanggal;

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getBesar() {
        return besar;
    }

    public void setBesar(String besar) {
        this.besar = besar;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public String getModify() {
        return modify;
    }

    public void setModify(String modify) {
        this.modify = modify;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
    
    
}
