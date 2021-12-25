package com.yurpetr.javastream.examples.product;

import com.yurpetr.javastream.Client;
import com.yurpetr.javastream.entity.Product;

/*  Created by JavaStream   */

public class UpdateProduct {

    public void start() {

        // Инициализация клиента (вебхук токен и аккаунт CRM)
        Client client = new Client("token", "your-account.bitrix24.ru", 1);

        Product product = client.getProductService().getProduct(4);
        product.setCODE("utr256587");
        product.setNAME("Super Router");
        client.getProductService().updateProduct(product);
    }

}
