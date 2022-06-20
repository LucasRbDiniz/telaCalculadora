package br.com.cesarschool.poo.telas;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import javax.swing.JOptionPane;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;   
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;



public class TelaCalculadoraMedia {
	int n = 0;
	protected Shell shell;
	private Text text;
	private Text operadores;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			TelaCalculadoraMedia window = new TelaCalculadoraMedia();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("Calculadora de Médias");
		
		text = new Text(shell, SWT.BORDER);
		text.setEnabled(false);
		text.setEditable(false);
		text.setBounds(148, 182, 78, 26);
		
		Label lblResultado = new Label(shell, SWT.NONE);
		lblResultado.setText("Resultado");
		lblResultado.setBounds(58, 185, 70, 20);
		
		Label lblValores = new Label(shell, SWT.NONE);
		lblValores.setBounds(44, 30, 55, 15);
		lblValores.setText("Valores:");
		
		operadores = new Text(shell, SWT.BORDER);
		operadores.setBounds(127, 27, 161, 21);
		
		Button btnMdiaAritmetica = new Button(shell, SWT.RADIO);

		btnMdiaAritmetica.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				n = 1;
			}
		});
		btnMdiaAritmetica.setBounds(127, 66, 112, 16);
		btnMdiaAritmetica.setText("M\u00E9dia Aritmetica");
		
		Button btnMdiaGeometrica = new Button(shell, SWT.RADIO);
		btnMdiaGeometrica.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				n = 2;
			}
		});
		btnMdiaGeometrica.setBounds(127, 99, 126, 16);
		btnMdiaGeometrica.setText("M\u00E9dia Geometrica");
		
		Button btnMdiaHarmnica = new Button(shell, SWT.RADIO);
		btnMdiaHarmnica.addSelectionListener(new SelectionAdapter() { 
			@Override
			public void widgetSelected(SelectionEvent e) {
				n = 3;
			}
		});
		btnMdiaHarmnica.setBounds(127, 133, 137, 16);
		
		
		btnMdiaHarmnica.setText("M\u00E9dia Harm\u00F4nica");
		
		Button btnCalcular = new Button(shell, SWT.NONE);
		btnCalcular.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				CalculadoraMediaMediator calculadora = new CalculadoraMediaMediator();
				String lista = obterOperandos(); 
				if(lista == "") {
					JOptionPane.showMessageDialog(null, "Formato inválido da lista de valores");
				}else {
					double resultado;
					try {
						resultado = calculadora.calcularMedia(lista,n);
						text.setText("" + resultado);
					} catch (ExcecaoListaInvalida e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, e1);
					}
				}
			}
		});
		btnCalcular.setBounds(53, 230, 75, 25);
		btnCalcular.setText("Calcular");
		btnMdiaAritmetica.setFocus();
		Button btnLimpar = new Button(shell, SWT.NONE);
		btnLimpar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				text.setText("");
				operadores.setText("");
				btnMdiaAritmetica.setFocus();
				operadores.setFocus();
			}
		});	
		btnLimpar.setBounds(151, 230, 75, 25);
		btnLimpar.setText("Limpar");
		
		Label lblOperao = new Label(shell, SWT.NONE);
		lblOperao.setBounds(44, 67, 55, 15);
		lblOperao.setText("Opera\u00E7\u00E3o");

	}
	private String obterOperandos() {
		try {
			String lista = (operadores.getText().trim());
			return lista;
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Ao menos um dos operadores não representa um número");
			operadores.setFocus();
			return null;
		}
	}
}
