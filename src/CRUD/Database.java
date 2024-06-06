/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CRUD;
import java.sql.Connection; // mendapatkan koneksi
import java.sql.DriverManager; // menghubunkan database
import java.sql.PreparedStatement; // perintah sql [CRUD]
import java.sql.ResultSet;
import java.util.ArrayList;
//
/**
 *
 * @author ACER
 */
public class Database {
    private String databaseName = "ahmadmaulanamalikibrahim2210010498";
    private String username = "root";
    private String password = "";
    private ResultSet result = null;
    public static Connection connectionDB;

    public Database(){
        try {
            String location = "jdbc:mysql://localhost:3307/" + databaseName;
            Class.forName("com.mysql.jdbc.Driver");
            connectionDB = DriverManager.getConnection(location, username, password);
            System.out.println("connected");

        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    // ================== Table UMKM ======================
    
    public void insertValueUMKM(String kode_umkm, String nama_umkm, String nama_pemilik, String deskripsi, String alamat , String kode_penduduk , String kode_industri){
        ArrayList<String> foreignKeyContainer = new ArrayList<String>();
        try {
            String sqlInsert = "insert into umkm (Kode_umkm, Nama_umkm, Nama_pemilik, Deskripsi, Alamat, Kode_penduduk, Kode_industri) value(?, ?, ?, ?, ?, ?, ?)";
            String sqlRead = "Select Kode_industri from jenis_industri";
            PreparedStatement perintahInsert = connectionDB.prepareStatement(sqlInsert);
            PreparedStatement perintahRead = connectionDB.prepareStatement(sqlRead);
            result = perintahRead.executeQuery();
            while(result.next()){
                String foreignKey = result.getString("Kode_industri");
                foreignKeyContainer.add(result.getString("Kode_industri"));                

            }
            if(foreignKeyContainer.equals(kode_industri)){
                perintahInsert.setString(1, kode_umkm);
                perintahInsert.setString(2, nama_umkm);
                perintahInsert.setString(3, nama_pemilik);
                perintahInsert.setString(4, deskripsi);
                perintahInsert.setString(5, alamat);
                perintahInsert.setString(6, kode_penduduk);
                perintahInsert.setString(7, kode_industri);

                perintahInsert.executeUpdate();
                System.out.println("added");
            }
            else{
                System.out.println("Value are not available in foreign Key");
                System.out.println("Avaiable value:");
                int tempNumber;
                tempNumber = 0;
                for(String value: foreignKeyContainer){
                    System.out.print(tempNumber + 1 + ". ");
                    System.out.println(value);
                    tempNumber += 1;
                }
            }
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        foreignKeyContainer.clear();
    }

    
    public void updateValueUMKM(String kode_umkm, String nama_umkm, String nama_pemilik, String deskripsi, String alamat , String kode_penduduk , String kode_industri){
        try {
            String sql = "update umkm set Nama_umkm = ?, Nama_pemilik = ?, Deskripsi = ?, Alamat = , Kode_penduduk = ?, Kode_industri = ? where Kode_umkm = ?";
            PreparedStatement perintah = connectionDB.prepareStatement(sql);
            perintah.setString(1, nama_umkm);
            perintah.setString(2, nama_pemilik);
            perintah.setString(3, deskripsi);
            perintah.setString(4, alamat);
            perintah.setString(5, kode_penduduk);
            perintah.setString(6, kode_industri);
            perintah.setString(7, kode_umkm);
            
            perintah.executeUpdate();
            System.out.println("updated");
            
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void deleteValueUMKM(String kode_umkm){
        try {
            String sql = "delete from umkm where kode_umkm = ?";
            PreparedStatement perintah = connectionDB.prepareStatement(sql);
            perintah.setString(1, kode_umkm);
            
            perintah.executeUpdate();
            System.out.println("deleted");
            
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // ================== Table Jenis_Industri ======================
    
    
    public void insertValueJenisIndustri(String kode_industri, String nama_industri){
        try {
            String sql = "insert into jenis_industri (Kode_industri, Nama_industri) value(?, ?)";
            PreparedStatement perintah = connectionDB.prepareStatement(sql);
            perintah.setString(1, kode_industri);
            perintah.setString(2, nama_industri);
            
            perintah.executeUpdate();
            System.out.println("added");
            
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void updateValueJenisIndustri(String kode_industri, String nama_industri){
        try {
            String sql = "update jenis_industri set Nama_industri = ? where Kode_industri, = ?";
            PreparedStatement perintah = connectionDB.prepareStatement(sql);
            perintah.setString(1, nama_industri);
            perintah.setString(2, kode_industri);
            
            perintah.executeUpdate();
            System.out.println("updated");
            
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void deleteValueJenisIndustri(String kode_industri){
        try {
            String sql = "delete from jenis_industri where kode_industri = ?";
            PreparedStatement perintah = connectionDB.prepareStatement(sql);
            perintah.setString(1, kode_industri);
            
            perintah.executeUpdate();
            System.out.println("deleted");
            
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    // ================== Table Jual_Produk ======================

    public void insertValueJualProduk(String kode, String kode_umkm, String nama_produk){
        ArrayList<String> foreignKeyContainer = new ArrayList<String>();
        try {
            String sqlInsert = "insert into umkm (Kode, Kode_umkm, Nama_produk) value(?, ?, ?)";
            String sqlRead = "Select Kode_umkm from umkm";
            PreparedStatement perintahInsert = connectionDB.prepareStatement(sqlInsert);
            PreparedStatement perintahRead = connectionDB.prepareStatement(sqlRead);
            result = perintahRead.executeQuery();
            while(result.next()){
                String foreignKey = result.getString("Kode_umkm");
                foreignKeyContainer.add(result.getString("Kode_umkm"));                

            }
            if(foreignKeyContainer.equals(kode_umkm)){
                perintahInsert.setString(1, kode);
                perintahInsert.setString(2, kode_umkm);
                perintahInsert.setString(3, nama_produk);

                perintahInsert.executeUpdate();
                System.out.println("added");
            }
            else{
                System.out.println("Value are not available in foreign Key");
                System.out.println("Avaiable value:");
                int tempNumber;
                tempNumber = 0;
                for(String value: foreignKeyContainer){
                    System.out.print(tempNumber + 1 + ". ");
                    System.out.println(value);
                    tempNumber += 1;
                }
            }
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        foreignKeyContainer.clear();
    }

    
    public void updateValueJualProduk(String kode, String kode_umkm, String nama_produk){
        try {
            String sql = "update jual_produk set Kode_umkm = ?, Nama_produk = ? where Kode = ?";
            PreparedStatement perintah = connectionDB.prepareStatement(sql);
            perintah.setString(1, kode_umkm);
            perintah.setString(2, nama_produk);
            perintah.setString(3, kode);
            
            perintah.executeUpdate();
            System.out.println("updated");
            
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void deleteValueJualProduk(String kode){
        try {
            String sql = "delete from jual_produk where Kode = ?";
            PreparedStatement perintah = connectionDB.prepareStatement(sql);
            perintah.setString(1, kode);
            
            perintah.executeUpdate();
            System.out.println("deleted");
            
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    // ================== Table Konsumen======================

    public void insertKonsumen(String kode, String nama_Konsumen, String alamat, String no_Telepon){
        try {
            String sql = "insert into Uji (Kode, Nama_konsumen, Alamat, No_Telepon) value(?, ?, ?, ?)";
            PreparedStatement perintah = connectionDB.prepareStatement(sql);
            perintah.setString(1, kode);
            perintah.setString(2, nama_Konsumen);
            perintah.setString(3, alamat);
            perintah.setString(4, no_Telepon);

            
            

//            perintah.setString(3, banyak_unit.toString());
            
            perintah.executeUpdate();
            System.out.println("added");
            
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    
    public void updateValueKonsumen(String kode, String nama_Konsumen, String alamat, String no_Telepon){
        try {
            String sql = "update transaksi set Nama_konsumen = ?, Alamat = ?, No_teelpon = ? where kode = ?";
            PreparedStatement perintah = connectionDB.prepareStatement(sql);
            perintah.setString(1, nama_Konsumen);
            perintah.setString(2, alamat);
            perintah.setString(3, no_Telepon);
            perintah.setString(4, kode);
            
            perintah.executeUpdate();
            System.out.println("updated");
            
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void deleteValueKonsumen(String kode_konsumen){
        try {
            String sql = "delete from konsumen where Kode = ?";
            PreparedStatement perintah = connectionDB.prepareStatement(sql);
            perintah.setString(1, kode_konsumen);
            
            perintah.executeUpdate();
            System.out.println("deleted");
            
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }    
}

