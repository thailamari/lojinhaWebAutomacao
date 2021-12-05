package paginas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FormularioDeEdicaoDoProdutoPage {
    private WebDriver navegador;

    public FormularioDeEdicaoDoProdutoPage (WebDriver navegador){
        this.navegador = navegador;
    }

    public String capturarMensagemApresentada(){
        return navegador.findElement(By.cssSelector(".toast.rounded")).getText();
    }
}
