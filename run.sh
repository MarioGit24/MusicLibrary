#!/bin/bash

echo "Starting backend services and frontend..."

# Backend services
(cd backend/artist && ./mvnw spring-boot:run) &
(cd backend/recordlabel && ./mvnw spring-boot:run) &
(cd backend/releases && ./mvnw spring-boot:run) &

# Frontend
(cd frontend && npm start) &

wait
