package uniandes.dpoo.swing.interfaz.mapa;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import uniandes.dpoo.swing.mundo.Restaurante;

@SuppressWarnings("serial")
public class PanelMapaVisualizar extends JPanel
{
    /**
     * La etiqueta donde se dibuja el mapa y se hacen las señales de los restaurantes
     */
    private JLabel labMapa;

    /**
     * La lista de restaurantes que se están dibujando en el mapa
     */
    private List<Restaurante> restaurantes;

    public PanelMapaVisualizar( )
    {
        setLayout( new BorderLayout() );

        this.labMapa = new JLabel( new ImageIcon( "./imagenes/mapa.png" ) );
        labMapa.setBorder( new LineBorder( Color.DARK_GRAY ) );
        add( labMapa, BorderLayout.CENTER );
    }

    @Override
    public void paint( Graphics g )
    {
        super.paint( g ); 
        Graphics2D g2d = ( Graphics2D )g;

        if (restaurantes != null)
        {
            for( Restaurante r : restaurantes )
            {
                int x = r.getX( );
                int y = r.getY( );

                if (r.isVisitado()) {
                    g2d.setColor( Color.RED ); 
                } 
                else {
                    g2d.setColor( new Color(255, 0, 0, 100) ); 
                }
                
                g2d.fillOval( x - 5, y - 5, 10, 10 ); 
                
                g2d.setColor( Color.BLACK );
                g2d.drawString( r.getNombre( ), x + 8, y + 5 ); 
            }
        }
    }

    /**
     * Actualiza la lista de restaurantes y llama al método repaint() para que se actualice el mapa
     */
    public void actualizarMapa( List<Restaurante> nuevosRestaurantes )
    {
        this.restaurantes = nuevosRestaurantes;
        repaint( );
    }
}