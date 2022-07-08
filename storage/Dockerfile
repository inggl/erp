FROM golang:alpine

ENV PORT=8084

WORKDIR /go/src

COPY . .

RUN go build ./cmd/app

EXPOSE $PORT