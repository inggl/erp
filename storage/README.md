# Storage
- Gin
- Viper
- Air
- GoMock

## Prerequisites
### Air
Install
```shell
go install github.com/cosmtrek/air@latest
```

Init
```shell
air init
```

Run
```shell
air
```

### GoMock
Install
```shell
go install github.com/golang/mock/mockgen@latest
```

Run
```shell
mockgen -source=internal\service\services.go -destination=internal\service\mocks\mock.go
```

## Configuration
#### IntelliJ IDEA
> Settings > Languages & Frameworks > Go > Go Modules > Enable Go modules integration

## Init
```shell
go mod init storage
```

## Update
```shell
go mod tidy
```

## Add remote dependency
```shell
go get github.com/gin-gonic/gin
```

## Download dependencies
```shell
go mod download
```

## Run
```shell
go run main.go
```

