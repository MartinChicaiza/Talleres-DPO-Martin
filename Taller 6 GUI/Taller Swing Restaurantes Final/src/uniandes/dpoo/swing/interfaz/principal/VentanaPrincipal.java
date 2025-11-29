package uniandes.dpoo.swing.interfaz.principal;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JFrame;

import uniandes.dpoo.swing.interfaz.agregar.VentanaAgregarRestaurante;
import uniandes.dpoo.swing.interfaz.mapa.VentanaMapa;
import uniandes.dpoo.swing.mundo.Diario;
import uniandes.dpoo.swing.mundo.Restaurante;

@SuppressWarnings("serial")
public class VentanaPrincipal extends JFrame
{
    /**
     * Es una referencia al diario en el que se registran las visitas a los restaurantes
     */
    private Diario mundo;

    /**
     * El panel con los botones para crear un nuevo restaurante o para ver el mapa de restaurantes
     */
    private PanelBotones pBotones;

    /**
     * El panel donde se muestran los detalles del restaurante seleccionado actualmente
     */
    private PanelDetallesRestaurante pDetalles;

    /**
     * El panel donde se muestra la lista de restaurantes
     */
    private PanelLista pLista;

    /**
     * Una referencia a la ventana del mapa, si ya se abrió alguna vez
     */
    private VentanaMapa ventanaMapa;

    /**
     * Una referencia a la ventana donde se agregan restaurantes, si ya se abrió alguna vez
     */
    private VentanaAgregarRestaurante ventanaAgregar;

    public VentanaPrincipal( Diario elDiario )
    {
        this.mundo = elDiario;
        setLayout( new BorderLayout( ) );

        pBotones = new PanelBotones( this );
        add( pBotones, BorderLayout.NORTH );

        pLista = new PanelLista( this );
        add( pLista, BorderLayout.CENTER ); 

        pDetalles = new PanelDetallesRestaurante( );
        add( pDetalles, BorderLayout.SOUTH );

        actualizarRestaurantes( );

        setTitle( "Restaurantes" );
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setSize( 400, 600 );
        setLocationRelativeTo( null );
        setVisible( true );
    }

    /**
     * Abre la ventana para agregar un nuevo restaurante, si no está abierta ya
     */
    public void mostrarVetanaNuevoRestaurante( )
    {
        if( ventanaAgregar == null || !ventanaAgregar.isVisible( ) )
        {
            ventanaAgregar = new VentanaAgregarRestaurante( this );
            ventanaAgregar.setVisible( true );
        }
    }

    /**
     * Abre la ventana para mostrar el mapa de restaurante, si no está abierta ya
     */
    public void mostrarVentanaMapa( )
    {
        if( ventanaMapa == null || !ventanaMapa.isVisible( ) )
        {
            ventanaMapa = new VentanaMapa( this );
            ventanaMapa.setVisible( true );
        }
    }

    /**
     * Agrega un nuevo restaurante al diario y actualiza la información que se muestra
     */
    public void agregarRestaurante( String nombre, int calificacion, int x, int y, boolean visitado )
    {
        Restaurante nuevoRestaurante = new Restaurante( nombre, calificacion, x, y, visitado );
        this.mundo.agregarRestaurante( nuevoRestaurante );
        this.actualizarRestaurantes( );
        this.pLista.seleccionarRestaurante( nuevoRestaurante ); // Selecciona el nuevo restaurante
    }

    /**
     * Retorna una lista de los restaurantes.
     */
    public List<Restaurante> getRestaurantes( boolean completos )
    {
        return mundo.getRestaurantes( completos );
    }

    /**
     * Actualiza los restaurantes que se muestran en la lista y el restaurante seleccionado del cual se muestran los detalles
     */
    private void actualizarRestaurantes( )
    {
        List<Restaurante> todos = this.mundo.getRestaurantes( true );
        this.pLista.actualizarRestaurantes( todos );
        
        // Selecciona el primer elemento si la lista no está vacía, o limpia los detalles.
        if (!todos.isEmpty()) {
            this.pLista.seleccionarRestaurante( todos.get(0) );
        } else {
            this.cambiarRestauranteSeleccionado( null );
        }
    }

    /**
     * Cambia el restaurante actualmente seleccionado (y mostrado) por el que se pasa por parámetro
     */
    public void cambiarRestauranteSeleccionado( Restaurante seleccionado )
    {
        pDetalles.actualizarRestaurante( seleccionado );
    }

    /**
     * Inicia la aplicación, creando un conjunto básico de restaurantes y luego creando la interfaz de la aplicación
     */
    public static void main( String[] args )
    {
        Diario elDiario = new Diario( );
        elDiario.agregarRestaurante( new Restaurante( "Pita Pan", 4, 30, 30, true ) );
        elDiario.agregarRestaurante( new Restaurante( "Lord of the Wings", 5, 170, 210, true ) );
        elDiario.agregarRestaurante( new Restaurante( "Nacho Business", 2, 350, 170, false ) );
        elDiario.agregarRestaurante( new Restaurante( "Thai Tanic", 1, 110, 100, false ) );
        elDiario.agregarRestaurante( new Restaurante( "Planet of the Creppes", 3, 400, 400, true ) );

        new VentanaPrincipal( elDiario );
    }

}