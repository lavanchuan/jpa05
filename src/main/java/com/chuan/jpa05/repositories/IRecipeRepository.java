package com.chuan.jpa05.repositories;

import com.chuan.jpa05.models.entities.Food;
import com.chuan.jpa05.models.entities.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface IRecipeRepository extends JpaRepository<Recipe, Integer> {

    @Query(value = "select m_name, quantity, unit_calc from tb_meterials m \n" +
            "            inner join tb_recipes r \n" +
            "            on m.m_id = r.m_id \n" +
            "            where f_id = 2;", nativeQuery = true)
    List<String> findMnameQuantityUnitcalcByFID(@Param("fId") int fId);

    @Transactional
    @Modifying
    @Query(value = "delete from tb_recipes where f_id = :fId", nativeQuery = true)
    int deleteByFID(@Param("fId") int fId);

    @Query(value = "select distinct f.f_id from tb_foods f \n" +
            "inner join tb_recipes r \n" +
            "on f.f_id = r.f_id \n" +
            "inner join tb_meterials m \n" +
            "on r.m_id = m.m_id \n" +
            "where f_name like %:fName% \n" +
            "and m_name like %:mName%", nativeQuery = true)
    List<Integer> findFoodByNameAndMeterial(@Param("fName") String fName, @Param("mName") String mName);

    @Query(value = "select distinct r_id from tb_recipes \n" +
            "where f_id = :fId", nativeQuery = true)
    List<Integer> findRIDByFID(@Param("fId") int fId);

    @Query(value = "select m_name from tb_meterials m \n" +
            "inner join tb_recipes r \n" +
            "on m.m_id = r.m_id \n" +
            "where r_id = :rId", nativeQuery = true)
    String findMMeterialNameByRID(@Param("rId") int rId);
}
