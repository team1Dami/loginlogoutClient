package Util;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Util {

	public static String introducirCadena(){
		 String cadena="";
		 InputStreamReader entrada =new InputStreamReader(System.in);
		 BufferedReader teclado= new BufferedReader(entrada);
		 try{
		 cadena=teclado.readLine();
		 }
		 catch(IOException er){
		 System.out.println("error al introducir datos");
		 System.exit(0);
		 }
		 return cadena;
		}
	
	public static String introducirCadena(String mensaje){
		 String cadena="";
		 InputStreamReader entrada =new InputStreamReader(System.in);
		 BufferedReader teclado= new BufferedReader(entrada);
		 
		 System.out.println(mensaje);
		 try{
			 cadena=teclado.readLine();
		 }
		 catch(IOException er){
			 System.out.println("error al introducir datos");
			 System.exit(0);
		 }
		 return cadena;
		}
	
	public static String introducirCadena(int x){
		 String cadena=null;
		boolean blnError;
		 do {
			 blnError=false;
			 cadena=introducirCadena();
			 if(cadena.length()>x) {
				 System.out.println("ERROR. Longitud superior a la permitida. Introduzca de nuevo: ");
				 blnError=true;
			 }
		 }while(blnError);
		
		 return cadena;
		}
	
	public static  int leerInt() {
		boolean blnError;
		int num=0;
		do {
			blnError=false;
			try {
				num=Integer.parseInt(introducirCadena());
			}
			catch(NumberFormatException e) {
				System.out.println("ERROR. El dato introducido no es num�rico, introduce de nuevo: ");
				blnError=true;
			}
		}while(blnError);
		
		return num;
	}
	
	public static  int leerInt(String mensaje) {
		boolean blnError;
		int num=0;
			System.out.println(mensaje);
		do {
			blnError=false;
			try {
				num=Integer.parseInt(introducirCadena());
			}
			catch(NumberFormatException e) {
				System.out.println("ERROR. El dato introducido no es num�rico, introduce de nuevo: ");
				blnError=true;
			}
		}while(blnError);
		
		return num;
	}
	
	public static  int leerInt(int x,int y) {
		boolean blnError;
		int num=0;
		
		do {
			blnError=false;
			try {
				num=Integer.parseInt(introducirCadena());
			}
			catch(NumberFormatException e) {
				System.out.println("ERROR. El dato introducido no es num�rico, introduce de nuevo: ");
				blnError=true;
				num=x;
			}
			if(num<x || num>y) {
				System.out.println("ERROR. Dato fuera de rango. ");
			}
		}while(blnError);
		
		return num;
	}
	
	public static  int leerInt(String mensaje, int x,int y) {
		boolean blnError;
		int num=0;
			System.out.println(mensaje);
		do {
			blnError=false;
			try {
				num=Integer.parseInt(introducirCadena());
			}
			catch(NumberFormatException e) {
				System.out.println("ERROR. El dato introducido no es num�rico, introduce de nuevo: ");
				blnError=true;
				num=x;
			}
			if(num<x || num>y) {
				System.out.println("ERROR. Dato fuera de rango, introduce de nuevo: ");
			}
		}while(blnError);
		
		return num;
	}
	public static  long leerLong(String mensaje) {
		boolean blnError;
		long num=0;
			System.out.println(mensaje);
		do {
			blnError=false;
			try {
				num=Long.parseLong(introducirCadena());
			}
			catch(NumberFormatException e) {
				System.out.println("ERROR. El dato introducido no es numérico, introduce de nuevo: ");
				blnError=true;
			}
		}while(blnError);
		
		return num;
	}
	public static  float leerFloat() {
		boolean blnError;
		float num=0;
		do {
			blnError=false;
			try {
				num=Float.parseFloat(introducirCadena());
			}
			catch(NumberFormatException e) {
				System.out.println("ERROR. El dato introducido no es num�rico, introduce de nuevo: ");
				blnError=true;
			}
		}while(blnError);
		
		return num;
	}
	
	public static  float leerFloat(String mensaje) {
		boolean blnError;
		float num=0;
		
			System.out.println(mensaje);
		do {
			blnError=false;
			try {
				num=Float.parseFloat(introducirCadena());
			}
			catch(NumberFormatException e) {
				System.out.println("ERROR. El dato introducido no es num�rico, introduce de nuevo: ");
				blnError=true;
			}
		}while(blnError);
		
		return num;
	}
	
	public static  float leerFloat(float x,float y) {
		boolean blnError;
		float num=0;
		
		do {
			blnError=false;
			try {
				num=Float.parseFloat(introducirCadena());
			}
			catch(NumberFormatException e) {
				System.out.println("ERROR. El dato introducido no es num�rico, introduce de nuevo: ");
				blnError=true;
				num=x;
			}
			if(num<x || num>y) {
				System.out.println("ERROR. Dato fuera de rango, introduce de nuevo: ");
			}
		}while(blnError);
		
		return num;
	}
	
	public static  float leerFloat(String mensaje, float x,float y) {
		boolean blnError;
		float num=0;
			System.out.println(mensaje);
		do {
			blnError=false;
			try {
				num=Float.parseFloat(introducirCadena());
			}
			catch(NumberFormatException e) {
				System.out.println("ERROR. El dato introducido no es num�rico, introduce de nuevo: ");
				blnError=true;
				num=x;
			}
			if(num<x || num>y) {
				System.out.println("ERROR. Dato fuera de rango, introduce de nuevo: ");
			}
		}while(blnError);
		
		return num;
	}
	public static double leerDouble(double x, double y) {
		double num = 0;
		boolean ok;
		do {
			try {
				ok = true;
				num =Double.parseDouble(introducirCadena());

			} catch (NumberFormatException e) {
				System.out.println("Hay que introducir n�meros");
				ok = false;
				num = x;

			}
			if (num < x || num > y) {
				System.out.println("Dato fuera de rango, introduce entre" + x + " y " + y);
				ok = false;
			}
		} while (!ok);
		return num;
	}

	public static double leerDouble() {
		double fNumero = 0;
		boolean ok;
		do {
			try {
				ok = true;
				fNumero = Double.parseDouble(introducirCadena());
			} catch (NumberFormatException e) {
				System.out.println("Error al introducir el n?mero");
				ok = false;
			}
		} while (!ok);
		return fNumero;
	}
	
	public static boolean esBoolean(){
		String respu;
		do{
			respu=introducirCadena().toLowerCase();
		}while(!respu.equals("0") &&!respu.equals("1") && !respu.equals("si") && !respu.equals("no") && !respu.equals("s") && !respu.equals("n") && !respu.equals("true") && !respu.equals("false") );
		if(respu.equals("1")||respu.equals("si")||respu.equals("s")||respu.equals("true")){
			return true;
		}
		else{
			return false;
		}
	}
	public static char leerChar(){
		boolean error=false;
		String letra;
		
		do{
			error=false;
			letra=introducirCadena();
			if(letra.length()!=1){
				System.out.println("Error, introduce un car�cter: ");
				error=true;
			}
			
		}while(error);
		return letra.charAt(0);
	}

	public static char leerChar(String mensaje)
	{
		char letra;
		String frase;
		System.out.println(mensaje);
		do {
			
			frase=introducirCadena();
			if (frase.length()!=1) {
				System.out.println("Error, introduce un �nico car�cter: ");
			}
		}while (frase.length()!=1);
		letra=frase.charAt(0);
		
		return letra;
	}
	
	public static char leerChar (char x, char y)
	{

		char letra;
		String frase;
		x=Character.toUpperCase(x);
		y=Character.toUpperCase(y);
		do{
			do {
				frase=introducirCadena();
				if (frase.length()!=1) {
					System.out.println("Error, introduce un �nico car�cter: ");
				}
			}while (frase.length()!=1);
			letra=frase.charAt(0);
			letra=Character.toUpperCase(letra);
			if(!(letra == x || letra==y))
			{
				System.out.println("Error car�cter no Valido");
			}
		}while (!(letra == x || letra==y));
		
		letra=Character.toUpperCase(letra);
		return letra;		
	}
	
	public static char leerChar (String mensaje,char x, char y)
	{

		char letra;
		String frase;
		System.out.println(mensaje);
		do{
			do {
				frase=introducirCadena();
				if (frase.length()!=1) {
					System.out.println("Error, introduce un �nico car�cter: ");
				}
			}while (frase.length()!=1);
			letra=frase.charAt(0);
			letra=Character.toUpperCase(letra);
			if(!(letra == x || letra==y))
			{
				System.out.println("Error car�cter no Valido");
			}
		}while (!(letra == x || letra==y));
		return letra;		
	}

	public static char leerCharArray(char caracteres[]){
		int i;
		boolean error=false;
		String letra;
		char aux=0;
		
		do{
			error=false;
			letra=introducirCadena();
			if(letra.length()!=1){
				System.out.println("Error, introduce un car�cter: ");
				error=true;
			}
			else{
				aux=letra.charAt(0);
				for(i=0;i<caracteres.length;i++){
					if(Character.toUpperCase(caracteres[i])==Character.toUpperCase(aux)){
						break;
					}
				}
				if(i==caracteres.length){
					error=true;
					System.out.println("Error, el car�cter introducido no es valido. ");
				}
			}
		}while(error);
		return aux;
	}
	public static boolean esPrimo(int numero){
		  int contador = 2;
		  boolean primo=true;
		  for (contador=2;contador<numero;contador++){
			  if (numero%contador==0){
				  primo=false;
				  break;
			  }
		  }
		  if (numero==2)primo=true;
		  return primo;
	}
	
	public static int numAleatorio(int x, int y) {
		int z, aux;
		
		if(x > y) {
			aux = x;
			x = y;
			y = aux;
		}
		
		z = (int)(x + Math.random() * (y - x + 1));
		
		return z;
	}

	public static LocalDate leerFecha() {
		String fechaAux;
		LocalDate fecha = LocalDate.now();
		boolean error;
		DateTimeFormatter formateador=DateTimeFormatter.ofPattern("dd/MM/yyyy");
		do{
	 		error=false;
	  		fechaAux=Util.introducirCadena();
	 		 try{		
	   			fecha=LocalDate.parse(fechaAux, formateador);
	 		 } catch(DateTimeParseException e){
	  	 	error=true;
			System.out.println("Error,Introduce fecha con formato dd/mm/aaaa: ");
	 		 }
		}while (error);
		return fecha;
	}

	public static LocalDate leerFecha(String mensaje) {
		String fechaAux;
		LocalDate fechaNac = LocalDate.now();
		boolean error;
		DateTimeFormatter formateador=DateTimeFormatter.ofPattern("dd/MM/yyyy");
		System.out.println(mensaje);
		do{
	  		error=false;
			fechaAux=Util.introducirCadena();
	  		try{		
	   			fechaNac=LocalDate.parse(fechaAux, formateador);
			} catch(DateTimeParseException e){
	   		error=true;
			System.out.println("Error,Introduce fecha con formato dd/mm/aaaa: ");
			}
		}while (error);
		return fechaNac;
	}

	public static String fechaToString(LocalDate fecha) {
		DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String wfecha;
		
		wfecha = fecha.format(formateador);
		
		return wfecha;
	}
	
	//Devuelve el n�mero de objetos de un fichero
		 public static int calculoFichero(File fich){
		 	int cont=0;
		 	if (fich.exists()){
			 	FileInputStream fis=null;
			 	ObjectInputStream ois=null;
			 	try{
			 		fis=new FileInputStream(fich);
			 		ois=new ObjectInputStream(fis);
		
			 		Object aux=ois.readObject();
		
			 		while (aux!=null){
			 			cont++;
			 			aux=ois.readObject();
			 		}
			 		
		
			 	}catch(EOFException e1){
				//	System.out.println("Has acabado de leer, tienes "+cont+" objetos");
					
			 	}catch (Exception e2){
					 e2.printStackTrace();
			 	}
			 	
			 	
			 	try {
					ois.close();
					fis.close();
				} catch (IOException e) {
					System.out.println("Error al cerrar los flujos");
					
				}
		 	}
		 	return cont;
		 }
}
