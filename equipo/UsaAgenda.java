package equipo;
/*
 * Asignatura: POO (Programación Orientada a Objetos)
 * Unidad 3: Proyecto en equipo
 * Clase: UsaAgenda
 * Docente: María Lucía Barrón Estrada
 * -- Integrantes: -- 
 * Luis Angel Vea Chairez 25171325
 * Diego Antonio López Olivas 25171090
 * Didier Montoya Samaniego 25170896
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class UsaAgenda {
	// Como se necesita crear 2 veces teléfono es mejor crear un método para reutilizar el código
	// este método se usa en las opciones 5 y 7
	public static Telefono crearTelefono(Scanner sc) {
		String prefijo, numeroTel;
		char tipo;
		Pattern regexTipo = Pattern.compile("^[mf]$");
		Pattern regexPrefijo = Pattern.compile("^[0-9]{2}$");
		Pattern regexNumeroTel = Pattern.compile("^[0-9]{10}$");
		
		//Validación del tipo de teléfono
		System.out.println("-- Ingrese el tipo de teléfono: Móvil (m) / Fijo (f) --");
		tipo = sc.next().toLowerCase().charAt(0);
		while (!regexTipo.matcher(String.valueOf(tipo)).matches()) { 
			System.out.println("-- Error: Ingrese un tipo válido (m) o (f)");
			tipo = sc.next().toLowerCase().charAt(0); 
		}
		sc.nextLine();
		
		//Validación del prefijo
		System.out.println("-- Ingrese el prefijo (2 dígitos) --");
		prefijo = sc.nextLine();
		while (!regexPrefijo.matcher(prefijo).matches()) {
			System.out.println("-- Error: Ingrese un prefijo de 2 dígitos --"); 
			prefijo = sc.nextLine();
		}
		
		//Validación del número de teléfono
		System.out.println("-- Ingrese el número de teléfono (10 dígitos) --");
		numeroTel = sc.nextLine();
		while (!regexNumeroTel.matcher(numeroTel).matches()) {
			System.out.println("-- Error: Ingrese un número de 10 dígitos --"); 
			numeroTel = sc.nextLine();
		}
		return new Telefono(tipo, prefijo, numeroTel);
	}
	// Como se usa varias veces este trozo de código (exactamente igual) se hizo mejor una función
	// para ahorrar líneas de código
	public static String revisarCorreo(Agenda miAgenda, String correoInicial, Scanner sc) {
		String correo = correoInicial;
		boolean coincide = miAgenda.coincideCorreo(correo);
		boolean verificar = Contacto.validarCorreo(correo);
		// Valida que el correo esté en un formato correcto o que no esté en otro contacto
		while(!verificar || coincide) {
			if (!verificar) {						
				System.out.println("-- Error: Ingresó un correo con un formato incorrecto --");
			}
			if (coincide) {
				System.out.println("-- Error: El correo ingresado ya pertenece a otro contacto --");
			}
			System.out.println("-- Ingrese nuevamente el correo --");
			correo = sc.nextLine();
			coincide = miAgenda.coincideCorreo(correo);
			verificar = Contacto.validarCorreo(correo);					
		}
		return correo;
	}
	
	public static void main(String[] args) {
		// Expresiones regulares
		// Tipo de teléfono (móvil o fijo)
		Pattern regexTipoTelefono = Pattern.compile("^[mf]$");
		// Si o No
		Pattern regexDecision = Pattern.compile("^[SN]$");		

		Scanner sc = new Scanner(System.in);
		// Valores iniciales para la agenda
		Telefono t1 = new Telefono('m', "52", "6671257890");
		Telefono t2 = new Telefono('f', "52", "6516513187");
		Contacto c1 = new Contacto("Alejandra", "Pérez", "ale", 'M', "ale@gmail.com", new ArrayList<Telefono>(List.of(t1, t2)));
		Telefono t3 = new Telefono('m', "52", "1651315848");
		Contacto c2 = new Contacto("Pepe", "Ernesto", "peps", 'H', "pepe@gmail.com", new ArrayList<Telefono>(List.of(t3)));
		Telefono t4 = new Telefono('m', "52", "6721221165");
		Telefono t5 = new Telefono('f', "64", "6675211478");
		Contacto c3 = new Contacto("Diego", "Lopez Olivas", "Ego", 'h', "dgo_antonio@gmail.com", new ArrayList<Telefono>(List.of(t4, t5)));
		
		// Dejar vacíos los paréntesis finales, es de prueba
		ArrayList<Contacto> datosPredeterminados = new ArrayList<>(List.of(c1, c2, c3));
		Agenda miAgenda = new Agenda(datosPredeterminados);
		
		// Como inicia con datos, esto es para que inicie ordenada
		miAgenda.ordenarAgenda();
		
		// Para que el programa permita hacer múltiples elecciones
		while (true) {			
			int eleccion;
			char tipo, sexo;
			String alias, correo, nombre, apellidos;
			
			// Se muestra el menú
			System.out.println("--- Bienvenido a las operaciones disponibles. Elige una: ---"
					+ "\n1.- Imprimir todos los datos de la Agenda"
					+ "\n2.- Imprimir un listado de contactos por tipo de teléfono"
					+ "\n3.- Agregar una persona a la agenda"
					+ "\n4.- Cambiar el correo de una persona"
					+ "\n5.- Agregar un teléfono a una persona"
					+ "\n6.- Eliminar un contacto"
					+ "\n7.- Eliminar el teléfono de un contacto"
					+ "\n8.- Consultar una persona (por nombre o por alias)"
					+ "\n9.- Salir");
			
			// Sólo se avanza si el usuario ingresó un número
			while (!sc.hasNextInt()) {sc.next();}
			eleccion = sc.nextInt();
			sc.nextLine();
			
			// Opción 1: Imprimir todos los datos de la Agenda
			if (eleccion == 1) {
				System.out.println(miAgenda);
				System.out.println();
			// Opción 2: Imprimir un listado de contactos por tipo de teléfono
			} else if (eleccion == 2) {
				System.out.println("Escoja el tipo de teléfono: Móvil (m)/ Fijo (f)");
				tipo = sc.next().toLowerCase().charAt(0);
				
				//Validación del tipo de teléfono
				while (!regexTipoTelefono.matcher(String.valueOf(tipo)).matches()) { 
					tipo = sc.next().toLowerCase().charAt(0); 
				}
				
				miAgenda.imprimirListadoDeContactosPorTipoDeTelefono(tipo);
				System.out.println();
			// Opción 3: Agregar una persona a la agenda
			} else if (eleccion == 3) {
				System.out.println("-- Ingrese su nombre --");
				nombre = sc.nextLine();
				
				System.out.println("-- Ingrese sus apellidos --");
				apellidos = sc.nextLine();
				
				System.out.println("-- Ingrese su alias --");
				alias = sc.nextLine();
				
				// Si ya existe el alias entonces se vuelve a pedir que se ingrese
				while(miAgenda.validarAlias(alias)) { 
					System.out.println("-- Error: El alias ya está registrado en otro contacto, ingrese uno nuevo --"); 
					alias = sc.nextLine(); 
				}
				
				System.out.println("-- Ingrese su sexo (H) o (M) --");
				sexo = sc.nextLine().toUpperCase().charAt(0);
				
				// Valida que el sexo sea de un caracter válido (H o M)
				while(!(Persona.validarSexo(sexo))) {
					System.out.println("-- Error: Ingresó un sexo incorrecto, elija (H) o (M) --");
					sexo = sc.nextLine().toUpperCase().charAt(0);
				}
				
				System.out.println("-- Ingrese su correo --");
				correo = sc.nextLine();
				
				// Se verifica que el correo esté bien (no sea repetido y cumpla con
				// la estructura correcta)
				correo = revisarCorreo(miAgenda, correo, sc);
				
				ArrayList<Telefono> telefonos = new ArrayList<>();
				Telefono t = crearTelefono(sc);				
				telefonos.add(t);
				
				System.out.println("-- ¿Desea agregar más teléfonos? (S)/(N)--");
				String op = sc.nextLine().toUpperCase();
				
				// Valida que el usuario ingrese una decisión correcta (S o N), si no, no avanza
				// el programa
				while (!regexDecision.matcher(op).matches()) {
					System.out.println("Ingrese un valor correcto (S)/(N)");
					op = sc.nextLine().toUpperCase();
				}
			
				// Para continuar agregando teléfonos hasta que el usuario se detenga
				while(op.equals("S")) {
					Telefono tAux = crearTelefono(sc);
					telefonos.add(tAux);
					System.out.println("-- ¿Desea agregar más teléfonos? (S)/(N) --");
					op = sc.nextLine().toUpperCase();
				}
				
				// Se crea el contacto
				Contacto c = new Contacto(nombre, apellidos, alias, sexo, correo, telefonos);
				
				// Se agrega el contacto
				miAgenda.agregarContacto(c);
				System.out.println();
			// Opción 4: Cambiar el correo a una persona
			} else if (eleccion == 4) {
				// Se consiguen los datos
				System.out.println("-- Ingrese el nombre del contacto --");
				nombre = sc.nextLine();
				
				// Valida el nombre hasta que haya un nombre válido
				while (!miAgenda.validarNombre(nombre)) { 
					System.out.println("-- Error: No se encontró ningún contacto con el nombre ingresado --"); 
					nombre = sc.nextLine(); 
				}
				
				System.out.println("-- Ingrese el alias del contacto --");
				alias = sc.nextLine();
				
				while (!miAgenda.validarAlias(alias)) { 
					System.out.println("-- Error: No se encontró ningún contacto con el alias ingresado --"); 
					alias = sc.nextLine(); 
				}
				
				System.out.println("-- Ingresa el nuevo correo del contacto --");
				correo = sc.nextLine();
		
				// Se verifica que el correo esté bien (no sea repetido y cumpla con
				// la estructura correcta)
				correo = revisarCorreo(miAgenda, correo, sc);
				
				// Se cambia el correo
				miAgenda.cambiarCorreo(nombre, alias, correo);
				System.out.println();
			//Agregar un teléfono a una persona
			} else if (eleccion == 5) {
				// Se obtiene de forma aparte el alias
				System.out.println("-- Ingrese el alias del contacto --");
				alias = sc.nextLine();
				
				while (!miAgenda.validarAlias(alias)) { 
					System.out.println("-- Error: No se encontró ningún contacto con el alias ingresado --"); 
					System.out.println("-- Ingrese nuevamente el alias --");
					alias = sc.nextLine(); 
				}
				
				// Se consigue el teléfono y se crea
				Telefono t = crearTelefono(sc);
				miAgenda.agregarTelefonoAContacto(alias,t);
				System.out.println();
			// Opción 6: Eliminar un contacto
			} else if (eleccion == 6) {
				// Obteniendo los datos a buscar
				System.out.println("-- Ingrese el correo del usuario a eliminar --");
				correo = sc.nextLine();
				
				// Si el correo no es válido se repite hasta que lo sea
				while (!(Contacto.validarCorreo(correo))) {
					System.out.println("-- Ingrese un correo válido --");
					correo = sc.nextLine();
				}
				
				// Se elimina el contacto
				miAgenda.eliminarContacto(correo);
				System.out.println();
			// Opción 7: Eliminar el teléfono de un contacto
			} else if (eleccion == 7) {
				// Se obtiene el alias
				System.out.println("-- Ingrese el alias del contacto --");
				alias = sc.nextLine();
				
				while (!miAgenda.validarAlias(alias)) { 
					System.out.println("-- Error: No se encontró ningún contacto con el alias ingresado --"); 
					alias = sc.nextLine(); 
				}
				
				// Se obtiene el teléfono y se elimina el teléfono del contacto
				Telefono t = crearTelefono(sc);
				miAgenda.eliminarTelefonoDeContacto(alias, t.getTipo(), t);
				System.out.println();
			// Opción 8: Consultar una persona (por nombre o por alias)
			} else if (eleccion == 8) {
				System.out.println("¿Desea buscar por alias o por nombre?"
						+ "\n1.- Alias"
						+ "\n2.- Nombre");
				int op = sc.nextInt();
				// Lee el buffer del \n
				sc.nextLine();
				
				// Caso de consulta por alias
				if (op == 1) {
					System.out.println("-- Ingrese el alias del contacto --");
					alias = sc.nextLine();
					
					// Valida el alias
					while (!miAgenda.validarAlias(alias)) { 
						System.out.println("-- Error: No se encontró ningún contacto con el alias ingresado --"); 
						alias = sc.nextLine(); 
					}
					
					miAgenda.consultarPersona(alias);
				// Caso de consulta por nombre
				} else if (op == 2) {
					System.out.println("-- Ingrese el nombre del contacto --");
					nombre = sc.nextLine();
					
					// Valida el nombre
					while (!miAgenda.validarNombre(nombre)) { 
						System.out.println("-- Error: No se encontró ningún contacto con el nombre ingresado --"); 
						nombre = sc.nextLine(); 
					}
					
					miAgenda.consultarPersona(nombre);
				// Caso default en que el usuario no elige ninguna opción válida
				} else {
					System.out.println("Ingrese una opción válida");
				}
				
				System.out.println();
			// Opción 9: Salir
			} else if (eleccion == 9) {
				break;
			//Entrada inválida
			} else {
				System.out.println("-- Error: Elige una opción válida --");
			}
		}
		System.out.println("-- Programa terminado --");
	}
}
