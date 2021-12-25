package com.yurpetr.javastream.examples.lead;

import com.yurpetr.javastream.Client;
import com.yurpetr.javastream.entity.Lead;
import com.yurpetr.javastream.entity.model.Phone;
import com.yurpetr.javastream.entity.types.Phone_type;
import com.yurpetr.javastream.entity.types.SourceID_type;

import java.util.ArrayList;
import java.util.List;

/*  Created by JavaStream   */


public class AddNewLead {

    public void start() {

        // Инициализация клиента (вебхук токен и аккаунт CRM)
         Client client = new Client("token", "your-account.bitrix24.ru", 1);

        // Создание ного Лида
        Lead lead = new Lead();
        lead.setTITLE("Test2");
        lead.setSOURCE_ID(SourceID_type.ADVERTISING.getCode());
        lead.setADDRESS("England, Rosenberg str, 100");
        Phone phone = Phone.builder()
                .VALUE("89110225686").VALUE_TYPE(Phone_type.HOME.getCode()).build();
        List<Phone> listPhones = new ArrayList<>();
        listPhones.add(phone);
        lead.setPHONE(listPhones);


        // Сохранение Лида
        client.getLeadService().addNewLead(lead);
    }


}
