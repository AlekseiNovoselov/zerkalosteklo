package base;

import messageSystem.Abonent;
import search.SearchServiceResponse;

/**
 * Created by aleksei on 08.02.15.
 */
public interface SearchService extends Runnable, Abonent {
    SearchServiceResponse findImage(String query) throws Exception;
}
