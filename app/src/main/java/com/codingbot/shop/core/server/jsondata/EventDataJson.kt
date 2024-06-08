package com.codingbot.shop.core.server.jsondata

import com.codingbot.shop.core.server.InitValue

val EventDataJson =
    """
        {
          "events": [
            {
              "id":0,
              "eventDateFrom": "2024-03-14",
              "eventDateTo": "2024-04-14",                     
              "hospital_id": 1,
              "eventName": "예쁘니깐 울랄라",
              "eventImg": "event_1.png",
              "surgeryIds": [0, 1,2,3],
              "desc": " 0 detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail descriptiondetail description\n detail description\n detail description\n detail description\n detail description"
            },
            {
              "id":1,
              "eventDateFrom": "2024-04-14",
              "eventDateTo": "2024-05-14",                     
              "hospital_id": 1,
              "eventName": "너이뻐 근데 더 이쁠래",
              "eventImg": "event_2.png",
              "surgeryIds": [0, 1,2],
              "desc": " 1 detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail descriptiondetail description\n detail description\n detail description\n detail description\n detail description"
            },
            {
              "id":2,
              "eventDateFrom": "2024-07-14",
              "eventDateTo": "2024-09-24",                     
              "hospital_id": 1,
              "eventName": "Awesome Beauty",
              "eventImg": "event_3.png",
              "surgeryIds": [0, 3],
              "desc": " 2 detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail descriptiondetail description\n detail description\n detail description\n detail description\n detail description"
            },
            {
              "id":3,
              "eventDateFrom": "2024-03-14",
              "eventDateTo": "2024-04-13",                     
              "hospital_id": 2,
              "eventName": "2번 이벤트1",
              "eventImg": "event_4.png",
              "surgeryIds": [0, 1],
              "desc": " 3 detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description"
            },
            {
              "id":4,
              "eventDateFrom": "2024-06-14",
              "eventDateTo": "2024-07-14",                     
              "hospital_id": 2,
              "eventName": "이벤트1",
              "eventImg": "event_5.png",
              "surgeryIds": [0, 2, 4],
              "desc": " 4 detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description"
            }, 
            {
              "id":5,
              "eventDateFrom": "2024-03-14",
              "eventDateTo": "2024-04-14",                     
              "hospital_id": 3,
              "eventName": "이벤트1",
              "eventImg": "event_6.png",
              "surgeryIds": [0, 1,2,3,5],
              "desc": " 5 detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description"
            },
            {
              "id":6,
              "eventDateFrom": "2024-07-14",
              "eventDateTo": "2024-08-24",                     
              "hospital_id": 2,
              "eventName": "이벤트1",
              "eventImg": "event_7.png",
              "surgeryIds": [0, 1,2,3,5 , 6,7,8],
              "desc": " 6 detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description"
            },
            {
              "id":7,
              "eventDateFrom": "2024-10-04",
              "eventDateTo": "2024-11-14",                     
              "hospital_id": 3,
              "eventName": "The K",
              "eventImg": "event_8.png",
              "surgeryIds": [1,2,3,4,5],
              "desc": " 7 detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description"
            },
            {
              "id":8,
              "eventDateFrom": "2024-05-14",
              "eventDateTo": "2024-08-14",                     
              "hospital_id": 1,
              "eventName": "K-Pop and K-Beauty",
              "eventImg": "event_9.png",
              "surgeryIds": [0,1,2,3,4,5],
              "desc": " 8 detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description"
            },
            {
              "id":9,
              "eventDateFrom": "2024-03-14",
              "eventDateTo": "2024-04-14",                     
              "hospital_id": 2,
              "eventName": "K-Beauty Beautiful",
              "eventImg": "event_10.png",
              "surgeryIds": [0,1,2,5],
              "desc": " 9 detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description"
            },
            {
              "id":10,
              "eventDateFrom": "2024-03-14",
              "eventDateTo": "2024-04-14",                     
              "hospital_id": 3,
              "eventName": "K-Beauty festival",
              "eventImg": "event_11.png",
              "surgeryIds": [0,1,2,3,5,7,8],
              "desc": " 10 detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description"
            },
            {
              "id":11,
              "eventDateFrom": "2024-03-14",
              "eventDateTo": "2024-04-14",                     
              "hospital_id": 1,
              "eventName": "We are the one",
              "eventImg": "event_6.png",
              "surgeryIds": [0,1,2,5],
              "desc": " 11 detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description\n detail description"
            }                                                           
          ]
        }
    """.trimIndent()