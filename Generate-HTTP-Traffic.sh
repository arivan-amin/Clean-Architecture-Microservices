#!/bin/bash

# Define the base URL of the API
BASE_URL="localhost:8080/patients/protected/v1"

# Endpoints and corresponding methods
declare -A endpoints=(
#    ["/profiles"]="GET"
#    ["/profiles/f8c3de3d-1fea-4d7c-a8b0-29f63c4c2387"]="DELETE"
    ["/accounts/715223be-45b5-43a3-bcb5-2d75b04a0c42"]="GET"
#    ["/profiles"]="POST"
#    ["/profiles/f8c3de3d-1fea-4d7c-a8b0-29f63c4c9876"]="PUT"
)

# Data for POST and PUT requests
POST_DATA='{"firstName": "Tiara","lastName": "Adams","email": "Sage_Hansen@gmail.com","dateOfBirth": "2024-09-16","gender": "MALE","address": "Tromp View"}'
PUT_DATA='{"firstName": "Adam","lastName": "Jason","email": "Smith_Hansen@gmail.com","dateOfBirth": "2024-10-16","gender": "FEMALE","address": "Lake Valley"}'

# Function to send API requests
send_request() {
    local endpoint=$1
    local method=$2
    local url="${BASE_URL}${endpoint}"

    case $method in
        GET)
            echo "Sending GET request to ${url}"
            curl -X GET "${url}" -H "Content-Type: application/json"
            ;;
        POST)
            echo "Sending POST request to ${url}"
            curl -X POST "${url}" -H "Content-Type: application/json" -d "${POST_DATA}"
            ;;
        PUT)
            echo "Sending PUT request to ${url}"
            curl -X PUT "${url}" -H "Content-Type: application/json" -d "${PUT_DATA}"
            ;;
        DELETE)
            echo "Sending DELETE request to ${url}"
            curl -X DELETE "${url}" -H "Content-Type: application/json"
            ;;
        *)
            echo "Unsupported HTTP method: ${method}"
            ;;
    esac
}

while true; do
    for endpoint in "${!endpoints[@]}"; do
        send_request "$endpoint" "${endpoints[$endpoint]}"
        echo -e "\n"
    done

    echo -e "Iteration finished \n"
    echo -e "\n"
    sleep 0.5
done
