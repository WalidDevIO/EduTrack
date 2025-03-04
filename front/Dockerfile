FROM node:lts AS build-stage

WORKDIR /app

COPY . /app

RUN npm run build

FROM node:lts AS final-stage

WORKDIR /app
COPY --from=build-stage /app/dist /app

RUN npm i -g serve

EXPOSE 3000

ENTRYPOINT ["serve", "-s", ".", "-l", "3000"]