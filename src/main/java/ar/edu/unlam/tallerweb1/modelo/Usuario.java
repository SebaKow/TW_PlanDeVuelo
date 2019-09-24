package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

// CLASE QUE MODELA EL CONCEPTO DE USUARIO, LA ANOTACIÓN @ENTITY LE AVISA A HIBERNATE QUE ESTA CLASE ES PERSISTIBLE.
/* EL PAQUETE AR.EDU.UNLAM.TALLERWEB1.MODELO ESTÁ INDICADO EN EL ARCHIVO HIBERNATECONTEXT.XML PARA QUE HIBERNATE
   BUSQUE ENTITIES. */
@Entity // ESTA CLASE REPRESENTA UNA ENTIDAD DE LA TABLA.
public class Usuario {

	/* LA ANOTACIÓN ID INDICA QUE ESTE ATRIBUTO ES EL UTILIZADO COMO CLAVE PRIMARIA DE LA ENTITY, SE INDICA QUE
	   EL VALOR ES AUTOGENERADO. */
	@Id // ESTE CAMPO REPRESENTA LA PK DE LA TABLA, DEBE SER LONG EN LO POSIBLE.
	@GeneratedValue(strategy = GenerationType.IDENTITY) // AUTOINCREMENTAL.
	private Long id;
	/* PARA EL RESTO DE LOS ATRIBUUTOS NO SE USAN ANOTACIONES, ENTONCES SE USA EL DEFAULT DE HIBERNATE:
	   LA COLUMNA SE LLAMA IGUAL QUE EL ATRIBUTO, LA MISMA ADMITE NULOS, Y EL TIPO DE DATO SE DEDUCE DEL
	   TIPO DE DATO DE JAVA. */
	private String email; // ESTOS ATRIBUTOS REPRESENTAN LOS CAMPOS DE LA TABLA.
	private String password;
	private String rol;
	private String nombre;
	private String apellido;
	private int dni;
//	private boolean esAdmin;
	
	public Usuario(){
		
	}

	@ManyToOne
	private PlanDeVuelo planDeVuelo;
	
	public Usuario(Long id, String email, String password, String rol,String nombre, String apellido, int dni){
		this.id = id;
		this.email = email;
		this.password = password;
		this.rol = rol;
		this.nombre=nombre;
		this.apellido=apellido;
		this.dni=dni;
	}
	
	public Long getId(){
		return id;
	}
	public void setId(Long id){
		this.id = id;
	}
	public String getEmail(){
		return email;
	}
	public void setEmail(String email){
		this.email = email;
	}
	public String getPassword(){
		return password;
	}
	public void setPassword(String password){
		this.password = password;
	}

	public String getRol(){
		return rol;
	}

	public void setRol(String rol){
		this.rol = rol;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	/*
	 * public boolean getEsAdmin() { return esAdmin; }
	 * 
	 * public void setEsAdmin(boolean esAdmin) { this.esAdmin = esAdmin; }
	 */

	public PlanDeVuelo getPlanDeVuelo() {
		return planDeVuelo;
	}

	public void setPlanDeVuelo(PlanDeVuelo planDeVuelo) {
		this.planDeVuelo = planDeVuelo;
	}
	
}