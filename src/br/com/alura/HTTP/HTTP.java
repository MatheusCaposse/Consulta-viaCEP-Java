    package br.com.alura.HTTP;

    import br.com.alura.Exception.CepInvalidoException;
    import br.com.alura.Exception.TamanhoCepInvalidoException;

    import java.io.IOException;
    import java.net.URI;
    import java.net.http.HttpClient;
    import java.net.http.HttpRequest;
    import java.net.http.HttpResponse;
    import java.util.Scanner;

    public class HTTP {
        HttpClient client = HttpClient.newHttpClient();

         private String resultado;
         private String cep;

         public void setResultado(String resultado){
             this.resultado = resultado;
         }

         public String getResultado(){
             return this.resultado;
         }

         public String getCep(){
             return cep;
         }

        public void busca() throws IOException, InterruptedException {
            Scanner l = new Scanner(System.in);

            System.out.println("Digite o cep que deseja buscar (formato 12345-090)");
            cep = l.nextLine();

            String cepFormatado = cep.replace("-", "");
            if (cepFormatado.length()!=8 && !cepFormatado.equalsIgnoreCase("sair")){
                throw new TamanhoCepInvalidoException("CEP inválido: deve contér 8 numeros");
            }
            String busca = "https://viacep.com.br/ws/"+ cepFormatado +"/json/";

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(busca))
                    .build();

            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            String resultado = response.body();
            if (resultado.contains("erro")){
                throw new CepInvalidoException("O CEP é invalido");
            } else {
                setResultado(resultado);
            }

        }
    }
