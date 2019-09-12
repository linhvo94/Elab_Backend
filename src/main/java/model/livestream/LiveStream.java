package model.livestream;

import model.user.Users;
import org.hibernate.annotations.Cascade;
import javax.persistence.*;

@Entity
@Table(name = "livestream")
public class LiveStream {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String title;

    @Column(length = 1000)
    private String description;

    @Column
    private int roomID;

    @Column
    private String url;

    @Column
    private String status;

    @ManyToOne(fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Users publisher;

    public LiveStream() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Users getPublisher() {
        return publisher;
    }

    public void setPublisher(Users publisher) {
        this.publisher = publisher;
    }
}
