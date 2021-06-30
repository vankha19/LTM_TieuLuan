/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tieuluan;

import java.lang.reflect.Array;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class Client {
    public static void main(String[] args) throws UnknownHostException {
        
        PhanSo ps[] = null;
        Random rand = new Random();
        Scanner sc = new Scanner(System.in);
        DatagramSocket clientsocket = null;   
        String address;
        int port;
        System.out.println("Dang cho ket noi voi server");
        System.out.print("Nhap vao dia chi: ");
        address = sc.nextLine();
        System.out.print("Nhap vao cong: ");
        port = sc.nextInt();
        
        InetAddress address2 = InetAddress.getByName(address);
        try {
            clientsocket = new DatagramSocket();
            System.out.println("Đã kết nối server");
            System.out.print("Nhap vao so n: ");
            int n = sc.nextInt();

            ArrayList<PhanSo> array = new ArrayList<>();
            ps = new PhanSo[n];

            for (int i = 0; i < n; i++) {
                ps[i] = new PhanSo();
                ps[i].TuSo = rand.nextInt(1000)+1;
                ps[i].MauSo = rand.nextInt(1000)+1;

                array.add(ps[i]);
            }
//            System.out.println(array);
            // khai báo  mảng để chưa dữ liệu gửi lên server, chuyển string > bytes
            String str = array.toString();
            
            System.out.println(str);
            byte[] data = str.getBytes();
             // tạo datagrampacket gửi dữ liêu
            DatagramPacket guiData = new DatagramPacket(data, data.length, address2, port);
                
            clientsocket.send(guiData);
            System.out.println("Da gui");
            
           
            
        } catch (Exception e) {
        }
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    }
}
