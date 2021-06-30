/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tieuluan;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Admin
 */
public class Server {
    public static void main(String[] args) throws UnknownHostException {
        DatagramSocket datagramSocket = null;
       
        InetAddress address = InetAddress.getByName("localhost");
        int port = 1234;
        try {
            datagramSocket = new DatagramSocket(1234);
            System.out.println("Server đã sẳn sàng");
            
             // tạo mảng byte để nhận dữ liệu
            byte nhanDuLieu[] = new byte[256];
            // datagrampacket nhận dữ liệu
            DatagramPacket nhanData = new DatagramPacket(nhanDuLieu, nhanDuLieu.length);
            datagramSocket.receive(nhanData);

            // tạo biến string để đọc dữ liệu nhận sang string
            String message = new String(nhanData.getData(), 0, nhanData.getLength()).trim();
            
            System.out.println("original  text: "+message);
            
            
            // Key            
            String SECRET_KEY = "12345678";
            SecretKeySpec skeySpec = new SecretKeySpec(SECRET_KEY.getBytes(), "DES");
             
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            //Mã hóa        
            byte[] byteEncrypted = cipher.doFinal(message.getBytes());
            String encrypted =  Base64.getEncoder().encodeToString(byteEncrypted);
            System.out.println("encrypted text: "+encrypted);
            //Giari mã
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            byte[] byteDecrypted = cipher.doFinal(byteEncrypted);
            String decrypted = new String(byteDecrypted);
            System.out.println("decrypted text: "+decrypted);
            
            
            
            
        }catch (Exception e) {
            System.err.print(e);
        } finally {
            if (datagramSocket != null) {
                datagramSocket.close();
            }
        }
    }
}
