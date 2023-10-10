package Entity;

import java.util.Date;

public class DeathNotice {
    private Date deathDate;
    private String nhi;


    public DeathNotice(String nhi, Date deathDate) {
        this.deathDate = deathDate;
        this.nhi = nhi;
    }
}


