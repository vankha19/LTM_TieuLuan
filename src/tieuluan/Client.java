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
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class Client { 
	@SuppressWarnings("resource")
	public static void main(String[] args) throws UnknownHostException {

		String address;
		int port;

		DatagramSocket client = null;
		
		PhanSo ps[] = null;
		Random rand = new Random();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Dang cho ket noi voi server");
		System.out.print("Nhap vao dia chi: ");
		address = sc.nextLine();
		System.out.print("Nhap vao cong: ");
		port = sc.nextInt();
		
		if(!(address.equalsIgnoreCase(address) && port == 8000)) {
			System.out.println("connect fail");
			return;
		}

		
		try {
			InetAddress serverAddress = InetAddress.getByName(address);
			client = new DatagramSocket();
			System.out.println("Connected to server with address="+address+" and port="+port);
			System.out.print("Nhap vao so n phan so can random: ");
			int n = sc.nextInt();

			ArrayList<PhanSo> array = new ArrayList<>();
			ps = new PhanSo[n];

			for (int i = 0; i < n; i++) {
				ps[i] = new PhanSo();
				ps[i].TuSo = rand.nextInt(1000) + 1;
				ps[i].MauSo = rand.nextInt(1000) + 1;

				array.add(ps[i]);
			}
			

			// convert data and send to server
			String str = array.toString();
			System.out.println(str); // display data was sent to server
			
			byte[] data = str.getBytes();
			DatagramPacket sendData = new DatagramPacket(data, data.length, serverAddress, port);
			client.send(sendData);
			System.out.println("Client sent a packet to server!!!");

			
			// receive data from server
			byte[] recByte = new byte[255];
			DatagramPacket recData = new DatagramPacket(recByte, recByte.length, InetAddress.getByName(address), port);
			client.receive(recData);

			String recString = new String(recData.getData(), 0, recData.getLength()).trim();
			String[] dataConvert = recString.substring(1, recString.length()-1).split(", ");
			
			ArrayList<PhanSo> psArray = new ArrayList<>();
			for (int i = 0; i < dataConvert.length; i++) {
				String[] p = dataConvert[i].split("/");
				PhanSo psConvert = new PhanSo(Integer.parseInt(p[0]), Integer.parseInt(p[1]));
				psArray.add(psConvert);
			}
			
			// display result on client
			System.out.println("Phan so co mau la thua so ngyen to:");
			for (PhanSo phanSo : psArray) {
				System.out.println(phanSo.toString());
			}
			
			client.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}
}