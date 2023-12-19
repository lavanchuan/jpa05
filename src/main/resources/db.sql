drop database if exists db_jpa05;
create database db_jpa05;
use db_jpa05;

select m_name, quantity, unit_calc from tb_meterials m 
inner join tb_recipes r 
on m.m_id = r.m_id 
where f_id = 2;

select m_name, quantity, unit_calc from tb_meterials m 
            inner join tb_recipes r 
            on m.m_id = r.m_id 
            where f_id = 2;

