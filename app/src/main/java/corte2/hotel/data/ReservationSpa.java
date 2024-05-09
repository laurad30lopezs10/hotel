package corte2.hotel.data;

import java.util.Date;
public class ReservationSpa {
    private Date start_date;
    private Date end_date;
    private String Spa;
    private int price;

    public ReservationSpa( Date start_date,int price , String Spa, Date end_date) {
        this.start_date = start_date;
        this.end_date = end_date;
        this.Spa = Spa;
        this.price = price;
    }
    public Date getStartDate() {
        return start_date;
    }
    public Date getEndDate() {
        return end_date;
    }
    public String getServiceType() {
        return Spa;
    }
    public int getPrice(){
        return price;
    }
}
