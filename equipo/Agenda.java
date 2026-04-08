package equipo;
/*
 * Asignatura: POO (Programación Orientada a Objetos)
 * Unidad 3: Proyecto en equipo
 * Clase: Agenda
 * Docente: María Lucía Barrón Estrada
 * -- Integrantes: -- 
 * Luis Angel Vea Chairez 25171325
 * Diego Antonio López Olivas 25171090
 * Didier Montoya Samaniego 25170896
 */
import java.util.ArrayList;

public class Agenda {
	private ArrayList<Contacto> agenda;
	//Constructor para los datos iniciales / predeterminados de la agenda
	public Agenda(ArrayList<Contacto> listaPredeterminada) {
		this.agenda = listaPredeterminada;
	}
	//b) Método para obtener el listado de contactos por tipo de teléfono
	public void imprimirListadoDeContactosPorTipoDeTelefono(char tipoTel) {
		for (Contacto c : agenda) {
			boolean contactoImpreso = false;	
			//Recorre todos los telefonos del contacto
			for (Telefono tel : c.getTelefonos()) {
				if (tel.getTipo() == tipoTel) {
					//Si el encabezado del contacto no ha sido impreso se imprime
					if (!contactoImpreso) {
						System.out.println("\n--- Contacto: " + c.getNombre() + " " + c.getApellidos() + " ---");
						contactoImpreso = true;
					}
					//Si el tipo de teléfono coincide se imprime ese teléfono
					System.out.println("Teléfono: " + tel.toString());
				}
			}
		}
	}
	// Método auxiliar para ordenar la agenda (puntos extras)
	public void ordenarAgenda() {
		// Ordena la agenda comparando los alias, si la comparación es positiva
		// significa que c2 va antes, si es negativa, c1 va antes
		// (c1,c2)-> es el comparador
		agenda.sort((c1, c2) -> c1.getNombre().compareToIgnoreCase(c2.getNombre()));
	}
	//c) Método para agregar un Contacto a la agenda
	public void agregarContacto(Contacto nuevoContacto) {
	    if (nuevoContacto != null) {
	        agenda.add(nuevoContacto);
	        System.out.println("-- Contacto agregado correctamente --");
	    } else {
	        System.out.println("-- No se pudo agregar el contacto --");
	    }
	    ordenarAgenda();
	} 
	
	//d) Método para cambiar el correo de un contacto
	public void cambiarCorreo(String nombre, String alias, String nuevoCorreo) {
	    boolean encontrado = false;
	    // Primero se busca el contacto y al encontrarse se le cambia el correo
	    for (Contacto c : agenda) {
	        if (c.getNombre().equalsIgnoreCase(nombre) && 
	            c.getAlias().equalsIgnoreCase(alias)) {
	            c.setCorreo(nuevoCorreo);
	            System.out.println("-- Correo modificado correctamente --");
	            encontrado = true;
	            break;
	        }
	    }
	    if (!encontrado) {
	        System.out.println("-- Error: No se encontró el contacto --");
	    }
	}
	//Método auxiliar para validar un alias dentro de la lista de contactos
	public boolean validarAlias(String alias) {
		for (Contacto c : agenda) {
			if (c.getAlias().equalsIgnoreCase(alias.trim())){
				return true;
			}
		}
		return false;
	}
	//Método auxiliar para validar un nombre dentro de la lista de contactos
	public boolean validarNombre(String nombre) {
		for (Contacto c : agenda) {
			if (c.getNombre().equalsIgnoreCase(nombre.trim())){
				return true;
			}
		}
		return false;
	}
	// Método auxiliar que ayuda a verificar que un correo no aparezca en otro contacto
	public boolean coincideCorreo(String correo) {
		// Regresa si el correo ya pertenece a otro contacto
		boolean coincide = false;
		for (Contacto c : agenda) {
			if (correo.equals(c.getCorreo())) {
				coincide = true;
				break;
			}
		}
		return coincide;
	}
	//e) Método para agregar un teléfono: valida primero que la entrada no venga completamente vacía, de lo contrario no se guarda.
	public void agregarTelefonoAContacto(String alias, Telefono t) {
		boolean encontrado = false;
		for (Contacto c: agenda) {
			if (c.getAlias().equalsIgnoreCase(alias.trim())) {
				c.getTelefonos().add(t);
				System.out.println("-- Teléfono agregado con éxito --");
				encontrado = true;
				break;
			}
		}
		if (!encontrado) {
			System.out.println("-- Error: No se pudo guardar el teléfono en el contacto --");
		}
	} 
		
	//f) Método para eliminar un contacto de la agenda
	public void eliminarContacto(String correo) {
		int index = 0;
		boolean encontrado = false;
		for (Contacto c : agenda) {
			// Se busca el correo iterando en la agenda y si se encuentra 
			// se termina el ciclo comparando el correo y el alias
			if (correo.equals(c.getCorreo())) {
				encontrado = true;
				break;
			}
			index++;
		}
		if (encontrado) {
			// Se elimina de la agenda usando el método remove de los ArrayList
			// sólo si se encuentra
			agenda.remove(index);
			System.out.println("-- Contacto eliminado --");				
		} else {
			System.out.println("-- Error: Contacto no encontrado --");
		}
	}
	
	//g) Método para eliminar los teléfonos de un contacto
	public void eliminarTelefonoDeContacto(String alias, char tipo, Telefono t1) {
		// Dos variables Contacto y Telefono para asesorarnos de cuando abortar el método
		Contacto contactoEncontrado = null;
		Telefono telefonoAEliminar = null;
		for (Contacto c : agenda) {
			// Si el contacto es encontrado se guarda en una variable 
			if (alias.equalsIgnoreCase(c.getAlias())) {
				contactoEncontrado = c;
				break;
			}
		}
		//Si el contacto no se encuentra en la lista se aborta
		if (contactoEncontrado == null) {
			System.out.println("-- Error: Contacto no encontrado --");
			return;
		}
		//Una vez que sabemos que el contacto existe buscamos su teléfono en la lista
		for (Telefono t : contactoEncontrado.getTelefonos()) {
			//De encontrar el teléfono en la lista se guarda en una variable
			if (t1.getNumTel().equals(t.getNumTel()) && t1.getPrefijo().equals(t.getPrefijo())) {
				telefonoAEliminar = t;
				break;
			} 
		}
		//Si se encontró el teléfono se elimina de ese contacto
		if (telefonoAEliminar != null) {
			//Se verifica que el telefono a eliminar sea del tipo que especificó el usuario
			if (telefonoAEliminar.getTipo() == tipo) {
				contactoEncontrado.getTelefonos().remove(telefonoAEliminar);
				System.out.println("-- Teléfono eliminado con éxito --");
			} else {
				System.out.println("-- Error: El teléfono existe pero no es tipo [" +tipo+ "]");
			}
		} else { //De lo contrario, se le hace saber al usuario que no existe ese teléfono
			System.out.println("-- Error: Este teléfono no existe para este contacto --");
		}
	}
	//h) Método para consultar a una persona y obtener sus datos
	public void consultarPersona(String dato) {
	    boolean encontrado = false;
	    // Se busca y se imprime el toString del contacto, el cual incluye
	    // el toString de persona y el toString de sus teléfonos
	    for (Contacto c : agenda) {
	        if (c.getNombre().equalsIgnoreCase(dato) || 
	            c.getAlias().equalsIgnoreCase(dato)) {
	            System.out.println(c.toString());
	            encontrado = true;
	            break;
	        }
	    }
	    if (!encontrado) {
	        System.out.println("Persona no encontrada.");
	    }
	}
	// Método toString
	public String toString() {
		// StringBuilder usado para no crear muchisimos Strings
		StringBuilder sb = new StringBuilder();
		for (Contacto c : agenda) {
			sb.append(c.toString());
			sb.append("\n");
		}
		// Elimina el último salto de linea que se agrega de más
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}
}
