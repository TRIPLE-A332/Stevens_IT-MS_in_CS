WITH aggregated_sales AS (
    SELECT 
        cust,
        prod,
        month,
        AVG(quant) AS avg_quant
    FROM sales
    GROUP BY cust, prod, month
),
reference AS (
    SELECT 
        curr.cust,
        curr.prod,
        curr.month,
        
        (SELECT AVG(avg_quant) 
         FROM aggregated_sales 
         WHERE cust = curr.cust 
           AND prod = curr.prod 
           AND month = curr.month - 1) AS prev_avg,
        
        (SELECT AVG(avg_quant) 
         FROM aggregated_sales 
         WHERE cust = curr.cust 
           AND prod = curr.prod 
           AND month = curr.month + 1) AS next_avg
    FROM aggregated_sales AS curr
)
select curr.cust , curr.prod, curr.month, (Select prev_avg from reference where prev), r.next_avg 
from reference as r
join aggregated_sales as curr on curr.cust = r.cust AND curr.prod = r.prod AND curr.month = r.month
order by cust,prod,month


