package com.guma.desarrollo.caronte;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by maryan.espinoza on 27/12/2016.
 */

public class LeadsRepository {
    private static LeadsRepository repository = new LeadsRepository();
    private HashMap<String, Lead> leads = new HashMap<>();

    public static LeadsRepository getInstance() {
        return repository;
    }

    private LeadsRepository() {
        for (int i=0;i<=100;i++){
            saveLead(new Lead("CLIENTE " + i, "Direccion.", "información", R.drawable.indicador));

        }
    }

    private void saveLead(Lead lead) {
        leads.put(lead.getId(), lead);
    }

    public List<Lead> getLeads() {
        return new ArrayList<>(leads.values());
    }
}
