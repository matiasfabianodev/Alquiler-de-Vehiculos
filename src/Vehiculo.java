public abstract class Vehiculo 
	{
	 	private String patente;
	 	private String marca;
	 	private double tarifaBase;
	 	private boolean disponibilidad;
	 	
		public Vehiculo(String patente, String marca, double tarifaBase) 
			{
		        this.patente = patente;
		        this.marca = marca;
		        this.tarifaBase = tarifaBase;
		        this.disponibilidad = true;
			}
		
		public abstract double calcularCostoAlquiler(int dias);

		public String getPatente() 
			{
				return patente;
			}

		public void setPatente(String patente) 
			{
				this.patente = patente;
			}

		public String getMarca() 
			{
				return marca;
			}

		public void setMarca(String marca) 
			{
				this.marca = marca;
			}

		public double getTarifaBase() 
			{
				return tarifaBase;
			}

		public void setTarifaBase(double tarifaBase) 
			{
				this.tarifaBase = tarifaBase;
			}

		public boolean isDisponibilidad() 
			{
				return disponibilidad;
			}

		public void setDisponibilidad(boolean disponibilidad) 
			{
				this.disponibilidad = disponibilidad;
			}
	}
