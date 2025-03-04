
WITH minimum AS (
    SELECT cust, prod AS min_prod, date AS min_date, state AS min_st, quant AS min_q
    FROM sales
    WHERE quant = (SELECT MIN(quant) FROM sales s2 WHERE s2.cust = sales.cust)
),
maximum AS (
    SELECT cust, prod AS max_prod, date AS max_date, state AS max_st, quant AS max_q
    FROM sales
    WHERE quant = (SELECT MAX(quant) FROM sales s2 WHERE s2.cust = sales.cust)
),
average AS (
    SELECT cust, AVG(quant) AS avg_q
    FROM sales
    GROUP BY cust
)
SELECT mi.cust, mi.min_q, mi.min_prod, mi.min_date, mi.min_st, 
       ma.max_q, ma.max_prod, ma.max_date, ma.max_st, 
       av.avg_q
FROM minimum AS mi
JOIN maximum AS ma ON mi.cust = ma.cust
JOIN average AS av ON mi.cust = av.cust

--------------------------------------------------------------------------------------------------------------------------------------
2-----------------------------------------------------------------


WITH monthly AS (
    SELECT year, month, SUM(quant) AS total
    FROM sales 
    GROUP BY year, month
),
busy AS (
    SELECT year, month AS busy_month, total AS busy_total
    FROM monthly
    WHERE (year, total) IN (
        SELECT year, MAX(total)
        FROM monthly
        GROUP BY year
    )
),
slow AS (
    SELECT year, month AS slow_month, total AS slow_total
    FROM monthly
    WHERE (year, total) IN (
        SELECT year, MIN(total)
        FROM monthly
        GROUP BY year
    )
)
SELECT b.year, b.busy_month, b.busy_total, s.slow_month, s.slow_total
FROM busy AS b
JOIN slow AS s ON b.year = s.year
order by year




--------------------------------------------------------------------------------------------------------------------------------------
3-----------------------------------------------------------------


with monthly as (
	select prod, month, sum(quant) as monthly_total
	from sales
	group by prod, month
),
popular as (
	select m.prod, m.month as pop 
	from monthly as m
	where (prod,monthly_total) IN (select prod,max(monthly_total)
							  		from monthly
							  		group by prod)
),
notpop as (
	select m.prod, m.month as nonpop
	from monthly as m
	where (prod,monthly_total) IN (select prod,min(monthly_total)
							  		from monthly
							  		group by prod)
)
select p.prod as product, p.pop as most_fav_month, np.nonpop as least_fav_month
from popular as p 
join notpop as np on p.prod=np.prod
order by product

--------------------------------------------------------------------------------------------------------------------------------------
4----------------------------------------------------------------------------------
with spring as (
	select cust as customer, prod as product, avg(quant) as spring_avg
	from sales
	where month in (3,4,5)
	group by cust,prod
),
summer as (
	select cust, prod, avg(quant) as summer_avg
	from sales
	where month in (6,7,8)
	group by cust,prod
),
fall as (
	select cust, prod, avg(quant) as fall_avg
	from sales
	where month in (9,10,11)
	group by cust,prod
),
winter as (
	select cust, prod, avg(quant) as winter_avg
	from sales
	where month in (12,1,2)
	group by cust,prod
),
agg as (
	select cust, prod, avg(quant) as average, sum(quant) as total, count(quant) as count
	from sales
	group by cust,prod
)

select sp.customer, sp.product, sp.spring_avg, su.summer_avg, f.fall_avg, w.winter_avg, a.average, a.total, a.count
from spring as sp
join summer as su on sp.customer=su.cust and sp.product= su.prod
join fall as f on su.cust=f.cust and su.prod=f.prod
join winter as w on f.cust=w.cust and f.prod=w.prod
join agg as a on w.cust=a.cust and w.prod=a.prod
order by su.cust, su.prod


-------------------------------------------------------------------------------------------------------------------------------------
5-------------------------------------------------------------------


WITH q1 AS (
    SELECT prod, MAX(quant) AS q1_max
    FROM sales
    WHERE month BETWEEN 1 AND 3
    GROUP BY prod
),
q2 AS (
    SELECT prod, MAX(quant) AS q2_max
    FROM sales
    WHERE month BETWEEN 4 AND 6
    GROUP BY prod
),
q3 AS (
    SELECT prod, MAX(quant) AS q3_max
    FROM sales
    WHERE month BETWEEN 7 AND 9
    GROUP BY prod
),
q4 AS (
    SELECT prod, MAX(quant) AS q4_max
    FROM sales
    WHERE month BETWEEN 10 AND 12
    GROUP BY prod
)
SELECT q1.prod AS PRODUCT,  
       q1.q1_max, s1.date AS q1_date,
       q2.q2_max, s2.date AS q2_date,
       q3.q3_max, s3.date AS q3_date,
       q4.q4_max, s4.date AS q4_date
FROM q1
JOIN sales s1 ON q1.prod = s1.prod AND q1.q1_max = s1.quant AND s1.month BETWEEN 1 AND 3
JOIN q2 ON q1.prod = q2.prod  
JOIN sales s2 ON q2.prod = s2.prod AND q2.q2_max = s2.quant AND s2.month BETWEEN 4 AND 6
JOIN q3 ON q1.prod = q3.prod  
JOIN sales s3 ON q3.prod = s3.prod AND q3.q3_max = s3.quant AND s3.month BETWEEN 7 AND 9
JOIN q4 ON q1.prod = q4.prod  
JOIN sales s4 ON q4.prod = s4.prod AND q4.q4_max = s4.quant AND s4.month BETWEEN 10 AND 12
ORDER BY product;
