package modulos.produtos;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import paginas.LoginPage;

import java.time.Duration;

@DisplayName("Testes Web no Modulo de Produtos")

public class ProdutosTest {

    private WebDriver navegador;

    @BeforeEach
    public void beforEach(){
        //Abrir o navegador
        System.setProperty("webdriver.chrome.driver", "C:\\drivers\\Chrome\\chromedriver.exe");
        this.navegador = new ChromeDriver();

        // Vou maximizar a tela
        this.navegador.manage().window().maximize();

        //Vou definir um tempo de espera padrão
        this.navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        //Navegar pela pagina da Lojinha Web
        this.navegador.get("http://165.227.93.41/lojinha-web/v2/");

    }

    @Test
    @DisplayName("Nao é permitido registrar um produto com valor igual a zero")
    public void testNaoEPermitidoRegistrarProdutosComValorIgualAZero(){

        //Fazer Login

       String mensagemApresentada =  new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                .acessarFormularioAdicaoNovoProduto()
                .informarNomeDoProduto("Macbook Pro")
                .informarValorDoProduto("000")
                .informarCoresDoProduto("preto,branco")
                .submeterFormularioDeAdicaoComErro()
                .capturarMensagemApresentada();


        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", mensagemApresentada);


    }

    @Test
    @DisplayName("Nao é permitido registrar um produto com valor maior que R$ 7.000,00")
    public void testNaoEPermitidoRegistrarProdutoComValorMaiorDeSeteMil(){
        String mensagemApresentada =  new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                .acessarFormularioAdicaoNovoProduto()
                .informarNomeDoProduto("Macbook Pro")
                .informarValorDoProduto("700001")
                .informarCoresDoProduto("preto,branco")
                .submeterFormularioDeAdicaoComErro()
                .capturarMensagemApresentada();

        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", mensagemApresentada);

    }

    @Test
    @DisplayName("Posso adicionar produtos que estejam no limite de 0,01")
    public void testPossoAdicionarProdutosComValorDeUmCentavo(){
       String mensagemApresentada =  new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                .acessarFormularioAdicaoNovoProduto()
                .informarNomeDoProduto("Macbook Pro")
                .informarValorDoProduto("3000")
                .informarCoresDoProduto("azul")
                .submeterFormularioDeAdicaoComSucesso()
                .capturarMensagemApresentada();


        Assertions.assertEquals("Produto adicionado com sucesso", mensagemApresentada);

    }

    @Test
    @DisplayName("Posso adicionar produtos que estejam no limite de 7.000,00")
    public void testPossoAdicionarProdutosComValorDeSeteMil(){
        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                .acessarFormularioAdicaoNovoProduto()
                .informarNomeDoProduto("Iphone 12")
                .informarValorDoProduto("70000")
                .informarCoresDoProduto("Rosa")
                .submeterFormularioDeAdicaoComSucesso()
                .capturarMensagemApresentada();

        Assertions.assertEquals("Produto adicionado com sucesso", mensagemApresentada);
    }


    @AfterEach
    public void afterEach(){
        //Vou fechar o navegador
        navegador.quit();
    }

}
