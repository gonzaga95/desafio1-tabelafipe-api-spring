package alura.desafios.spring_desafio_tabela_fipe_api_alura.service;

import java.util.List;

public interface IConverteDados {
    <T> T obterDados (String json, Class<T> classe);

    <T> List<T> obterLista (String json, Class<T> classe);
}
