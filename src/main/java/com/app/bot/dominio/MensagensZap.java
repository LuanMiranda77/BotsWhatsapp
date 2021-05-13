package com.app.bot.dominio;


public class MensagensZap {
	

	private int id;
	
	private String titulo;
	private String clinica;
	private String destino;
	private String sexo;
	private String body;
	private String procedimento;
	private String mensagemData;
	
	private String data;
	private String mensagemHora;
	private String medico;
	
	private String hora;
	private String fone;
	
	
//	construtor class
	public MensagensZap() {
		
	}
	
	public MensagensZap(String titlulo,String clinica,String destino, String body,String mensagemData,String data,String mensagemHora,String hora,String fone) {
		this.titulo=titlulo;
		this.clinica=clinica;
		this.destino=destino;
		this.body=body;
		this.mensagemData=mensagemData;
		this.data=data;
		this.mensagemHora = mensagemHora;
		this.hora=hora;
		this.fone=fone;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getClinica() {
		return clinica;
	}
	public void setClinica(String clinica) {
		this.clinica = clinica;
	}
	public String getDestino() {
		return destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public String getFone() {
		return fone;
	}
	public void setFone(String fone) {
		this.fone = fone;
	}
	public String getMensagemData() {
		return mensagemData;
	}
	public void setMensagemData(String mensagenData) {
		this.mensagemData = mensagenData;
	}
	public String getProcedimento() {
		return procedimento;
	}
	public void setProcedimento(String procedimento) {
		this.procedimento = procedimento;
	}
	public String getMedico() {
		return medico;
	}
	public void setMedico(String medico) {
		this.medico = medico;
	}
	public String getMensagemHora() {
		return mensagemHora;
	}
	public void setMensagemHora(String mensagemHora) {
		this.mensagemHora = mensagemHora;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	

   public static MensagensZap  restoreModelo(){
	   MensagensZap padrao = new MensagensZap();
		padrao.setTitulo("⏰ *Lembrete* ⏰");
		padrao.setClinica("Clinica Da mulher");
		padrao.setDestino("Sr(a)");
		padrao.setBody("👥nós estamos a sua disposição e agradeçemos sua preferência,                 🗣confirmar sua presença?");
		padrao.setMensagemData("📅seu procedimento esta marcada para o dia: ");
		padrao.setMensagemHora("⌚");
	   padrao.setId(1);
	   
	   return padrao;
	}
   public static void main(String[] args) {

}

}
