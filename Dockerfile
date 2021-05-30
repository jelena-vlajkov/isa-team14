FROM node:14.11.0 as frontend

WORKDIR /usr/src/atlaspharmacy
COPY frontend/ .
ENV PATH /app/node_modules/.bin:$PATH

COPY frontend/package.json .
RUN npm install
RUN npm install -g @angular/cli@7.3.9
RUN ng build --prod --base-href=/

FROM maven:3.6.3-ibmjava-8-alpine AS atlaspharmacy

WORKDIR /usr/src/atlaspharmacy
COPY . .
COPY --from=frontend /usr/src/atlaspharmacy/dist/frontend ./src/main/resources/static
RUN ["mvn", "package", "-DskipTests"]

FROM openjdk:8-jdk-alpine
WORKDIR /app
COPY --from=atlaspharmacy /usr/src/atlaspharmacy/target/atlaspharmacy-0.0.1-SNAPSHOT.jar ./

EXPOSE 8088

CMD ["java", "-jar","atlaspharmacy-0.0.1-SNAPSHOT.jar"]