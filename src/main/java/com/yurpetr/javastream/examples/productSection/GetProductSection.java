package com.yurpetr.javastream.examples.productSection;

import com.yurpetr.javastream.Client;
import com.yurpetr.javastream.entity.ProductSection;

/*  Created by JavaStream   */

public class GetProductSection {

    public void start() {

         // Инициализация клиента (вебхук токен и аккаунт CRM)
         Client client = new Client("token", "your-account.bitrix24.ru", 1);

         @SuppressWarnings("unused")
         ProductSection productSection = client.getProductSectionService().getProductSection(2);

    }

}
