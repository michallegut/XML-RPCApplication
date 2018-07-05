import org.apache.xmlrpc.WebServer;

public class Server {
    public String shiftEncrypt(String text, String shift) {
        StringBuilder result = new StringBuilder();
        int integerShift = Integer.parseInt(shift);
        for (int i=0; i < text.length(); i++) {
            char character = text.charAt(i);
            if (character >= 'A' && character <= 'Z') {
                result.append((char) ((text.charAt(i) - 65 + integerShift) % 26 + 65));
            }
            else if (character >= 'a' && character <= 'z') {
                result.append((char) ((text.charAt(i) - 97 + integerShift) % 26 + 97));
            }
            else result.append(character);
        }
        return result.toString();
    }

    public String shiftDecrypt(String text, String shift) {
        StringBuilder result = new StringBuilder();
        int integerShift = Integer.parseInt(shift);
        for (int i=0; i < text.length(); i++) {
            char character = text.charAt(i);
            if (character >= 'A' && character <= 'Z') {
                character -= integerShift % 26;
                if (character < 'A') character += 26;
                result.append(character);
            }
            else if (character >= 'a' && character <= 'z') {
                character -= integerShift % 26;
                if (character < 'a') character += 26;
                result.append(character);
            }
            else result.append(character);
        }
        return result.toString();
    }

    public String XOREncrypt(String text, String key) {
        StringBuilder result = new StringBuilder();
        for (int i=0; i < text.length(); i++) {
            result.append(Integer.toHexString(text.charAt(i) ^ key.charAt(i % key.length())));
            result.append("-");
        }
        return result.substring(0, result.length()-1);
    }

    public String XORDecrypt(String text, String key) {
        String[] splitText = text.split("-");
        StringBuilder result = new StringBuilder();
        for (int i=0; i < splitText.length; i++) {
            result.append((char) (Integer.parseInt(splitText[i], 16) ^ key.charAt(i % key.length())));
        }
        return result.toString();
    }

    public String show() {
        return "shiftEncrypt /<text> /<shift>\n" +
                "   <text> - sequence of characters\n" +
                "   <shift> - integer number\n" +
                "Encrypts the <text> using shift cipher with <shift> as a shift value\n" +
                "Example: shiftEncrypt /Hello world! /2\n" +
                "Result: Jgnnq yqtnf!\n\n" +
                "shiftDecrypt /<text> /<shift>\n" +
                "   <text> - sequence of characters\n" +
                "   <shift> - integer number\n" +
                "Decrypts the <text> using shift cipher with <shift> as a shift value\n" +
                "Example: shiftDecrypt /Jgnnq yqtnf! /2\n" +
                "Result: Hello world!\n\n" +
                "XOREncrypt /<text> /<key>\n" +
                "   <text> - sequence of characters\n" +
                "   <key> - sequence of characters\n" +
                "Encrypts the <text> using xor cipher with <key> as a key. Returns a sequence of hexadecimal numbers divided with '-' sign\n" +
                "Example: XOREncrypt /Hello world! /abc\n" +
                "Result: 29-7-f-d-d-43-16-d-11-d-6-42\n\n" +
                "XORDecrypt /<text> /<key>\n" +
                "   <text> - sequence of hexadecimal numbers divided with '-' sign\n" +
                "   <key> - sequence of characters\n" +
                "Decrypts the <text> using xor cipher with <key> as a key\n" +
                "Example: XORDecrypt /29-7-f-d-d-43-16-d-11-d-6-42 /abc\n" +
                "Result: Hello world!";
    }

    //Modyfikacja
    public boolean repeat(String text, int n) {
        for (int i = 0; i < n; i++) {
            System.out.println(text);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        try {
            System.out.println("Server is starting...");
            int port = 10007;
            WebServer server = new WebServer(port);
            server.addHandler("server", new Server());
            server.start();
            System.out.println("Server has started successfully");
            System.out.println("Listening on port: " + port);
            System.out.println("Press crl + c to stop the server");
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
