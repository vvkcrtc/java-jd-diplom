FROM node:20

EXPOSE 8081


WORKDIR /app

#COPY ../netology-diplom-frontend/package.json ./
COPY package.json ./

RUN npm install
#RUN npm install -g npm@9.8.1

COPY . .

CMD ["npm", "run", "serve"]