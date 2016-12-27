package com.guma.desarrollo.caronte;

import com.guma.desarrollo.core.Cliente;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by maryan.espinoza on 27/12/2016.
 */

public class ClientesRepository {
    private static ClientesRepository repository = new ClientesRepository();
    private HashMap<String, Cliente> clents = new HashMap<>();

    public static ClientesRepository getInstance() {
        return repository;
    }

    private ClientesRepository() {
        for (int i=0;i<=100;i++){
            saveLead(new Cliente("CLIENTE " + i, "Direccion.", "información", R.drawable.indicador));

        }
    }

    private void saveLead(Cliente cls) {
        clents.put(cls.getId(), cls);
    }

    public List<Cliente> getClientes() {
        return new ArrayList<>(clents.values());
    }
}
