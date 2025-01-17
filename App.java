import java.text.DecimalFormat;
import java.util.Scanner;

public class App {

    // SCANNER PARA ENTRADA DE DATOS DEL USUARIO
    static Scanner entry = new Scanner(System.in);

    // ARREGLO DE PLANETAS Y SUS DISTANCIAS DESDE LA TIERRA
    static String[] planetas = { "Mercurio", "Venus", "Marte", "Jupiter", "Saturno", "Urano", "Neptuno" };
    static long[] distancias = { 77000000L, 41000000L, 55000000L, 588000000L, 1275000000L, 2900000000L, 4500000000L };

    // INFORMACION DE LAS NAVES
    static String[] naves = { "Nave Nebulon", "Nave Eclipse", "Nave Aurora" };
    static int[] capacidades = { 150, 300, 350 };
    static double[] velocidades = { 35000, 43000, 50000 };

    // INFORMACION DE LOS RECURSOS
    static long combustible = 100_000_000; // Unidades iniciales de combustible
    static long oxigeno = 100_000_000; // Unidades iniciales de oxígeno

    // VARIABLES PARA ALMACENAR DATOS
    static String naveSeleccionada;
    static String planetaSeleccionado;
    static long distanciaPlanetaSeleccionado;
    static double velocidadNaveSeleccionada;
    static int opcion;
    static int opcionNave;

    public static void main(String[] args) throws Exception {

        menuPrincipal(); // MOSTRAR MENU PRINCIPAL

    }

    public static void menuPrincipal() {
        boolean salir = false;
        boolean opcion1Seleccionada = false; // CONTROLA SELECCION PLANETAS
        boolean opcion2Seleccionada = false; // CONTROLA SELECCION NAVES

        while (!salir) {
            System.out.println("\n--- Menu Principal ---");
            System.out.println("1. Seleccionar planeta de destino");
            System.out.println("2. Seleccionar una nave espacial");
            System.out.println("3. Iniciar el vuelo");
            System.out.println("4. Salir");
            System.out.print("Por favor, elige una opción: ");

            if (entry.hasNextInt()) { // VERIFICAR SI LA ENTRADA ES UN NUMERO
                int opcion = entry.nextInt(); // ALMACENAR LA OPCION DEL USUARIO
                entry.nextLine(); // Limpiar el buffer de entrada

                switch (opcion) {
                    case 1:
                        if (!opcion1Seleccionada) {
                            seleccionPlanetas();
                            System.out.println("_");
                            System.out.println("PRESIONE 8 para CONFIRMAR y VOLVER AL MENU PRINCIPAL");
                            if (entry.hasNextInt() && entry.nextInt() == 8) {
                                opcion1Seleccionada = true; // CONFIRMAR LA SELECCION DEL PLANETA
                                System.out.println("Planeta de destino confirmado correctamente.");
                            } else {
                                System.out.println("Debes presionar 8 para confirmar.");
                            }
                        } else {
                            System.out.println("Ya seleccionaste un planeta de destino. Procede a la opción 2.");
                        }
                        break;

                    case 2:
                        if (opcion1Seleccionada && !opcion2Seleccionada) { // SI EL PLANETA YA ESTA SELECCIONADO PERO LA
                                                                           // NAVE AUN NO
                            seleccionNaves();
                            System.out.println("_");
                            System.out.println("PRESIONE 8 para CONFIRMAR y VOLVER AL MENU PRINCIPAL");
                            if (entry.hasNextInt() && entry.nextInt() == 8) {
                                opcion2Seleccionada = true; // CONFIRMAR SELECCION DE NAVE
                                System.out.println("Nave espacial confirmada correctamente.");
                            } else {
                                System.out.println("Debes presionar 8 para confirmar.");
                            }
                        } else if (!opcion1Seleccionada) { // SI EL PLANETA NO HA SIDO SELECCIONADO
                            System.out.println("Primero debes seleccionar un planeta de destino (opción 1).");
                        } else {
                            System.out.println("Ya seleccionaste una nave. Procede a la opción 3.");
                        }
                        break;

                    case 3:
                        if (opcion1Seleccionada && opcion2Seleccionada) { // SI LA NAVE Y EL PLANETA YA FUERON
                                                                          // SELECCIONADOS
                            iniciarVuelo(); // INICIAR SIMULACION!
                            //System.out.println("¡Vuelo iniciado correctamente!");
                        } else if (!opcion1Seleccionada) { // SI LA OPCION DEL PLANETA NO HA SIDO SELECCIONADO
                            System.out.println("Primero debes seleccionar un planeta de destino (opción 1).");
                        } else { // SI LA NAVE NO HA SIDO SELECCIONADA
                            System.out.println("Debes seleccionar una nave antes de iniciar el vuelo (opción 2).");
                        }
                        break;

                    case 4: // SALIR DEL PROGRAMA
                        System.out.println("Saliendo del programa. ¡Gracias por usar nuestra aplicación!");
                        salir = true;
                        break;

                    default:
                        System.out.println("Opción inválida. Por favor, ingrese un número del 1 al 4.");
                }
            } else {
                System.out.println("Entrada inválida. Por favor, ingrese un número.");
                entry.nextLine(); // LIMPIAR BUFFER
            }
        }
    }

    private static void iniciarVuelo() {
        // MOSTRAR EL TIEMPO ESTIMADO DE VIAJE DESDE LA TIERRA HASTA EL PLANETA
        tiempoDeViaje();

    }

    public static void seleccionNaves() {

        System.out.println("----------------");
        System.out.println("Naves Espaciales");
        System.out.println("----------------");

        // ITERAR LAS NAVES CON EL BUCLE FOR
        for (int i = 0; i < naves.length; i++) {
            // MOSTRAR NAVES
            System.out.println((i + 1) + " " + naves[i]);
            // MOSTRAR CAPACIDAD Y VELOCIDAD
            mostrarCapacidadYVelocidadNaves(i);

        }
        // PEDIR AL USUARIO QUE ELIGA UNA DE LAS NAVES
        System.out.println("Seleccione una nave: ");
        opcionNave = entry.nextInt();
        entry.nextLine();
        System.out.println("Ingrese la cantidad de pasajeros:");
        var pasajeros = entry.nextInt();
        // VERIFICAR SI EL INGRESO DE PASAJEROS EXCEDE LA CAPACIDAD DE LA NAVE
        if (opcionNave == 1 && pasajeros <= 150) {
            System.out.println("Selección exitosa!");
        } else if (opcionNave == 2 && pasajeros <= 300) {
            System.out.println("Selección exitosa!");
        } else if (opcionNave == 3 && pasajeros <= 350) {
            System.out.println("Selección exitosa!");
        } else {
            System.out.println("Cantidad de pasajeros no valida! Intente de nuevo");
            seleccionNaves();
        }

        // almacenar velocidad nave seleccionada
        naveSeleccionada = naves[opcionNave - 1];
        velocidadNaveSeleccionada = velocidades[opcionNave - 1];

        // VERIFICAR QUE SELECCIONO UNA NAVE VALIDA
        if (opcionNave > 0 && opcionNave <= 3) {
            System.out.println("SELECCIONASTE LA NAVE: " + naveSeleccionada);
        } else {

            System.out.println("Opción inválida, intente de nuevo.");
            seleccionNaves();
        }

    }

    private static void mostrarCapacidadYVelocidadNaves(int i) {
        if (i == 0) {
            // velocidad
            System.out.println("Velocidad de la Nave Nebulon: " + velocidades[0] + " km/h");
            // pasajeros
            System.out.println("Capacidad: Pasajeros: " + capacidades[0]);

        } else if (i == 1) {
            System.out.println("Velocidad de la Nave Eclipse: " + velocidades[1] + " km/h");
            // pasajeros
            System.out.println("Capacidad: Pasajeros: " + capacidades[1]);
        } else if (i == 2) {
            System.out.println("Velocidad de la Nave Eclipse: " + velocidades[2] + " km/h");
            // pasajeros
            System.out.println("Capacidad: Pasajeros: " + capacidades[2]);
        }
    }

    public static void seleccionPlanetas() {

        System.out.println("--------");
        System.out.println("Planetas");
        System.out.println("--------");

        // MOSTRAR OPCIONES DE PLANETAS
        System.out.println(
                "Seleccione un Planeta:\n1.Mercurio\n2.Venus\n3.Marte\n4.Jupiter\n5.Saturno\n6.Urano\n7.Neptuno");
        int seleccionPlaneta = entry.nextInt();
        entry.nextLine();
        // ALMACENAR EL PLANETA SELECCIONADO Y LA DISTANCIA
        planetaSeleccionado = planetas[seleccionPlaneta - 1];
        distanciaPlanetaSeleccionado = distancias[seleccionPlaneta - 1];

        // MOSTRAR INFORMACION DEL PLANETA SELECCIONADO
        System.out.println("Has seleccionado: " + planetas[seleccionPlaneta - 1] + "\n");

        // MOSTRAR DISTANCIA DEL PLANETA SELECCIONADO DESDE LA TIERRA
        System.out
                .println("Distancia desde la Tierra: " + distancias[seleccionPlaneta - 1] + " millones de Kilometros");

        // MOSTRAR INFORMACION DEL PLANETA SELECCIONADO
        imprimirInformacionPlaneta(seleccionPlaneta);

    }

    public static void tiempoDeViaje() {

        // tiempo = (distancia / velocidad) / 24

        long tiempoEstimado = (long) (distanciaPlanetaSeleccionado / velocidadNaveSeleccionada) / 24;
        long tiempoMilisegundos = tiempoEstimado * 3600 * 1000;
        long tiempoPorcentaje = 15000;

        System.out.println("TIEMPO DE VIAJE ESTIMADO");
        System.out.println(
                "A continuación se estimarán los tiempos de viaje y recursos para ir al planeta en nuestro sistema solar:");

        System.out.println("El tiempo estimado para ir al planeta " + planetaSeleccionado + " con la "
                + naveSeleccionada + " es de " + tiempoEstimado + " DIAS.");

        System.out.println("Combustible disponible: " + combustible + " unidades."); 
        System.out.println("Oxígeno disponible: " + oxigeno + " unidades."); 

        System.out.println("------------------------------------------------------------");

        //Verificar recursos antes de iniciar la simulación 
        if (verificarRecursos(distanciaPlanetaSeleccionado)) {
             System.out.println("Recursos suficientes. Iniciando la simulación del viaje..."); 
             System.out.println("El tiempo estimado para ir al planeta " + planetaSeleccionado + " con la "
                + naveSeleccionada + " es de " + tiempoEstimado + " DIAS.");
             iniciarSimulacionViaje(tiempoEstimado); 
            } else { 
                System.out.println("No hay suficientes recursos para realizar el viaje."); 
                menuPrincipal();// Volver al menú después del mensaje
            } 
        } 
        static boolean verificarRecursos(long distancia) {
             long combustibleNecesario = distancia / opcionNave; 
             long oxigenoNecesario = distancia / opcionNave; 
             boolean recursosSuficientes = combustible >= combustibleNecesario && oxigeno >= oxigenoNecesario; 
             System.out.println("Combustible necesario: " + combustibleNecesario + " unidades."); 
             System.out.println("Oxígeno necesario: " + oxigenoNecesario + " unidades."); 
             
             return recursosSuficientes; }

    private static void iniciarSimulacionViaje(long tiempoPorcentaje) {
        System.out.println("Iniciando simulacion del viaje....");
        for (int i = 0; i <= 100; i += 10) {

            System.out.println(" 🚀 Progreso del viaje " + i + "% 🚀");

            if (i == 10) {
                System.out.println(
                        "🚀 Hemos alcanzado el 10% del viaje. Todo marcha según lo planeado, pero aún queda un largo camino por recorrer. Disfruta de las vistas del espacio infinito mientras continuamos hacia "
                                + planetaSeleccionado + ". 🌌");
            } else if (i == 20) {

                getAbrocharCinturon();
            } else if (i == 30) {
                System.out.println(
                        "⚠ ¡ATENCION! ⚠ \nEstamos en un momento de gran explosion de asteroides! No te preocupes, tu cinturon te protege.");
            } else if (i == 40) {
                System.out.println(
                        "Estamos a pocas horas para llegar a la mitad del viaje.\nSIGUE DISFRUTANDO DE LAS HERMOSAS VISTAS!");
            } else if (i == 50) {
                System.out.println("Mitad del camino.");
            } else if (i == 60) {
                getEscudos();
            } else if (i == 70) {
                getActivarModoDefensivo();

            } else if (i == 80) {
                System.out.println("Contacto con base espacial cercana. Actualizando coordenadas.");
            } else if (i == 90) {
                System.out.println("🌟 ¡Estamos al 90% del viaje! El destino está a la vista. Los sensores detectan a "
                        + planetaSeleccionado
                        + " en el horizonte. 🪐 Prepárate para la llegada, ajustando sistemas y asegurando la nave. ¡La aventura está por comenzar! 🚀");
            } else if (i == 100) {
                System.out.println("! El viaje ha finalizado con éxito. Disfruta de tu aventura!");

            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("ERROR EN LA SIMULACION! " + e);
            }

        }

        System.out.println("Viaje finalizado con exito!");
        System.out.println("Haz llegado a " + planetaSeleccionado);
    }

    private static void getEscudos() {
        System.out.println(
                "La nave atraviesa una región de radiación cósmica. 🗳  Escribe 'Ajustar escudos'  🗳   para Ajustar los Escudos.");
        String activarEscudos = entry.nextLine();
        if (activarEscudos.equals("Ajustar escudos")) {
            System.out.println(" ⚙  Ajustando escudos.  ⚙ ");
            System.out.println(" ✅   ESCUDOS AJUSTADOS!");
        } else {
            System.out.println("No se pudo activar los Escudos. Vuelva a intentarlo.");
            getEscudos();
        }
    }

    private static void getActivarModoDefensivo() {
        System.out.println(
                "Detectamos una pequeña lluvia de meteoritos. 🗳  PRESIONE G para poner la Nave en modo defensivo.  🗳");
        String activarModoDefensivo = entry.nextLine().trim();

        if (activarModoDefensivo.length() == 1 && activarModoDefensivo.equalsIgnoreCase("G")) {
            System.out.println(" 🛡  Iniciando MODO DEFENSIVO...  🛡 ");
            System.out.println(" 🛡 MODO DEFENSIVO ACTIVADO  🛡  ");
        } else {
            System.out.println("No se pudo activar el modo defensivo. Vuelva a intentarlo.");
            getActivarModoDefensivo();
        }
    }

    private static void getAbrocharCinturon() {
        System.out.println(
                "⚠ ¡ATENCION! ⚠ \nEstamos pasando en estos momentos por una lluvia de ☄ Meteoritos! Abroche su cinturon para salvaguardarse de las turbulencias.\n 🗳  OPRIMA LA LETRA C PARA ABROCHAR EL CINTURON  🗳");
        String abrocharCinturon = entry.nextLine().trim();

        if (abrocharCinturon.length() == 1 && abrocharCinturon.equalsIgnoreCase("C")) {
            System.out.println("Cinturon activado! ✅");
        } else {
            System.out.println("❌ No se pudo activar el cinturon. Vuelva a intentarlo.");
            getAbrocharCinturon();
        }
    }

    public static void imprimirInformacionPlaneta(int seleccionPlaneta) {
        switch (seleccionPlaneta) {
            case 1:

                System.out.println(
                        "Buena eleccion!\n" +
                                "Mercurio, el veloz mensajero del cosmos, es un pequeño y misterioso mundo de extremos. "
                                +
                                "Siendo el planeta más cercano al Sol, su superficie está marcada por cráteres ancestrales "
                                +
                                "y llanuras silenciosas, testigos de incontables impactos cósmicos. Durante el día, el calor "
                                +
                                "alcanza los abrasadores 430 °C, pero al caer la noche, se congela hasta los -180 °C, mostrando "
                                +
                                "el contraste de un planeta sin atmósfera que lo abrace. Mercurio orbita al Sol en tan solo 88 días "
                                +
                                "terrestres, convirtiéndose en el corredor más rápido del sistema solar. Aunque carece de lunas y "
                                +
                                "atmósfera densa, su historia geológica y su soledad en el espacio lo convierten en un enigma "
                                +
                                "fascinante, explorado solo por las misiones más audaces.");

                break;

            case 2:

                System.out.println(
                        "Buena eleccion!\n" +
                                "Venus, la brillante joya del firmamento, es un mundo de contrastes cautivadores y letales.  "
                                +
                                "Conocido como el “gemelo de la Tierra” por su tamaño y composición, su belleza oculta un corazón furioso:"
                                +
                                "un clima infernal con temperaturas que alcanzan los 465 °C, suficientes para derretir plomo, y una atmósfera densa"
                                +
                                "de dióxido de carbono cargada de nubes de ácido sulfúrico. Bajo esta envoltura, su superficie volcánica está salpicada de montañas y vastas"
                                +
                                "llanuras, mientras que vientos huracanados recorren el planeta a velocidades impresionantes. A pesar de su ferocidad, Venus tiene un encanto"
                                +
                                "hipnótico, girando lentamente al revés en una danza única que intriga tanto como desafía a los exploradores espaciales."

                );
                break;
            case 3:
                System.out.println(
                        "Buena eleccion!\n" +
                                "Marte, el intrigante “Planeta Rojo”, es un desierto frío y polvoriento que despierta sueños de exploración y vida más allá"
                                +
                                "de la Tierra. Su paisaje está dominado por imponentes volcanes como el gigantesco Monte Olimpo, profundos cañones"
                                +
                                "como el Valles Marineris y vastas llanuras cubiertas de óxido, que dan al planeta su distintivo color rojizo."
                                +
                                "Aunque su atmósfera es delgada y helada, con temperaturas que caen hasta los -125 °C en sus polos, Marte alguna vez albergó"
                                +
                                "ríos y océanos, dejando rastros de su antiguo pasado acuoso. Cada amanecer y atardecer tiñe su cielo de un azul"
                                +
                                "suave, recordándonos su conexión única con la Tierra. Es un mundo de retos y promesas, un lugar donde la ciencia y la"
                                +
                                "imaginación sueñan con colonias humanas y nuevos comienzos.");
                break;

            case 4:
                System.out.println(
                        "Buena eleccion!\n" +
                                "Júpiter, el gigante de gas y rey del sistema solar, es un mundo majestuoso y turbulento, adornado con nubes de colores"
                                +
                                "vibrantes y tormentas eternas. Su rasgo más famoso, la Gran Mancha Roja, es un huracán colosal que ha rugido por más"
                                +
                                "de 300 años, lo suficientemente grande como para tragarse tres Tierras. Aunque carece de una superficie sólida, Júpiter"
                                +
                                "es un tesoro cósmico con más de 90 lunas, incluyendo a Europa, que podría ocultar un océano bajo su capa de hielo, y Ío, un"
                                +
                                "volcánico espectáculo de fuego. Su inmenso campo magnético y su velocidad de rotación vertiginosa lo convierten en una maravilla"
                                +
                                "inigualable, un coloso que guarda secretos sobre el origen y la dinámica de nuestro sistema solar.");
                break;
            case 5:
                System.out.println(
                        "Buena eleccion!\n" +
                                "Saturno, el hermoso y majestuoso rey de los anillos, es un planeta que deslumbra con su increíble belleza y misterio. Sus vastos"
                                +
                                "anillos, formados por polvo, rocas y hielo, crean un espectáculo visual único que lo convierte en uno de los objetos más fascinantes"
                                +
                                "del cielo nocturno. Saturno es un gigante gaseoso compuesto principalmente de hidrógeno y helio, con una atmósfera marcada por capas"
                                +
                                "de nubes doradas y tormentas violentas. A pesar de su tamaño imponente, con un diámetro 9 veces mayor que el de la Tierra, su baja"
                                +
                                "densidad significa que podría flotar en el agua si existiera un océano lo suficientemente grande. Con más de 80 lunas, incluidas Titán,"
                                +
                                "un mundo misterioso con una atmósfera densa y mares de metano, Saturno sigue siendo un enigma cósmico que desafía nuestra comprensión"
                                +
                                "y despierta la imaginación de los exploradores espaciales");
                break;
            case 6:
                System.out.println(
                        "Buena eleccion!\n" +
                                "Urano, el planeta inclinado, es un mundo de extremos y misterios que orbita de lado, como si estuviera descansando en su"
                                +
                                "eje. Su atmósfera azul verdosa, compuesta principalmente de hidrógeno, helio y metano, le da un tono único y helado. Este"
                                +
                                "gigante gaseoso, que es más de 4 veces el tamaño de la Tierra, es hogar de vientos extremadamente rápidos que alcanzan"
                                +
                                "los 900 km/h, y aunque es conocido por sus bajas temperaturas de hasta -224 °C, no es el planeta más frío del sistema solar."
                                +
                                "Urano tiene una serie de anillos sutiles y más de 20 lunas, siendo Titania la más grande. Este planeta misterioso y lejano, "
                                +
                                "descubierto solo en el siglo XVIII, sigue siendo un desafío para los astrónomos, ya que su inclinación extrema y su ubicación"
                                +
                                "en el borde del sistema solar lo convierten en un objeto fascinante de estudio y exploración");
                break;
            case 7:
                System.out.println(
                        "Buena eleccion!\n" +
                                "Neptuno, el último de los gigantes gaseosos, es un mundo profundo y misterioso que se encuentra en las fronteras del sistema solar. "
                                +
                                "Su atmósfera, rica en hidrógeno, helio y metano, le da su distintivo color azul intenso, y su clima es uno de los más turbulentos"
                                +
                                "del sistema, con vientos que alcanzan hasta 2,100 km/h, los más rápidos conocidos en el sistema solar. A pesar de su lejanía, Neptuno"
                                +
                                "emite más calor del que recibe del Sol, lo que sugiere un núcleo caliente y activo. Su órbita es tan extensa que su año dura 165 años"
                                +
                                "terrestres, pero su día dura solo 16 horas. Neptuno posee 14 lunas conocidas, siendo Tritón la más grande y única en su tipo, ya que"
                                +
                                "orbita en dirección opuesta a la rotación del planeta. Este enigmático gigante sigue siendo un punto de fascinación para los astrónomos,"
                                +
                                "que continúan desentrañando sus secretos, a pesar de que solo ha sido visitado una vez por una nave espacial, Voyager 2.");
                break;

        }
    }

}

