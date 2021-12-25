package com.yurpetr.javastream;

import com.yurpetr.javastream.configs.Settings;
import com.yurpetr.javastream.service.ChatService;
import com.yurpetr.javastream.service.CompanyService;
import com.yurpetr.javastream.service.ContactService;
import com.yurpetr.javastream.service.LeadService;
import com.yurpetr.javastream.service.ProductSectionService;
import com.yurpetr.javastream.service.ProductService;


public class Client {

    public Client(String token, String account, Integer rest_ID) {
        Settings.token = token;
        Settings.account = account;
        Settings.rest_ID = rest_ID;
    }

    public ContactService getContactService() {
        return new ContactService();
    }

    public LeadService getLeadService() {
        return new LeadService();
    }

    public CompanyService getCompanyService() {
        return new CompanyService();
    }

    public ProductSectionService getProductSectionService() {
        return new ProductSectionService();
    }

    public ProductService getProductService() {
        return new ProductService();
    }

    public ChatService getChatService() {
        return new ChatService();
    }

}
