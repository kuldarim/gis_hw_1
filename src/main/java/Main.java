package main.java;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.stream.Collectors;

import com.esri.runtime.ArcGISRuntime;
import com.esri.core.geometry.Point;
import com.esri.core.map.Graphic;
import com.esri.core.symbol.SimpleMarkerSymbol;
import com.esri.core.symbol.SimpleMarkerSymbol.Style;
import com.esri.map.GraphicsLayer;
import com.esri.map.JMap;
import com.esri.map.MapOptions;
import com.esri.map.MapOptions.MapType;

public class Main {

  private JFrame window;
  private JMap map;

  public Main() {
    window = new JFrame();
    window.setSize(800, 600);
    window.setLocationRelativeTo(null); // center on screen
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.getContentPane().setLayout(new BorderLayout(0, 0));

    // dispose map just before application window is closed.
    window.addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent windowEvent) {
        super.windowClosing(windowEvent);
        map.dispose();
      }
    });
    
    //Focus Lithuania
    MapOptions mapOptions = new MapOptions(MapType.TOPO/*, 55.0, 24.0, 6*/);
    map = new JMap(mapOptions);

    // Add the JMap to the JFrame's content pane
    window.getContentPane().add(map);
    
    // create and add a graphics layer to the map
    GraphicsLayer myGraphicsLayer = new GraphicsLayer();
    map.getLayers().add(myGraphicsLayer);
    
  //create a point marker symbol (red, size 10, of type circle)
    SimpleMarkerSymbol simpleMarker = new SimpleMarkerSymbol(Color.RED, 10, Style.CIRCLE);
     
    //create a point at x=-302557, y=7570663 (for a map using metres as units; this depends on the spatial reference)
    //create list of points red from file
    List<Point> pointGeometries = FileReader
    	.getPointsFromFile()
    	.stream()
    	.map(p -> new Point(p.x, p.y))
    	.collect(Collectors.toList());
    //Point pointGeometry = new Point(-302557, 7570663);
    pointGeometries
    	.stream()
    	.forEach(p -> myGraphicsLayer.addGraphic(new Graphic(p, simpleMarker)));
     
    //add the graphic to the graphics layer
    pointGeometries
		.stream()
		.forEach(p -> myGraphicsLayer.addGraphic(new Graphic(p, simpleMarker)));
  }

  /**
   * Starting point of this application.
   * @param args
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {

      @Override
      public void run() {
        try {
          Main application = new Main();
          application.window.setVisible(true);
        	//FileReader.getPointsFromFile().stream().forEach( p -> System.out.println(p.y));
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }
}
