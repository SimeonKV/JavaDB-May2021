package gringottsDatabase;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "wizard_deposits",
uniqueConstraints = {@UniqueConstraint(columnNames = {"first_name","last_name"})})
public class WizardDeposits {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name",columnDefinition = "VARCHAR(50)",nullable = false)
    private String firstName;

    @Column(name = "last_name",columnDefinition = "VARCHAR(50)",nullable = false)
    private String lastName;

    @Column(columnDefinition = "TEXT",length = 1000)
    private String notes;

    @Column(nullable = false)
    private Integer age;

    @Column(name = "magic_wand_creator",columnDefinition = "VARCHAR(100)")
    private String magicWandCreator;

    @Column(name = "magic_wand_size")
    private Integer magicWandSize;

    @Column(name = "deposit_group",columnDefinition = "VARCHAR(20)",nullable = false)
    private String depositGroup;

    @Column(name = "deposit_start_date",nullable = false)
    private LocalDateTime depositStartDate;

    @Column(name = "deposit_amount",columnDefinition = "DECIMAL(10,2)",nullable = false)
    private BigDecimal depositAmount;

    @Column(name = "deposit_interest",columnDefinition = "DECIMAL(5,2)",nullable = false)
    private BigDecimal depositInterest;

    @Column(name = "deposit_charge",columnDefinition = "DECIMAL(5,2)",nullable = false)
    private BigDecimal depositCharge;

    @Column(name = "deposit_expirtion_date",nullable = false)
    private LocalDateTime depositExpirationDate;

    @Column(name = "is_deposit_expired",nullable = false)
    private Boolean isDepositExpired;

    public WizardDeposits(){};

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getMagicWandCreator() {
        return magicWandCreator;
    }

    public void setMagicWandCreator(String magicWandCreator) {
        this.magicWandCreator = magicWandCreator;
    }

    public Integer getMagicWandSize() {
        return magicWandSize;
    }

    public void setMagicWandSize(Integer magicWandSize) {
        this.magicWandSize = magicWandSize;
    }

    public String getDepositGroup() {
        return depositGroup;
    }

    public void setDepositGroup(String depositGroup) {
        this.depositGroup = depositGroup;
    }

    public LocalDateTime getDepositStartDate() {
        return depositStartDate;
    }

    public void setDepositStartDate(LocalDateTime depositStartDate) {
        this.depositStartDate = depositStartDate;
    }

    public BigDecimal getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(BigDecimal depositAmount) {
        this.depositAmount = depositAmount;
    }

    public BigDecimal getDepositInterest() {
        return depositInterest;
    }

    public void setDepositInterest(BigDecimal depositInterest) {
        this.depositInterest = depositInterest;
    }

    public BigDecimal getDepositCharge() {
        return depositCharge;
    }

    public void setDepositCharge(BigDecimal depositCharge) {
        this.depositCharge = depositCharge;
    }

    public LocalDateTime getDepositExpirationDate() {
        return depositExpirationDate;
    }

    public void setDepositExpirationDate(LocalDateTime depositExpirationDate) {
        this.depositExpirationDate = depositExpirationDate;
    }

    public Boolean getDepositExpired() {
        return isDepositExpired;
    }

    public void setDepositExpired(Boolean depositExpired) {
        isDepositExpired = depositExpired;
    }
}
