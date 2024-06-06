/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Main;
import CRUD.Database;

/**
 *
 * @author ACER
 */
public class MainFile {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Database dbConnect = new Database();
        dbConnect.insertValueUMKM("001UF", "Batik Boga", "Rahmadi", "Fashion", "Jalan C", "3313091704331", "AISAS");
        // Input Table Industr
//        dbConnect.insertValueJenisIndustri("ADES", "PT INDO FOOD");
//        dbConnect.insertValueJenisIndustri("AISA", "PT Gucci China");
//        dbConnect.insertValueJenisIndustri("ALTO", "PT Chanel youtube");
//        dbConnect.insertValueJenisIndustri("BTEK", "PT INDO FOOD");
//        dbConnect.insertValueJenisIndustri("BUDI", "PT Course Makmur");
        
    }
}
