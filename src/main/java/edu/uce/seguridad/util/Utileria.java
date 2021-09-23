package edu.uce.seguridad.util;

import edu.uce.seguridad.model.*;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class Utileria {

    private static Usuario nuevoUsuario;

    public static Usuario generarUsuario(String nombre, String apellido, String role) {
        nuevoUsuario = new Usuario();
        nuevoUsuario.setNombreUsuario(generarNombreUsuario(nombre, apellido));
        nuevoUsuario.setContrasena(generarContrasena());
        nuevoUsuario.setRole(role);
        return nuevoUsuario;
    }

    public static String generarNombreUsuario(String nombre, String apellido) {
        char[] letra = new char[2];
        // Obtengo la primero y ultima letra del nombre
        letra[0] = nombre.toLowerCase().charAt(0);
        letra[1] = nombre.toLowerCase().charAt(nombre.length() - 1);

        Random r = new Random();
        int numero1 = r.nextInt(50) + 1; // Numero entre 1 y 50
        int numero2 = r.nextInt(50) + 1;
        StringBuilder nombreUsuario = new StringBuilder();
        // Lo concateno con el apellido
        nombreUsuario.append(letra[0])
                .append(apellido.toLowerCase().trim())
                .append(letra[1]).append(numero1 += numero2);
        return nombreUsuario.toString();
    }

    public static String generarContrasena() {
        Random r = new Random();
        StringBuilder pass = new StringBuilder();
        for (int i = 0; i < 2; i++) {
            pass.append(r.nextInt(10) + 1);
        }
        for (int i = 0; i < 3; i++) {
            pass.append((char) (r.nextInt(26) + 'A'));
        }
        return pass.toString();
    }

    public static Map<String, List<Estimacion>> establecerEstimaciones(Recurso recurso) {
        HashMap<String, List<Estimacion>> estimaciones = new HashMap<>();

        // TODO: Reconozco que este código es una basura pero cumple su trabajo, se aceptan mejoras XD - Ya esta mejorado mi llave @ByErick

        recurso.getRecursos().forEach((llave, valor) -> { // Recorro el mapa que me llega del form 3.1

            List<Estimacion> estimacionLista = valor.stream().map(getRecurso -> { // mediante map puedo obtener cada valor de la lista, define el predicado
                return new EstimacionDano().definirEstimacion(getRecurso.getNombre(), 0, 0, 0, false); // isntancio un objeto de tipo Estimacion
            }).collect(Collectors.toList()); // Lo convierto en lista

            estimaciones.put(llave, estimacionLista); // agrego la llave y la estimacion

        });

        return estimaciones;
    }

    public static List<CostoRecuperacion> establecerRecursos(Recurso pojo) {
        List<CostoRecuperacion> recurs = new ArrayList<>();

        pojo.getRecursos().forEach((llave, valor) -> {

            List<CostoRecuperacion> recurso = valor.stream()
                    .map(getRecurso -> new CostoRecuperacion(getRecurso.getNombre(), 0, "")) // se debe guardar aquí
                    .collect(Collectors.toList());
            recurs.addAll(recurso);

        });
        return recurs;
    }

    public static FondosDistribucion getFondoTotal(BigDecimal montoTotal) {
        FondosDistribucion fondosDistribucion = new FondosDistribucion();
        fondosDistribucion.setMonto(montoTotal);
        fondosDistribucion.setTipo("Total Fondos Disponibles (A)");
        fondosDistribucion.setOtros("N/A");
        return fondosDistribucion;
    }

    public static double calcularBalance(EstatusFinanciero estatusFinanciero) {
        return estatusFinanciero.getFondosDisponiblesA() -
                estatusFinanciero.getCostoRecuperacionB() -
                estatusFinanciero.getGastosOrdinariosC();
    }

    public static List<DataAmenazas> agregarAmenazasGeneral(BiaListaAmenazas amenazas) {
        List<DataAmenazas> dataAmenazasGeneral = new ArrayList<>();

        amenazas.getLista().forEach((clave, valor) -> {

            List<DataAmenazas> dataAmenazas = valor.stream().map(getAmenaza ->
                    new DataAmenazas(getAmenaza.getTipoAmenaza(), 0, 0, 0)
            ).collect(Collectors.toList());

            dataAmenazasGeneral.addAll(dataAmenazas);

        });
        return dataAmenazasGeneral;
    }

    public static List<DataRecursos> recursosDefault() {
        List<String> dataDefault = Arrays.asList("Inmuebles", "Equipos", "Tecnológico", "Humanos", "Primordiales", "Secundarios");
        return dataDefault.stream().map(data -> new DataRecursos(new DataAmenazas(data, 0, 0, 0), 0, 0)).collect(Collectors.toList());
    }

    public static List<FormularioRevision> formulariosDefault() {
        HashMap<Integer, String> datos = new HashMap<>();
        datos.put(1, "Marco teorico del PCN, Proposito, Alcance, y equipo");
        datos.put(2,"Actividades Prioritarias y Tiempos de Recuperación Ideales");
        datos.put(3, "Recursos necesarios para PCN");
        datos.put(4, "Evaluación de riesgos");
        datos.put(5, "Protección antes del Desastre y Mitigación");
        datos.put(6, "Respuesta de Emergencia ante el desastre");
        datos.put(7, "Estrategias para resumir operaciones prematuramente");
        datos.put(8, "Estar preparado financieramente");
        datos.put(9, "Practica tu Plan");
        datos.put(10, "Monitorea, Revisa y Mejora");

        List<FormularioRevision> lista = new ArrayList<>();

        datos.forEach((llave, valor) -> {
            lista.add(new FormularioRevision(llave, valor, relacionarForms(llave), "No completado", "", ""));
        });
        return lista;
    }

    public static String relacionarForms(int llave) {
        List<String> forms = Arrays.asList("1-1",
                "2-1\n2-2\n2-3",
                "3-1",
                "4-1\n4-2",
                "5-1",
                "6-1\n6-2\n6-3\n6-4\n6-5\n6-6",
                "7-1\n7-2",
                "8-1\n8-2\n8-3\n8-4\n8-5",
                "9-1",
                "10-1\n10-2");
        return forms.get(llave-1);
    }

}
