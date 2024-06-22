/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.Random;

/**
 *
 * @author ACER
 */
public class Konsumen extends GetDataTransaksi{
    Random randomKode = new Random();
    public int kodeKonsumen;
    public String namaKonsumen;
    public float saldo;
    public String alamatKonsumen;
    public String noTeleponKonsumen;
    
    
    LocalDateTime dateTime = LocalDateTime.now();
    DateTimeFormatter localDateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"); // parsing
    DateTimeFormatter dateTimeTransaksi = DateTimeFormatter.ofPattern("yyyy/MM/dd"); // parsing

    
    
//    public Konsumen(String namaKonsumen, String alamatKonsumen, String noTeleponKonsumen){
//        this.kodeKonsumen = randomKode.nextInt(1000000);
//        this.namaKonsumen = namaKonsumen;
//        this.alamatKonsumen = alamatKonsumen;
//        this.noTeleponKonsumen = noTeleponKonsumen;
//    }

    public void inputKodeKonsumen(int kode){
        this.kodeKonsumen = kode;
    }    
    
    public int getKodeKonsumen(){
        int kode = randomKode.nextInt(1000000);
        return kode;
    }    
        
    public void inputNamaKonsumen(String name){
        this.namaKonsumen = name;
    }

    public String getNamaKonsumen(){
        return this.namaKonsumen;
    }
    
    public void inputAlamatKonsumen(String alamat){
        this.alamatKonsumen = alamat;
    }
    
    public String gentAlamatKonsumen(){
        return this.alamatKonsumen;
    }    
    
    public void inputNoTeleponKonsumen(String noTelepon){
        this.noTeleponKonsumen = noTelepon;
    }    

    public String getNoTeleponKonsumen(){
        return this.noTeleponKonsumen;
    }    

    
    public float getSaldo(){
        return this.saldo;
    }
    
    public float inputSaldo(float tambahSaldo){
        this.saldo += tambahSaldo;
        return this.saldo;
    }
    
    public String proceedTransaction(){
        if(this.saldo < this.totalHarga){
            return "saldo tidak cukup untuk melakukan transaksi";
        }
        else{
            this.saldo -= this.totalHarga - this.getDiscount();
            this.carts.clear();
            this.tanggalTransaksi = dateTime.format(localDateFormat);
            return "transaksi berhasil, sisa saldo: " + this.saldo;
        }
    }
    
    public String getDate(){
        return dateTime.format(dateTimeTransaksi);
    }
}
