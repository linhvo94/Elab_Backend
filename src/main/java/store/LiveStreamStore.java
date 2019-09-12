package store;


import model.livestream.LiveStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import service.LiveStreamService;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class LiveStreamStore {

    @Autowired
    private LiveStreamService liveStreamService;

    public List<LiveStream> getAllLiveStream() {
        return liveStreamService.getAllLiveStream();
    }

    public ResponseEntity<?> createLiveStream(LiveStream liveStreamReq) {
        return liveStreamService.createLiveStream(liveStreamReq);
    }

    public ResponseEntity<String> updateLiveStream(LiveStream liveStreamReq) {
        return liveStreamService.updateLiveStream(liveStreamReq);
    }

    public LiveStream getLiveStream(int id) {
        return liveStreamService.getLiveStream(id);
    }

    public LiveStream getLiveStreamByRoomID(int roomID) {
        return liveStreamService.getLiveStreamByRoomID(roomID);
    }

    public List<LiveStream> getLiveStreamsByUsername(String username) {
        return liveStreamService.getLiveStreamsByUsername(username);
    }


    public void deleteLiveStream(int id) {
        liveStreamService.deleteLiveStream(id);
    }
}
