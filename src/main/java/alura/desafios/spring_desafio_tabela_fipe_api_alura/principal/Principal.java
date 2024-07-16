package alura.desafios.spring_desafio_tabela_fipe_api_alura.principal;

import alura.desafios.spring_desafio_tabela_fipe_api_alura.model.Dados;
import alura.desafios.spring_desafio_tabela_fipe_api_alura.model.Modelos;
import alura.desafios.spring_desafio_tabela_fipe_api_alura.model.Veiculo;
import alura.desafios.spring_desafio_tabela_fipe_api_alura.service.ConsumoApi;
import alura.desafios.spring_desafio_tabela_fipe_api_alura.service.ConverteDados;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

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

        endereco = endereco + codigoMarca + "/modelos/";
        json = consumo.obterDados(endereco);
        var modeloLista = conversor.obterDados(json, Modelos.class);

        System.out.println("\nModelos para essa marca: ");
        modeloLista.modelos().stream()
                .sorted(Comparator.comparing(Dados::codigo))
                .forEach(System.out::println);

        System.out.println("\nPara filtrar, digite um trecho do modelo desejado: ");
        var veiculoNome = scanner.nextLine();

        List<Dados> modelosFiltrados = modeloLista.modelos().stream()
                .filter(v -> v.nome().toLowerCase().contains(veiculoNome.toLowerCase()))
                .collect(Collectors.toList());

        System.out.println("Modelos encontrados: ");
        modelosFiltrados.forEach(System.out::println);

        System.out.println("Digite o código do modelo desejado: ");
        var codigoModelo = scanner.nextLine();

        endereco = endereco + codigoModelo + "/anos/";
        json = consumo.obterDados(endereco);
        List<Dados> anos = conversor.obterLista(json, Dados.class);

        List<Veiculo> veiculos = new ArrayList<>();

        for (int i = 0; i < anos.size(); i++) {
            var enderecosAnos = endereco + anos.get(i).codigo();
            json = consumo.obterDados(enderecosAnos);
            Veiculo veiculo = conversor.obterDados(json, Veiculo.class);
            veiculos.add((veiculo));
        }

        System.out.println("Os veículos filtrados com preço por ano: ");
        veiculos.forEach(System.out::println);
    }
}
