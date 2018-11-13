package collectionsservice;

import java.util.List;

public interface IMediaCollectionsService {
    List<CollectionItem> getThemeCollection(String collectionId);

    List<CollectionItem> getThemeCollection(String collectionId, String theme);

    List<CollectionItem> getSegmentCollection(String collectionId);

    List<CollectionItem> getSegmentCollection(String collectionId, List<String> tags);

    List<CollectionItem> getSimilarCollection(String collectionId);
}
