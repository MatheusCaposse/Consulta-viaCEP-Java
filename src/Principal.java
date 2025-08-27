import br.com.alura.Busca.Busca;
import br.com.alura.BuscaRecord.BuscaRecord;
import br.com.alura.Exception.CepInvalidoException;
import br.com.alura.Exception.TamanhoCepInvalidoException;
import br.com.alura.HTTP.HTTP;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Principal {

    public static void limparTela(){
        for(int i=0; i < 50 ;i++){
            System.out.println();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        var cep = new HTTP();

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        List<Busca> escrita = new ArrayList<>();

        boolean sair = false;

        while (!sair){
            try{

                System.out.println("--------------------------------------");
                cep.busca();

                if(cep.getCep().equalsIgnoreCase("sair")){
                    break;
                }


                BuscaRecord json = gson.fromJson(cep.getResultado(), BuscaRecord.class);
                Busca busca = new Busca(json);
                escrita.add(busca);

                limparTela();

                System.out.println(busca);


                FileWriter arquivo = new FileWriter("cep.json");
                arquivo.write(gson.toJson(escrita));
                arquivo.close();

            } catch (CepInvalidoException e){
                limparTela();
                System.out.println(e.getMessage());
            } catch (TamanhoCepInvalidoException e){
                limparTela();
                System.out.println(e.getMessage());
            } catch (JsonSyntaxException e){
                limparTela();
                System.out.println("O Cep deve conter apenas numeros");
            }
        }

    }
}
