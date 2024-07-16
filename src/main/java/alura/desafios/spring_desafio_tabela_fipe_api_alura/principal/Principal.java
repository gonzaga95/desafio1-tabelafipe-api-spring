package alura.desafios.spring_desafio_tabela_fipe_api_alura.principal;

import alura.desafios.spring_desafio_tabela_fipe_api_alura.model.Dados;
import alura.desafios.spring_desafio_tabela_fipe_api_alura.service.ConsumoApi;
import alura.desafios.spring_desafio_tabela_fipe_api_alura.service.ConverteDados;

import java.util.Comparator;
import java.util.Scanner;

public class Principal {
    Scanner scanner = new Scanner(System.in);

    private final String URL_BASE = "https://parallelum.com.br/fipe/api/v1/";
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();

    public void exibeMenu() {
        var menu = """
                *** OPÇÕES ***
                Carro
                Moto
                Caminhões
                               
                Digite uma opção para consulta: 
                                
                """;

        System.out.println(menu);
        var opcao = scanner.nextLine();

        String endereco;

        if (opcao.toLowerCase().contains("carr")) {
            endereco = URL_BASE + "carros/marcas/";
        } else if (opcao.toLowerCase().contains("mot")) {
            endereco = URL_BASE + "motos/marcas/";
//        } else if (opcao.toLowerCase().contains("camin")) {
//            endereco = URL_BASE + "caminhoes/marcas/";
        } else {
            endereco = URL_BASE + "caminhoes/marcas/";
//            System.out.println("Opção inválida");
        }

        String json;
        json = consumo.obterDados(endereco);
        System.out.println(json);

        var marcas = conversor.obterLista(json, Dados.class);
        marcas.stream()
                .sorted(Comparator.comparing(Dados::codigo))
                .forEach(System.out::println);

        System.out.println("Informe o código da marca que deseja consultar ");
        var codigoMarca = scanner.nextLine();
    }
}
