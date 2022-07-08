package repository

import (
	"github.com/inggl/erp/storage/internal/model"
	"github.com/jmoiron/sqlx"
)

type BucketRepository interface {
	GetBucketFiles() ([]model.Bucket, error)
	GetBucketFile(bucketId int) (model.Bucket, error)
}

type Repositories struct {
	BucketRepository
}

func NewRepositories(db *sqlx.DB) *Repositories {
	return &Repositories{
		BucketRepository: NewBucketRepository(db),
	}
}
