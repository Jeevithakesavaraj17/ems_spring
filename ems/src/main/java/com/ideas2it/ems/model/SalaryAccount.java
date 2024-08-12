package com.ideas2it.ems.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <p>
 * This class represents salary account details of the employee
 * id               id for the account detail of the employee
 * accountNumber    employee's Account number
 * ifscCode         IFSC code of the account
 * </p>
 *
 * @author Jeevithakesavaraj
 */

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
@Entity
@Table(name = "salary_account")
public class SalaryAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "account_number")
    private long accountNumber;

    @Column(name = "ifsc_code")
    private String ifscCode;

    @Override
    public String toString() {
        return "Account Number : " + accountNumber
                + "\nIFSC Code : " + ifscCode;
    }
}