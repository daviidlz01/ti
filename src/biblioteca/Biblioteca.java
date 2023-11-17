package biblioteca;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Biblioteca {
    private AVLTree<Libro> arbolLibros;
    private AVLTree<Socio> arbolSocios;
    private LinkedList<Libro> listaPrestados;
    private LinkedList<Libro> listaPerdidos;


    public Biblioteca() {
    	this.arbolLibros = new AVLTree<>();
        this.arbolSocios = new AVLTree<>();
        this.listaPrestados = new LinkedList<>();
        this.listaPerdidos = new LinkedList<>(); 

    }

  

    //libro 
    
    public void altaLibro(Libro libro) {
    	arbolLibros.add(libro);
        System.out.println("Libro dado de alta correctamente.");
    }
    public Libro crearLibro() {
    	String codigo = Helper.getString("Ingrese el código del libro: ");
        String titulo = Helper.getString("Ingrese el título del libro: ");
        String autor = Helper.getString("Ingrese el autor del libro: ");
        String editorial = Helper.getString("Ingrese la editorial del libro: ");
        int añoPublicacion = Helper.getInt("Ingrese el año de publicacion");
        String tematica = Helper.getString("Ingrese la tematica del libro: ");
        double precio = Helper.getDouble("Ingrese el precio del libro");

        return new Libro(codigo, titulo, autor,editorial,añoPublicacion,tematica,precio);
    }

    public void bajaLibro(AVLTree<Libro> arbolLibros) {
        String codigoBaja = Helper.getString("Ingrese el código del libro a dar de baja: ");
        
       
        Libro libroBaja = buscarLibroPorCodigo(arbolLibros, codigoBaja);

        if (libroBaja != null) {
           
            boolean prestado = listaPrestados.contains(libroBaja);
            boolean perdido = listaPerdidos.contains(libroBaja);

            if (!prestado && !perdido) {
                
                arbolLibros.remove(libroBaja);
                System.out.println("El libro ha sido dado de baja correctamente.");
            } else {
                System.out.println("El libro no puede ser dado de baja. Está prestado o se encuentra en la lista de perdidos.");
            }
        } else {
            System.out.println("El libro con el código ingresado no se encuentra en la biblioteca.");
        }
    }
    public Libro buscarLibroPorCodigo(String codigo) {
        return buscarLibroPorCodigo(arbolLibros, codigo);
    }
    private Libro buscarLibroPorCodigo(AVLTree<Libro> arbolLibros, String codigo) {
        AVLTree<Libro>.AVLNode<Libro> nodoActual = arbolLibros.root;

        while (nodoActual != null) {
            int comparacion = codigo.compareTo(nodoActual.item.getCodigo());
            
            if (comparacion == 0) {
                return nodoActual.item;
            } else if (comparacion < 0) {
                nodoActual = nodoActual.left;
            } else {
                nodoActual = nodoActual.right;
            }
        }

        return null;
    }
    public void bajaLibroPorRoturaOPerdida(String codigoBaja) {
        // Buscar el libro por su código en el árbol de libros disponibles
        Libro libroBaja = buscarLibroPorCodigo(arbolLibros, codigoBaja);

        if (libroBaja != null) {
            // Verificar si el libro está en la lista de prestados
            if (listaPrestados.contains(libroBaja)) {
                // Eliminar el libro de la lista de prestados
                listaPrestados.remove(libroBaja);

                // Agregar el libro a la lista de perdidos
                listaPerdidos.add(libroBaja);

                System.out.println("El libro ha sido dado de baja por rotura o pérdida correctamente.");
            } else {
                System.out.println("El libro no está actualmente prestado.");
            }
        } else {
            System.out.println("El libro con el código ingresado no se encuentra en la biblioteca.");
        }
    }
    private List<Libro> buscarLibrosPorCriterio(String criterio, String tipo) {
        List<Libro> librosEncontrados = new ArrayList<>();
        switch (tipo) {
            case "titulo":
                buscarLibrosPorSubstring(arbolLibros.root, criterio, librosEncontrados, "titulo");
                break;
            case "autor":
                buscarLibrosPorSubstring(arbolLibros.root, criterio, librosEncontrados, "autor");
                break;
            case "tematica":
                buscarLibrosPorSubstring(arbolLibros.root, criterio, librosEncontrados, "tematica");
                break;
            default:
                System.out.println("Opción inválida.");
                break;
        }
        return librosEncontrados;
    }

    private void buscarLibrosPorSubstring(AVLTree<Libro>.AVLNode<Libro> nodo, String criterio, List<Libro> encontrados, String tipo) {
        if (nodo != null) {
            buscarLibrosPorSubstring(nodo.left, criterio, encontrados, tipo);
            boolean agregar = false;
            switch (tipo) {
                case "titulo":
                    agregar = nodo.item.getTitulo().toLowerCase().contains(criterio.toLowerCase());
                    break;
                case "autor":
                    agregar = nodo.item.getAutor().toLowerCase().contains(criterio.toLowerCase());
                    break;
                case "tematica":
                    agregar = nodo.item.getTematica().toLowerCase().contains(criterio.toLowerCase());
                    break;
                default:
                    break;
            }
            if (agregar) {
                encontrados.add(nodo.item);
            }
            buscarLibrosPorSubstring(nodo.right, criterio, encontrados, tipo);
        }
    }
    private void buscarLibro() {
        int opcionBusqueda = Helper.getInt("Elija el criterio de búsqueda:\n1. Título\n2. Autor\n3. Temática\nIngrese la opción: ");
        String tipoBusqueda;
        String criterioBusqueda = Helper.getString("Ingrese el criterio de búsqueda: ");

        switch (opcionBusqueda) {
            case 1:
                tipoBusqueda = "titulo";
                break;
            case 2:
                tipoBusqueda = "autor";
                break;
            case 3:
                tipoBusqueda = "tematica";
                break;
            default:
                System.out.println("Opción inválida.");
                return;
        }

        List<Libro> librosEncontrados = buscarLibrosPorCriterio(criterioBusqueda, tipoBusqueda);
        if (!librosEncontrados.isEmpty()) {
            System.out.println("Libros encontrados:");
            librosEncontrados.forEach(libro -> System.out.println(libro.toString()));
        } else {
            System.out.println("No se encontraron libros con ese criterio.");
        }
    }
    //socio 
    public void altaSocio(Socio socio) {
        arbolSocios.add(socio);
        System.out.println("Socio dado de alta correctamente.");
    }

    public Socio crearSocio() {
        int numeroAsociado = Helper.getInt("Ingrese el número de asociado del socio: ");
        String dni = Helper.getString("Ingrese el DNI del socio: ");
        String nombre = Helper.getString("Ingrese el nombre del socio: ");
        String apellido = Helper.getString("Ingrese el apellido del socio: ");
        String domicilio = Helper.getString("Ingrese el domicilio del socio: ");
        String telefono = Helper.getString("Ingrese el teléfono del socio: ");

        return new Socio(numeroAsociado, dni, nombre, apellido, domicilio, telefono);
    }
    public void bajaSocio(AVLTree<Socio> arbolSocios) {
        int numeroAsociadoBaja = Helper.getInt("Ingrese el número de asociado del socio a dar de baja: ");
        
        // Buscar el socio por su número de asociado en el árbol de socios
        Socio socioBaja = buscarSocioPorNumeroAsociado(arbolSocios, numeroAsociadoBaja);

        if (socioBaja != null) {
            // Verificar si el socio tiene préstamos pendientes o está activo
            if (socioBaja.getEstado().equalsIgnoreCase("ok")) {
                // Eliminar el socio del árbol de socios
                arbolSocios.remove(socioBaja);
                System.out.println("El socio ha sido dado de baja correctamente.");
            } else {
                System.out.println("El socio no puede ser dado de baja. Tiene préstamos pendientes o no está activo.");
            }
        } else {
            System.out.println("El socio con el número de asociado ingresado no se encuentra en la biblioteca.");
        }
    }

    private Socio buscarSocioPorNumeroAsociado(AVLTree<Socio> arbolSocios, int numeroAsociado) {
        // Realiza la búsqueda del socio por su número de asociado en el AVLTree
        AVLTree<Socio>.AVLNode<Socio> nodoActual = arbolSocios.root;

        while (nodoActual != null) {
            int comparacion = Integer.compare(numeroAsociado, nodoActual.item.getNumeroAsociado());
            
            if (comparacion == 0) {
                // Se encontró el socio con el número de asociado especificado
                return nodoActual.item;
            } else if (comparacion < 0) {
                nodoActual = nodoActual.left; // El número de asociado es menor, buscar en el subárbol izquierdo
            } else {
                nodoActual = nodoActual.right; // El número de asociado es mayor, buscar en el subárbol derecho
            }
        }

        // El socio con el número de asociado especificado no se encontró en el árbol
        return null;
    }
    // Método para mostrar el menú de opciones
    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("----- Menú -----");
            System.out.println("1. Alta de libro");
            System.out.println("2. Baja de libro");
            System.out.println("3. Alta de socio");
            System.out.println("4. Baja de socio");
            System.out.println("5. Realizar préstamo");
            System.out.println("6. Realizar devolución");
            System.out.println("7. Controlar préstamos");
            System.out.println("8. Consultas");
            System.out.println("9. Salir");
            System.out.print("Ingrese la opción deseada: ");

            opcion = Helper.getInt("Ingrese la opción deseada: ");
            switch (opcion) {
                case 1:
                	Libro nuevoLibro = crearLibro();
                	altaLibro(nuevoLibro);
                    break;
                case 2:
                	bajaLibro(arbolLibros);
                    break;
                case 3:
                	Socio nuevoSocio = crearSocio();
                    altaSocio(nuevoSocio);
                    break;
                case 4:
                	bajaSocio(arbolSocios);
                    break;
                case 5:
                	String codigoBaja = Helper.getString("Ingrese el código del libro a dar de baja: ");
                    bajaLibroPorRoturaOPerdida(codigoBaja);
                    // Lógica para realizar préstamo
                    break;
                case 6:
                    // Lógica para realizar devolución
                    break;
                case 7:
                	cargarLibrosAutomaticamente();
                	cargarSociosAutomaticamente();
                    // Lógica para controlar préstamos
                    break;
                case 8:
                    // Lógica para consultas
                	arbolLibros.InOrder2();
                	arbolSocios.InOrder2();
                	//buscar por codigo 
                	//String codigo = Helper.getString("Ingrese el código del libro a buscar:");
                	//System.out.println(buscarLibroPorCodigo(codigo));
                	
                    break;
                case 9:
                	// buscar por titulo,...
                	buscarLibro();
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
                    break;
            }
        } while (opcion != 10);
    }

    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        biblioteca.mostrarMenu();
    }
    public void cargarLibrosAutomaticamente() {
        Libro[] libros = {
        		new Libro("011", "Don Quijote de la Mancha", "Miguel de Cervantes", "Editorial XYZ", 1605, "Novela", 29.99),
                new Libro("002", "Orgullo y prejuicio", "Jane Austen", "Editorial ABC", 1813, "Novela romántica", 24.99),
                new Libro("033", "Cien años de soledad", "Gabriel García Márquez", "Editorial DEF", 1967, "Realismo mágico", 35.50),
                new Libro("004", "El Gran Gatsby", "F. Scott Fitzgerald", "Editorial LMN", 1925, "Ficción", 21.75),
                new Libro("005", "La Odisea", "Homero", "Editorial QRS", -800, "Épica", 19.99),
                new Libro("001", "Crónica de una muerte anunciada", "Gabriel García Márquez", "Editorial GHI", 1981, "Novela", 27.00),
                new Libro("007", "La Metamorfosis", "Franz Kafka", "Editorial JKL", 1915, "Ficción", 18.25),
                new Libro("008", "Matar a un ruiseñor", "Harper Lee", "Editorial UVW", 1960, "Novela", 30.80),
                new Libro("009", "Los juegos del hambre", "Suzanne Collins", "Editorial OPQ", 2008, "Ciencia ficción", 22.50),
                new Libro("010", "El Hobbit", "J.R.R. Tolkien", "Editorial RST", 1937, "Fantasía", 26.50),

            };

        for (Libro libro : libros) {
        	altaLibro(libro); // Agrega cada libro usando el método altaLibro que ya has definido
        }
    }
    public void cargarSociosAutomaticamente() {
        Socio[] socios = {
            new Socio(10, "12345678A", "Juan", "González", "Calle 123", "123456789"),
            new Socio(2, "23456789B", "María", "López", "Carrera 456", "987654321"),
            new Socio(3, "34567890C", "Pedro", "Martínez", "Avenida 789", "567890123"),
            new Socio(4, "45678901D", "Laura", "García", "Plaza 987", "321654987"),
            new Socio(12, "56789012E", "Carlos", "Fernández", "Ruta 654", "789012345"),
            new Socio(6, "67890123F", "Sofía", "Pérez", "Bulevar 321", "234567890"),
            new Socio(7, "78901234G", "Pablo", "Sánchez", "Paseo 159", "654321098"),
            new Socio(8, "89012345H", "Ana", "Rodríguez", "Camino 357", "901234567"),
            new Socio(9, "90123456I", "David", "López", "Autopista 753", "456789012"),
            new Socio(1, "01234567J", "Elena", "Gómez", "Sendero 852", "112233445")
        };

        for (Socio socio : socios) {
            altaSocio(socio); // Agrega cada socio usando el método altaSocio que ya has definido
        }

        System.out.println("¡Se han cargado 10 socios automáticamente!");
    }

}
