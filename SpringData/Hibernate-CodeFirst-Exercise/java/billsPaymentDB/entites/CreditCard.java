package billsPaymentDB.entites;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "credit_cards")
public class CreditCard extends BillingDetail {
    @Column(nullable = false,length = 20)
    private String type;
    private Integer expirationMonth;
    private Integer expirationYear;

    public CreditCard() {

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getExpirationMonth() {
        return expirationMonth;
    }

    public void setExpirationMonth(Integer expirationMonth) {
        this.expirationMonth = expirationMonth;
    }

    public Integer getExpirationYear() {
        return expirationYear;
    }

    public void setExpirationYear(Integer expirationYear) {
        this.expirationYear = expirationYear;
    }



}
