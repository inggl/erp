package handler

import (
	"github.com/gin-gonic/gin"
	"github.com/golang/mock/gomock"
	"github.com/inggl/erp/storage/internal/service"
	mock_service "github.com/inggl/erp/storage/internal/service/mocks"
	"github.com/stretchr/testify/assert"
	"net/http/httptest"
	"testing"
)

func TestBucket_GetBucketFile(t *testing.T) {
	gin.SetMode(gin.TestMode)

	type mockBehaviour = func(r *mock_service.MockBucketService, bucketId int)

	testCases := []struct {
		name               string
		buckedId           int
		mockBehaviour      mockBehaviour
		expectedStatusCode int
	}{
		{
			name:     "Ok",
			buckedId: 1000,
			mockBehaviour: func(r *mock_service.MockBucketService, bucketId int) {
				r.EXPECT().GetBucketFile(bucketId).Return()
			},
			expectedStatusCode: 200,
		},
	}

	for _, test := range testCases {
		t.Run(test.name, func(t *testing.T) {
			// Init deps
			c := gomock.NewController(t)
			defer c.Finish()

			bucketService := mock_service.NewMockBucketService(c)
			test.mockBehaviour(bucketService, test.buckedId)

			services := &service.Services{BucketService: bucketService}
			handler := Handler{services}

			// Init endpoint
			r := gin.New()
			r.GET("/", handler.getBucketFile)

			// Create Request
			w := httptest.NewRecorder()

			req := httptest.NewRequest("GET", "/api/storage/bucket/"+string(rune(test.buckedId)), nil)

			// Make Request
			r.ServeHTTP(w, req)

			// Assert
			assert.Equal(t, test.expectedStatusCode, w.Code)

		})
	}
}
