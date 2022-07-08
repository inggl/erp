package repository

import (
	"errors"
	"github.com/inggl/erp/storage/internal/model"
	"github.com/jmoiron/sqlx"
)

type bucketRepository struct {
	db *sqlx.DB
}

func NewBucketRepository(db *sqlx.DB) BucketRepository {
	return &bucketRepository{
		db: db,
	}
}

func (r *bucketRepository) GetBucketFiles() ([]model.Bucket, error) {
	var buckets []model.Bucket

	return buckets, nil
}

func (r *bucketRepository) GetBucketFile(bucketId int) (model.Bucket, error) {
	if bucketId < 0 {
		return model.Bucket{}, errors.New("invalid bucket id")
	}

	return model.Bucket{Id: bucketId}, nil
}
