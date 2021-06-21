package aplicacao.server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileReader;
import java.net.Socket;

public class ThreadServer extends Thread{
	Socket s;
	
	public ThreadServer(Socket socket) {
		s = socket;
	}
	
	@Override
	public void run() {
		try {
			DataInputStream dis = new DataInputStream(s.getInputStream());
			FileInputStream fis = null;
			String nome = dis.readUTF();
			System.out.println(nome);
			if(nome.contains("Apoio")) {
				fis = new FileInputStream("C:\\Users\\Administrador\\Documents\\Sabi\\SABI\\Apoio\\Apoio.exe");
			}else if(nome.contains("Atendimento")) {
				fis = new FileInputStream("C:\\Users\\Administrador\\Documents\\Sabi\\SABI\\Atendimento\\Atendimento.exe");
			}else if(nome.contains("Clinica")) {
				fis = new FileInputStream("C:\\Users\\Administrador\\Documents\\Sabi\\SABI\\Clinica\\Clinica.exe");
			}else if(nome.contains("Controle")) {
				fis = new FileInputStream("C:\\Users\\Administrador\\Documents\\Sabi\\SABI\\Controle\\Controle.exe");
			}else if(nome.contains("Sads")) {
				fis = new FileInputStream("C:\\Users\\Administrador\\Documents\\Sabi\\SABI\\Sads\\Sads.exe");
			}
			BufferedInputStream bis = new BufferedInputStream(fis);
			DataOutputStream dos = new DataOutputStream(s.getOutputStream());
			FileReader fr = new FileReader("C:\\Users\\Administrador\\Documents\\Sabi\\SABI\\" + nome + "\\versao.txt");
			BufferedReader br = new BufferedReader(fr);
			dos.writeUTF(br.readLine());
			br.close();
			
			BufferedOutputStream bos = new BufferedOutputStream(dos);
			int dados;
			while((dados = bis.read()) != -1) {
				bos.write(dados);
			}
			bos.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
