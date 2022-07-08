package handler

import (
	"github.com/gin-gonic/gin"
	"github.com/inggl/erp/storage/internal/service"
)

type Handler struct {
	services *service.Services
}

func NewHandler(services *service.Services) *Handler {
	return &Handler{services: services}
}

func (h *Handler) InitRoutes() *gin.Engine {
	router := gin.Default()

	router.Use(gin.Recovery(), gin.Logger())

	api := router.Group("/api")
	{
		storage := api.Group("/storage")
		{
			bucket := storage.Group("/bucket")
			{
				bucket.GET("/", h.getBucketFiles)
				bucket.GET("/:id", h.getBucketFile)
				bucket.POST("/", h.createBucketFile)
				bucket.PUT("/:id", h.updateBucketFile)
				bucket.DELETE("/:id", h.deleteBucketFile)
			}
		}
	}

	return router
}
