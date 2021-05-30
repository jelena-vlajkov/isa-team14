#STAGE 1

FROM node:alpine as build
WORKDIR /app
COPY . .
RUN npm ci && npm run build --prod


#STAGE 2

FROM nginx:alpine
COPY --from=build /app/dist/frontend /usr/share/nginx/html
EXPOSE 5000