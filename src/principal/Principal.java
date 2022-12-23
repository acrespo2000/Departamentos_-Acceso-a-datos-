package principal;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.ParseException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;


public class Principal {
   
    private static boolean Comprobarid(int id) throws IOException{
        File fichero=new File(".\\departamentos.dat");
        RandomAccessFile file = new RandomAccessFile(fichero,"r");
        boolean valido=false;
        if(id<=0 ){
            valido=false;
        }else{
            int idViejo;
            for(int j=0; j<file.length(); j=j+205){
                file.seek(j);                                 //apuntamos a la posicion correcta del fichero
                idViejo = file.readInt();                              //obtenemos id del empleado
                if(id==idViejo){
                    valido=false; 
                    break; 
                }else{
                    valido=true; 
                }
            }
            file.close();
        }
        return valido;
    }

    private static boolean existeId(int id) throws FileNotFoundException, IOException{
        File fichero=new File(".\\departamentos.dat");
        RandomAccessFile file = new RandomAccessFile(fichero,"r");
        boolean valido=false;
        if(id<=0){
            valido=false;
        }else{
            int idViejo;
            for(int j=0; j<file.length(); j=j+205){
                file.seek(j);                                 //apuntamos a la posicion correcta del fichero
                idViejo = file.readInt();                              //obtenemos id del empleado
                if(id==idViejo){
                    valido=true; 
                    break; 
                }else{
                    valido=false; 
                } 
            }
            file.close();
        } 
        return valido;
    }
    
    private static int editar() throws IOException{
        Scanner teclado = new Scanner(System.in);
        
        int idEditar = 0;
        String idConsulta = null;
        do{
            System.out.print("Escribe la id del departamento que quieres editar: ");   //escribimos la id
            idConsulta=teclado.nextLine();
            
            System.out.println("");
            if (idConsulta.isEmpty()) {
                System.out.println("No has escrito nada, escribe una id. ");           //si está vacia no es válido
            } else {
                boolean cValido = false;
                char c[] = new char[50];
                for (int i = 0; i < idConsulta.length(); i++) {

                    c[i] = idConsulta.charAt(i);
                    if (c[i] < 48 || c[i] > 57) {
                        System.out.println("No es valida, escriba un numero");   // si no son numeros no es válido
                        cValido = false;
                        break;
                    } else {
                         try {
                            cValido = true;
                        } catch (NumberFormatException numberFormatException) {
                            System.out.println("La opcion no es valida, escriba un numero");
                            cValido=false;
                        }
                        
                    }
                }
                if (cValido) {
                    idEditar = Integer.parseInt(idConsulta);
                    existeId(idEditar);
                    if (!existeId(idEditar)) {
                        System.out.println("La id no es valida o no existe");       //si no existe en el fchero no es valido
                    }
                    System.out.println("");
                }
            }
        }while(!existeId(idEditar));
        
        return idEditar;
    }
    
    private static void añadir() throws FileNotFoundException, IOException{
        //pedimos el departamento que sera un int mayor a 0, verificar si este departemento ya existe
        File fichero=new File(".\\departamentos.dat");
        if(!fichero.exists()){
            fichero.createNewFile();
        }
        //declara el fichero de acceso aleatorio
        RandomAccessFile file = new RandomAccessFile(fichero,"rw");
        Scanner teclado = new Scanner(System.in);
        
        int idNuevo=0;
        String idProceso;
        boolean Idvalido=false;
        do{
            System.out.print("Escribe la id del departamento: ");   //escribimos la id
            idProceso=teclado.nextLine();
            System.out.println("");
            if(idProceso.isEmpty()){
                Idvalido=false;
            }else{
                boolean cValido=false;
                for(int i=0; i<idProceso.length(); i++){
                    char c;
                    c=idProceso.charAt(i);
                    if(c<48 || c>57){
                        System.out.println("La id no es valida, escriba un numero");
                        break;
                    }else{
                        cValido=true;
                    }
                }  
                if(cValido){
                    idNuevo = Integer.parseInt(idProceso);
                    Comprobarid(idNuevo);
                    if (!Comprobarid(idNuevo)) {
                        System.out.println("La id no es valida o ya existe");
                        Idvalido = false;
                    } else {
                        Idvalido = true;
                    }
                    System.out.println("");
                }
            }
        }while(!Idvalido);
        
            
        String nombreNuevo;                                       //escribimos el nombre del departamento
        do {
            System.out.print("Escribe el nombre del departamento: ");
            nombreNuevo = teclado.nextLine();
            if (nombreNuevo.length() > 50) {
                System.out.println("Su nombre es muy largo, escriba otro mas corto");
            } else if (nombreNuevo.length() <= 0) {
                System.out.println("No has escrito nombre de departamento");
            }
        } while (nombreNuevo.length() > 50 || nombreNuevo.length() <= 0);
        System.out.println("");

        
        String byteProceso;
        byte nEmpleadosNuevo = 0;                               
        boolean byteValido=false;
        do{
            System.out.print("Escribe el numero de empleados  del departamento: ");   
            byteProceso=teclado.nextLine();
            System.out.println("");
            if(byteProceso.isEmpty()){
                Idvalido=false;
            }else{
                boolean cValido=false;
                for(int i=0; i<byteProceso.length(); i++){
                    char c;
                    c=byteProceso.charAt(i);
                    if(c<48 || c>57){
                        System.out.println("La id no es valida, escriba un numero");
                        break;
                    }else{
                        cValido=true;
                    }
                }  
                if(cValido){
                    nEmpleadosNuevo = Byte.parseByte(byteProceso);
                    if(nEmpleadosNuevo<0 || nEmpleadosNuevo>127){
                        System.out.println("No es valido el numero");
                        byteValido=false;
                    }else{
                        byteValido=true;
                    }
                    System.out.println("");
                }
            }
        }while(!byteValido);
        

        String localidadNuevo;                                  //escribimos la localidad
        System.out.print("Escribe la localidad  del departamento: ");
        do {
            localidadNuevo = teclado.nextLine();
            if (nombreNuevo.length() > 50) {
                System.out.println("Su nombre es muy largo, escriba otro mas corto");
            } else if (nombreNuevo.length() <= 0) {
                System.out.println("No has escrito nombre de localidad");
            }
        } while (nombreNuevo.length() > 50 || nombreNuevo.length() <= 0);
        System.out.println("");
        
        
        StringBuffer buffer= null;
        try{
            file.seek(idNuevo*205);
            
            file.writeInt(idNuevo);

            buffer = new StringBuffer(nombreNuevo);
            buffer.setLength(50);
            file.writeChars(buffer.toString());

            file.writeByte(nEmpleadosNuevo);

            buffer = new StringBuffer(localidadNuevo);
            buffer.setLength(50);
            file.writeChars(buffer.toString());
         
            file.close();
        }catch(IOException e){
            System.out.println("Ha ocurrido un error");
        }
    }
            
    private static void consultar() throws FileNotFoundException, IOException{
       File fichero=new File(".\\departamentos.dat");
       //declara el fichero de acceso aleatorio
       if(!fichero.exists()){
            fichero.createNewFile();
       }
       RandomAccessFile file = new RandomAccessFile(fichero,"r");
       Scanner teclado = new Scanner(System.in);
       
       int id;
       char nombre[] = new char[50];
       byte nEmpleados;
       char localidad[] = new char[50];
       
       if(file.length()==0){
           System.out.println("No hay departamentos");
       }else{

            int idValida=editar();
           
            file.seek(idValida*205);
            id = file.readInt();
            if(id==0){
                System.out.println("Este departamento no existe");
            }else{
                for (int j = 0; j < nombre.length; j++) {               //recorro uno a uno los caracteres del nombre de departamento
                    nombre[j] = file.readChar();                        //Guardar cada char en el array de char (todo el array será el nombre)
                }
                String nombres = new String(nombre);

                nEmpleados = file.readByte();                           //obtenemos numero de empleados

                for (int j = 0; j < localidad.length; j++) {            //recorro uno a uno los caracteres del nombre de departamento
                    localidad[j] = file.readChar();                     //Guardar cada char en el array de char (todo el array será el nombre)
                }
                String localidades = new String(localidad);

                System.out.printf("ID: %d, Departamento: %s, Numero de empleados: %d, Localidad: %s \n", id, nombres.trim(), nEmpleados, localidades.trim());
            }
           file.close();      
       } 
    }

    private static void editarNombre() throws FileNotFoundException, IOException{
        File fichero=new File(".\\departamentos.dat");
        //declara el fichero de acceso aleatorio
        RandomAccessFile file = new RandomAccessFile(fichero,"rw");
        Scanner teclado = new Scanner(System.in);
        
        int id=editar();    //Llamamos la funicon para aegurarse de que la id es valida

        String nombreNuevo;
        System.out.print("Escribe el nuevo nombre del departamento: ");
        nombreNuevo = teclado.nextLine();
        
        
        StringBuffer buffer= null;
        file.seek(id*205);
        file.skipBytes(4);
        buffer = new StringBuffer(nombreNuevo);
        buffer.setLength(50);
        file.writeChars(buffer.toString());
        file.close();
    }
    
    private static void editarNumero() throws FileNotFoundException, IOException{
        File fichero=new File(".\\departamentos.dat");
        //declara el fichero de acceso aleatorio
        RandomAccessFile file = new RandomAccessFile(fichero,"rw");
        Scanner teclado = new Scanner(System.in);
        
        int id=editar();
        byte nEmpleadosNuevo = 0;
        String nEmpleados = null;
        boolean valido=false;
        do{
            System.out.print("Escribe el nuevo numero de empelados del departamento: ");
            nEmpleados = teclado.nextLine();
            
            if(nEmpleados.isEmpty()){
                System.out.println("Escriba un numero;");
                valido=false;
            }else{
                for (int i = 0; i < nEmpleados.length(); i++) {
                    char c;
                    c = nEmpleados.charAt(i);
                    if (c < 48 || c > 57) {
                        System.out.println("La opcion no es valida, escriba un numero");   // si no son numeros no es válido
                        valido=false;
                        break;
                    } else {
                        try {
                            nEmpleadosNuevo = Byte.parseByte(nEmpleados);
                            valido=true;
                        } catch (NumberFormatException numberFormatException) {
                            System.out.println("La opcion no es valida, escriba un numero");
                            valido=false;
                        }
                    }
                }
            }
        }while(!valido);
        

        file.seek(id*205);
        file.skipBytes(104);
        file.writeByte(nEmpleadosNuevo);
        file.close();
    }
    
    private static void editarLocalidad() throws IOException{
        File fichero=new File(".\\departamentos.dat");
        //declara el fichero de acceso aleatorio
        RandomAccessFile file = new RandomAccessFile(fichero,"rw");
        Scanner teclado = new Scanner(System.in);
        
        int id=editar();
        String localidadNuevo;
        
        System.out.print("Escribe la nueva localidad del departamento: ");
        localidadNuevo = teclado.nextLine();
        
        StringBuffer buffer= null;
        file.seek(id*205);
        file.skipBytes(105);
        buffer = new StringBuffer(localidadNuevo);
        buffer.setLength(50);
        file.writeChars(buffer.toString());
        file.close();
    }
    
    private static void eliminar() throws FileNotFoundException, IOException{
       File fichero=new File(".\\departamentos.dat");
        //declara el fichero de acceso aleatorio
        RandomAccessFile file = new RandomAccessFile(fichero,"rw");
        Scanner teclado = new Scanner(System.in);
        
        int id=editar();
        
        file.seek(id*205);
        file.writeInt(0);
        file.close();
    }
    
    private static void exportar() throws FileNotFoundException, IOException, ParserConfigurationException{
        File fichero = new File(".\\departamentos.dat"); 
        RandomAccessFile file = new RandomAccessFile(fichero, "r");
   
        // Declaración de variables para manejar los datos del fichero.
        int  id, posicion=0;       
        char nombre[] = new char[50], aux;
        char localidad[]=new char[50], aux2;
        byte nEmpleados;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        
        try
        {
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();
            Document document = implementation.createDocument(null, "Departamentos", null);
            document.setXmlVersion("1.0"); 

            for(;;) {
                file.seek(posicion);
                // Leemos el id
                id=file.readInt();  	  
                // Bucle for para recorrer el array de "char" de 10 posiciones. Vamos obteniendo cada letra almacenandola secuencialmente en el array.
                for (int i = 0; i < nombre.length; i++) 
                {
                    aux = file.readChar();
                    nombre[i] = aux;    
                }
                String nombres = new String(nombre);
                // El siguiente dato es el departamento. Lo leemos.
                nEmpleados= file.readByte();
                // El último dato es el salario. Lo leemos
                for (int i = 0; i < localidad.length; i++) 
                {
                    aux2 = file.readChar();
                    localidad[i] = aux2;    
                }
                String localidades = new String(localidad); 
                if(id>0){ 
                    // Creamos el que será el elemento raíz de nuestro archivo XML (El nodo "Empleado")
                    Element raiz = document.createElement("departamentos"); 
                    // "Pegamos" dicho nodo "empleado" a la raíz de nuestro objeto "document"
                    document.getDocumentElement().appendChild(raiz); 
        
                    // añadir ID                       
                    crearElemento("id",Integer.toString(id), raiz, document); 
                    // añadir Apellido
                    crearElemento("dep",nombres.trim(), raiz, document); 
                    // añadir Departamento
                    crearElemento("empelados",Byte.toString(nEmpleados), raiz, document); 
                    // añadir Salario
                    crearElemento("localidad",localidades.trim(), raiz, document); 
                }
                posicion = posicion + 205;
                if (file.getFilePointer() == file.length()) break;
                
            } // FIN DEL BUCLE QUE RECORRE EL FICHERO

            System.out.println("Se ha exportado correctamente");
            Source source = new DOMSource(document); 
            Result result = new StreamResult(new java.io.File(".\\ficheroPruebaXML.xml"));   
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);
        }
        catch(Exception e)
        {
            System.err.println("Error: "+e); 
        }
        // Hemos terminado. No nos olvidemos de cerrar el fichero.
        file.close();
    }
    
    static void  crearElemento(String datoEmple, String valor, Element raiz, Document document){
        Element elem = document.createElement(datoEmple); 
        Text text = document.createTextNode(valor);
        raiz.appendChild(elem); 
        elem.appendChild(text);
    }

    public static void main(String[] args) throws IOException, FileNotFoundException, ParserConfigurationException {
    boolean salir;
    salir = false;  
    

    String opcion;
    int opcionValida = 0;
    do{
        do{
            System.out.println("=====PROGRAMA DE DEPARTAMENTOS=====");
            System.out.println("Elija una opcion: ");
            System.out.println("1) Crear departamento");
            System.out.println("2) Consultar departamento");
            System.out.println("3) Modificar nombre de un departamento");
            System.out.println("4) Modificar numero de empleados de un departamento");
            System.out.println("5) Modificar la localidad de un departamento");
            System.out.println("6) Eliminar departamento");
            System.out.println("7) Exportar base de datos a fichero xml");
            System.out.println("8) Salir");
            System.out.print("->");
            Scanner teclado = new Scanner(System.in);
            opcion = teclado.nextLine();
            
            if (opcion.isEmpty()) {
                System.out.println("No has escrito nada, escribe una opcion. ");           //si está vacia no es válido
            } else {
                for (int i = 0; i < opcion.length(); i++) {
                    char c;
                    c = opcion.charAt(i);
                    if (c < 48 || c > 57) {
                        System.out.println("La opcion no es valida, escriba un numero");   // si no son numeros no es válido
                        break;
                    } else {
                        try {
                            opcionValida = Integer.parseInt(opcion);
                        } catch (NumberFormatException numberFormatException) {
                            System.out.println("La opcion no es valida, escriba un numero");
                        }
                    }
                }
            }
            System.out.println("");
            System.out.println("");
        }while(opcionValida<1 || opcionValida>8);

        switch(opcionValida){
            case 1: //HECHO
                System.out.println("Crear departamento");
                añadir();
                System.out.println("");
                break;
            case 2: //HECHO
                System.out.println("Consultar departamento");
                consultar();
                System.out.println("");
                break;
            case 3: //HECHO
                System.out.println("Modificar nombre de un departamento");
                editarNombre();
                System.out.println("");
                break;
            case 4: //HECHO
                System.out.println("Modificar numero de empleados de un departamento");
                editarNumero();
                System.out.println("");
                break;
            case 5: //HECHO
                System.out.println("Modificar la localidad de un departamento");
                editarLocalidad();
                System.out.println("");
                break;
            case 6: //HECHO
                System.out.println("Eliminar departamento");
                eliminar();
                System.out.println("");
                break;
            case 7: //HECHO
                System.out.println("Exportar base de datos a fichero xml");
                exportar();
                System.out.println("");
                break; 
            case 8: //HECHO
                salir=true;
                System.out.println("SALIENDO DEL PROGRAMA");
                break;
        }
    }while(!salir); 
    }
}