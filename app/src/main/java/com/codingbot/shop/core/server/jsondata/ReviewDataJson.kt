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
              "id":4,
              "hospital_id": 1,
              "surgeryId": [1, 2, 3, 4],
              "reviewImg":"",
              "userId": "user3",
              "reviewDesc": "좋은데 너무 오래기다려야함 ㅋ"                    
            }
          ]
        }
    """.trimIndent()