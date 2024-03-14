<h1 align="center"> Gas Utility Service </h1>

>### Prerequisites
* ![MySql](https://img.shields.io/badge/DBMS-MYSQL%205.7%20or%20Higher-red)
 * ![SpringBoot](https://img.shields.io/badge/Framework-SpringBoot-green)


* ![Java](https://img.shields.io/badge/Language-Java%208%20or%20higher-yellow)
>### Technologies Used
* java
* Spring Boot
* Hibernate
## Dependencies
The following dependencies are required to run the project:

* Spring Boot Dev Tools
* Spring Web
* Spring Data JPA
* MySQL Driver
* Lombok
* Validation
* spring security
  
## Overview
This guide provides instructions on implementing authorization in your Gas Utility Service application using Spring Security with JSON Web Tokens (JWT).
 Authorization ensures that users have access only to the functionalities they are allowed to use based on their roles.
 - Allow only users with the USER role to access submitServiceRequest and getServiceRequest endpoints.
 - Allow only users with the ADMIN role to access updateServiceRequestStatus and getAllServiceRequests endpoints.

## tables created
- User
- Service_Request

## Usage

After installing and running the application, users can access the following features:

- Submit service requests
- Track the status of service requests
- View account information

  ## Endpoints

### Customers

- `POST /api/customers/register` : Register a new user/customer.
- `POST /api/customers/logIn` : Log in as a customer.

### Service Requests

- `POST /api/service-requests`: Submit a new service request
- `GET /api/service-requests/{id}`: Get details of a service request by ID

### Admin

- `GET /api/admin`: Get all service requests (requires admin role)
- `GET /api/admin/{id}`: Get details of a customer by ID (requires admin role)
- `PUT /api/admin/{id}/update-status`: Update status of a service request (requires admin role)

## Authentication

Gas Utility Service uses JWT (JSON Web Tokens) for authentication. To access protected endpoints, users need to obtain a JWT token by logging in.



![image](https://github.com/ankitSarwar/GasUtilityService_Backend/assets/111841677/8a66fcf3-a0c9-4ebd-a744-16913056ff69)

![image](https://github.com/ankitSarwar/GasUtilityService_Backend/assets/111841677/ddf6878f-2915-4e79-b0a4-f080170135d8)

![image](https://github.com/ankitSarwar/GasUtilityService_Backend/assets/111841677/15e1cc50-8e08-42f4-b3f4-d6b237e73694)

![image](https://github.com/ankitSarwar/GasUtilityService_Backend/assets/111841677/a4cd72e3-e3d4-4703-a222-70b0fef78b2b)


![image](https://github.com/ankitSarwar/GasUtilityService_Backend/assets/111841677/ccdacdcb-0c56-44e8-b9c4-004f75b04a6a)








