package controller;


import model.livestream.LiveStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import store.LiveStreamStore;

import java.util.List;

@RestController
@RequestMapping("/")
public class LivestreamController {

    @Autowired
    private LiveStreamStore liveStreamStore;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER', 'ROLE_STUDENT')")
    @RequestMapping(path = "get-livestreams", method = RequestMethod.GET)
    public List<LiveStream> getAllLiveStream() {
        return liveStreamStore.getAllLiveStream();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_LECTURER', 'ROLE_STUDENT')")
    @RequestMapping(path = "get-livestreams/{id}", method = RequestMethod.GET)
    public LiveStream getLiveStream(@PathVariable int id) {
        return liveStreamStore.getLiveStream(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_LECTURER', 'ROLE_STUDENT')")
    @RequestMapping(path = "get-livestream-by-room/{id}", method = RequestMethod.GET)
    public LiveStream getLiveStreamByRoomID(@PathVariable int id) {
        return liveStreamStore.getLiveStreamByRoomID(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_LECTURER', 'ROLE_STUDENT')")
    @RequestMapping(path = "create-livestream", method = RequestMethod.POST)
    public ResponseEntity<?> createLiveStream(@RequestBody LiveStream liveStream) {
        return liveStreamStore.createLiveStream(liveStream);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_LECTURER', 'ROLE_STUDENT')")
    @RequestMapping(path = "update-livestream", method = RequestMethod.PUT)
    public ResponseEntity<String> updateLiveStream(@RequestBody LiveStream liveStream) {
        return liveStreamStore.updateLiveStream(liveStream);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_LECTURER', 'ROLE_STUDENT')")
    @RequestMapping(path = "get-livestream-by-username/{username}")
    public List<LiveStream> getLiveStreamsByUsername(@PathVariable String username) {
        return liveStreamStore.getLiveStreamsByUsername(username);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_LECTURER', 'ROLE_STUDENT')")
    @RequestMapping(path = "delete-livestream/{id}", method = RequestMethod.DELETE)
    public void deleteLiveStream(@PathVariable int id) {
        liveStreamStore.deleteLiveStream(id);
    }
}
