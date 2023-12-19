package com.chuan.jpa05.repositories;

import com.chuan.jpa05.models.entities.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface IFoodRepository extends JpaRepository<Food, Integer> {

    @Transactional
    @Modifying
    @Query(value = "delete from tb_foods where ft_id = :ftId", nativeQuery = true)
    int deleteByFTID(@Param("ftId") int ftId);

    @Query(value = "select f_id from tb_foods where ft_id = :ftId", nativeQuery = true)
    List<Integer> findIdFoodsByFTID(@Param("ftId") int ftId);

    @Query(value = "select distinct f.f_id from tb_foods f inner join tb_recipes r on f.f_id = r.f_id \n" +
            "inner join tb_meterials m on r.m_id = m.m_id \n" +
            "where m_name like %:condition%", nativeQuery = true)
    List<Integer> findFIDsByMNAME(@Param("condition") String condition);
}
