package billsPaymentDB.entites;

import javax.persistence.*;

@Entity
@Table(name = "bank_accounts")
public class BankAccount extends BillingDetail{
    @Column(nullable = false,length = 20)
    private String name;
    @Column(nullable = false,length = 20)
    private String swiftCode;

    public BankAccount() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSwiftCode() {
        return swiftCode;
    }

    public void setSwiftCode(String swiftCode) {
        this.swiftCode = swiftCode;
    }
}
