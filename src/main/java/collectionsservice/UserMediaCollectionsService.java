package collectionsservice;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;


import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserMediaCollectionsService implements IUserMediaCollectionsService {

    private final IMediaCollectionsService collectionsService;

    @Override
    public List<CollectionItem> getCuratedUserCollection(String userId, int size, CuratedCollectionType curatedCollectionType, String curatedCollectionId) {
        List<CollectionItem> curatedCollection = this.fetchCuratedCollection(curatedCollectionType, curatedCollectionId);
        List<CollectionItem> recommendationsCollection = this.getRecommendationsUserCollection(userId);

        if (curatedCollection == null || curatedCollection.isEmpty()){
            return null;
        }

        if (recommendationsCollection == null || recommendationsCollection.isEmpty()){
            return this.filterCollection(curatedCollection, size);
        }

        PersonalizedCuratedCollectionBuilder personalizedCuratedCollectionBuilder = new PersonalizedCuratedCollectionBuilder(curatedCollection, recommendationsCollection);

        return personalizedCuratedCollectionBuilder.buildPersonalizedCuratedCollection(size);
    }

    @Override
    public List<CollectionItem> getRecommendationsUserCollection(String userId) {
        return createMockCollection("personalized");
    }

    private List<CollectionItem> fetchCuratedCollection(CuratedCollectionType curatedCollectionType, String curatedCollectionId){
        switch(curatedCollectionType){
            case theme:
                return collectionsService.getThemeCollection(curatedCollectionId);
            case segment:
                return collectionsService.getSegmentCollection(curatedCollectionId);
            case similar:
                return collectionsService.getSimilarCollection(curatedCollectionId);
            default:
                return null;
        }
    }

    private List<CollectionItem> filterCollection(List<CollectionItem> collection, int size){
        return collection.subList(0, size - 1);
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
