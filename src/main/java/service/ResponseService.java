package service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ResponseService {

    public ResponseEntity<String> handleBadRequest(String message) {
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> handleDuplicateEntityRequest(String message) {
        return new ResponseEntity<>(message, HttpStatus.CONFLICT);
    }

    public ResponseEntity<String> handleNotFoundEntityRequest(String message) {
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> handleSuccessRequest() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
