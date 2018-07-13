select
  orders.customerid,
  sum(orderdetails_products.quantity * orderdetails_products.price) total_buy
from (
      select
          products.productid,
          orderdetails.orderid,
          orderdetails.quantity,
          products.price
      from orderdetails
      inner JOIN products ON
          orderdetails.productid = products.productid
      ) orderdetails_products
inner join orders on
    orders.orderid = orderdetails_products.orderid

group by orders.customerid
order by total_buy desc


        
