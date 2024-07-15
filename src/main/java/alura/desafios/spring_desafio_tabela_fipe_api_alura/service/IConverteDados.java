package alura.desafios.spring_desafio_tabela_fipe_api_alura.service;

public interface IConverteDados {
    <T> T obterDados (String json, Class<T> classe);
}
