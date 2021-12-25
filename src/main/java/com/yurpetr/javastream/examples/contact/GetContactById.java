package com.yurpetr.javastream.examples.contact;

import com.yurpetr.javastream.Client;
import com.yurpetr.javastream.entity.Contact;

/*  Created by JavaStream   */

public class GetContactById {

    public void start() {

        // Инициализация клиента (вебхук токен и аккаунт CRM)
        Client client = new Client("token", "your-account.bitrix24.ru", 1);

        //Получаем карточку клиента по ID контакта
        @SuppressWarnings("unused")
      Contact contact = client.getContactService().getContactById(74);
    }
}
