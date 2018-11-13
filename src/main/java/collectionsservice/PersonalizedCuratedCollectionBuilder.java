package collectionsservice;

import java.util.ArrayList;
import java.util.List;

public class PersonalizedCuratedCollectionBuilder {
    private List<CollectionItem> curatedCollection;
    private List<CollectionItem> recommendationsCollection;

    public PersonalizedCuratedCollectionBuilder(List<CollectionItem> curatedCollection,
                                                List<CollectionItem> recommendationsCollection){
        this.curatedCollection = curatedCollection;
        this.recommendationsCollection = recommendationsCollection;
    }

    public List<CollectionItem> buildPersonalizedCuratedCollection(int size){
        List<CollectionItem> finalCollection = new ArrayList<CollectionItem>();
        finalCollection.addAll(this.curatedCollection);
        finalCollection.addAll(this.recommendationsCollection);
        return finalCollection;
    }
}
