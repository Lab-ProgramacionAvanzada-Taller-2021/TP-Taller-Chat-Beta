package cliente;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;

public class Cliente {

	private Socket cliente;
	
	public Cliente(String ip, int puerto) {
		try {
			cliente = new Socket(ip, puerto);
			new ClienteHilo(cliente).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	public void escribe() throws IOException, NullPointerException {
		InetAddress address = InetAddress.getLocalHost();
		String ip = address.getHostAddress();
		InputStreamReader leer = new InputStreamReader(System.in);
		BufferedReader buffer = new BufferedReader(leer);
		System.out.println(">: ");
		String texto = buffer.readLine();
		while(!texto.equals("Salir")) {
			new DataOutputStream(cliente.getOutputStream()).writeUTF(ip + ": " + texto);
			System.out.println(">: ");
			texto = buffer.readLine();
		}
		cliente.close();
	}				
	
	public static void main(String[] args) {
		
		try {
			new Cliente("localhost", 40000).escribe();
		} catch (IOException e) {
			System.err.println("Se cerro la conexion");
		}
	}

}
