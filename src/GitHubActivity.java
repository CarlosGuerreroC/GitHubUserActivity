import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GitHubActivity {
    public static void main(String[] args) {
        if (args.length != 1){
            System.out.println("Uso: GitHubActivity <nombre de usuario>");
            return;
        }
        String usuario = args[0];
        String urlString = "https://api.github.com/users/" + usuario + "/events";

        try {
            URL url = new URL(urlString);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            int codigoR = con.getResponseCode();
            if (codigoR != 200){
                System.out.println("Error: No se pudo obtener la actividad (codigo "+ codigoR +")");
                return;
            }

            BufferedReader lector = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String linea;
            int cont = 0;
            System.out.println("Actividad reciente del usuario "+ usuario +":");
            while ((linea = lector.readLine()) != null && cont < 20){
                System.out.println(linea);
                cont++;
            }
            lector.close();
        }catch (Exception e){
            System.out.println("Ocurrio un error: "+ e.getMessage());
        }
    }
}
