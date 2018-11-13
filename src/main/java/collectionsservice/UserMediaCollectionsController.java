package collectionsservice;

import hello.Greeting;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import user.UserHelper;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/user/collections/media")
public class UserMediaCollectionsController {

    private static final String template = "Hello, user %s!";
    private final AtomicLong counter = new AtomicLong();

    private final IUserMediaCollectionsService userCollectionsService;

    private String token;

    private String getUserId() {

        return UserHelper.getUserId(token);

    }

    /// Recommendations specific for a user
    @RequestMapping("/recommendations")
    public Greeting recommendations(@RequestParam(value="name", defaultValue = "Recommendations") String name) {
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }

    /// A list of titles the user may want to watch again
    @RequestMapping("/watchagain")
    public Greeting watchAgain(@RequestParam(value="name", defaultValue = "Watch Again") String name) {
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }

    @RequestMapping("/batch")
    public Greeting batch(@RequestParam(value="name", defaultValue = "Batch") String name) {
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }

    @RequestMapping(value = "/curated/{curatedCollectionType}/{curatedCollectionId}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<CollectionItem> curated(@RequestHeader(value = "Authorization", required = true) String token,
                                        @PathVariable CuratedCollectionType curatedCollectionType,
                                        @PathVariable String curatedCollectionId,
                                        @RequestParam(value="size", defaultValue = "20") int size) {
        this.token = token;
        return userCollectionsService.getCuratedUserCollection(this.getUserId(), size, curatedCollectionType, curatedCollectionId);
    }

}
