package com.app.bot.dominio;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.ProfilesIni;

import com.app.bot.view.TelaLoandingZap;



public class RoboZap {

//	atriubutos das classes
	private static WebDriver driver;
	private static String urlZap = "https://web.whatsapp.com";
	private static String caminhoDriver = "/driver/geckodriver.exe";
//	campo de envio de mensagem do whast
	private static String idCampo = "//*[@id='main']/footer/div[1]/div[2]/div/div[2]";
	private static String urlMens = "https://web.whatsapp.com/send?phone=";
	private static String config = "SIM";


	public RoboZap() {

		try {
		System.setProperty("webdriver.gecko.driver", caminhoDriver);
		
		if(config.equals("SIM")) {
		ProfilesIni listProfiles = new ProfilesIni();
		FirefoxProfile   profile = listProfiles.getProfile("default");
		FirefoxOptions opcao = new FirefoxOptions();
		opcao.setProfile(profile);
		driver = new FirefoxDriver(opcao);
		
		}else {
			driver = new FirefoxDriver();
			driver.get(urlZap);
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);
		}catch (Exception e) {
			new JOptionPane("Erro ao iniciar o gechokDriver: "+e.getMessage());
		}
	}

	public  void enviarMensagensAuto(ArrayList<?> fone) throws NoSuchElementException, InterruptedException {
//configuração do browser
		
		Thread.sleep(2000);
		MensagensZap mensagem = MensagensZap.restoreModelo();
		
			for (Object f : fone) {
//	    	metodo pegar modelo da mensagem
				
				mensagem.setFone(f.toString());
				

//	    	setadndo o nvegador pra o url certo
				driver.get(urlMens + "55" + mensagem.getFone() + "&source=&data=#");

//	    	pegando o campo para enviar mensagem
				Thread.sleep(1000);
				WebElement txtBox = driver.findElement(By.xpath(idCampo));

//	    	dados da mensagem
				txtBox.sendKeys(mensagem.getTitulo());
				txtBox.sendKeys(Keys.chord(Keys.SHIFT, Keys.ENTER));// pular linha
				txtBox.sendKeys(Keys.BACK_SPACE);// apagar final script
//NOME DA CLINICA 
				txtBox.sendKeys("🏥 " + "*" + mensagem.getClinica() + "*");
				txtBox.sendKeys(Keys.chord(Keys.SHIFT, Keys.ENTER));// pular linha
				txtBox.sendKeys(Keys.BACK_SPACE);// apagar final script
			
//			condição de teste do sexo
				if (mensagem.getSexo().equals("M")) {
					txtBox.sendKeys("👨 " + "*" + mensagem.getDestino() + "*");
				} else if (mensagem.getSexo().equals("F")) {
					txtBox.sendKeys("👩 " + "*" + mensagem.getDestino() + "*");
				} else {
					txtBox.sendKeys("👤 " + "*" + mensagem.getDestino() + "*");
				}
				txtBox.sendKeys(Keys.chord(Keys.SHIFT, Keys.ENTER));// pular linha
				txtBox.sendKeys(Keys.BACK_SPACE);// apagar final script

//DATA E HORA DA CONSULTA	
				txtBox.sendKeys(mensagem.getMensagemData() + mensagem.getData());
				txtBox.sendKeys(Keys.chord(Keys.SHIFT, Keys.ENTER));// pular linha
				txtBox.sendKeys(Keys.BACK_SPACE);// apagar final script
				txtBox.sendKeys(mensagem.getMensagemHora() + mensagem.getHora());
				txtBox.sendKeys(Keys.chord(Keys.SHIFT, Keys.ENTER));// pular linha
				txtBox.sendKeys(Keys.BACK_SPACE);// apagar final script

//	NOME DO MEDICO
				txtBox.sendKeys("👨‍⚕️ Dr(a)" + mensagem.getMedico());
				txtBox.sendKeys(Keys.chord(Keys.SHIFT, Keys.ENTER));// pular linha
				txtBox.sendKeys(Keys.BACK_SPACE);// apagar final script

//PROCEDIMENTO A SER REALIZADO
				txtBox.sendKeys("📋 para realizar: " + "Sondagem");
				txtBox.sendKeys(Keys.chord(Keys.SHIFT, Keys.ENTER));// pular linha
				txtBox.sendKeys(Keys.BACK_SPACE);// apagar final script

//  FINAL MENSAGEM
				txtBox.sendKeys(mensagem.getBody());// corpo da mensagem
				Thread.sleep(1000);
				txtBox.sendKeys(Keys.ENTER);// envair usando enter
				Thread.sleep(1000);
			JOptionPane.showMessageDialog(null, "Mensangem enviadas com sucesso!", "", JOptionPane.INFORMATION_MESSAGE);
			}
			
		   driver.close();
	}

//	metodo de enviar para grupos
	public void enviarDivulgacoesGrupo(ArrayList<String> listagrupo,String titulo, String mensagem) throws InterruptedException ,  NoSuchElementException {
		Thread.sleep(2000);
		for (String grupo : listagrupo) {
			WebElement mouse = driver.findElement(By.xpath("//span[@title='"+grupo+"']"));
			mouse.click();
			Thread.sleep(1000);
			WebElement txtBox = driver.findElement(By.xpath(idCampo));
			txtBox.click();
			txtBox.sendKeys(" *"+titulo+"* ");
			txtBox.sendKeys(Keys.SHIFT,Keys.ENTER);
			txtBox.sendKeys(mensagem);
			Thread.sleep(1000);
			txtBox.sendKeys(Keys.ENTER);
			Thread.sleep(1000);
			
		}
		driver.close();
	}

//	metodo de enviar para varias pessoa
	public void enviarDivulgacoesPessoas(ArrayList<String> lista,String titulo, String mensagem) throws InterruptedException , NoSuchElementException{
		
		Thread.sleep(2000);
		
		for (String numero : lista) {
			driver.get(urlMens + "55" + numero + "&source=&data=#");
			Thread.sleep(1000);
			WebElement txtBox = driver.findElement(By.xpath(idCampo));
			txtBox.sendKeys(" *"+titulo+"* ");
			txtBox.sendKeys(Keys.SHIFT,Keys.ENTER);
			txtBox.sendKeys(mensagem);
			Thread.sleep(1000);
			txtBox.sendKeys(Keys.ENTER);
			Thread.sleep(1000);
		}
		driver.close();
	}

//			metodo de enviar para apenas uma pessoa
	public void enviarMensagemPessoa(String numero, String titulo,String mensagem) throws InterruptedException,  NoSuchElementException{
		Thread.sleep(2000);
		driver.get(urlMens + "55" + numero + "&source=&data=#");
		Thread.sleep(1000);
		WebElement txtBox = driver.findElement(By.xpath(idCampo));
		txtBox.sendKeys(" *"+titulo+"* ");
		txtBox.sendKeys(Keys.SHIFT,Keys.ENTER);
		txtBox.sendKeys(mensagem);
		Thread.sleep(1000);
		txtBox.sendKeys(Keys.ENTER);
		Thread.sleep(1000);
		driver.close();
	}
	public void closebrowser() {
		driver.close();
	}
	
//	metodo de enviar para varias pessoa
	public void enviarMensagensNiver(JTable table) throws InterruptedException , NoSuchElementException{
		
		Thread.sleep(2000);
		
		for (int i=0; i<table.getRowCount();i++) {
			driver.get(urlMens + "55" + table.getValueAt(i, 2) + "&source=&data=#");
			Thread.sleep(1000);
			WebElement txtBox = driver.findElement(By.xpath(idCampo));
			txtBox.sendKeys("🎂"+" *"+"Parabéns pelo seu aniversário!"+"* "+"🎂");
			txtBox.sendKeys(Keys.SHIFT,Keys.ENTER);
			txtBox.sendKeys("🎁 Oi,  *"+table.getValueAt(i, 1)+"* ");
			txtBox.sendKeys(Keys.SHIFT,Keys.ENTER);
			txtBox.sendKeys("A Clinica deseja que sua vida seja recheada de sorrisos e bons momentos. "
					+ "Obrigado por fazer parte de nossa história.🎊🎉");
			Thread.sleep(1000);
			txtBox.sendKeys(Keys.ENTER);
			Thread.sleep(1000);
		}
		driver.close();
	}

	public static void main(String[] args) {
	
		// RoboZap.enviarMensagensAuto(null);
		ArrayList<String> lista = new ArrayList<>();
		lista.add("83996386694");
		lista.add("‍83996538287");
		lista.add("749804-6264");
		lista.add("‍749980-7980");
		
		String m = "🤖 oi sou andrel o robo automantico do senhor *Luan Miranda* ele está me testando! ❤ desculpe o incomodo";
		new TelaLoandingZap(null, lista, null,"", "*Aprendendo a robotizar*",m);
	

	}

}
