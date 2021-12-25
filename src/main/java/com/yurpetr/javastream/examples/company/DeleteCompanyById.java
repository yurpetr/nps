package com.yurpetr.javastream.examples.company;

import com.yurpetr.javastream.Client;

/*  Created by JavaStream   */

public class DeleteCompanyById {

    public void start() {

        // Инициализация клиента (вебхук токен и аккаунт CRM)
        Client client = new Client("token", "your-account.bitrix24.ru", 1);


        // Удаляет Лид по его ID (в примере удаляется лид с id=8)
        client.getCompanyService().deleteCompanyById(4);
    }

}
