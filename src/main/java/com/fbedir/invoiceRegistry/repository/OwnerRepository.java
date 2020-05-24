package com.fbedir.invoiceRegistry.repository;

import com.fbedir.invoiceRegistry.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface OwnerRepository extends JpaRepository<Owner, Long> {

    Owner findFirstByFirstNameIgnoreCaseAndLastNameIgnoreCase(String firstName, String lastName);

    Owner findFirstByFirstNameIgnoreCaseAndLastNameIgnoreCaseAndEmail(String firstName, String lastName, String email);

    @Query(value = "select o.first_name   as firstName," +
            "       o.last_name    as lastName," +
            "       o.email        as email," +
            "       b.bill_no      as billNo," +
            "       b.amount       as amount," +
            "       b.product_name as productName," +
            "       b.bill_state   as billState," +
            "       a.name         as name," +
            "       a.surname      as surname" +
            "       from owner_bill as ob " +
            "       left join owner o on ob.owner_id = o.id" +
            "       left join bill b on ob.bill_id = b.id" +
            "       left join accountant a on b.accountant_id = a.id", nativeQuery = true)
    List<Map<String, Object>> findAllBill();

    @Query(value = " select o.first_name   as firstName, " +
            " o.last_name    as lastName, " +
            " o.email        as email," +
            " b.bill_no      as billNo, " +
            " b.amount       as amount, " +
            " b.product_name as productName, " +
            " b.bill_state   as billState, " +
            " a.name         as name, " +
            " a.surname      as surname " +
            " from owner_bill as ob " +
            " left join owner o on ob.owner_id = o.id " +
            " left join bill b on ob.bill_id = b.id " +
            " left join accountant a on b.accountant_id = a.id " +
            " where b.bill_state = :billState ", nativeQuery = true)
    List<Map<String, Object>> findAllBillByState(@Param("billState") Boolean state);


    @Query(value = "select o.first_name   as firstName, " +
            " o.last_name    as lastName, " +
            " o.email        as email, " +
            " b.bill_no      as billNo, " +
            " b.amount       as amount, " +
            " b.product_name as productName, " +
            " b.bill_state   as billState, " +
            " a.name         as name, " +
            " a.surname      as surname " +
            " from owner_bill as ob " +
            " left join owner o on ob.owner_id = o.id " +
            " left join bill b on ob.bill_id = b.id " +
            " left join accountant a on b.accountant_id = a.id " +
            " where o.first_name = :firstName " +
            " and o.last_name = :lastName " +
            " and o.email = :email ", nativeQuery = true)
    List<Map<String, Object>> findAllBillByOwnerState(@Param("firstName") String firstName,
                                                      @Param("lastName") String lastName,
                                                      @Param("email") String email);
}
