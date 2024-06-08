package com.codingbot.shop.core.server.jsondata

import com.codingbot.shop.core.server.InitValue

val ReviewDataJson =
    """
        {
          "reviews": [
            {
              "id":1,
              "hospital_id": 1,
              "surgeryId": [1,2],
              "reviewImg":"review_1.png",
              "userId": "user1",
              "reviewDesc": "시술 잘 받음 행벜"                    
            },
            {
              "id":2,
              "hospital_id": 1,
              "surgeryId": [3],
              "reviewImg":"review_2.png",
              "userId": "user2",
              "reviewDesc": "의사쌤 최고"                    
            },
            {
              "id":3,
              "hospital_id": 2,
              "surgeryId": [3,4],
              "reviewImg":"review_3.png",
              "userId": "user3",
              "reviewDesc": "존못 존잘로 만들어주심 ㅋ"                    
            },
            {
              "id":4,
              "hospital_id": 2,
              "surgeryId": [3,4],
              "reviewImg":"",
              "userId": "user3",
              "reviewDesc": "아히히 좋아 ㅋ"                    
            },
            {
              "id":5,
              "hospital_id": 1,
              "surgeryId": [1, 2, 3, 4],
              "reviewImg":"",
              "userId": "user3",
              "reviewDesc": "좋은데 너무 오래기다려야함 ㅋ"                    
            },
            {
              "id":6,
              "hospital_id": 1,
              "surgeryId": [1, 2, 3, 4],
              "reviewImg":"",
              "userId": "user4",
              "reviewDesc": "이것은 리뷰6"                    
            },
            {
              "id":7,
              "hospital_id": 1,
              "surgeryId": [1, 2, 3, 4],
              "reviewImg":"review_3.png",
              "userId": "user4",
              "reviewDesc": "이것은 리뷰6"                    
            },
            {
              "id":8,
              "hospital_id": 2,
              "surgeryId": [1, 2, 3, 4],
              "reviewImg":"review_3.png",
              "userId": "user5",
              "reviewDesc": "이것은 리뷰6"                    
            },
            {
              "id":9,
              "hospital_id": 2,
              "surgeryId": [2, 3, 6, 7],
              "reviewImg":"review_3.png",
              "userId": "user6",
              "reviewDesc": "이것은 리뷰7"                    
            },
            {
              "id":10,
              "hospital_id": 3,
              "surgeryId": [2, 3, 6, 7],
              "reviewImg":"review_1.png",
              "userId": "user7",
              "reviewDesc": "이것은 리뷰7"                    
            },
            {
              "id":11,
              "hospital_id": 4,
              "surgeryId": [2, 3, 4,5,6, 7],
              "reviewImg":"review_3.png",
              "userId": "user6",
              "reviewDesc": "이것은 리뷰8"                    
            },
            {
              "id":12,
              "hospital_id": 2,
              "surgeryId": [2, 3, 4,5,6, 7],
              "reviewImg":"review_2.png",
              "userId": "user7",
              "reviewDesc": "이것은 리뷰9"                    
            },
            {
              "id":13,
              "hospital_id": 5,
              "surgeryId": [2, 4, 7],
              "reviewImg":"",
              "userId": "user8",
              "reviewDesc": "이것은 리뷰10"                    
            },
            {
              "id":14,
              "hospital_id": 6,
              "surgeryId": [1, 4, 4],
              "reviewImg":"",
              "userId": "user9",
              "reviewDesc": "이것은 리뷰11"                    
            }
          ]
        }
    """.trimIndent()