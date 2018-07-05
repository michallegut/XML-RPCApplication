import org.apache.xmlrpc.XmlRpcClient;

import java.util.Scanner;
import java.util.Vector;

public class Client {
    public static void main(String[] args) {
        try {
            String[] input = null;
            while (input == null || !input[0].equals("exit")) {
                System.out.print("Enter server's IP address: ");
                Scanner scanner = new Scanner(System.in);
                String ip = scanner.nextLine();
                System.out.print("Enter server's port: ");
                String port = scanner.nextLine();
                XmlRpcClient client = new XmlRpcClient("http://" + ip + ":" + port);
                System.out.println("You can now request services. Enter 'disconnect' to disconnect. Enter 'exit' to terminate client");
                System.out.println(client.execute("server.show", new Vector()));

                //Modyfikacja
                Vector<Object> repeatParameters1 = new Vector<>();
                repeatParameters1.addElement("aaa");
                repeatParameters1.addElement(5);
                Vector<Object> repeatParameters2 = new Vector<>();
                repeatParameters2.addElement("bbb");
                repeatParameters2.addElement(1);
                client.executeAsync("server.repeat", repeatParameters1, new AsynchronousCallback());
                client.executeAsync("server.repeat", repeatParameters2, new AsynchronousCallback());

                input = scanner.nextLine().split(" /");
                while (!input[0].equals("disconnect") && !input[0].equals("exit")) {
                    Vector<String> parameters = new Vector<>();
                    for (int i = 1; i < input.length; i++) {
                        parameters.addElement(input[i]);
                    }
                    Object result = client.execute("server." + input[0], parameters);
                    System.out.println(result);
                    parameters.clear();
                    input = scanner.nextLine().split(" /");
                }
                System.out.println("Disconnected");
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
