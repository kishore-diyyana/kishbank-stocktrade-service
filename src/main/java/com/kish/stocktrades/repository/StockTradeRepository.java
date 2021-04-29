package com.kish.stocktrades.repository;

import com.kish.stocktrades.model.StockTrade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockTradeRepository extends JpaRepository<StockTrade, Long> {

    /**
     * Finds trade by using the given type as a search criteria.
     * @param type
     * @return  A list of trades whose type is an exact match with the given trade type.
     *          If no type is found, this method returns null.
     */
    public List<StockTrade> findByType(String type);

    /**
     * Finds trade by using the given userId as a search criteria.
     * @param
     * @param userId
     * @return  A list of trades whose userId is an exact match with the given trade userId.
     *          If no userId is found, this method returns null.
     */
    public List<StockTrade> findByUserId(Integer userId);

    /**
     * Finds trade by using the given type and userId as a search criteria.
     * @param type
     * @param userId
     * @return A list of trades whose type and userId is an exact match with the
     *  given trade type as well as  userId.
     *  If no userId is found, this method returns null.
    */
    public List<StockTrade> findByTypeAndUserId(String type, Integer userId);
}
