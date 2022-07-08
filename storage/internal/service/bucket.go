package service

import (
	"github.com/inggl/erp/storage/internal/model"
	"github.com/inggl/erp/storage/internal/repository"
)

type bucketService struct {
	bucketRepository repository.BucketRepository
}

func NewBucketService(bucketRepository repository.BucketRepository) BucketService {
	return &bucketService{bucketRepository: bucketRepository}
}

func (b *bucketService) GetBucketFiles() ([]model.Bucket, error) {
	return b.bucketRepository.GetBucketFiles()
}

func (b *bucketService) GetBucketFile(bucketId int) (model.Bucket, error) {
	return b.bucketRepository.GetBucketFile(bucketId)
}
