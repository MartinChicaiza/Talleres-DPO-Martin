package uniandes.dpoo.swing.interfaz.agregar;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import uniandes.dpoo.swing.interfaz.principal.VentanaPrincipal;

@SuppressWarnings("serial")
public class VentanaAgregarRestaurante extends JFrame
{
    /**
     * El panel donde se editan los detalles del restaurante
     */
    private PanelEditarRestaurante panelDetalles;

    /**
     * El panel con los botones para agregar un restaurante o cerrar la ventana
     */
    private PanelBotonesAgregar panelBotones;

    /**
     * El panel para marcar la ubicación del restaurante
     */
    private PanelMapaAgregar panelMapa;

    /**
     * La ventana principal de la aplicación
     */
    private VentanaPrincipal ventanaPrincipal;

    public VentanaAgregarRestaurante( VentanaPrincipal principal )
    {
        this.ventanaPrincipal = principal;
        setTitle("Agregar Nuevo Restaurante");
        setLayout( new BorderLayout( ) );
        
        panelMapa = new PanelMapaAgregar( );
        add( panelMapa, BorderLayout.CENTER );

        JPanel pSur = new JPanel( new BorderLayout() );
        
        panelDetalles = new PanelEditarRestaurante( );
        pSur.add( panelDetalles, BorderLayout.CENTER );
        
        panelBotones = new PanelBotonesAgregar( this );
        pSur.add( panelBotones, BorderLayout.EAST );
        
        add( pSur, BorderLayout.SOUTH );

        pack( );
        setLocationRelativeTo( null );
        setDefaultCloseOperation( DISPOSE_ON_CLOSE );
        setResizable( false );
    }

    /**
     * Le pide al panelDetalles los datos del nuevo restaurante y se los envía a la ventana principal para que cree el nuevo restaurante, y luego cierra la ventana.
     */
    public void agregarRestaurante( )
    {
        String nombre = panelDetalles.getNombre( );
        int calificacion = panelDetalles.getCalificacion( );
        boolean visitado = panelDetalles.getVisitado( );
        int[] coordenadas = panelMapa.getCoordenadas( );
        int x = coordenadas[0];
        int y = coordenadas[1];
        
        if( nombre.trim().isEmpty() )
        {
            JOptionPane.showMessageDialog( this, "El nombre del restaurante no puede estar vacío.", "Error de Creación", JOptionPane.ERROR_MESSAGE );
            return;
        }
        
        if (x == 0 && y == 0)
        {
             JOptionPane.showMessageDialog( this, "Por favor, haga clic en el mapa para seleccionar la ubicación del restaurante.", "Error de Creación", JOptionPane.ERROR_MESSAGE );
            return;
        }

        ventanaPrincipal.agregarRestaurante( nombre, calificacion, x, y, visitado );

        cerrarVentana( );
    }

    /**
     * Cierra la ventana sin crear un nuevo restaurante
     */
    public void cerrarVentana( )
    {
        dispose( );
    }

}