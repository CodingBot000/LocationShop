package com.codingbot.shop.core.server.jsondata

import com.codingbot.shop.core.server.InitValue
val ReviewDataJson =
    """
        {
          "reviews": [
            {
              "id":0,
              "hospital_id": 0,
              "surgeryId": [1,2],
              "reviewImg":"review_1.png",
              "userId": "user1",
              "reviewDesc": "The doctor was kind and explained everything in detail, so I was very satisfied."                    
            },
            {
              "id":1,
              "hospital_id": 0,
              "surgeryId": [3],
              "reviewImg":"review_2.png",
              "userId": "user2",
              "reviewDesc": "My skin improved a lot after the treatment. I would consider visiting again."                    
            },
            {
              "id":2,
              "hospital_id": 1,
              "surgeryId": [3,4],
              "reviewImg":"review_3.png",
              "userId": "user3",
              "reviewDesc": "The waiting time was a bit long, but the treatment results were excellent."                    
            },
            {
              "id":3,
              "hospital_id": 1,
              "surgeryId": [3,4],
              "reviewImg":"",
              "userId": "user3",
              "reviewDesc": "I was satisfied with the clean and modern facilities."                    
            },
            {
              "id":4,
              "hospital_id": 0,
              "surgeryId": [1, 2, 3, 4],
              "reviewImg":"",
              "userId": "user3",
              "reviewDesc": "I was a bit disappointed that the redness didn't subside after the procedure."                    
            },
            {
              "id":5,
              "hospital_id": 0,
              "surgeryId": [1, 2, 3, 4],
              "reviewImg":"",
              "userId": "user4",
              "reviewDesc": "My skin troubles have greatly improved. Thank you!"                    
            },
            {
              "id":6,
              "hospital_id": 0,
              "surgeryId": [1, 2, 3, 4],
              "reviewImg":"review_3.png",
              "userId": "user4",
              "reviewDesc": "The kind consultation and meticulous care were impressive."                    
            },
            {
              "id":7,
              "hospital_id": 1,
              "surgeryId": [1, 2, 3, 4],
              "reviewImg":"review_3.png",
              "userId": "user5",
              "reviewDesc": "It's a bit pricey, but the results are definite."                    
            },
            {
              "id":8,
              "hospital_id": 1,
              "surgeryId": [2, 3, 6, 7],
              "reviewImg":"review_3.png",
              "userId": "user6",
              "reviewDesc": "It's hard to get an appointment, but it's worth the wait."                    
            },
            {
              "id":9,
              "hospital_id": 2,
              "surgeryId": [2, 3, 6, 7],
              "reviewImg":"review_1.png",
              "userId": "user7",
              "reviewDesc": "My skin became much smoother after the treatment."                    
            },
            {
              "id":10,
              "hospital_id":3,
              "surgeryId": [2, 3, 4,5,6, 7],
              "reviewImg":"review_3.png",
              "userId": "user6",
              "reviewDesc": "It was my first visit, and I was very pleased."                    
            },
            {
              "id":11,
              "hospital_id": 1,
              "surgeryId": [2, 3, 4,5,6, 7],
              "reviewImg":"review_2.png",
              "userId": "user7",
              "reviewDesc": "The staff are friendly and the clinic is clean."                    
            },
            {
              "id":12,
              "hospital_id": 4,
              "surgeryId": [2, 4, 7],
              "reviewImg":"",
              "userId": "user8",
              "reviewDesc": "I'm highly satisfied with the results after the procedure. I want to visit again."                    
            },
            {
              "id":13,
              "hospital_id": 5,
              "surgeryId": [1, 4, 4],
              "reviewImg":"",
              "userId": "user9",
              "reviewDesc": "It's the best dermatology clinic. I want to keep going there."                    
            }
          ]
        }
    """.trimIndent()

