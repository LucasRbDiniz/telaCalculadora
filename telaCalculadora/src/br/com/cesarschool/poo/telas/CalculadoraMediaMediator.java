package br.com.cesarschool.poo.telas;

public class CalculadoraMediaMediator {

	public double calcularMedia(String lista, int tipo) throws ExcecaoListaInvalida  {
	    
	    String[] valores = lista.split(",");
	    
	    if(valores.length > 10) {
	       throw new ExcecaoListaInvalida("Lista de valores com muitos elementos");
	    }
	    
	    double[] numeros = new double[valores.length];
	    
	    for(int i = 0; i < valores.length; i++) {
	        try {
	            numeros[i] = Double.parseDouble(valores[i]);
	        } catch(NumberFormatException e) {
	        	throw new ExcecaoListaInvalida("Formato inválido da lista de valores");
	        }
	    }
	    for(int i = 0; i < valores.length; i++) {
	       if(numeros[i] < 0) {
	    	   throw new ExcecaoListaInvalida("Valor(es) negativo(s)");
	       }
	    }
	    switch(tipo) {
	        case 1:
	            return calcularMediaAritmetica(numeros);
	        case 2:
	            return calcularMediaGeometrica(numeros);
	        case 3:
	            return calcularMediaHarmonica(numeros);
	       default:
	    	   return calcularMediaHarmonica(numeros);
	    }
	}
	
	private double calcularMediaAritmetica(double[] numeros) {
	    double soma = 0.0;
	    
	    for(double numero : numeros) {
	        soma += numero;
	    }
	    
	    return soma / numeros.length;
	}
	
	private double calcularMediaGeometrica(double[] numeros) {
	    double produto = 1.0;
	    
	    for(double numero : numeros) {
	        produto *= numero;
	    }
	    
	    return Math.pow(produto, 1.0 / numeros.length);
	}
	
	private double calcularMediaHarmonica(double[] numeros) {
	    double soma = 0.0;
	    
	    for(double numero : numeros) {
	        soma += 1.0 / numero;
	    }
	    
	    return numeros.length / soma;
	}
}