public class Moto extends Vehiculo 
	{
		private int cilindrada;
		private static final double costoExtra = 2000.0;
		
		public Moto(String patente, String marca, double tarifaBase, int cilindrada) 
		 	{
		        super(patente, marca, tarifaBase);
		        this.cilindrada = cilindrada;
		    }
		
	    @Override
	    
	    public double calcularCostoAlquiler(int dias) 
	    	{
		        double costo = getTarifaBase() * dias;
		        if (cilindrada > 250) 
		        	{
		            	costo += costoExtra * dias;
		        	}
		        return costo;
	    	}

		public int getCilindrada() 
			{
				return cilindrada;
			}

		public void setCilindrada(int cilindrada) 
			{
				this.cilindrada = cilindrada;
			}

		public static double getCostoextra() 
			{
				return costoExtra;
			}
	}
