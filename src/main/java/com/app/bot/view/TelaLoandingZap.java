package com.app.bot.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.MatteBorder;

import org.openqa.selenium.NoSuchElementException;

import com.app.bot.dominio.RoboZap;

public class TelaLoandingZap extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JProgressBar barra = new JProgressBar();
	private int mlSegundos;
	private final JPanel panel = new JPanel();
	private JLabel lblTempoCorrido;
	private final JLabel label = new JLabel("");
	private int seg=0;
	private int min=0;
	private ArrayList<?>lista;
	private ArrayList<String>listaPessoa;
	private ArrayList<String>listaGrupo;
	private String fone;
	private String titulo;
	private String corpo;
	private boolean band=false;

	public TelaLoandingZap(ArrayList<?>lista, ArrayList<String>listaPessoa,ArrayList<String>listaGrupo,String fone, String titulo,String corpo) {
		setBackground(Color.DARK_GRAY);
		setTitle("Processando seu Pedido....");
		setResizable(false);// seuJFrame
		setType(java.awt.Window.Type.UTILITY);// nao minimizar
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(586, 144);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new MatteBorder(10, 5, 5, 5, (Color) new Color(0, 102, 51)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setModal(true);
	    
		Timer timer = new Timer(1000, new hora());
		timer.start();
		
		 this.lista=lista;
		 this.listaPessoa=listaPessoa;
		 this.listaGrupo=listaGrupo;
		 this.fone=fone;
		 this.titulo=titulo;
		 this.corpo=corpo;
		 
		mlSegundos=3000;
		barra.setStringPainted(true);
		barra.setMaximum(mlSegundos);
		barra.setIndeterminate(true);
		barra.setBounds(22, 52, 527, 34);
		contentPane.add(barra);
		
		new Temporizador().start();
		
		JLabel lblNewLabel = new JLabel("Carregando mensagens aguarde...");
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(12, 13, 523, 40);
		contentPane.add(lblNewLabel);
		
		panel.setBackground(Color.WHITE);
		panel.setForeground(Color.RED);
		panel.setBounds(22, 95, 527, 15);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblTempoCorrido = new JLabel("Tempo Corrido: ");
		lblTempoCorrido.setForeground(Color.RED);
		lblTempoCorrido.setBounds(170, 0, 77, 14);
		lblTempoCorrido.setFont(new Font("Dialog", Font.BOLD, 10));
		panel.add(lblTempoCorrido);
		
		label.setFont(new Font("Tahoma", Font.BOLD, 14));
		label.setForeground(Color.BLACK);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(245, 0, 50, 14);
		
		panel.add(label);
		
		setVisible(true);
		
	
		
		
	}
	
	class hora implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			seg++;
			if(seg==60) {
				min++;
				seg=0;
			}
		     label.setText(String.format("%02d:%02d",min,seg));
			
		}
	}
	
	public class Temporizador extends Thread{
		public void run() {
			RoboZap robo = null;
			while(barra.getValue()<mlSegundos) {
				try {
					sleep(10);
					barra.setValue(barra.getValue()+100);
					if(barra.getValue()==1000 && lista!=null) {
						    robo = new RoboZap();
							robo.enviarMensagensAuto(lista);
							
					}else if(barra.getValue()==1000 && listaPessoa!=null) {
						     robo = new RoboZap();
						     robo.enviarDivulgacoesPessoas(listaPessoa, titulo, corpo);
						     
					}else if(barra.getValue()==1000 && listaGrupo!=null) {
						  robo = new RoboZap();
					     robo.enviarDivulgacoesGrupo(listaGrupo, titulo, corpo);
					     
					}else if(barra.getValue()==1000) {
						  robo = new RoboZap();
				     robo.enviarMensagemPessoa(fone, titulo, corpo);
					}
					if(barra.getValue()==mlSegundos) {
						dispose();
					}
				} catch (NoSuchElementException | InterruptedException e) {
					band=true;
					JOptionPane.showMessageDialog(null, "Erro  ao envir mensagem zap: "+e.getMessage(),"Erro", JOptionPane.ERROR_MESSAGE);
					robo.closebrowser();
					dispose();
					break;
				}
			}
		}
	}
	public boolean getBand() {
		return this.band;
	}
	public static void main(String[] args) {
		
	}
	
}
