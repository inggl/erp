package handler

import "github.com/gin-gonic/gin"

func (h *Handler) getBucketFiles(c *gin.Context) {
	_, err := h.services.BucketService.GetBucketFiles()
	if err != nil {
		return
	}
}

func (h *Handler) getBucketFile(c *gin.Context) {

}

func (h *Handler) createBucketFile(c *gin.Context) {

}

func (h *Handler) updateBucketFile(c *gin.Context) {

}

func (h *Handler) deleteBucketFile(c *gin.Context) {

}
