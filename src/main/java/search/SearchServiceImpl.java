package search;

import base.AccountServiceError;
import base.DBService;
import base.SearchService;
import database.DBServiceImpl;
import database.ImageDataSet;
import messageSystem.Abonent;
import messageSystem.Address;
import messageSystem.MessageSystem;
import resourse.DataBase;
import resourse.ResourceFactory;

import java.util.ArrayList;

public class SearchServiceImpl implements SearchService, Abonent {

    private final Address address = new Address();
    private final MessageSystem messageSystem;

    private DBService dbService;

    public Address getAddress() {
        return address;
    }

    public MessageSystem getMessageSystem() {
        return messageSystem;
    }

    public SearchServiceImpl(MessageSystem ms) {
        this.messageSystem = ms;
        messageSystem.addService(this);
        messageSystem.getAddressService().registerSearchService(this);
        try {
            DataBase dataBase = (DataBase) ResourceFactory.instance().get("./data/dataBase.xml");
            dbService = new DBServiceImpl(dataBase.getHost(), dataBase.getPort(), dataBase.getUser(), dataBase.getName(), dataBase.getPassword());
        } catch (Exception e) {
            System.out.println("Exception in SearchService.SearchServiceImpl: " + e.getMessage());
        }
    }

    public SearchServiceResponse findImage(String query) throws Exception {
        try {
            System.out.println(query);
            return new SearchServiceResponse<ArrayList<ImageDataSet>>(true, dbService.findImageByNameLike(query));
        } catch (Exception e) {
            return new SearchServiceResponse<SearchServiceError>(false, SearchServiceError.ServerError);
        }
    }

    @Override
    public void run() {

    }
}
