import java.awt.GridLayout;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Agencia 
	{
		private List<Cliente> clientes;
		private List<Vehiculo> vehiculos;
		private List<Alquiler> alquileres;
		
		public Agencia() 
			{
				this.clientes = new ArrayList<>();
				this.vehiculos = new ArrayList<>();
				this.alquileres = new ArrayList<>();
				
				//VEHICULOS PREDETERMINADOS
				
				this.vehiculos.add(new Auto("111111", "Toyota", 25000.0, 4, "Manual"));
				this.vehiculos.add(new Auto("222222", "Ford", 32000.0, 5, "Automatica"));
				this.vehiculos.add(new Auto("333333", "Fiat", 20000.0, 4, "Manual"));
				
				this.vehiculos.add(new Moto("444444", "Honda", 12000.0, 150));
				this.vehiculos.add(new Moto("555555", "Yamaha", 16500.0, 250));
				this.vehiculos.add(new Moto("666666", "Kawasaki", 38000.0, 400));
			}

		public Cliente buscarCliente(String dni) //BUSCADOR DE CLIENTES POR DNI
			{
				for (Cliente c : this.clientes) 
					{
						if (c.getDni().equals(dni)) 
							{
								return c;
							}
					}
				return null;
			}
		
		public void menuRegistrarse() //MENU DE REGISTRO
			{
				JTextField txtDni = new JTextField();
				JTextField txtNombre = new JTextField();
				String[] opcionesLicencia = {"Sí", "No"};
				JComboBox<String> comboLicencia = new JComboBox<>(opcionesLicencia);
				
				JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));
				panel.add(new JLabel("DNI:"));
				panel.add(txtDni);
				panel.add(new JLabel("Nombre Completo:"));
				panel.add(txtNombre);
				panel.add(new JLabel("Tiene Licencia de Conducir?:"));
				panel.add(comboLicencia);
				
				int result = JOptionPane.showConfirmDialog(null, panel, "Registro de Nuevo Cliente", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
				
				if (result == JOptionPane.OK_OPTION) 
					{
						String dni = txtDni.getText().trim();
						String nombre = txtNombre.getText().trim();
						boolean tieneLicencia = comboLicencia.getSelectedIndex() == 0;
						
						if (dni.isEmpty() || nombre.isEmpty()) 
							{
								JOptionPane.showMessageDialog(null, "Porfavor, ingrese datos en TODOS los campos.", "ERROR", JOptionPane.ERROR_MESSAGE);
								return;
							}
						
						if (!dni.matches("\\d{0,9}"))
							{
								JOptionPane.showMessageDialog(null, "El DNI debe ser un número válido (entre 0 y 9 dígitos de largo, sin letras ni puntos).", "ERROR de formato", JOptionPane.ERROR_MESSAGE);
								return;
							}
						
						if (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+")) 
							{
								JOptionPane.showMessageDialog(null, "El nombre debe contener únicamente letras y espacios.", "ERROR de Formato", JOptionPane.ERROR_MESSAGE);
								return;
							}
						
						if (buscarCliente(dni) != null) 
							{
								JOptionPane.showMessageDialog(null, "Ya existe un cliente registrado con el DNI: " + dni, "ERROR de Registro", JOptionPane.ERROR_MESSAGE);
								return;
							}
						
						Cliente nuevoCliente = new Cliente(dni, nombre, tieneLicencia);
						this.clientes.add(nuevoCliente);
						
						JOptionPane.showMessageDialog(null, "Cliente registrado con éxito!\nYa puede iniciar sesión.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
					}
			}
		
		public Cliente menuIniciarSesion() //MENU DE INICIO DE SESION
			{
				String input = JOptionPane.showInputDialog(null, "Ingrese su DNI:", "Inicio de Sesión", JOptionPane.PLAIN_MESSAGE);
			
				if (input == null) 
					{
						return null; 
					}
			
				String dniInput = input.trim();
				if (dniInput.isEmpty()) 
					{
						JOptionPane.showMessageDialog(null, "El DNI no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
						return null;
					}
				
				if (!dniInput.matches("\\d+")) 
					{
						JOptionPane.showMessageDialog(null, "EL DNI debe contener únicamente números (sin letras, puntos, guiones ni espacios).", "ERROR de Formato", JOptionPane.ERROR_MESSAGE);
						return null;
					}
				
				Cliente cliente = buscarCliente(dniInput);
				if (cliente != null) 
					{
						return cliente;
					} else {
						JOptionPane.showMessageDialog(null, "El DNI ingresado no está registrado en el sistema.", "Error", JOptionPane.ERROR_MESSAGE);
						return null;
					}
			}
		
		public String obtenerNombrePorDni(String dni) //METODO PARA MANEJAR EL NOMBRE EN LOS MENUS
			{
			    for (Cliente c : clientes) 
			    	{
				        if (c.getDni().equals(dni)) 
				        	{
				            	return c.getNombre();
				        	}
			    	}
			    return "";
			}
		
		public void modificarNom (String nuevoNombre, String dniLogueado) //METODO PARA MODIFICAR NOMBRE
			{
			 	for (Cliente c : clientes) 
			 	{
			 		if (c.getDni().equals(dniLogueado)) 
			 			{
			 				c.setNombre(nuevoNombre);
			 				break;
			 			}
			 	}
			}
		
		public void modificarDNI (String dniAnterior, String nuevodni) //METODO PARA MODIFICAR DNI
			{
			 	for (Cliente c : clientes) 
			 	{
			 		if (c.getDni().equals(dniAnterior)) 
			 			{
				 			c.setDni(nuevodni);
				 			break;
			 			}
			 	}
			}
		
		public void modificarLicen (boolean nuevaLicencia, String dniLogueado) //METODO PARA MODIFICAR EL ESTADO DE LA LICENCIA
			{
			 	for (Cliente c : clientes) 
			 	{
			 		if (c.getDni().equals(dniLogueado)) 
			 			{
			 				c.setLicencia(nuevaLicencia);
			 				break;
			 			}
			 	}
			}
		
		public boolean consultarLicenciaPorDni (String dni) //METODO PARA VERIFICAR SI TIENE O NO LICENCIA
			{
				for (Cliente c : clientes) 
					{
						if (c.getDni().equals(dni)) 
							{
								return c.isLicencia();
							}
					}
				return false;
			} 
		
		public String obtenerVehiculosDisponi() //METODO PARA MOSTRAR EL LISTADO DE VEHICULOS
			{
				StringBuilder sb = new StringBuilder();
				
				boolean Disponibles = false;
				NumberFormat formatoMoneda = NumberFormat.getCurrencyInstance();
				
				for (Vehiculo v : vehiculos) 
					{
						if (v.isDisponibilidad()) 
							{
								sb.append(" - Patente: ").append(v.getPatente()).append("\n");
								sb.append("   Marca: ").append(v.getMarca()).append("\n");
								sb.append("   Tarifa Base: ").append(formatoMoneda.format(v.getTarifaBase())).append("\n");
							
						
								if (v instanceof Auto) 
									{
										Auto miAuto = (Auto) v;
										sb.append("   Tipo: Auto (").append(miAuto.getPuertas()).append(" puertas - ").append(miAuto.getTransmision()).append(")\n");
									}
								else if (v instanceof Moto) 
									{
										Moto miMoto = (Moto) v;
										sb.append("   Tipo: Moto (Cilindrada: ").append(miMoto.getCilindrada()).append("cc)\n");
									}
						sb.append("------------------------------\n");
						Disponibles = true;
							}
					}
				
				if (!Disponibles) 
					{
						return "No tenemos vehiculos disponibles";
					}
				
				return sb.toString();
			}
		
		public boolean existePatente (String patente) //VERFICA SI LA PATENTE TA EXISTE EN LA FLOTA
			{
				for (Vehiculo v : vehiculos) 
					{
						if (v.getPatente().equalsIgnoreCase(patente)) 
							{
								return true;
							}
					}
				return false;
			}
		
		public void agregarVehiculo (Vehiculo nuevoVehiculo)  //AGREGA A LA LISTA EL VEHICULO DONADO
			{
				this.vehiculos.add(nuevoVehiculo);
			}
		
		public void menuDonarVehiculo() //MENU PARA DONAR
			{
				String [] opcionesTipo = {"Auto", "Moto"};
				int tipoVehiculo = JOptionPane.showOptionDialog(null, 
			            "Qué tipo de vehículo desea donar?", 
			            "Donar Vehículo", 
			            JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, 
			            null, opcionesTipo, opcionesTipo[0]);
				
				if (tipoVehiculo == JOptionPane.CLOSED_OPTION)  
					{
						return;
					}
				
				double TarifaAuto = 22000.0; //TARIFAS PREDEFINIDAS PARA LOS AUTOS DONADOS
				double TarifaMoto = 14000.0;
				
				if (tipoVehiculo == 0) //AUTO
					{
						JTextField txtPatente = new JTextField();
						JTextField txtMarca = new JTextField();
						JTextField txtPuertas = new JTextField();
						String [] transmisiones = {"Manual", "Automatico"};
						JComboBox<String> comboTransmision = new JComboBox<String>(transmisiones);
						
						JPanel panelAuto = new JPanel(new GridLayout(0,2,5,10));
						panelAuto.add(new JLabel("Patente:"));
				        panelAuto.add(txtPatente);
				        panelAuto.add(new JLabel("Marca:"));
				        panelAuto.add(txtMarca);
				        panelAuto.add(new JLabel("Cantidad de Puertas:"));
				        panelAuto.add(txtPuertas);
				        panelAuto.add(new JLabel("Transmisión:"));
				        panelAuto.add(comboTransmision);
				        
				        int mensaje = JOptionPane.showConfirmDialog(null, panelAuto, "Datos del auto a donar: ",JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
				        if (mensaje == JOptionPane.OK_OPTION) 
				        	{
								try 
									{
										String patente = txtPatente.getText().trim().toUpperCase();
										String marca = txtMarca.getText().trim();
										int puertas = Integer.parseInt(txtPuertas.getText().trim());
										
										if (puertas <= 0) 
											{
										    	JOptionPane.showMessageDialog(null, "Error: La cantidad de puertas debe ser mayor a 0.", "Error", JOptionPane.ERROR_MESSAGE);
										    	return;
											}
										
										String transmision = (String) comboTransmision.getSelectedItem();
										
										if (patente.isEmpty() || marca.isEmpty()) 
											{
												JOptionPane.showMessageDialog(null, "Error: Campos vacíos.", "Error", JOptionPane.ERROR_MESSAGE);
												return;
											}
									
										if (this.existePatente(patente)) 
											{
												JOptionPane.showMessageDialog(null, "Error: La patente ya existe.", "Error", JOptionPane.ERROR_MESSAGE);
												return;
											}
										
										Auto nuevoAuto = new Auto (patente, marca, TarifaAuto, puertas, transmision);
										this.agregarVehiculo(nuevoAuto);
										
										JOptionPane.showMessageDialog(null, "Auto Incorporado con exito!.", "Gracias!", JOptionPane.INFORMATION_MESSAGE);
									} 
								catch (NumberFormatException e) 
									{
									JOptionPane.showMessageDialog(null, "Error: Los datos no fueron ingresados correctamente.", "Error", JOptionPane.ERROR_MESSAGE);
									}
				        	}
					}
				    else if (tipoVehiculo == 1) //MOTO
				        {
				        	JTextField txtPatente2 = new JTextField();
				        	JTextField txtMarca2 = new JTextField();
				        	JTextField txtCilindrada = new JTextField();
				        		
				        	JPanel panelMoto = new JPanel(new GridLayout(0,2,5,10));
				            panelMoto.add(new JLabel("Patente:"));
				            panelMoto.add(txtPatente2);
				            panelMoto.add(new JLabel("Marca:"));
				            panelMoto.add(txtMarca2);
				            panelMoto.add(new JLabel("Cilindrada (cc):"));
				            panelMoto.add(txtCilindrada);
				                
				            int mensaje2 = JOptionPane.showConfirmDialog(null, panelMoto, "Datos de la Moto a Donar", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
				            if (mensaje2 == JOptionPane.OK_OPTION) 
				               {
									try 
										{
											String patente2 = txtPatente2.getText().trim().toUpperCase();
											String marca2 = txtMarca2.getText().trim();
											int cilindrada = Integer.parseInt(txtCilindrada.getText().trim());
											
											if (cilindrada <= 0) 
												{
											    	JOptionPane.showMessageDialog(null, "Error: La cilindrada debe ser mayor a 0cc.", "Error", JOptionPane.ERROR_MESSAGE);
											    	return;
												}
												
											if (patente2.isEmpty() || marca2.isEmpty()) 
												{
													JOptionPane.showMessageDialog(null, "Error: Campos vacíos.", "Error", JOptionPane.ERROR_MESSAGE);
													return;
												}
											if (this.existePatente(patente2)) 
												{
													JOptionPane.showMessageDialog(null, "Error: La patente ya existe.", "Error", JOptionPane.ERROR_MESSAGE);
													return;
												}
												
											Moto nuevaMoto = new Moto (patente2, marca2, TarifaMoto, cilindrada);
											this.agregarVehiculo(nuevaMoto);
											JOptionPane.showMessageDialog(null, "Moto Incorporado con exito!.", "Gracias!", JOptionPane.INFORMATION_MESSAGE);
										} 
									catch (NumberFormatException e) 
										{
											JOptionPane.showMessageDialog(null, "Error: Los datos no fueron ingresados correctamente.", "Error", JOptionPane.ERROR_MESSAGE);
										}
				                }
				        	}
			}
		
		public boolean tieneAlquiler(String dni) //VERFICA SI EL CLIENTE TIENE VEHICULO VIGENTE
			{
				for (Alquiler a : alquileres) 
					{
						if (a.getDniCliente().equals(dni) && a.getEstado().equals("ACTIVO")) 
							{
								return true;
							}
					}
				return false;
			}
		
		public void menuAlquiler (String dniAlquiler) //MENU PARA ALQUILAR

			{
				
				if (dniAlquiler == null || dniAlquiler.trim().isEmpty()) 
					{
						return;
					}
				
				dniAlquiler = dniAlquiler.trim();
				
				if (!this.consultarLicenciaPorDni(dniAlquiler)) 
		        	{
		            	JOptionPane.showMessageDialog(null, 
		            			"ALQUILER DENEGADO: El cliente no posee una licencia de conducir vigente.\n" +
		            					"Si acaba de revalidar su licencia, modifique el estado de esta antes de alquilar.", 
		            					"ERROR de validez", 
		            					JOptionPane.ERROR_MESSAGE);
		            	return;
		        	}
				
				if (this.tieneAlquiler(dniAlquiler)) 
					{
						JOptionPane.showMessageDialog(null, 
			                "Usted ya posee un alquiler ACTIVO.\nDebe devolver el vehículo antes de solicitar otro.", 
			                "Alquiler Denegado", JOptionPane.WARNING_MESSAGE);
						return;
					}
				
				ArrayList<Vehiculo> disponibles = new ArrayList<Vehiculo>();
				JComboBox<String> comboVehiculos = new JComboBox<>();
	            NumberFormat formatoMoneda = NumberFormat.getCurrencyInstance();
	            
				for (Vehiculo v : this.vehiculos) 
					{
						if (v.isDisponibilidad()) 
							{
								disponibles.add(v);
								comboVehiculos.addItem("[" + v.getPatente() + "] " + v.getMarca() + " - " + formatoMoneda.format(v.getTarifaBase()) + "/día");
							}
					}
				
				if (disponibles.isEmpty()) 
					{
						JOptionPane.showMessageDialog(null, 
			                "Lo sentimos, en este momento no hay vehículos disponibles.", 
			                "ERROR", 
			                JOptionPane.INFORMATION_MESSAGE);
						return;
					}
				
				JTextField txtDias = new JTextField();
			    
			    JPanel panelAlquiler = new JPanel(new java.awt.GridLayout(0, 2, 5, 10));
			    panelAlquiler.add(new JLabel("Vehículo Disponible:"));
			    panelAlquiler.add(comboVehiculos);
			    panelAlquiler.add(new JLabel("Cantidad de Días:"));
			    panelAlquiler.add(txtDias);
			    
			    int mensaje = JOptionPane.showConfirmDialog(null, panelAlquiler, 
			            "Registrar Alquiler - DNI: " + dniAlquiler, 
			            JOptionPane.OK_CANCEL_OPTION, 
			            JOptionPane.PLAIN_MESSAGE);
				
			    if (mensaje == JOptionPane.OK_OPTION) 
			    	{
						try 
							{
								int dias = Integer.parseInt(txtDias.getText().trim());

								if (dias <= 0) 
									{
										JOptionPane.showMessageDialog(null, "La cantidad de días debe ser mayor a 0.", "Error", JOptionPane.ERROR_MESSAGE);
										return;
									}
								
								int posSeleccionada = comboVehiculos.getSelectedIndex();
					            Vehiculo vehiculoElegido = disponibles.get(posSeleccionada);
					            
					            int nuevoID = this.alquileres.size() + 1;
					            LocalDate fechaInicio = LocalDate.now();
					            double costoTotal = vehiculoElegido.calcularCostoAlquiler(dias);
					            
					            Alquiler nuevoAlquiler = new Alquiler(nuevoID, fechaInicio, dias, costoTotal, "ACTIVO", dniAlquiler, vehiculoElegido.getPatente());
					            this.alquileres.add(nuevoAlquiler);
					            
					            vehiculoElegido.setDisponibilidad(false);
					            
					            JOptionPane.showMessageDialog(null, 
					                    "Alquiler registrado con éxito!\n\n" +
					                    "Nro Contrato (ID): " + nuevoID + "\n" +
					                    "Cliente DNI: " + dniAlquiler + "\n" +
					                    "Vehículo: " + vehiculoElegido.getMarca() + " [" + vehiculoElegido.getPatente() + "]\n" +
					                    "Días: " + dias + "\n" +
					                    "Total a abonar: " + formatoMoneda.format(costoTotal), 
					                    "Éxito", 
					                    JOptionPane.INFORMATION_MESSAGE);
							} catch (NumberFormatException e) 
							{
								JOptionPane.showMessageDialog(null, "Porfavor, ingrese un numero valido.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
							}
			    	}
			}
		
		public void menuDevolucion(String dniDevolucion) //MENU DE DEVOLUCION
			{
		    	Alquiler alquilerActivo = null;

		    	for (Alquiler a : this.alquileres) 
		    		{
		    			if (a.getDniCliente().equals(dniDevolucion) && a.getEstado().equalsIgnoreCase("ACTIVO")) 
		    				{
		    					alquilerActivo = a;
		    					break;
		    				}
		    		}

		    	if (alquilerActivo == null) 
		    		{
			    		JOptionPane.showMessageDialog(null, 
			    				"Usted no posee ningún vehículo alquilado.", 
			    				"Información", 
			    				JOptionPane.INFORMATION_MESSAGE);
			    		return;
		    		}

		    	String patenteVehiculo = alquilerActivo.getPatenteAlquilada();
		    	Vehiculo vehiculoADevolver = null;

		    	for (Vehiculo v : this.vehiculos) 
		    		{
		    			if (v.getPatente().equals(patenteVehiculo)) 
		    				{
		    					vehiculoADevolver = v;
		    					break;
		    				}
		    		}

		    	if (vehiculoADevolver != null) 
		    		{
		    			vehiculoADevolver.setDisponibilidad(true); 
		    		}

		    	alquilerActivo.setEstado("FINALIZADO");

			    JOptionPane.showMessageDialog(null, 
			            "Vehículo devuelto con éxito!\n\n" +
			            "Detalles de la devolución:\n" +
			            "- Vehículo: " + (vehiculoADevolver != null ? vehiculoADevolver.getMarca() : "Desconocido") + "\n" +
			            "- Patente: " + patenteVehiculo + "\n" +
			            "- Contrato ID: " + alquilerActivo.getId() + "\n\n" +
			            "Gracias!", 
			            "Devolución Exitosa", 
			            JOptionPane.INFORMATION_MESSAGE);
			}
		
		public void menuHistorial(String dniUsuario) //MENU DE ALQUILERES DEL USUARIO
			{
			    StringBuilder historialTexto = new StringBuilder("--- Su historial de Alquileres ---\n\n");
			    boolean tieneHistorial = false;
			    NumberFormat formatoMoneda = NumberFormat.getCurrencyInstance();
			    
			    for (Alquiler a : this.alquileres) 
			    	{
				        if (a.getDniCliente().equals(dniUsuario)) 
				        	{
				            	tieneHistorial = true;
				            	String datosVehiculo = "Patente: " + a.getPatenteAlquilada();
				            	for (Vehiculo v : this.vehiculos) 
				            		{
				            			if (v.getPatente().equals(a.getPatenteAlquilada())) 
				            				{
				            					datosVehiculo = v.getMarca() + " [" + v.getPatente() + "]";
				            					break;
				            				}
				            		}
				            	historialTexto.append("Contrato ID: ").append(a.getId()).append("\n")
				                          .append("Vehículo: ").append(datosVehiculo).append("\n")
				                          .append("Fecha de Inicio: ").append(a.getInicio()).append("\n")
				                          .append("Días Contratados: ").append(a.getCantDias()).append("\n")
				                          .append("Monto Abonado: ").append(formatoMoneda.format(a.getCostoCalculado())).append("\n")
				                          .append("Estado del Alquiler: ").append(a.getEstado().toUpperCase()).append("\n")
				                          .append("--------------------------------------------------\n");
				        	}
			    	}

		    if (!tieneHistorial) 
		    	{
			        JOptionPane.showMessageDialog(null, 
			                "Usted no registro ningun alquiler en nuestro sistema.", 
			                "Historial Vacío", 
			                JOptionPane.INFORMATION_MESSAGE);
		    	} else 
		    	{
			        JOptionPane.showMessageDialog(null, 
			                historialTexto.toString(), 
			                "Mi Historial de Alquileres", 
			                JOptionPane.INFORMATION_MESSAGE);
		    	}
			}
		
		public String menuModificarDatos(String dniLogueado) //MENU PARA MODIFICAR LOS DATOS
			{
		    	String [] opcionesMod = {"Nombre", "DNI", "Estado de licencia"};
		    	int eleccionMod = JOptionPane.showOptionDialog(null, 
		    			"¿Qué dato desea modificar de su perfil?", 
		    			"Modificar Datos", 
		    			JOptionPane.DEFAULT_OPTION, 
		    			JOptionPane.QUESTION_MESSAGE, 
		    			null, opcionesMod, opcionesMod[0]);
		    
		    	switch (eleccionMod) 
		    		{
		    			case 0: // MODIFICAR NOMBRE
		    				String nuevoNom = JOptionPane.showInputDialog("Ingrese su nuevo nombre: ");
		    				if (nuevoNom != null) 
		    					{
		    						String nuevoNom2 = nuevoNom.trim();
		    						if (!nuevoNom2.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+"))
		    							{
		    								JOptionPane.showMessageDialog(null, "El nombre debe contener solo letras y no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
		    							} else {
		    								this.modificarNom(nuevoNom2, dniLogueado);
		    								JOptionPane.showMessageDialog(null, "Nombre modificado con éxito!", "Listo", JOptionPane.INFORMATION_MESSAGE);
		    							}
		    					}
		    				break;
		        
		    			case 1: // MODIFICAR DNI
		    				String nuevoDni = JOptionPane.showInputDialog("Ingrese su nuevo DNI: ");
		    				if (nuevoDni != null) 
		    					{
		    						String nuevoDni2 = nuevoDni.trim();
		    						
		    						if (nuevoDni2.isEmpty())
		    							{
		    								JOptionPane.showMessageDialog(null, "El DNI no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
		    							} else if (!nuevoDni2.matches("\\d{1,9}")) {
		    								JOptionPane.showMessageDialog(null, "El DNI debe contener solo numeros (hasta 9 digitos de largo)", "Error", JOptionPane.ERROR_MESSAGE);
		    							} else if (this.tieneAlquiler(dniLogueado)) {
		    								JOptionPane.showMessageDialog(null, "No puede modificar su DNI si tiene un alquiler ACTIVO.\nDebe devolver el vehículo primero.", "Modificación Denegada", JOptionPane.WARNING_MESSAGE);
		    							} else if (this.buscarCliente(nuevoDni2) != null) {
		    								JOptionPane.showMessageDialog(null, "El DNI ingresado ya se encuentra registrado por otro usuario.", "Error", JOptionPane.ERROR_MESSAGE);
		    							} else {
		    								this.modificarDNI(dniLogueado, nuevoDni2);
		    								dniLogueado = nuevoDni2;
		    								JOptionPane.showMessageDialog(null, "DNI modificado con éxito!", "Listo", JOptionPane.INFORMATION_MESSAGE);
		    							}
		    					}
		    				break;
		            
		    			case 2: // MODIFICAR LICENCIA
		    				boolean ValidezLicencia = this.consultarLicenciaPorDni(dniLogueado);
		    				String [] opcionesLic = {"Si", "No"};
		    				int resLic = JOptionPane.showOptionDialog(null, "Cuenta actualmente con Licencia vigente?", "Modificar Licencia", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcionesLic, opcionesLic[0]);
		        
		    				if (resLic != JOptionPane.CLOSED_OPTION) 
		    					{
		    						boolean nuevaLic = (resLic == 0);
		    						if (nuevaLic == ValidezLicencia) 
		    							{
		    								if (nuevaLic == true) 
		    									{
		    										JOptionPane.showMessageDialog(null, "Usted ya tiene registrado en el sistema que si tiene licencia.", "Información", JOptionPane.WARNING_MESSAGE);
		    									} else {
		    										JOptionPane.showMessageDialog(null, "Usted ya tiene registrado en el sistema que no tiene licencia.", "Información", JOptionPane.WARNING_MESSAGE);
		    									}
		    							} else {
		    								this.modificarLicen(nuevaLic, dniLogueado);
		    								JOptionPane.showMessageDialog(null, "Estado de licencia actualizado con éxito!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
		    							}
		    					}
		    				break;
		    		}
		    
		    	return dniLogueado;
			}
}
