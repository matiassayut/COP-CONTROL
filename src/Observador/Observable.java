package Observador;
  /*Esta clase abstracta representa los objetos susceptibles de ser
  *observados. Incorpora metodos para agregar y eliminar observadores
  *y un metodo de notificacion. La asociacion Observable-Observadores
  *se implementa mediante un vector de observadores
  */
 
  import java.util.ArrayList;
 
  public abstract class Observable {
	  
	  //Este atributo privado mantiene el vector con los observadores
	  private ArrayList<Observador> _observadores;
  //El constructor crea el vector con la asociacion Observable-Observador
  public Observable() {
      _observadores = new ArrayList<Observador>();
  }
 
  //Agregar y eliminar sencillamente operan sobre vector _observadores...
  public void agregarObservador(Observador o) {
      _observadores.add(o);
  }
 
  public void eliminarObservador(Observador o) {
      _observadores.remove(o);
  }
 
  //Notificacion: Para cada observador se invoca el método actualizar().
  public void notificarObservadores() {
      for (Observador o:_observadores) {
         o.actualizar();
      }
  }

}