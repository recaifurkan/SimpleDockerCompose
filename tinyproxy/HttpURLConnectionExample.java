
import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.net.URL;

public class HttpURLConnectionExample {

    private static final class BasicAuthenticator extends Authenticator {
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication("user", "password".toCharArray());
        }
    }


    private static final String GET_URL = "https://ifconfig.co";

    public static void main(String[] args) throws IOException {
        System.setProperty("jdk.http.auth.tunneling.disabledSchemes", "");
        final String authUser = "user";
        final String authPassword = "password";
        Authenticator.setDefault(
                new Authenticator() {
                    public PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(authUser, authPassword.toCharArray());
                    }
                }
        );
        sendGET();
        System.out.println("GET DONE");
    }

    private static void sendGET() throws IOException {
        URL obj = new URL(GET_URL);
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 8888));
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection(proxy);
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept", "application/json");
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        if (responseCode == HttpsURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            System.out.println(response.toString());
        } else {
            System.out.println("GET request not worked");
        }

    }

}
