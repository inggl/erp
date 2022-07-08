package main

import (
	"flag"
	"fmt"
	"github.com/inggl/erp/storage/configs"
	"github.com/inggl/erp/storage/internal/handler"
	"github.com/inggl/erp/storage/internal/repository"
	"github.com/inggl/erp/storage/internal/service"
	"github.com/inggl/erp/storage/pkg/server"
	log "github.com/sirupsen/logrus"
	"github.com/spf13/viper"
	"os"
)

func main() {
	// environment := flag.String("e", "development", "")
	flag.Usage = func() {
		fmt.Println("Usage: server -e {mode}")
		os.Exit(1)
	}
	flag.Parse()

	if err := configs.Init(); err != nil {
		log.Fatalf("%s", err.Error())
	}

	app := server.NewApp()

	repos := repository.NewRepositories(nil)
	services := service.NewServices(repos)
	h := handler.NewHandler(services)

	if err := app.Run(viper.GetString("http.port"), h.InitRoutes()); err != nil {
		log.Fatalf("%s", err.Error())
	}
}
