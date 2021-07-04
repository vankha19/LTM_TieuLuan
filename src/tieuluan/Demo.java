package tieuluan;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.awt.event.ActionEvent;

public class Demo extends JFrame {

	private JPanel contentPane;
	private JTextField txt_address;
	private JTextField txt_port;
	private JTextField txt_n;
	private JTextField txt_key;
	private JTextField textField;
	private JTextField txt_nguyenTo;

	/**
	 * Launch the application.
	 */
	DatagramSocket client = null;
	PhanSo ps[] = null;
	Random rand = new Random();
	String address;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Demo frame = new Demo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Demo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 623, 639);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 566, 204);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nhập địa chỉ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(26, 47, 139, 26);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nhập cổng");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(26, 97, 111, 26);
		panel.add(lblNewLabel_1);
		
		txt_address = new JTextField();
		txt_address.setBounds(188, 48, 209, 26);
		panel.add(txt_address);
		txt_address.setColumns(10);
		
		txt_port = new JTextField();
		txt_port.setBounds(188, 98, 209, 26);
		panel.add(txt_port);
		txt_port.setColumns(10);
		
		JButton btnNewButton = new JButton("KẾT NỐI");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				address = txt_address.getText();
				String port;
				port = txt_port.getText();
			
				if((address.equalsIgnoreCase(address) && port == "8000" && address == "localhost")) {
					JOptionPane.showMessageDialog(null, "ket noi thanh cong");
					return;
				}
				else {					
					JOptionPane.showMessageDialog(null, "ket noi khong thanh cong");
					}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(222, 146, 111, 35);
		panel.add(btnNewButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 224, 589, 360);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Nhập n");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(37, 10, 105, 27);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Nhập khóa");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(37, 59, 105, 34);
		panel_1.add(lblNewLabel_3);
		
		txt_n = new JTextField();
		txt_n.setBounds(194, 15, 209, 26);
		panel_1.add(txt_n);
		txt_n.setColumns(10);
		
		txt_key = new JTextField();
		txt_key.setBounds(194, 71, 209, 26);
		panel_1.add(txt_key);
		txt_key.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Phân Số");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_4.setBounds(37, 167, 105, 27);
		panel_1.add(lblNewLabel_4);
		
		JLabel lblNewLabel_6 = new JLabel("Phân số có mẫu là số nguyên tố");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_6.setBounds(38, 258, 250, 38);
		panel_1.add(lblNewLabel_6);
		
		textField = new JTextField();
		textField.setBounds(37, 204, 511, 44);
		panel_1.add(textField);
		textField.setColumns(10);
		
		txt_nguyenTo = new JTextField();
		txt_nguyenTo.setBounds(37, 306, 511, 44);
		panel_1.add(txt_nguyenTo);
		txt_nguyenTo.setColumns(10);
		
		JButton btnPS = new JButton("MÃ HÓA");
		btnPS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				address = txt_address.getText();
				String strPort  = txt_port.getText();
			    int port = Integer.parseInt(strPort);

				try {
					InetAddress serverAddress = InetAddress.getByName(address);
					client = new DatagramSocket();
	                String n = txt_n.getText();
					
				    int N = Integer.parseInt(n);

					ArrayList<PhanSo> array = new ArrayList<>();
					ps = new PhanSo[N];

					for (int i = 0; i < N; i++) {
						ps[i] = new PhanSo();
						ps[i].TuSo = rand.nextInt(1000) + 1;
						ps[i].MauSo = rand.nextInt(1000) + 1;

						array.add(ps[i]);
						String str = array.toString();
						byte[] data = str.getBytes();
						DatagramPacket sendData = new DatagramPacket(data, data.length, serverAddress, port);
						client.send(sendData);
		                String strKey = txt_key.getText();
						byte[] key = strKey.getBytes();
						DatagramPacket sendKey = new DatagramPacket(key, key.length, serverAddress, port);
						client.send(sendKey);
						textField.setText(str);
						}
					
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
					for (PhanSo phanSo : psArray) {
						String psNguyenTo = phanSo.toString();
						txt_nguyenTo.setText(psNguyenTo);
					}
					
					client.close();

				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		btnPS.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnPS.setBounds(229, 118, 114, 38);
		panel_1.add(btnPS);
	}
}
