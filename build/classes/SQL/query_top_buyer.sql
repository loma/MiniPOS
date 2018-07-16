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

--ilamkeo
SELECT p.productid,p.productname, count(1) countSell,sum(od.quantity) sumQty FROM Products p
inner join OrderDetails od on p.ProductID=od.ProductID
inner join Orders o on o.OrderID=od.OrderID

where o.OrderDate between '1997-01-01' and '1997-01-31'

group by p.productid
order by countSell desc


        
