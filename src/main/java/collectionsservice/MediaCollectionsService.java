package collectionsservice;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MediaCollectionsService implements IMediaCollectionsService {

    @Override
    public List<CollectionItem> getThemeCollection(String collectionId) {
        return getThemeCollection(collectionId, null);
    }

    @Override
    public List<CollectionItem> getThemeCollection(String collectionId, String theme) {
        return createMockCollection("theme");
    }

    @Override
    public List<CollectionItem> getSegmentCollection(String collectionId) {
        return getSegmentCollection(collectionId, null);
    }

    @Override
    public List<CollectionItem> getSegmentCollection(String collectionId, List<String> tags) {
        return createMockCollection("segment");
    }

    @Override
    public List<CollectionItem> getSimilarCollection(String collectionId) {
        return createMockCollection("similar");
    }

    private List<CollectionItem> createMockCollection(String contentId){
        List<CollectionItem> collection =  new ArrayList<CollectionItem>() ;
        for (int i=0; i<5; i++){
            collection.add(createCollectionItem(contentId + "-" + i));
        }
        return collection;
    }

    private CollectionItem createCollectionItem(String contentId){
        CollectionItem collectionItem = new CollectionItem();
        collectionItem.contentId = contentId;
        return collectionItem;
    }
}
