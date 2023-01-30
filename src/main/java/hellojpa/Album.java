package hellojpa;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Album extends Item{
    private String artist;
}
