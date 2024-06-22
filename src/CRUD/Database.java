/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CRUD;

import java.sql.Connection; // mendapatkan koneksi
import java.sql.DriverManager; // menghubunkan database
import java.sql.PreparedStatement; // perintah sql [CRUD]
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import Class.Konsumen;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;


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
    private Konsumen date;

    public Database() {
        date = new Konsumen();
        try {
            String location = "jdbc:mysql://localhost:3307/" + databaseName;
            Class.forName("com.mysql.jdbc.Driver");
            connectionDB = DriverManager.getConnection(location, username, password);
            System.out.println("connected");

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // ================== Table UMKM ======================
    public String insertValueUMKM(String kode_umkm, String nama_umkm, String nama_pemilik, String deskripsi, String alamat, String kode_penduduk, String kode_industri) {
        ArrayList<String> foreignKeyContainer = new ArrayList<>();
        String outputState;
        try {
            String sqlInsert = "insert into umkm (Kode_umkm, Nama_umkm, Nama_pemilik, Deskripsi, Alamat, Kode_penduduk, Kode_industri) value(?, ?, ?, ?, ?, ?, ?)";
            String sqlSelect = "Select Kode_industri from jenis_industri";
            PreparedStatement perintah = connectionDB.prepareStatement(sqlSelect);
            result = perintah.executeQuery();
            
            while (result.next()) {
                foreignKeyContainer.add(result.getString("Kode_industri"));

            }
            
            perintah = connectionDB.prepareStatement(sqlInsert);
            if (foreignKeyContainer.contains(kode_industri)) {
                perintah.setString(1, kode_umkm);
                perintah.setString(2, nama_umkm);
                perintah.setString(3, nama_pemilik);
                perintah.setString(4, deskripsi);
                perintah.setString(5, alamat);
                perintah.setString(6, kode_penduduk);
                perintah.setString(7, kode_industri);

                perintah.executeUpdate();
                outputState = "added";
            } else {
                outputState = "Value are not available in foreign Key";
//                System.out.println("Avaiable value:");
//                int tempNumber;
//                tempNumber = 0;
//                for (String value : foreignKeyContainer) {
//                    System.out.print(tempNumber + 1 + ". ");
//                    System.out.println(value);
//                    tempNumber += 1;
                }
        } catch (SQLException e) {
            outputState = e.getMessage();
        }
        foreignKeyContainer.clear();
        return outputState;
    }

    public String updateValueUMKM(String kode_umkm, String nama_umkm, String nama_pemilik, String deskripsi, String alamat, String kode_penduduk, String kode_industri) {
        ArrayList<String> foreignKeyContainer1 = new ArrayList<>();
        ArrayList<String> primaryKeyContainer1 = new ArrayList<>();
        String outputState;
         
        try {
            String sqlUpdate = "update umkm set Nama_umkm = ?, Nama_pemilik = ?, Deskripsi = ?, Alamat = ?, Kode_penduduk = ?, Kode_industri = ? WHERE Kode_umkm = ?";
            String sqlSelect = "Select Kode_industri from jenis_industri";

            PreparedStatement perintah = connectionDB.prepareStatement(sqlSelect);
            result = perintah.executeQuery();
            while (result.next()) {
                foreignKeyContainer1.add(result.getString("Kode_industri"));
            }
            
            sqlSelect = "Select Kode_umkm from umkm";
            perintah = connectionDB.prepareStatement(sqlSelect);
            result = perintah.executeQuery();
            
            while (result.next()) {
                primaryKeyContainer1.add(result.getString("Kode_umkm"));
            }

            perintah = connectionDB.prepareStatement(sqlUpdate);
            if (foreignKeyContainer1.contains(kode_industri) && primaryKeyContainer1.contains(kode_umkm)) {
                perintah.setString(1, nama_umkm);
                perintah.setString(2, nama_pemilik);
                perintah.setString(3, deskripsi);
                perintah.setString(4, alamat);
                perintah.setString(5, kode_penduduk);
                perintah.setString(6, kode_industri);
                perintah.setString(7, kode_umkm);

                perintah.executeUpdate();
                outputState ="updated";
            } else {
                outputState = "Value are not available in foreign Key or primary Key";
                }
        } catch (SQLException e) {
            outputState = e.getMessage();
        }
        
        return outputState;
    }

    public String deleteValueUMKM(String kode_umkm) {
        ArrayList<String> primaryKeyContainer1 = new ArrayList<>();
        String outputState;
         
        try {
            String sqlDelete = "delete from umkm where Kode_umkm = ?";
            String sqlSelect = "Select Kode_umkm from umkm";
            PreparedStatement perintah = connectionDB.prepareStatement(sqlSelect);
            result = perintah.executeQuery();
            
            while (result.next()) {
                primaryKeyContainer1.add(result.getString("Kode_umkm"));
            }

            perintah = connectionDB.prepareStatement(sqlDelete);
            if (primaryKeyContainer1.contains(kode_umkm)) {
            perintah.setString(1, kode_umkm);

            perintah.executeUpdate();
            outputState ="deleted";
            } else {
                outputState = "Value are not available in foreign Key or primary Key";
            }
        } catch (SQLException e) {
            outputState = e.getMessage();
        }
        return outputState;
    }

    // ================== Table Jenis_Industri ======================
    public String insertValueJenisIndustri(String kode_industri, String nama_industri) {
        String outputState;
        try {
            String sqlInsert = "insert into jenis_industri (Kode_industri, Nama_industri) value(?, ?)";
            PreparedStatement perintah = connectionDB.prepareStatement(sqlInsert);
            perintah.setString(1, kode_industri);
            perintah.setString(2, nama_industri);

            perintah.executeUpdate();
            outputState = "added";

        } catch (SQLException e) {
            outputState = e.getMessage();
        }
        return outputState;
    }

    public String updateValueJenisIndustri(String kode_industri, String nama_industri) {
         ArrayList<String> primaryKeyContainer1 = new ArrayList<>();
         String outputState;
         
        try {
            String sqlUpdate = "update jenis_industri set Nama_industri = ? where Kode_industri = ?";
            String sqlSelect = "Select Kode_industri from jenis_industri";

            PreparedStatement perintah = connectionDB.prepareStatement(sqlSelect);
            result = perintah.executeQuery();
            while (result.next()) {
                primaryKeyContainer1.add(result.getString("Kode_industri"));
            }
            
            perintah = connectionDB.prepareStatement(sqlUpdate);
            if (primaryKeyContainer1.contains(kode_industri)) {
                perintah.setString(1, nama_industri);
                perintah.setString(2, kode_industri);

                perintah.executeUpdate();
                outputState = "updated";
            } else {
                outputState = "Value are not available in foreign Key or primary Key";
                }
        } catch (SQLException e) {
            outputState = e.getMessage();
        }
        return outputState;
    }

    public String deleteValueJenisIndustri(String kode_industri) {
        ArrayList<String> primaryKeyContainer1 = new ArrayList<>();
        String outputState;
         
        try {
            String sqlDelete = "delete from jenis_industri where Kode_industri = ?";
            String sqlSelect = "Select Kode_industri from jenis_industri";
            PreparedStatement perintah = connectionDB.prepareStatement(sqlSelect);
            result = perintah.executeQuery();
            
            while (result.next()) {
                primaryKeyContainer1.add(result.getString("Kode_industri"));
            }

            perintah = connectionDB.prepareStatement(sqlDelete);
            if (primaryKeyContainer1.contains(kode_industri)) {
            perintah.setString(1, kode_industri);

            perintah.executeUpdate();
            outputState ="deleted";
            } else {
                outputState = "Value are not available in foreign Key or primary Key";
            }
        } catch (SQLException e) {
            outputState = e.getMessage();
        }
        
        return outputState;
    }

    // ================== Table Jual_Produk ======================
    public String insertValueJualProduk(String kode, String kode_umkm, String nama_produk) {
        ArrayList<String> foreignKeyContainer1 = new ArrayList<>();
        String outputState;
        try {
            String sqlInsert = "insert into jual_produk (Kode, Kode_umkm, Nama_produk) value(?, ?, ?)";
            String sqlSelect = "Select Kode_umkm from umkm";
            PreparedStatement perintah = connectionDB.prepareStatement(sqlSelect);
            result = perintah.executeQuery();
            
            while (result.next()) {
                foreignKeyContainer1.add(result.getString("Kode_umkm"));
            }

            sqlSelect = "Select Kode from jual_produk";
            perintah = connectionDB.prepareStatement(sqlSelect);
            result = perintah.executeQuery();
            

            perintah = connectionDB.prepareStatement(sqlInsert);
            if (foreignKeyContainer1.contains(kode_umkm)) {
                perintah.setString(1, kode);
                perintah.setString(2, kode_umkm);
                perintah.setString(3, nama_produk);

                perintah.executeUpdate();
                outputState = "added";
            } else {
                outputState = "Value are not available in foreign Key or primary Key";
            }
        } catch (SQLException e) {
            outputState = e.getMessage();
        }
        foreignKeyContainer1.clear();
        return outputState;
    }

    public String updateValueJualProduk(String kode, String kode_umkm, String nama_produk) {
        ArrayList<String> foreignKeyContainer1 = new ArrayList<>();
        ArrayList<String> primaryKeyContainer1 = new ArrayList<>();
        String outputState;
        
        try {
            String sqlUpdate = "update jual_produk set Kode_umkm = ?, Nama_produk = ? where Kode = ?";
            String sqlSelect = "Select Kode_umkm from umkm";

            PreparedStatement perintah = connectionDB.prepareStatement(sqlSelect);
            result = perintah.executeQuery();
            while (result.next()) {
                foreignKeyContainer1.add(result.getString("Kode_umkm"));
            }
            
            sqlSelect = "Select Kode from jual_produk";
            perintah = connectionDB.prepareStatement(sqlSelect);
            result = perintah.executeQuery();
            while (result.next()) {
                primaryKeyContainer1.add(result.getString("Kode"));
            }

            perintah = connectionDB.prepareStatement(sqlUpdate);
            if (foreignKeyContainer1.contains(kode_umkm) && primaryKeyContainer1.contains(kode)) {
                perintah.setString(1, kode_umkm);
                perintah.setString(2, nama_produk);
                perintah.setString(3, kode);

                perintah.executeUpdate();
                outputState = "updated";
            } else {
                outputState = "Value are not available in foreign Key or primary Key";
                }
        } catch (SQLException e) {
            outputState = e.getMessage();
        }
        return outputState;
    }


    public String deleteValueJualProduk(String kode) {
        ArrayList<String> primaryKeyContainer1 = new ArrayList<>();
        String outputState;
         
        try {
            String sqlDelete = "delete from jual_produk where Kode = ?";
            String sqlSelect = "Select Kode from jual_produk";
            PreparedStatement perintah = connectionDB.prepareStatement(sqlSelect);
            result = perintah.executeQuery();
            
            while (result.next()) {
                primaryKeyContainer1.add(result.getString("Kode"));
            }

            perintah = connectionDB.prepareStatement(sqlDelete);
            if (primaryKeyContainer1.contains(kode)) {
            perintah.setString(1, kode);

            perintah.executeUpdate();
            outputState ="deleted";
            } else {
                outputState = "Value are not available in foreign Key or primary Key";
            }
        } catch (SQLException e) {
            outputState = e.getMessage();
        }
        return outputState;        
    }

    // ================== Table Konsumen======================
    public String insertValueKonsumen(String kode, String nama_Konsumen, String alamat, String no_Telepon) {
        String outputState;
        try {
            String sqlInsert = "insert into konsumen (Kode, Nama_konsumen, Alamat, No_Telepon) value(?, ?, ?, ?)";
            PreparedStatement perintah = connectionDB.prepareStatement(sqlInsert);
            perintah.setString(1, kode);
            perintah.setString(2, nama_Konsumen);
            perintah.setString(3, alamat);
            perintah.setString(4, no_Telepon);

//            perintah.setString(3, banyak_unit.toString());
            perintah.executeUpdate();
            outputState = "added";

        } catch (SQLException e) {
            outputState = e.getMessage();
        }
        return outputState;
    }

    public String updateValueKonsumen(String kode, String nama_Konsumen, String alamat, String no_Telepon) {
         ArrayList<String> primaryKeyContainer1 = new ArrayList<>();
         String outputState;
        try {
            String sqlUpdate = "update konsumen set Nama_konsumen = ?, Alamat = ?, No_telepon = ? where Kode = ?";
            String sqlSelect = "Select Kode from konsumen";

            PreparedStatement perintah = connectionDB.prepareStatement(sqlSelect);
            result = perintah.executeQuery();
            while (result.next()) {
                primaryKeyContainer1.add(result.getString("Kode"));
            }
            
            perintah = connectionDB.prepareStatement(sqlUpdate);
            if (primaryKeyContainer1.contains(kode)) {
                perintah.setString(1, nama_Konsumen);
                perintah.setString(2, alamat);
                perintah.setString(3, no_Telepon);
                perintah.setString(4, kode);

                perintah.executeUpdate();
                outputState = "updated";
            } else {
                outputState = "Value are not available in foreign Key or primary Key";
                }
        } catch (SQLException e) {
            outputState = e.getMessage();
        }
        return outputState;
    }

    public String deleteValueKonsumen(String kode) {
        ArrayList<String> primaryKeyContainer1 = new ArrayList<>();
        String outputState;
         
        try {
            String sqlDelete = "delete from konsumen where Kode = ?";
            String sqlSelect = "Select Kode from konsumen";
            PreparedStatement perintah = connectionDB.prepareStatement(sqlSelect);
            result = perintah.executeQuery();
            
            while (result.next()) {
                primaryKeyContainer1.add(result.getString("Kode"));
            }

            perintah = connectionDB.prepareStatement(sqlDelete);
            if (primaryKeyContainer1.contains(kode)) {
            perintah.setString(1, kode);

            perintah.executeUpdate();
            outputState ="deleted";
            } else {
                outputState = "Value are not available in foreign Key or primary Key";
            }
        } catch (SQLException e) {
            outputState = e.getMessage();
        }
        return outputState;        
    }
    
    // ================== Table Transaksi ======================
    public String insertValueTransaksi(String kode_transaksi, String kode_produksi, int banyak_unit, String kode_konsumen) {
        ArrayList<String> foreignKeyContainer1 = new ArrayList<>();
        ArrayList<String> foreignKeyContainer2 = new ArrayList<>();
        String outputState;

        try {
            String sqlInsert = "insert into transaksi (Kode_transaksi, Kode_produksi, Tanggal_transaksi, Banyak_unit, Kode_konsumen) value(?, ?, ?, ?, ?)";
            String sqlSelect = "Select Kode from jual_produk";

            PreparedStatement perintah = connectionDB.prepareStatement(sqlSelect);
            result = perintah.executeQuery();
            
            while (result.next()) {
                foreignKeyContainer1.add(result.getString("Kode"));
            }
            
            sqlSelect = "Select Kode from konsumen";
            perintah = connectionDB.prepareStatement(sqlSelect);
            result = perintah.executeQuery();
            while (result.next()) {
                foreignKeyContainer2.add(result.getString("Kode"));
            }
            
            perintah = connectionDB.prepareStatement(sqlInsert);
            if (foreignKeyContainer1.contains(kode_produksi) && foreignKeyContainer2.contains(kode_konsumen)) {
                perintah.setString(1, kode_transaksi);
                perintah.setString(2, kode_produksi);
                perintah.setString(3, date.getDate());
                perintah.setInt(4, banyak_unit);
                perintah.setString(5, kode_konsumen);

                perintah.executeUpdate();
                outputState = "added";
            } else {
                outputState = "Value are not available in foreign Key";
            }
        } catch (SQLException e) {
            outputState = e.getMessage();
        }
        foreignKeyContainer1.clear();
        foreignKeyContainer2.clear();
        return outputState;
    }

    public String updateValueTransaksi(String kode_transaksi, String kode_produksi,int banyak_unit, String kode_konsumen) {
        ArrayList<String> foreignKeyContainer1 = new ArrayList<>();
        ArrayList<String> foreignKeyContainer2 = new ArrayList<>();
        ArrayList<String> primaryKeyContainer1 = new ArrayList<>();
        String outputState;
        
        try {
            String sqlUpdate = "update transaksi set Kode_produksi = ?, Banyak_unit = ?, Kode_konsumen = ? WHERE Kode_transaksi = ?";
            String sqlSelect = "Select Kode from jual_produk";

            PreparedStatement perintah = connectionDB.prepareStatement(sqlSelect);
            result = perintah.executeQuery();
            
            while (result.next()) {
                foreignKeyContainer1.add(result.getString("Kode"));
            }
            
            sqlSelect = "Select Kode from konsumen";
            perintah = connectionDB.prepareStatement(sqlSelect);
            result = perintah.executeQuery();
            
            while (result.next()) {
                foreignKeyContainer2.add(result.getString("Kode"));
            }

            sqlSelect = "Select Kode_transaksi from transaksi";
            perintah = connectionDB.prepareStatement(sqlSelect);
            result = perintah.executeQuery();
            
            while (result.next()) {
                primaryKeyContainer1.add(result.getString("Kode_transaksi"));
            }

            perintah = connectionDB.prepareStatement(sqlUpdate);
            
            if (foreignKeyContainer1.contains(kode_produksi) && foreignKeyContainer2.contains(kode_konsumen) && primaryKeyContainer1.contains(kode_transaksi)) {
                perintah.setString(1, kode_produksi);
                perintah.setInt(2, banyak_unit);
                perintah.setString(3, kode_konsumen);
                perintah.setString(4, kode_transaksi);

                perintah.executeUpdate();
                outputState = "updated";
            } else {
                outputState = "Value are not available in foreign Key or primary Key";
                }
        } catch (SQLException e) {
            outputState = e.getMessage();
        }
        return outputState;
    }

    public String deleteValueTransaksi(String kode_transaksi) {
        ArrayList<String> primaryKeyContainer1 = new ArrayList<>();
        String outputState;
         
        try {
            String sqlDelete = "delete from transaksi where Kode_transaksi = ?";
            String sqlSelect = "Select Kode_transaksi from transaksi";
            PreparedStatement perintah = connectionDB.prepareStatement(sqlSelect);
            result = perintah.executeQuery();
            
            while (result.next()) {
                primaryKeyContainer1.add(result.getString("Kode_transaksi"));
            }

            perintah = connectionDB.prepareStatement(sqlDelete);
            if (primaryKeyContainer1.contains(kode_transaksi)) {
            perintah.setString(1, kode_transaksi);

            perintah.executeUpdate();
            outputState ="deleted";
            } else {
                outputState = "Value are not available in foreign Key or primary Key";
            }
        } catch (SQLException e) {
            outputState = e.getMessage();
        }
        return outputState;          
    }

    
    // =============================================================
        public void getAllTableValue(String namaTable) {
        try {
            String sqlSelect = "Select * from " + namaTable;
            PreparedStatement perintah = connectionDB.prepareStatement(sqlSelect);
            result = perintah.executeQuery();
            int tempNum = 0;
            switch (namaTable) {
                case "jenis_industri" -> {
                    tempNum = 0;
                    System.out.println("No\tKode Industri\tNama Industri");
                    while (result.next()) {
                        tempNum +=1;
                        System.out.println(tempNum +
                                "\t" + result.getString("Kode_industri")+ 
                                "\t\t" + result.getString("Nama_industri"));
                    }
                }
                case "umkm" -> {
                    tempNum = 0;
                    System.out.println("No\tKode Umkm\tNama Umkm\t\tNama Pemilik\tDeskripsi\tAlamat\t\tKode Penduduk\tKode Industri");
                    while (result.next()) {
                        tempNum +=1;
                        System.out.println(tempNum +
                                "\t" + result.getString("Kode_industri") + 
                                "\t\t" + result.getString("Nama_umkm") +
                                "\t\t" + result.getString("Nama_pemilik") +
                                "\t\t"+ result.getString("Deskripsi") +
                                "\t" + result.getString("Alamat") +
                                "\t\t" + result.getString("Kode_penduduk") +
                                "\t" + result.getString("Kode_industri"));
                        
                    }
                }
                case "jual_produk" -> {
                    tempNum = 0;
                    System.out.println("No\tKode\tKode Umkm\tNama Produk");
                    while (result.next()) {
                        tempNum +=1;
                        System.out.println(tempNum + 
                                "\t" + result.getString("Kode") +
                                "\t" + result.getString("Kode_umkm") +                                 
                                "\t\t" + result.getString("Nama_produk"));
                    }
                }
                case "konsumen" -> {
                    tempNum = 0;
                    System.out.println("No\tKode\tNama Konsumen\tAlamat\t\tNomor Telepon");
                    while (result.next()) {
                        tempNum +=1;
                        System.out.println(tempNum +
                                "\t" + result.getString("Kode")+
                                "\t" + result.getString("Nama_konsumen")+
                                "\t\t" + result.getString("Alamat")+ 
                                "\t\t" + result.getString("No_telepon"));
                    }
                }
                case "transaksi" -> {
                    tempNum = 0;
                    System.out.println("No\tKode Transaksi\tKode Produksi\tTanggal Transaksi\tBanyak Unit \t Kode Konsumen");
                    while (result.next()) {
                        tempNum +=1; 
                        System.out.println(tempNum +
                                "\t" + result.getString("Kode_transaksi")+
                                "\t" + result.getString("Kode_produksi")+
                                "\t" + result.getString("Tanggal_transaksi")+ 
                                "\t" + result.getString("Banyak_unit") +
                                "\t" + result.getString("Kode_konsumen"));
                    }
                }                
                default -> System.out.println("Table tidak ada");
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
        
    public void getSingleColumnValue(String tableName, String columnName){
        try {
            String sqlSelect = "Select " + columnName + " From " + tableName;
            PreparedStatement perintah = connectionDB.prepareStatement(sqlSelect);
            result = perintah.executeQuery();
            int tempNum = 0;
            System.out.println("No\t" + columnName);
            while (result.next()) {
                tempNum += 1;
                System.out.println(tempNum + "\t" + result.getString(columnName));
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }    
    
    public void getTableColumn(String tableName){
        try {
            String sqlSelect = "Select * From " + tableName;
            PreparedStatement perintah = connectionDB.prepareStatement(sqlSelect);
            result = perintah.executeQuery();
            ResultSetMetaData resultMetaData = result.getMetaData();
            for (int index = 1; index < resultMetaData.getColumnCount(); index++) {
                System.out.println(index + ": " + resultMetaData.getColumnName(index));
            }
        }
        catch (SQLException e) {
        }
    }
    
    public void showtTable(DefaultTableModel table, String tableName){
        String sqlSelect = "Select * From " + tableName;
        try {
            PreparedStatement perintah = connectionDB.prepareStatement(sqlSelect);
            result = perintah.executeQuery();
            int tempNum = 1;
            switch (tableName) {
                case "jenis_industri" -> {
                    while(result.next()){
                        table.addRow(new String[]{Integer.toString(tempNum),result.getString(1), result.getString(2)});
                        tempNum+=1;
                    }
                }
                case "jual_produk" -> {
                    while(result.next()){
                        table.addRow(new String[]{Integer.toString(tempNum),result.getString(1),
                            result.getString(2), result.getString(3)});
                        tempNum+=1;
                    }
                }                
                case "konsumen" -> {
                    while(result.next()){
                        table.addRow(new String[]{Integer.toString(tempNum),result.getString(1),
                            result.getString(2), result.getString(3), result.getString(4)});
                        tempNum+=1;
                    }
                }                
                case "transaksi" -> {
                    while(result.next()){
                        table.addRow(new String[]{Integer.toString(tempNum),result.getString(1),
                            result.getString(2), result.getString(3), result.getString(4),
                            result.getString(5)});
                        tempNum+=1;
                    }
                }
                case "umkm" -> {
                    while(result.next()){
                        table.addRow(new String[]{Integer.toString(tempNum),result.getString(1),
                            result.getString(2), result.getString(3), result.getString(4),
                            result.getString(5), result.getString(6), result.getString(7)});
                        tempNum+=1;
                    }
                }                  
                default -> throw new AssertionError();
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }
    
    public void searchTableLike(DefaultTableModel table, String tableName, String keyLike){
                String sqlSelect = "Select * From " + tableName + " Where Nama_industri Like '%" + keyLike + "%'";
        try {
            PreparedStatement perintah = connectionDB.prepareStatement(sqlSelect);
            result = perintah.executeQuery();
            int tempNum = 1;
            switch (tableName) {
                case "jenis_industri" -> {
                    while(result.next()){
                        table.addRow(new String[]{Integer.toString(tempNum),result.getString(1),
                            result.getString(2)});
                        tempNum+=1;
                    }
                }
                case "jual_produk" -> {
                    while(result.next()){
                        table.addRow(new String[]{Integer.toString(tempNum),result.getString(1),
                            result.getString(2), result.getString(3)});
                        tempNum+=1;
                    }
                }                
                case "konsumen" -> {
                    while(result.next()){
                        table.addRow(new String[]{Integer.toString(tempNum),result.getString(1),
                            result.getString(2), result.getString(3), result.getString(4)});
                        tempNum+=1;
                    }
                }                
                case "transaksi" -> {
                    while(result.next()){
                        table.addRow(new String[]{Integer.toString(tempNum),result.getString(1),
                            result.getString(2), result.getString(3), result.getString(4),
                            result.getString(5)});
                        tempNum+=1;
                    }
                }
                case "umkm" -> {
                    while(result.next()){
                        table.addRow(new String[]{Integer.toString(tempNum),result.getString(1),
                            result.getString(2), result.getString(3), result.getString(4),
                            result.getString(5), result.getString(6), result.getString(7)});
                        tempNum+=1;
                    }
                }                
                default -> throw new AssertionError();
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }
    
        public void getForeignKey(JComboBox comboBox, String tableName, String foreignKey){
                String sqlSelect = "Select " + foreignKey + " From " + tableName;
        try {
            PreparedStatement perintah = connectionDB.prepareStatement(sqlSelect);
            result = perintah.executeQuery();
            while(result.next()){
                comboBox.addItem(result.getString(1));
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }
    public void insertValueUjian(String npm, double cb1, double cb2, double rata) {
        try {
            String sqlInsert = "insert into praktikum (npm, nilai1, nilai2, rata_rata) value(?, ?, ?, ?)";
            PreparedStatement perintah = connectionDB.prepareStatement(sqlInsert);
                perintah.setString(1, npm);
                perintah.setDouble(2, cb1);
                perintah.setDouble(3, cb2);
                perintah.setDouble(4, rata);

                perintah.executeUpdate();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }        
}
