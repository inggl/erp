package service

import (
	"github.com/inggl/erp/storage/internal/model"
	"github.com/inggl/erp/storage/internal/repository"
)

type BucketService interface {
	GetBucketFiles() ([]model.Bucket, error)
	GetBucketFile(bucketId int) (model.Bucket, error)
}

type Services struct {
	BucketService
}

func NewServices(repos *repository.Repositories) *Services {
	return &Services{
		BucketService: NewBucketService(repos.BucketRepository),
	}
}
