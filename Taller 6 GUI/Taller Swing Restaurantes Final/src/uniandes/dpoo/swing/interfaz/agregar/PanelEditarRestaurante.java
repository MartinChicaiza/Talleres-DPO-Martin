package uniandes.dpoo.swing.interfaz.agregar;

import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class PanelEditarRestaurante extends JPanel
{
    /**
     * El campo para que el usuario ingrese el nombre del restaurante
     */
    private JTextField txtNombre;

    /**
     * Un selector (JComboBox) para que el usuario seleccione la calificación (1 a 5) del restaurante
     */
    private JComboBox<String> cbbCalificacion;

    /**
     * Un selector (JComboBox) para que el usuario indique si ya visitó el restaurante o no
     */
    private JComboBox<String> cbbVisitado;

    public PanelEditarRestaurante( )
    {
        setLayout( new GridLayout( 3, 2, 5, 5 ) );

        add( new JLabel( "Nombre:" ) );
        txtNombre = new JTextField( 20 );
        add( txtNombre );

        
        add( new JLabel( "Calificación:" ) );
        String[] calificaciones = { "1", "2", "3", "4", "5" };
        cbbCalificacion = new JComboBox<>( calificaciones );
        cbbCalificacion.setSelectedIndex( 4 );
        add( cbbCalificacion );

        add( new JLabel( "¿Visitado?:" ) );
        String[] visitadoOpciones = { "Sí", "No" };
        cbbVisitado = new JComboBox<>( visitadoOpciones );
        cbbVisitado.setSelectedIndex( 1 );
        add( cbbVisitado );
    }

    /**
     * Indica si en el selector se seleccionó la opción que dice que el restaurante fue visitado
     */
    public boolean getVisitado( )
    {
        return cbbVisitado.getSelectedItem( ).equals( "Sí" );
    }

    /**
     * Indica la calificación marcada en el selector
     */
    public int getCalificacion( )
    {
        String calif = ( String )cbbCalificacion.getSelectedItem( );
        return Integer.parseInt( calif );
    }

    /**
     * Indica el nombre digitado para el restaurante
     */
    public String getNombre( )
    {
        return txtNombre.getText( );
    }
}