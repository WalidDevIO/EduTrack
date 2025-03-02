FROM node:lts

WORKDIR /app

COPY . /app

RUN npm run build && npm i -g serve

ENTRYPOINT ["serve", "-s", "dist", "-l", "3000"]