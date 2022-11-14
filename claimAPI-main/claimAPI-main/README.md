# Testing

Load postman
Make a post request using: 
> localhost:8080/claims/add 
to add data

Within the request body add the following as raw JSON:

>{
    "VIN": "32763236",
    "insuranceNo":"8172871931",
    "claimAmount" : 20000
}

Make a post request using: 
> localhost:8080/claims/all

to select a claim based on insuranceNo and VIN number

Within the request body add the following as raw JSON:

>{
    "VIN": "32763236",
    "insuranceNo":"8172871931",
}

Make a GET request using: 
> localhost:8080/claims/allUsers

to display all claims


Make a GET request using: 
> localhost:8080/claims/deleteUser

Within the request body add the userID of the user you wish to delete

>{
    "userID" : 1
}
