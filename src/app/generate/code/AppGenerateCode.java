package app.generate.code;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

/**
 *
 * @author Anom Sejogati
 * update 11 Desember 2017
 * versi 1.0
 */
public class AppGenerateCode {
    
    Connection _Cnn;
    KoneksiDB getCnn = new KoneksiDB();
    SimpleDateFormat tglnow = new SimpleDateFormat("yyyy-MM-dd");
    String mkd_ruang, vkd_kategori, vkd_obat, no_trans;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
    public void createKode1(){
        try{
            _Cnn = getCnn.getConnection();
            String id = "select max(right(kd_ruang,2)) as kd from mst_ruang";
            Statement stat = _Cnn.createStatement();
            ResultSet res = stat.executeQuery(id);
            if(res.first() == false){
                mkd_ruang = "R.01";
            }else{
                res.last();
                int noID = res.getInt(1) + 1;
                String no = String.valueOf(noID);
                if(noID < 100){
                    mkd_ruang = "R.0" + no;
                }else{
                    mkd_ruang = "R." + no;
                }
            }
        }catch(SQLException ex){
            System.out.println("Error Method createKode1 : " + ex);
        }
    }
    
    public void createKode2(){
        try{
            _Cnn = getCnn.getConnection();
            String id = "select max(right(kd_kategori,2)) as no from tbkategoriitem";
            Statement stat = _Cnn.createStatement();
            ResultSet res = stat.executeQuery(id);
            if(res.first() == false){
                vkd_kategori = "01";
            }else{
                res.last();
                int noID = res.getInt(1) + 1;
                String no = String.valueOf(noID);
                if(noID < 10){
                    vkd_kategori = "0" + no;
                }else if(noID < 100){
                    vkd_kategori = no;
                }
            }
        }catch(SQLException ex){
            System.out.println("Error Method autoID : " + ex);
        }
    }
    
    String[] KeyKategori;
    public void createKode3(){
        try{
            _Cnn = getCnn.getConnection();
            String id = "select max(right(kd_item,5)) as kd from tbitem where "
                    + " kd_kategori='"+vkd_kategori+"' ";
            Statement stat = _Cnn.createStatement();
            ResultSet res = stat.executeQuery(id);
                if(res.first() == false){
                    vkd_obat = vkd_kategori+".00001";
                } else{
                    res.last();
                    int noID = res.getInt(1) + 1;
                    String no = String.valueOf(noID);
                    if(noID < 10){
                        vkd_obat = vkd_kategori+"."+"0000" + no;
                    }else if(noID < 100){
                        vkd_obat = vkd_kategori+"."+"000" + no;
                    }else if(noID < 1000){
                        vkd_obat = vkd_kategori+"."+"00" + no;
                    }else if(noID < 10000){
                        vkd_obat = vkd_kategori+"."+"0" + no;
                    }
                }
        }catch(SQLException ex){
            System.out.println("Error Method createKode() : " + ex);
        }
    }
    
    public void createNoTransaksi() {
        try{
            _Cnn = getCnn.getConnection();
            String id = "select max(right(no_trans,3)) as nomor from tbtransaksi "
                    + " where left(no_trans,8)='"+tglnow+"' ";
            Statement stat = _Cnn.createStatement();
            ResultSet res = stat.executeQuery(id);
                if(res.first() == false){
                    no_trans = tglnow+"001";
                }else{
                    res.last();
                    int noID = res.getInt(1) + 1;
                    String no = String.valueOf(noID);
                    if(noID < 10){
                        no_trans = tglnow+"00"+ no;
                    } else if(noID < 100){
                        no_trans = tglnow+"0" + no;
                    }else if(noID < 1000){
                        no_trans = tglnow + no;
                    }
                }
        } catch(SQLException ex){
            System.out.println("Error Method createNoTransaksi : " + ex);
        }
    }
    
}
