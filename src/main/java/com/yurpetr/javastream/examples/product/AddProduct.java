package com.yurpetr.javastream.examples.product;

import com.yurpetr.javastream.Client;
import com.yurpetr.javastream.entity.Product;

/*  Created by JavaStream   */

public class AddProduct {

    public void start() {

         // Инициализация клиента (вебхук токен и аккаунт CRM)
         Client client = new Client("token", "your-account.bitrix24.ru", 1);

        Product product = new Product();
        product.setNAME("MainRouter");
        client.getProductService().addProduct(product);
    }

}
