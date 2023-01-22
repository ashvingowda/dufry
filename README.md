# dufry
This Home Assighnmnet is Build Using H2 in memory database


# List of the API'S to be used



1) To Create Product
    http://localhost:8082/createProducts : HTTP METHOD (POST)

    Sample Request Load 

    {
     "name": "samsung",
     " price": 2330
    }

2) TO List all the products created
   http://localhost:8082/listofProducts : HTTP METHOD GET


3) To Update Product
   http://localhost:8082/updateProduct/{id} : HTTP METHOD PUT
   Sample Request Load
   {
   "name": "Samsung",
   "price": 3455
   }
   
4) http://localhost:8082/deleteProduct/{id} : HTTP METHOD DELETE

5) To Place a new Order
   http://localhost:8082/placeOrder:  HTTP METHOD POST
   Sample Request Load 
   {
   "email": "ashwimgwd@gmail.com",
   "productCodes": [1,2]
   }

6) http://localhost:8082/updateOrderStatus/{id}/{PAID, SENT,DELIVERED,CANCELLED,REIMBURSED} : :  HTTP METHOD PUT

7) http://localhost:8082/getAllOrders : HTTP METHOD GET

8) http://localhost:8082/GetCompletedOrders : HTTP METHOD GET

9) http://localhost:8082/getOrderStatus : HTTP METHOD GET
   