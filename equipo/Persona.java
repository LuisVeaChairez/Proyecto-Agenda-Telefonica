package equipo;

import java.util.regex.Pattern;
/*
 * Asignatura: POO (Programación Orientada a Objetos)
 * Unidad 3: Proyecto en equipo
 * Clase: Persona
 * Docente: María Lucía Barrón Estrada
 * -- Integrantes: -- 
 * Luis Angel Vea Chairez 25171325
 * Diego Antonio López Olivas 25171090
 * Didier Montoya Samaniego 25170896
 */
public class Persona {
	//Atributos
	protected String nombre, apellidos, alias;
	protected char sexo;
	// Estático porque no necesita ser creado con cada objeto, no cambia
	// Expresión regular: sólo 1 caracter que sea H o M 
	private static final Pattern regexSexo = Pattern.compile("^[HM]$");
	
	//Constructor
	public Persona(String nombre, String apellidos, String alias, char sexo) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.alias = alias;
		setSexo(sexo);
	}
	//Método auxiliar para validar el sexo
	// Es estática porque no se necesita que sea única por objeto
	public static boolean validarSexo(char sexo) {
		return (regexSexo.matcher(String.valueOf(sexo)).matches());
	}
	//Getters y setters
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public char getSexo() {
		return sexo;
	}
	public void setSexo(char sexo) {
		this.sexo = Character.toUpperCase(sexo);
	}
	
	//Método toString
	public String toString() {
		return String.format("Nombre: %s %s | Alias: %s | Sexo: %c ", nombre, apellidos, alias, sexo);
	}
}
