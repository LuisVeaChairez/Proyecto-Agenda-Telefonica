package equipo;
import java.util.regex.Pattern;

/*
 * Asignatura: POO (Programación Orientada a Objetos)
 * Unidad 3: Proyecto en equipo
 * Clase: Teléfono
 * Docente: María Lucía Barrón Estrada
 * -- Integrantes: -- 
 * Luis Angel Vea Chairez 25171325
 * Diego Antonio López Olivas 25171090
 * Didier Montoya Samaniego 25170896
 */
public class Telefono {
	//Atributos
	private char tipo;
	private String prefijo, numTel;
	// Expresiones regulares, estáticas porque no necesitan ser exclusivas de un objeto
	// Expresión que pregunta si es móvil (m) o fijo (f)
	private static final Pattern regexTipoTelefono = Pattern.compile("^[mf]$");
	//Que sean sólo dos ({2}) dígitos ([0-9]) de principio (^) a fin ($)
	private static final Pattern regexPrefijo = Pattern.compile("^[0-9]{2}$");
	// Que sean sólo 10 dígitos
	private static final Pattern regexNumTel = Pattern.compile("^[0-9]{10}$");
	
	//Constructor, reutiliza los métodos setters para simplificar código
	public Telefono(char tipo, String prefijo, String numTel) {
		setTipo(tipo);
		setPrefijo(prefijo);
		setNumTel(numTel);
	}
	
	//Getter y setters
	public char getTipo() {
		return tipo;
	}
	//Setter con validación de tipo de teléfono móvil (m) y fijo (f)
	public void setTipo(char tipo) {
		
		if (regexTipoTelefono.matcher(String.valueOf(tipo)).matches()) { 
			this.tipo = tipo;
		}
		else {
			this.tipo = 'X';
		}
	}
	public String getPrefijo() {
		return prefijo;
	}
	//Setter con validación de prefijo (valida que no esté vacío y que contenga únicamente dos dígitos)
	public void setPrefijo(String prefijo) {
		if (prefijo != null && regexPrefijo.matcher(prefijo).matches()) { 
			this.prefijo = prefijo; 
		}
		else {
			this.prefijo = "XX";
		}
	}
	public String getNumTel() {
		return numTel;
	}
	//Setter con validación del número de teléfono (valida que no esté vacío y que contenga única y exclusivamente 10 dígitos)
	public void setNumTel(String numTel) {
		if (regexNumTel.matcher(numTel).matches()) { 
			this.numTel = numTel;
		}
		else {
			this.numTel = "0000000000";
		}
	}
	
	//Método toString
	public String toString() {
		return String.format("[%c](+%s)%s", tipo, prefijo, numTel);
	}
}
