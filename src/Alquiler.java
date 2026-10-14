import java.time.LocalDate;

public class Alquiler 
	{
		private int id;
		private LocalDate inicio;
		private int cantDias;
		private double costoCalculado;
		private String estado;
		private String dniCliente;
		private String patenteAlquilada;
		
		public Alquiler(int iD, LocalDate inicio, int cantDias, double costoCalculado, String estado, String dniCliente, String patenteAlquilada) 
			{
				this.id = iD;
				this.inicio = inicio;
				this.cantDias = cantDias;
				this.costoCalculado = costoCalculado;
				this.estado = "ACTIVO";
				this.dniCliente = dniCliente;
		        this.patenteAlquilada = patenteAlquilada;
			}

		public int getId() 
			{
				return id;
			}

		public void setId(int id) 
			{
				this.id = id;
			}

		public LocalDate getInicio() 
			{
				return inicio;
			}

		public void setInicio(LocalDate inicio) 
			{
				this.inicio = inicio;
			}

		public int getCantDias() 
			{
				return cantDias;
			}

		public void setCantDias(int cantDias) 
			{
				this.cantDias = cantDias;
			}

		public double getCostoCalculado() 
			{
				return costoCalculado;
			}

		public void setCostoCalculado(double costoCalculado) 
			{
				this.costoCalculado = costoCalculado;
			}

		public String getEstado() 
			{
				return estado;
			}

		public void setEstado(String estado) 
			{
				this.estado = estado;
			}

		public String getDniCliente() 
			{
				return dniCliente;
			}

		public void setDniCliente(String dniCliente) 
			{
				this.dniCliente = dniCliente;
			}

		public String getPatenteAlquilada() 
			{
				return patenteAlquilada;
			}

		public void setPatenteAlquilada(String patenteAlquilada) 
			{
				this.patenteAlquilada = patenteAlquilada;
			}
	}
