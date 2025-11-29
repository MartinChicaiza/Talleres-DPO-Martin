package uniandes.dpoo.swing.interfaz.principal;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import uniandes.dpoo.swing.mundo.Restaurante;

@SuppressWarnings("serial")
public class PanelDetallesRestaurante extends JPanel
{
    /**
     * La etiqueta donde se muestra el nombre de un restaurante
     */
    private JLabel labNombre;

    /**
     * La etiqueta donde se muestra la calificación de un restaurante, usando imágenes de estrellas
     */
    private JLabel labCalificacion;

    /**
     * Un checkbox en el que se muestra si un restaurante fue visitado o no
     */
    private JCheckBox chkVisitado;

    public PanelDetallesRestaurante( )
    {
        setLayout( new GridLayout( 3, 1 ) );

        labNombre = new JLabel( "Nombre: [N/A]" );
        add( labNombre );
        
        labCalificacion = new JLabel( "Calificación: [N/A]" );
        add( labCalificacion );

        chkVisitado = new JCheckBox( "Visitado" );
        chkVisitado.setEnabled( false );
        add( chkVisitado );
        
        actualizarRestaurante( null );
    }

    /**
     * Actualiza los datos mostrados del restaurante, indicando los valores por separado.
     */
    private void actualizarRestaurante( String nombre, int calificacion, boolean visitado )
    {
        if (nombre == null) {
            labNombre.setText( "Nombre: [No Seleccionado]" );
            labCalificacion.setText( "Calificación: N/A" );
            labCalificacion.setIcon( null );
            chkVisitado.setSelected( false );
            chkVisitado.setText( "Visitado" );
        } else {
            labNombre.setText( "Nombre: " + nombre );
            
            ImageIcon icono = buscarIconoCalificacion( calificacion );
            labCalificacion.setIcon( icono );
            labCalificacion.setText( "Calificación: " + calificacion + "/5" );
            
            chkVisitado.setSelected( visitado );
            chkVisitado.setText( visitado ? "Visitado: Sí" : "Visitado: No" );
        }
    }

    /**
     * Actualiza los datos que se muestran de un restaurante
     * @param r El restaurante que se debe mostrar. Puede ser null si se limpia la selección.
     */
    public void actualizarRestaurante( Restaurante r )
    {
        if (r != null) {
            this.actualizarRestaurante( r.getNombre( ), r.getCalificacion( ), r.isVisitado( ) );
        } else {
            this.actualizarRestaurante( null, 0, false );
        }
    }

    /**
     * Dada una calificación, retorna una imagen para utilizar en la etiqueta que muestra la calificación
     */
    private ImageIcon buscarIconoCalificacion( int calificacion )
    {
        int califValida = Math.max(1, Math.min(5, calificacion));
        String imagen = "./imagenes/stars" + califValida + ".png";
        return new ImageIcon( imagen );
    }
}