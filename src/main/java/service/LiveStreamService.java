package service;

import model.livestream.LiveStream;
import model.user.Users;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class LiveStreamService {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private UserService userService;

    @Autowired
    ResponseService responseService;

    public List<LiveStream> getAllLiveStream() {
        Query query = sessionFactory.getCurrentSession().createQuery("from LiveStream");
        return query.list();
    }

    public ResponseEntity<?> createLiveStream(LiveStream liveStreamReq) {
        if (liveStreamReq.getPublisher().getId() < 1) {
            return responseService.handleBadRequest("Invalid publisher ID");
        } else {
            Users publisher = userService.findUserByID(liveStreamReq.getPublisher().getId());
            if (publisher == null) {
                return responseService.handleNotFoundEntityRequest("Cannot find the publisher in the request");
            } else {
                LiveStream liveStream = new LiveStream();
                liveStream.setTitle(liveStreamReq.getTitle());
                liveStream.setDescription(liveStreamReq.getDescription());
                liveStream.setRoomID(liveStreamReq.getRoomID());
                liveStreamReq.setUrl(liveStreamReq.getUrl());
                liveStream.setStatus(liveStreamReq.getStatus());
                liveStream.setPublisher(publisher);
                publisher.getLiveStreams().add(liveStream);
                sessionFactory.getCurrentSession().save(liveStream);
                return ResponseEntity.ok().body(liveStream);
//                return responseService.handleSuccessRequest();
            }
        }
    }

    public ResponseEntity<String> updateLiveStream(LiveStream liveStreamReq) {
        if(liveStreamReq.getId() < 1) {
            return responseService.handleBadRequest("Invalid live stream ID");
        } else {
            LiveStream liveStream = findLiveStreamByID(liveStreamReq.getId());
            if (liveStream == null) {
                return responseService.handleNotFoundEntityRequest("Cannot find the live stream in the request");
            } else {
                liveStream.setDescription(liveStreamReq.getDescription());
                liveStream.setTitle(liveStreamReq.getTitle());
                liveStream.setUrl(liveStreamReq.getUrl());
                liveStream.setStatus(liveStreamReq.getStatus());
                sessionFactory.getCurrentSession().update(liveStream);
                return responseService.handleSuccessRequest();
            }
        }
    }


    public LiveStream findLiveStreamByID(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from LiveStream as l where l.id = :id");
        query.setInteger("id", id);
        return (LiveStream) query.uniqueResult();
    }

    public LiveStream getLiveStream(int id) {
        return findLiveStreamByID(id);
    }

    public LiveStream getLiveStreamByRoomID(int roomID) {
        Query query = sessionFactory.getCurrentSession().createQuery("from LiveStream as l where l.roomID = :id");
        query.setInteger("id", roomID);
        return (LiveStream) query.uniqueResult();
    }

    public List<LiveStream> getLiveStreamsByUsername(String username) {
        Query query = sessionFactory.getCurrentSession().createQuery("from LiveStream as l group by l.publisher");
        query.setString("username", username);
        return query.list();
    }


    public void deleteLiveStream(int id) {
        LiveStream livestream = findLiveStreamByID(id);
        sessionFactory.getCurrentSession().delete(livestream);
    }
}
