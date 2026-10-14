
public class Auto extends Vehiculo 
	{
		private int puertas;
		private String transmision;
		
		 public Auto(String patente, String marca, double tarifaBase, int puertas, String transmision) 
		 	{
		        super(patente, marca, tarifaBase);
		        this.puertas = puertas;
		        this.transmision = transmision;
		    }
		 
		 @Override
		 
		 public double calcularCostoAlquiler(int dias) 
		 	{
		        return getTarifaBase() * dias * 1.15;
		    }
		 
		 public int getPuertas() 
			 { 
				 return puertas; 
			 }
		 
		  public void setPuertas(int puertas) 
		  	{
			  	this.puertas = puertas;
		  	}
		 
		  public String getTransmision() 
			{ 
				 return transmision; 
			}

		  public void setTransmision(String transmision) 
		  	{
			  	this.transmision = transmision;
		  	}
		  
		  
	}
