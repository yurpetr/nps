package com.yurpetr.javastream.examples.contact;

import com.yurpetr.javastream.Client;

/*  Created by JavaStream   */

public class DeleteContactById {

    public void start() {

        // Инициализация клиента (вебхук токен и аккаунт CRM)
        Client client = new Client("token", "your-account.bitrix24.ru", 1);

        // Удалить Контакт по его ID
        client.getContactService().deleteContactById(72);
    }
}

