package com.yurpetr.javastream.examples.product;

import com.yurpetr.javastream.Client;
import com.yurpetr.javastream.entity.Product;

/*  Created by JavaStream   */

public class GetProduct {

    public void start() {

        // Инициализация клиента (вебхук токен и аккаунт CRM)
        Client client = new Client("token", "your-account.bitrix24.ru", 1);

        @SuppressWarnings("unused")
      Product product = client.getProductService().getProduct(4);
    }

}
