package org.example.parking;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.Assert.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EstacionamientoTest {

    @Test
    public void testRetirarVehiculo() throws Exception {
        //TODO test

        Estacionamiento estacionamiento = new Estacionamiento();

        Cliente cliente = new Cliente("44873304","Tomas");
        Vehiculo vehiculo = new Vehiculo("MLM650","FIAT", Vehiculo.Tipo.AUTO);

        estacionamiento.ingresarVehiculo(cliente.getDni(), cliente.getNombre(), vehiculo);

        Ticket esperado = new Ticket(cliente, vehiculo);
        Ticket resultado = estacionamiento.retirarVehiculo("MLM650");

        assertNotNull(resultado);
        assertEquals(esperado.getVehiculo(), resultado.getVehiculo());
        assertEquals(esperado.getCliente().getDni(), resultado.getCliente().getDni());

        assertThrows(Exception.class, () -> estacionamiento.retirarVehiculo("MLM650"));

    }

    @Test
    public void testCalcularPrecio() throws Exception {
        // TODO test

        Cliente clienteUno = new Cliente("44873304","Tomas");
        Vehiculo vehiculoUno = new Vehiculo("MLM650","FIAT", Vehiculo.Tipo.AUTO);
        Ticket ticketOne = new Ticket(clienteUno, vehiculoUno);
        ticketOne.marcarSalida();

        double resultado = ticketOne.calcularPrecio();

        assertNotNull(resultado);
        assertNotEquals(resultado,0.0);
        assertTrue(resultado>0);

    }

}