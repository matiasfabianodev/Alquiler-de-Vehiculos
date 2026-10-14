import javax.swing.JOptionPane;

public class Cliente 
	{
		private String dni;
		private String nombre;
		private boolean licencia;
		
		public Cliente(String dNI, String nombre, boolean licencia) 
			{
				this.dni = dNI;
				this.nombre = nombre;
				this.licencia = licencia;
			}

		public String getDni() 
			{
				return dni;
			}

		public void setDni(String dni) 
			{
				this.dni = dni;
			}

		public String getNombre() 
			{
				return nombre;
			}

		public void setNombre(String nombre) 
			{
				this.nombre = nombre;
			}

		public boolean isLicencia() 
			{
				return licencia;
			}

		public void setLicencia(boolean licencia) 
			{
				this.licencia = licencia;
			} 
	}
