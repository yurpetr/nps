package com.yurpetr.javastream.examples.company;

import com.yurpetr.javastream.Client;
import com.yurpetr.javastream.entity.Company;

/*  Created by JavaStream   */

//@Component
public class GetCompanyById {

    public void start() {

        // Инициализация клиента (вебхук токен и аккаунт CRM)
        Client client = new Client("wovpq8bg7tq7of0b", "b24-v12wfp.bitrix24.ru", 1);


        // Получаем Лид по его ID
        Company company = client.getCompanyService().getCompanyById(2);
        System.out.println("ID = " + company.getID());
    }

    //@PostConstruct
    public void run() {
        start();
    }
}
