package webcloud.bestrestaurent.store.domain;

import java.util.List;

public interface StoreRepositoryCustom {

    List<Store> getStores(int page);
}
