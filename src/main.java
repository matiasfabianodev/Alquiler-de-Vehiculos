import java.awt.GridLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class main {
    public static void main(String[] args) 
    	{
    		Agencia miAgencia = new Agencia();
    		boolean programaGeneral = true;
    		String nombreUsuarioLogueado = "";
    		
    		do 
    			{ //BUCLE PROGRAMA
    				boolean pregunta1 = true; 
    				boolean loginExitoso = false;
    				String dniLogueado = ""; 
    				
    				do //BUCLE LOGIN/REGISTER
    					{ 
    						String [] botones1 = {"Inciar Sesion", "Registrarse", "Salir"};
    						int seleccion1 = JOptionPane.showOptionDialog(null, "Bienvenido!\nPor favor, seleccione una opción para comenzar:", "Alquiler de vehiculos", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, botones1, botones1[0]); //MENU
    						
    						switch (seleccion1) 
    							{
    								case 0: // INICIO DE SESIÓN
    									Cliente clienteLogueado = miAgencia.menuIniciarSesion();
    									if (clienteLogueado != null) 
    										{
    											loginExitoso = true;
    											dniLogueado = clienteLogueado.getDni();
    											nombreUsuarioLogueado = clienteLogueado.getNombre();
    											
    											pregunta1 = false;
    										}
    									break;
								
    								case 1: //REGISTRO
    									miAgencia.menuRegistrarse();
    									break;
								
    								case 2: //SALIR
										case JOptionPane.CLOSED_OPTION: JOptionPane.showMessageDialog(null, "Hasta Luego!");
										pregunta1 = false;
										programaGeneral = false;
										break;
    							}

    					} while (pregunta1); //CIERRA SECCION DE INICIO/REGISTRO
    				
    				if (loginExitoso && programaGeneral) //VERIFICA QUE EL LOGIN HAYA FUNCIONADO
    					{
    						boolean enSistema = true; 
    					
    						do // BUCLE MENU PRINCIPAL
    							{
    								String[] botonesMenuPrincipal = {"Alquilar Vehículo", "Devolver Vehículo", "Vehiculos disponibles", "Historial de Alquileres", "Modificar mis datos", "Donar Vehiculo", "Cerrar sesion"};
    						
    								int seleccion2 = JOptionPane.showOptionDialog(null, 
    										"Que desea hacer " + nombreUsuarioLogueado + "?", 
    										"Agencia de Vehiculos", 
    										JOptionPane.DEFAULT_OPTION, 
    										JOptionPane.PLAIN_MESSAGE, 
    										null, botonesMenuPrincipal, botonesMenuPrincipal[0]);
    						
    								switch (seleccion2) 
    									{
    										case 0: //ALQUILAR
    											miAgencia.menuAlquiler(dniLogueado);
    											break;
	    								
    										case 1: //DEVOLVER
    											miAgencia.menuDevolucion(dniLogueado);
    											break;
	    								
    										case 2: //CONSULTRA VEHICULOS
    											String listadoVehiculos = miAgencia.obtenerVehiculosDisponi();
    											JOptionPane.showMessageDialog(null, listadoVehiculos, "Vehiculos Disponibles", JOptionPane.INFORMATION_MESSAGE);
    											break;
	    								
    										case 3: //HISTORIAL DE ALQUILERES
    											miAgencia.menuHistorial(dniLogueado);
    											break;
	    								
    										case 4: //MODIFICAR DATOS
    											dniLogueado = miAgencia.menuModificarDatos(dniLogueado);
    											nombreUsuarioLogueado = miAgencia.obtenerNombrePorDni(dniLogueado);
    											break;
	    								
    										case 5: //AGREGAR AUTOS
    											miAgencia.menuDonarVehiculo();
    											break;
	    								
    										case 6: // CERRAR SESIÓN
    											case JOptionPane.CLOSED_OPTION:
    											JOptionPane.showMessageDialog(null, "Cerrando sesión... Volviendo al inicio.");
    											enSistema = false;
    											break;
    									}
    						
    							} while (enSistema); //CIERRA EL MENU PRINCIPAL
    					}

    			} while (programaGeneral); //CIERRA EL PROGRAMA
    	}
    }