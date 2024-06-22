/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class;

/**
 *
 * @author ACER
 */
public class GetDataTransaksi extends Transaksi{
    
    public float discount(){
        if(this.totalHarga > 10000){
            return this.totalHarga * 0.10f;
        }
        
        else if(this.totalHarga > 30000){
            return this.totalHarga * 0.20f;
        }
        
        else if(this.totalHarga > 100000){
            return this.totalHarga * 0.30f;
        }
        else{
            return 0;
        }
    }
    
    public int getDiscount(){
        if(this.totalHarga > 9999 && this.totalHarga < 29999){
            return 10;
        }
        
        else if(this.totalHarga >= 29999 && this.totalHarga < 99999){
            return 20;
        }
        
        else if(this.totalHarga >= 99999){
            return 30;
        }
        else{
            return 0;
        }

    }
}
