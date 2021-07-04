/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tieuluan;

import java.io.FileOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Admin
 */
public class Server {

	// check number is prime
	public static boolean isPrimeNumber(int n) {
		// so nguyen n < 2 khong phai la so nguyen to
		if (n < 2) {
			return false;
		}
		// check so nguyen to khi n >= 2
		int squareRoot = (int) Math.sqrt(n);
		for (int i = 2; i <= squareRoot; i++) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) throws UnknownHostException {
		InetAddress address = InetAddress.getByName("localhost");
		int port = 8000;

		DatagramSocket server = null;

		try {
			server = new DatagramSocket(port, address);
			System.out.println("Server ready for connect");

			// Receive data from client
			byte recByte[] = new byte[256];
			DatagramPacket recData = new DatagramPacket(recByte, recByte.length);
			server.receive(recData);

			// Convert byte array to string
			String dataString = new String(recData.getData(), 0, recData.getLength()).trim();

			// Convert string to PhanSo class object
			String[] a = dataString.substring(1, dataString.length() - 1).split(", ");
			ArrayList<PhanSo> psArray = new ArrayList<>();
			for (int i = 0; i < a.length; i++) {
				String[] p = a[i].split("/");
				PhanSo ps = new PhanSo(Integer.parseInt(p[0]), Integer.parseInt(p[1]));

				if (Server.isPrimeNumber(Integer.parseInt(p[1]))) {
					psArray.add(ps);
				}
			}

			// Handle PhanSo has 'MauSo' is prime
			System.out.println("Phan so co mau la thua so ngyen to:");
			for (PhanSo phanSo : psArray) {
				System.out.println(phanSo.toString());
			}

			// Send PhanSo has 'MauSo' is prime to client
			DatagramPacket sendPacket = new DatagramPacket(psArray.toString().getBytes(), psArray.toString().length(),
					recData.getAddress(), recData.getPort());
			server.send(sendPacket);

			// Generate Specific Key
			String SECRET_KEY = "12345678";
			SecretKeySpec skeySpec = new SecretKeySpec(SECRET_KEY.getBytes(), "DES");

			/*
			 * Note: DES = Data Encryption Standard. 
			 * ECB = Electronic Codebook mode.
			 * PKCS5Padding = PKCS #5-style padding.
			 */
			Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5PADDING");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
			
			// Encryption
			byte[] byteEncrypted = cipher.doFinal(dataString.getBytes());
			String encrypted = Base64.getEncoder().encodeToString(byteEncrypted);
			System.out.println("encrypted text: " + encrypted);

			// Save to file
			FileOutputStream outputStream = new FileOutputStream("phanso.dat");
			outputStream.write(byteEncrypted);
			outputStream.close();
 
			// Decryption
			cipher.init(Cipher.DECRYPT_MODE, skeySpec);
			byte[] byteDecrypted = cipher.doFinal(byteEncrypted);
			String decrypted = new String(byteDecrypted);
			System.out.println("decrypted text: " + decrypted);

		} catch (Exception e) {
			System.err.print(e);
		} finally {
			if (server != null) {
				server.close();
			}
		}
	}
}