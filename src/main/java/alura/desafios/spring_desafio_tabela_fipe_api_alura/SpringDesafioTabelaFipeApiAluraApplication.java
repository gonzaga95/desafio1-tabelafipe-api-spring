package alura.desafios.spring_desafio_tabela_fipe_api_alura;

import alura.desafios.spring_desafio_tabela_fipe_api_alura.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringDesafioTabelaFipeApiAluraApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringDesafioTabelaFipeApiAluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal();
		principal.exibeMenu();
	}
}
