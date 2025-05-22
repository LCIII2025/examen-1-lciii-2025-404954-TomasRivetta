package org.example.parking;

import java.time.LocalDateTime;
import java.util.*;

public class Estacionamiento {
    private final int capacidadMaxima = 50;
    // PATENTE, TICKET
    private final Map<String, Ticket> vehiculosEstacionados = new HashMap<>();
    // DNI, CLIENTE
    private final Map<String, Cliente> clientesRegistrados = new HashMap<>();

    public boolean ingresarVehiculo(String dni, String nombre, Vehiculo vehiculo) {
        // TODO implementar la logica para registrar el ingreso de un nuevo vehiculo en el parking
        // la capacidad maxima del estacionamiento es de 50 vehiculos, si supera esta cap[acidad retornar FALSE
        // validar que no exista otro vehiculo con la misma patente, es un caso de error, retornar FALSE
        // validar si existe el cliente registrado, agregar el nuevo vehiculo en la lista del cliente existente
        // , caso contrario crear un nuevo registro
        // si el proceso es exitoso retornar TRUE

        boolean ingreso = true;

        if (vehiculosEstacionados.size() == capacidadMaxima || vehiculosEstacionados.containsKey(vehiculo.getPatente())) {
            ingreso = false;
        }
        else{
            Cliente cliente = null;

            if (clientesRegistrados.containsKey(dni)) {

                for (Cliente c : clientesRegistrados.values()) {
                    if (cliente.getDni().equals(dni)) {
                        cliente = c;
                        break;
                    }
                }

            } else{
                cliente = new Cliente(dni, nombre);
                clientesRegistrados.put(dni, cliente);
            }

            cliente.agregarVehiculo(vehiculo);
            Ticket ticket = new Ticket(cliente,vehiculo);
            vehiculosEstacionados.put(vehiculo.getPatente(),ticket);
        }

        return ingreso;
    }

    public Ticket retirarVehiculo(String patente) throws Exception {
        // TODO implementar la lógica para retirar un vehiculo del parking
        // validar que exista la patente, caso contrario arrojar la exception "Vehiculo no encontrado"
        // calcular y retornar el ticket del vehiculoEstacionado (ver Ticket.marcarSalida())

        Ticket ticket = null;

        for (Ticket t : vehiculosEstacionados.values()) {
            if (t.getVehiculo().getPatente().equals(patente)) {
                t.marcarSalida();
                ticket = t;
                vehiculosEstacionados.remove(patente);
                break;
            }
        }
        if (ticket == null) {
            throw new Exception("Vehiculo no encontrado");
        }

        return ticket;
    }

    public List<Ticket> listarVehiculosEstacionados() {
        return new ArrayList<>(vehiculosEstacionados.values());
    }
}
