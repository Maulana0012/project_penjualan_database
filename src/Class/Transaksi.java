/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class;
import java.util.HashMap;
import java.util.ArrayList;


/**
 *
 * @author ACER
 */
public class Transaksi {

    public float totalHarga;
    public int banyaknyaItem;
    public ArrayList<String> carts = new ArrayList<>();
    public String tanggalTransaksi;
    public int kodeKonsumer;
    
    
    public String getTanggalTransaksi(){
        return this.tanggalTransaksi;
    }
    
    public void getItem(HashMap<String, Float> items){
        for(int index = 0; index < items.size(); index++){
            System.out.println((index+1) + ". " + items.keySet().toArray()[index].toString() + "\t: " + items.get(items.keySet().toArray()[index].toString()));
        }
    }
    
    public int getBanyakItem(){
        return this.banyaknyaItem;
    }
    
    public void buyItem(HashMap<String, Float> items,int index){
        switch(index){
            case 1 -> {
                this.carts.add(items.keySet().toArray()[0].toString());
                this.totalHarga += items.get(items.keySet().toArray()[0].toString());
                this.banyaknyaItem+=1;
                break;
            }
            case 2 -> {
                this.carts.add(items.keySet().toArray()[1].toString());
                this.totalHarga += items.get(items.keySet().toArray()[1].toString());
                this.banyaknyaItem+=1;
                break;
            }
            case 3 -> {
                this.carts.add(items.keySet().toArray()[2].toString());
                this.totalHarga += items.get(items.keySet().toArray()[2].toString());
                this.banyaknyaItem+=1;
                break;
            }
            case 4 -> {
                this.carts.add(items.keySet().toArray()[3].toString());
                this.totalHarga += items.get(items.keySet().toArray()[3].toString());
                this.banyaknyaItem+=1;
                break;
            }
            case 5 -> {
                this.carts.add(items.keySet().toArray()[4].toString());
                this.totalHarga += items.get(items.keySet().toArray()[4].toString());
                this.banyaknyaItem+=1;
                break;
            }
            case 6 -> {
                this.carts.add(items.keySet().toArray()[5].toString());
                this.totalHarga += items.get(items.keySet().toArray()[5].toString());
                this.banyaknyaItem+=1;
                break;
            }
            case 7 -> {
                this.carts.add(items.keySet().toArray()[6].toString());
                this.totalHarga += items.get(items.keySet().toArray()[6].toString());
                this.banyaknyaItem+=1;
                break;
            }
            default ->{
                break;
            }                               
        }
    }
    public void getCart(HashMap<String, Float> items){
        for(int index=0;index < this.carts.size();index++){
            System.out.println((index+1) + ". " + this.carts.get(index) + "\t: " + items.get(this.carts.get(index)));
        }
//        for(HashMap.Entry<String, Float> item: this.carts.entrySet()){
//            System.out.println(item);
    }
    
    public float getTotalHarga(){
        return totalHarga;
    }
    
    public void deleteItemInCarts(HashMap<String, Float> items,int index){
        this.totalHarga -= items.get(this.carts.get(index-1));
        this.carts.remove(index-1);
    }
    
}
