package collectionsservice;

import java.util.List;


public interface IUserMediaCollectionsService {
    List<CollectionItem> getCuratedUserCollection(String userId, int size, CuratedCollectionType curatedCollectionType, String curatedCollectionId);

    List<CollectionItem> getRecommendationsUserCollection(String userId);
}
