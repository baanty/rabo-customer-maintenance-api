# rabo-customer-maintenance-api
Repository for Customer maintenance

## This project will simply give an API to read, update delete a customer of Rabo Bank.

There are 7 API endpints

1) /findAllCustomers
2) /addNewCustomer - with Payload of the customer
3) /findCustomerById/{custpmerId}
4) /findCustomerByFirstNameOrLastName - with Customer payload in the body of the request.
5) /findCustomerByFirstNameOrLastName/{firstName}/{lastName}
6) /updateAddress - with the updated customer in the payload.
